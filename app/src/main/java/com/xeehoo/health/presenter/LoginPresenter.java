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
import com.xeehoo.health.view.LoginView;
import com.xeehoo.health.view.MyAccountItemView;

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
public class LoginPresenter extends ServicePresenter {
    private Context context;
    private LoginView view;

    public void onCreate(Context context, LoginView view) {
        this.context = context;
        this.view = view;
        super.init(context);

        register("login", loginAction1);
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

                BrainApplication.mobile = view.getMobile();
                BrainApplication.isLogin = true;

                Result r = new Result();
                r.setTag(MyAccountItemView.TAG_LOGIN);
                r.setCode("OK");
                RxBus.get().post(MyAccountItemView.TAG_LOGIN, r);

                loginActivity.finish();
            }
        }
    };
}
