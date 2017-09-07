package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.PlaceBuyLimitOrderResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;
import static com.corycharlton.bittrexapi.internal.util.Ensure.isTrue;

/**
 * Used to place a limit buy order in a specific market.
 */
public class PlaceBuyLimitOrderRequest extends Request<PlaceBuyLimitOrderResponse> {

    /**
     * Used to place a limit buy order in a specific market.
     * @param market A string literal for the market (ex: BTC-LTC)
     * @param quantity The amount to purchase
     * @param rate The rate at which to place the order
     */
    public PlaceBuyLimitOrderRequest(@NonNull String market, double quantity, double rate) {
        super(Url.PLACEBUYLIMITORDER, true, PlaceBuyLimitOrderResponse.class);

        isNotNullOrWhitespace("market", market);
        isTrue("quantity", quantity > 0.0);
        isTrue("rate", rate > 0.0);

        addParameter("market", market);
        addParameter("quantity", quantity);
        addParameter("rate", rate);
    }
}
