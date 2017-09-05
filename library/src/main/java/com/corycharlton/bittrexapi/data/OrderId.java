package com.corycharlton.bittrexapi.data;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

@SuppressWarnings("unused")
public class OrderId {

    @SerializedName("uuid") private UUID _uuid;

    private OrderId() {} // Cannot be instantiated

    public UUID uuid() {
        return _uuid;
    }

}
