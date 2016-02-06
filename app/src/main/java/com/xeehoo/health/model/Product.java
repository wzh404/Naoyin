package com.xeehoo.health.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.xeehoo.health.util.CommonUtil;

import java.math.BigDecimal;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class Product implements Parcelable {
    @SerializedName("product_id")
    private Integer productId;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("release_time")
    private String releaseTime;

    @SerializedName("loan_rate")
    private String loanRate;

    @SerializedName("invest_day")
    private String investDay;

    @SerializedName("residual_amount")
    private BigDecimal residualAmount;

    @SerializedName("total_amount")
    private BigDecimal totalAmount;

    private String investUnit;

    public String getInvestUnit() {
        return CommonUtil.getInvestUnit(this.investDay);
    }

    public void setInvestUnit(String investUnit) {
        this.investUnit = investUnit;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(String loanRate) {
        this.loanRate = loanRate;
    }

    public String getInvestDay() {
        return CommonUtil.getInvestDue(investDay);
    }

    public void setInvestDay(String investDay) {
        this.investDay = investDay;
    }

    public BigDecimal getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(BigDecimal residualAmount) {
        this.residualAmount = residualAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Product(){}
    public Product(Parcel source){
        this.productId = source.readInt();
        this.productName = source.readString();
        this.residualAmount = new BigDecimal(source.readString());
        this.totalAmount = new BigDecimal(source.readString());
        this.investDay = source.readString();
        this.loanRate = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.productId);
        dest.writeString(this.productName);
        dest.writeString(this.residualAmount.toPlainString());
        dest.writeString(this.totalAmount.toPlainString());
        dest.writeString(this.investDay);
        dest.writeString(this.loanRate);
    }

    public static final Parcelable.Creator<Product> CREATOR = new  Parcelable.Creator<Product>(){

        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
