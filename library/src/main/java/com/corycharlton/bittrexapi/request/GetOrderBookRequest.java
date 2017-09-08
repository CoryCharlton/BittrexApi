package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetOrderBookResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;

/**
 * Used to retrieve the latest trades that have occurred for a specific market.
 */
public class GetOrderBookRequest extends Request<GetOrderBookResponse> {

    /**
     * Used to retrieve the order book for a given market.
     * @param market A string literal for the market (ex: BTC-LTC)
     */
    // TODO: Expose the 'type' parameter?
    public GetOrderBookRequest(@NonNull String market) {
        super(Url.GETORDERBOOK, false, GetOrderBookResponse.class);

        isNotNullOrWhitespace("market", market);
        addParameter("market", market);
        addParameter("type", "both");
    }
}
