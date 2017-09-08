package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Enclosed.class)
public final class GetOrderBookRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_not_require_authentication() {
            assertFalse(new GetOrderBookRequest("BTC-LTC").requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty()  {
            new GetOrderBookRequest(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() {
            new GetOrderBookRequest(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() {
            new GetOrderBookRequest(" \t\r\n");
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetOrderBookResponse.class, new GetOrderBookRequest("BTC-LTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETORDERBOOK, new GetOrderBookRequest("BTC-LTC").url());
        }
    }
}
