package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetOpenOrdersResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetOpenOrdersRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetOpenOrdersRequest("BTC-LTC").requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetOpenOrdersResponse.class, new GetOpenOrdersRequest().responseType());
            assertEquals(GetOpenOrdersResponse.class, new GetOpenOrdersRequest("BTC-LTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETOPENORDERS, new GetOpenOrdersRequest().url());
            assertEquals(Url.GETOPENORDERS, new GetOpenOrdersRequest("BTC-LTC").url());
        }
    }
}
