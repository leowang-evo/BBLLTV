#!/usr/bin/env python3
"""Drive the BBLL UI on the existing emulator using observable views and key events."""
import argparse
import json
import os
import re
import subprocess
import time
from pathlib import Path
from xml.etree import ElementTree as ET

DEVICE = os.environ.get('BBLL_TEST_EMULATOR', 'emulator-5566')
PACKAGE = 'com.damon.bblab'
ACTIVITY = PACKAGE + '/com.xx.blbl.ui.MainActivity'
UI_FILE = '/data/local/tmp/bbll-regression-ui.xml'
OUT = Path(__file__).resolve().parent.parent / 'app/build/ui-regression'
OUT.mkdir(parents=True, exist_ok=True)


def adb(*args, timeout=22):
    return subprocess.check_output(['adb', '-s', DEVICE, *args], timeout=timeout)


def shell(*args):
    return adb('shell', *args).decode('utf-8', errors='replace')


def ui():
    result = shell('uiautomator', 'dump', UI_FILE)
    if 'dumped to' not in result:
        raise RuntimeError('UI hierarchy unavailable')
    return list(ET.fromstring(adb('shell', 'cat', UI_FILE)).iter('node'))


def ident(node):
    return node.get('resource-id', '').partition(':id/')[2]


def label(node):
    return node.get('text') or node.get('content-desc') or ''


def by_id(name):
    return lambda n: ident(n) == name and n.get('package') == PACKAGE


def text_is(name):
    return lambda n: label(n) == name and n.get('package') == PACKAGE


def find(test, nodes=None, attempts=3):
    for attempt in range(attempts):
        try:
            data = nodes if nodes is not None else ui()
            item = next((n for n in data if test(n)), None)
            if item is not None:
                return item
        except (RuntimeError, subprocess.TimeoutExpired, subprocess.CalledProcessError):
            pass
        if nodes is not None:
            break
        time.sleep(0.35)
    raise AssertionError('Expected UI element not visible')


def bounds(node):
    return tuple(map(int, re.findall(r'\d+', node.get('bounds', ''))))


def tap(node):
    a, b, c, d = bounds(node)
    shell('input', 'tap', str((a + c) // 2), str((b + d) // 2))


def press(*keys):
    shell('input', 'keyevent', *map(str, keys))


def tap_id(name, nodes=None):
    tap(find(by_id(name), nodes))


def tap_text(name, nodes=None):
    tap(find(text_is(name), nodes))


def screenshot(name):
    (OUT / ('bbll-regression-' + name + '.png')).write_bytes(adb('exec-out', 'screencap', '-p'))


def app_alive():
    state = shell('dumpsys', 'activity', 'activities')
    active = [line for line in state.splitlines() if re.search(r'(?:topResumedActivity|mTopResumedActivity|mResumedActivity)\s*[:=]', line)]
    return any(PACKAGE + '/' in line for line in active)


def restart():
    shell('am', 'force-stop', PACKAGE)
    launch = shell('am', 'start', '-W', '-n', ACTIVITY)
    assert 'Status: ok' in launch
    return find(by_id('button_home'))


def nav(name):
    try:
        tap(find(by_id(name), attempts=1))
    except AssertionError:
        restart()
        tap_id(name)
    return find(lambda n: by_id(name)(n) and n.get('selected') == 'true')


def visible_cards(nodes):
    return [n for n in nodes if ident(n) in ('textView', 'text_title', 'text_video_title') and n.get('text') and bounds(n)[1] > 103]


def require_cards(attempts=3):
    for attempt in range(attempts):
        data = visible_cards(ui())
        if len(data) >= 2:
            return data
        time.sleep(0.4)
    raise AssertionError('Fewer than two content cards rendered')


def tab(title):
    def on_strip(n):
        return text_is(title)(n) and bounds(n)[0] > 100 and bounds(n)[1] < 200
    tap(find(on_strip))
    find(lambda n: on_strip(n) and n.get('selected') == 'true')
    return f'{len(require_cards())} visible content cards'


def search_query(query):
    nav('button_search')
    page = ui()
    tap_id('button_clear', page)
    tap_id('button_qwe', page)
    keys = {label(n): n for n in ui() if by_id('button_key_qwe')(n)}
    for letter in query.upper():
        tap(keys[letter])
    submit = find(lambda n: by_id('button_search')(n) and bounds(n)[0] > 95)
    tap(submit)
    find(text_is(query.upper()))
    return f'{len(require_cards())} visible results for {query.upper()}'


def settings_section(name, marker=None):
    tap_id(name)
    if marker:
        find(text_is(marker))
    page = ui()
    content = [label(n) for n in page if label(n) and bounds(n)[0] > 350]
    assert content, 'Settings section contained no visible values'
    return ' | '.join(content[:6])


def guest_gate():
    find(text_is('该功能需要登录后才可以使用'))
    return 'Sign-in requirement shown'


class Run:
    failed = False

    def __init__(self, name):
        self.report = OUT / (os.environ.get('BBLL_REPORT_PREFIX', 'bbll-regression') + f'-{name}.json')
        self.rows = []

    def case(self, name, test):
        try:
            evidence = test()
            row = {'case': name, 'status': 'passed', 'evidence': evidence}
        except Exception as error:
            Run.failed = True
            row = {'case': name, 'status': 'needs-review', 'evidence': str(error)}
        self.rows.append(row)
        self.report.write_text(json.dumps(self.rows, ensure_ascii=False, indent=2) + '\n')
        print(f"{row['status']}: {name}: {row['evidence']}", flush=True)
        return row['status'] == 'passed'


def guest():
    r = Run('guest')
    r.case('冷启动与首页', lambda: (restart(), f'{len(require_cards())} cards')[1])
    nav('button_home')
    for title in ['推荐', '热门', '番剧', '影视']:
        r.case('首页 / ' + title, lambda t=title: tab(t))
    nav('button_category')
    for title in ['全站', '动画', '音乐', '游戏', '知识', '美食', '纪录片']:
        r.case('分区 / ' + title, lambda t=title: tab(t))
    r.case('搜索 / 全键盘与视频结果', lambda: search_query('bili'))
    for title in ['直播', '用户']:
        r.case('搜索 / ' + title, lambda t=title: tab(t))
    r.case('动态 / 匿名登录提示', lambda: (nav('button_dynamic'), guest_gate())[1])
    nav('button_me')
    for title in ['历史', '收藏', '追番', '追剧', '稍后观看']:
        r.case('我的 / ' + title + '匿名入口', lambda t=title: (tap_text(t), guest_gate())[1])
    settings_live(r)


def settings_live(r):
    nav('button_live')
    for title in ['推荐', '网游', '手游', '知识']:
        r.case('直播分区 / ' + title, lambda t=title: tab(t))
    nav('button_home')
    tap_id('button_setting')
    for name, marker in [('common', '检查更新'), ('play', '默认播放速度'), ('dm', '弹幕开关'), ('about', None), ('device', None)]:
        r.case('设置 / ' + name, lambda n=name, m=marker: settings_section('button_setting_' + n, m))
    r.case('设置 / 更新状态', lambda: check_update())
    press('KEYCODE_BACK')
    nav('button_home')


def check_update():
    tap_id('button_setting_common')
    tap_text('检查更新')
    message = find(lambda n: '未配置更新源' in label(n))
    press('KEYCODE_BACK')
    return label(message)


def seconds(value):
    total = 0
    for part in value.split(':'):
        total = total * 60 + int(part)
    return total


def position():
    return seconds(label(find(by_id('exo_position'), attempts=2)))


def visible_motion(name):
    from io import BytesIO
    from PIL import Image, ImageChops, ImageStat
    def frame():
        image = Image.open(BytesIO(adb('exec-out', 'screencap', '-p'))).convert('RGB')
        w, h = image.size
        return image.crop((int(w * 0.30), int(h * 0.30), int(w * 0.70), int(h * 0.72))).resize((160, 90))
    for _ in range(4):
        one = frame()
        time.sleep(1.15)
        two = frame()
        change = sum(ImageStat.Stat(ImageChops.difference(one, two)).mean) / 3
        if change > 1 and sum(ImageStat.Stat(two).mean) > 12:
            screenshot(name)
            return f'Video frame changed ({change:.1f}); BBLL foreground={app_alive()}'
    raise AssertionError('No motion in the central video image')


def open_video(live=False):
    nav('button_live' if live else 'button_home')
    if not live:
        tap_text('热门')
    cards = [n for n in require_cards() if ident(n) == 'textView']
    assert cards, 'Video cards not visible'
    card = cards[2] if live and len(cards) >= 3 else cards[0]
    a, b, c, _ = bounds(card)
    shell('input', 'tap', str((a+c)//2), str(b-110))
    time.sleep(3)
    try:
        return visible_motion('live' if live else 'vod')
    except AssertionError:
        screenshot('live-observed' if live else 'vod-observed')
        raise


def player_pause_seek():
    time.sleep(6.2)
    press('KEYCODE_DPAD_DOWN')
    time.sleep(.5)
    press('KEYCODE_DPAD_CENTER')
    try:
        find(by_id('button_play'), attempts=1)
    except AssertionError:
        press('KEYCODE_DPAD_DOWN')
    first = position()
    time.sleep(1)
    paused = position()
    assert abs(first - paused) <= 1, f'Pause position moved from {first} to {paused}'
    bar = find(by_id('exo_progress'))
    a, b, c, d = bounds(bar)
    shell('input', 'tap', str(a + int((c-a) * .58)), str((b+d)//2))
    seeked = position()
    assert abs(seeked - paused) > 3, f'Seek did not change position ({seeked})'
    tap_id('button_play')
    motion = visible_motion('resumed')
    time.sleep(6.2)
    press('KEYCODE_DPAD_DOWN')
    time.sleep(.5)
    press('KEYCODE_DPAD_CENTER')
    return f'Paused at {paused}s, sought to {seeked}s, then resumed. {motion}'


def player_menu(title):
    press('KEYCODE_MENU')
    find(text_is('画质'))
    tap_text(title)
    options = [label(n) for n in ui() if ident(n) in ('text_view_primary', 'text_view_sub') and label(n)]
    press('KEYCODE_BACK', 'KEYCODE_BACK')
    assert options, 'No options rendered'
    return ' | '.join(options[:8])


def player_related():
    try:
        button = find(by_id('button_related'), attempts=1)
    except AssertionError:
        press('KEYCODE_DPAD_CENTER')
        button = find(by_id('button_related'))
    tap(button)
    close = find(by_id('button_close_related'))
    data = require_cards()
    tap(close)
    return f'{len(data)} related cards visible'


def player_back():
    press('KEYCODE_BACK', 'KEYCODE_BACK', 'KEYCODE_BACK')
    find(by_id('button_home'))
    return 'Back key returned to the list'


def live_resume():
    press('KEYCODE_HOME')
    time.sleep(1)
    shell('am', 'start', '-W', '-n', ACTIVITY)
    time.sleep(2)
    return visible_motion('live-resumed')


def playback(vod_only=False):
    r = Run('vod' if vod_only else 'player')
    if r.case('点播 / 首帧与连续播放', open_video):
        r.case('点播 / 暂停、跳转、恢复播放', player_pause_seek)
        for name in ['画质', '播放速度', '视频编码', '音轨', '画面缩放', '弹幕设置']:
            r.case('点播设置 / ' + name, lambda n=name: player_menu(n))
        r.case('点播 / 相关视频列表', player_related)
        r.case('点播 / 遥控器返回', player_back)
    if not vod_only and r.case('直播 / 播放画面', lambda: open_video(True)):
        r.case('直播 / 退到桌面并恢复', live_resume)
        r.case('直播 / 遥控器返回', player_back)
    nav('button_home')


def consent_if_needed():
    nodes = ui()
    agreement = next((n for n in nodes if n.get('package') == PACKAGE and label(n) == '接受'), None)
    if agreement is not None:
        tap(agreement)
    return 'App startup complete'


def qr_ready():
    from io import BytesIO
    from PIL import Image, ImageStat
    find(by_id('button_check_result'))
    qr = find(by_id('imageView'))
    a, b, c, d = bounds(qr)
    for _ in range(4):
        shot = Image.open(BytesIO(adb('exec-out', 'screencap', '-p'))).convert('L')
        pixels = shot.crop((a, b, c, d))
        hist = pixels.histogram()
        if sum(hist[:30]) > 3000 and sum(hist[230:]) > 3000 and ImageStat.Stat(pixels).stddev[0] > 65:
            tap_id('button_check_result')
            result = find(lambda n: by_id('text_result')(n) and label(n))
            return f'QR image rendered; manual check reports {label(result)[:55]}'
        time.sleep(.8)
    raise AssertionError('QR image did not render')


def start_qr():
    r = Run('qr')
    r.case('新安装 / 首次打开', consent_if_needed)
    r.case('动态 / 未登录提示', lambda: (nav('button_dynamic'), guest_gate())[1])
    nav('button_me')
    for title in ['历史', '收藏', '追番', '追剧', '稍后观看']:
        r.case('我的 / ' + title + '未登录提示', lambda t=title: (tap_text(t), guest_gate())[1])
    nav('button_home')
    tap_id('image_avatar')
    r.case('登录 / 二维码生成和扫码状态查询', qr_ready)
    if r.case('登录 / 其他登录方式入口', lambda: (tap_id('button_other_way_login'), find(lambda n: any(x in label(n) for x in ('验证码', '密码', '手机号', 'Cookie'))), 'Alternate login form visible')[2]):
        press('KEYCODE_BACK')


def extra(catalog=False):
    r = Run('catalog' if catalog else 'extra')
    r.case('搜索 / 全键盘与视频结果', lambda: search_query('bili'))
    r.case('搜索 / 直播结果', lambda: tab('直播'))
    if r.case('搜索 / 用户结果', lambda: tab('用户')):
        r.case('用户 / UP 主主页', lambda: (tap(require_cards()[0]), find(by_id('button_follow')), 'UP profile and follow action shown')[2])
        press('KEYCODE_BACK')
    nav('button_home')
    r.case('首页 / 连续下翻', home_scroll)
    r.case('番剧 / 详情与分集', series_detail)
    if not catalog:
        r.case('收藏 / 打开收藏夹详情', collection_detail)
        for row, title in [('text_following','关注'), ('text_follower','粉丝')]:
            r.case('账号资料 / ' + title + '列表', lambda r=row: relation_list(r))
    nav('button_home')


def home_scroll():
    tap_text('热门')
    before = {label(n) for n in require_cards()}
    for _ in range(6):
        shell('input', 'swipe', '1600', '960', '1600', '280', '260')
    after = {label(n) for n in require_cards()}
    assert after - before and app_alive(), 'No newly displayed list entries'
    return f'{len(after-before)} different titles after scrolling'


def series_detail():
    tab('番剧')
    candidates = [n for n in require_cards() if ident(n) in ('textView', 'text_video_title')]
    assert candidates, 'No playable series card visible'
    # A retained scroll position can clip the cover above its visible title.
    # Tap the observed title rather than an assumed point 100 px above it.
    tap(candidates[0])
    nodes = []
    for _ in range(8):
        nodes = ui()
        if not any(by_id('button_home')(n) for n in nodes):
            break
        time.sleep(.25)
    assert not any(by_id('button_home')(n) for n in nodes), 'Series card did not navigate'
    result = [label(n) for n in nodes if label(n) and ('集' in label(n) or '播放' in label(n))]
    assert result, 'Series detail did not expose episodes or playback'
    press('KEYCODE_BACK')
    return 'Episode or playback information visible'


def collection_detail():
    nav('button_me')
    tap_text('收藏')
    data = require_cards()
    tap(data[0])
    find(lambda n: by_id('button_back_1')(n) or by_id('button_back')(n))
    nodes = ui()
    cards = visible_cards(nodes)
    empty = any(label(n) in ('啥都没有', '空空如也') for n in nodes)
    assert cards or empty, 'Folder has no visible videos or empty state'
    press('KEYCODE_BACK')
    return f'Folder detail opened, {len(cards)} visible entries'


def relation_list(row):
    nav('button_home')
    tap_id('image_avatar')
    tap_id(row)
    find(by_id('recyclerView'))
    nodes = ui()
    items = visible_cards(nodes)
    empty = any(label(n) in ('啥都没有', '空空如也') for n in nodes)
    assert items or empty, 'No relationship rows or empty state'
    press('KEYCODE_BACK')
    return f'{len(items)} visible entries'


def account():
    r = Run('account')
    r.case('登录 / 已登录后的账号弹窗', lambda: (nav('button_home'), tap_id('image_avatar'), find(lambda n: '退出登录' in label(n) or '退出账号' in label(n) or '注销' == label(n)), press('KEYCODE_BACK'), 'Signed-in account dialog')[4])
    r.case('登录 / 冷启动保留账号', lambda: (restart(), tap_id('image_avatar'), find(lambda n: '退出登录' in label(n) or '退出账号' in label(n) or '注销' == label(n)), press('KEYCODE_BACK'), 'Signed-in account dialog after restart')[4])
    r.case('动态 / 已登录列表', lambda: (nav('button_dynamic'), f'{len(require_cards())} visible entries')[1])
    nav('button_me')
    for name in ['历史', '收藏', '追番', '追剧', '稍后观看']:
        r.case('我的 / ' + name, lambda n=name: account_list(n))
    nav('button_home')


def account_list(title):
    tap_text(title)
    time.sleep(0.6)
    nodes = ui()
    assert not any('该功能需要登录' in label(n) for n in nodes), 'Still signed out'
    rows = [n for n in nodes if label(n) and ident(n) in ('textView', 'text_title', 'text_name', 'text_info', 'text_empty') or label(n) in ('啥都没有', '空空如也')]
    assert rows, 'No visible data or empty-state marker'
    return f'{len(rows)} visible data/empty-state nodes'


if __name__ == '__main__':
    parser = argparse.ArgumentParser()
    parser.add_argument('suite', choices=['guest', 'settings', 'playback', 'vod', 'qr', 'account', 'extra', 'catalog'])
    args = parser.parse_args()
    {'guest': guest, 'settings': lambda: settings_live(Run('settings')), 'playback': playback, 'vod': lambda: playback(True), 'qr': start_qr, 'account': account, 'extra': extra, 'catalog': lambda: extra(True)}[args.suite]()
    if Run.failed:
        raise SystemExit(1)
