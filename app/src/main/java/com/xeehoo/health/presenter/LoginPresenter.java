package com.xeehoo.health.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.common.view.IView;
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
 * Created by wangzunhui on 2016/1/28.
 */
public class LoginPresenter extends ServicePresenter  {
//    private ShareService shareService;
    private Context context;

//    private Map<String, Observable> map = new HashMap<String, Observable>();

    public void onCreate(Context context, IView view) {
        this.context = context;
        super.init(context);

        Log.e("Login", context.toString());
        if (context instanceof MainActivity){
            Log.e("Login", "----------Fragment---------");
        }
        else{
            Log.e("Login", "----------LoginActivity---------");
        }
//        LoginActivity loginActivity = (LoginActivity) context;
//        BrainApplication app = (BrainApplication) loginActivity.getApplication();
//        Retrofit retrofit = app.getRetrofit();
//        shareService = retrofit.create(ShareService.class);

        register("login", loginAction1);
//        register("password");
//        register("register");
//        register("change_password");
    }

    private Action1 loginAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            LoginActivity loginActivity = (LoginActivity) context;
            loginActivity.dismissProgressBar();
            Toast.makeText(context, result.getTag() + " - " + result.getCode() + " - " + result.getMsg(), Toast.LENGTH_SHORT).show();
            if (result.isResult("login", "OK")) {
                BrainApplication.token = result.getMsg();
                Log.e("token", BrainApplication.token);
                if (context instanceof MainActivity){
                    MainActivity ct = (MainActivity)context;
                    ct.change();
                }
                else{
                    Log.e("Login", "----------login ok  LoginActivity---------");
//                    LoginActivity loginActivity = (LoginActivity)context;
                    loginActivity.loginOK();
//                    loginActivity.dismissProgressBar();
                    loginActivity.finish();
                }
//                loginActivity.finish();
            }
        }
    };

//    private void register(String tag) {
//        Observable<Result> observable = RxBus.get().register(tag, Result.class);
//        observable.subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Result>() {
//                    @Override
//                    public void call(Result result) {
//                        LoginActivity loginActivity = (LoginActivity) context;
//                        loginActivity.dismissProgressBar();
//                        Toast.makeText(context, result.getTag() + " - " + result.getCode() + " - " + result.getMsg(), Toast.LENGTH_SHORT).show();
//                        if (result.isResult("login", "OK")) {
//                            BrainApplication.token = result.getMsg();
//                            Log.e("token", BrainApplication.token);
//                            loginActivity.finish();
//                        }
//                    }
//                });
//
//        map.put(tag, observable);
//    }

//    public void onDestroy() {
//        for (Map.Entry<String, Observable> entry : map.entrySet()) {
//            RxBus.get().unregister(entry.getKey(), entry.getValue());
//        }
//    }

//    private void call(Observable<JsonObject> observable, String tag) {
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ResultAction1(tag), new ErrorAction1(tag));
//    }

//    public void loginClick(String name, String pwd) {
//        Observable<JsonObject> observable = shareService.login(name, pwd);
//        call(observable, "login");
//    }
//
//    public void resetPwdClick(String name, String sms, String pwd) {
//        Observable<JsonObject> observable = shareService.resetPwd(name, sms, pwd);
//        call(observable, "password");
//    }
//
//    public void registerClick(String name, String sms, String pwd) {
//        Observable<JsonObject> observable = shareService.register(name, sms, pwd);
//        call(observable, "register");
//    }
//
//    public void changePwdClick(String oldPwd, String newPwd) {
//        Observable<JsonObject> observable = shareService.changePwd(oldPwd, newPwd);
//        call(observable, "change_password");
//    }
}
