package com.corycharlton.bittrexapi.demo.models;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.demo.activities.Activity;
import com.corycharlton.bittrexapi.internal.util.Ensure;

public class RequestActivity<T extends Activity> {

    private final Class<T> _activityClass;
    private final boolean _requiresAuthentication;
    private final String _title;

    public RequestActivity(@NonNull String title, boolean requiresAuthentication, @NonNull Class<T> activityClass) {
        Ensure.isNotNull("activityClass", activityClass);
        Ensure.isNotNullOrWhitespace("title", title);

        _activityClass = activityClass;
        _requiresAuthentication = requiresAuthentication;
        _title = title;
    }

    public boolean requiresAuthentication() {
        return _requiresAuthentication;
    }

    public void startActivity(@NonNull Context context) {
        context.startActivity(new Intent(context, _activityClass));
    }

    @NonNull
    public String title() {
        return _title;
    }
}
