package com.damon.bblab.playbacktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/** Exercise actual Bilibili media through the application's own APIs and player. */
public final class RefreshNavigationProbe extends Instrumentation {
    private Activity host;
    private ClassLoader loader;
    private Object fragment, network;
    private Class<?> fragmentType;
    private View playerView, controls;
    private int checks;
    private String stage = "startup";

    interface Task {
        void run() throws Exception;
    }

    private static Class<?> wrap(Class<?> c) {
        if (c == int.class) return Integer.class;
        if (c == long.class) return Long.class;
        if (c == boolean.class) return Boolean.class;
        if (c == float.class) return Float.class;
        return c;
    }

    private Object call(Object target, String name, Object... args) throws Exception {
        Class<?> type = target instanceof Class ? (Class<?>) target : target.getClass();
        outer:
        for (Method method : type.getMethods()) {
            if (!method.getName().equals(name) || method.getParameterTypes().length != args.length)
                continue;
            Class<?>[] params = method.getParameterTypes();
            for (int i = 0; i < args.length; i++)
                if (args[i] != null && !wrap(params[i]).isInstance(args[i])) continue outer;
            method.setAccessible(true);
            try {
                return method.invoke(target instanceof Class ? null : target, args);
            } catch (InvocationTargetException e) {
                throw new IllegalStateException(e.getCause());
            }
        }
        throw new NoSuchMethodException(type.getName() + "." + name);
    }

    private void main(Task task) throws Exception {
        AtomicReference<Throwable> error = new AtomicReference<>();
        runOnMainSync(
                () -> {
                    try {
                        task.run();
                    } catch (Throwable e) {
                        error.set(e);
                    }
                });
        if (error.get() != null) throw new IllegalStateException(error.get());
    }

    private Object field(Object target, String name) throws Exception {
        return target.getClass().getField(name).get(target);
    }

    private void check(boolean condition, String label) {
        if (!condition) throw new AssertionError(stage + ": " + label);
        checks++;
        android.util.Log.i("BBLL_REFRESH_TEST", "PASS " + stage + " / " + label);
    }

    private int id(String name) { return host.getResources().getIdentifier(name, "id", host.getPackageName()); }
    private Object gson;
    private Object cloneVideo(Object video) throws Exception {
        return call(gson, "fromJson", call(gson, "toJson", video), video.getClass());
    }
    private Object hidden(Object target, String name) throws Exception {
        Field f = target.getClass().getDeclaredField(name); f.setAccessible(true); return f.get(target);
    }
    private List items(Object adapter) throws Exception { return (List) field(adapter, "items"); }
    private void awaitData(Object adapter, Object first, int count) throws Exception {
        for (int i = 0; i < 100; i++) {
            boolean[] ready = {false};
            main(() -> ready[0] = items(adapter).size() == count && (count == 0 || items(adapter).get(0) == first));
            if (ready[0]) { SystemClock.sleep(250); return; }
            SystemClock.sleep(40);
        }
        throw new AssertionError("Latest data was not committed");
    }
    private int focusedPosition(RecyclerView rv) throws Exception {
        int[] result = {-1};
        main(() -> { View focus = rv.findFocus(); RecyclerView.ViewHolder h = focus == null ? null : rv.findContainingViewHolder(focus); if(h != null) result[0] = h.getBindingAdapterPosition(); });
        return result[0];
    }
    private void focusRow(RecyclerView rv, int position) throws Exception {
        main(() -> rv.scrollToPosition(position));
        String[] reason={"not laid out"};
        for(int attempt=0;attempt<40;attempt++) {
            SystemClock.sleep(75);
            boolean[] focused={false};
            main(() -> {
                RecyclerView.ViewHolder holder=rv.findViewHolderForAdapterPosition(position);
                if(holder!=null) {
                    focused[0]=holder.itemView.requestFocusFromTouch();
                    reason[0]="shown="+holder.itemView.isShown()+" focusable="+holder.itemView.isFocusable()+" touch="+holder.itemView.isInTouchMode();
                }
            });
            if(focused[0]) {SystemClock.sleep(180);return;}
        }
        throw new AssertionError("Cannot focus row "+position+": "+reason[0]);
    }
    private int focusY(RecyclerView rv) throws Exception {
        int[] y = {0,0}; main(() -> rv.findFocus().getLocationOnScreen(y)); return y[1];
    }
    static final class Observer extends RecyclerView.AdapterDataObserver {
        int full, inserted, removed, changed, moved;
        public void onChanged(){ full++; }
        public void onItemRangeInserted(int start,int n){ inserted+=n; }
        public void onItemRangeRemoved(int start,int n){ removed+=n; }
        public void onItemRangeChanged(int start,int n){ changed+=n; }
        public void onItemRangeChanged(int start,int n,Object payload){ changed+=n; }
        public void onItemRangeMoved(int from,int to,int n){ moved+=n; }
        void reset(){full=inserted=removed=changed=moved=0;}
    }
    private void lists(Object page) throws Exception {
        stage = "incremental list";
        Object adapter = field(page, "O0"); RecyclerView rv = (RecyclerView) field(page, "recyclerView");
        List original = new ArrayList(items(adapter));
        boolean hasMore = (Boolean) field(page,"hasMorePages");
        Observer observer = new Observer(); RecyclerView.Adapter typed = (RecyclerView.Adapter) adapter;
        Class<?> paged = loader.loadClass("com.xx.blbl.ui.screen.PagedListFragment");
        Method invalidate = paged.getDeclaredMethod("beginPageRequest", int.class); invalidate.setAccessible(true);
        main(() -> { invalidate.invoke(page,1); call(page,"setHasMore",false); page.getClass().getField("loadingPage").setBoolean(page,true); typed.registerAdapterDataObserver(observer); });
        Field wf = loader.loadClass("com.xx.blbl.ui.adapter.VideoListUpdates").getDeclaredField("WORKER"); wf.setAccessible(true);
        ExecutorService worker = (ExecutorService) wf.get(null);
        try {
            List equal = new ArrayList(); for(Object video:original) equal.add(cloneVideo(video));
            main(() -> call(adapter,"replaceItems",equal)); awaitData(adapter,equal.get(0),equal.size());
            check(observer.full==0 && observer.inserted==0 && observer.removed==0 && observer.changed==0 && observer.moved==0,
                    "equal render data dispatches no updates");
            focusRow(rv,8);
            Object[] cover = {null};
            int requestTag=id("glide_custom_view_target_tag");
            main(() -> cover[0] = rv.findViewHolderForAdapterPosition(8).itemView.findViewById(id("imageView")).getTag(requestTag));
            check(requestTag != 0 && cover[0] != null,"visible cover has an active image request");
            Object textOnly = cloneVideo(equal.get(8)); call(textOnly,"setTitle",call(textOnly,"getTitle")+" · text update"); equal.set(8,textOnly);
            main(() -> call(adapter,"replaceItems",equal));
            for(int i=0;i<50 && items(adapter).get(8)!=textOnly;i++)SystemClock.sleep(40);
            SystemClock.sleep(200);
            main(() -> {
                View card = rv.findViewHolderForAdapterPosition(8).itemView;
                check(((android.widget.TextView)card.findViewById(id("textView"))).getText().toString().equals(call(textOnly,"getTitle")),"partial binding updates visible title");
                check(card.findViewById(id("imageView")).getTag(requestTag)==cover[0],"text update retains the existing cover request");
            });
            String identity = (String) call(items(adapter).get(8),"getBvid"); int y=focusY(rv);
            List added = new ArrayList(equal); Object extra=cloneVideo(equal.get(0));
            call(extra,"setBvid","BV140TEST0001"); call(extra,"setAid",900140001L); added.add(0,extra);
            Object changed = cloneVideo(added.get(12)); call(changed,"setTitle",call(changed,"getTitle")+" · updated"); added.set(12,changed);
            observer.reset(); main(() -> call(adapter,"replaceItems",added)); awaitData(adapter,extra,added.size());
            check(observer.inserted==1 && observer.removed==0 && observer.full==0 && observer.changed==1,
                    "prepend and one title change update only affected rows");
            check(focusedPosition(rv)==9 && identity.equals(call(items(adapter).get(9),"getBvid")), "prepend retains focused video identity");
            check(Math.abs(focusY(rv)-y)<=2,"prepend retains focused card vertical offset");
            List moved = new ArrayList(added); Object focused=moved.remove(9); moved.add(13,focused);
            observer.reset(); main(() -> call(adapter,"replaceItems",moved)); awaitData(adapter,moved.get(0),moved.size());
            // First item is unchanged, so also wait for the moved model's actual position.
            for(int i=0;i<40 && items(adapter).get(13)!=focused;i++) SystemClock.sleep(50);
            SystemClock.sleep(250);
            check(observer.moved>0 && observer.removed==0 && observer.inserted==0,"reorder uses move notifications");
            check(focusedPosition(rv)==13 && identity.equals(call(items(adapter).get(13),"getBvid")),"reorder retains focus on the same video");
            check(Math.abs(focusY(rv)-y)<=2,"reorder retains visible card offset");
            List removed=new ArrayList(moved); removed.remove(13); main(() -> call(adapter,"replaceItems",removed)); awaitData(adapter,removed.get(0),removed.size());
            check(focusedPosition(rv)==13,"removed focused item falls back to nearest remaining row");
            View nav=host.findViewById(id("button_home")); main(() -> nav.requestFocus());
            List fresh = new ArrayList();for(Object video:removed)fresh.add(cloneVideo(video));
            main(() -> call(adapter,"replaceItems",fresh)); awaitData(adapter,fresh.get(0),fresh.size());
            check(nav.hasFocus(),"background update cannot steal focus from navigation");
            CountDownLatch release=new CountDownLatch(1),started=new CountDownLatch(1);
            worker.execute(() -> {started.countDown();try{release.await(5,TimeUnit.SECONDS);}catch(InterruptedException e){Thread.currentThread().interrupt();}});
            check(started.await(2,TimeUnit.SECONDS),"background diff worker can be controlled independently of UI");
            List stale=new ArrayList(equal),latest=new ArrayList(equal.subList(0,16)),tail=new ArrayList(equal.subList(16,18));
            Object latestFirst=cloneVideo(latest.get(0)); call(latestFirst,"setTitle","latest update");latest.set(0,latestFirst);
            main(() -> {call(adapter,"replaceItems",stale);call(adapter,"replaceItems",latest);call(adapter,"appendItems",tail);call(adapter,"setHasLoadMoreRow",true);});
            release.countDown();awaitData(adapter,latestFirst,18);
            check(items(adapter).get(16)==tail.get(0) && items(adapter).get(17)==tail.get(1),"append uses latest pending replacement");
            check(typed.getItemCount()==19 && typed.getItemViewType(18)==123456,"footer stays consistent across pending data changes");
            main(() -> call(adapter,"setHasLoadMoreRow",false));check(typed.getItemCount()==18,"footer removal after diff is consistent");
            CountDownLatch release2=new CountDownLatch(1),started2=new CountDownLatch(1);
            worker.execute(() -> {started2.countDown();try{release2.await(5,TimeUnit.SECONDS);}catch(InterruptedException e){Thread.currentThread().interrupt();}});
            check(started2.await(2,TimeUnit.SECONDS),"second diff can be suspended");
            main(() -> {call(adapter,"replaceItems",equal);call(adapter,"cancelPendingUpdates");}); release2.countDown();SystemClock.sleep(350);
            check(items(adapter).size()==18 && items(adapter).get(0)==latestFirst,"detached generation rejects late diff results");
        } finally {
            main(() -> {typed.unregisterAdapterDataObserver(observer);rv.setAdapter(null);rv.setAdapter(typed);call(adapter,"replaceItems",original);});
            awaitData(adapter,original.get(0),original.size());
            main(() -> {rv.scrollToPosition(0);});SystemClock.sleep(200);
            main(() -> {page.getClass().getField("loadingPage").setBoolean(page,false);call(page,"setHasMore",hasMore);});
        }
    }
    private String focusedText() throws Exception {
        String[] result={""};main(() -> {View focus=host.getCurrentFocus();android.widget.TextView title=focus==null?null:focus.findViewById(id("text_view_primary"));if(title!=null)result[0]=title.getText().toString();});return result[0];
    }
    private void key(int code){sendKeyDownUpSync(code);SystemClock.sleep(150);}
    private long[] playback(Object fragment) throws Exception {
        long[] state=new long[4];main(() -> {Object engine=field(fragment,"player");if(engine==null)return;state[0]=(Long)call(engine,"getCurrentPosition");state[1]=(Boolean)call(engine,"isPlaying")?1:0;state[2]=((Number)field(call(engine,"getVideoSize"),"width")).longValue();state[3]=(Integer)call(engine,"getPlaybackState");});return state;
    }
    private void menusAndBack(Object page) throws Exception {
        stage="menus and back";
        Object adapter=field(page,"O0");RecyclerView grid=(RecyclerView)field(page,"recyclerView");focusRow(grid,0);
        Object model=cloneVideo(items(adapter).get(0));call(model,"setProgress",10000L);
        main(() -> call(loader.loadClass("com.xx.blbl.ui.navigation.ScreenNavigator"),"openVideo",host,model,false));
        Object manager=call(host,"fragments"),fragment=null;
        for(int i=0;i<60 && fragment==null;i++){SystemClock.sleep(150);for(Object f:(List<?>)call(manager,"getFragments"))if(f.getClass().getName().endsWith(".PlaybackFragment"))fragment=f;}
        check(fragment!=null,"video opens from the existing navigation route");final Object target=fragment;
        long[] playing=null;for(int i=0;i<100;i++){playing=playback(target);if(playing[1]==1 && playing[2]>0 && playing[0]>=9500)break;SystemClock.sleep(200);}
        check(playing[1]==1 && playing[2]>0 && playing[0]>=9500,"resumed real video renders normally");
        View player=(View)field(target,"playerView");Object settings=hidden(player,"settingView");
        for(String method:new String[]{"showVideoQualityMenu","showPlaybackSpeed","showSubtitles","showVideoCodec","showAudioQuality","showScreenRatio"}){
            main(() -> {call(player,"showHideSettingView",true);call(settings,method);});SystemClock.sleep(300);
            String expected;
            if(method.equals("showPlaybackSpeed"))expected=call(settings,"getCurrentSpeed")+"x";
            else if(method.equals("showSubtitles")){List tracks=(List)call(settings,"getSubtitles");int selected=(Integer)call(settings,"getCurrentSubtitlePosition");if(tracks==null||tracks.isEmpty()){main(() -> call(host,"onBackPressed"));continue;}expected=(String)call(tracks.get(selected>=0&&selected<tracks.size()?selected:0),"getLan_doc");}
            else if(method.equals("showScreenRatio"))expected=host.getResources().getStringArray(host.getResources().getIdentifier("screen_ratios","array",host.getPackageName()))[(Integer)call(settings,"getCurrentScreenRatio")];
            else{String getter=method.equals("showVideoQualityMenu")?"getCurrentVideoQuality":method.equals("showAudioQuality")?"getCurrentAudioQuality":"getCurrentVideoCodec";Object current=call(settings,getter);String availableGetter=method.equals("showVideoQualityMenu")?"getVideoQualities":method.equals("showAudioQuality")?"getAudioQualities":"getVideoCodecs";List available=(List)call(settings,availableGetter);Object shown=available!=null && available.contains(current)?current:available.get(0);expected=(String)call(shown,"getShowName");}
            check(expected.equals(focusedText()),method+" starts on current/available value: "+expected+" actual="+focusedText());
            main(() -> call(host,"onBackPressed"));SystemClock.sleep(200);
            check((Boolean)call(settings,"isShowing"),"system Back returns from choices to settings");
            main(() -> call(host,"onBackPressed"));SystemClock.sleep(250);
            check(!(Boolean)call(settings,"isShowing") && (Boolean)call(target,"isAdded"),"system Back closes settings without leaving playback");
        }
        for(int row:new int[]{1,6,7}){
            main(() -> {call(player,"showHideSettingView",true);call(settings,"showDmSetting");});SystemClock.sleep(250);
            RecyclerView rv=(RecyclerView)hidden(settings,"recyclerView");focusRow(rv,row);
            String getter=row==1?"getDmEnable":row==6?"getDmAllowTop":"getDmAllowBottom";Object original=call(settings,getter);String parent=focusedText();
            key(23);check(!original.equals(call(settings,getter)),parent+" toggles with one confirmation");
            check(((android.widget.TextView)host.getCurrentFocus().findViewById(id("text_view_sub"))).getText().toString().equals((Boolean)original?"关":"开"),"visible toggle label immediately follows changed value");
            check(parent.equals(focusedText()),"toggle keeps focus on its row");
            check((Integer)hidden(settings,"menuLevel")==2,"toggle does not open another menu level");
            View toggle=host.getCurrentFocus().findViewById(id("tv_setting_toggle"));check(toggle!=null && toggle.isShown(),"toggle has a visible switch indicator");
            key(23);check(original.equals(call(settings,getter)),"second confirmation restores original setting");
            check(((android.widget.TextView)host.getCurrentFocus().findViewById(id("text_view_sub"))).getText().toString().equals((Boolean)original?"开":"关"),"visible toggle label returns to original value");
            main(() -> call(host,"onBackPressed"));SystemClock.sleep(150);main(() -> call(host,"onBackPressed"));SystemClock.sleep(250);
        }
        main(() -> {Object engine=field(target,"player");call(engine,"pause");call(engine,"seekTo",10000L);call(player,"showController");});SystemClock.sleep(250);
        View restart=player.findViewById(id("button_restart"));check(restart!=null && restart.isShown(),"explicit from-start action is visible for on-demand video");
        main(() -> restart.requestFocus());key(23);long[] restarted=null;
        for(int i=0;i<50;i++){restarted=playback(target);if(restarted[0]<2500&&restarted[1]==1)break;SystemClock.sleep(150);}
        check(restarted[0]<2500 && restarted[1]==1,"from-start action restarts and plays actual media");
        main(() -> call(player,"showHideLiveSettingButton",true));check(restart.getVisibility()==View.GONE,"live mode hides from-start action");main(() -> call(player,"showHideLiveSettingButton",false));
        main(() -> call(player,"showController"));SystemClock.sleep(350);main(() -> call(host,"onBackPressed"));SystemClock.sleep(350);
        check((Boolean)call(target,"isAdded") && !(Boolean)call(player,"isControllerFullyVisible"),"Back first hides the control bar");
        main(() -> call(host,"onBackPressed"));SystemClock.sleep(550);
        check(!(Boolean)call(target,"isAdded") && (Integer)call(manager,"getBackStackEntryCount")==0,"one further Back exits playback without restarting or requiring double Back");
        check(host.findViewById(id("button_home")).isShown(),"leaving playback restores side navigation");
        check(focusedPosition(grid)==0,"leaving playback restores the originating card focus");
    }
    @Override public void onCreate(Bundle args){super.onCreate(args);start();}
    @Override public void onStart(){
        Bundle result=new Bundle();int status=Activity.RESULT_CANCELED;
        try{
            loader=getTargetContext().getClassLoader();gson=loader.loadClass("com.google.gson.Gson").getConstructor().newInstance();
            host=startActivitySync(new Intent().setClassName(getTargetContext(),"com.xx.blbl.ui.MainActivity").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));setInTouchMode(false);
            SystemClock.sleep(1200);main(() -> call(host,"selectTab",0));SystemClock.sleep(500);
            Object home=null;for(Object f:(List<?>)call(call(host,"fragments"),"getFragments"))if(f.getClass().getName().endsWith(".HomeFragment"))home=f;
            check(home!=null,"home available");Object pager=field(home,"f9662H0");main(() -> call(pager,"setCurrentItem",1));Object page=null;
            for(int i=0;i<70&&page==null;i++){SystemClock.sleep(200);for(Object f:(List<?>)call(call(home,"childFragments"),"getFragments"))if(f.getClass().getName().endsWith(".PopularVideosFragment")&&field(f,"O0")!=null&&items(field(f,"O0")).size()>=18)page=f;}
            check(page!=null,"real video grid loaded");lists(page);menusAndBack(page);
            result.putString("stream","REFRESH_NAV_PASS "+checks+" assertions\n");status=Activity.RESULT_OK;
        }catch(Throwable e){result.putString("stream","REFRESH_NAV_FAIL "+stage+" "+android.util.Log.getStackTraceString(e));}
        finally{if(host!=null)try{main(() -> host.finish());}catch(Exception ignored){}finish(status,result);}
    }
}
