#!/usr/bin/env python3
"""One-time auditable migration of app-owned playback state, excluding all SDK code."""
from pathlib import Path
import re
ROOT=Path(__file__).resolve().parents[1];OLD=ROOT.parent/'java-export/app/src/main/java/p091o5';OUT=ROOT/'app/src/main/java/p091o5';OUT.mkdir(exist_ok=True)
def common(s):
 s=s.replace('import O5.j;','import kotlin.Unit;').replace('j.f2237a','Unit.INSTANCE')
 s=re.sub(r'\bj (jVar\w*)',r'Unit \1',s)
 s=s.replace('import kotlin.jvm.internal.f;','import kotlin.jvm.internal.Intrinsics;')
 for a,b in [('f.a(','Intrinsics.areEqual('),('f.b(','Intrinsics.checkNotNull('),('f.c(','Intrinsics.checkNotNull('),('f.d(','Intrinsics.checkNotNullExpressionValue('),('f.e(','Intrinsics.checkNotNullParameter('),('f.j(','Intrinsics.throwUninitializedPropertyAccessException(')]:s=s.replace(a,b)
 s=s.replace('p076m3.b.r(0, ','m5.ActionUi.toast(').replace('import p076m3.b;','')
 s=s.replace('b.r(0, ','m5.ActionUi.toast(')
 s=s.replace('import androidx.media3.exoplayer.F;','import androidx.media3.exoplayer.ExoPlayer;').replace('F f7;','ExoPlayer f7;').replace('f7.n(5, 0L);','f7.seekTo(0L);')
 s=s.replace('import p077m4.b;','import com.kuaishou.akdanmaku.data.DanmakuItemData;')
 return s
for name in ['a','b','c','d','e']:
 s=common((OLD/(name+'.java')).read_text())
 if name=='a':
  s=s.replace('import kotlin.collections.l;','').replace('(EpisodeModel) l.Q(episodes)','episodes.get(episodes.size()-1)')
 if name=='b':
  # XML DM parser returns independent core DTOs; map at the UI boundary only.
  s=s.replace('uVar3.e0(list2);','uVar3.e0(DanmakuBridge.convert(list2));')
 if name=='c':
  s=s.replace('import p006a5.a;','import com.xx.blbl.network.chat.WebSocketEcho;').replace('implements NetResultCallback, a','implements NetResultCallback, WebSocketEcho.DmListener')
  s=s.replace('public void c(DmModel dmModel)', 'public void onDm(DmModel dmModel)')
  s=s.replace('new b(jCurrentTimeMillis, progress, content, i4, 25, dmModel.getColor(), 0, 1984)','new DanmakuItemData(jCurrentTimeMillis, progress, content, i4, 25, dmModel.getColor(), 0, 1, 0, null, 0)')
 if name=='d':
  s=s.replace('new b(id, progress, content, i8, danmakuElem.getFontsize(), danmakuElem.getColor(), (danmakuElem.getColorful() == Dm.DmColorfulType.VipGradualColor && z7) ? 1 : 0, 1536)','new DanmakuItemData(id, progress, content, i8, danmakuElem.getFontsize(), danmakuElem.getColor(), 0, 1, (danmakuElem.getColorful() == Dm.DmColorfulType.VipGradualColor && z7) ? 1 : 0, null, 0)')
 if name=='e':
  s=s.replace('import L6.a;','').replace('implements a','')
  for line in ['import android.support.v4.media.session.b;','import com.bumptech.glide.c;','import kotlin.LazyThreadSafetyMode;','import kotlin.collections.l;','import kotlin.collections.x;','import kotlin.jvm.internal.h;','import kotlin.text.o;','import p070l0.v;']:s=s.replace(line,'')
  s=s.replace('public final Object f12274R;','public final kotlin.Lazy<NetworkManager> f12274R;')
  s=s.replace('x.t(LazyThreadSafetyMode.SYNCHRONIZED, new A5.d(this, 17))','kotlin.LazyKt.lazy(AppController::network)')
  s=s.replace('(d) Z4.d.h().f1935a.f2994b.c(null, null, h.a(d.class))','AppController.state()')
  s=s.replace('(NetworkManager) ((O5.c) this.f12274R).getValue()','this.f12274R.getValue()')
  s=re.sub(r'    @Override // L6.a\n    public final K6.a getKoin\(\) \{.*?\n    \}\n','',s,flags=re.S)
  s=s.replace('com.xx.blbl.a aVar = AppController.f9455a;','android.content.SharedPreferences preferences=AppController.instance().getSharedPreferences("BLBL",0);')
  s=re.sub(r'b.C\(aVar.a\(\), "([^"]+)", ([^)]+)\)',r'preferences.getInt("\1", \2)',s)
  s=re.sub(r'b.y\(aVar.a\(\), "([^"]+)", ([^)]+)\)',r'preferences.getBoolean("\1", \2)',s)
  s=s.replace('b.B(aVar.a(), "defaultPlaySpeed")','preferences.getFloat("defaultPlaySpeed",1.0f)').replace('AppController.f9455a.a().getSharedPreferences("BLBL", 0)','preferences')
  s=s.replace('kotlin.text.x.u(', 'kotlin.text.StringsKt.startsWith(').replace('kotlin.text.x.s(str, "ss", "")','str.replace("ss", "")').replace('kotlin.text.x.s(str, "ep", "")','str.replace("ep", "")')
  s=s.replace('(String) l.Q(o.L(path, new String[]{"/"}))','path.substring(path.lastIndexOf(\'/\')+1)').replace('List listL = o.L(path, new String[]{"/"});','List<String> listL = java.util.Arrays.asList(path.split("/"));')
  s=s.replace('c.g(', 'android.util.Log.d(')
  s=s.replace('new v(5, eVar, videoModel)','new InteractionCallback(eVar, videoModel)')
 (OUT/(name+'.java')).write_text(s)
