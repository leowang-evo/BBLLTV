# -*- coding: utf-8 -*-
from pathlib import Path
import importlib.util,os,time,json,subprocess,argparse,xml.etree.ElementTree as ET
parser=argparse.ArgumentParser(description='Exercise TV settings choices without clearing account data; reversible numeric choices are restored.')
parser.add_argument('--device',default='emulator-5566')
parser.add_argument('--out',type=Path)
args=parser.parse_args()
os.environ['BBLL_TEST_EMULATOR']=args.device
p=Path(__file__).resolve().parents[1]
o=args.out or p/'app/build/ui-regression/settings-tv'
o.mkdir(parents=True,exist_ok=True)
sp=importlib.util.spec_from_file_location('ui',p/'tools/verify_application_ui.py');m=importlib.util.module_from_spec(sp);sp.loader.exec_module(m)
strings={n.get('name'):''.join(n.itertext())for n in ET.parse(p/'app/src/main/res/values/strings.xml').getroot()if n.tag=='string'}
original_ui=m.ui
def reliable_ui():
 for attempt in range(3):
  try:return original_ui()
  except (subprocess.CalledProcessError,subprocess.TimeoutExpired,RuntimeError,ET.ParseError):
   if attempt==2:raise
   print('UI_DUMP_RETRY',attempt+1,flush=True);time.sleep(.5)
m.ui=reliable_ui
rows=json.loads((o/'results.json').read_text()) if os.environ.get('BBLL_SETTINGS_RESUME')=='1' and (o/'results.json').exists() else []
assert all(x['passed']for x in rows)
def check(ok,name,details=''):
 rows.append(dict(case=name,passed=bool(ok),details=details));(o/'results.json').write_text(json.dumps(rows,ensure_ascii=False,indent=2)+'\n');assert ok,(name,details);print('PASS',name,details,flush=True)
def shot(name):(o/(name+'.png')).write_bytes(m.adb('exec-out','screencap','-p'))
def swipe(up):m.shell('input','swipe','1500','850'if up else'300','1500','300'if up else'850','260');time.sleep(.25)
def title_row(name):
 for i in range(9):
  ns=m.ui();r=next((n for n in ns if m.ident(n)=='click_view'and any(m.ident(c)=='text_title'and m.label(c)==name for c in n.iter('node'))and m.bounds(n)[1]>=10 and m.bounds(n)[3]<=1060),None)
  if r is not None:return r
  swipe(True)
 raise AssertionError('Setting row not found '+name)
def value(row):return next((m.label(x)for x in row.iter('node')if m.ident(x)=='text_sub'),'')
def open_choice(name):
 row=title_row(name);before=value(row);m.tap(row);ns=m.ui();check(any(m.label(n)=='方向键选择 · 确认保存 · 返回取消'for n in ns),'统一选项弹窗 / '+name)
 focused=next((n for n in ns if n.get('focused')=='true'),None);check(focused is not None and len([c for c in focused.iter('node')if m.ident(c)=='text_view_primary'])==1 and any(m.label(c)=='●'for c in focused.iter('node')),'当前选项获得焦点 / '+name,before)
 return before
sections={'common':['cache_limit','default_start_page','image_quality','theme','fullscreen_app'],'play':['default_video_quality','default_audio_track','default_play_speed','after_play','play_finish_exit_player','show_re_ff','video_codec','show_subtitle_default','subtitle_text_size','show_debug','show_video_detail','show_bottom_progress_bar','simple_key_press','give_coin_number','show_next_previous','show_dm_switch','ff_seek_second'],'dm':['dm_switch','dm_alpha','dm_text_size','dm_screen_area','dm_speed','dm_allow_top','dm_allow_bottom','dm_filter_weight','allow_vip_colorful_dm']}
m.restart();m.tap_id('button_setting')
for section,keys in sections.items():
 m.tap_id('button_setting_'+section)
 for k in keys:
  name=strings[k]
  if any(x['case']=='返回取消保留设置 / '+name and x['passed']for x in rows):continue
  before=open_choice(name)
  if k in['default_video_quality','dm_text_size']:shot('choice-'+k)
  m.press(4);after=value(title_row(name));check(after==before,'返回取消保留设置 / '+name,after)
 shot('section-'+section)
# Commit, reopen and restore a reversible numeric preference using the original callback.
m.tap_id('button_setting_play');name=strings['default_play_speed'];original=open_choice(name);m.tap(m.find(lambda n:m.ident(n)=='text_view_primary'and m.label(n)=='1.25'));check(value(title_row(name))=='1.25','倍速选择立即更新设置值')
m.restart();m.tap_id('button_setting');m.tap_id('button_setting_play');check(value(title_row(name))=='1.25','非调试包冷启动保留选项');open_choice(name);m.tap(m.find(lambda n:m.ident(n)=='text_view_primary'and m.label(n)==original));check(value(title_row(name))==original,'恢复原倍速',original)
m.tap_id('button_setting_common');m.tap(title_row(strings['check_version']));ns=m.ui();check(any('此独立构建尚未配置更新源'in m.label(n)for n in ns),'更新状态使用统一消息弹窗');shot('message-update');m.press(4)
m.tap_id('button_setting_common');shot('settings-final');m.press(4);print('SETTINGS_TV_PASS',len(rows),flush=True)
