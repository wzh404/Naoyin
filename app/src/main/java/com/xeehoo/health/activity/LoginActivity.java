package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xeehoo.health.util.ValidateUtil;
import com.xeehoo.health.view.LoginView;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.presenter.LoginPresenter;
import com.xeehoo.health.util.CircleActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends Activity {
    private LoginPresenter loginPresenter;
    private SweetAlertDialog dialog;
    private LoginView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.loginPresenter = new LoginPresenter();
        loginView = new LoginView(this, null);
        setContentView(loginView.getView());
        loginPresenter.onCreate(this, loginView);

        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("登录中...");
        dialog.setCancelable(false);
    }

    public void loginOnClick(View view) {
        if (!ValidateUtil.isMobileNO(loginView.getMobile())){
            Toast.makeText(this, "手机号不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("".equalsIgnoreCase(loginView.getPwd()) || loginView.getPwd().length() < 6){
            Toast.makeText(this, "密码不能为空且长度大于6位!", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();
        loginPresenter.login(loginView.getMobile(), loginView.getPwd());
    }

    public void dismissProgressBar(){
        dialog.dismiss();
    }

    public void resetPwdOnClick(View view) {
        Intent saveIntent = new Intent(LoginActivity.this, ResetPwdActivity.class);
        saveIntent.putExtra("type", "login");
        startActivity(saveIntent);
    }

    public void registerOnClick(View view) {
        Intent saveIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(saveIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }

    public void exitOnClick(View view) {
        this.finish();
    }
}
