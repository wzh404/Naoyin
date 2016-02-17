package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.presenter.ProductPresenter;
import com.xeehoo.health.presenter.UserPresenter;
import com.xeehoo.health.util.AppConfig;
import com.xeehoo.health.view.ProductView;
import com.xeehoo.health.view.UserView;

/**
 * 托管账户
 *
 * Created by WIN10 on 2016/2/2.
 */
public class UserActivity extends Activity {
    private UserPresenter userPresenter;
    private UserView userView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userPresenter = new UserPresenter();

        userView = new UserView(this, null);
        setContentView(userView.getView());
        userPresenter.onCreate(this, userView);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        userPresenter.onDestroy();
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void fuiouUserOnClick(View view) {
        startWebview("注册第三方托管账户", AppConfig.WEB_URL + "/app/fuiou/register?token=" + BrainApplication.token);
    }

    public void withdrawOnClick(View view) {
        Intent saveIntent = new Intent(UserActivity.this, BalanceActivity.class);
        saveIntent.putExtra("type", "withdraw");
        startActivity(saveIntent);
    }

    public void rechargeOnClick(View view) {
        Intent saveIntent = new Intent(UserActivity.this, BalanceActivity.class);
        saveIntent.putExtra("type", "recharge");
        startActivity(saveIntent);
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
