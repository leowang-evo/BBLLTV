#!/usr/bin/env python3
"""Build an exported source tree, with no project binaries/transforms/build cache.
--quarantine-legacy additionally moves this workspace's legacy BBLL/APK folders
out of their normal paths during the build, restoring them in a finally block.
"""
import argparse,hashlib,json,os,pathlib,shutil,subprocess,sys,tempfile,time,zipfile
ROOT=pathlib.Path(__file__).resolve().parents[1]
def digest(p):return hashlib.sha256(p.read_bytes()).hexdigest()
def snapshot(root):return {str(p.relative_to(root)):digest(p)for p in sorted(root.rglob('*'))if p.is_file()}
def main():
    parser = argparse.ArgumentParser()
    parser.add_argument('--quarantine-legacy', action='store_true')
    parser.add_argument('--version-code', type=int, default=128)
    parser.add_argument('--variant', choices=['debug','benchmark'], default='debug')
    args = parser.parse_args()
    start=time.time();work=pathlib.Path(tempfile.mkdtemp(prefix='bbll-source-only-'));project=work/'project';project.mkdir()
    selected=['README.md','.gitignore','build.gradle','settings.gradle','gradle.properties','gradlew','gradlew.bat','gradle/wrapper',
      'docs','app/build.gradle','app/src','domain/build.gradle','domain/src','network/build.gradle','network/src',
      'vendor/asimplecache/build.gradle','vendor/asimplecache/src','vendor/asimplecache/LICENSE','vendor/asimplecache/provenance.json',
      'keys/README.txt','tools','dependencies.json']
    for name in selected:
        src=ROOT/name;dest=project/name;dest.parent.mkdir(parents=True,exist_ok=True)
        if src.is_dir():shutil.copytree(src,dest,ignore=shutil.ignore_patterns('__pycache__','*.pyc'))
        else:shutil.copy2(src,dest)
    files=snapshot(project)
    forbidden=[n for n in files if pathlib.Path(n).suffix.lower()in['.apk','.dex','.class','.aar','.so','.jar','.keystore','.jks','.p12','.pfx','.pem'] and n!='gradle/wrapper/gradle-wrapper.jar']
    if forbidden:raise RuntimeError('Non-source program payloads in export: '+str(forbidden))
    source_archive=work/'bbll-independent-source.zip'
    with zipfile.ZipFile(source_archive,'w',zipfile.ZIP_DEFLATED)as z:
        for name in files:z.write(project/name,'bbll-independent/'+name)
    # Only original Maven downloads/metadata are reused. No transforms, task history,
    # own compiled classes, DEX or build caches are copied into the empty project.
    published=ROOT/'.gradle-user-home/caches/modules-2'
    shutil.copytree(published,project/'.gradle-user-home/caches/modules-2')
    moved=[];commands=[];failure=None;passed=False
    try:
        if args.quarantine_legacy:
            legacy=ROOT.parent;artifacts=ROOT.parents[2]/'artifacts'
            if legacy.name!='bbll' or not artifacts.is_dir():raise RuntimeError('Legacy quarantine requires the known BBLL workspace layout')
            for src in [legacy,artifacts]:
                dst=src.with_name(src.name+'.source-isolation-'+str(os.getpid()))
                src.rename(dst);moved.append((src,dst))
            if any(src.exists()for src,_ in moved):raise RuntimeError('Legacy paths remain accessible')
        log=work/'gradle.log'
        command=[sys.executable,str(project/'tools/run_gradle.py'),'clean',':app:exportStandaloneBuildAudit','--offline','--no-build-cache','--rerun-tasks','-PvalidationApplicationId=com.damon.bblab',f'-PvalidationVersionCode={args.version_code}',f'-PauditVariant={args.variant}']
        with log.open('w')as out:
            result=subprocess.run(command,cwd=project,stdout=out,stderr=subprocess.STDOUT)
        commands.append({'command':command,'returncode':result.returncode,'log':str(log)})
        if result.returncode:raise RuntimeError('Isolated Gradle build failed; inspect '+str(log))
        command=[sys.executable,str(project/'tools/audit_standalone_apk.py')]
        result=subprocess.run(command,cwd=project,text=True,capture_output=True)
        (work/'audit.log').write_text(result.stdout+result.stderr);commands.append({'command':command,'returncode':result.returncode,'log':str(work/'audit.log')})
        if result.returncode:raise RuntimeError('Isolated program input audit failed')
        changed=[name for name,hash_value in files.items()if digest(project/name)!=hash_value]
        if changed:raise RuntimeError('Exported source changed while building: '+str(changed))
        passed=True
    except Exception as error:failure=str(error)
    finally:
        for src,dst in reversed(moved):
            if src.exists():raise RuntimeError('Cannot restore quarantined path; unexpected replacement exists: '+str(src))
            dst.rename(src)
    current={name:digest(ROOT/name)for name in files}
    if current!=files:passed=False;failure='Original source changed after export'
    out=ROOT/'dist';out.mkdir(exist_ok=True)
    shutil.copy2(source_archive,out/source_archive.name)
    source_manifest={'files':files,'file_count':len(files),'java_files':sum(name.endswith('.java')for name in files),'sha256':digest(source_archive)}
    (out/'independent-source-manifest.json').write_text(json.dumps(source_manifest,indent=2)+'\n')
    report={'build_variant':args.variant,'debuggable':args.variant=='debug','pass':passed,'failure':failure,'elapsed_seconds':round(time.time()-start,2),'isolated_project':str(project),'source_archive_sha256':digest(source_archive),
      'exported_files':len(files),'java_files':source_manifest['java_files'],'source_unchanged_during_build':current==files,
      'initial_project_program_binaries':forbidden,'reused_input':'Only publicly resolved Maven artifact downloads/metadata; no project build outputs, Gradle transforms or build cache',
      'legacy_paths_physically_absent_during_build':[str(src)for src,_ in moved],'legacy_paths_restored':all(src.exists()and not dst.exists()for src,dst in moved),'commands':commands,
      'functional_validation':'Device regression is recorded separately; this build harness does not interact with devices.'}
    if passed:
        apk=project/f'app/build/outputs/apk/{args.variant}/app-{args.variant}.apk';target=out/f'bbll-independent-{args.variant}.apk';shutil.copy2(apk,target)
        report.update(apk=str(target),apk_sha256=digest(target),apk_bytes=target.stat().st_size)
        for name in ['standalone-build-inputs.json','standalone-apk-audit.json']:
            shutil.copy2(project/'app/build'/name,out/name)
    shutil.copy2(work/'gradle.log',out/'isolated-gradle.log')
    if(work/'audit.log').exists():shutil.copy2(work/'audit.log',out/'isolated-audit.log')
    (out/'apk-isolation-verification.json').write_text(json.dumps(report,indent=2)+'\n')
    print(json.dumps(report,indent=2));return 0 if passed else 1
if __name__=='__main__':sys.exit(main())
