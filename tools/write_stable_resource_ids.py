#!/usr/bin/env python3
"""Pin the recovered public resource IDs during AGP's own resource link."""
import pathlib
import sys
import xml.etree.ElementTree as ET

ROOT = pathlib.Path(__file__).resolve().parent.parent


def main():
    package, output = sys.argv[1], pathlib.Path(sys.argv[2])
    rows = []
    for entry in ET.parse(ROOT / 'app/src/main/res/values/public.xml').getroot():
        if entry.tag == 'public':
            rows.append(package + ':' + entry.attrib['type'] + '/' + entry.attrib['name'] + ' = ' + entry.attrib['id'])
    output.parent.mkdir(parents=True, exist_ok=True)
    output.write_text('\n'.join(rows) + '\n')
    print('Pinned resource IDs:', len(rows), 'package=', package)


if __name__ == '__main__':
    main()
