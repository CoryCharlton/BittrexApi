package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.Deposit;
import com.corycharlton.bittrexapi.data.Withdrawal;

import java.util.ArrayList;
import java.util.List;

public class GetDepositHistoryResponse extends Response<ArrayList<Deposit>> {
    private GetDepositHistoryResponse() {} // Cannot be instantiated
}
