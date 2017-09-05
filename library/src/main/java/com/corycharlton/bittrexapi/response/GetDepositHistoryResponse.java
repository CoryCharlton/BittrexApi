package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Deposit;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetDepositHistoryResponse extends Response<ArrayList<Deposit>> {
    private GetDepositHistoryResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<Deposit> result() {
        return super.result();
    }
}
