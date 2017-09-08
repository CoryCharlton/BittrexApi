package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetDepositAddressResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.*;

/**
 * Used to retrieve or generate an address for a specific currency. If one does not exist, the call will fail and return ADDRESS_GENERATING until one is available.
 */
public class GetDepositAddressRequest extends Request<GetDepositAddressResponse> {

    /**
     * Used to retrieve or generate an address for a specific currency. If one does not exist, the call will fail and return ADDRESS_GENERATING until one is available.
     * @param currency A string literal for the currency (ie. BTC)
     */
    public GetDepositAddressRequest(@NonNull String currency) {
        super(Url.GETDEPOSITADDRESS, true, GetDepositAddressResponse.class);

        isNotNullOrWhitespace("currency", currency);
        addParameter("currency", currency);
    }
}
