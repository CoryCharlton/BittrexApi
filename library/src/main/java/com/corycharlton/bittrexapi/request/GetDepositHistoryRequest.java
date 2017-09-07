package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.GetBalanceResponse;
import com.corycharlton.bittrexapi.response.GetDepositHistoryResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;

/**
 * Used to retrieve your deposit history.
 */
public class GetDepositHistoryRequest extends Request<GetDepositHistoryResponse> {

    /**
     * Used to retrieve your deposit history for all currencies.
     */
    public GetDepositHistoryRequest() {
        this(null);
    }

    /**
     * Used to retrieve your deposit history.
     * @param currency An optional string literal for the currency (ie. BTC). If omitted, will return for all currencies
     */
    public GetDepositHistoryRequest(@Nullable String currency) {
        super(Url.GETDEPOSITHISTORY, true, GetDepositHistoryResponse.class);

        if (!StringUtils.isNullOrWhiteSpace(currency)) {
            addParameter("currency", currency);
        }
    }
}
