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
import com.corycharlton.bittrexapi.extension.okhttp.OkHttpDownloader;
import com.corycharlton.bittrexapi.model.Currency;
import com.corycharlton.bittrexapi.request.GetCurrenciesRequest;
import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;

public class GetCurrenciesAdapter extends SelectableAdapter<GetCurrenciesAdapter.ViewHolder, Currency> {

    private final ArrayList<Currency> _items = new ArrayList<>();

    public GetCurrenciesAdapter(@NonNull Context context) {
        final BittrexApiClient client = new BittrexApiClient.Builder()
                .downloader(new OkHttpDownloader())
                .build();

        client.executeAsync(new GetCurrenciesRequest(), new CurrencyCallback(context));
    }

    @Override
    public Currency getItem(int position) {
        return _items.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GetCurrenciesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Currency item) {
        viewHolder.currencyLongTextView.setText(item.currencyLong());
        viewHolder.currencyTextView.setText(item.currency());
        viewHolder.minConfirmationTextView.setText("Min Confirmations: " + (int) item.minConfirmation());
        viewHolder.txFeeTextView.setText(String.format(Locale.US, "Tx Fee: %1$,.8f", item.txFee()));
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }

    private class CurrencyCallback extends ToastCallback<GetCurrenciesResponse> {
        CurrencyCallback(@NonNull Context context) {
            super(context);
        }

        @Override
        public void onResponse(Request<GetCurrenciesResponse> request, GetCurrenciesResponse response) {
            super.onResponse(request, response);

            if (response.success()) {
                _items.clear();

                for (Currency currency : response.result()) {
                    _items.add(currency);
                }

                notifyDataSetChanged();
            }
        }
    }
    // region ViewHolder
    class ViewHolder extends SelectableAdapter.ViewHolder {

        @BindView(R.id.currencylong_textview)
        TextView currencyLongTextView;
        @BindView(R.id.currency_textview)
        TextView currencyTextView;
        @BindView(R.id.minconfirmation_textview)
        TextView minConfirmationTextView;
        @BindView(R.id.txfee_textview)
        TextView txFeeTextView;

        ViewHolder(@NonNull View view) {
            super(view);
        }
    }
    // endregion
}
