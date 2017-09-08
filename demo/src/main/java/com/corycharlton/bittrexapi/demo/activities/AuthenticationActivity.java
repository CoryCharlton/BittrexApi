package com.corycharlton.bittrexapi.demo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.internal.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthenticationActivity extends Activity implements TextView.OnEditorActionListener, View.OnClickListener, View.OnFocusChangeListener {
    private static final String TAG = AuthenticationActivity.class.getSimpleName();

    @BindView(R.id.key_edittext) EditText _keyEditText;
    @BindView(R.id.secret_edittext) EditText _secretEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        _keyEditText.setText(ApplicationSettings.instance().getKey());
        _keyEditText.setOnEditorActionListener(this);
        _keyEditText.setOnFocusChangeListener(this);

        _secretEditText.setText(ApplicationSettings.instance().getSecret());
        _secretEditText.setOnEditorActionListener(this);
        _secretEditText.setOnFocusChangeListener(this);
    }

    @NonNull
    @Override
    protected String getTag() {
        return TAG;
    }

    @OnClick({R.id.cancel_button, R.id.ok_button})
    @Override
    public void onClick(View view) {
        final int viewId = view.getId();

        switch (viewId) {
            case R.id.cancel_button:
                finish();

                break;
            case R.id.ok_button:
                updateAuthenticationSettings();
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        final int id = textView.getId();
        switch (id) {
            case R.id.key_edittext:
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    _secretEditText.requestFocus();
                }

                return true;
            case R.id.secret_edittext:
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    updateAuthenticationSettings();
                }

                return true;
        }

        return false;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        final int id = view.getId();
        switch (id) {
            case R.id.key_edittext:
            case R.id.secret_edittext:
                if (hasFocus) {
                    showKeyboard(view);
                } else {
                    hideKeyboard(view);
                }
                break;
        }
    }

    public static void startActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, AuthenticationActivity.class));
    }

    private void updateAuthenticationSettings() {
        final String key = _keyEditText.getText().toString();
        final String secret = _secretEditText.getText().toString();

        if (!StringUtils.isNullOrWhiteSpace(key) && !StringUtils.isNullOrWhiteSpace(secret)) {
            ApplicationSettings.instance().setKey(key);
            ApplicationSettings.instance().setSecret(secret);

            finish();
        }
    }
}
