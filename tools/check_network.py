#!/usr/bin/env python3
"""Clean compile and test the network core against verified published artifacts."""
import hashlib
import json
import pathlib
import shutil
import subprocess
import tempfile

ROOT = pathlib.Path(__file__).resolve().parent.parent
JDK = pathlib.Path('/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home/bin')
TESTS = ['com.xx.blbl.network.RecoveredNetworkContractTest',
         'com.xx.blbl.network.NetworkManagerIntegrationTest',
         'com.xx.blbl.network.chat.WebSocketEchoContractTest']


def main():
    jars = []
    coordinates = []
    for scope in ('domain', 'network'):
        dependency_root = ROOT / 'dependencies' / scope
        provenance = json.loads((dependency_root / 'provenance.json').read_text())
        for entry in provenance['artifacts']:
            path = ROOT / entry['local_artifact']
            if not path.resolve().is_relative_to(dependency_root.resolve()):
                raise RuntimeError('Dependency escapes published-artifact directory: ' + str(path))
            if hashlib.sha256(path.read_bytes()).hexdigest() != entry['artifact_sha256']:
                raise RuntimeError('Dependency identity changed: ' + str(path))
            jars.append(path)
            coordinates.append(entry['coordinate'])
    domain = ROOT / 'domain/build/classes/java/main'
    if not domain.is_dir() or not json.loads((ROOT / 'domain-compile.json').read_text())['success']:
        raise RuntimeError('Run compile_domain.py first')
    build = ROOT / 'network/build'
    build.mkdir(exist_ok=True)
    sources = sorted((ROOT / 'network/src/main/java').rglob('*.java'))
    tests = sorted((ROOT / 'network/src/test/java').rglob('*.java'))
    report = dict(success=False, source_files=len(sources), test_files=len(tests),
                  dependencies=coordinates, original_apk_input=False, original_dex_input=False,
                  compiler_header_jar_input=False, external_http_requests=0, tests=[])
    with tempfile.TemporaryDirectory(prefix='clean-check-', dir=build) as temporary:
        output = pathlib.Path(temporary) / 'classes'
        output.mkdir()
        cp = ':'.join(map(str, [domain, *jars]))
        result = subprocess.run([str(JDK / 'javac'), '--release', '8', '-proc:none', '-sourcepath', '',
                                 '-Xmaxerrs', '500', '-classpath', cp, '-d', str(output),
                                 *map(str, sources + tests)], capture_output=True, text=True)
        (build / 'clean-javac.log').write_text(result.stdout + result.stderr)
        if result.returncode:
            print((result.stdout + result.stderr)[:18000])
        else:
            report['emitted_class_files'] = len(list(output.rglob('*.class')))
            for name in TESTS:
                try:
                    test = subprocess.run([str(JDK / 'java'), '-classpath', str(output) + ':' + cp, name],
                                          capture_output=True, text=True, timeout=30)
                    log = test.stdout + test.stderr
                    passed = test.returncode == 0
                except subprocess.TimeoutExpired as error:
                    log = (error.stdout or b'') + (error.stderr or b'')
                    if isinstance(log, bytes):
                        log = log.decode(errors='replace')
                    log += '\nTEST_PROCESS_TIMED_OUT\n'
                    passed = False
                log_path = build / (name.rsplit('.', 1)[-1] + '.log')
                log_path.write_text(log)
                report['tests'].append(dict(name=name, passed=passed,
                                            log=str(log_path.relative_to(ROOT))))
                print(log[-4000:])
            report['success'] = all(t['passed'] for t in report['tests'])
            if report['success']:
                final = build / 'classes/java/verified'
                if final.exists():
                    shutil.rmtree(final)
                final.parent.mkdir(parents=True, exist_ok=True)
                shutil.copytree(output, final)
    (ROOT / 'network-check.json').write_text(json.dumps(report, indent=2) + '\n')
    print(json.dumps(report, indent=2))
    if not report['success']:
        raise SystemExit(1)


if __name__ == '__main__':
    main()
