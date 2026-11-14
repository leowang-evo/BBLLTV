#!/usr/bin/env python3
"""Use the installed Gradle distribution with all writable caches in standalone."""
import os
import pathlib
import subprocess
import sys

ROOT = pathlib.Path(__file__).resolve().parent.parent
DEFAULT_JAVA = pathlib.Path('/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home')


def main():
    candidates = sorted((pathlib.Path.home() / '.gradle/wrapper/dists/gradle-9.5.0-bin').glob('*/gradle-9.5.0/bin/gradle'))
    launcher = str(candidates[0]) if candidates else str(ROOT / 'gradlew')
    environment = os.environ.copy()
    if not environment.get('JAVA_HOME') and DEFAULT_JAVA.exists():
        environment['JAVA_HOME'] = str(DEFAULT_JAVA)
    if not environment.get('ANDROID_HOME'):
        sdk = pathlib.Path.home() / 'Library/Android/sdk'
        if sdk.exists(): environment['ANDROID_HOME'] = str(sdk)
    environment['GRADLE_USER_HOME'] = str(ROOT / '.gradle-user-home')
    (ROOT / '.tmp').mkdir(exist_ok=True)
    command = [launcher, '--no-daemon', '--console=plain', '-Djava.io.tmpdir=' + str(ROOT / '.tmp'), *sys.argv[1:]]
    raise SystemExit(subprocess.run(command, cwd=ROOT, env=environment).returncode)


if __name__ == '__main__':
    main()
