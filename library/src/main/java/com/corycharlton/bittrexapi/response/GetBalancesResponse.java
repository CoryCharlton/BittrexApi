package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.Balance;
import com.corycharlton.bittrexapi.data.Currency;

import java.util.ArrayList;
import java.util.List;

public class GetBalancesResponse extends Response<ArrayList<Balance>> {
    private GetBalancesResponse() {} // Cannot be instantiated
}
