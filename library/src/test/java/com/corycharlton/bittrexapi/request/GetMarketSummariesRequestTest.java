package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(Enclosed.class)
public final class GetMarketSummariesRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_not_require_authentication() {
            assertFalse(new GetMarketSummariesRequest().requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetMarketSummariesResponse.class, new GetMarketSummariesRequest().responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETMARKETSUMMARIES, new GetMarketSummariesRequest().url());
        }
    }
}
