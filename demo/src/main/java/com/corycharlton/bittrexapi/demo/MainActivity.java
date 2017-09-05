package com.corycharlton.bittrexapi.demo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.BittrexApiLibraryInfo;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;
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
            final BittrexApiClient client = new BittrexApiClient.Builder(ApplicationSettings.instance().getKey(), ApplicationSettings.instance().getSecret()).build();

            CancelOrderResponse response15 = client.cancelOrder(UUID.fromString("3d0b9f14-4452-4458-83ae-a1f426a1b1ce"));

            Log.v(BittrexApiLibraryInfo.TAG, "Just for a breakpoint...");
            /*

            GetBalanceResponse response9 = client.getBalance("BTC");
            GetBalancesResponse response8 = client.getBalances();
            GetCurrenciesResponse response1 = client.getCurrencies();
            GetDepositAddressResponse response10 = client.getDepositAddress("BTC");
            GetDepositHistoryResponse response12 = client.getDepositHistory();
            GetDepositHistoryResponse response11 = client.getDepositHistory("BTC");
            GetMarketHistoryResponse response2 = client.getMarketHistory("BTC-LTC");
            GetMarketsResponse response3 = client.getMarkets();
            GetMarketSummariesResponse response4 = client.getMarketSummaries();
            GetMarketSummaryResponse response5 = client.getMarketSummary("BTC-LTC");
            GetOrderResponse response17 = client.getOrder(UUID.fromString("360f7031-e358-4bd0-9efc-2558b66d6157"));
            GetOrderBookResponse response6 = client.getOrderBook("BTC-LTC");
            GetOrderHistoryResponse response16 = client.getOrderHistory();
            GetOrderHistoryResponse response15 = client.getOrderHistory("BTC-SC");
            GetTickerResponse response7 = client.getTicker("BTC-LTC");
            GetWithdrawalHistoryResponse response14 = client.getWithdrawalHistory();
            GetWithdrawalHistoryResponse response13 = client.getWithdrawalHistory("BTC");

            CancelOrderResponse response15 = client.cancelOrder(UUID.fromString("b45c12ab-eb10-418a-ba4a-4e85b8d7db28"));
            PlaceBuyLimitOrderResponse response16 = client.placeBuyLimitOrder("BTC-SC", 1000, 0.00000150);
            PlaceSellLimitOrderResponse response15 = client.placeSellLimitOrder("BTC-SC", 1000, 0.00001930);
            */
        } catch (IOException e) {
            Log.e(BittrexApiLibraryInfo.TAG, e.toString(), e);
        }
    }
}
