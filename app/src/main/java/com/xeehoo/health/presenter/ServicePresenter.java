package com.xeehoo.health.presenter;

import android.content.Context;

import com.google.gson.JsonObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.rxjava.action.ErrorAction1;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.action.ResultAction1;
import com.xeehoo.health.rxjava.rxbus.RxBus;
import com.xeehoo.health.share.bean.ShareService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by WIN10 on 2016/2/3.
 */
public class ServicePresenter {
    private ShareService shareService;
    private Map<String, Observable> map = new HashMap<String, Observable>();

    public void init(Context context){
        BrainApplication app = (BrainApplication)context.getApplicationContext();

        Retrofit retrofit = app.getRetrofit();
        shareService = retrofit.create(ShareService.class);
    }

    public void onDestroy() {
        for (Map.Entry<String, Observable> entry : map.entrySet()) {
            RxBus.get().unregister(entry.getKey(), entry.getValue());
        }
    }

    public void register(String tag, Action1 action1) {
        Observable<Result> observable = RxBus.get().register(tag, Result.class);
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(action1);

        map.put(tag, observable);
    }

    private void call(Observable<JsonObject> observable, String tag) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResultAction1(tag), new ErrorAction1(tag));
    }

    public void login(String name, String pwd) {
        Observable<JsonObject> observable = shareService.login(name, pwd);
        call(observable, "login");
    }

    public void resetPwd(String name, String sms, String pwd) {
        Observable<JsonObject> observable = shareService.resetPwd(name, sms, pwd);
        call(observable, "password");
    }

    public void register(String name, String sms, String pwd) {
        Observable<JsonObject> observable = shareService.register(name, sms, pwd);
        call(observable, "register");
    }

    public void changePwd(String oldPwd, String newPwd) {
        Observable<JsonObject> observable = shareService.changePwd(oldPwd, newPwd);
        call(observable, "change_password");
    }

    public void balance() {
        Observable<JsonObject> observable = shareService.balance();
        call(observable, "balance");
    }

    public void user() {
        Observable<JsonObject> observable = shareService.user();
        call(observable, "user");
    }

    public void product() {
        Observable<JsonObject> observable = shareService.products(BrainApplication.productId);
        call(observable, "product");
    }

    public void myProduct() {
        Observable<JsonObject> observable = shareService.myProduct(BrainApplication.investId);
        call(observable, "my_product");
    }
}
