package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetOrderResponse;

import java.util.UUID;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNull;

/**
 * Used to retrieve a single order by uuid.
 */
public class GetOrderRequest extends Request<GetOrderResponse> {

    /**
     * Used to retrieve a single order by uuid.
     * @param uuid The uuid of the buy or sell order
     */
    public GetOrderRequest(@NonNull UUID uuid) {
        super(Url.GETORDER, true, GetOrderResponse.class);

        isNotNull("uuid", uuid);
        addParameter("uuid", uuid);
    }
}
