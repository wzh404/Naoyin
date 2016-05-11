package com.xeehoo.health.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.xeehoo.health.util.CommonUtil;

import java.math.BigDecimal;

/**
 * Created by wangzunhui on 2016/5/11.
 */
public class Transfer implements Parcelable {
    @SerializedName("transferId")
    private Integer transferId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("transferDiscount")
    private BigDecimal discount;

    @SerializedName("transferAmount")
    private BigDecimal amount;

    @SerializedName("investDay")
    private String investDay;

    @SerializedName("rate")
    private BigDecimal rate;

    private String investUnit;

    private String amountTitle;

    public String getAmountTitle(){
        return "转让金额: " + this.amount.toPlainString().trim();
    }

    public String getInvestUnit() {
        return CommonUtil.getInvestUnit(this.investDay);
    }

    public void setInvestUnit(String investUnit) {
        this.investUnit = investUnit;
    }

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getInvestDay() {
        return CommonUtil.getInvestDue(investDay);
    }

    public void setInvestDay(String investDay) {
        this.investDay = investDay;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Transfer(){}
    protected Transfer(Parcel source) {
        this.transferId = source.readInt();
        this.productName = source.readString();
        this.discount = new BigDecimal(source.readString());
        this.amount = new BigDecimal(source.readString());
        this.investDay = source.readString();
        this.rate = new BigDecimal(source.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.transferId);
        dest.writeString(this.productName);
        dest.writeString(this.discount.toPlainString());
        dest.writeString(this.amount.toPlainString());
        dest.writeString(this.investDay);
        dest.writeString(this.rate.toPlainString());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Transfer> CREATOR = new Creator<Transfer>() {
        @Override
        public Transfer createFromParcel(Parcel in) {
            return new Transfer(in);
        }

        @Override
        public Transfer[] newArray(int size) {
            return new Transfer[size];
        }
    };
}
