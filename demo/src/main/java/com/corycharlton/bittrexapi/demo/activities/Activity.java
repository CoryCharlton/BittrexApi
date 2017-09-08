package com.corycharlton.bittrexapi.demo.activities;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.corycharlton.bittrexapi.demo.utils.Log;

import butterknife.ButterKnife;

public abstract class Activity extends AppCompatActivity {
    private boolean _isRunning;

    //@BindView(R.id.title_textview) @Nullable TextView _titleTextView;
    //@BindView(R.id.toolbar) @Nullable Toolbar _toolbar;

    // region Activity lifecycle methods
    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(getTag(), "onCreate()");
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();

        Log.v(getTag(), "onStart()");
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();

        Log.v(getTag(), "onResume()");

        _isRunning = true;
    }

    @CallSuper
    @Override
    protected void onPause() {
        _isRunning = false;

        Log.v(getTag(), "onPause()");

        super.onPause();
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();

        Log.v(getTag(), "onStop()");
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        Log.v(getTag(), "onDestroy()");

        super.onDestroy();
    }
    // endregion

    @NonNull
    protected abstract String getTag();

    /*
    @CallSuper
    void initToolbar() {
        initToolbar(false, false);
    }

    @SuppressWarnings("SameParameterValue")
    @CallSuper
    private void initToolbar(boolean showHomeAsUp, boolean showTitle) {
        if (_toolbar == null) {
            return;
        }

        setSupportActionBar(_toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            //actionBar.setElevation(4);
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(showHomeAsUp);
            actionBar.setDisplayShowTitleEnabled(showTitle);
        }
    }
    */

    public boolean isRunning() {
        return _isRunning;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);

        ButterKnife.bind(this);
    }

    /*
    @Override
    public void setTitle(CharSequence title) {
        if (_titleTextView != null) {
            _titleTextView.setText(title);
        }
    }

    @Override
    public void setTitle(@StringRes int titleId) {
        setTitle(getString(titleId));
    }

    public void setTitle(@StringRes int titleId, Object... formatArgs) {
        setTitle(getString(titleId, formatArgs));
    }

    // TODO: Change to container when I implement subtitles
    public void setTitleVisibility(int visibility) {
        if (_titleTextView != null) {
            _titleTextView.setVisibility(visibility);
        }
    }

    public void showDialogFragment(@NonNull DialogFragment dialogFragment, @SuppressWarnings("SameParameterValue") boolean cancelable) {
        dialogFragment.setCancelable(cancelable);
        dialogFragment.setShowsDialog(true);
        dialogFragment.show(getSupportFragmentManager(), "dialog_fragment");
    }
    */
}
