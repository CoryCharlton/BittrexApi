package com.corycharlton.bittrexapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

@SuppressWarnings("unused")
public class OpenOrder {

    @SerializedName("CancelInitiated") private boolean _cancelInitiated;
    @SerializedName("Closed") private Date _closed;
    @SerializedName("CommissionPaid") private double _commissionPaid;
    @SerializedName("Condition") private String _condition;
    @SerializedName("ConditionTarget") private double _conditionTarget;
    @SerializedName("Exchange") private String _exchange;
    @SerializedName("ImmediateOrCancel") private boolean _immediateOrCancel;
    @SerializedName("IsConditional") private boolean _isConditional;
    @SerializedName("Limit") private double _limit;
    @SerializedName("Opened") private Date _opened;
    @SerializedName("OrderType") private String _orderType;
    @SerializedName("OrderUuid") private UUID _orderUuid;
    @SerializedName("Price") private double _price;
    @SerializedName("PricePerUnit") private double _pricePerUnit;
    @SerializedName("Quantity") private double _quantity;
    @SerializedName("QuantityRemaining") private double _quantityRemaining;
    @SerializedName("Uuid") private UUID _uuid;

    private OpenOrder() {} // Cannot be instantiated

    public boolean cancelInitiated() {
        return _cancelInitiated;
    }

    public Date closed() {
        return _closed;
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

    public double limit() {
        return _limit;
    }

    public Date opened() {
        return _opened;
    }

    public String orderType() {
        return _orderType;
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

    public UUID uuid() {
        return _uuid;
    }
}
