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

        _context = context;
    }

    @CallSuper
    @Override
    public void onFailure(Request<T> request, IOException e) {
        Toast.makeText(_context, "Error executing " + request.getClass().getSimpleName() + ": " + e, Toast.LENGTH_LONG).show();
    }

    @Override
    public abstract void onResponse(Request<T> request, T response);
}
