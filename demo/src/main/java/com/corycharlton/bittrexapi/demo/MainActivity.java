package com.corycharlton.bittrexapi.demo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.BittrexApiLibraryInfo;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.extension.okhttp.OkHttpDownloader;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;
import com.corycharlton.bittrexapi.response.GetOpenOrdersResponse;
import com.corycharlton.bittrexapi.response.PlaceBuyLimitOrderResponse;
import com.corycharlton.bittrexapi.response.PlaceSellLimitOrderResponse;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationSettings.initialize(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            final BittrexApiClient client = new BittrexApiClient.Builder()
                    .downloader(new OkHttpDownloader())
                    .key(ApplicationSettings.instance().getKey())
                    .secret(ApplicationSettings.instance().getSecret())
                    .build();

            CancelOrderResponse response11 = client.cancelOrder(UUID.randomUUID());

            Log.v(BittrexApiLibraryInfo.TAG, "Just for a breakpoint... " + response11.toString());

            /*
            GetBalanceResponse response1 = client.getBalance("BTC");
            GetBalancesResponse response2 = client.getBalances();
            GetCurrenciesResponse response3 = client.getCurrencies();
            GetDepositAddressResponse response4 = client.getDepositAddress("BTC");
            GetDepositHistoryResponse response5 = client.getDepositHistory();
            GetDepositHistoryResponse response6 = client.getDepositHistory("BTC");
            GetMarketHistoryResponse response7 = client.getMarketHistory("BTC-LTC");
            GetMarketsResponse response8 = client.getMarkets();
            GetMarketSummariesResponse response9 = client.getMarketSummaries();
            GetMarketSummaryResponse response10 = client.getMarketSummary("BTC-LTC");
            GetOpenOrdersResponse response11 = client.getOpenOrders();
            GetOrderResponse response12 = client.getOrder(UUID.fromString("360f7031-e358-4bd0-9efc-2558b66d6157"));
            GetOrderBookResponse response13 = client.getOrderBook("BTC-LTC");
            GetOrderHistoryResponse response14 = client.getOrderHistory();
            GetOrderHistoryResponse response15 = client.getOrderHistory("BTC-SC");
            GetTickerResponse response16 = client.getTicker("BTC-LTC");
            GetWithdrawalHistoryResponse response17 = client.getWithdrawalHistory();
            GetWithdrawalHistoryResponse response18 = client.getWithdrawalHistory("BTC");

            CancelOrderResponse response19 = client.cancelOrder(UUID.fromString("b45c12ab-eb10-418a-ba4a-4e85b8d7db28"));
            PlaceBuyLimitOrderResponse response20 = client.placeBuyLimitOrder("BTC-SC", 1000, 0.00000150);
            PlaceSellLimitOrderResponse response21 = client.placeSellLimitOrder("BTC-SC", 1000, 0.00001930);
            */
        } catch (IOException e) {
            Log.e(BittrexApiLibraryInfo.TAG, e.toString(), e);
        }
    }
}
