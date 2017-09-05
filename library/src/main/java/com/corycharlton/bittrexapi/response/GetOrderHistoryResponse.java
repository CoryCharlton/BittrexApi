package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.OrderHistory;

import java.util.ArrayList;

public class GetOrderHistoryResponse extends Response<ArrayList<OrderHistory>> {
    private GetOrderHistoryResponse() {} // Cannot be instantiated
}
