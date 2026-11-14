#!/usr/bin/env python3
"""Fetch only explicit published upstream artifacts and retain provenance/checksums."""
import argparse
import concurrent.futures
import hashlib
import json
import pathlib
import time
import urllib.error
import urllib.request
import zipfile

ROOT = pathlib.Path(__file__).resolve().parent.parent


def fetch(url):
    request = urllib.request.Request(url, headers={'User-Agent': 'BBLL-standalone-dependency-recovery/1'})
    for attempt in range(3):
        try:
            with urllib.request.urlopen(request, timeout=40) as response:
                return response.read()
        except urllib.error.HTTPError as error:
            if error.code not in (429, 500, 502, 503, 504) or attempt == 2:
                raise
        except (TimeoutError, urllib.error.URLError):
            if attempt == 2:
                raise
        time.sleep(attempt + 1)


def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--scope', default='domain')
    args = parser.parse_args()
    manifest = json.loads((ROOT / 'dependencies.json').read_text())
    output = ROOT / 'dependencies' / args.scope
    output.mkdir(parents=True, exist_ok=True)
    previous_file = output / 'provenance.json'
    previous = {a['coordinate'] + ':' + a.get('classifier', ''): a for a in json.loads(previous_file.read_text())['artifacts']} if previous_file.exists() else {}
    reports, classpath, classes, failures = [], [], {}, []
    def download(artifact):
        group, name, version = artifact['coordinate'].split(':')
        base = artifact['repository'].rstrip('/') + '/' + group.replace('.', '/') + '/' + name + '/' + version + '/' + name + '-' + version
        artifact_base = base + ('-' + artifact['classifier'] if artifact.get('classifier') else '')
        suffix = '.' + artifact['packaging']
        path = output / (name + '-' + version + ('-' + artifact['classifier'] if artifact.get('classifier') else '') + suffix)
        cached = previous.get(artifact['coordinate'] + ':' + artifact.get('classifier', ''))
        pom_path = output / (name + '-' + version + '.pom')
        if cached and path.exists() and pom_path.exists() and hashlib.sha256(path.read_bytes()).hexdigest() == cached['artifact_sha256']:
            data = path.read_bytes()
            checksum = cached['published_sha1']
            pom = pom_path.read_bytes()
            if hashlib.sha256(pom).hexdigest() != cached['pom_sha256']:
                raise RuntimeError('Cached provenance POM changed: ' + artifact['coordinate'])
        else:
            data = fetch(artifact_base + suffix)
            checksum = fetch(artifact_base + suffix + '.sha1').decode('ascii').strip().split()[0].lower()
            pom = fetch(base + '.pom')
        if hashlib.sha1(data).hexdigest() != checksum:
            raise RuntimeError('Published SHA1 mismatch: ' + artifact['coordinate'])
        path.write_bytes(data)
        pom_path.write_bytes(pom)
        compile_path = path
        if suffix == '.aar':
            with zipfile.ZipFile(path) as aar:
                compile_path = output / (name + '-' + version + '-classes.jar')
                compile_path.write_bytes(aar.read('classes.jar'))
        with zipfile.ZipFile(compile_path) as jar:
            artifact_classes = [entry[:-6] for entry in jar.namelist() if entry.endswith('.class') and not entry.startswith('META-INF/versions/')]
        with zipfile.ZipFile(path) as archive:
            native_entries = [dict(name=n, sha256=hashlib.sha256(archive.read(n)).hexdigest()) for n in archive.namelist() if n.endswith('.so')]
        report = dict(artifact, url=artifact_base + suffix, pom_url=base + '.pom',
                      local_artifact=str(path.relative_to(ROOT)), local_compile_jar=str(compile_path.relative_to(ROOT)),
                      artifact_sha256=hashlib.sha256(data).hexdigest(), published_sha1=checksum,
                      published_sha1_verified=True, pom_sha256=hashlib.sha256(pom).hexdigest(),
                      classes=len(artifact_classes), native_entries=native_entries,
                      origin='published upstream Maven artifact; not original APK')
        print(artifact['coordinate'], len(data), 'bytes; official SHA1 verified', flush=True)
        return report, str(compile_path.resolve()), artifact_classes
    with concurrent.futures.ThreadPoolExecutor(max_workers=6) as executor:
        futures = {executor.submit(download, artifact): artifact for artifact in manifest['artifacts'] if artifact['scope'] == args.scope}
        for future in concurrent.futures.as_completed(futures):
            artifact = futures[future]
            try:
                report, jar, artifact_classes = future.result()
                reports.append(report)
                classpath.append(jar)
                for cls in artifact_classes:
                    classes.setdefault(cls, []).append(artifact['coordinate'])
            except Exception as error:
                failures.append(dict(coordinate=artifact['coordinate'], error=type(error).__name__ + ': ' + str(error)))
                print('FAILED', artifact['coordinate'], type(error).__name__, str(error), flush=True)
    if not reports:
        raise RuntimeError('No explicit artifacts selected')
    classpath.sort()
    reports.sort(key=lambda a: a['coordinate'])
    (output / 'classpath.txt').write_text(':'.join(classpath) + '\n')
    (output / 'classpath-files.json').write_text(json.dumps(classpath, indent=2))
    (output / 'classes.json').write_text(json.dumps(classes, indent=2))
    (output / 'provenance.json').write_text(json.dumps(dict(scope=args.scope, artifacts=reports, complete=not failures, failed_artifacts=failures,
                                                         boundary=manifest['boundary']), ensure_ascii=False, indent=2))
    if failures:
        raise SystemExit(1)


if __name__ == '__main__':
    main()
