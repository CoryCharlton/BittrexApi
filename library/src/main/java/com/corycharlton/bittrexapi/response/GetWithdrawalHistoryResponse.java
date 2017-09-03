package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.Withdrawal;

import java.util.ArrayList;
import java.util.List;

public class GetWithdrawalHistoryResponse extends Response<ArrayList<Withdrawal>> {
    private GetWithdrawalHistoryResponse() {} // Cannot be instantiated
}
