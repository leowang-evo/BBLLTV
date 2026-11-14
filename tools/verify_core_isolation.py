#!/usr/bin/env python3
"""Rebuild/test only the source core in a fresh directory outside this workspace.

No APK, recovered bytecode, compiler declarations, existing class output or
cached dependency files are copied. Published dependencies are downloaded anew.
The full Android UI application has a separate, still-required acceptance gate.
"""
import hashlib
import json
import pathlib
import shutil
import subprocess
import sys
import tempfile

ROOT = pathlib.Path(__file__).resolve().parent.parent


def main():
    steps = []
    with tempfile.TemporaryDirectory(prefix='bbll-source-core-') as temporary:
        project = pathlib.Path(temporary)
        for module in ('domain', 'network'):
            shutil.copytree(ROOT / module / 'src', project / module / 'src')
        shutil.copyfile(ROOT / 'dependencies.json', project / 'dependencies.json')
        (project / 'tools').mkdir()
        for name in ('fetch_dependencies.py', 'compile_domain.py', 'check_network.py'):
            shutil.copyfile(ROOT / 'tools' / name, project / 'tools' / name)
        copied = sorted(p for p in project.rglob('*') if p.is_file())
        unexpected = [str(p) for p in copied if p.suffix in ('.apk', '.dex', '.jar', '.aar', '.class', '.so', '.smali')]
        if unexpected:
            raise RuntimeError('Unexpected binary build input: ' + repr(unexpected))
        manifest = {str(p.relative_to(project)): hashlib.sha256(p.read_bytes()).hexdigest() for p in copied}
        commands = [
            ['fetch_dependencies.py', '--scope', 'domain'],
            ['fetch_dependencies.py', '--scope', 'network'],
            ['compile_domain.py'], ['check_network.py'],
        ]
        for command in commands:
            result = subprocess.run([sys.executable, str(project / 'tools' / command[0]), *command[1:]],
                                    capture_output=True, text=True, cwd=project)
            log = result.stdout + result.stderr
            steps.append(dict(command=command, passed=result.returncode == 0, log=log))
            print(command[0], 'PASS' if result.returncode == 0 else 'FAIL', flush=True)
            if result.returncode:
                print(log[-12000:])
                break
        reports = {}
        for name in ('domain-compile.json', 'network-check.json'):
            if (project / name).exists(): reports[name] = json.loads((project / name).read_text())
    report = dict(passed=len(steps) == 4 and all(s['passed'] for s in steps),
                  scope='domain and network only; full Android application acceptance is still required',
                  fresh_directory_outside_workspace=True, copied_original_apk=False, copied_original_bytecode=False,
                  copied_compile_headers=False, copied_precompiled_classes=False, copied_cached_dependencies=False,
                  source_manifest=manifest, steps=steps, reports=reports)
    (ROOT / 'core-isolation-verification.json').write_text(json.dumps(report, ensure_ascii=False, indent=2) + '\n')
    if not report['passed']:
        raise SystemExit(1)


if __name__ == '__main__': main()
