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
    private SweetAlertDialog pDialog;
    private LoginView loginView;

//    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.loginPresenter = new LoginPresenter();

        loginView = new LoginView(this, null);
        setContentView(loginView.getView());
        loginPresenter.onCreate(this, loginView);

        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
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

        pDialog.show();
        loginPresenter.login(loginView.getMobile(), loginView.getPwd());
    }

    public void loginOK(){
        setResult(2);
    }

    public void dismissProgressBar(){
        pDialog.dismiss();
    }

    public void pwdOnClick(View view) {

//        loginPresenter.resetPwdClick("18611330404", "0000", "123456");
//        if (BrainApplication.token != null) {
////            startWebview("测试", "http://192.168.10.60:8080/app/fuiou/register?token=" + BrainApplication.token);
//            startWebview("测试", "http://192.168.10.60:8080/app/fuiou/recharge?token=" + BrainApplication.token);
//        } else {
//            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
//        }
        Intent saveIntent = new Intent(LoginActivity.this, CircleActivity.class);
        startActivity(saveIntent);
    }

    public void regOnClick(View view) {
//        loginPresenter.registerClick("18611330404", "0000", "123456");
//        loginPresenter.changePwdClick("123456", "123456");
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();

        Log.e("Destroy", "onDestroy");
    }

    private void startWebview(String title, String url) {
        Bundle bundle = new Bundle();

        bundle.putString("url", url);
        bundle.putString("title", title);
        Intent intent = new Intent(this, BaseWebActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
