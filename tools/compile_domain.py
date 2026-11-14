#!/usr/bin/env python3
"""Compile the standalone domain using only a JDK and published upstream JARs."""
import hashlib
import json
import pathlib
import shutil
import subprocess
import tempfile

ROOT = pathlib.Path(__file__).resolve().parent.parent
JAVAC = pathlib.Path('/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home/bin/javac')


def main():
    sources = sorted((ROOT / 'domain/src/main/java').rglob('*.java'))
    if not sources:
        raise RuntimeError('No generated domain sources; the source generator is maintained separately')
    dependencies = ROOT / 'dependencies/domain'
    jars = [pathlib.Path(p) for p in json.loads((dependencies / 'classpath-files.json').read_text())]
    if any(not p.is_relative_to(dependencies.resolve()) for p in jars):
        raise RuntimeError('Classpath may only contain published dependency artifacts under this standalone project')
    provenance = json.loads((dependencies / 'provenance.json').read_text())
    for artifact in provenance['artifacts']:
        file = ROOT / artifact['local_artifact']
        if hashlib.sha256(file.read_bytes()).hexdigest() != artifact['artifact_sha256']:
            raise RuntimeError('Published dependency bytes changed: ' + str(file))
    build = ROOT / 'domain/build'
    build.mkdir(parents=True, exist_ok=True)
    with tempfile.TemporaryDirectory(prefix='javac-', dir=build) as temporary:
        output = pathlib.Path(temporary) / 'classes'
        output.mkdir()
        command = [str(JAVAC), '--release', '8', '-proc:none', '-sourcepath', '', '-Xmaxerrs', '1000',
                   '-classpath', ':'.join(map(str, jars)), '-d', str(output), *map(str, sources)]
        result = subprocess.run(command, capture_output=True, text=True)
        diagnostics = result.stdout + result.stderr
        (build / 'javac-diagnostics.log').write_text(diagnostics)
        emitted = len(list(output.rglob('*.class')))
        report = dict(success=result.returncode == 0, source_files=len(sources), emitted_class_files=emitted,
                      dependencies=[a['coordinate'] for a in provenance['artifacts']],
                      android_jar_used=False, original_apk_dex_used=False, original_native_libraries_used=False,
                      compiler_header_jar_used=False, diagnostics='domain/build/javac-diagnostics.log')
        (ROOT / 'domain-compile.json').write_text(json.dumps(report, indent=2))
        if result.returncode == 0:
            final = build / 'classes/java/main'
            if final.exists():
                shutil.rmtree(final)
            final.parent.mkdir(parents=True, exist_ok=True)
            shutil.copytree(output, final)
        print(json.dumps(report, indent=2))
        if result.returncode:
            print(diagnostics[:16000])
            raise SystemExit(result.returncode)


if __name__ == '__main__':
    main()
