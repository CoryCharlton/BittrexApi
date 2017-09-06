package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Uuid;

@SuppressWarnings("EmptyMethod")
public class PlaceBuyLimitOrderResponse extends Response<Uuid> {
    private PlaceBuyLimitOrderResponse() {} // Cannot be instantiated

    @Override
    public Uuid result() {
        return super.result();
    }
}
