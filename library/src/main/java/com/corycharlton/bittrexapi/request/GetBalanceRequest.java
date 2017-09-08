package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;

/**
 * Used to retrieve the total from your account for a specific currency.
 */
public class GetBalanceRequest extends Request<GetBalanceResponse> {

    /**
     * Used to retrieve the total from your account for a specific currency.
     * @param currency string literal for the currency (ex: LTC)
     */
    public GetBalanceRequest(@NonNull String currency) {
        super(Url.GETBALANCE, true, GetBalanceResponse.class);

        isNotNullOrWhitespace("currency", currency);
        addParameter("currency", currency);
    }
}
