package com.corycharlton.bittrexapi.demo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.adapters.RequestActivityAdapter;
import com.corycharlton.bittrexapi.demo.adapters.SelectableAdapter;
import com.corycharlton.bittrexapi.demo.models.RequestActivity;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;

import butterknife.BindView;

public class MainActivity extends Activity implements SelectableAdapter.EventListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recyclerview) RecyclerView _recyclerView;

    private RequestActivityAdapter _adapter;
    private LinearLayoutManager _layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);

        ApplicationSettings.initialize(this);

        _adapter = new RequestActivityAdapter();
        _adapter.setEventListener(this);

        _layoutManager = new LinearLayoutManager(this);

        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onItemClicked(int position) {
        final RequestActivity<?> requestActivity = _adapter.getItem(position);

        if (requestActivity.requiresAuthentication() && !ApplicationSettings.instance().isAuthenticationConfigured()) {
            AuthenticationActivity.startActivity(this);
        } else {
            requestActivity.startActivity(this);
        }
    }

    @Override
    public void onItemSelected(int position) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_authentication:
                AuthenticationActivity.startActivity(this);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
