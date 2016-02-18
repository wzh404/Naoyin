package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xeehoo.health.presenter.RegisterPresenter;
import com.xeehoo.health.presenter.ResetPwdPresenter;
import com.xeehoo.health.util.ValidateUtil;
import com.xeehoo.health.view.ChangePwdView;
import com.xeehoo.health.view.ResetPwdView;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class ResetPwdActivity extends Activity {
    private ResetPwdView resetPwdView;
    private ResetPwdPresenter resetPwdPresenter;
    private SweetAlertDialog dialog;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getStringExtra("type");
        resetPwdView = new ResetPwdView(this, null);
        setContentView(resetPwdView.getView());

        if ("pay".equalsIgnoreCase(type)){
            resetPwdView.setPay();
        }
        resetPwdPresenter = new ResetPwdPresenter();
        resetPwdPresenter.onCreate(this, resetPwdView, type);

        dialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
    }

    public void dismissProgressBar(){
        dialog.dismiss();
    }

    public void mobileSmsOnClick(View view){
        Log.e("pwd", "mobile is " + resetPwdView.getMobile());
        if (!ValidateUtil.isMobileNO(resetPwdView.getMobile())){
            Toast.makeText(this, "手机号输入不正确!", Toast.LENGTH_SHORT).show();
            return;
        }

        if ("".equalsIgnoreCase(resetPwdView.getPwd()) || resetPwdView.getPwd().length() < 6){
            Toast.makeText(this, "密码不能为空且长度大于6位!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent saveIntent = new Intent(ResetPwdActivity.this, MobileSmsActivity.class);
        saveIntent.putExtra("type", type);
        saveIntent.putExtra("para1", resetPwdView.getMobile());
        saveIntent.putExtra("para2", resetPwdView.getPwd());
        startActivity(saveIntent);
    }

    public void exitOnClick(View view) {
        this.finish();
    }
}
