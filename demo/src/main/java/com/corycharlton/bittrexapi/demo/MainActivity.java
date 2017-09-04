package com.corycharlton.bittrexapi.demo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.BittrexApiLibraryInfo;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetDepositAddressResponse;
import com.corycharlton.bittrexapi.response.GetDepositHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetOrderHistoryResponse;
import com.corycharlton.bittrexapi.response.GetOrderResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;
import com.corycharlton.bittrexapi.response.GetWithdrawalHistoryResponse;

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

            GetMarketHistoryResponse response2 = client.getMarketHistory("BTC-LTC");

            Log.v(BittrexApiLibraryInfo.TAG, "Just for a breakpoint...");
            /*
            GetOrderResponse response17 = client.getOrder(UUID.fromString("360f7031-e358-4bd0-9efc-2558b66d6157"));
            GetOrderHistoryResponse response15 = client.getOrderHistory("BTC-SC");
            GetOrderHistoryResponse response16 = client.getOrderHistory();
            GetDepositHistoryResponse response11 = client.getDepositHistory("BTC");
            GetWithdrawalHistoryResponse response13 = client.getWithdrawalHistory("BTC");
            GetWithdrawalHistoryResponse response14 = client.getWithdrawalHistory();
            GetMarketsResponse response3 = client.getMarkets();
            GetMarketSummariesResponse response4 = client.getMarketSummaries();
            GetMarketSummaryResponse response5 = client.getMarketSummary("BTC-LTC");
            GetOrderBookResponse response6 = client.getOrderBook("BTC-LTC");
            GetTickerResponse response7 = client.getTicker("BTC-LTC");

            GetBalanceResponse response9 = client.getBalance("BTC");
            GetBalancesResponse response8 = client.getBalances();
            GetCurrenciesResponse response1 = client.getCurrencies();
            GetDepositAddressResponse response10 = client.getDepositAddress("BTC");
            GetDepositHistoryResponse response12 = client.getDepositHistory();
            GetDepositHistoryResponse response11 = client.getDepositHistory("BTC");
            GetMarketHistoryResponse response2 = client.getMarketHistory("BTC-LTC");

            */
        } catch (IOException e) {
            Log.e(BittrexApiLibraryInfo.TAG, e.toString(), e);
        }
    }
}
