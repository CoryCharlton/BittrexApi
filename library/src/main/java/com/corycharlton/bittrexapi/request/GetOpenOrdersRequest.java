package com.corycharlton.bittrexapi.request;

import android.support.annotation.Nullable;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetOpenOrdersResponse;

/**
 * Get all orders that you currently have opened.
 */
public class GetOpenOrdersRequest extends Request<GetOpenOrdersResponse> {

    /**
     * Get all orders that you currently have opened.
     */
    public GetOpenOrdersRequest() {
        this(null);
    }

    /**
     * Get all orders that you currently have opened.
     * @param market An optional string literal for the market (ie. BTC-LTC)
     */
    public GetOpenOrdersRequest(@Nullable String market) {
        super(Url.GETOPENORDERS, true, GetOpenOrdersResponse.class);

        if (!StringUtils.isNullOrWhiteSpace(market)) {
            addParameter("market", market);
        }
    }
}
