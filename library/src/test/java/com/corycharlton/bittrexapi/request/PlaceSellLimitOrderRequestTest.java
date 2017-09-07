package com.corycharlton.bittrexapi.request;


import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.PlaceSellLimitOrderResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class PlaceSellLimitOrderRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new PlaceSellLimitOrderRequest("BTC-LTC", 1.00000000, 0.00000123).requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() {
            new PlaceSellLimitOrderRequest(StringUtils.EMPTY, 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() {
            new PlaceSellLimitOrderRequest(null, 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() {
            new PlaceSellLimitOrderRequest(" \t\r\n", 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_quantity_is_less_than_or_equal_to_zero() {
            new PlaceSellLimitOrderRequest("BTC-LTC", 0.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_rate_is_less_than_or_equal_to_zero() {
            new PlaceSellLimitOrderRequest("BTC-LTC", 1.00000000, 0.00000000);
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(PlaceSellLimitOrderResponse.class, new PlaceSellLimitOrderRequest("BTC-LTC", 1.00000000, 0.00000123).responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.PLACESELLLIMITORDER, new PlaceSellLimitOrderRequest("BTC-LTC", 1.00000000, 0.00000123).url());
        }
    }
}
