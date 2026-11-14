#!/usr/bin/env python3
"""Run BBLL player and feature regression on an emulator or the known TV.
The default uses generated H.264. Optional media, ranking and extended suites use
Bilibili APIs and the existing login; normal playback can update viewing history.
Requires the Android SDK and JDK 21; local generated-media tests also need ffmpeg.
"""
import argparse,os,pathlib,re,shutil,subprocess,tempfile,zipfile,hashlib
ROOT=pathlib.Path(__file__).resolve().parent.parent
PACKAGE='com.damon.bblab'
PROBE=PACKAGE+'.playbacktest'
INSTALLER='com.android.packageinstaller'

def main():
    parser=argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--device',default='emulator-5566')
    parser.add_argument('--apk',type=pathlib.Path,default=ROOT/'app/build/outputs/apk/debug/app-debug.apk')
    parser.add_argument('--pgc-diagnostic',action='store_true',help='Compare the official PGC request versions without exporting cookies or stream URLs')
    parser.add_argument('--case',choices=['subtitles','multipart','series','interactive','dialogs','highProfiles','continuation','specialProfiles','lifecycle'])
    parser.add_argument('--extended',action='store_true',help='Test actual subtitles, multi-part continuation, interactive branches and dialogs')
    parser.add_argument('--refresh-navigation',action='store_true',help='Check incremental list updates, shallow menus and linear playback Back using real media')
    parser.add_argument('--home-ux', action='store_true', help='Check home focus, per-account local history, and real resume playback using the existing login; viewing history may update')
    parser.add_argument('--rankings',action='store_true',help='Check five ranking APIs and rendered category grids')
    parser.add_argument('--home-navigation',action='store_true',help='Check deep home Back, fresh catalog origin, detail return and sort reset')
    parser.add_argument('--home-navigation-case',choices=['all','home','catalog'],default='all')
    parser.add_argument('--observe-navigation',action='store_true',help='Record old-build navigation failures without claiming a passing regression; requires --home-navigation')
    parser.add_argument('--entry-routes',action='store_true',help='Click real search/dynamic videos and live area room cards, including pagination and Back')
    parser.add_argument('--real-media',action='store_true',help='Use actual Bilibili VOD/live APIs and the existing login; normal playback history may update')
    parser.add_argument('--allow-tv-installer',action='store_true',help='Temporarily disable and restore the known TCL TV package installer')
    parser.add_argument('--reuse-installed',action='store_true',help='Skip target installation only after its installed APK SHA-256 matches --apk exactly; still installs the probe')
    args=parser.parse_args()
    if args.observe_navigation and not args.home_navigation:parser.error("--observe-navigation requires --home-navigation")
    print("APK_SHA256", hashlib.sha256(args.apk.read_bytes()).hexdigest(), flush=True)
    sdk=pathlib.Path(os.environ.get('ANDROID_HOME',str(pathlib.Path.home()/'Library/Android/sdk')))
    jdk=pathlib.Path(os.environ.get('JAVA_HOME','/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home'))
    os.environ['JAVA_HOME']=str(jdk)
    os.environ['PATH']=str(jdk/'bin')+os.pathsep+os.environ.get('PATH','')
    bt=sdk/'build-tools/36.0.0';android=sdk/'platforms/android-36/android.jar'
    adb=str(sdk/'platform-tools/adb')
    def run(command,**kwargs):
        result=subprocess.run(list(map(str,command)),stdout=subprocess.PIPE,stderr=subprocess.STDOUT,**kwargs)
        output=result.stdout.decode(errors='replace')
        if result.returncode:raise RuntimeError(output)
        return output
    def device(*command):return run([adb,'-s',args.device,*command],timeout=120)
    def installer_state():
        match=re.search(r'User 0:.*?enabled=(\d+)',device('shell','dumpsys','package',INSTALLER))
        if not match:raise RuntimeError('Cannot read installer state')
        return int(match[1])
    badging=run([bt/'aapt','dump','badging',args.apk])
    if not badging.startswith("package: name='"+PACKAGE+"'"):raise RuntimeError('Probe requires validation applicationId '+PACKAGE)
    tv=device('shell','getprop','ro.product.device').strip()=='tcl_mt5879_cn'
    if not args.device.startswith('emulator-') and not (tv and args.allow_tv_installer):raise RuntimeError('For the known TV use --allow-tv-installer; other physical devices are not supported by this installer')
    with tempfile.TemporaryDirectory(prefix='bbll-player-probe-') as temp:
        work=pathlib.Path(temp);(work/'assets').mkdir();(work/'classes').mkdir()
        probe_class='HomeNavigationProbe' if args.home_navigation else 'EntryRoutesProbe' if args.entry_routes else 'RefreshNavigationProbe' if args.refresh_navigation else 'HomeUxProbe' if args.home_ux else 'PgcDiagnosticProbe' if args.pgc_diagnostic else ('ExtendedPlaybackProbe' if args.extended else ('RankingProbe' if args.rankings else ('RealPlaybackProbe' if args.real_media else 'PlaybackRemoteProbe')))
        source=work/(probe_class+'.java');shutil.copy2(ROOT/('tools/tests/'+probe_class+'.java'),source)
        if not (args.refresh_navigation or args.home_ux or args.real_media or args.rankings or args.extended or args.pgc_diagnostic or args.entry_routes or args.home_navigation):run(['ffmpeg','-v','error','-f','lavfi','-i','testsrc2=size=640x360:rate=24','-t','20','-an','-c:v','libx264','-preset','ultrafast','-pix_fmt','yuv420p',work/'assets/sample.mp4'])
        manifest=work/'AndroidManifest.xml';manifest.write_text(f'<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="{PROBE}"><uses-sdk android:minSdkVersion="21" android:targetSdkVersion="35"/><application android:label="BBLL Playback Probe"/><instrumentation android:name=".{probe_class}" android:targetPackage="{PACKAGE}"/></manifest>')
        classpath=[android]
        if args.refresh_navigation:
            # Compile the observer against the same published AndroidX SDKs as the app.
            for group,artifact,version in [('androidx.recyclerview','recyclerview','1.3.0'),('androidx.core','core','1.15.0'),('androidx.customview','customview','1.1.0')]:
                files=list((ROOT/'.gradle-user-home/caches/modules-2/files-2.1'/group/artifact/version).glob('*/*.aar'))
                if len(files)!=1:raise RuntimeError('Expected the pinned public '+artifact+' AAR')
                jar=work/(artifact+'.jar')
                with zipfile.ZipFile(files[0]) as z:jar.write_bytes(z.read('classes.jar'))
                classpath.append(jar)
        run([jdk/'bin/javac','--release','8','-classpath',os.pathsep.join(map(str,classpath)),'-d',work/'classes',source])
        run([bt/'d8','--lib',android,'--min-api','21','--output',work/'dex.zip',*sorted((work/'classes').rglob('*.class'))])
        run([bt/'aapt2','link','-I',android,'--manifest',manifest,'-A',work/'assets','-o',work/'unsigned.apk'])
        with zipfile.ZipFile(work/'dex.zip') as dex,zipfile.ZipFile(work/'unsigned.apk','a',zipfile.ZIP_DEFLATED) as apk:apk.writestr('classes.dex',dex.read('classes.dex'))
        run([bt/'zipalign','-f','4',work/'unsigned.apk',work/'aligned.apk'])
        signing_key=ROOT/'keys/reconstruction-debug.keystore'
        if not signing_key.is_file():signing_key=pathlib.Path.home()/'.android/debug.keystore'
        if not signing_key.is_file():raise RuntimeError('Build the target APK first to generate the local debug signing key')
        run([bt/'apksigner','sign','--ks',signing_key,'--ks-pass','pass:android','--ks-key-alias','androiddebugkey','--out',work/'probe.apk',work/'aligned.apk'])
        previous=installer_state() if tv else None
        if args.reuse_installed:
            paths=device('shell','pm','path',PACKAGE).strip().splitlines()
            if len(paths)!=1 or not paths[0].startswith('package:'):raise RuntimeError('Expected one installed target APK')
            installed_hash=device('shell','sha256sum',paths[0][len('package:'):]).split()[0]
            if installed_hash!=hashlib.sha256(args.apk.read_bytes()).hexdigest():raise RuntimeError('Installed target differs from --apk; cannot reuse')
            print('Reusing installed target with matching SHA-256',flush=True)
        try:
            if tv:device('shell','pm','disable-user','--user','0',INSTALLER)
            for apk in ((work/'probe.apk',) if args.reuse_installed else (args.apk,work/'probe.apk')):
                output=device('install','--no-incremental','-r',apk)
                if 'Success' not in output:raise RuntimeError('Install did not succeed')
        finally:
            if tv:
                commands={0:'default-state',1:'enable',2:'disable',3:'disable-user',4:'disable-until-used'}
                device('shell','pm',commands[previous],'--user','0',INSTALLER)
                if installer_state()!=previous:raise RuntimeError('Installer restoration failed')
                print('Restored TV installer state:',previous,flush=True)
        if tv:
            device('shell','input','keyevent','KEYCODE_WAKEUP')
            device('shell','input','keyevent','KEYCODE_HOME')
        instrument=[adb,'-s',args.device,'shell','am','instrument','-w']
        if args.home_navigation:instrument += ['-e','navigation_case',args.home_navigation_case]
        if args.observe_navigation:instrument += ['-e','observe','true']
        if args.case:instrument += ['-e','case',args.case]
        output=run(instrument+[PROBE+'/.'+probe_class],timeout=600);print(output)
        if args.home_navigation:
            passed=('HOME_NAV_OBSERVATION_COMPLETE' if args.observe_navigation else 'HOME_NAV_PASS') in output and 'FAIL ' not in output
        elif args.entry_routes:
            passed='ENTRY_ROUTES_PASS' in output and 'FAIL' not in output
        elif args.refresh_navigation:
            passed='REFRESH_NAV_PASS' in output and 'FAIL' not in output
        elif args.home_ux:
            passed='HOME_UX_PASS' in output and 'FAIL' not in output
        elif args.pgc_diagnostic:
            passed='PGC_DIAGNOSTIC_COMPLETE' in output and 'FAIL' not in output
        elif args.extended:
            passed='EXTENDED_PASS' in output and 'FAIL' not in output
        elif args.rankings:
            passed='RANKING_PASS' in output and 'FAIL' not in output
        elif args.real_media:
            passed='BILI_REAL_PASS' in output and 'FAIL' not in output
        else:
            passed='PASS local H.264' in output and bool(re.search(r'PASS \d+ remote control',output)) and 'FAIL' not in output
        if not passed:raise RuntimeError('Playback or remote regression failed')
        print('Navigation observation completed; not a passing regression' if args.observe_navigation else 'Remote player regression passed',flush=True)

if __name__=='__main__':main()
