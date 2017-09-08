package com.corycharlton.bittrexapi.demo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.BittrexApiLibraryInfo;
import com.corycharlton.bittrexapi.Callback;
import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.extension.okhttp.OkHttpDownloader;
import com.corycharlton.bittrexapi.request.GetCurrenciesRequest;
import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;

import java.io.IOException;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationSettings.initialize(this);

        try {
            final BittrexApiClient client = new BittrexApiClient.Builder()
                    .downloader(new OkHttpDownloader())
                    .key(ApplicationSettings.instance().getKey())
                    .secret(ApplicationSettings.instance().getSecret())
                    .build();

            // TODO: Test the other requests beyond this one...
            client.executeAsync(new GetCurrenciesRequest(), new Callback<GetCurrenciesResponse>() {
                @Override
                public void onFailure(Request<GetCurrenciesResponse> request, IOException exception) {
                    Log.e(BittrexApiLibraryInfo.TAG, "*** onFailure", exception);
                }

                @Override
                public void onResponse(Request<GetCurrenciesResponse> request, GetCurrenciesResponse response) {
                    Log.e(BittrexApiLibraryInfo.TAG, "*** onResponse: " + response.toString());
                }
            });

            Log.v(BittrexApiLibraryInfo.TAG, "Just for a breakpoint... ");

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
            WithdrawResponse response3 = client.withdraw("BTC", 0.004, "1HcDQvR5YGx7S5udZL1MigWpgxRzsQRzrb");

            */
        } catch (Exception e) {
            Log.e(BittrexApiLibraryInfo.TAG, e.toString(), e);
        }

        if (savedInstanceState == null && !ApplicationSettings.instance().isAuthenticationConfigured()) {
            AuthenticationActivity.startActivity(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Enable buttons based on ApplicationSettings.instance().isAuthenticationConfigured()
    }

    @NonNull
    @Override
    protected String getTag() {
        return TAG;
    }
}
