package com.corycharlton.bittrexapi.demo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.corycharlton.bittrexapi.BittrexApiClient;
import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.ToastCallback;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;
import com.corycharlton.bittrexapi.extension.okhttp.OkHttpDownloader;
import com.corycharlton.bittrexapi.model.Balance;
import com.corycharlton.bittrexapi.request.GetBalancesRequest;
import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;

public class GetBalancesAdapter extends SelectableAdapter<GetBalancesAdapter.ViewHolder, Balance> {

    private final Context _context;
    private boolean _hideZeroBalances;
    private final ArrayList<Balance> _items = new ArrayList<>();
    private final ArrayList<Balance> _visibleItems = new ArrayList<>();

    public GetBalancesAdapter(@NonNull Context context) {
        _context = context;
    }

    @Override
    public Balance getItem(int position) {
        return _visibleItems.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GetBalancesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_balance, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Balance item) {
        viewHolder.currencyTextView.setText(item.currency());

        viewHolder.availableTextView.setText(String.format(Locale.US, "%1$,.8f", item.available()));
        viewHolder.pendingTextView.setText(String.format(Locale.US, "%1$,.8f", item.pending()));
        viewHolder.reservedTextView.setText(String.format(Locale.US, "%1$,.8f", item.reserved()));
        viewHolder.totalTextView.setText(String.format(Locale.US, "%1$,.8f", item.total()));
    }

    @Override
    public int getItemCount() {
        return _visibleItems.size();
    }

    public void loadItems() {
        final BittrexApiClient client = new BittrexApiClient.Builder()
                .downloader(new OkHttpDownloader())
                .key(ApplicationSettings.instance().getKey())
                .secret(ApplicationSettings.instance().getSecret())
                .build();

        client.executeAsync(new GetBalancesRequest(), new BalancesCallback(_context));
    }

    public void setHideZeroBalances(boolean hideZeroBalances) {
        _hideZeroBalances = hideZeroBalances;

        updateVisibleItems();
    }

    private void updateVisibleItems() {
        _visibleItems.clear();

        for (Balance item : _items) {
            if (!_hideZeroBalances || item.total() > 0) {
                _visibleItems.add(item);
            }
        }

        notifyDataSetChanged();
    }

    private class BalancesCallback extends ToastCallback<GetBalancesResponse> {
        BalancesCallback(@NonNull Context context) {
            super(context);
        }

        @Override
        public void onResponse(Request<GetBalancesResponse> request, GetBalancesResponse response) {
            super.onResponse(request, response);

            if (response.success()) {
                _items.clear();

                for (Balance balance : response.result()) {
                    _items.add(balance);
                }

                updateVisibleItems();
            }
        }
    }
    // region ViewHolder
    class ViewHolder extends SelectableAdapter.ViewHolder {

        @BindView(R.id.available_textview) TextView availableTextView;
        @BindView(R.id.currency_textview) TextView currencyTextView;
        @BindView(R.id.pending_textview) TextView pendingTextView;
        @BindView(R.id.reserved_textview) TextView reservedTextView;
        @BindView(R.id.total_textview) TextView totalTextView;

        ViewHolder(@NonNull View view) {
            super(view);
        }
    }
    // endregion
}
