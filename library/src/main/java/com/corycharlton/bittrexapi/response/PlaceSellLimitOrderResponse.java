package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.OrderId;

@SuppressWarnings("EmptyMethod")
public class PlaceSellLimitOrderResponse extends Response<OrderId> {
    private PlaceSellLimitOrderResponse() {} // Cannot be instantiated

    @Override
    public OrderId result() {
        return super.result();
    }
}
