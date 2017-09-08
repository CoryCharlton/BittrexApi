package com.corycharlton.bittrexapi.demo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;

import com.corycharlton.bittrexapi.demo.R;
import com.corycharlton.bittrexapi.demo.adapters.GetBalancesAdapter;
import com.corycharlton.bittrexapi.demo.settings.ApplicationSettings;

import butterknife.BindView;
import butterknife.OnClick;

public class GetBalancesActivity extends Activity implements View.OnClickListener {
    private static final String TAG = GetBalancesActivity.class.getSimpleName();

    @BindView(R.id.hidezerobalances_checkbox) CheckedTextView _hideZeroBalancesCheckbox;
    @BindView(R.id.recyclerview) RecyclerView _recyclerView;

    private GetBalancesAdapter _adapter;
    private LinearLayoutManager _layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getbalances);

        _adapter = new GetBalancesAdapter(this);
        _adapter.setHideZeroBalances(ApplicationSettings.instance().getHideZeroBalances());

        _layoutManager = new LinearLayoutManager(this);

        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);

        _hideZeroBalancesCheckbox.setChecked(ApplicationSettings.instance().getHideZeroBalances());
        _hideZeroBalancesCheckbox.setOnClickListener(this);

        _adapter.loadItems();
    }

    @NonNull
    @Override
    protected String getTag() {
        return TAG;
    }

    @OnClick(R.id.hidezerobalances_checkbox)
    @Override
    public void onClick(View view) {
        final int id = view.getId();

        switch (id) {
            case R.id.hidezerobalances_checkbox:
                _hideZeroBalancesCheckbox.setChecked(!_hideZeroBalancesCheckbox.isChecked());
                ApplicationSettings.instance().setHideZeroBalances(_hideZeroBalancesCheckbox.isChecked());

                _adapter.setHideZeroBalances(_hideZeroBalancesCheckbox.isChecked());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        switch (itemId) {
            case R.id.action_refresh:
                _adapter.loadItems();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
