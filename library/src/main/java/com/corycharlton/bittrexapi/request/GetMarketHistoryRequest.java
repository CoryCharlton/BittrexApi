package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetMarketHistoryResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;

/**
 * Used to retrieve the latest trades that have occurred for a specific market.
 */
public class GetMarketHistoryRequest extends Request<GetMarketHistoryResponse> {

    /**
     * Used to retrieve the latest trades that have occurred for a specific market.
     * @param market A string literal for the market (ex: BTC-LTC)
     */
    public GetMarketHistoryRequest(@NonNull String market) {
        super(Url.GETMARKETHISTORY, false, GetMarketHistoryResponse.class);

        isNotNullOrWhitespace("market", market);
        addParameter("market", market);
    }
}
