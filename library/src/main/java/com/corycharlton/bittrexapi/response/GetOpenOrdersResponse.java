package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.OpenOrder;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetOpenOrdersResponse extends Response<ArrayList<OpenOrder>> {
    private GetOpenOrdersResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<OpenOrder> result() {
        return super.result();
    }
}
