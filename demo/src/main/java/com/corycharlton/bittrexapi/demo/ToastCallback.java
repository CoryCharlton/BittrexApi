package com.corycharlton.bittrexapi.demo;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.corycharlton.bittrexapi.Callback;
import com.corycharlton.bittrexapi.internal.util.Ensure;
import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.response.Response;

import java.io.IOException;

public abstract class ToastCallback<T extends Response> implements Callback<T> {

    private final Context _context;

    protected ToastCallback(@NonNull Context context) {
        Ensure.isNotNull("context", context);

        _context = context.getApplicationContext();
    }

    @CallSuper
    @Override
    public void onFailure(Request<T> request, IOException e) {
        Toast.makeText(_context, "Error executing " + request.getClass().getSimpleName() + ": " + e, Toast.LENGTH_LONG).show();
    }

    @CallSuper
    @Override
    public void onResponse(Request<T> request, T response) {
        if (response == null) {
            Toast.makeText(_context, "Error executing " + request.getClass().getSimpleName() + ": Empty response returned", Toast.LENGTH_LONG).show();
        } else if (!response.success()) {
            Toast.makeText(_context, "Error executing " + request.getClass().getSimpleName() + ": " + response.message(), Toast.LENGTH_LONG).show();
        }
    }
}
