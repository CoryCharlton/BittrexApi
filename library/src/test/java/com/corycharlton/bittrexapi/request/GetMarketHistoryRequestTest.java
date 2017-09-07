package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetMarketHistoryRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_not_require_authentication() {
            assertFalse(new GetMarketHistoryRequest("BTC-LTC").requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty()  {
            new GetMarketHistoryRequest(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() {
            new GetMarketHistoryRequest(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() {
            new GetMarketHistoryRequest(" \t\r\n");
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetMarketHistoryResponse.class, new GetMarketHistoryRequest("BTC-LTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETMARKETHISTORY, new GetMarketHistoryRequest("BTC-LTC").url());
        }
    }
}
