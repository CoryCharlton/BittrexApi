package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.WithdrawResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class WithdrawRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb").requiresAuthentication());
            assertTrue(new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb", "paymentId").requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_address_is_empty() {
            new WithdrawRequest("BTC", 1.00000000, StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_address_is_null() {
            new WithdrawRequest("BTC", 1.00000000, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_address_is_whitespace() {
            new WithdrawRequest("BTC", 1.00000000, " \t\r\n");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_empty() {
            new WithdrawRequest(StringUtils.EMPTY, 1.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_null() {
            new WithdrawRequest(null, 1.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_whitespace() {
            new WithdrawRequest(" \t\r\n", 1.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_quantity_is_less_than_or_equal_to_zero() {
            new WithdrawRequest("BTC-LTC", 0.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(WithdrawResponse.class, new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb").responseType());
            assertEquals(WithdrawResponse.class, new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb", "paymentId").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.WITHDRAW, new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb").url());
            assertEquals(Url.WITHDRAW, new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb", "paymentId").url());
        }
    }
}
