package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetDepositAddressResponse;
import com.corycharlton.bittrexapi.response.GetDepositHistoryResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetDepositHistoryRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetDepositHistoryRequest().requiresAuthentication());
            assertTrue(new GetDepositHistoryRequest("BTC").requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetDepositHistoryResponse.class, new GetDepositHistoryRequest().responseType());
            assertEquals(GetDepositHistoryResponse.class, new GetDepositHistoryRequest("BTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETDEPOSITHISTORY, new GetDepositHistoryRequest().url());
            assertEquals(Url.GETDEPOSITHISTORY, new GetDepositHistoryRequest("BTC").url());
        }
    }
}
