package com.xx.blbl.ui.screen;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.xx.blbl.AppController;
import com.xx.blbl.R;
import com.xx.blbl.state.AppState;
import com.xx.blbl.ui.MainActivity;

import kotlin.Lazy;
import kotlin.LazyKt;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Application fragment shell on the published AndroidX lifecycle and view APIs. */
public abstract class BaseScreenFragment extends Fragment implements ScreenEvents.Listener {
    public AppCompatImageView backButton;
    public AppCompatTextView screenTitle;
    public final Lazy<AppState> f9728C0 = LazyKt.lazy(AppController::state);
    public final CoroutineScope f9729D0 = CoroutineScopeKt.MainScope();
    private final CoroutineScope ioScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    public final Lazy<CoroutineScope> E0 = LazyKt.lazy(() -> ioScope);
    public boolean cacheLoaded;
    public MainActivity activityHost;
    public View screenView;
    public FrameLayout contentContainer;
    public ConstraintLayout statusContainer;
    public AppCompatTextView statusText;
    public AppCompatImageView statusImage;
    public AppCompatTextView retryButton;
    public ProgressBar loadingIndicator;
    public LinearLayoutCompat toolbar;
    private static final ExecutorService CACHE_IO = Executors.newFixedThreadPool(2);
    protected final Handler mainHandler = new Handler(Looper.getMainLooper());
    private int viewGeneration;

    public final CoroutineScope S() {
        return E0.getValue();
    }

    public String cacheKey() {
        return getClass().getSimpleName() + "Cache";
    }

    public abstract int layoutResource();

    public final AppState applicationState() {
        return f9728C0.getValue();
    }

    public final void runIo(Runnable action) {
        CACHE_IO.execute(action);
    }

    public final void runOnMain(Runnable action) {
        if (Looper.myLooper() == Looper.getMainLooper()) action.run();
        else mainHandler.post(action);
    }

    public final void showContent() {
        if (!isAttachedToHost()) return;
        if (this.statusContainer != null) this.statusContainer.setVisibility(View.GONE);
        if (this.contentContainer != null) this.contentContainer.setVisibility(View.VISIBLE);
    }

    public void renderCachedData(Object value) {
        Objects.requireNonNull(value, "obj");
    }

    public void reloadContent() {}

    public final void cacheData(Serializable value) {
        Objects.requireNonNull(value, "obj");
        final String key = cacheKey();
        runIo(() -> applicationState().cache.b(key, value));
    }

    public final void setScreenTitle(String text) {
        if (isAttachedToHost() && this.screenTitle != null) this.screenTitle.setText(text);
    }

    public final void showEmptyState() {
        if (isAttachedToHost()) showStatus(R.mipmap.empty, getString(R.string.empty));
    }

    public final void showNetworkError() {
        if (isAttachedToHost()) showStatus(R.mipmap.net_error, getString(R.string.net_error));
    }

    public final void showError(String error) {
        if (isAttachedToHost()) showStatus(R.mipmap.net_error, error == null ? "" : error);
    }

    public final void setLoading(boolean loading) {
        if (isAttachedToHost() && this.loadingIndicator != null)
            this.loadingIndicator.setVisibility(loading ? View.VISIBLE : View.GONE);
    }

    public final void showStatus(int image, String message) {
        if (!isAttachedToHost()) return;
        if (this.statusContainer != null) this.statusContainer.setVisibility(View.VISIBLE);
        if (this.statusImage != null) this.statusImage.setImageResource(image);
        if (this.statusText != null) this.statusText.setText(message);
        if (this.retryButton != null) this.retryButton.requestFocus();
        if (this.contentContainer != null) this.contentContainer.setVisibility(View.GONE);
    }

    public boolean hasToolbar() {
        return false;
    }

    public final void loadCachedData() {
        if (this.cacheLoaded) return;
        final String key = cacheKey();
        final int generation = viewGeneration;
        runIo(
                () -> {
                    Object cached = applicationState().cache.a(key);
                    this.cacheLoaded = true;
                    if (cached != null)
                        runOnMain(
                                () -> {
                                    if (generation == viewGeneration
                                            && isAttachedToHost()
                                            && this.screenView != null) renderCachedData(cached);
                                });
                });
    }

    public abstract void bindScreen(View view);

    // Application aliases retained while pages move off R8-inlined SDK internals.
    public final MainActivity hostActivity() {
        return getActivity() instanceof MainActivity ? (MainActivity) getActivity() : null;
    }

    public final FragmentManager childFragments() {
        return getChildFragmentManager();
    }

    public final Context contextOrNull() {
        return getContext();
    }

    public final FragmentManager parentFragments() {
        return getParentFragmentManager();
    }

    public final Resources resources() {
        return getResources();
    }

    public final String stringResource(int resource) {
        return getString(resource);
    }

    public final boolean isAttachedToHost() {
        return isAdded();
    }

    public final boolean isScreenHidden() {
        return isHidden();
    }

    public final boolean isOnBackStack() {
        String tag = getTag();
        if (tag == null || !isAdded()) return false;
        FragmentManager manager = getParentFragmentManager();
        for (int index = 0; index < manager.getBackStackEntryCount(); index++)
            if (tag.equals(manager.getBackStackEntryAt(index).getName())) return true;
        return false;
    }

    public final boolean isScreenVisible() {
        return isVisible();
    }

    public final MainActivity requireHostActivity() {
        return (MainActivity) requireActivity();
    }

    public final Context requireScreenContext() {
        return requireContext();
    }

    public final View requireScreenView() {
        return requireView();
    }

    public final void setScreenArguments(Bundle arguments) {
        setArguments(arguments);
    }

    public void screenHiddenChanged(boolean hidden) {}

    public void screenPaused() {}

    public void screenResumed() {}

    public void saveScreenState(Bundle state) {}

    public void screenStarted() {
        ScreenEvents.register(this);
    }

    public void screenStopped() {
        ScreenEvents.unregister(this);
    }

    public void screenViewCreated(View view) {}

    @Override
    public void onMessageEvent(String event) {}

    public void initializeScreen(Bundle state) {
        this.activityHost = hostActivity();
    }

    public View createScreenView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        if (this.screenView == null) {
            View root = inflater.inflate(R.layout.fragment_base, container, false);
            this.screenView = root;
            this.contentContainer = root.findViewById(R.id.contentContainer);
            this.statusText = root.findViewById(R.id.text_error);
            this.statusImage = root.findViewById(R.id.image_error);
            this.retryButton = root.findViewById(R.id.button_retry);
            this.toolbar = root.findViewById(R.id.top_bar);
            this.backButton = root.findViewById(R.id.button_back);
            this.screenTitle = root.findViewById(R.id.text_main_title);
            this.loadingIndicator = root.findViewById(R.id.loading_progress_bar);
            this.statusContainer = root.findViewById(R.id.view_error);
            if (this.toolbar != null)
                this.toolbar.setVisibility(hasToolbar() ? View.VISIBLE : View.GONE);
            if (this.retryButton != null)
                this.retryButton.setOnClickListener(view -> reloadContent());
            if (this.backButton != null)
                this.backButton.setOnClickListener(
                        view -> {
                            MainActivity activity = hostActivity();
                            if (activity != null) activity.onBackPressed();
                        });
            bindScreen(inflater.inflate(layoutResource(), this.contentContainer, true));
        }
        return this.screenView;
    }

    public void releaseScreenView() {
        viewGeneration++;
        this.activityHost = null;
        this.statusContainer = null;
        this.statusText = null;
        this.statusImage = null;
        this.retryButton = null;
        this.loadingIndicator = null;
        this.screenView = null;
        this.toolbar = null;
        this.backButton = null;
        this.screenTitle = null;
        if (this.contentContainer != null) this.contentContainer.removeAllViews();
        this.contentContainer = null;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        initializeScreen(state);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return createScreenView(inflater, container, state);
    }

    @Override
    public void onViewCreated(View view, Bundle state) {
        super.onViewCreated(view, state);
        screenViewCreated(view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        screenHiddenChanged(hidden);
    }

    @Override
    public void onStart() {
        super.onStart();
        screenStarted();
    }

    @Override
    public void onResume() {
        super.onResume();
        screenResumed();
    }

    @Override
    public void onPause() {
        screenPaused();
        super.onPause();
    }

    @Override
    public void onStop() {
        screenStopped();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        saveScreenState(state);
    }

    @Override
    public void onDestroyView() {
        releaseScreenView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        ScreenEvents.unregister(this);
        CoroutineScopeKt.cancel(f9729D0, null);
        CoroutineScopeKt.cancel(ioScope, null);
        super.onDestroy();
    }
}
