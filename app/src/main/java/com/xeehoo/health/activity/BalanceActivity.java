package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.presenter.BalancePresenter;
import com.xeehoo.health.util.AppConfig;
import com.xeehoo.health.view.BalanceView;

import java.math.BigDecimal;

/**
 * 在线充值、提现
 *
 * Created by WIN10 on 2016/2/2.
 */
public class BalanceActivity extends Activity {
    private BalancePresenter balancePresenter;
    private BalanceView balanceView;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        balancePresenter = new BalancePresenter();
        this.type = getIntent().getStringExtra("type");

        balanceView = new BalanceView(this, null);
        setContentView(balanceView.getView());
        balancePresenter.onCreate(this, balanceView);

        if ("withdraw".equalsIgnoreCase(type)){
            balanceView.setTitle("在线提现");
        }else if ("recharge".equalsIgnoreCase(type)){
            balanceView.setTitle("在线充值");
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        balancePresenter.onDestroy();
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void withdrawOrRechargeOnClick(View view) {
        Long longMoney = new BigDecimal(balanceView.getMoney()).multiply(new BigDecimal(100)).longValue();
        String money = longMoney.toString();

        if ("withdraw".equalsIgnoreCase(type)){
            startWebview("在线提现", AppConfig.WEB_URL + "/app/fuiou/withdraw?amt=" + money + "&token=" + BrainApplication.token);
        }
        else if ("recharge".equalsIgnoreCase(type)){
            startWebview("在线充值", AppConfig.WEB_URL + "/app/fuiou/recharge?amt=" + money + "&token=" + BrainApplication.token);
        }
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
