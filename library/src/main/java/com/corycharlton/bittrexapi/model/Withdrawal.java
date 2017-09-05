package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

@SuppressWarnings("unused")
public class Withdrawal {

    @SerializedName("Address") private String _address;
    @SerializedName("Amount") private double _amount;
    @SerializedName("Authorized") private boolean _authorized;
    @SerializedName("Canceled") private boolean _canceled;
    @SerializedName("Currency") private String _currency;
    @SerializedName("InvalidAddress") private boolean _invalidAddress;
    @SerializedName("Opened") private Date _opened;
    @SerializedName("PaymentUuid") private UUID _paymentUuid;
    @SerializedName("PendingPayment") private boolean _pendingPayment;
    @SerializedName("TxCost") private double _txCost;
    @SerializedName("TxId") private String _txId;

    private Withdrawal() {} // Cannot be instantiated

    public String address() {
        return _address;
    }

    public double amount() {
        return _amount;
    }

    public boolean authorized() {
        return _authorized;
    }

    public boolean canceled() {
        return _canceled;
    }

    public String currency() {
        return _currency;
    }

    public boolean invalidAddress() {
        return _invalidAddress;
    }

    public Date opened() {
        return _opened;
    }

    public UUID paymentUuid() {
        return _paymentUuid;
    }

    public boolean pendingPayment() {
        return _pendingPayment;
    }

    public double txCost() {
        return _txCost;
    }

    public String txId() {
        return _txId;
    }
}
