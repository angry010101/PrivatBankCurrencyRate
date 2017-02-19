package com.example.s1.privat;

/**
 * Created by S1 on 2/18/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostModel {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("baseCurrency")
    @Expose
    private int baseCurrency;
    @SerializedName("baseCurrencyLit")
    @Expose
    private String baseCurrencyLit;
    @SerializedName("exchangeRate")
    @Expose
    private ArrayList<CurrencyModel> exchangeRate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBaseCurrency() {
        return baseCurrency;
    }

    public ArrayList<CurrencyModel> getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ArrayList<CurrencyModel> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankname() {
        return bank;
    }

    public String getBaseCurrencyLit() {
        return baseCurrencyLit;
    }

    public void setBankname(String bankname) {
        this.bank = bankname;
    }

    public void setBaseCurrency(int baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setBaseCurrencyLit(String baseCurrencyLit) {
        this.baseCurrencyLit = baseCurrencyLit;
    }
}
