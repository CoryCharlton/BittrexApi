package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.model.Balance;
import com.corycharlton.bittrexapi.model.Currency;
import com.corycharlton.bittrexapi.model.Deposit;
import com.corycharlton.bittrexapi.model.DepositAddress;
import com.corycharlton.bittrexapi.model.Market;
import com.corycharlton.bittrexapi.model.MarketHistory;
import com.corycharlton.bittrexapi.model.MarketSummary;
import com.corycharlton.bittrexapi.model.OpenOrder;
import com.corycharlton.bittrexapi.model.Order;
import com.corycharlton.bittrexapi.model.OrderBook;
import com.corycharlton.bittrexapi.model.OrderBookEntry;
import com.corycharlton.bittrexapi.model.OrderHistory;
import com.corycharlton.bittrexapi.model.Ticker;
import com.corycharlton.bittrexapi.model.Uuid;
import com.corycharlton.bittrexapi.model.Withdrawal;
import com.corycharlton.bittrexapi.request.CancelOrderRequest;
import com.corycharlton.bittrexapi.request.GetBalanceRequest;
import com.corycharlton.bittrexapi.request.GetBalancesRequest;
import com.corycharlton.bittrexapi.request.GetCurrenciesRequest;
import com.corycharlton.bittrexapi.request.GetDepositAddressRequest;
import com.corycharlton.bittrexapi.request.GetDepositHistoryRequest;
import com.corycharlton.bittrexapi.request.GetMarketHistoryRequest;
import com.corycharlton.bittrexapi.request.GetMarketSummariesRequest;
import com.corycharlton.bittrexapi.request.GetMarketSummaryRequest;
import com.corycharlton.bittrexapi.request.GetMarketsRequest;
import com.corycharlton.bittrexapi.request.GetOpenOrdersRequest;
import com.corycharlton.bittrexapi.request.GetOrderBookRequest;
import com.corycharlton.bittrexapi.request.GetOrderHistoryRequest;
import com.corycharlton.bittrexapi.request.GetOrderRequest;
import com.corycharlton.bittrexapi.request.GetTickerRequest;
import com.corycharlton.bittrexapi.request.GetWithdrawalHistoryRequest;
import com.corycharlton.bittrexapi.request.PlaceBuyLimitOrderRequest;
import com.corycharlton.bittrexapi.request.PlaceSellLimitOrderRequest;
import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.request.WithdrawRequest;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetDepositAddressResponse;
import com.corycharlton.bittrexapi.response.GetDepositHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOpenOrdersResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetOrderHistoryResponse;
import com.corycharlton.bittrexapi.response.GetOrderResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;
import com.corycharlton.bittrexapi.response.GetWithdrawalHistoryResponse;
import com.corycharlton.bittrexapi.response.PlaceBuyLimitOrderResponse;
import com.corycharlton.bittrexapi.response.PlaceSellLimitOrderResponse;
import com.corycharlton.bittrexapi.response.Response;
import com.corycharlton.bittrexapi.response.WithdrawResponse;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
@SuppressWarnings("ConstantConditions")
public class BittrexApiClientTest {

    private static final String key = "c6a6881faa1160c8869f0f85431e2acc";
    private static final String secret = "259cf3b30a4c82715c6d58a951617916";
    
    private static void assertDateParsed(Date date) {
        // TODO: Is this enough to verify data parsed?
        assertNotNull(date);
    }

    @NonNull
    private static BittrexApiClient getClient() {
        return new BittrexApiClient.Builder()
                .downloader(new MockDownloader())
                .key(key)
                .secret(secret)
                .build();
    }

    public static class When_execute_is_called {

        @Test()
        public void it_should_execute_CancelOrderRequest() throws IOException {
            final CancelOrderResponse response = getClient().execute(new CancelOrderRequest(UUID.randomUUID()));

            assertNotNull(response);
            assertTrue(response.success());
        }

        @Test()
        public void it_should_execute_GetBalanceRequest() throws IOException {
            final GetBalanceResponse response = getClient().execute(new GetBalanceRequest("BTC"));

            assertNotNull(response);
            assertTrue(response.success());

            final Balance result = response.result();

            assertNotNull(result);
            assertEquals("BTC", result.currency());
            assertEquals(0.0000000119203, result.available(), 0.00000001);
            assertEquals(0.0000000119203, result.total(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS", result.cryptoAddress());
            assertEquals(0.00000000, result.pending(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetBalancesRequest() throws IOException {
            final GetBalancesResponse response = getClient().execute(new GetBalancesRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<Balance> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final Balance item = result.get(0);

            assertNotNull(item);
            assertEquals("BTC", item.currency());
            assertEquals(0.0000000119203, item.available(), 0.00000001);
            assertEquals(0.0000000119203, item.total(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS-BTC", item.cryptoAddress());
            assertEquals(0.00000000, item.pending(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetCurrenciesRequest() throws IOException {
            final GetCurrenciesResponse response = getClient().execute(new GetCurrenciesRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<Currency> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final Currency item = result.get(0);

            assertNotNull(item);
            assertEquals("BASE-ADDRESS", item.baseAddress());
            assertEquals("BITCOIN", item.coinType());
            assertEquals("BTC", item.currency());
            assertEquals("Bitcoin", item.currencyLong());
            assertEquals(true, item.isActive());
            assertEquals(2.00000000, item.minConfirmation(), 0.00000001);
            assertEquals(0.0000000100000, item.txFee(), 0.00000001);
            assertEquals(null, item.notice());
        }

        @Test()
        public void it_should_execute_GetDepositAddressRequest() throws IOException {
            final GetDepositAddressResponse response = getClient().execute(new GetDepositAddressRequest("BTC"));

            assertNotNull(response);
            assertTrue(response.success());

            final DepositAddress result = response.result();

            assertNotNull(result);
            assertEquals("DEPOSIT-ADDRESS", result.address());
            assertEquals("BTC", result.currency());
        }

        @Test()
        public void it_should_execute_GetDepositHistoryRequest() throws IOException {
            final GetDepositHistoryResponse response = getClient().execute(new GetDepositHistoryRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<Deposit> result = response.result();

            assertNotNull(result);
            assertEquals(1, result.size());

            final Deposit item = result.get(0);

            assertNotNull(item);
            assertEquals(0.50000000, item.amount(), 0.00000001);
            assertEquals(3, item.confirmations(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS", item.cryptoAddress());
            assertEquals("BTC", item.currency());
            assertEquals(12345678, item.id());
            assertDateParsed(item.lastUpdated());
            assertEquals("TX-ID", item.txId());
        }

        @Test()
        public void it_should_execute_GetMarketHistoryRequest() throws IOException {
            final GetMarketHistoryResponse response = getClient().execute(new GetMarketHistoryRequest("BTC-LTC"));

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<MarketHistory> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final MarketHistory item = result.get(0);

            assertNotNull(item);
            assertEquals("PARTIAL_FILL", item.fillType());
            assertEquals(68484991, item.id());
            assertEquals("SELL", item.orderType());
            assertEquals(0.01708541, item.price(), 0.00000001);
            assertEquals(3.18417341, item.quantity(), 0.00000001);
            assertDateParsed(item.timeStamp());
            assertEquals(0.05440290, item.total(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetMarketsRequest() throws IOException {
            final GetMarketsResponse response = getClient().execute(new GetMarketsRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<Market> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final Market item = result.get(0);

            assertNotNull(item);
            assertEquals("BTC", item.baseCurrency());
            assertEquals("Bitcoin", item.baseCurrencyLong());
            assertDateParsed(item.created());
            assertTrue(item.isActive());
            assertFalse(item.isSponsored());
            assertEquals("https://i.imgur.com/R29q3dD.png", item.logoUrl());
            assertEquals("LTC", item.marketCurrency());
            assertEquals("Litecoin", item.marketCurrencyLong());
            assertEquals("BTC-LTC", item.marketName());
            assertEquals(0.00000001, item.minTradeSize(), 0.00000001);
            assertNull(item.notice());
        }

        @Test()
        public void it_should_execute_GetMarketSummariesRequest() throws IOException {
            final GetMarketSummariesResponse response = getClient().execute(new GetMarketSummariesRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<MarketSummary> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final MarketSummary item = result.get(0);

            assertNotNull(item);

            assertEquals(0.00008824, item.ask(), 0.00000001);
            assertEquals(112.80166117, item.baseVolume(), 0.00000001);
            assertEquals(0.00008770, item.bid(), 0.00000001);
            assertDateParsed(item.created());
            assertEquals(0.00012950, item.high(), 0.00000001);
            assertEquals(0.00008824, item.last(), 0.00000001);
            assertEquals(0.00008551, item.low(), 0.00000001);
            assertEquals("BTC-1ST", item.marketName());
            assertEquals(86, item.openBuyOrders());
            assertEquals(5110, item.openSellOrders());
            assertEquals(0.00012950, item.prevDay(), 0.00000001);
            assertDateParsed(item.timeStamp());
            assertEquals(969357.59257975, item.volume(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetMarketSummaryRequest() throws IOException {
            final GetMarketSummaryResponse response = getClient().execute(new GetMarketSummaryRequest("BTC-LTC"));

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<MarketSummary> result = response.result();

            assertNotNull(result);
            assertEquals(1, result.size());

            final MarketSummary item = result.get(0);

            assertNotNull(item);

            assertEquals(0.01591411, item.ask(), 0.00000001);
            assertEquals(2371.85351044, item.baseVolume(), 0.00000001);
            assertEquals(0.01591002, item.bid(), 0.00000001);
            assertDateParsed(item.created());
            assertEquals(0.01753224, item.high(), 0.00000001);
            assertEquals(0.01591411, item.last(), 0.00000001);
            assertEquals(0.01532000, item.low(), 0.00000001);
            assertEquals("BTC-LTC", item.marketName());
            assertEquals(2099, item.openBuyOrders());
            assertEquals(5647, item.openSellOrders());
            assertEquals(0.01745500, item.prevDay(), 0.00000001);
            assertDateParsed(item.timeStamp());
            assertEquals(140566.93272055, item.volume(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetOpenOrdersRequest() throws IOException {
            final GetOpenOrdersResponse response = getClient().execute(new GetOpenOrdersRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<OpenOrder> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final OpenOrder item = result.get(0);

            assertNull(item.closed());
            assertFalse(item.cancelInitiated());
            assertEquals(0.00000000, item.commissionPaid(), 0.00000001);
            assertEquals("NONE", item.condition());
            assertEquals(0.00000000, item.conditionTarget(), 0.00000001);
            assertEquals("BTC-FLO", item.exchange());
            assertFalse(item.immediateOrCancel());
            assertFalse(item.isConditional());
            assertEquals(0.00010000, item.limit(), 0.00000001);
            assertDateParsed(item.opened());
            assertEquals("LIMIT_SELL", item.orderType());
            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.orderUuid().toString());
            assertEquals(0.00000000, item.price(), 0.00000001);
            assertEquals(0.00000000, item.pricePerUnit(), 0.00000001);
            assertEquals(1500.00000000, item.quantity(), 0.00000001);
            assertEquals(1500.00000000, item.quantityRemaining(), 0.00000001);
            assertNull(item.uuid());
        }

        @Test()
        public void it_should_execute_GetOrderBookRequest() throws IOException {
            final GetOrderBookResponse response = getClient().execute(new GetOrderBookRequest("BTC-LTC"));

            assertNotNull(response);
            assertTrue(response.success());

            final OrderBook result = response.result();

            assertNotNull(result);
            assertNotNull(result.buys());
            assertNotNull(result.sells());


            final OrderBookEntry buy = result.buys().get(0);

            assertNotNull(buy);

            assertEquals(7.58754865, buy.quantity(), 0.00000001);
            assertEquals(0.01604003, buy.rate(), 0.00000001);

            final OrderBookEntry sell = result.sells().get(0);

            assertNotNull(sell);

            assertEquals(0.00000075, sell.quantity(), 0.00000001);
            assertEquals(0.01613499, sell.rate(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetOrderHistoryRequest() throws IOException {
            final GetOrderHistoryResponse response = getClient().execute(new GetOrderHistoryRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<OrderHistory> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final OrderHistory item = result.get(0);

            assertNotNull(item);

            assertDateParsed(item.closed());
            assertEquals(0.00003380, item.commission(), 0.00000001);
            assertEquals("NONE", item.condition());
            assertEquals(0.00000000, item.conditionTarget(), 0.00000001);
            assertEquals("BTC-LSK", item.exchange());
            assertFalse(item.immediateOrCancel());
            assertFalse(item.isConditional());
            assertEquals(0.00005000, item.limit(), 0.00000001);
            assertEquals("LIMIT_SELL", item.orderType());
            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.orderUuid().toString());
            assertEquals(0.01352350, item.price(), 0.00000001);
            assertEquals(0.00135235, item.pricePerUnit(), 0.00000001);
            assertEquals(10.00000000, item.quantity(), 0.00000001);
            assertEquals(0.00000000, item.quantityRemaining(), 0.00000001);
            assertDateParsed(item.timeStamp());
        }

        @Test()
        public void it_should_execute_GetOrderRequest() throws IOException {
            final GetOrderResponse response = getClient().execute(new GetOrderRequest(UUID.randomUUID()));

            assertNotNull(response);
            assertTrue(response.success());

            final Order item = response.result();

            assertNotNull(item);

            assertNull(item.accountId());
            assertFalse(item.cancelInitiated());
            assertDateParsed(item.closed());
            assertEquals(0.00003525, item.commissionPaid(), 0.00000001);
            assertEquals(0.00003525, item.commissionReserved(), 0.00000001);
            assertEquals(0.00000000, item.commissionReservedRemaining(), 0.00000001);
            assertEquals("NONE", item.condition());
            assertEquals(0.00000000, item.conditionTarget(), 0.00000001);
            assertEquals("BTC-SC", item.exchange());
            assertFalse(item.immediateOrCancel());
            assertFalse(item.isConditional());
            assertFalse(item.isOpen());
            assertEquals(0.00000188, item.limit(), 0.00000001);
            assertDateParsed(item.opened());
            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.orderUuid().toString());
            assertEquals(0.01410000, item.price(), 0.00000001);
            assertEquals(0.00000188, item.pricePerUnit(), 0.00000001);
            assertEquals(7500.00000000, item.quantity(), 0.00000001);
            assertEquals(0.00000000, item.quantityRemaining(), 0.00000001);
            assertEquals(0.01410000, item.reserved(), 0.00000001);
            assertEquals(0.01410000, item.reservedRemaining(), 0.00000001);
            assertEquals("ac3714e0-8655-4207-b78f-d5bb588ac36a", item.sentinel().toString());
            assertEquals("LIMIT_BUY", item.type());
        }

        @Test()
        public void it_should_execute_GetTickerRequest() throws IOException {
            final GetTickerResponse response = getClient().execute(new GetTickerRequest("BTC-LTC"));

            assertNotNull(response);
            assertTrue(response.success());

            final Ticker item = response.result();

            assertNotNull(item);

            assertEquals(0.01614000, item.ask(), 0.00000001);
            assertEquals(0.01612000, item.bid(), 0.00000001);
            assertEquals(0.01613999, item.last(), 0.00000001);
        }

        @Test()
        public void it_should_execute_GetWithdrawalHistoryRequest() throws IOException {
            final GetWithdrawalHistoryResponse response = getClient().execute(new GetWithdrawalHistoryRequest());

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<Withdrawal> result = response.result();

            assertNotNull(result);
            assertEquals(1, result.size());

            final Withdrawal item = result.get(0);

            assertNotNull(item);

            assertEquals("WITHDRAWAL-ADDRESS", item.address());
            assertEquals(0.02122400, item.amount(), 0.00000001);
            assertTrue(item.authorized());
            assertFalse(item.canceled());
            assertEquals("BTC", item.currency());
            assertFalse(item.invalidAddress());
            assertDateParsed(item.opened());
            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.paymentUuid().toString());
            assertFalse(item.pendingPayment());
            assertEquals(0.00100000, item.txCost(), 0.00000001);
            assertEquals("TX-ID", item.txId());
        }

        @Test()
        public void it_should_execute_PlaceBuyLimitOrderRequest() throws IOException {
            final PlaceBuyLimitOrderResponse response = getClient().execute(new PlaceBuyLimitOrderRequest("BTC-LTC", 1.00000, 0.00000123));

            assertNotNull(response);
            assertTrue(response.success());

            final Uuid item = response.result();

            assertNotNull(item);

            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.uuid().toString());
        }

        @Test()
        public void it_should_execute_PlaceSellLimitOrderRequest() throws IOException {
            final PlaceSellLimitOrderResponse response = getClient().execute(new PlaceSellLimitOrderRequest("BTC-LTC", 1.00000, 0.00000123));

            assertNotNull(response);
            assertTrue(response.success());

            final Uuid item = response.result();

            assertNotNull(item);

            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.uuid().toString());
        }

        @Test()
        public void it_should_execute_WithdrawRequest() throws IOException {
            final WithdrawResponse response = getClient().execute(new WithdrawRequest("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb"));

            assertNotNull(response);
            assertTrue(response.success());

            final Uuid item = response.result();

            assertNotNull(item);

            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.uuid().toString());
        }
    }

    /* java.lang.RuntimeException: Method execute in android.os.AsyncTask not mocked. See http://g.co/androidstudio/not-mocked for details.
    // Could test it in the instrumented tests but spinning up the emulator during build is slow
    public static class When_executeAsync_is_called {

        @Test()
        public void it_should_execute_asynchronously() {
            final AtomicBoolean hasRun = new AtomicBoolean();
            final MockCallback<CancelOrderResponse> callback = new MockCallback<>();
            final BittrexApiClient client = new BittrexApiClient.Builder()
                    .downloader(new MockDownloader())
                    .executor(new Executor() {
                        @Override
                        public void execute(@NonNull Runnable runnable) {
                            runnable.run();
                            hasRun.set(true);
                        }
                    })
                    .key(key)
                    .secret(secret)
                    .build();

            client.executeAsync(new CancelOrderRequest(UUID.randomUUID()), callback);

            assertNotNull(callback.response);
            assertTrue(callback.response.success());
        }
    }
    */

    static final class MockCallback<T extends Response> implements Callback<T> {
        IOException exception;
        boolean hasRun;
        T response;

        @Override
        public void onFailure(Request<T> request, IOException e) {
            this.exception = e;
            this.hasRun = true;
        }

        @Override
        public void onResponse(Request<T> request, T response) {
            this.hasRun = true;
            this.response = response;
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
                case Url.CANCELORDER:
                    return handleCancelOrder();
                case Url.GETBALANCE:
                    return handleGetBalance();
                case Url.GETBALANCES:
                    return handleGetBalances();
                case Url.GETCURRENCIES:
                    return handleGetCurrencies();
                case Url.GETDEPOSITADDRESS:
                    return handleGetDepositAddress();
                case Url.GETDEPOSITHISTORY:
                    return handleGetDepositHistory();
                case Url.GETMARKETHISTORY:
                    return handleGetMarketHistory();
                case Url.GETMARKETS:
                    return handleGetMarkets();
                case Url.GETMARKETSUMMARIES:
                    return handleGetMarketSummaries();
                case Url.GETMARKETSUMMARY:
                    return handleGetMarketSummary();
                case Url.GETOPENORDERS:
                    return handleGetOpenOrders();
                case Url.GETORDER:
                    return handleGetOrder();
                case Url.GETORDERBOOK:
                    return handleGetOrderBook();
                case Url.GETORDERHISTORY:
                    return handleGetOrderHistory();
                case Url.GETTICKER:
                    return handleGetTicker();
                case Url.GETWITHDRAWALHISTORY:
                    return handleGetWithdrawalHistory();
                case Url.PLACEBUYLIMITORDER:
                    return handlePlaceBuyLimitOrder();
                case Url.PLACESELLLIMITORDER:
                    return handlePlaceSellLimitOrder();
                case Url.WITHDRAW:
                    return handleWithdraw();
                default:
                    throw new IllegalArgumentException("No mock configured for " + url);
            }
        }

        @NonNull
        private Response handleCancelOrder() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"uuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\"\n" +
                    "\t}\n" +
                    "}\n", 200);
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
                    "\t\t\t\"BaseAddress\": \"BASE-ADDRESS\",\n" +
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
                    "\t\t\"Address\": \"DEPOSIT-ADDRESS\"\n" +
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

        @NonNull
        private Response handleGetMarkets() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"MarketCurrency\": \"LTC\",\n" +
                    "\t\t\t\"BaseCurrency\": \"BTC\",\n" +
                    "\t\t\t\"MarketCurrencyLong\": \"Litecoin\",\n" +
                    "\t\t\t\"BaseCurrencyLong\": \"Bitcoin\",\n" +
                    "\t\t\t\"MinTradeSize\": 0.00000001,\n" +
                    "\t\t\t\"MarketName\": \"BTC-LTC\",\n" +
                    "\t\t\t\"IsActive\": true,\n" +
                    "\t\t\t\"Created\": \"2014-02-13T00:00:00\",\n" +
                    "\t\t\t\"Notice\": null,\n" +
                    "\t\t\t\"IsSponsored\": null,\n" +
                    "\t\t\t\"LogoUrl\": \"https://i.imgur.com/R29q3dD.png\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"MarketCurrency\": \"DOGE\",\n" +
                    "\t\t\t\"BaseCurrency\": \"BTC\",\n" +
                    "\t\t\t\"MarketCurrencyLong\": \"Dogecoin\",\n" +
                    "\t\t\t\"BaseCurrencyLong\": \"Bitcoin\",\n" +
                    "\t\t\t\"MinTradeSize\": 0.00000001,\n" +
                    "\t\t\t\"MarketName\": \"BTC-DOGE\",\n" +
                    "\t\t\t\"IsActive\": true,\n" +
                    "\t\t\t\"Created\": \"2014-02-13T00:00:00\",\n" +
                    "\t\t\t\"Notice\": null,\n" +
                    "\t\t\t\"IsSponsored\": null,\n" +
                    "\t\t\t\"LogoUrl\": \"https://i.imgur.com/e1RS4Hn.png\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetMarketSummaries() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"MarketName\": \"BTC-1ST\",\n" +
                    "\t\t\t\"High\": 0.00012950,\n" +
                    "\t\t\t\"Low\": 0.00008551,\n" +
                    "\t\t\t\"Volume\": 969357.59257975,\n" +
                    "\t\t\t\"Last\": 0.00008824,\n" +
                    "\t\t\t\"BaseVolume\": 112.80166117,\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T08:36:13.543\",\n" +
                    "\t\t\t\"Bid\": 0.00008770,\n" +
                    "\t\t\t\"Ask\": 0.00008824,\n" +
                    "\t\t\t\"OpenBuyOrders\": 86,\n" +
                    "\t\t\t\"OpenSellOrders\": 5110,\n" +
                    "\t\t\t\"PrevDay\": 0.00012950,\n" +
                    "\t\t\t\"Created\": \"2017-06-06T01:22:35.727\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"MarketName\": \"BTC-2GIVE\",\n" +
                    "\t\t\t\"High\": 0.00000135,\n" +
                    "\t\t\t\"Low\": 0.00000122,\n" +
                    "\t\t\t\"Volume\": 7241763.57177872,\n" +
                    "\t\t\t\"Last\": 0.00000122,\n" +
                    "\t\t\t\"BaseVolume\": 9.30079327,\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T08:36:03.23\",\n" +
                    "\t\t\t\"Bid\": 0.00000122,\n" +
                    "\t\t\t\"Ask\": 0.00000123,\n" +
                    "\t\t\t\"OpenBuyOrders\": 357,\n" +
                    "\t\t\t\"OpenSellOrders\": 2071,\n" +
                    "\t\t\t\"PrevDay\": 0.00000135,\n" +
                    "\t\t\t\"Created\": \"2016-05-16T06:44:15.287\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetMarketSummary() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"MarketName\": \"BTC-LTC\",\n" +
                    "\t\t\t\"High\": 0.01753224,\n" +
                    "\t\t\t\"Low\": 0.01532000,\n" +
                    "\t\t\t\"Volume\": 140566.93272055,\n" +
                    "\t\t\t\"Last\": 0.01591411,\n" +
                    "\t\t\t\"BaseVolume\": 2371.85351044,\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T08:45:22.28\",\n" +
                    "\t\t\t\"Bid\": 0.01591002,\n" +
                    "\t\t\t\"Ask\": 0.01591411,\n" +
                    "\t\t\t\"OpenBuyOrders\": 2099,\n" +
                    "\t\t\t\"OpenSellOrders\": 5647,\n" +
                    "\t\t\t\"PrevDay\": 0.01745500,\n" +
                    "\t\t\t\"Created\": \"2014-02-13T00:00:00\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetOpenOrders() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"Uuid\": null,\n" +
                    "\t\t\t\"OrderUuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\",\n" +
                    "\t\t\t\"Exchange\": \"BTC-FLO\",\n" +
                    "\t\t\t\"OrderType\": \"LIMIT_SELL\",\n" +
                    "\t\t\t\"Quantity\": 1500.00000000,\n" +
                    "\t\t\t\"QuantityRemaining\": 1500.00000000,\n" +
                    "\t\t\t\"Limit\": 0.00010000,\n" +
                    "\t\t\t\"CommissionPaid\": 0.00000000,\n" +
                    "\t\t\t\"Price\": 0.00000000,\n" +
                    "\t\t\t\"PricePerUnit\": null,\n" +
                    "\t\t\t\"Opened\": \"2017-08-28T23:37:44.56\",\n" +
                    "\t\t\t\"Closed\": null,\n" +
                    "\t\t\t\"CancelInitiated\": false,\n" +
                    "\t\t\t\"ImmediateOrCancel\": false,\n" +
                    "\t\t\t\"IsConditional\": false,\n" +
                    "\t\t\t\"Condition\": \"NONE\",\n" +
                    "\t\t\t\"ConditionTarget\": null\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"Uuid\": null,\n" +
                    "\t\t\t\"OrderUuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\",\n" +
                    "\t\t\t\"Exchange\": \"BTC-LSK\",\n" +
                    "\t\t\t\"OrderType\": \"LIMIT_SELL\",\n" +
                    "\t\t\t\"Quantity\": 10.00000000,\n" +
                    "\t\t\t\"QuantityRemaining\": 10.00000000,\n" +
                    "\t\t\t\"Limit\": 0.00146214,\n" +
                    "\t\t\t\"CommissionPaid\": 0.00000000,\n" +
                    "\t\t\t\"Price\": 0.00000000,\n" +
                    "\t\t\t\"PricePerUnit\": null,\n" +
                    "\t\t\t\"Opened\": \"2017-09-04T22:33:13.543\",\n" +
                    "\t\t\t\"Closed\": null,\n" +
                    "\t\t\t\"CancelInitiated\": false,\n" +
                    "\t\t\t\"ImmediateOrCancel\": false,\n" +
                    "\t\t\t\"IsConditional\": false,\n" +
                    "\t\t\t\"Condition\": \"NONE\",\n" +
                    "\t\t\t\"ConditionTarget\": null\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetOrder() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"AccountId\": null,\n" +
                    "\t\t\"OrderUuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\",\n" +
                    "\t\t\"Exchange\": \"BTC-SC\",\n" +
                    "\t\t\"Type\": \"LIMIT_BUY\",\n" +
                    "\t\t\"Quantity\": 7500.00000000,\n" +
                    "\t\t\"QuantityRemaining\": 0.00000000,\n" +
                    "\t\t\"Limit\": 0.00000188,\n" +
                    "\t\t\"Reserved\": 0.01410000,\n" +
                    "\t\t\"ReserveRemaining\": 0.01410000,\n" +
                    "\t\t\"CommissionReserved\": 0.00003525,\n" +
                    "\t\t\"CommissionReserveRemaining\": 0.00000000,\n" +
                    "\t\t\"CommissionPaid\": 0.00003525,\n" +
                    "\t\t\"Price\": 0.01410000,\n" +
                    "\t\t\"PricePerUnit\": 0.00000188000000000000,\n" +
                    "\t\t\"Opened\": \"2017-09-03T09:05:05.563\",\n" +
                    "\t\t\"Closed\": \"2017-09-03T09:22:08.26\",\n" +
                    "\t\t\"IsOpen\": false,\n" +
                    "\t\t\"Sentinel\": \"ac3714e0-8655-4207-b78f-d5bb588ac36a\",\n" +
                    "\t\t\"CancelInitiated\": false,\n" +
                    "\t\t\"ImmediateOrCancel\": false,\n" +
                    "\t\t\"IsConditional\": false,\n" +
                    "\t\t\"Condition\": \"NONE\",\n" +
                    "\t\t\"ConditionTarget\": null\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetOrderBook() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"buy\": [{\n" +
                    "\t\t\t\t\"Quantity\": 7.58754865,\n" +
                    "\t\t\t\t\"Rate\": 0.01604003\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t\"Quantity\": 0.10000000,\n" +
                    "\t\t\t\t\"Rate\": 0.01604002\n" +
                    "\t\t\t}\n" +
                    "\t\t],\n" +
                    "\t\t\"sell\": [{\n" +
                    "\t\t\t\t\"Quantity\": 0.00000075,\n" +
                    "\t\t\t\t\"Rate\": 0.01613499\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t\"Quantity\": 12.92178240,\n" +
                    "\t\t\t\t\"Rate\": 0.01614497\n" +
                    "\t\t\t}\n" +
                    "\t\t]\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetOrderHistory() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"OrderUuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\",\n" +
                    "\t\t\t\"Exchange\": \"BTC-LSK\",\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T11:41:44.997\",\n" +
                    "\t\t\t\"OrderType\": \"LIMIT_SELL\",\n" +
                    "\t\t\t\"Limit\": 0.00005000,\n" +
                    "\t\t\t\"Quantity\": 10.00000000,\n" +
                    "\t\t\t\"QuantityRemaining\": 0.00000000,\n" +
                    "\t\t\t\"Commission\": 0.00003380,\n" +
                    "\t\t\t\"Price\": 0.01352350,\n" +
                    "\t\t\t\"PricePerUnit\": 0.00135235000000000000,\n" +
                    "\t\t\t\"IsConditional\": false,\n" +
                    "\t\t\t\"Condition\": \"NONE\",\n" +
                    "\t\t\t\"ConditionTarget\": null,\n" +
                    "\t\t\t\"ImmediateOrCancel\": false,\n" +
                    "\t\t\t\"Closed\": \"2017-09-04T11:41:45.26\"\n" +
                    "\t\t}, {\n" +
                    "\t\t\t\"OrderUuid\": \"ac3714e0-8655-4207-b78f-d5bb588ac36a\",\n" +
                    "\t\t\t\"Exchange\": \"BTC-LSK\",\n" +
                    "\t\t\t\"TimeStamp\": \"2017-09-04T10:50:57.953\",\n" +
                    "\t\t\t\"OrderType\": \"LIMIT_BUY\",\n" +
                    "\t\t\t\"Limit\": 0.00143784,\n" +
                    "\t\t\t\"Quantity\": 10.00000000,\n" +
                    "\t\t\t\"QuantityRemaining\": 0.00000000,\n" +
                    "\t\t\t\"Commission\": 0.00003593,\n" +
                    "\t\t\t\"Price\": 0.01437839,\n" +
                    "\t\t\t\"PricePerUnit\": 0.00143783000000000000,\n" +
                    "\t\t\t\"IsConditional\": false,\n" +
                    "\t\t\t\"Condition\": \"NONE\",\n" +
                    "\t\t\t\"ConditionTarget\": null,\n" +
                    "\t\t\t\"ImmediateOrCancel\": false,\n" +
                    "\t\t\t\"Closed\": \"2017-09-04T11:27:44.077\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetTicker() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"Bid\": 0.01612000,\n" +
                    "\t\t\"Ask\": 0.01614000,\n" +
                    "\t\t\"Last\": 0.01613999\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleGetWithdrawalHistory() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": [{\n" +
                    "\t\t\t\"PaymentUuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\",\n" +
                    "\t\t\t\"Currency\": \"BTC\",\n" +
                    "\t\t\t\"Amount\": 0.02122400,\n" +
                    "\t\t\t\"Address\": \"WITHDRAWAL-ADDRESS\",\n" +
                    "\t\t\t\"Opened\": \"2017-08-26T23:01:36.9\",\n" +
                    "\t\t\t\"Authorized\": true,\n" +
                    "\t\t\t\"PendingPayment\": false,\n" +
                    "\t\t\t\"TxCost\": 0.00100000,\n" +
                    "\t\t\t\"TxId\": \"TX-ID\",\n" +
                    "\t\t\t\"Canceled\": false,\n" +
                    "\t\t\t\"InvalidAddress\": false\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handlePlaceBuyLimitOrder() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"uuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\"\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handlePlaceSellLimitOrder() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"uuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\"\n" +
                    "\t}\n" +
                    "}\n", 200);
        }

        @NonNull
        private Response handleWithdraw() {
            return new Response("{\n" +
                    "\t\"success\": true,\n" +
                    "\t\"message\": \"\",\n" +
                    "\t\"result\": {\n" +
                    "\t\t\"uuid\": \"57aac6c3-197f-45e0-af29-cd650cc5dea8\"\n" +
                    "\t}\n" +
                    "}\n", 200);
        }
    }
}
