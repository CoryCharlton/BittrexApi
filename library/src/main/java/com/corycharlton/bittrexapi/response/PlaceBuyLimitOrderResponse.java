package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.OrderId;

@SuppressWarnings("EmptyMethod")
public class PlaceBuyLimitOrderResponse extends Response<OrderId> {
    private PlaceBuyLimitOrderResponse() {} // Cannot be instantiated

    @Override
    public OrderId result() {
        return super.result();
    }
}
