package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.data.Balance;
import com.corycharlton.bittrexapi.data.Currency;
import com.corycharlton.bittrexapi.data.Deposit;
import com.corycharlton.bittrexapi.data.DepositAddress;
import com.corycharlton.bittrexapi.data.MarketHistory;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class BittrexApiClientTest {

    private static final String key = "12345";
    private static final String secret = "54321";

    private static void assertDateParsed(Date date) {
        // TODO: Is this enough to verify data parsed?
        assertNotNull(date);
    }

    private static BittrexApiClient getClient() {
        return new BittrexApiClient.Builder(key, secret).downloader(new MockDownloader()).build();
    }

    public static class When_getBalance_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetBalanceResponse response = getClient().getBalance("BTC");
            final Balance result = response.result();

            assertNotNull(response);
            assertNotNull(result);
            assertTrue(response.success());
            assertEquals("BTC", result.currency());
            assertEquals(0.0000000119203, result.available(), 0.00000001);
            assertEquals(0.0000000119203, result.balance(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS", result.cryptoAddress());
            assertEquals(0.00000000, result.pending(), 0.00000001);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_empty() throws IOException {
            getClient().getBalance(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_null() throws IOException {
            getClient().getBalance(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_whitespace() throws IOException {
            getClient().getBalance(" \t\r\n");
        }
    }

    public static class When_getBalances_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetBalancesResponse response = getClient().getBalances();
            final ArrayList<Balance> result = response.result();

            assertNotNull(response);
            assertNotNull(result);
            assertTrue(response.success());
            assertEquals(2, result.size());

            assertEquals("BTC", result.get(0).currency());
            assertEquals(0.0000000119203, result.get(0).available(), 0.00000001);
            assertEquals(0.0000000119203, result.get(0).balance(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS-BTC", result.get(0).cryptoAddress());
            assertEquals(0.00000000, result.get(0).pending(), 0.00000001);

            assertEquals("FLO", result.get(1).currency());
            assertEquals(1234.00000000, result.get(1).available(), 0.00000001);
            assertEquals(4321.00000000, result.get(1).balance(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS-FLO", result.get(1).cryptoAddress());
            assertEquals(3087.00000000, result.get(1).pending(), 0.00000001);
        }
    }

    public static class When_getCurrencies_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetCurrenciesResponse response = getClient().getCurrencies();
            final ArrayList<Currency> result = response.result();

            assertNotNull(response);
            assertNotNull(result);
            assertTrue(response.success());
            assertEquals(2, result.size());

            assertEquals("1N52wHoVR79PMDishab2XmRHsbekCdGquK", result.get(0).baseAddress());
            assertEquals("BITCOIN", result.get(0).coinType());
            assertEquals("BTC", result.get(0).currency());
            assertEquals("Bitcoin", result.get(0).currencyLong());
            assertEquals(true, result.get(0).isActive());
            assertEquals(2.00000000, result.get(0).minConfirmation(), 0.00000001);
            assertEquals(0.0000000100000, result.get(0).txFee(), 0.00000001);
            assertEquals(null, result.get(0).notice());

            assertEquals("LhyLNfBkoKshT7R8Pce6vkB9T2cP2o84hx", result.get(1).baseAddress());
            assertEquals("BITCOIN", result.get(1).coinType());
            assertEquals("LTC", result.get(1).currency());
            assertEquals("Litecoin", result.get(1).currencyLong());
            assertEquals(true, result.get(1).isActive());
            assertEquals(6.00000000, result.get(1).minConfirmation(), 0.00000001);
            assertEquals(0.00200000, result.get(1).txFee(), 0.00000001);
            assertEquals(null, result.get(1).notice());
        }
    }

    public static class When_getDepositAddress_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetDepositAddressResponse response = getClient().getDepositAddress("BTC");
            final DepositAddress result = response.result();

            assertNotNull(response);
            assertNotNull(result);
            assertTrue(response.success());
            assertEquals("3232ln32lknrldslkflkdsan324", result.address());
            assertEquals("BTC", result.currency());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_empty() throws IOException {
            getClient().getDepositAddress(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_null() throws IOException {
            getClient().getDepositAddress(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_whitespace() throws IOException {
            getClient().getDepositAddress(" \t\r\n");
        }
    }

    public static class When_getDepositHistory_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetDepositHistoryResponse response = getClient().getDepositHistory();
            final ArrayList<Deposit> result = response.result();

            assertNotNull(response);
            assertNotNull(result);
            assertTrue(response.success());
            assertEquals(1, result.size());

            assertEquals(0.50000000, result.get(0).amount(), 0.00000001);
            assertEquals(3, result.get(0).confirmations(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS", result.get(0).cryptoAddress());
            assertEquals("BTC", result.get(0).currency());
            assertEquals(12345678, result.get(0).id());
            assertDateParsed(result.get(0).lastUpdated());
            assertEquals("TX-ID", result.get(0).txId());
        }
    }

    public static class When_getMarketHistory_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetMarketHistoryResponse response = getClient().getMarketHistory("BTC-LTC");
            final ArrayList<MarketHistory> result = response.result();

            assertNotNull(response);
            assertNotNull(result);
            assertTrue(response.success());
            assertEquals(2, result.size());

            assertEquals("PARTIAL_FILL", result.get(0).fillType());
            assertEquals(68484991, result.get(0).id());
            assertEquals("SELL", result.get(0).orderType());
            assertEquals(0.01708541, result.get(0).price(), 0.00000001);
            assertEquals(3.18417341, result.get(0).quantity(), 0.00000001);
            assertDateParsed(result.get(0).timeStamp());
            assertEquals(0.05440290, result.get(0).total(), 0.00000001);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() throws IOException {
            getClient().getMarketHistory(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() throws IOException {
            getClient().getMarketHistory(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() throws IOException {
            getClient().getMarketHistory(" \t\r\n");
        }
    }

    static final class MockDownloader implements Downloader {

        @Override
        public Response execute(@NonNull Request request) throws IOException {
            String url = request.url();

            if (url.contains("?")) {
                url = url.substring(0, url.indexOf("?"));
            }

            switch (url) {
                case BittrexApiClient.URL_GETBALANCE:
                    return handleGetBalance();
                case BittrexApiClient.URL_GETBALANCES:
                    return handleGetBalances();
                case BittrexApiClient.URL_GETCURRENCIES:
                    return handleGetCurrencies();
                case BittrexApiClient.URL_GETDEPOSITADDRESS:
                    return handleGetDepositAddress();
                case BittrexApiClient.URL_GETDEPOSITHISTORY:
                    return handleGetDepositHistory();
                case BittrexApiClient.URL_GETMARKETHISTORY:
                    return handleGetMarketHistory();
                default:
                    throw new IllegalArgumentException("No mock configured for " + url);
            }
        }

        @NonNull
        private Response handleGetBalance() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"Currency\": \"BTC\",\n" +
                    "\t\t\"Balance\": 0.0000000119203,\n" +
                    "\t\t\"Available\": 0.0000000119203,\n" +
                    "\t\t\"Pending\": 0.00000000,\n" +
                    "\t\t\"CryptoAddress\": \"CRYPTO-ADDRESS\"\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetBalances() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"Currency\": \"BTC\",\n" +
                    "\t\t\t\"Balance\": 0.0000000119203,\n" +
                    "\t\t\t\"Available\": 0.0000000119203,\n" +
                    "\t\t\t\"Pending\": 0.00000000,\n" +
                    "\t\t\t\"CryptoAddress\": \"CRYPTO-ADDRESS-BTC\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"Currency\": \"FLO\",\n" +
                    "\t\t\t\"Balance\": 4321.00000000,\n" +
                    "\t\t\t\"Available\": 1234.00000000,\n" +
                    "\t\t\t\"Pending\": 3087.00000000,\n" +
                    "\t\t\t\"CryptoAddress\": \"CRYPTO-ADDRESS-FLO\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetCurrencies() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"Currency\": \"BTC\",\n" +
                    "\t\t\t\"CurrencyLong\": \"Bitcoin\",\n" +
                    "\t\t\t\"MinConfirmation\": 2,\n" +
                    "\t\t\t\"TxFee\": 0.0000000100000,\n" +
                    "\t\t\t\"IsActive\": true,\n" +
                    "\t\t\t\"CoinType\": \"BITCOIN\",\n" +
                    "\t\t\t\"BaseAddress\": \"1N52wHoVR79PMDishab2XmRHsbekCdGquK\",\n" +
                    "\t\t\t\"Notice\": null\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"Currency\": \"LTC\",\n" +
                    "\t\t\t\"CurrencyLong\": \"Litecoin\",\n" +
                    "\t\t\t\"MinConfirmation\": 6,\n" +
                    "\t\t\t\"TxFee\": 0.00200000,\n" +
                    "\t\t\t\"IsActive\": true,\n" +
                    "\t\t\t\"CoinType\": \"BITCOIN\",\n" +
                    "\t\t\t\"BaseAddress\": \"LhyLNfBkoKshT7R8Pce6vkB9T2cP2o84hx\",\n" +
                    "\t\t\t\"Notice\": null\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetDepositAddress() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"Currency\": \"BTC\",\n" +
                    "\t\t\"Address\": \"3232ln32lknrldslkflkdsan324\"\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetDepositHistory() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"Id\": 12345678,\n" +
                    "\t\t\t\"Amount\": 0.50000000,\n" +
                    "\t\t\t\"Currency\": \"BTC\",\n" +
                    "\t\t\t\"Confirmations\": 3,\n" +
                    "\t\t\t\"LastUpdated\": \"2017-08-26T22:59:17.267\",\n" +
                    "\t\t\t\"TxId\": \"TX-ID\",\n" +
                    "\t\t\t\"CryptoAddress\": \"CRYPTO-ADDRESS\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetMarketHistory() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"Id\": 68484991,\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T00:01:04.91\",\n" +
                    "\t\t\t\"Quantity\": 3.18417341,\n" +
                    "\t\t\t\"Price\": 0.01708541,\n" +
                    "\t\t\t\"Total\": 0.05440290,\n" +
                    "\t\t\t\"FillType\": \"PARTIAL_FILL\",\n" +
                    "\t\t\t\"OrderType\": \"SELL\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"Id\": 68484990,\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T00:01:04.91\",\n" +
                    "\t\t\t\"Quantity\": 0.12137375,\n" +
                    "\t\t\t\"Price\": 0.01708543,\n" +
                    "\t\t\t\"Total\": 0.00207372,\n" +
                    "\t\t\t\"FillType\": \"FILL\",\n" +
                    "\t\t\t\"OrderType\": \"SELL\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }
    }
}
