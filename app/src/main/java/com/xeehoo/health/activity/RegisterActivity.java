package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xeehoo.health.presenter.RegisterPresenter;
import com.xeehoo.health.util.ValidateUtil;
import com.xeehoo.health.view.RegisterView;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class RegisterActivity extends Activity {
    private RegisterView registerView;
    private SweetAlertDialog dialog;
    private RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerView = new RegisterView(this, null);
        setContentView(registerView.getView());
        registerPresenter = new RegisterPresenter();
        registerPresenter.onCreate(this, registerView);

        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
    }

    public void mobileSmsOnClick(View view){
        if (!ValidateUtil.isMobileNO(registerView.getAccount())){
            Toast.makeText(this, "手机号不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("".equalsIgnoreCase(registerView.getPwd()) || registerView.getPwd().length() < 6){
            Toast.makeText(this, "密码不能为空且长度大于6位!", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();
        registerPresenter.mobile(registerView.getAccount());
    }

    public void sendMobileSms(){
        Intent saveIntent = new Intent(RegisterActivity.this, MobileSmsActivity.class);
        saveIntent.putExtra("type", "register");
        saveIntent.putExtra("para1", registerView.getAccount());
        saveIntent.putExtra("para2", registerView.getPwd());
        startActivity(saveIntent);
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void dismissProgressBar(){
        dialog.dismiss();
    }

}
