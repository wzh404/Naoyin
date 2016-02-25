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
import com.xeehoo.health.util.CommonUtil;
import com.xeehoo.health.util.MoneyUtil;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

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

        TextView textViewName = (TextView)findViewById(R.id.online_product_name);
        textViewName.setText(product.getProductName());

        textViewName = (TextView)findViewById(R.id.online_product_invest_date);
        textViewName.setText(CommonUtil.tomorrow());

        final TextView textViewIncome = (TextView)findViewById(R.id.online_product_income);
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

                    Date startDate = CommonUtil.getDate(product.getReleaseTime());
                    Date closeDate = product.getCloseDate();
                    long days = CommonUtil.diffDate(startDate, closeDate);
                    BigDecimal income = CommonUtil.calculateInterest(
                            new BigDecimal(a),
                            new BigDecimal(product.getLoanRate()),
                            days);
                    String strIncome = income.setScale(2, BigDecimal.ROUND_HALF_DOWN).toString();
                    textViewIncome.setText(strIncome);
                }
                else{
                    textViewRmb.setText("");
                    textViewIncome.setText("0.00");
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

            startActivityForResult(intent, 0);
        }
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("invest", requestCode + " resultCode " + resultCode);
        if (resultCode == 1){
            new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("投资成功")
//                    .setContentText("You clicked the button!")
                    .show();
        }
    }
}

