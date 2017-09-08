package com.corycharlton.bittrexapi;

import com.corycharlton.bittrexapi.request.Request;
import com.corycharlton.bittrexapi.response.Response;

import java.io.IOException;

public interface Callback<T extends Response> {
    /**
     * Called when the request could not be executed due to cancellation, a connectivity problem or
     * timeout. Because networks can fail during an exchange, it is possible that the remote server
     * accepted the request before the failure.
     * @param request The {@link Request}
     * @param e The {@link IOException}
     */
    void onFailure(Request<T> request, IOException e);

    /**
     * Called when the HTTP response was successfully returned by the remote server. The callback may
     * proceed to read the {@link Response}. The recipient of the callback may consume the {@link Response}
     * on another thread.
     * @param request The {@link Request}
     * @param response The {@link Response}
     */
    void onResponse(Request<T> request, T response);
}
