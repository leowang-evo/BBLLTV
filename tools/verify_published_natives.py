#!/usr/bin/env python3
"""Audit only published AkDanmaku/libGDX binaries and AGP's normal merge output."""
import hashlib,json,struct,zipfile
from pathlib import Path
ROOT=Path(__file__).resolve().parents[1]
def sha(b):return hashlib.sha256(b).hexdigest()
def elf(b):
    assert b[:4]==b'\x7fELF' and b[5]==1
    bits=32 if b[4]==1 else 64
    if bits==32:
        off=struct.unpack_from('<I',b,32)[0];size,count=struct.unpack_from('<HH',b,46)
        fmt='<10I'
    else:
        off=struct.unpack_from('<Q',b,40)[0];size,count=struct.unpack_from('<HH',b,58)
        fmt='<IIQQQQIIQQ'
    sections=[struct.unpack_from(fmt,b,off+i*size) for i in range(count)]
    symbols=set()
    for s in sections:
        if s[1]!=11:continue
        st=sections[s[6]];strings=b[st[4]:st[4]+st[5]]
        for p in range(s[4],s[4]+s[5],s[9]):
            name=struct.unpack_from('<I',b,p)[0]
            info=b[p+12] if bits==32 else b[p+4]
            ndx=struct.unpack_from('<H',b,p+14 if bits==32 else p+6)[0]
            if ndx and info>>4 in [1,2]:symbols.add(strings[name:strings.find(b'\0',name)].decode())
    allocated=[sha(b[s[4]:s[4]+s[5]]) for s in sections if s[2]&2 and s[1]!=8]
    return {'bits':bits,'machine':struct.unpack_from('<H',b,18)[0]},symbols,allocated

def mangle(s):
    out=''
    for c in s:
        if c.isascii() and c.isalnum():out+=c
        elif c=='/':out+='_'+''
        elif c=='_':out+='_1'
        elif c==';':out+='_2'
        elif c=='[':out+='_3'
        else:out+='_0'+format(ord(c),'04x')
    return out

classes=json.loads((ROOT/'dependencies/android/class-details.json').read_text())
natives=[]
for name,defs in classes.items():
    if not name.startswith('com/badlogic/gdx/'):continue
    for c in defs:
        for m in c['methods']:
            if m['access_flags']&0x100:
                short='Java_'+mangle(name)+'_'+mangle(m['name'])
                args=m['descriptor'].split(')')[0][1:]
                natives.append({'class':name,'method':m['name'],'descriptor':m['descriptor'],'short':short,'long':short+'__'+mangle(args)})
results=[]
with zipfile.ZipFile(ROOT/'dependencies/android/akdanmaku-1.0.3.aar') as aar:
    for abi,bits,machine in [('armeabi-v7a',32,40),('arm64-v8a',64,183),('x86',32,3),('x86_64',64,62)]:
        published=aar.read('jni/'+abi+'/libgdx.so')
        with zipfile.ZipFile(ROOT/f'dependencies/natives/gdx-platform-1.10.0-natives-{abi}.jar') as jar: official=jar.read('libgdx.so')
        architecture,symbols,allocated=elf(published);official_arch,official_symbols,official_allocated=elf(official)
        expected=[m for m in natives if m['short'] in symbols or m['long'] in symbols]
        missing=[m for m in natives if m not in expected]
        # GL20/30 native declarations live in Android system libGLES, not libgdx.
        app_required=[m for m in missing if '/graphics/GL' not in m['class'] and '/backends/android/AndroidGL' not in m['class']]
        merged=ROOT/f'app/build/intermediates/merged_native_libs/debug/mergeDebugNativeLibs/out/lib/{abi}/libgdx.so'
        results.append({'abi':abi,'bytes':len(published),'architecture':architecture,'architecture_matches':architecture=={'bits':bits,'machine':machine},'akdanmaku_sha256':sha(published),'gdx_platform_sha256':sha(official),'bytes_identical':published==official,'allocated_section_contents_match_official':allocated==official_allocated,'exported_jni_symbols':len([s for s in symbols if s.startswith('Java_')]),'jni_symbol_sets_match_official':{s for s in symbols if s.startswith('Java_')}=={s for s in official_symbols if s.startswith('Java_')},'published_java_native_declarations':len(natives),'java_declarations_resolved':len(expected),'unresolved_native_declarations':missing,'merged_path':str(merged.relative_to(ROOT)),'merged_exists':merged.exists(),'merged_matches_published_aar':merged.exists() and merged.read_bytes()==published})
report={'inputs':'Only official Maven AkDanmaku1.0.3 AAR, libGDX1.10.0 JARs/native classifier artifacts and normal AGP merged outputs; no original APK input.','selection':'The official AkDanmaku1.0.3 POM pins gdx and gdx-backend-android1.10.0. Its AAR already contains all four ABI libraries; do not package duplicate native classifier inputs.','byte_note':'AAR vs gdx-platform differ in metadata bytes; matching architecture and complete JNI symbol sets are verified separately.','abis':results,'all_pass':all(x['architecture_matches'] and x['allocated_section_contents_match_official'] and x['jni_symbol_sets_match_official'] and not x['unresolved_native_declarations'] and x['merged_matches_published_aar'] for x in results)}
out=ROOT/'dependencies/natives/verification.json';out.write_text(json.dumps(report,indent=2)+'\n')
print(json.dumps({k:v for k,v in report.items() if k!='abis'},indent=2))
for r in results:print(r['abi'],r['java_declarations_resolved'],'/',r['published_java_native_declarations'],'unresolved',len(r['unresolved_native_declarations']),'merged',r['merged_matches_published_aar'])
