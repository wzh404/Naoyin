package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xeehoo.health.view.RegisterView;


/**
 * Created by WIN10 on 2016/2/2.
 */
public class RegisterActivity extends Activity {
    private RegisterView registerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        registerView = new RegisterView(this, null);
        setContentView(registerView.getView());
    }

    public void mobileSmsOnClick(View view){
        Intent saveIntent = new Intent(RegisterActivity.this, MobileSmsActivity.class);
        startActivity(saveIntent);
    }

    public void exitOnClick(View view) {
        this.finish();
    }
}
