package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.view.ChangePwdView;
import com.xeehoo.health.view.ResetPwdView;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class ResetPwdActivity extends Activity {
    private ResetPwdView resetPwdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resetPwdView = new ResetPwdView(this, null);
        setContentView(resetPwdView.getView());
    }

    public void mobileSmsOnClick(View view){
        Intent saveIntent = new Intent(ResetPwdActivity.this, MobileSmsActivity.class);
        startActivity(saveIntent);
    }

    public void exitOnClick(View view) {
        this.finish();
    }
}
