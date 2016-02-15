package com.xeehoo.health.presenter;

import android.content.Context;
import android.widget.Toast;

import com.xeehoo.health.activity.RegisterActivity;
import com.xeehoo.health.activity.ResetPwdActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.view.RegisterView;
import com.xeehoo.health.view.ResetPwdView;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/28.
 */
public class ResetPwdPresenter extends ServicePresenter {
    private Context context;
    private ResetPwdView view;
    private String type;

    public void onCreate(Context context, ResetPwdView view, String type) {
        this.context = context;
        this.view = view;
        this.type = type;
        super.init(context);

        if ("login".equalsIgnoreCase(type)){
            view.setTitle("设置登录密码");
        }
        else if ("pay".equalsIgnoreCase(type)){
            view.setTitle("设置支付密码");
            view.setPay();
        }

        register("set_pwd", resetPwdAction1);
    }

    private Action1 resetPwdAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            ResetPwdActivity resetPwdActivity = (ResetPwdActivity) context;
            resetPwdActivity.dismissProgressBar();
            Toast.makeText(context, result.getTag() + " - " + result.getCode() + " - " + result.getMsg(), Toast.LENGTH_SHORT).show();
            if (result.isResult("set_pwd", "OK")) {

            }
        }
    };
}
