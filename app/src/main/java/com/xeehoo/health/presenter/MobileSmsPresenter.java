package com.xeehoo.health.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.MobileSmsActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.rxbus.RxBus;
import com.xeehoo.health.view.LoginView;
import com.xeehoo.health.view.MobileSmsView;
import com.xeehoo.health.view.MyAccountItemView;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/28.
 */
public class MobileSmsPresenter extends ServicePresenter {
    private Context context;
    private MobileSmsView view;
    private String type;

    public void onCreate(Context context, MobileSmsView view, String type) {
        this.context = context;
        this.view = view;
        this.type = type;
        super.init(context);

        if ("register".equalsIgnoreCase(type)){
            register(type, registerAction1);
        }
        else if ("login".equalsIgnoreCase(type)){
            register("password", registerAction1);
        }
        else if ("pay".equalsIgnoreCase(type)){
            register("pay_password", registerAction1);
        }
    }

    private Action1 registerAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            MobileSmsActivity mobileSmsActivity = (MobileSmsActivity) context;
            mobileSmsActivity.dismissProgressBar();
            Toast.makeText(context, result.getTag() + " - " + result.getCode() + " - " + result.getMsg(), Toast.LENGTH_SHORT).show();
            if (result.isResult("register", "OK")) {
                mobileSmsActivity.finish();
            }
            else if (result.isResult("pay_password", "OK")) {
                mobileSmsActivity.finish();
            }
            else if (result.isResult("password", "OK")) {
                mobileSmsActivity.finish();
            }
        }
    };
}
