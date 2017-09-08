package com.corycharlton.bittrexapi.request;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public final class CancelOrderRequestTest {

    public static final class When_constructor_is_called {

        @Test()
        public void it_should_require_authentication() {
            assertTrue(new CancelOrderRequest(UUID.randomUUID()).requiresAuthentication());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_uuid_is_null() {
            new CancelOrderRequest(null);
        }

        @Test
        public void responseType_should_be_correct() {
            assertEquals(CancelOrderResponse.class, new CancelOrderRequest(UUID.randomUUID()).responseType());
        }

        @Test
        public void url_should_be_correct() {
            assertEquals(Url.CANCELORDER, new CancelOrderRequest(UUID.randomUUID()).url());
        }
    }
}
