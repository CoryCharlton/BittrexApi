package com.corycharlton.bittrexapi.demo.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unused,WeakerAccess")
abstract class Settings {

    private final Context _context;
    private final SharedPreferences _sharedPreferences;
    private final HashMap<String, HashSet<String>> _stringSets;
    private final Object _stringSetsLock = new Object();

    Settings(@NonNull Context context) {
        _context = context;
        _sharedPreferences = initializeSharedPreferences(_context);
        _stringSets = new HashMap<>();
    }

    @NonNull
    protected Context getContext() {
        return _context;
    }

    @NonNull
    private SharedPreferences getSharedPreferences() {
        return _sharedPreferences;
    }

    @NonNull
    protected Resources getResources() {
        return _context.getResources();
    }

    @NonNull
    protected abstract SharedPreferences initializeSharedPreferences(@NonNull Context context);

    // region SharedPreferences Helper Methods
    protected boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    protected int getInt(@NonNull String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    protected long getLong(@NonNull String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    protected String getString(@NonNull String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    protected HashSet<String> getStringSet(@NonNull String key, Set<String> defaultValue) {
        synchronized (_stringSetsLock) {
            if (!_stringSets.containsKey(key)) {
                _stringSets.put(key, new HashSet<>(getSharedPreferences().getStringSet(key, defaultValue)));
            }

            return _stringSets.get(key);
        }
    }

    protected void putBoolean(@NonNull String key, boolean value) {
        putBoolean(key, value, false);
    }

    @SuppressLint("CommitPrefEdits")
    protected void putBoolean(@NonNull String key, boolean value, boolean shouldCommit) {
        final SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putBoolean(key, value);

        tryApply(editor, shouldCommit);
    }

    protected void putInt(@NonNull String key, int value) {
        putInt(key, value, false);
    }

    @SuppressLint("CommitPrefEdits")
    protected void putInt(@NonNull String key, int value, boolean shouldCommit) {
        final SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putInt(key, value);

        tryApply(editor, shouldCommit);
    }

    @SuppressLint("CommitPrefEdits")
    protected void putLong(@NonNull String key, long value, boolean shouldCommit) {
        final SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putLong(key, value);

        tryApply(editor, shouldCommit);
    }

    protected void putString(@NonNull String key, String value) {
        putString(key, value, false);
    }

    @SuppressLint("CommitPrefEdits")
    protected void putString(@NonNull String key, String value, boolean shouldCommit) {
        final SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(key, value);

        tryApply(editor, shouldCommit);
    }

    protected void putStringSet(@NonNull String key, Set<String> values) {
        putStringSet(key, values, false);
    }

    @SuppressLint("CommitPrefEdits")
    protected void putStringSet(@NonNull String key, Set<String> values, boolean shouldCommit) {
        synchronized (_stringSetsLock) {
            final HashSet<String> hashedValues = new HashSet<>(values);
            _stringSets.put(key, hashedValues);

            final SharedPreferences.Editor editor = _sharedPreferences.edit();
            editor.putStringSet(key, hashedValues);
            tryApply(editor, shouldCommit);
        }
    }

    @SuppressLint("CommitPrefEdits")
    protected void remove(@NonNull String key, boolean shouldCommit) {
        final SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.remove(key);

        tryApply(editor, shouldCommit);
    }

    @SuppressLint("CommitPrefEdits")
    private void tryApply(@NonNull SharedPreferences.Editor editor, boolean shouldCommit) {
        if (shouldCommit) {
            editor.commit();
        } else {
            try {
                editor.apply();
            } catch (AbstractMethodError unused) {
                editor.commit();
            }
        }
    }
    // endregion

    // region SharedPreferenceChangeListener
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        _sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
    // endregion
}
