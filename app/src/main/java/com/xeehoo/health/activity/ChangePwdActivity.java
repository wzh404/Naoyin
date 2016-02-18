package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xeehoo.health.presenter.ChangePwdPresenter;
import com.xeehoo.health.util.ValidateUtil;
import com.xeehoo.health.view.ChangePwdView;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class ChangePwdActivity extends Activity {
    private ChangePwdView changePwdView;
    private ChangePwdPresenter changePwdPresenter;
    private String type;
    private SweetAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getStringExtra("type");
        changePwdView = new ChangePwdView(this, null);
        setContentView(changePwdView.getView());

        if ("pay".equalsIgnoreCase(type)){
            changePwdView.setPay();
        }

        changePwdPresenter = new ChangePwdPresenter();
        changePwdPresenter.onCreate(this, changePwdView, type);

        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        changePwdPresenter.onDestroy();
    }

    public void dismissProgressBar(){
        dialog.dismiss();
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void changePwdOnClick(View view){
        String pwd = changePwdView.getPwd();
        if (!ValidateUtil.isPassword(pwd)){
            Toast.makeText(this, "原密码输入不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        String newPwd = changePwdView.getNewPwd();
        if (!ValidateUtil.isPassword(newPwd)){
            Toast.makeText(this, "新密码输入不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        String retryNewPwd = changePwdView.getNewRetryPwd();
        if (!ValidateUtil.isPassword(retryNewPwd)){
            Toast.makeText(this, "确认密码输入不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPwd.equalsIgnoreCase(retryNewPwd)){
            Toast.makeText(this, "两次密码输入不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.show();
        if ("login".equalsIgnoreCase(type)){
            changePwdPresenter.changePwd(pwd, newPwd);
        }
        else if ("pay".equalsIgnoreCase(type)){
            changePwdPresenter.changePayPwd(pwd, newPwd);
        }
    }

    public void resetPwdOnClick(View view) {
        Intent saveIntent = new Intent(ChangePwdActivity.this, ResetPwdActivity.class);
        saveIntent.putExtra("type", type);
        startActivity(saveIntent);
    }
}
