package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.CancelOrderResponse;

import java.util.UUID;

import static com.corycharlton.bittrexapi.internal.util.Ensure.*;

/**
 * Used to cancel a buy or sell order.
 */
// TODO: Test live
public class CancelOrderRequest extends Request<CancelOrderResponse> {

    /**
     * Used to cancel a buy or sell order.
     * @param uuid uuid of the buy or sell order to cancel
     */
    public CancelOrderRequest(@NonNull UUID uuid) {
        super(Url.CANCELORDER, true, CancelOrderResponse.class);

        isNotNull("uuid", uuid);
        addParameter("uuid", uuid);
    }
}
