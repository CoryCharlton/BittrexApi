package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Withdrawal;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetWithdrawalHistoryResponse extends Response<ArrayList<Withdrawal>> {
    private GetWithdrawalHistoryResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<Withdrawal> result() {
        return super.result();
    }
}
