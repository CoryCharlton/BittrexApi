package com.corycharlton.bittrexapi.demo.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.internal.util.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class AuthenticationActivity extends Activity implements View.OnClickListener {
    private static final String TAG = AuthenticationActivity.class.getSimpleName();

    @BindView(R.id.key_edittext) EditText _keyEditText;
    @BindView(R.id.secret_edittext) EditText _secretEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        _keyEditText.setText(ApplicationSettings.instance().getKey());
        _secretEditText.setText(ApplicationSettings.instance().getSecret());
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
                if (!ApplicationSettings.instance().isAuthenticationConfigured()) {
                    System.exit(0);
                } else {
                    finish();
                }

                break;
            case R.id.ok_button:
                final String key = _keyEditText.getText().toString();
                final String secret = _secretEditText.getText().toString();

                if (!StringUtils.isNullOrWhiteSpace(key) && !StringUtils.isNullOrWhiteSpace(secret)) {
                    ApplicationSettings.instance().setKey(key);
                    ApplicationSettings.instance().setSecret(secret);

                    finish();
                }
        }
    }

    public static void startActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, AuthenticationActivity.class));
    }
}
