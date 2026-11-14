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
public final class HomeNavigationProbe extends Instrumentation {
    private Activity host;
    private ClassLoader loader;
    private Object fragment, network;
    private Class<?> fragmentType;
    private View playerView, controls;
    private int checks;
    private boolean observe;
    private String navigationCase="all";
    private int observedFailures;
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
        if (!condition) { if(observe) {observedFailures++; android.util.Log.i("BBLL_NAV_TEST", "OBSERVED_FAILURE "+stage+" / "+label); return;} throw new AssertionError(stage + ": " + label); }
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
        observe=args!=null && Boolean.parseBoolean(args.getString("observe", "false"));
        navigationCase=args==null ? "all" : args.getString("navigation_case", "all");
        start();
    }

    private interface Condition {boolean ready() throws Exception;}
    private void until(Condition condition,String label) throws Exception {
        for(int i=0;i<120;i++){if(condition.ready()) return;SystemClock.sleep(200);}
        throw new AssertionError(stage+" / timeout "+label);
    }
    private Object active(String name) throws Exception {
        Object[] value={null};main(() -> {
            Object manager=call(host,"fragments");call(manager,"executePendingTransactions");
            for(Object f:(List<?>)call(manager,"getFragments"))
                if(f.getClass().getSimpleName().equals(name) && (Boolean)call(f,"isResumed")
                        && !(Boolean)call(f,"isHidden") && ((View)call(f,"getView")).isShown()) value[0]=f;
        });return value[0];
    }
    private Object currentPage(Object home) throws Exception {
        Object[] value={null};main(() -> {
            for(Object f:(List<?>)call(call(home,"getChildFragmentManager"),"getFragments"))
                if((Boolean)call(f,"isResumed") && ((View)call(f,"getView")).isShown()) value[0]=f;
        });return value[0];
    }
    private Object chooseHome(int index) throws Exception {
        main(() -> call(host,"selectTab",0));
        Object home=active("HomeFragment");Object tabs=field(home,"f9661G0");
        main(() -> call(call(tabs,"getTabAt",index),"select"));SystemClock.sleep(250);
        Object[] page={null};until(() -> {
            page[0]=currentPage(home);
            if(page[0]==null)return false;
            String kind=page[0].getClass().getSimpleName();
            boolean expected=index==0 ? kind.equals("RecommendedVideosFragment")
                    : index==1 ? kind.equals("PopularVideosFragment")
                    : kind.equals("SeriesLanesFragment") && (Integer)field(page[0],"f9608S0")==index-1;
            return expected && field(page[0],"listAdapter")!=null && !(Boolean)field(page[0],"loadingPage")
                    && !((List<?>)field(field(page[0],"listAdapter"),"items")).isEmpty();
        },"populated home page");return page[0];
    }
    private void focusAt(View rv,int position) throws Exception {
        main(() -> {rv.requestFocusFromTouch();call(loader.loadClass("com.xx.blbl.platform.RecyclerFocus"),"request",rv,position);});
        until(() -> focusedPosition(rv)==position,"focus row "+position);
    }
    private int focusedPosition(View rv) throws Exception {
        int[] value={-1};main(() -> {
            View focus=rv.findFocus();if(focus==null)return;
            Object holder=call(rv,"findContainingViewHolder",focus);
            if(holder!=null)value[0]=(Integer)call(holder,"getBindingAdapterPosition");
        });return value[0];
    }
    private View holderView(View rv,int position) throws Exception {
        View[] v={null};until(() -> {
            main(() -> {Object h=call(rv,"findViewHolderForAdapterPosition",position);if(h!=null)v[0]=(View)field(h,"itemView");});return v[0]!=null;
        },"holder "+position);return v[0];
    }
    private void homeBack(int index) throws Exception {
        stage="home tab "+index;
        Object page=chooseHome(index);View rv=(View)field(page,"recyclerView");Object layout=field(page,"listLayout");
        int count=((List<?>)field(field(page,"listAdapter"),"items")).size();
        if(index<2) {
            focusAt(rv,0);
            for(int i=0;i<12;i++)key(20);
        } else {
            int position=Math.min(3,count-1);main(() -> call(layout,"scrollToPositionWithOffset",position,0));
            View row=holderView(rv,position);View inner=row.findViewById(id("recyclerView"));
            if(inner!=null && (Integer)call(call(inner,"getAdapter"),"getItemCount")>0)
                focusAt(inner,Math.min(12,(Integer)call(call(inner,"getAdapter"),"getItemCount")-1));
            else {View target=row.findViewById(id("top_title"));main(() -> target.requestFocusFromTouch());}
            SystemClock.sleep(300);
        }
        int firstPosition=(Integer)call(layout,"findFirstVisibleItemPosition");
        View firstRow=(View)call(layout,"findViewByPosition",firstPosition);
        check(firstPosition>0 || firstRow!=null && firstRow.getTop()<rv.getPaddingTop(),"browse below the initial viewport");
        Object home=active("HomeFragment");Object tabs=field(home,"f9661G0");
        View selected=(View)field(call(tabs,"getTabAt",index),"view");
        main(() -> host.getClass().getField("lastBackPressAt").setLong(host,0));
        key(4);SystemClock.sleep(350);
        check(!host.isFinishing(),"first Back keeps application open");
        check(selected.hasFocus(),"first Back focuses the selected top tab");
        check((Integer)call(layout,"findFirstVisibleItemPosition")==0,"first Back scrolls content to row zero");
        View top=(View)call(layout,"findViewByPosition",0);
        check(top!=null && top.getTop()>=rv.getPaddingTop(),"first row is aligned with the top after Back");
        check((Long)field(host,"lastBackPressAt")==0L,"first Back does not arm exit confirmation");
        main(() -> host.getClass().getField("lastBackPressAt").setLong(host,0));
        if(!observe) {
            key(20);SystemClock.sleep(250);
            check(!selected.hasFocus() && rv.hasFocus(),"Down re-enters the current page from the top tab");
            check((Integer)call(layout,"findFirstVisibleItemPosition")==0,"Down starts at the top rather than old focus");
            if(index>=2) {
                if(host.getCurrentFocus()!=null && host.getCurrentFocus().getId()!=id("click_view"))key(20);
                View firstLane=(View)call(layout,"findViewByPosition",0);
                check(firstLane!=null && firstLane.getTop()>=rv.getPaddingTop(),"returning to lane cards does not jump down again");
            }
            main(() -> host.getClass().getField("lastBackPressAt").setLong(host,System.currentTimeMillis()));
            key(4);check(selected.hasFocus() && !host.isFinishing(),"content Back ignores and clears a stale exit timer");
            check((Long)field(host,"lastBackPressAt")==0L,"returning to tabs resets stale exit timer");
        }
    }
    private void catalog(int homeIndex) throws Exception {
        stage="catalog from home "+homeIndex;
        Object page=chooseHome(homeIndex);View rv=(View)field(page,"recyclerView");Object layout=field(page,"listLayout");
        View[] moreRef={null};String[] laneTitle={null};
        until(() -> {
            main(() -> {
                List<?> modules=(List<?>)field(field(page,"listAdapter"),"items");int chosen=-1;
                for(int i=0;i<modules.size();i++) {
                    Object module=modules.get(i);List<?> headers=(List<?>)call(module,"getHeaders");
                    if(headers!=null && !headers.isEmpty() && ((String)call(headers.get(0),"getUrl")).contains("index_type")) {
                        if(chosen<0)chosen=i;
                        if(homeIndex==3 && ((String)call(module,"getTitle")).contains("热门电影")){chosen=i;break;}
                    }
                }
                if(chosen<0)return;
                String title=(String)call(modules.get(chosen),"getTitle");
                call(layout,"scrollToPositionWithOffset",chosen,300);
                Object holder=call(rv,"findViewHolderForAdapterPosition",chosen);
                if(holder==null || !holder.getClass().getSimpleName().equals("SeriesLaneViewHolder"))return;
                View row=(View)field(holder,"itemView");
                android.widget.TextView label=row.findViewById(id("top_title"));
                View button=row.findViewById(id("button_more"));
                if(button!=null && button.isShown() && title.contentEquals(label.getText())) {
                    moreRef[0]=button;laneTitle[0]=title;
                }
            });return moreRef[0]!=null;
        },"stable visible browse-more lane");
        check(true,"real home lane has a visible browse-more entry: "+laneTitle[0]);
        View more=moreRef[0];main(() -> {more.requestFocusFromTouch();more.performClick();});
        Object[] browser={null};until(() -> {browser[0]=active("SeriesBrowserFragment");return browser[0]!=null;},"catalog route");
        Object screen=browser[0];View grid=(View)field(screen,"f9493G0");Object gridLayout=field(screen,"f9494H0");
        until(() -> !((List<?>)field(field(screen,"f9495I0"),"items")).isEmpty() && !(Boolean)field(screen,"f9507Y0"),"catalog first response");
        SystemClock.sleep(800);
        check((Integer)call(gridLayout,"findFirstVisibleItemPosition")==0,"new catalog opens at the first row: first="+call(gridLayout,"findFirstVisibleItemPosition")+", focus="+focusedPosition(grid)+", filter="+field(screen,"f9502S0"));
        check(focusedPosition(grid)==0,"new catalog focuses the first poster");
        Object firstPage=api("getAllSeries",1,field(screen,"f9500Q0"),field(field(screen,"f9497L0"),"f11052a"));
        List<?> expected=(List<?>)call(firstPage,"getList");
        List<?> actual=(List<?>)field(field(screen,"f9495I0"),"items");
        check(!expected.isEmpty() && call(expected.get(0),"getSeason_id").equals(call(actual.get(0),"getSeason_id")),
                "first poster matches the server's first page, not a later page");
        View first= (View)call(gridLayout,"findViewByPosition",0);
        check(first!=null && first.getTop()>=grid.getPaddingTop(),"first poster row is fully visible");
        if(!observe) {
            focusAt(grid,18);
            until(() -> {
                View card=(View)call(gridLayout,"findViewByPosition",18);
                return card!=null && card.getTop()>=grid.getPaddingTop()
                        && card.getBottom()<=grid.getHeight()-grid.getPaddingBottom();
            },"focused poster fully inside viewport");
            SystemClock.sleep(600);main(() -> call(grid,"stopScroll"));
            int before=(Integer)call(gridLayout,"findFirstVisibleItemPosition");
            View poster=holderView(grid,18).findViewById(id("click_view"));main(() -> poster.performClick());
            until(() -> active("SeriesDetailFragment")!=null,"series detail");SystemClock.sleep(500);
            key(4);until(() -> active("SeriesBrowserFragment")!=null && focusedPosition(grid)==18,"restored poster");
            check(focusedPosition(grid)==18,"return from detail preserves the chosen poster");
            check((Integer)call(gridLayout,"findFirstVisibleItemPosition")==before,"return from detail preserves catalog position: "+before+" -> "+call(gridLayout,"findFirstVisibleItemPosition"));
            Object oldFirst=((List<?>)field(field(screen,"f9495I0"),"items")).get(0);
            View filters=(View)field(screen,"J0");View filterRow=holderView(filters,0);
            View filter=filterRow.findViewById(id("click_view"));if(filter==null)filter=filterRow;
            final View filterClick=filter;main(() -> {filterClick.requestFocusFromTouch();filterClick.performClick();});
            SystemClock.sleep(300);key(20);key(23);
            until(() -> !(Boolean)field(screen,"f9507Y0") && !((List<?>)field(field(screen,"f9495I0"),"items")).isEmpty()
                    && ((List<?>)field(field(screen,"f9495I0"),"items")).get(0)!=oldFirst,"changed sort response");
            SystemClock.sleep(450);
            check((Integer)call(gridLayout,"findFirstVisibleItemPosition")==0,"changing sort resets catalog to row zero");
            check(!grid.hasFocus() || focusedPosition(grid)==0,"changing sort does not restore an old lower-row focus");
        }
        for(int i=0;i<3 && active("SeriesBrowserFragment")!=null;i++)key(4);
        check(active("HomeFragment")!=null,"catalog Back restores home");
    }
    @Override public void onStart() {
        Bundle result=new Bundle();int status=Activity.RESULT_CANCELED;
        try {
            loader=getTargetContext().getClassLoader();
            host=startActivitySync(new Intent().setClassName(getTargetContext(),"com.xx.blbl.ui.MainActivity")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            setInTouchMode(false);SystemClock.sleep(1500);
            network=call(loader.loadClass("com.xx.blbl.AppController"),"network");
            if(!navigationCase.equals("catalog"))for(int i=0;i<4;i++)homeBack(i);
            if(!navigationCase.equals("home")){catalog(2);catalog(3);}
            if(!observe && !navigationCase.equals("catalog")) {
                stage="exit only from top tabs";Object home=active("HomeFragment");Object tabs=field(home,"f9661G0");
                int index=(Integer)call(tabs,"getSelectedTabPosition");View selected=(View)field(call(tabs,"getTabAt",index),"view");
                main(() -> {selected.requestFocusFromTouch();host.getClass().getField("lastBackPressAt").setLong(host,0);});
                key(4);check(!host.isFinishing() && (Long)field(host,"lastBackPressAt")>0L,"Back on top tabs asks for exit confirmation");
                key(4);check(host.isFinishing(),"consecutive Back confirms exit");
            }
            result.putString("stream",observe?"HOME_NAV_OBSERVATION_COMPLETE failures="+observedFailures:
                    "HOME_NAV_PASS assertions="+checks+" four home tabs, deep browse Back, fresh catalog origin, detail return and sort reset");
            status=Activity.RESULT_OK;
        } catch(Throwable error) {
            result.putString("stream","FAIL "+stage+" "+error);
            try {
                Object browser=active("SeriesBrowserFragment");
                if(browser!=null) main(() -> {
                    View grid=(View)field(browser,"f9493G0");View anchor=((View)call(browser,"getView")).findViewById(id("button_back_1"));
                    View f=host.getCurrentFocus();
                    String debug="NAV_DEBUG first="+call(field(browser,"f9494H0"),"findFirstVisibleItemPosition")
                            +" focus="+(f==null?"none":f.getClass().getSimpleName()+"#"+f.getId()+" "+f.getContentDescription())
                            +" gridShown="+grid.isShown()+" gridFocus="+grid.hasFocus()+" touch="+grid.isInTouchMode()
                            +" descendant="+((android.view.ViewGroup)grid).getDescendantFocusability()+" anchorFocus="+anchor.hasFocus()
                            +" hasWindowFocus="+grid.hasWindowFocus()+" filter="+field(browser,"f9502S0")
                            +" count="+call(field(browser,"f9495I0"),"getItemCount");
                    android.util.Log.i("BBLL_NAV_TEST",debug);
                });
            } catch(Exception ignored) {}
        }
        finally {if(host!=null)try{main(() -> host.finish());}catch(Exception ignored){} finish(status,result);}
    }
}
