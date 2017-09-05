package com.corycharlton.bittrexapi.response;

import com.corycharlton.bittrexapi.data.MarketSummary;

import java.util.ArrayList;

// TODO: Not sure I like the list where there is technically only one object
// Consider implementing a type adapter like this: https://stackoverflow.com/questions/7668507/gson-handle-object-or-array
public class GetMarketSummaryResponse extends Response<ArrayList<MarketSummary>> {
    private GetMarketSummaryResponse() {} // Cannot be instantiated
}
