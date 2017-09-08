package com.corycharlton.bittrexapi.request;

import android.support.annotation.Nullable;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetOrderHistoryResponse;

/**
 * Used to retrieve your order history..
 */
public class GetOrderHistoryRequest extends Request<GetOrderHistoryResponse> {

    /**
     * Used to retrieve your order history..
     */
    public GetOrderHistoryRequest() {
        this(null);
    }

    /**
     * Used to retrieve your order history.
     * @param market An optional string literal for the market (ie. BTC-LTC). If omitted, will return for all markets
     */
    public GetOrderHistoryRequest(@Nullable String market) {
        super(Url.GETORDERHISTORY, true, GetOrderHistoryResponse.class);

        if (!StringUtils.isNullOrWhiteSpace(market)) {
            addParameter("market", market);
        }
    }
}
