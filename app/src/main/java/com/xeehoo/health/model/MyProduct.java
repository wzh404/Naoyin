package com.xeehoo.health.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProduct implements Parcelable {
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

    @SerializedName("invest_rate")
    private BigDecimal investRate;

    @SerializedName("invest_start_date")
    private String investStartDate;

    @SerializedName("invest_closing_date")
    private String investClosingDate;

    @SerializedName("transfer_status")
    private String transferStatus;

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

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public BigDecimal getInvestRate() {
        return investRate;
    }

    public void setInvestRate(BigDecimal investRate) {
        this.investRate = investRate;
    }

    public String getInvestStartDate() {
        return investStartDate;
    }

    public void setInvestStartDate(String investStartDate) {
        this.investStartDate = investStartDate;
    }

    public String getInvestClosingDate() {
        return investClosingDate;
    }

    public void setInvestClosingDate(String investClosingDate) {
        this.investClosingDate = investClosingDate;
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


    public MyProduct(){}
    public MyProduct(Parcel source){
        this.investId = source.readInt();
        this.productName = source.readString();
        this.investAmount = new BigDecimal(source.readString());
        this.investTime = source.readString();
        this.investIncome = new BigDecimal(source.readString());
        this.investStatus = source.readString();
        this.investRate = new BigDecimal(source.readString());
        this.investStartDate = source.readString();
        this.investClosingDate = source.readString();
        this.transferStatus = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.investId);
        dest.writeString(this.productName);
        dest.writeString(this.investAmount.toPlainString());
        dest.writeString(this.investTime);
        dest.writeString(this.investIncome.toPlainString());
        dest.writeString(this.investStatus);
        dest.writeString(this.investRate.toPlainString());
        dest.writeString(this.investStartDate);
        dest.writeString(this.investClosingDate);
        dest.writeString(this.transferStatus);
    }

    public static final Parcelable.Creator<MyProduct> CREATOR = new  Parcelable.Creator<MyProduct>(){

        @Override
        public MyProduct createFromParcel(Parcel source) {
            return new MyProduct(source);
        }

        @Override
        public MyProduct[] newArray(int size) {
            return new MyProduct[size];
        }
    };
}
