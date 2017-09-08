package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetBalancesRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetBalancesRequest().requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetBalancesResponse.class, new GetBalancesRequest().responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETBALANCES, new GetBalancesRequest().url());
        }
    }
}
