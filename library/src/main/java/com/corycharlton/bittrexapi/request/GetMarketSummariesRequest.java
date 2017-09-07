package com.corycharlton.bittrexapi.request;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetMarketSummariesResponse;

/**
 * Used to get the last 24 hour summary of all active exchanges.
 */
public class GetMarketSummariesRequest extends Request<GetMarketSummariesResponse> {

    /**
     * Used to get the last 24 hour summary of all active exchanges.
     */
    public GetMarketSummariesRequest() {
        super(Url.GETMARKETSUMMARIES, false, GetMarketSummariesResponse.class);
    }
}
