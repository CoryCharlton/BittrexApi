package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetCurrenciesRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_not_require_authentication() {
            assertFalse(new GetCurrenciesRequest().requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetCurrenciesResponse.class, new GetCurrenciesRequest().responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETCURRENCIES, new GetCurrenciesRequest().url());
        }
    }
}
