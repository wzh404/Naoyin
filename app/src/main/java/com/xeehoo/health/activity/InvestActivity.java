package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
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
import com.xeehoo.health.model.Product;
import com.xeehoo.health.util.MoneyUtil;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by wangzunhui on 2016/2/3.
 */
public class InvestActivity extends Activity {
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_confirm_invest);
        product = getIntent().getParcelableExtra("product");

        final TextView textViewRmb = (TextView)findViewById(R.id.invest_amount_rmb);
        final EditText editAmount = (EditText)findViewById(R.id.invest_amount);
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
                if (!"".equalsIgnoreCase(a.trim())){
                    String rmb = MoneyUtil.convert(a + ".00");
                    textViewRmb.setText(rmb);
                }
                else{
                    textViewRmb.setText("");
                }
            }
        });
    }

    public void investOnClick(View view){
        EditText editAmount = (EditText)findViewById(R.id.invest_amount);
        String amount = editAmount.getText().toString();
        if (!"".equalsIgnoreCase(amount)){
            Intent intent = new Intent(this, PayActivity.class);
            intent.putExtra("type", "invest");
            intent.putExtra("name", product.getProductName());
            intent.putExtra("amount", amount);
            intent.putExtra("payId", product.getProductId());

            startActivity(intent);
        }
    }

    public void exitOnClick(View view) {
        this.finish();
    }
}

