package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.DepositAddress;

@SuppressWarnings("EmptyMethod")
public class GetDepositAddressResponse extends Response<DepositAddress> {
    private GetDepositAddressResponse() {} // Cannot be instantiated

    @Override
    public DepositAddress result() {
        return super.result();
    }
}
