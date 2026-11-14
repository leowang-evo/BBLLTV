#!/usr/bin/env python3
"""Prepare AGP-only resource projection without duplicating upstream attr enums.

The complete recovered source remains untouched in app/src/main/res. Only an
attribute definition already supplied with the SAME values by a real upstream
AAR may be reduced to a reference in this generated build directory.
"""
import json
import pathlib
import shutil
import sys
import xml.etree.ElementTree as ET
import zipfile

ROOT = pathlib.Path(__file__).resolve().parent.parent


def enum_values(attr):
    return {child.attrib['name']: (child.tag, int(child.attrib['value'], 0))
            for child in attr if child.tag in ('enum', 'flag')}


def main():
    aar_list = pathlib.Path(sys.argv[1])
    output = pathlib.Path(sys.argv[2]).resolve()
    if not output.is_relative_to((ROOT / 'app/build').resolve()):
        raise RuntimeError('Projection output must remain under standalone/app/build')
    definitions = {}
    for path in json.loads(aar_list.read_text()):
        with zipfile.ZipFile(path) as archive:
            for name in archive.namelist():
                if not name.startswith('res/values') or not name.endswith('.xml'):
                    continue
                tree = ET.fromstring(archive.read(name))
                for attr in tree.iter('attr'):
                    values = enum_values(attr)
                    if values:
                        definitions.setdefault(attr.attrib['name'], []).append(dict(values=values, aar=path, resource=name))
    if output.exists():
        shutil.rmtree(output)
    shutil.copytree(ROOT / 'app/src/main/res', output)
    removed, conflicts = [], []
    for file in output.glob('values*/attrs.xml'):
        tree = ET.parse(file)
        changed = False
        for attr in list(tree.getroot()):
            if attr.tag != 'attr':
                continue
            name = attr.attrib.get('name')
            values = enum_values(attr)
            if not values or name not in definitions:
                continue
            matches = [item for item in definitions[name] if item['values'] == values]
            if not matches:
                conflicts.append(dict(resource=str(file.relative_to(output)), name=name, recovered=values,
                                      upstream=definitions[name]))
                continue
            # Let upstream supply the enum/flag definition. Public IDs are kept
            # by app's public.xml, and consumers may still reference the attr.
            tree.getroot().remove(attr)
            removed.append(dict(resource=str(file.relative_to(output)), name=name, values=values,
                                supplied_by=matches[0]['aar'], upstream_resource=matches[0]['resource']))
            changed = True
        if changed:
            tree.write(file, encoding='utf-8', xml_declaration=True)
    report = dict(projection=str(output), removed_identical_attr_definitions=removed,
                  mismatched_upstream_definitions=conflicts, source_resources_modified=False,
                  boundary='Generated AGP overlay only; unchanged complete source resources retain the resource-only AAPT2 path')
    (ROOT / 'app/build/resource-projection.json').write_text(json.dumps(report, indent=2))
    print('AGP resource projection: identical attr definitions delegated=', len(removed), 'mismatches=', len(conflicts))
    if conflicts:
        raise SystemExit(1)


if __name__ == '__main__':
    main()
