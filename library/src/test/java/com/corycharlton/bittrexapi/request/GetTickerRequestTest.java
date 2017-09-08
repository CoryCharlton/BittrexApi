package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Enclosed.class)
public final class GetTickerRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_not_require_authentication() {
            assertFalse(new GetTickerRequest("BTC-LTC").requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty()  {
            new GetTickerRequest(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() {
            new GetTickerRequest(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() {
            new GetTickerRequest(" \t\r\n");
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetTickerResponse.class, new GetTickerRequest("BTC-LTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETTICKER, new GetTickerRequest("BTC-LTC").url());
        }
    }
}
