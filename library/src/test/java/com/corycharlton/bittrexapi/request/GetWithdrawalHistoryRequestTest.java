package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetDepositHistoryResponse;
import com.corycharlton.bittrexapi.response.GetWithdrawalHistoryResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetWithdrawalHistoryRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetWithdrawalHistoryRequest().requiresAuthentication());
            assertTrue(new GetWithdrawalHistoryRequest("BTC").requiresAuthentication());
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetWithdrawalHistoryResponse.class, new GetWithdrawalHistoryRequest().responseType());
            assertEquals(GetWithdrawalHistoryResponse.class, new GetWithdrawalHistoryRequest("BTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETWITHDRAWALHISTORY, new GetWithdrawalHistoryRequest().url());
            assertEquals(Url.GETWITHDRAWALHISTORY, new GetWithdrawalHistoryRequest("BTC").url());
        }
    }
}
