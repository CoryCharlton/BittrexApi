package com.corycharlton.bittrexapi.internal.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

// Ported over from https://github.com/CoryCharlton/CCSWE.Core
@SuppressWarnings({"WeakerAccess", "SameParameterValue"})
public final class Ensure {

    private Ensure() {} // Cannot instantiate

    private static RuntimeException getException(@NonNull String message, @NonNull ExceptionType exceptionType) {
        switch (exceptionType) {
            case IllegalArgumentException:
                return new IllegalArgumentException(message);
            case IllegalStateException:
                return new IllegalStateException(message);
            default:
                throw new IllegalArgumentException("The value passed for 'exceptionType' is not valid.");
        }
    }

    public static <T> void isNotNull(@NonNull String name, @Nullable T value) {
        isNotNull(name, value, null);
    }

    public static <T> void isNotNull(@NonNull String name, @Nullable T value, @Nullable String message) {
        isValid(name, value != null, message, ExceptionType.IllegalArgumentException);
    }

    public static void isNotNullOrWhitespace(@NonNull String name, @Nullable String value) {
        isNotNullOrWhitespace(name, value, null);
    }

    public static void isNotNullOrWhitespace(@NonNull String name, @Nullable String value, @Nullable String message) {
        isValid(name, !StringUtils.isNullOrWhiteSpace(value), message, ExceptionType.IllegalArgumentException);
    }

    public static void isTrue(@NonNull String name, boolean expression) {
        isTrue(name, expression, null);
    }

    public static void isTrue(@NonNull String name, boolean expression, @Nullable String message) {
        isValid(name, expression, message, ExceptionType.IllegalArgumentException);
    }

    private static void isValid(@NonNull String name, boolean expression, @Nullable String message, @NonNull ExceptionType exceptionType) {
        if (StringUtils.isNullOrWhiteSpace(name)) {
            throw new IllegalArgumentException(StringUtils.isNullOrWhiteSpace(message) ? "The value passed for 'name' is not valid." : message);
        }

        if (expression) {
            return;
        }

        throw getException(StringUtils.isNullOrWhiteSpace(message) ? "The value passed for '" + name + "' is not valid." : message, exceptionType);
    }

    public static void isValidState(@NonNull String name, boolean expression, @Nullable String message) {
        isValid(name, expression, message, ExceptionType.IllegalStateException);
    }

    private enum ExceptionType {
        IllegalArgumentException,
        IllegalStateException
    }
}
