package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetTickerResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;

/**
 * Used to get the current tick values for a market.
 */
public class GetTickerRequest extends Request<GetTickerResponse> {

    /**
     * Used to get the current tick values for a market.
     * @param market A string literal for the market (ex: BTC-LTC)
     */
    public GetTickerRequest(@NonNull String market) {
        super(Url.GETTICKER, false, GetTickerResponse.class);

        isNotNullOrWhitespace("market", market);
        addParameter("market", market);
    }
}
