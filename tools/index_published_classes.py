#!/usr/bin/env python3
"""Index upstream class hierarchy and members without executing library code."""
import argparse
import json
import math
import pathlib
import struct
import zipfile

ROOT = pathlib.Path(__file__).resolve().parent.parent


class Reader:
    def __init__(self, data):
        self.data, self.offset = data, 0

    def take(self, count):
        value = self.data[self.offset:self.offset + count]
        self.offset += count
        if len(value) != count:
            raise ValueError('Truncated class file')
        return value

    def number(self, fmt):
        return struct.unpack('>' + fmt, self.take(struct.calcsize('>' + fmt)))[0]

    def u1(self): return self.number('B')
    def u2(self): return self.number('H')
    def u4(self): return self.number('I')


class ClassFile(Reader):
    def __init__(self, data):
        super().__init__(data)
        if self.u4() != 0xcafebabe:
            raise ValueError('Not a class file')
        self.minor, self.major = self.u2(), self.u2()
        self.cp = [None] * self.u2()
        index = 1
        while index < len(self.cp):
            tag = self.u1()
            if tag == 1:
                value = self.take(self.u2()).decode('utf-8', 'replace')
            elif tag == 3:
                value = self.number('i')
            elif tag == 4:
                value = self.number('f')
            elif tag == 5:
                value = self.number('q')
            elif tag == 6:
                value = self.number('d')
            elif tag in (7, 8, 16, 19, 20):
                value = self.u2()
            elif tag in (9, 10, 11, 12, 17, 18):
                value = (self.u2(), self.u2())
            elif tag == 15:
                value = (self.u1(), self.u2())
            else:
                raise ValueError('Unknown constant-pool tag ' + str(tag))
            self.cp[index] = (tag, value)
            index += 2 if tag in (5, 6) else 1

    def constant(self, index):
        if index == 0:
            return None
        tag, value = self.cp[index]
        if tag in (7, 8, 16, 19, 20):
            return self.constant(value)
        if isinstance(value, float) and not math.isfinite(value):
            return str(value)
        return value

    def annotation_value(self, reader):
        tag = chr(reader.u1())
        if tag in 'BCDFIJSZs':
            return self.constant(reader.u2())
        if tag == 'e':
            return dict(enum_type=self.constant(reader.u2()), name=self.constant(reader.u2()))
        if tag == 'c':
            return dict(class_descriptor=self.constant(reader.u2()))
        if tag == '@':
            return self.annotation(reader)
        if tag == '[':
            return [self.annotation_value(reader) for _ in range(reader.u2())]
        raise ValueError('Unknown annotation tag ' + tag)

    def annotation(self, reader):
        kind = self.constant(reader.u2())
        values = {self.constant(reader.u2()): self.annotation_value(reader) for _ in range(reader.u2())}
        if kind == 'Lkotlin/Metadata;':
            values = {k: v for k, v in values.items() if k in ('mv', 'bv', 'k', 'xi', 'pn', 'xs')}
        return dict(type=kind, values=values)

    def attributes(self):
        result = {}
        for _ in range(self.u2()):
            name, length = self.constant(self.u2()), self.u4()
            payload = Reader(self.take(length))
            if name == 'Signature':
                result['signature'] = self.constant(payload.u2())
            elif name == 'ConstantValue':
                result['constant_value'] = self.constant(payload.u2())
            elif name == 'SourceFile':
                result['source_file'] = self.constant(payload.u2())
            elif name == 'Exceptions':
                result['throws'] = [self.constant(payload.u2()) for _ in range(payload.u2())]
            elif name in ('RuntimeVisibleAnnotations', 'RuntimeInvisibleAnnotations'):
                result[name] = [self.annotation(payload) for _ in range(payload.u2())]
            elif name == 'AnnotationDefault':
                result['annotation_default'] = self.annotation_value(payload)
            elif name == 'InnerClasses':
                result['inner_classes'] = [dict(inner=self.constant(payload.u2()), outer=self.constant(payload.u2()),
                                                name=self.constant(payload.u2()), access=payload.u2()) for _ in range(payload.u2())]
            elif name == 'EnclosingMethod':
                owner, method = self.constant(payload.u2()), self.constant(payload.u2())
                result['enclosing_method'] = dict(owner=owner, method=[self.constant(v) for v in method] if method else None)
        return result

    def member(self):
        access, name, descriptor = self.u2(), self.constant(self.u2()), self.constant(self.u2())
        return dict(name=name, descriptor=descriptor, access_flags=access, **self.attributes())

    def parse(self):
        access, name, parent = self.u2(), self.constant(self.u2()), self.constant(self.u2())
        interfaces = [self.constant(self.u2()) for _ in range(self.u2())]
        fields = [self.member() for _ in range(self.u2())]
        methods = [self.member() for _ in range(self.u2())]
        return dict(name=name, major_version=self.major, access_flags=access, superclass=parent,
                    interfaces=interfaces, fields=fields, methods=methods, **self.attributes())


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--scope', default='android')
    args = parser.parse_args()
    output = ROOT / 'dependencies' / args.scope
    artifacts = json.loads((output / 'provenance.json').read_text())['artifacts']
    details, errors = {}, []
    for artifact in artifacts:
        path = ROOT / artifact['local_compile_jar']
        with zipfile.ZipFile(path) as jar:
            for entry in jar.namelist():
                if not entry.endswith('.class') or entry.startswith('META-INF/'):
                    continue
                try:
                    info = ClassFile(jar.read(entry)).parse()
                    info['coordinate'] = artifact['coordinate']
                    info['artifact_sha256'] = artifact['artifact_sha256']
                    details.setdefault(info['name'], []).append(info)
                except Exception as error:
                    errors.append(dict(coordinate=artifact['coordinate'], entry=entry, error=str(error)))
    (output / 'class-details.json').write_text(json.dumps(details, ensure_ascii=False, indent=2))
    report = dict(classes=len(details), methods=sum(len(x['methods']) for v in details.values() for x in v),
                  fields=sum(len(x['fields']) for v in details.values() for x in v), errors=errors,
                  scope='Published upstream class files only; no original APK/header/code loaded or executed')
    (output / 'class-index-summary.json').write_text(json.dumps(report, indent=2))
    print(json.dumps(report, indent=2))
    if errors:
        raise SystemExit(1)


if __name__ == '__main__':
    main()
