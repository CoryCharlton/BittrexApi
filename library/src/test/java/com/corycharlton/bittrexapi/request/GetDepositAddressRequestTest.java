package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetDepositAddressResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public final class GetDepositAddressRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetDepositAddressRequest("BTC").requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_empty()  {
            new GetDepositAddressRequest(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_null() {
            new GetDepositAddressRequest(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_whitespace() {
            new GetDepositAddressRequest(" \t\r\n");
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetDepositAddressResponse.class, new GetDepositAddressRequest("BTC").responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETDEPOSITADDRESS, new GetDepositAddressRequest("BTC").url());
        }
    }
}
