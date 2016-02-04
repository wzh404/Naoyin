package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;
import com.xeehoo.health.R;
import com.xeehoo.health.util.MoneyUtil;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by wangzunhui on 2016/2/3.
 */
public class InvestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm_invest);

        final TextView textViewRmb = (TextView)findViewById(R.id.invest_amount_rmb);
        final TextView editAmount = (TextView)findViewById(R.id.invest_amount);
        editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String a = s.toString();
                Log.e("Amount", "[" + a + "]");
                if (!"".equalsIgnoreCase(a.trim())){
                    String rmb = MoneyUtil.convert(a + ".00");
                    Toast.makeText(InvestActivity.this, rmb, Toast.LENGTH_SHORT).show();
                    textViewRmb.setText(rmb);
                }
                else{
                    textViewRmb.setText("");
                }
            }
        });
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

    public void investOnClick(View view){

    }
}

