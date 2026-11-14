package com.damon.bblab.playbacktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.PopupWindow;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/** Exercise actual Bilibili media through the application's own APIs and player. */
public final class EntryRoutesProbe extends Instrumentation {
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
        android.util.Log.i("BBLL_REAL_TEST", "PASS " + stage + " / " + label);
    }

    private Object api(String name, Object... args) throws Exception {
        Class<?> callback = loader.loadClass("com.xx.blbl.network.NetResultCallback");
        CountDownLatch ready = new CountDownLatch(1);
        AtomicReference<Object> result = new AtomicReference<>();
        AtomicReference<Throwable> error = new AtomicReference<>();
        Object proxy =
                Proxy.newProxyInstance(
                        loader,
                        new Class<?>[] {callback},
                        (p, m, values) -> {
                            if (m.getName().equals("onResponse")) {
                                result.set(values[0]);
                                ready.countDown();
                            }
                            if (m.getName().equals("onFailure")) {
                                error.set((Throwable) values[0]);
                                ready.countDown();
                            }
                            return null;
                        });
        Object[] parameters = Arrays.copyOf(args, args.length + 1);
        parameters[args.length] = proxy;
        call(network, name, parameters);
        if (!ready.await(20, TimeUnit.SECONDS)) throw new AssertionError(name + " timeout");
        if (error.get() != null)
            throw new IllegalStateException(
                    name + " failed: " + error.get().getClass().getSimpleName());
        Object response = result.get();
        check(response != null, name + " received response");
        check(
                ((Integer) call(response, "getCode")) == 0,
                name + " business code=" + call(response, "getCode"));
        return call(response, "getData");
    }

    private void open(Object model) throws Exception {
        fragment = fragmentType.getConstructor().newInstance();
        Bundle arguments = new Bundle();
        arguments.putSerializable("model", (Serializable) model);
        call(fragment, "setArguments", arguments);
        main(
                () -> {
                    Object manager = call(host, "fragments");
                    call(manager, "executePendingTransactions");
                    call(host, "showScreen", fragment, "realPlaybackProbe");
                    call(manager, "executePendingTransactions");
                });
        SystemClock.sleep(500);
    }

    private long[] snapshot() throws Exception {
        long[] state = new long[7];
        main(
                () -> {
                    Object engine = field(fragment, "player");
                    if (engine == null) return;
                    state[0] = (Long) call(engine, "getCurrentPosition");
                    state[1] = (Integer) call(engine, "getPlaybackState");
                    Object size = call(engine, "getVideoSize");
                    state[2] = (Integer) field(size, "width");
                    state[3] = (Integer) field(size, "height");
                    state[4] = (Boolean) call(engine, "getPlayWhenReady") ? 1 : 0;
                    state[5] = (Long) call(engine, "getDuration");
                    state[6] = (Boolean) call(engine, "isPlaying") ? 1 : 0;
                });
        return state;
    }

    private long[] playing(long minimum) throws Exception {
        long[] state = null;
        for (int i = 0; i < 100; i++) {
            state = snapshot();
            if (state[0] > minimum && state[2] > 0 && state[3] > 0 && state[6] == 1) return state;
            SystemClock.sleep(300);
        }
        throw new AssertionError(stage + " did not decode/advance: " + Arrays.toString(state));
    }

    private int id(String name) {
        return host.getResources().getIdentifier(name, "id", host.getPackageName());
    }

    private void key(int code) {
        sendKeyDownUpSync(code);
        SystemClock.sleep(180);
    }

    private void bindViews() throws Exception {
        playerView = (View) field(fragment, "playerView");
        controls = (View) call(playerView, "getAppController");
    }

    private void focusIs(String name) throws Exception {
        boolean[] value = {false};
        main(
                () -> {
                    View focused = host.getCurrentFocus();
                    value[0] = focused != null && focused.getId() == id(name);
                });
        check(value[0], "focus " + name);
    }

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
        start();
    }

    private void screenshot(String name) throws Exception {
        SystemClock.sleep(350);
        android.graphics.Bitmap bitmap=getUiAutomation().takeScreenshot();
        if(bitmap==null) throw new AssertionError("screenshot unavailable");
        java.io.File file=new java.io.File(getTargetContext().getExternalFilesDir("regression142"),name+".png");
        try(java.io.FileOutputStream out=new java.io.FileOutputStream(file)) {
            bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG,100,out);
        } finally {bitmap.recycle();}
    }
    private interface Condition { boolean ready() throws Exception; }
    private void until(Condition c, String label) throws Exception {
        for (int i=0;i<100;i++) { if(c.ready()) return; SystemClock.sleep(250); }
        throw new AssertionError(stage+" / timeout "+label);
    }
    private Object active(String type) throws Exception {
        Object[] found={null};
        main(() -> {
            Object manager=call(host,"fragments"); call(manager,"executePendingTransactions");
            for(Object f:(List<?>)call(manager,"getFragments"))
                if(f.getClass().getName().endsWith("."+type) && (Boolean)call(f,"isResumed")) found[0]=f;
        });
        return found[0];
    }
    private Object tab(int index) throws Exception {
        main(() -> call(host,"selectTab",index)); SystemClock.sleep(250);
        return ((List<?>)field(host,"tabScreens")).get(index);
    }
    private View findList(View view, String adapterName) throws Exception {
        if(view.getClass().getName().contains("RecyclerView")) {
            Object a=call(view,"getAdapter");
            if(a!=null && a.getClass().getSimpleName().equals(adapterName)) return view;
        }
        if(view instanceof android.view.ViewGroup)
            for(int i=0;i<((android.view.ViewGroup)view).getChildCount();i++) {
                View result=findList(((android.view.ViewGroup)view).getChildAt(i),adapterName);
                if(result!=null) return result;
            }
        return null;
    }
    private View loadedList(Object screen, String adapterName) throws Exception {
        View[] list={null};
        until(() -> {
            main(() -> {
                list[0]=findList((View)call(screen,"getView"),adapterName);
                if(list[0]!=null && (Integer)call(call(list[0],"getAdapter"),"getItemCount")==0) list[0]=null;
            }); return list[0]!=null;
        }, adapterName+" populated");
        return list[0];
    }
    private void clickFirst(View list) throws Exception {
        until(() -> {
            boolean[] clicked={false};main(() -> {
                Object holder=call(list,"findViewHolderForAdapterPosition",0);
                if(holder!=null) {
                    View item=(View)field(holder,"itemView");View click=item.findViewById(id("click_view"));
                    clicked[0]=(click==null?item:click).performClick();
                }
            });return clicked[0];
        },"first visible card click");
    }
    private void decodedEntry(String label) throws Exception {
        until(() -> { fragment=active("PlaybackFragment"); return fragment!=null; },"routed to player");
        long[] result=playing(1000);
        check(result[2]>0 && result[3]>0 && result[6]==1,label+" decodes and advances");
        Object state=field(fragment,"coordinator");
        if((Integer)field(state,"f12278V")==0)
            check((Long)call(field(state,"video"),"getCid")>0,label+" resolves missing cid");
    }
    private void backFromPlayer() throws Exception {
        for(int i=0;i<5 && active("PlaybackFragment")!=null;i++) {
            main(() -> call(host,"onBackPressed"));SystemClock.sleep(300);
        }
        check(active("PlaybackFragment")==null,"Back restores entry page");
    }
    @Override public void onStart() {
        Bundle result=new Bundle();int status=Activity.RESULT_CANCELED;
        try {
            loader=getTargetContext().getClassLoader();
            host=startActivitySync(new Intent().setClassName(getTargetContext(),"com.xx.blbl.ui.MainActivity")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            setInTouchMode(false);SystemClock.sleep(1200);
            fragmentType=loader.loadClass("com.xx.blbl.ui.playback.PlaybackFragment");
            network=call(loader.loadClass("com.xx.blbl.AppController"),"network");
            stage="search entry";
            Object search=tab(5);
            main(() -> { ((android.widget.TextView)field(search,"f9583G0")).setText("BILI");call(search,"onSearch"); });
            View videos=loadedList(search,"VideoGridAdapter");
            check((Integer)call(call(videos,"getAdapter"),"getItemCount")>0,"search returned real videos");
            clickFirst(videos);decodedEntry("search card");backFromPlayer();
            check(active("SearchFragment")!=null,"Back restores search results");
            stage="dynamic entry";
            Object dynamic=tab(2);View feed=loadedList(dynamic,"DynamicVideoFeedAdapter");
            check((Integer)call(call(feed,"getAdapter"),"getItemCount")>0,"signed-in dynamic has real videos");
            clickFirst(feed);decodedEntry("dynamic card");backFromPlayer();
            check(active("DynamicFragment")!=null,"Back restores dynamic feed");
            stage="live area entry";
            Object live=tab(3);
            until(() -> field(live,"f9665H0")!=null && (Integer)call(field(live,"f9665H0"),"getTabCount")>1,"live tabs");
            main(() -> call(call(field(live,"f9665H0"),"getTabAt",1),"select"));
            Object[] category={null};
            until(() -> {
                main(() -> { for(Object f:(List<?>)call(call(live,"getChildFragmentManager"),"getFragments"))
                    if(f.getClass().getSimpleName().equals("LiveCategoriesFragment") && (Boolean)call(f,"isResumed")) category[0]=f; });
                return category[0]!=null;
            },"live categories");
            View categories=loadedList(category[0],"LaneItemAdapter");clickFirst(categories);
            Object[] area={null};until(() -> {area[0]=active("LiveAreaFragment");return area[0]!=null;},"area route");
            View rooms=loadedList(area[0],"CatalogAdapter");
            check((Integer)call(call(rooms,"getAdapter"),"getItemCount")>1,"area contains room cards");
            main(() -> call(rooms,"scrollToPosition",(Integer)call(call(rooms,"getAdapter"),"getItemCount")-1));
            until(() -> ((List<?>)field(call(rooms,"getAdapter"),"items")).size()>20,"next page of rooms");
            check(true,"area pagination loads more than twenty rooms");screenshot("live-area-rooms");
            main(() -> call(rooms,"scrollToPosition",0));SystemClock.sleep(400);clickFirst(rooms);
            decodedEntry("area room card");backFromPlayer();
            check(active("LiveAreaFragment")!=null,"Back restores live area list");
            main(() -> call(host,"onBackPressed"));
            stage="home tab focus";
            Object home=tab(0);Object tabs=field(home,"f9661G0");int selected=(Integer)call(tabs,"getSelectedTabPosition");
            View selectedView=(View)field(call(tabs,"getTabAt",selected),"view");
            main(() -> selectedView.requestFocusFromTouch());key(selected==3?21:22);
            check((Integer)call(tabs,"getSelectedTabPosition")==selected,"D-pad focus does not select tab before confirmation");
            View focused=host.getCurrentFocus();check(focused!=null && !focused.isSelected(),"unselected tab receives focus");screenshot("home-tab-focused");
            key(23);check((Integer)call(tabs,"getSelectedTabPosition")!=selected,"Center confirms focused tab");screenshot("home-tab-selected");
            stage="bounded Back focus";
            main(() -> host.findViewById(id("button_setting")).performClick());
            Object[] settings={null};until(() -> {settings[0]=active("SettingsFragment");return settings[0]!=null;},"settings page");
            View back=(View)field(settings[0],"backButton");main(() -> back.requestFocusFromTouch());
            check(host.getCurrentFocus()==back,"Back icon receives remote focus");screenshot("settings-back-focused");
            main(() -> back.performClick());SystemClock.sleep(300);
            check(active("SettingsFragment")==null,"Back icon restores home page");
            result.putString("stream","ENTRY_ROUTES_PASS assertions="+checks+" search/dynamic card decode, live area paging/room decode, tab focus and Back");
            status=Activity.RESULT_OK;
        } catch(Throwable error) { result.putString("stream","FAIL "+stage+" "+error); }
        finally { if(host!=null) try{main(() -> host.finish());}catch(Exception ignored){} finish(status,result); }
    }
}
