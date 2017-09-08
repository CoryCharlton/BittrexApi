package com.corycharlton.bittrexapi.request;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.response.GetBalancesResponse;

/**
 * Used to retrieve all balances from your account.
 */
public class GetBalancesRequest extends Request<GetBalancesResponse> {

    /**
     * Used to retrieve all balances from your account.
     */
    public GetBalancesRequest() {
        super(Url.GETBALANCES, true, GetBalancesResponse.class);
    }
}
