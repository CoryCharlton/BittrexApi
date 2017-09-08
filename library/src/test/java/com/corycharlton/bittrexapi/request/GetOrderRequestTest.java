package com.corycharlton.bittrexapi.request;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;
import com.corycharlton.bittrexapi.response.GetOrderResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public final class GetOrderRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new GetOrderRequest(UUID.randomUUID()).requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_uuid_is_null() {
            new GetOrderRequest(null);
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(GetOrderResponse.class, new GetOrderRequest(UUID.randomUUID()).responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.GETORDER, new GetOrderRequest(UUID.randomUUID()).url());
        }
    }
}
