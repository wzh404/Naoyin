package com.xeehoo.health.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xeehoo.health.presenter.MobileSmsPresenter;
import com.xeehoo.health.view.MobileSmsView;
import com.xeehoo.health.view.RegisterView;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class MobileSmsActivity extends Activity {
    private MobileSmsView mobileSmsView;
    private MobileSmsPresenter mobileSmsPresenter;
    private String type;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getStringExtra("type");
        mobileSmsView = new MobileSmsView(this, null);
        setContentView(mobileSmsView.getView());
        mobileSmsPresenter = new MobileSmsPresenter();
        mobileSmsPresenter.onCreate(this, mobileSmsView, type);

        mobileSmsView.setMobile(getIntent().getStringExtra("para1"));

        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mobileSmsPresenter.onDestroy();
    }

    public void dismissProgressBar(){
        dialog.dismiss();
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void smsSubmitOnClick(View view){

        String sms = mobileSmsView.getSmsCode();
        if ("".equalsIgnoreCase(sms) || sms.length() != 6){
            Toast.makeText(this, "请输入6位验证码!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.e("Tag", "type is " + type);
        dialog.show();
        if ("register".equalsIgnoreCase(type)){
            String para1 = getIntent().getStringExtra("para1"); // 手机号
            String para2 = getIntent().getStringExtra("para2"); // 密码
            String para3 = getIntent().getStringExtra("para3"); // 邀请码
            mobileSmsPresenter.register(para1, sms, para2, "18611330404");
        }
        else if ("pay".equalsIgnoreCase(type)){
            String para1 = getIntent().getStringExtra("para1"); // 手机号
            String para2 = getIntent().getStringExtra("para2"); // 密码
            mobileSmsPresenter.resetPayPwd(para1, sms, para2);
        }
        else if ("login".equalsIgnoreCase(type)){
            String para1 = getIntent().getStringExtra("para1"); // 手机号
            String para2 = getIntent().getStringExtra("para2"); // 密码
            mobileSmsPresenter.resetPwd(para1, sms, para2);
        }
    }
}
