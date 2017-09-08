package com.corycharlton.bittrexapi.request;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.corycharlton.bittrexapi.internal.constants.Url;
import com.corycharlton.bittrexapi.internal.util.StringUtils;
import com.corycharlton.bittrexapi.response.WithdrawResponse;

import static com.corycharlton.bittrexapi.internal.util.Ensure.isNotNullOrWhitespace;
import static com.corycharlton.bittrexapi.internal.util.Ensure.isTrue;

/**
 * Used to withdraw funds from your account.
 */
public class WithdrawRequest extends Request<WithdrawResponse> {

    /**
     * Used to withdraw funds from your account.
     * @param currency A string literal for the currency (ie. BTC)
     * @param quantity The quantity of coins to withdraw
     * @param address The address where to send the funds
     */
    public WithdrawRequest(@NonNull String currency, double quantity, @NonNull String address) {
        this(currency, quantity, address, null);
    }

    /**
     * Used to withdraw funds from your account.
     * @param currency A string literal for the currency (ie. BTC)
     * @param quantity The quantity of coins to withdraw
     * @param address The address where to send the funds
     * @param paymentId An optional string used for CryptoNotes/BitShareX/Nxt optional field (memo/paymentid)
     */
    public WithdrawRequest(@NonNull String currency, double quantity, @NonNull String address, @Nullable String paymentId) {
        super(Url.WITHDRAW, true, WithdrawResponse.class);

        isNotNullOrWhitespace("address", address);
        isNotNullOrWhitespace("currency", currency);
        isTrue("quantity", quantity > 0.0);

        addParameter("address", address);
        addParameter("currency", currency);
        addParameter("quantity", quantity);

        if (!StringUtils.isNullOrWhiteSpace(paymentId)) {
            addParameter("paymentid", paymentId);
        }
    }
}
