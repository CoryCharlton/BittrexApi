package com.corycharlton.bittrexapi.request;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetCurrenciesResponse;

/**
 * Used to get all supported currencies at Bittrex along with other meta data.
 */
public class GetCurrenciesRequest extends Request<GetCurrenciesResponse> {

    /**
     * Used to get all supported currencies at Bittrex along with other meta data.
     */
    public GetCurrenciesRequest() {
        super(Url.GETCURRENCIES, false, GetCurrenciesResponse.class);
    }
}
