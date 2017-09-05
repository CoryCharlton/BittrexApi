package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Balance;

public class GetBalanceResponse extends Response<Balance> {
    private GetBalanceResponse() {} // Cannot be instantiated

    @Override
    public Balance result() {
        return super.result();
    }
}
