package com.xeehoo.health.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProduct {
    @SerializedName("invest_id")
    private Integer investId;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("invest_time")
    private String investTime;

    @SerializedName("invest_amount")
    private BigDecimal investAmount;

    @SerializedName("invest_income")
    private BigDecimal investIncome;

    @SerializedName("invest_status")
    private String investStatus;

    private String amount;
    private String income;

    public String getAmount() {
//        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        DecimalFormat format = new DecimalFormat(",###.##");
        if (investAmount.compareTo(new BigDecimal(10000.0)) == 1){
            double amt = investAmount.divide(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_DOWN).doubleValue();

            return format.format(amt) + "万";
        }
        else{
            double amt = investAmount.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
            return format.format(amt);
        }
    }

    public String getIncome(){
//        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        DecimalFormat format = new DecimalFormat(",###.##");
        double amt = investIncome.setScale(2,BigDecimal.ROUND_DOWN).doubleValue();
        return format.format(amt);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getInvestTime() {
        return investTime;
    }

    public void setInvestTime(String investTime) {
        this.investTime = investTime;
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getInvestIncome() {
        return investIncome;
    }

    public void setInvestIncome(BigDecimal investIncome) {
        this.investIncome = investIncome;
    }

    public String getInvestStatus() {
        return investStatus;
    }

    public void setInvestStatus(String investStatus) {
        this.investStatus = investStatus;
    }
}
