package com.corycharlton.bittrexapi.demo.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.activities.GetBalancesActivity;
import com.corycharlton.bittrexapi.demo.activities.GetCurrenciesActivity;
import com.corycharlton.bittrexapi.demo.models.RequestActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class RequestActivityAdapter extends SelectableAdapter<RequestActivityAdapter.ViewHolder, RequestActivity<?>> {

    private final ArrayList<RequestActivity> _requestActivities = new ArrayList<>();

    public RequestActivityAdapter() {
        _requestActivities.add(new RequestActivity<>("GetBalancesRequest", true, GetBalancesActivity.class));
        _requestActivities.add(new RequestActivity<>("GetCurrenciesRequest", false, GetCurrenciesActivity.class));
    }

    @Override
    public RequestActivity getItem(int position) {
        return _requestActivities.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requestactivity, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, RequestActivity item) {
        viewHolder.authenticationImageView.setImageResource(item.requiresAuthentication() ? R.drawable.ic_lock_outline_black_24dp : R.drawable.ic_lock_open_black_24dp);
        viewHolder.titleTextView.setText(item.title());
    }

    @Override
    public int getItemCount() {
        return _requestActivities.size();
    }

    // region ViewHolder
    class ViewHolder extends SelectableAdapter.ViewHolder {

        @BindView(R.id.authentication_imageview) ImageView authenticationImageView;
        @BindView(R.id.title_textview) TextView titleTextView;

        ViewHolder(@NonNull View view) {
            super(view);
        }
    }
    // endregion
}