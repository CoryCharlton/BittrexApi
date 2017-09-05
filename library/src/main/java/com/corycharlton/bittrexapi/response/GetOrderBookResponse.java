package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.OrderBook;

public class GetOrderBookResponse extends Response<OrderBook> {
    private GetOrderBookResponse() {} // Cannot be instantiated

    @Override
    public OrderBook result() {
        return super.result();
    }
}
