# -*- coding: utf-8 -*-
from pathlib import Path
import importlib.util,os,time,json,re,hashlib,sys,argparse
parser=argparse.ArgumentParser(description='Non-debuggable TV diagnostics: identical paced home navigation, real playback menus and three process-cold launches. Does not clear caches or account data.')
parser.add_argument('--device',required=True)
parser.add_argument('--out',type=Path,required=True)
parser.add_argument('--apk',type=Path,required=True)
args=parser.parse_args()
p=Path(__file__).resolve().parents[1]
o=args.out;o.mkdir(parents=True,exist_ok=True);apk=args.apk
os.environ['BBLL_TEST_EMULATOR']=args.device
sp=importlib.util.spec_from_file_location('ui',p/'tools/verify_application_ui.py');m=importlib.util.module_from_spec(sp);sp.loader.exec_module(m)
report={'device':args.device,'model':m.shell('getprop','ro.product.model').strip(),'version_code':141,'package':m.PACKAGE,'sampling':'Non-debuggable benchmark build; same paced D-pad operations on the same TV, warm image cache, no cache clearing.','phases':{},'checks':[]}
def save():(o/'performance.json').write_text(json.dumps(report,ensure_ascii=False,indent=2)+'\n')
def check(ok,name,details=''):
 report['checks'].append({'case':name,'passed':bool(ok),'details':details});save();assert ok,(name,details);print('PASS',name,details,flush=True)
def shot(name):(o/(name+'.png')).write_bytes(m.adb('exec-out','screencap','-p'))
def mem(name):
 raw=m.shell('dumpsys','meminfo',m.PACKAGE);(o/(name+'-meminfo.txt')).write_text(raw)
 return [s.strip()for s in raw.splitlines()if any(x in s for x in ['TOTAL PSS:','TOTAL RSS:','Native Heap','Dalvik Heap','Java Heap:','Native Heap:','Graphics:','Activities:','Views:'])]
def stats(name,keys):
 raw=m.shell('dumpsys','gfxinfo',m.PACKAGE,'framestats');(o/(name+'-gfxinfo.txt')).write_text(raw);rows=[s.strip()for s in raw.splitlines()if any(x in s for x in ['Total frames','Janky frames','percentile','Number Missed','Number High','Number Slow','Number Frame'])];data={'android_gfxinfo':rows,'keys':keys,'memory':mem(name)};report['phases'][name]=data;save();print(name,json.dumps(rows,ensure_ascii=False),flush=True)
def reset():m.shell('dumpsys','gfxinfo',m.PACKAGE,'reset')
def keys(codes,delay):
 for code in codes:m.press(code);time.sleep(delay)
installed=m.shell('pm','path',m.PACKAGE).strip().removeprefix('package:');sha=m.shell('sha256sum',installed).split()[0];expected=hashlib.sha256(apk.read_bytes()).hexdigest();check(sha==expected,'电视安装包与本次非调试构建一致',sha);report['apk_sha256']=sha
flags=m.shell('dumpsys','package',m.PACKAGE);assert 'DEBUGGABLE'not in re.search(r'flags=\[([^]]+)',flags)[1];report['debuggable']=False;report['version_code']=int(re.search(r'versionCode=(\d+)',flags)[1])
m.shell('am','force-stop',m.PACKAGE);launch=m.shell('am','start','-W','-n',m.ACTIVITY);(o/'cold-launch.txt').write_text(launch);report['cold_activity_launch_ms']={k:int(v)for k,v in re.findall(r'(TotalTime|ThisTime|WaitTime): (\d+)',launch)};check('Status: ok'in launch,'冷启动 Activity 成功',report['cold_activity_launch_ms']);m.find(m.by_id('button_home'));m.tab('热门');m.press(20,22,22,20);time.sleep(.5)
nodes=m.ui();focused=[n for n in nodes if n.get('focused')=='true']
if not any(m.ident(n)=='click_view'for n in focused):m.press(20);time.sleep(.3);nodes=m.ui();focused=[n for n in nodes if n.get('focused')=='true']
check(any(m.ident(n)=='click_view'for n in focused),'首页方向键卡片获得焦点');shot('home-before-profile');report['home_memory_before']=mem('home-before')
reset();seq=[22,22,22,21,21,21]*4;keys(seq,.16);time.sleep(.35);stats('home-horizontal',len(seq))
reset();seq=[20]*6+[19]*6;keys(seq,.22);time.sleep(.5);stats('home-scroll',len(seq));nodes=m.ui();focused=[n for n in nodes if n.get('focused')=='true'];check(any(m.ident(n)=='click_view'for n in focused),'连续滚动后焦点仍在视频卡片')
# Start the currently focused real video and sample normal setting-panel transitions.
m.press(23);time.sleep(6);m.press(82);time.sleep(.4);m.find(m.text_is('画质'));check(True,'真实播放页可打开画质设置');shot('player-settings');m.press(4);time.sleep(.4)
reset()
for _ in range(6):m.press(82);time.sleep(.3);m.press(4);time.sleep(.3)
time.sleep(.4);stats('player-menu',12)
m.press(82);time.sleep(.4);m.find(m.text_is('画质'));m.press(4);time.sleep(.35);m.press(19);time.sleep(.3);m.press(4);time.sleep(.35);m.press(4);time.sleep(.6)
nodes=m.ui();check(any(m.by_id('button_home')(n)for n in nodes),'真机菜单与控制区逐层返回首页');shot('home-final');report['home_memory_after_player']=mem('home-after-player')
report['cold_repeats']=[]
for _ in range(2):
 m.shell('am','force-stop',m.PACKAGE);cold=m.shell('am','start','-W','-n',m.ACTIVITY);report['cold_repeats'].append({k:int(v)for k,v in re.findall(r'(TotalTime|ThisTime|WaitTime): (\d+)',cold)});m.find(m.by_id('button_home'));time.sleep(2)
report['finished']=True;save();print('TV_PROFILE_DONE',flush=True)
