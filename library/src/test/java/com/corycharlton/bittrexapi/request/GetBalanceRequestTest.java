package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetBalanceRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetBalanceRequest("BTC").requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_empty()  {
            new GetBalanceRequest(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_null() {
            new GetBalanceRequest(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_whitespace() {
            new GetBalanceRequest(" \t\r\n");
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetBalanceResponse.class, new GetBalanceRequest("BTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETBALANCE, new GetBalanceRequest("BTC").url());
        }
    }
}
