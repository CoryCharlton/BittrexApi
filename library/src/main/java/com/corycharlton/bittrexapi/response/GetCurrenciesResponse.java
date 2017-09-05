package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.model.Currency;

import java.util.ArrayList;

@SuppressWarnings("EmptyMethod")
public class GetCurrenciesResponse extends Response<ArrayList<Currency>> {
    private GetCurrenciesResponse() {} // Cannot be instantiated

    @Override
    public ArrayList<Currency> result() {
        return super.result();
    }
}
