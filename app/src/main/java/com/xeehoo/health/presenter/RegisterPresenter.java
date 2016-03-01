package com.xeehoo.health.presenter;

import android.content.Context;
import android.widget.Toast;

import com.xeehoo.health.activity.MobileSmsActivity;
import com.xeehoo.health.activity.RegisterActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.view.MobileSmsView;
import com.xeehoo.health.view.RegisterView;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/28.
 */
public class RegisterPresenter extends ServicePresenter {
    private Context context;
    private RegisterView view;

    public void onCreate(Context context, RegisterView view) {
        this.context = context;
        this.view = view;
        super.init(context);

        register("mobile", mobileAction1);
    }

    private Action1 mobileAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            RegisterActivity registerActivity = (RegisterActivity) context;
            registerActivity.dismissProgressBar();
            if (result.isResult("mobile", "ER18")) { // 号码不存在
                registerActivity.sendMobileSms();
            } else {
                Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
