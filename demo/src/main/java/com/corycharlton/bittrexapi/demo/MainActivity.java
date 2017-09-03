package com.corycharlton.bittrexapi.demo;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.BittrexApiLibraryInfo;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;
import com.corycharlton.bittrexapi.response.GetTickerResponse;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            final BittrexApiClient client =  new BittrexApiClient.Builder("12345", "54321").build();

            GetCurrenciesResponse response1 = client.getCurrencies();
            /*
            GetMarketHistoryResponse response2 = client.getMarketHistory("BTC-LTC");
            GetMarketsResponse response3 = client.getMarkets();
            GetMarketSummariesResponse response4 = client.getMarketSummaries();
            GetMarketSummaryResponse response5 = client.getMarketSummary("BTC-LTC");
            GetOrderBookResponse response6 = client.getOrderBook("BTC-LTC");
            GetTickerResponse response7 = client.getTicker("BTC-LTC");
            */
        } catch (IOException e) {
            Log.e(BittrexApiLibraryInfo.TAG, e.toString(), e);
        }
    }
}
