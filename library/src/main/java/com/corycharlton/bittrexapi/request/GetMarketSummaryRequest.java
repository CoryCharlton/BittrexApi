package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetMarketSummaryResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;

/**
 * Used to get the last 24 hour summary for a specific exchange.
 */
public class GetMarketSummaryRequest extends Request<GetMarketSummaryResponse> {

    /**
     * Used to get the last 24 hour summary for a specific exchange.
     * @param market A string literal for the market (ex: BTC-LTC)
     */
    public GetMarketSummaryRequest(@NonNull String market) {
        super(Url.GETMARKETSUMMARY, false, GetMarketSummaryResponse.class);

        isNotNullOrWhitespace("market", market);
        addParameter("market", market);
    }
}
