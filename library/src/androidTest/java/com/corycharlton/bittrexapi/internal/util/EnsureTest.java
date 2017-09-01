package com.corycharlton.bittrexapi.internal.util;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class EnsureTest {
    private static final String name = "name";
    private static final Object value = new Object();

    public static class When_isNotNull_isCalled {
        @Test(expected = IllegalArgumentException.class)
        public void it_should_not_throw_exception_if_name_is_null() {
            Ensure.isNotNull(null, value);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_name_is_null() {
            Ensure.isNotNull(null, value);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_value_is_null() {
            Ensure.isNotNull(name, null);
        }
    }
}
