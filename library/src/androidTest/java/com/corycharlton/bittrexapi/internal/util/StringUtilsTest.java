package com.corycharlton.bittrexapi.internal.util;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class StringUtilsTest {

    public static class When_isNullOrWhiteSpace_is_called {
        @Test
        public void it_should_return_false_if_value_is_not_null_whitespace_or_empty() {
            Assert.assertFalse(StringUtils.isNullOrWhiteSpace("A string"));
        }

        @Test
        public void it_should_return_true_if_value_is_empty() {
            Assert.assertTrue(StringUtils.isNullOrWhiteSpace(""));
        }

        @Test
        public void it_should_return_true_if_value_is_null() {
            Assert.assertTrue(StringUtils.isNullOrWhiteSpace(null));
        }

        @Test
        public void it_should_return_true_if_value_is_whitespace() {
            Assert.assertTrue(StringUtils.isNullOrWhiteSpace(" \t\r\n"));
        }
    }
}
