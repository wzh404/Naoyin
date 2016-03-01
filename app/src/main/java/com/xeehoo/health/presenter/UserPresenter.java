package com.xeehoo.health.presenter;

import android.content.Context;
import android.graphics.Color;
import android.text.style.TtsSpan;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.activity.PayActivity;
import com.xeehoo.health.activity.UserActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.util.CommonUtil;
import com.xeehoo.health.util.MoneyUtil;
import com.xeehoo.health.view.PayView;
import com.xeehoo.health.view.UserView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/2/6.
 */
public class UserPresenter extends ServicePresenter {
    private Context context;
    private SweetAlertDialog dialog;
    private UserView userView;

    public void onCreate(Context context, UserView view) {
        this.context = context;
        this.userView = view;
        super.init(context);

        dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);

        dialog.show();
        register("user", userAction1);
        user();
    }

    private Action1 userAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            dialog.dismiss();
            if (result.isResult("user", "OK")) {
                JsonObject jsonObject = result.getObj();
                userView.setUserName(jsonObject.get("name").getAsString());
                userView.setUserMobile(BrainApplication.mobile);
                userView.setUserCt(CommonUtil.getMoney2(jsonObject.get("ct").getAsString()));
                userView.setUserCa(CommonUtil.getMoney2(jsonObject.get("ca").getAsString()));
                userView.setUserCf(CommonUtil.getMoney2(jsonObject.get("cf").getAsString()));
                userView.setUserCu(CommonUtil.getMoney2(jsonObject.get("cu").getAsString()));
                BrainApplication.isAccount = true;
                userView.showRecharge();
            }
            else{
                Toast.makeText(context,  result.getMsg(), Toast.LENGTH_SHORT).show();
                userView.setUserName("没有实名认证");
                userView.setUserMobile(BrainApplication.mobile);
                userView.setUserCt("0.00");
                userView.setUserCa("0.00");
                userView.setUserCf("0.00");
                userView.setUserCu("0.00");
                userView.showRegister();

                BrainApplication.isAccount = false;
            }
        }
    };
}
