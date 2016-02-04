package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;
import com.xeehoo.health.R;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by wangzunhui on 2016/2/3.
 */
public class PayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pay);
        TextView textAmount = (TextView)findViewById(R.id.online_pay_amount);
        TextView textName = (TextView)findViewById(R.id.online_pay_name);

        String name = getIntent().getStringExtra("name");
        String amount = getIntent().getStringExtra("amount");
        textAmount.setText(getMoney(amount));
        textName.setText(name);
    }


    public void payOnClick(View view){
        GridPasswordView v = (GridPasswordView)findViewById(R.id.pay);
        Toast.makeText(this, v.getPassWord(),Toast.LENGTH_SHORT).show();
    }

    private  String getMoney(String string) {
        Double numDouble = Double.parseDouble(string);
        // 想要转换成指定国家的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        return format.format(numDouble);
    }
}

