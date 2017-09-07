package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.util.StringUtils;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.concurrent.Executor;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class BittrexApiClient_BuilderTest {
    // TODO: Not sure I like the name. Would be nice if JUnit supported more than
    // only level of nested classed. It looks like JUnit5 supports this with the
    // @Nested attribute

    private static final String _key = "12345";
    private static final String _secret = "54321";

    public static class When_build_is_called {
        private BittrexApiClient.Builder buildValidBuilder() {
            return new BittrexApiClient.Builder()
                    .key(_key)
                    .secret(_secret);
        }
        @Test()
        public void it_should_not_throw_exception_if_required_parameters_are_set() {
            assertNotNull(buildValidBuilder().build());
        }
    }

    public static class When_downloader_is_called {
        @Test()
        public void it_should_not_throw_exception_if_downloader_is_valid() {
            assertNotNull(new BittrexApiClient.Builder().downloader(new UrlConnectionDownloader()));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_downloader_is_null() {
            new BittrexApiClient.Builder().downloader(null);
        }
    }

    public static class When_executor_is_called {
        @Test()
        public void it_should_not_throw_exception_if_executor_is_valid() {
            assertNotNull(new BittrexApiClient.Builder().executor(new Executor() {
                @Override
                public void execute(@NonNull Runnable runnable) {

                }
            }));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_executor_is_null() {
            new BittrexApiClient.Builder().executor(null);
        }
    }

    public static class When_key_is_called {
        @Test()
        public void it_should_not_throw_exception_if_key_is_valid() {
            assertNotNull(new BittrexApiClient.Builder().key(_key));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_key_is_empty() {
            assertNotNull(new BittrexApiClient.Builder().key(StringUtils.EMPTY));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_key_is_null() {
            assertNotNull(new BittrexApiClient.Builder().key(null));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_key_is_whitespace() {
            assertNotNull(new BittrexApiClient.Builder().key(" \r\n\t"));
        }
    }

    public static class When_secret_is_called {
        @Test()
        public void it_should_not_throw_exception_if_secret_is_valid() {
            assertNotNull(new BittrexApiClient.Builder().secret(_secret));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_secret_is_empty() {
            assertNotNull(new BittrexApiClient.Builder().secret(StringUtils.EMPTY));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_secret_is_null() {
            assertNotNull(new BittrexApiClient.Builder().secret(null));
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_secret_is_whitespace() {
            assertNotNull(new BittrexApiClient.Builder().secret(" \r\n\t"));
        }
    }
}
