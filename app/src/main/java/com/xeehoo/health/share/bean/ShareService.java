package com.xeehoo.health.share.bean;

import com.google.gson.JsonObject;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wangzunhui on 2015/7/25.
 */
public interface ShareService {
    @GET("/app/login")
    Observable<JsonObject> login(@Query("name") String name, @Query("pwd") String pwd);

    @GET("/app/changePwd")
    Observable<JsonObject> changePwd(@Query("old_pwd") String oldPwd, @Query("new_pwd") String newPwd);

    @GET("/app/resetPwd")
    Observable<JsonObject> resetPwd(@Query("mobile") String mobile, @Query("sms") String sms, @Query("pwd") String pwd);

    @GET("/app/changePayPwd")
    Observable<JsonObject> changePayPwd(@Query("old_pwd") String oldPwd, @Query("new_pwd") String newPwd);

    @GET("/app/setPayPwd")
    Observable<JsonObject> setPayPwd(@Query("mobile") String mobile, @Query("sms") String sms, @Query("pwd") String pwd);

    @GET("/app/register")
    Observable<JsonObject> register(@Query("mobile") String mobile, @Query("sms") String sms, @Query("pwd") String pwd, @Query("invite") String invite);

    @GET("/app/fuiou/balance")
    Observable<JsonObject> balance();

    @GET("/app/fuiou/user")
    Observable<JsonObject> user();

    @GET("/app/user/investments")
    Observable<JsonObject> myProduct(@Query("invest_id") Integer investId);

    @GET("/app/product")
    Observable<JsonObject> products(@Query("product_id") Integer productId);

    @GET("/app/mobile")
    Observable<JsonObject> mobile(@Query("mobile") String mobile);

    @GET("/app/invest")
    Observable<JsonObject> invest(@Query("product_id") Integer productId, @Query("amount")Integer amount, @Query("pwd")String pwd);
}
