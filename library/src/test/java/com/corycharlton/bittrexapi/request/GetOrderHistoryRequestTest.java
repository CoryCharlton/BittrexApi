package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetOrderHistoryResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetOrderHistoryRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetOrderHistoryRequest().requiresAuthentication());
            assertTrue(new GetOrderHistoryRequest("BTC-LTC").requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetOrderHistoryResponse.class, new GetOrderHistoryRequest().responseType());
            assertEquals(GetOrderHistoryResponse.class, new GetOrderHistoryRequest("BTC-LTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETORDERHISTORY, new GetOrderHistoryRequest().url());
            assertEquals(Url.GETORDERHISTORY, new GetOrderHistoryRequest("BTC-LTC").url());
        }
    }
}
