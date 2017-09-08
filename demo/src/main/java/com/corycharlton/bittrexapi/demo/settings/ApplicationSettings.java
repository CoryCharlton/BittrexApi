package com.corycharlton.bittrexapi.demo.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.util.StringUtils;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNull;
import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;
import static com.corycharlton.bittrexapi.internal.util.Ensure.isValidState;

@SuppressWarnings("WeakerAccess")
public final class ApplicationSettings extends Settings {
    //TODO: Get default values from resources?
    public static final String KEY_KEY = "key";
    public static final String KEY_SECRET = "secret";

    private ApplicationSettings(@NonNull Context context) {
        super(context);

        remove(KEY_KEY, true);
        remove(KEY_SECRET, true);
    }

    @NonNull
    @Override
    protected SharedPreferences initializeSharedPreferences(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @NonNull
    public String getKey() {
        return getString(KEY_KEY, StringUtils.EMPTY);
    }

    @NonNull
    public String getSecret() {
        return getString(KEY_SECRET, StringUtils.EMPTY);
    }

    public boolean isAuthenticationConfigured() {
        return !StringUtils.isNullOrWhiteSpace(getKey()) && !StringUtils.isNullOrWhiteSpace(getSecret());
    }

    public void setKey(@NonNull String key) {
        isNotNullOrWhitespace("key", key);

        putString(KEY_KEY, key, true);
    }

    public void setSecret(@NonNull String secret) {
        isNotNullOrWhitespace("secret", secret);

        putString(KEY_SECRET, secret, true);
    }

    // region Singleton
    @SuppressLint("StaticFieldLeak")
    private static ApplicationSettings _instance;
    private static final Object _instanceLock = new Object();

    public static ApplicationSettings instance() {
        synchronized (_instanceLock) {
            isValidState("_instance", _instance != null, "ApplicationSettings has not been initialized");
        }

        return _instance;
    }

    public static void initialize(@NonNull Context context) {
        isNotNull("context", context);

        synchronized (_instanceLock) {
            if (_instance == null) {
                _instance = new ApplicationSettings(context.getApplicationContext());
            }
        }
    }
    // endregion
}
