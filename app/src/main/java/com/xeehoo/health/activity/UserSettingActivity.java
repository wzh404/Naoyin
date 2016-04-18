package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.view.UserSettingView;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wangzunhui on 2016/2/3.
 */
public class UserSettingActivity extends Activity {
    private UserSettingView userSettingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSettingView = new UserSettingView(this, null);
        setContentView(userSettingView.getView());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void logoutOnClick(View view){
        final UserSettingActivity activity = this;

        if (BrainApplication.isLogin) {
            new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("确定退出吗?")
                    .setContentText("退出后，不能进行投资、提现及充值功能!")
                    .setConfirmText("确定")
                    .showCancelButton(true)
                    .setCancelText("取消")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                            BrainApplication.isLogin = false;
                            BrainApplication.token = null;
                            BrainApplication.investId = 0;
                            BrainApplication.isAccount = false;
                            BrainApplication.productId = 0;
                            BrainApplication.mobile = null;

                            AssetsUtils.clearParas(activity);
                            activity.setResult(10);
                            activity.finish();
                        }
                    })
                    .show();
        }
    }
}

