package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xeehoo.health.view.MobileSmsView;
import com.xeehoo.health.view.RegisterView;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class MobileSmsActivity extends Activity {
    private MobileSmsView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = new MobileSmsView(this, null);
        setContentView(view.getView());
    }
}
