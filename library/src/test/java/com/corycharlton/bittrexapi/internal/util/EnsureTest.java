package com.corycharlton.bittrexapi.internal.util;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class EnsureTest {
    private static final String name = "name";
    private static final Object valueObject = new Object();
    private static final String valueString = "I'm a non-empty string!";

    public static class When_isNotNull_isCalled {
        @Test
        public void it_should_not_throw_exception_if_value_is_not_null() {
            Ensure.isNotNull(name, valueObject);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_name_is_null() {
            Ensure.isNotNull(null, valueObject);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_value_is_null() {
            Ensure.isNotNull(name, null);
        }
    }

    public static class When_isNotNullOrWhitespace_isCalled {
        @Test
        public void it_should_not_throw_exception_if_value_is_not_null_whitespace_or_empty() {
            Ensure.isNotNullOrWhitespace(name, valueString);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_name_is_null() {
            Ensure.isNotNullOrWhitespace(null, valueString);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_value_is_empty() {
            Ensure.isNotNullOrWhitespace(name, "");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_value_is_null() {
            Ensure.isNotNullOrWhitespace(name, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_value_is_whitespace() {
            Ensure.isNotNullOrWhitespace(name, "\t\r\n");
        }
    }

    public static class When_isValidState_isCalled {
        @Test
        public void it_should_not_throw_exception_if_value_is_true() {
            Ensure.isValidState(name, true, valueString);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_name_is_null() {
            Ensure.isValidState(null, true, valueString);
        }

        @Test(expected = IllegalStateException.class)
        public void it_should_throw_exception_if_value_is_false() {
            Ensure.isValidState(name, false, valueString);
        }
    }
}
