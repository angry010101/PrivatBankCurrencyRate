package com.example.s1.privat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by S1 on 2/19/2017.
 */
public class CurrencyModel {
    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("saleRateNB")
    @Expose
    private double saleRateNb;

    @SerializedName("purchaseRateNB")
    @Expose
    private double purchaseRateNb;

    @SerializedName("saleRate")
    @Expose
    private double saleRate;

    @SerializedName("purchaseRate")
    @Expose
    private double purchaseRate;

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getCurrency() {
        return currency;
    }

    public void setSaleRateNb(double saleRateNb) {
        this.saleRateNb = saleRateNb;
    }

    public void setSaleRate(double saleRate) {
        this.saleRate = saleRate;
    }

    public void setPurchaseRateNb(double purchaseRateNb) {
        this.purchaseRateNb = purchaseRateNb;
    }

    public double getPurchaseRate() {
        return purchaseRate;
    }

    public double getPurchaseRateNb() {
        return purchaseRateNb;
    }

    public double getSaleRate() {
        return saleRate;
    }

    public double getSaleRateNb() {
        return saleRateNb;
    }

    public void setPurchaseRate(double purchaseRate) {
        this.purchaseRate = purchaseRate;
    }
}
