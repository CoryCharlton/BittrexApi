package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Balance;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetBalancesResponse extends Response<ArrayList<Balance>> {
    private GetBalancesResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<Balance> result() {
        return super.result();
    }
}
