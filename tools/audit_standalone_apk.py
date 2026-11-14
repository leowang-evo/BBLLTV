#!/usr/bin/env python3
"""Audit normal AGP input provenance, source freshness and APK program payloads."""
import hashlib,json,sys,zipfile
from pathlib import Path
ROOT=Path(__file__).resolve().parents[1]
def sha(b):return hashlib.sha256(b).hexdigest()
def within(path,root):return path.is_relative_to(root)
inputs=json.loads((ROOT/'app/build/standalone-build-inputs.json').read_text())
variant=inputs.get('buildVariant','debug');cap=variant.capitalize()
standalone=Path(inputs['standaloneRoot']).resolve();sdk=Path(inputs['sdkRoot']).resolve();errors=[]
if standalone!=ROOT:errors.append('Exported project root differs from audit root')
forbidden=('compile-only','original-api','source-api-compile','bytecode-project','java-export','reconstructed-headers')
cache=standalone/'.gradle-user-home/caches'
builds=[standalone/x for x in ['app/build','domain/build','network/build','vendor/asimplecache/build']]
sources=[standalone/x for x in ['app/src/main/java','domain/src/main/java','network/src/main/java','vendor/asimplecache/src/main/java']]
def program_input(path):return within(path,cache) or within(path,sdk) or any(within(path,b)for b in builds)
entries=[]
for task in inputs['compileTasks']:
    for kind in ['sources','classpath','bootstrapClasspath']:
        if kind not in task:errors.append('Compile input export missing '+kind+': '+task['task'])
        for entry in task.get(kind,[]):entries.append((task['task'],kind,entry))
    if not any(within(Path(task['destination']).resolve(),b)for b in builds):errors.append('Unexpected compilation destination: '+task['destination'])
for task in inputs['packagingTasks']:
    for entry in task.get('inputs',[]):entries.append((task['task'],'packaging',entry))
for task,kind,entry in entries:
    p=Path(entry['path']).resolve()
    permitted=(any(within(p,s)for s in sources+builds) if kind=='sources' else program_input(p))
    # Signing keys are not program inputs and must remain outside the public source archive.
    if kind=='packaging' and p in {standalone/'keys/reconstruction-debug.keystore',Path.home()/'.android/debug.keystore'}:permitted=True
    if not permitted:errors.append(f'Input outside source/published-cache/module-build/SDK boundary: {task} {p}')
    if any(x in str(p).lower() for x in forbidden):errors.append(f'Forbidden reconstructed/original input: {task} {p}')
    if p.suffix.lower()=='.apk':errors.append(f'APK used as build input: {task} {p}')
    if kind=='sources' and p.suffix!='.java':errors.append(f'Unexpected Java program source: {p}')
    if p.is_file() and sha(p.read_bytes())!=entry['sha256']:errors.append('Input changed since AGP export: '+str(p))
for component in inputs['runtimeComponents']:
    if not (component['module'] or component['project']):errors.append('Unidentified runtime component: '+component['id'])
published_natives={}
published_aars=list((cache/'modules-2/files-2.1/com.kuaishou/akdanmaku/1.0.3').glob('*/*.aar'))
if len(published_aars)!=1:errors.append('Expected exactly one resolved official AkDanmaku AAR')
else:
    aar=published_aars[0]
    if hashlib.sha1(aar.read_bytes()).hexdigest()!=aar.parent.name:errors.append('Published AAR content differs from Gradle artifact identity')
    with zipfile.ZipFile(aar) as z:
        published_natives={n.split('/')[1]:sha(z.read(n))for n in z.namelist()if n.startswith('jni/') and n.endswith('/libgdx.so')}
    if set(published_natives)!={'armeabi-v7a','arm64-v8a','x86','x86_64'}:errors.append('Unexpected official native ABI inventory')
# This published coroutine diagnostic is a JVM class intentionally named .bin.
# It is accepted only by exact bytes, never by filename alone.
coroutine_probe_sha='ae8a082dd609313835ba93c3ef479a3af9903977d7bdc81df45bbd209ec7d0da'
packaged=[]
for apk in inputs['apks']:
    p=Path(apk['path']);data=p.read_bytes()
    if sha(data)!=apk['sha256']:errors.append('APK changed since AGP input export: '+str(p))
    with zipfile.ZipFile(p) as z:
        names=z.namelist();dex=[];nested=[];published_probes=[];natives=[]
        for n in names:
            if n.endswith('/'):continue
            b=z.read(n);magic=b[:4]
            if n.endswith('.dex') or magic==b'dex\n':
                if '/' in n or not n.startswith('classes') or not n.endswith('.dex'):errors.append('Unexpected nested/disguised DEX: '+n)
                dex.append({'name':n,'sha256':sha(b),'bytes':len(b)})
            if n.endswith(('.jar','.class','.apk')) or magic in [b'PK\x03\x04',b'\xca\xfe\xba\xbe']:
                if n=='DebugProbesKt.bin' and sha(b)==coroutine_probe_sha:
                    published_probes.append({'name':n,'sha256':sha(b),'upstream':'org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.7.3'})
                else:nested.append(n)
            if not n.endswith('.so'):continue
            parts=n.split('/')
            if len(parts)!=3 or parts[0]!='lib' or parts[2]!='libgdx.so':errors.append('Unrecognized native library: '+n);continue
            abi=parts[1]
            candidates=[ROOT/f'app/build/intermediates/stripped_native_libs/{variant}/strip{cap}DebugSymbols/out/lib/{abi}/libgdx.so',ROOT/f'app/build/intermediates/merged_native_libs/{variant}/merge{cap}NativeLibs/out/lib/{abi}/libgdx.so']
            matched=[str(c.relative_to(ROOT))for c in candidates if c.exists() and c.read_bytes()==b]
            if not matched:errors.append('APK native differs from AGP output: '+n)
            official=published_natives.get(abi)==sha(b)
            if not official:errors.append('APK native differs from official AkDanmaku AAR: '+n)
            natives.append({'name':n,'sha256':sha(b),'bytes':len(b),'matches_agp_output':matched,'matches_official_aar':official})
        if nested:errors.append('Nested/disguised program archive: '+str(nested))
        if not dex:errors.append('APK has no DEX: '+str(p))
        if {n['name'].split('/')[1]for n in natives}!=set(published_natives):errors.append('APK native ABI set differs from verified official artifact')
        packaged.append({'apk':str(p),'sha256':sha(data),'bytes':len(data),'dex':dex,'native_libraries':natives,'nested_program_archives':nested,'published_jvm_diagnostics':published_probes})
report={'pass':not errors,'errors':errors,'program_input_boundary':'JavaCompile sources, classpath and bootstrapClasspath; DEX/native/package inputs restricted to actual module source/build output, published Gradle cache and Android SDK; input hashes rechecked. Runtime graph has only Maven modules/source projects. APK magic scan and exact upstream native hashes checked.','sources_per_task':{x['task']:len(x['sources'])for x in inputs['compileTasks']},'inspected_input_entries':len(entries),'runtime_components':len(inputs['runtimeComponents']),'packaged':packaged,'evidence':'app/build/standalone-build-inputs.json','limits':'Build provenance is separate from functional parity; final TV tests remain necessary.'}
out=ROOT/'app/build/standalone-apk-audit.json';out.write_text(json.dumps(report,indent=2)+'\n');print(json.dumps({k:v for k,v in report.items()if k!='packaged'},indent=2));sys.exit(0 if report['pass'] else 1)
