package com.corycharlton.bittrexapi.demo.utils;


import com.corycharlton.bittrexapi.demo.BuildConfig;

@SuppressWarnings({"SameParameterValue", "UnusedReturnValue"})
public final class Log {
    private Log() {}

    private static volatile boolean _isDebugLoggingEnabled = false;

    public static boolean getIsDebugLoggingEnabled() {
        return BuildConfig.DEBUG || _isDebugLoggingEnabled;
    }

    public static void setIsDebugLoggingEnabled(boolean isDebugLoggingEnabled) {
        _isDebugLoggingEnabled = isDebugLoggingEnabled;
    }

    public static int v(String tag, String msg) {
        return getIsDebugLoggingEnabled() ? android.util.Log.v(tag, msg) : 0;
    }

    public static int v(String tag, String msg, Throwable tr) {
        return getIsDebugLoggingEnabled() ? android.util.Log.v(tag, msg, tr) : 0;
    }

    public static int d(String tag, String msg) {
        return getIsDebugLoggingEnabled() ? android.util.Log.d(tag, msg) : 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return getIsDebugLoggingEnabled() ? android.util.Log.d(tag, msg, tr) : 0;
    }

    public static int i(String tag, String msg) {
        return android.util.Log.i(tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        return android.util.Log.i(tag, msg, tr);
    }

    public static int w(String tag, String msg) {
        return android.util.Log.w(tag, msg);
    }

    public static int w(String tag, String msg, Throwable tr) {
        return android.util.Log.w(tag, msg, tr);
    }

    public static int w(String tag, Throwable tr) {
        return android.util.Log.w(tag, tr);
    }

    public static int e(String tag, String msg) {
        return android.util.Log.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        return android.util.Log.e(tag, msg, tr);
    }
}
