package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;
import com.xeehoo.health.R;
import com.xeehoo.health.presenter.PayPresenter;
import com.xeehoo.health.view.PayView;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by wangzunhui on 2016/2/3.
 */
public class PayActivity extends Activity {
    private PayPresenter presenter;
    private PayView payView;

    private String amount;
    private String type;
    private Integer payId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        payView = new PayView(this, null);
        setContentView(payView.getView());

        this.presenter = new PayPresenter();
        this.presenter.onCreate(this, payView);

        this.type = getIntent().getStringExtra("type");
        String name = getIntent().getStringExtra("name");
        this.amount = getIntent().getStringExtra("amount");
//        this.amt = Integer.parseInt(amount);
        this.payId = getIntent().getIntExtra("payId", 0);

        payView.setPayAmount(getMoney(amount));
        payView.setPayName(name);
    }

    public void payOnClick(View view){
        GridPasswordView v = (GridPasswordView)findViewById(R.id.pay);
//        Toast.makeText(this, v.getPassWord(),Toast.LENGTH_SHORT).show();
        if ("invest".equalsIgnoreCase(type)){
            payView.showDialog();
            presenter.invest(payId, Integer.parseInt(this.amount), v.getPassWord());
        }
        else if ("transfer".equalsIgnoreCase(type)){
            payView.showDialog();
            presenter.transferComplete(payId, v.getPassWord());
        }
    }

    private  String getMoney(String string) {
        Double numDouble = Double.parseDouble(string);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(numDouble);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    public void exitOnClick(View view) {
        this.finish();
    }
}

