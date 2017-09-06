package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

@SuppressWarnings("unused")
public class Uuid {

    @SerializedName("uuid") private UUID _uuid;

    private Uuid() {} // Cannot be instantiated

    public UUID uuid() {
        return _uuid;
    }

}
