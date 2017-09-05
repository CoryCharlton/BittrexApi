package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

@SuppressWarnings("unused")
public class Order {

    @SerializedName("AccountId") private String _accountId;
    @SerializedName("CancelInitiated") private boolean _cancelInitiated;
    @SerializedName("Closed") private Date _closed;
    @SerializedName("CommissionReserved") private double _commissionReserved;
    @SerializedName("CommissionReserveRemaining") private double _commissionReservedRemaining;
    @SerializedName("CommissionPaid") private double _commissionPaid;
    @SerializedName("Condition") private String _condition;
    @SerializedName("ConditionTarget") private double _conditionTarget;
    @SerializedName("Exchange") private String _exchange;
    @SerializedName("ImmediateOrCancel") private boolean _immediateOrCancel;
    @SerializedName("IsConditional") private boolean _isConditional;
    @SerializedName("IsOpen") private boolean _isOpen;
    @SerializedName("Limit") private double _limit;
    @SerializedName("Opened") private Date _opened;
    @SerializedName("OrderUuid") private UUID _orderUuid;
    @SerializedName("Price") private double _price;
    @SerializedName("PricePerUnit") private double _pricePerUnit;
    @SerializedName("Quantity") private double _quantity;
    @SerializedName("QuantityRemaining") private double _quantityRemaining;
    @SerializedName("Reserved") private double _reserved;
    @SerializedName("ReserveRemaining") private double _reservedRemaining;
    @SerializedName("Sentinel") private UUID _sentinel;
    @SerializedName("Type") private String _type;

    private Order() {} // Cannot be instantiated

    public String accountId() {
        return _accountId;
    }

    public boolean cancelInitiated() {
        return _cancelInitiated;
    }

    public Date closed() {
        return _closed;
    }

    public double commissionReserved() {
        return _commissionReserved;
    }

    public double commissionReservedRemaining() {
        return _commissionReservedRemaining;
    }

    public double commissionPaid() {
        return _commissionPaid;
    }

    public String condition() {
        return _condition;
    }

    public double conditionTarget() {
        return _conditionTarget;
    }

    public String exchange() {
        return _exchange;
    }

    public boolean immediateOrCancel() {
        return _immediateOrCancel;
    }

    public boolean isConditional() {
        return _isConditional;
    }

    public boolean isOpen() {
        return _isOpen;
    }

    public double limit() {
        return _limit;
    }

    public Date opened() {
        return _opened;
    }

    public UUID orderUuid() {
        return _orderUuid;
    }

    public double price() {
        return _price;
    }

    public double pricePerUnit() {
        return _pricePerUnit;
    }

    public double quantity() {
        return _quantity;
    }

    public double quantityRemaining() {
        return _quantityRemaining;
    }

    public double reserved() {
        return _reserved;
    }

    public double reservedRemaining() {
        return _reservedRemaining;
    }

    public UUID sentinel() {
        return _sentinel;
    }

    public String type() {
        return _type;
    }
}
