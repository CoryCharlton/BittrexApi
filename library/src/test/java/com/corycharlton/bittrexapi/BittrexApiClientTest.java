package com.corycharlton.bittrexapi;

import android.support.annotation.NonNull;

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
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

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

    public static class When_cancelOrder_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final CancelOrderResponse response = getClient().cancelOrder(UUID.randomUUID());

            assertNotNull(response);
            assertTrue(response.success());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_uuid_is_null() throws IOException {
            getClient().cancelOrder(null);
        }
    }

    public static class When_getBalance_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetBalanceResponse response = getClient().getBalance("BTC");

            assertNotNull(response);
            assertTrue(response.success());

            final Balance result = response.result();

            assertNotNull(result);
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

            assertNotNull(response);
            assertTrue(response.success());

            final ArrayList<Balance> result = response.result();

            assertNotNull(result);
            assertEquals(2, result.size());

            final Balance item = result.get(0);
            
            assertNotNull(item);
            assertEquals("BTC", item.currency());
            assertEquals(0.0000000119203, item.available(), 0.00000001);
            assertEquals(0.0000000119203, item.balance(), 0.00000001);
            assertEquals("CRYPTO-ADDRESS-BTC", item.cryptoAddress());
            assertEquals(0.00000000, item.pending(), 0.00000001);
        }
    }

    public static class When_getCurrencies_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetCurrenciesResponse response = getClient().getCurrencies();

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
    }

    public static class When_getDepositAddress_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetDepositAddressResponse response = getClient().getDepositAddress("BTC");

            assertNotNull(response);
            assertTrue(response.success());

            final DepositAddress result = response.result();

            assertNotNull(result);
            assertEquals("DEPOSIT-ADDRESS", result.address());
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
    }

    public static class When_getMarketHistory_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetMarketHistoryResponse response = getClient().getMarketHistory("BTC-LTC");

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

    public static class When_getMarkets_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetMarketsResponse response = getClient().getMarkets();

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
    }

    public static class When_getMarketSummaries_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetMarketSummariesResponse response = getClient().getMarketSummaries();

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
    }

    public static class When_getMarketSummary_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetMarketSummaryResponse response = getClient().getMarketSummary("BTC-LTC");

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

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() throws IOException {
            getClient().getMarketSummary(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() throws IOException {
            getClient().getMarketSummary(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() throws IOException {
            getClient().getMarketSummary(" \t\r\n");
        }
    }

    public static class When_getOpenOrders_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetOpenOrdersResponse response = getClient().getOpenOrders();

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
    }

    public static class When_getOrder_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetOrderResponse response = getClient().getOrder(UUID.randomUUID());

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

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_uuid_is_null() throws IOException {
            getClient().getOrder(null);
        }
    }

    public static class When_getOrderBook_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetOrderBookResponse response = getClient().getOrderBook("BTC-LTC");

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

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() throws IOException {
            getClient().getOrderBook(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() throws IOException {
            getClient().getOrderBook(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() throws IOException {
            getClient().getOrderBook(" \t\r\n");
        }
    }

    public static class When_getOrderHistory_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetOrderHistoryResponse response = getClient().getOrderHistory();

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
    }

    public static class When_getTicker_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetTickerResponse response = getClient().getTicker("BTC-LTC");

            assertNotNull(response);
            assertTrue(response.success());

            final Ticker item = response.result();

            assertNotNull(item);

            assertEquals(0.01614000, item.ask(), 0.00000001);
            assertEquals(0.01612000, item.bid(), 0.00000001);
            assertEquals(0.01613999, item.last(), 0.00000001);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() throws IOException {
            getClient().getTicker(StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() throws IOException {
            getClient().getTicker(null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() throws IOException {
            getClient().getTicker(" \t\r\n");
        }
    }

    public static class When_getWithdrawalHistory_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final GetWithdrawalHistoryResponse response = getClient().getWithdrawalHistory();

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
    }

    public static class When_placeBuyLimitOrder_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final PlaceBuyLimitOrderResponse response = getClient().placeBuyLimitOrder("BTC-LTC", 1.00000, 0.00000123);

            assertNotNull(response);
            assertTrue(response.success());

            final Uuid item = response.result();

            assertNotNull(item);

            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.uuid().toString());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() throws IOException {
            getClient().placeBuyLimitOrder(StringUtils.EMPTY, 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() throws IOException {
            getClient().placeBuyLimitOrder(null, 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() throws IOException {
            getClient().placeBuyLimitOrder(" \t\r\n", 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_quantity_is_less_than_or_equal_to_zero() throws IOException {
            getClient().placeBuyLimitOrder("BTC-LTC", 0.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_rate_is_less_than_or_equal_to_zero() throws IOException {
            getClient().placeBuyLimitOrder("BTC-LTC", 1.00000000, 0.00000000);
        }
    }

    public static class When_placeSellLimitOrder_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final PlaceSellLimitOrderResponse response = getClient().placeSellLimitOrder("BTC-LTC", 1.00000, 0.00000123);

            assertNotNull(response);
            assertTrue(response.success());

            final Uuid item = response.result();

            assertNotNull(item);

            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.uuid().toString());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_empty() throws IOException {
            getClient().placeSellLimitOrder(StringUtils.EMPTY, 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_null() throws IOException {
            getClient().placeSellLimitOrder(null, 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_market_is_whitespace() throws IOException {
            getClient().placeSellLimitOrder(" \t\r\n", 1.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_quantity_is_less_than_or_equal_to_zero() throws IOException {
            getClient().placeSellLimitOrder("BTC-LTC", 0.00000000, 0.00000123);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_rate_is_less_than_or_equal_to_zero() throws IOException {
            getClient().placeSellLimitOrder("BTC-LTC", 1.00000000, 0.00000000);
        }
    }

    public static class When_withdraw_is_called {
        @Test()
        public void it_should_parse_response() throws IOException {
            final WithdrawResponse response = getClient().withdraw("BTC", 1.00000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");

            assertNotNull(response);
            assertTrue(response.success());

            final Uuid item = response.result();

            assertNotNull(item);

            assertEquals("57aac6c3-197f-45e0-af29-cd650cc5dea8", item.uuid().toString());
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_address_is_empty() throws IOException {
            getClient().withdraw("BTC", 1.00000000, StringUtils.EMPTY);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_address_is_null() throws IOException {
            getClient().withdraw("BTC", 1.00000000, null);
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_address_is_whitespace() throws IOException {
            getClient().withdraw("BTC", 1.00000000, " \t\r\n");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_empty() throws IOException {
            getClient().withdraw(StringUtils.EMPTY, 1.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_null() throws IOException {
            getClient().withdraw(null, 1.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_currency_is_whitespace() throws IOException {
            getClient().withdraw(" \t\r\n", 1.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
        }

        @Test(expected = IllegalArgumentException.class)
        public void it_should_throw_exception_if_quantity_is_less_than_or_equal_to_zero() throws IOException {
            getClient().withdraw("BTC-LTC", 0.00000000, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");
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
                case BittrexApiClient.URL_CANCELORDER:
                    return handleCancelOrder();
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
                case BittrexApiClient.URL_GETMARKETS:
                    return handleGetMarkets();
                case BittrexApiClient.URL_GETMARKETSUMMARIES:
                    return handleGetMarketSummaries();
                case BittrexApiClient.URL_GETMARKETSUMMARY:
                    return handleGetMarketSummary();
                case BittrexApiClient.URL_GETOPENORDERS:
                    return handleGetOpenOrders();
                case BittrexApiClient.URL_GETORDER:
                    return handleGetOrder();
                case BittrexApiClient.URL_GETORDERBOOK:
                    return handleGetOrderBook();
                case BittrexApiClient.URL_GETORDERHISTORY:
                    return handleGetOrderHistory();
                case BittrexApiClient.URL_GETTICKER:
                    return handleGetTicker();
                case BittrexApiClient.URL_GETWITHDRAWALHISTORY:
                    return handleGetWithdrawalHistory();
                case BittrexApiClient.URL_PLACEBUYLIMITORDER:
                    return handlePlaceBuyLimitOrder();
                case BittrexApiClient.URL_PLACESELLLIMITORDER:
                    return handlePlaceSellLimitOrder();
                case BittrexApiClient.URL_WITHDRAW:
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
