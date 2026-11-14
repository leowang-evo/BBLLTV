package com.xx.blbl.ui;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.model.favorite.FavoriteFolderModel;
import com.xx.blbl.model.user.LevelInfoModel;
import com.xx.blbl.model.user.UserDetailInfoModel;
import com.xx.blbl.model.user.VipInfoModel;
import com.xx.blbl.network.NetResultCallback;
import com.xx.blbl.network.NetworkManager;
import com.xx.blbl.network.response.BaseResponse;
import com.xx.blbl.network.response.FavoriteFoldersWrapper;
import com.xx.blbl.platform.CacheMaintenance;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.dialog.AccountDialog;
import com.xx.blbl.ui.screen.ScreenEvents;
import com.xx.blbl.ui.view.TabBarView;

import kotlin.Lazy;
import kotlin.LazyKt;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/** Recovered application navigation; FragmentActivity supplies the SDK lifecycle. */
public final class MainActivity extends FragmentActivity implements ScreenEvents.Listener {
    public interface BackHandler {
        boolean handleBack();
    }

    public static final int f9460b0 = 0;
    public boolean f9463P, f9464Q, f9465R = true;
    public TabBarView tabBar;
    public View divider;
    public final ArrayList<Fragment> tabScreens = new ArrayList<>();
    public int selectedTab;
    public long lastBackPressAt;
    private boolean accountRefreshing, accountOpenPending;
    public final Lazy<NetworkManager> f9471X = LazyKt.lazy(AppController::network);
    public final Lazy<AppState> f9472Y = LazyKt.lazy(AppController::state);
    public final WeakReference<MainActivity> f9474a0 = new WeakReference<>(this);
    private static final String[] TABS = {
        "com.xx.blbl.ui.home.HomeFragment", "com.xx.blbl.ui.catalog.CategoryFragment",
        "com.xx.blbl.ui.home.DynamicFragment", "com.xx.blbl.ui.live.LiveFragment",
        "com.xx.blbl.ui.user.MyLibraryFragment", "com.xx.blbl.ui.search.SearchFragment"
    };

    public FragmentManager fragments() {
        return getSupportFragmentManager();
    }

    public AppState applicationState() {
        return AppController.state();
    }

    private Fragment fragment(String className) {
        return fragments().getFragmentFactory().instantiate(getClassLoader(), className);
    }

    private SharedPreferences preferences() {
        return getSharedPreferences("BLBL", 0);
    }

    private int theme() {
        switch (preferences().getInt("theme", 1)) {
            case 0:
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                return hour >= 6 && hour < 18 ? R.style.LightTheme : R.style.DarkTheme;
            case 2:
                return R.style.LightTheme;
            case 3:
                return R.style.ClassicsTheme;
            case 4:
                return R.style.PinkTheme;
            case 5:
                return R.style.BlueTheme;
            case 6:
                return R.style.PurpleTheme;
            case 7:
                return R.style.RedTheme;
            default:
                return R.style.DarkTheme;
        }
    }

    @Override
    public void onCreate(Bundle state) {
        setTheme(theme());
        // The recovered UI uses canonical upstream Fragment state. On its first
        // upgrade, retain account preferences but discard the old R8 class state.
        if (preferences().getInt("source_ui_schema", 0) != 3) {
            state = null;
            preferences().edit().putInt("source_ui_schema", 3).apply();
        }
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        this.tabBar = findViewById(R.id.myTabView);
        this.divider = findViewById(R.id.divide);
        ((AppCompatImageView) findViewById(R.id.mainBackgroundImage))
                .setVisibility(preferences().getInt("theme", 1) == 3 ? View.VISIBLE : View.GONE);
        this.tabBar.setOnTabClick(this::selectTab);
        this.tabBar.setActionListener(
                new TabBarView.ActionListener() {
                    public void onSettings() {
                        showScreen(fragment("com.xx.blbl.ui.settings.SettingsFragment"), "setting");
                    }

                    public void onAccount(boolean signedIn) {
                        if (accountRefreshing) accountOpenPending = true;
                        else openAccount();
                    }
                });
        for (int index = 0; index < TABS.length; index++) {
            Fragment restored = fragments().findFragmentByTag("tab" + index);
            this.tabScreens.add(restored);
        }
        int selected;
        if (state != null) selected = state.getInt("recovery_selected_tab", 0);
        else {
            int page = preferences().getInt("defaultStartPage", 1);
            selected = page == 2 ? 1 : page == 3 ? 2 : page >= 4 && page < 9 ? 4 : 0;
        }
        this.selectedTab = selected;
        selectTab(selected);
        fragments()
                .addOnBackStackChangedListener(
                        () -> {
                            if (fragments().getBackStackEntryCount() == 0) {
                                FragmentTransaction transaction = fragments().beginTransaction();
                                transaction.show(this.tabScreens.get(this.selectedTab)).commit();
                                showTabBar(true);
                            }
                        });
        showTabBar(fragments().getBackStackEntryCount() == 0);
        applicationState().displayWidth = getResources().getDisplayMetrics().widthPixels;
        applicationState().displayHeight = getResources().getDisplayMetrics().heightPixels;
        if (!preferences().getBoolean("userAgree", false)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.user_agree_title)
                    .setMessage(R.string.user_agree)
                    .setCancelable(false)
                    .setNegativeButton(R.string.reject, (dialog, which) -> finish())
                    .setPositiveButton(
                            R.string.accept,
                            (dialog, which) ->
                                    preferences().edit().putBoolean("userAgree", true).apply())
                    .show();
        }
        refreshUser();
        CacheMaintenance.enforceLimit(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ScreenEvents.register(this);
    }

    @Override
    protected void onStop() {
        ScreenEvents.unregister(this);
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        state.putInt("recovery_selected_tab", this.selectedTab);
        super.onSaveInstanceState(state);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preferences().getBoolean("fullscreenEnable", true)) {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
            WindowInsetsControllerCompat insets =
                    WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
            insets.hide(WindowInsetsCompat.Type.systemBars());
            insets.setSystemBarsBehavior(
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && event.getAction() == KeyEvent.ACTION_DOWN)
            ScreenEvents.post("keyMenuPress");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onMessageEvent(String event) {
        if ("signIn".equals(event)) refreshUser();
        if ("updateUserInfo".equals(event) || "signOut".equals(event)) updateAccount();
    }

    private boolean canUpdateAccount() {
        return !isFinishing() && !isDestroyed();
    }

    private void openAccount() {
        if (!canUpdateAccount()) return;
        if (applicationState().signedIn) new AccountDialog(this).show();
        else showLogin();
    }

    private void finishAccountRefresh(boolean valid) {
        accountRefreshing = false;
        boolean open = accountOpenPending;
        accountOpenPending = false;
        if (valid && open) openAccount();
    }

    private void updateAccount() {
        if (!canUpdateAccount()) return;
        this.tabBar.setAccount(
                applicationState().signedIn,
                applicationState().avatar,
                AppController.network().getCurrentUA());
    }

    private void refreshUser() {
        if (accountRefreshing) return;
        accountRefreshing = true;
        AppController.network()
                .getUserDetailInfo(
                        new NetResultCallback<BaseResponse<UserDetailInfoModel>>() {
                            public void onFailure(Throwable error) {
                                finishAccountRefresh(false);
                                if (canUpdateAccount())
                                    Toast.makeText(
                                                    MainActivity.this,
                                                    String.valueOf(
                                                            error == null
                                                                    ? null
                                                                    : error.getMessage()),
                                                    Toast.LENGTH_SHORT)
                                            .show();
                            }

                            public void onResponse(BaseResponse<UserDetailInfoModel> response) {
                                if (!canUpdateAccount()
                                        || response == null
                                        || response.getData() == null) {
                                    finishAccountRefresh(false);
                                    return;
                                }
                                UserDetailInfoModel user = response.getData();
                                if (user.getWbi_img() != null)
                                    AppController.network().setWbiInfo(user.getWbi_img());
                                if (!user.isLogin()) {
                                    applicationState().clearAccount();
                                    updateAccount();
                                    ScreenEvents.post("signOut");
                                    finishAccountRefresh(true);
                                    return;
                                }
                                AppState account = applicationState();
                                account.signedIn = true;
                                account.premiumMember = user.getVipStatus() == 1;
                                account.userId = user.getMid();
                                account.nickname = user.getUname();
                                account.avatar = user.getFace();
                                account.coinBalance = user.getMoney();
                                LevelInfoModel level = user.getLevel_info();
                                if (level != null) account.userLevel = level.getCurrent_level();
                                VipInfoModel vip = user.getVip_label();
                                if (vip != null) account.vipLabel = vip.getText();
                                preferences()
                                        .edit()
                                        .putLong("user_mid", account.userId)
                                        .putString("nickname", account.nickname)
                                        .putString("avatar", account.avatar)
                                        .putInt("user_level", account.userLevel)
                                        .putString("user_vip", account.vipLabel)
                                        .putFloat("coinMoney", account.coinBalance)
                                        .apply();
                                updateAccount();
                                ScreenEvents.post("updateUserInfo");
                                finishAccountRefresh(true);
                                AppController.network().getMainPage();
                                AppController.network()
                                        .getFavoriteFolders(
                                                account.userId,
                                                new NetResultCallback<
                                                        BaseResponse<FavoriteFoldersWrapper>>() {
                                                    public void onFailure(Throwable error) {
                                                        android.util.Log.w(
                                                                "BBLL",
                                                                "Could not refresh default favorite"
                                                                        + " folder",
                                                                error);
                                                    }

                                                    public void onResponse(
                                                            BaseResponse<FavoriteFoldersWrapper>
                                                                    value) {
                                                        if (!canUpdateAccount()
                                                                || value == null
                                                                || value.getData() == null) return;
                                                        List<FavoriteFolderModel> folders =
                                                                value.getData().getList();
                                                        if (folders != null && !folders.isEmpty()) {
                                                            account.defaultFavoriteId =
                                                                    folders.get(0).getId();
                                                            preferences()
                                                                    .edit()
                                                                    .putLong(
                                                                            "defaultFavoriteId",
                                                                            account.defaultFavoriteId)
                                                                    .apply();
                                                        }
                                                    }
                                                });
                            }
                        });
    }

    public void selectTab(int index) {
        if (index < 0 || index >= this.tabScreens.size() || fragments().isStateSaved()) return;
        lastBackPressAt = 0;
        FragmentTransaction transaction =
                fragments()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim,
                                R.anim.nav_default_pop_enter_anim,
                                        R.anim.nav_default_pop_exit_anim);
        for (Fragment tab : this.tabScreens) if (tab != null && tab.isAdded()) transaction.hide(tab);
        Fragment selected = this.tabScreens.get(index);
        if (selected == null) {
            selected = fragment(TABS[index]);
            this.tabScreens.set(index, selected);
        }
        if (!selected.isAdded()) transaction.add(R.id.container, selected, "tab" + index);
        if (fragments().getBackStackEntryCount() == 0) transaction.show(selected);
        transaction.commit();
        this.selectedTab = index;
        this.tabBar.a(index);
        ScreenEvents.post("clickTab" + index);
    }

    public void showTabBar(boolean show) {
        int visibility = show ? View.VISIBLE : View.GONE;
        if (this.tabBar.getVisibility() == visibility) return;
        this.tabBar.setVisibility(visibility);
        this.divider.setVisibility(visibility);
        this.tabBar.startAnimation(
                AnimationUtils.loadAnimation(
                        this, show ? R.anim.slide_in_to_left : R.anim.slide_out_to_left));
    }

    public void showLogin() {
        showScreen(fragment("com.xx.blbl.ui.auth.QrLoginFragment"), "signIn");
    }

    public void showScreen(Fragment target, String tag) {
        if (fragments().isStateSaved()) return;
        lastBackPressAt = 0;
        fragments().executePendingTransactions();
        FragmentTransaction transaction =
                fragments()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        for (Fragment current : fragments().getFragments())
            if (current.isVisible()) {
                if (current.getView() != null) current.getView().clearFocus();
                transaction.hide(current);
            }
        transaction.add(R.id.container, target, tag).addToBackStack(tag).commit();
        showTabBar(false);
    }

    @Override
    public void onBackPressed() {
        accountOpenPending = false;
        if (fragments().isStateSaved()) return;
        fragments().executePendingTransactions();
        List<Fragment> fragments = fragments().getFragments();
        for (int index = fragments.size() - 1; index >= 0; index--) {
            Fragment current = fragments.get(index);
            if (current.isVisible()
                    && current instanceof BackHandler
                    && ((BackHandler) current).handleBack()) {
                lastBackPressAt = 0;
                return;
            }
        }
        if (fragments().getBackStackEntryCount() > 0) {
            lastBackPressAt = 0;
            fragments().popBackStackImmediate();
            fragments().executePendingTransactions();
            if (fragments().getBackStackEntryCount() == 0) showTabBar(true);
            List<Fragment> remaining = fragments().getFragments();
            for (int index = remaining.size() - 1; index >= 0; index--) {
                View view = remaining.get(index).getView();
                if (remaining.get(index).isVisible() && view != null) {
                    view.requestFocus();
                    break;
                }
            }
            return;
        }
        if (this.selectedTab == 5) {
            selectTab(0);
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.lastBackPressAt <= 2000) finish();
        else {
            this.lastBackPressAt = now;
            Toast.makeText(this, R.string.app_exit, Toast.LENGTH_SHORT).show();
        }
    }
}
