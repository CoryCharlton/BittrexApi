package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Uuid;

@SuppressWarnings("EmptyMethod")
public class WithdrawResponse extends Response<Uuid> {
    private WithdrawResponse() {} // Cannot be instantiated

    @Override
    public Uuid result() {
        return super.result();
    }
}
