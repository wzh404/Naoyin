package com.xeehoo.health.model;

import com.google.gson.annotations.SerializedName;
import com.xeehoo.health.util.CommonUtil;

import java.math.BigDecimal;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class Product {
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
}
