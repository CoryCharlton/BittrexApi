package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Order;

@SuppressWarnings("EmptyMethod")
public class GetOrderResponse extends Response<Order> {
    private GetOrderResponse() {} // Cannot be instantiated

    @Override
    public Order result() {
        return super.result();
    }
}
