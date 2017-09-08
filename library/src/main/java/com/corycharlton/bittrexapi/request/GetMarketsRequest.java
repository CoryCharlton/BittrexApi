package com.corycharlton.bittrexapi.request;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetMarketsResponse;

/**
 * Used to get the open and available trading markets at Bittrex along with other meta data.
 */
public class GetMarketsRequest extends Request<GetMarketsResponse> {

    /**
     * Used to get the open and available trading markets at Bittrex along with other meta data.
     */
    public GetMarketsRequest() {
        super(Url.GETMARKETS, false, GetMarketsResponse.class);
    }
}
