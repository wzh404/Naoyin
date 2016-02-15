package com.xeehoo.health.presenter;

import android.content.Context;
import android.widget.Toast;

import com.xeehoo.health.activity.ChangePwdActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.view.ChangePwdView;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/28.
 */
public class ChangePwdPresenter extends ServicePresenter {
    private Context context;
    private ChangePwdView view;
    private String type;

    public void onCreate(Context context, ChangePwdView view, String type) {
        this.context = context;
        this.view = view;
        this.type = type;
        super.init(context);

        if ("login".equalsIgnoreCase(type)) {
            view.setTitle("修改登录密码");
            register("change_password", changePwdAction1);
        } else if ("pay".equalsIgnoreCase(type)) {
            view.setTitle("修改支付密码");
            register("change_pay_password", changePwdAction1);
        }
    }

    private Action1 changePwdAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            ChangePwdActivity changePwdActivity = (ChangePwdActivity) context;
            changePwdActivity.dismissProgressBar();
            Toast.makeText(context, result.getTag() + " - " + result.getCode() + " - " + result.getMsg(), Toast.LENGTH_SHORT).show();
            if (result.isResult("change_password", "OK")) {
                changePwdActivity.finish();
            } else if (result.isResult("change_pay_password", "OK")) {
                changePwdActivity.finish();
            }
        }
    };
}
