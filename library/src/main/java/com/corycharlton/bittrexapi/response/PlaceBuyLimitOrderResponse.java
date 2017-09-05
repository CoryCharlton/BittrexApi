package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.OrderId;

public class PlaceBuyLimitOrderResponse extends Response<OrderId> {
    private PlaceBuyLimitOrderResponse() {} // Cannot be instantiated

    @Override
    public OrderId result() {
        return super.result();
    }
}
