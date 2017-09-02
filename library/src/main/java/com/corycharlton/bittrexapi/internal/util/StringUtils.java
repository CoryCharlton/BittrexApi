package com.corycharlton.bittrexapi.internal.util;

import android.support.annotation.Nullable;

@SuppressWarnings("WeakerAccess")
public final class StringUtils {
    public static final String EMPTY = "";

    public static boolean isNullOrWhiteSpace(@Nullable String value) {
        if (value == null) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private StringUtils() {} // Cannot instantiate
}
