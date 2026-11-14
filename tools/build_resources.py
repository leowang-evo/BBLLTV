#!/usr/bin/env python3
"""Compile recovered XML/image resources with the ordinary SDK AAPT2.

This is a resource-only build, not a working application APK. It consumes
materialized recovered resource sources, never original DEX/native/header data.
"""
import argparse
import hashlib
import json
import pathlib
import re
import shutil
import subprocess
import struct
import xml.etree.ElementTree as ET

ROOT = pathlib.Path(__file__).resolve().parent.parent
SOURCE = ROOT.parent / 'java-export/app/src/main/res'
SDK = pathlib.Path.home() / 'Library/Android/sdk'


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--sync', action='store_true', help='Materialize recovered XML/image files into standalone/app resources')
    parser.add_argument('--repair-ninepatch', action='store_true', help='Restore editable borders from already recovered nine-patch PNG source assets')
    parser.add_argument('--repair-exported-numbers', action='store_true', help='Remove JADX locale thousands separators from numeric dimension literals')
    parser.add_argument('--recover-xml', action='store_true', help='Materialize symbolic editable XML recovered from the original resource table, using JADX ID-name aliases')
    args = parser.parse_args()
    resources = ROOT / 'app/src/main/res'
    if args.sync:
        if resources.exists():
            raise RuntimeError('Resources already materialized; refusing to overwrite local edits')
        resources.parent.mkdir(parents=True, exist_ok=True)
        shutil.copytree(SOURCE, resources)
    if not resources.exists():
        raise RuntimeError('Run with --sync once to materialize recovered resource sources')
    build = ROOT / 'resource-build'
    build.mkdir(exist_ok=True)
    repairs = []
    if args.repair_ninepatch:
        for target in resources.rglob('*.9.png'):
            before = target.read_bytes()
            if b'npTc' not in before:
                continue
            recovered = ROOT.parent / 'bytecode-project/res' / target.relative_to(resources)
            after = recovered.read_bytes()
            old_size = struct.unpack_from('>II', before, 16)
            new_size = struct.unpack_from('>II', after, 16)
            if b'npTc' in after or new_size != (old_size[0] + 2, old_size[1] + 2):
                raise RuntimeError('Recovered nine-patch is not an editable border reconstruction: ' + str(recovered))
            target.write_bytes(after)
            repairs.append(dict(resource=str(target.relative_to(resources)), recovered_source=str(recovered),
                                before_sha256=hashlib.sha256(before).hexdigest(), after_sha256=hashlib.sha256(after).hexdigest(),
                                compiled_size=old_size, editable_border_size=new_size))
        (build / 'ninepatch-recovery.json').write_text(json.dumps(repairs, indent=2))
    numeric_repairs = []
    if args.repair_exported_numbers:
        for target in resources.rglob('dimens.xml'):
            text = target.read_text()
            def repair(match):
                value = match[2]
                if not re.fullmatch(r'-?\d{1,3}(?:,\d{3})+(?:\.\d+)?(?:dp|dip|sp|px|pt|in|mm)', value):
                    return match[0]
                restored = value.replace(',', '')
                numeric_repairs.append(dict(resource=str(target.relative_to(resources)), name=match[1], exported=value, restored=restored))
                return '<dimen name="' + match[1] + '">' + restored + '</dimen>'
            repaired = re.sub(r'<dimen name="([^"]+)">([^<]+)</dimen>', repair, text)
            if repaired != text:
                target.write_text(repaired)
        (build / 'dimension-locale-recovery.json').write_text(json.dumps(numeric_repairs, indent=2))
    xml_recovery = []
    if args.recover_xml:
        recovered_root = ROOT.parent / 'bytecode-project/res'
        before_public = ET.parse(recovered_root / 'values/public.xml').getroot()
        exported_public = ET.parse(resources / 'values/public.xml').getroot()
        by_id = {entry.attrib['id']: (entry.attrib['type'], entry.attrib['name']) for entry in exported_public if entry.tag == 'public'}
        aliases = {}
        for entry in before_public:
            if entry.tag != 'public' or entry.attrib['id'] not in by_id:
                continue
            kind, exported_name = by_id[entry.attrib['id']]
            if kind != entry.attrib['type']:
                raise RuntimeError('Resource type changed for ID ' + entry.attrib['id'])
            if exported_name != entry.attrib['name']:
                aliases[(kind, entry.attrib['name'])] = exported_name
        for source in recovered_root.rglob('*.xml'):
            relative = source.relative_to(recovered_root)
            kind = relative.parts[0].split('-', 1)[0]
            target = resources / relative
            if kind != 'values' and (kind, source.stem) in aliases:
                target = target.with_name(aliases[(kind, source.stem)] + '.xml')
            text = source.read_text()
            for (resource_type, raw_name), alias in aliases.items():
                text = text.replace('@' + resource_type + '/' + raw_name, '@' + resource_type + '/' + alias)
                if kind == 'values':
                    text = text.replace('name="' + raw_name + '"', 'name="' + alias + '"')
            target.parent.mkdir(parents=True, exist_ok=True)
            target.write_text(text)
            xml_recovery.append(dict(resource=str(target.relative_to(resources)), recovered_source=str(source),
                                     sha256=hashlib.sha256(text.encode()).hexdigest()))
        (build / 'symbolic-xml-recovery.json').write_text(json.dumps(dict(resource_name_aliases=[dict(type=t, original=n, standalone=a) for (t,n),a in aliases.items()], files=xml_recovery), indent=2))
    manifest = build / 'AndroidManifest.xml'
    manifest.write_text('''<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.xx.blbl">
    <uses-sdk android:minSdkVersion="21" android:targetSdkVersion="35"/>
    <application android:label="@string/app_name"/>
</manifest>
''')
    aapt = SDK / 'build-tools/36.0.0/aapt2'
    compile_result = subprocess.run([str(aapt), 'compile', '--dir', str(resources), '-o', str(build / 'compiled.zip')], capture_output=True, text=True)
    (build / 'compile.log').write_text(compile_result.stdout + compile_result.stderr)
    report = dict(resource_files=len(list(resources.rglob('*'))), aapt2=str(aapt), sdk_platform='android-36',
                  original_apk_dex_used=False, original_native_libraries_used=False,
                  compiler_header_jar_used=False, patched_apktool_aapt_used=False,
                  ninepatch_borders_restored=len(repairs),
                  exported_dimension_numbers_restored=len(numeric_repairs),
                  symbolic_xml_sources_restored=len(xml_recovery),
                  compile_success=compile_result.returncode == 0, link_success=False)
    if compile_result.returncode == 0:
        java = build / 'generated'
        java.mkdir(exist_ok=True)
        result = subprocess.run([str(aapt), 'link', '-I', str(SDK / 'platforms/android-36/android.jar'),
                                 '--manifest', str(manifest), '--java', str(java), '--emit-ids', str(build / 'resource-ids.txt'),
                                 '--auto-add-overlay', '-o', str(build / 'resources.ap_'), str(build / 'compiled.zip')], capture_output=True, text=True)
        (build / 'link.log').write_text(result.stdout + result.stderr)
        report['link_success'] = result.returncode == 0
        report['generated_r_java'] = str(java / 'com/xx/blbl/R.java') if result.returncode == 0 else None
        if result.returncode:
            print((result.stdout + result.stderr)[:6000])
    else:
        print((compile_result.stdout + compile_result.stderr)[:6000])
    (ROOT / 'resource-build.json').write_text(json.dumps(report, indent=2))
    print(json.dumps(report, indent=2))
    if not report['link_success']:
        raise SystemExit(1)


if __name__ == '__main__':
    main()
