package com.corycharlton.bittrexapi.request;

import android.support.annotation.Nullable;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetWithdrawalHistoryResponse;

/**
 * Used to retrieve your withdrawal history.
 */
public class GetWithdrawalHistoryRequest extends Request<GetWithdrawalHistoryResponse> {

    /**
     * Used to retrieve your withdrawal history for all currencies.
     */
    public GetWithdrawalHistoryRequest() {
        this(null);
    }

    /**
     * Used to retrieve your withdrawal history.
     * @param currency An optional string literal for the currency (ie. BTC). If omitted, will return for all currencies
     */
    public GetWithdrawalHistoryRequest(@Nullable String currency) {
        super(Url.GETWITHDRAWALHISTORY, true, GetWithdrawalHistoryResponse.class);

        if (!StringUtils.isNullOrWhiteSpace(currency)) {
            addParameter("currency", currency);
        }
    }
}
