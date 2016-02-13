package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.view.ChangePwdView;
import com.xeehoo.health.view.MobileSmsView;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class ChangePwdActivity extends Activity {
    private ChangePwdView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new ChangePwdView(this, null);
        setContentView(view.getView());
    }

    public void exitOnClick(View view) {
        this.finish();
    }

}
