package com.xeehoo.health.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProduct {
    @SerializedName("product_name")
    private String productName;

    @SerializedName("invest_time")
    private String investTime;

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
}
