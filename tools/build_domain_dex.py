#!/usr/bin/env python3
"""Build a device-loadable protocol test artifact using standalone classes only.

The output is a DEX archive for isolated test loading, not an installable app.
It has no manifest, resources or application UI. No original APK is an input.
"""
import hashlib
import json
import pathlib
import subprocess
import tempfile
import zipfile

ROOT = pathlib.Path(__file__).resolve().parent.parent
JAVA = '/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home/bin/java'
SDK = pathlib.Path.home() / 'Library/Android/sdk'


def main():
    report = json.loads((ROOT / 'domain-compile.json').read_text())
    if not report['success']:
        raise RuntimeError('Standalone domain compilation must pass first')
    classes = ROOT / 'domain/build/classes/java/main'
    dependencies = [pathlib.Path(p) for p in json.loads(
        (ROOT / 'dependencies/domain/classpath-files.json').read_text())]
    with tempfile.TemporaryDirectory(prefix='device-dex-', dir=ROOT / 'domain/build') as temp:
        stage = pathlib.Path(temp)
        program = stage / 'domain.jar'
        with zipfile.ZipFile(program, 'w') as jar:
            for path in sorted(classes.rglob('*.class')):
                jar.write(path, str(path.relative_to(classes)))
        dex = stage / 'dex'
        dex.mkdir()
        subprocess.run([JAVA, '-cp', str(SDK / 'build-tools/36.0.0/lib/d8.jar'),
                        'com.android.tools.r8.D8', '--min-api', '21', '--lib',
                        str(SDK / 'platforms/android-36/android.jar'), '--output', str(dex),
                        str(program), *map(str, dependencies)], check=True)
        output = ROOT / 'domain/build/domain-device.dex.zip'
        with zipfile.ZipFile(output, 'w') as archive:
            for path in sorted(dex.glob('*.dex')):
                archive.write(path, path.name)
    result = dict(artifact=str(output), sha256=hashlib.sha256(output.read_bytes()).hexdigest(),
                  installable_application=False, original_apk_input=False,
                  original_dex_input=False, header_jar_input=False,
                  published_dependencies=report['dependencies'])
    (ROOT / 'domain-device-build.json').write_text(json.dumps(result, indent=2) + '\n')
    print(json.dumps(result, indent=2))


if __name__ == '__main__':
    main()
