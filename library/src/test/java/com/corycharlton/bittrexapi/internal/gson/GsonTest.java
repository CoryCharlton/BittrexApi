package com.corycharlton.bittrexapi.internal.gson;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class GsonTest {

    public static class When_getGson_is_called {

        @Test
        public void it_should_return_an_instance_of_Gson() {
            assertNotNull(Gson.getGson());
        }
    }
}
