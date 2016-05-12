package com.xeehoo.health.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;
import com.xeehoo.health.share.bean.BitmapCache;

import java.math.BigDecimal;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class TransferRequestView extends AbstractView {
    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_transfer_request);
    }

    public TransferRequestView(Context context, ViewGroup container, final BigDecimal min, final BigDecimal max){
        init(context, container);

        this.setAmountInputHint(min.toPlainString() + " - " + max.toPlainString());
        initTransferAmount(min, max);
    }

    private void initTransferAmount(BigDecimal min, BigDecimal max){
        EditText editText = get(R.id.transfer_request_amount);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String a = s.toString();
                if (!"".equalsIgnoreCase(a.trim())){
                    BigDecimal v = new BigDecimal(a);
                    if (v.compareTo(min) == 1 && v.compareTo(max) == -1){ // min < v < max
                        BigDecimal discount = v.divide(max,2, BigDecimal.ROUND_HALF_UP);
                        setTransferDiscount(discount);

                        BigDecimal fee = v.multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        if (fee.compareTo(new BigDecimal(1.0)) == -1) { // < 1.0
                            fee = new BigDecimal(1.0);
                        }
                        if (fee.compareTo(new BigDecimal(15.0)) == 1) { // > 15.0
                            fee = new BigDecimal(15.0);
                        }

                        setTransferFee(fee);
                    }
                }
            }
        });
    }

    public BigDecimal getTransferRequestAmount(){
        EditText editText = get(R.id.transfer_request_amount);
        return new BigDecimal(editText.getText().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public void setTitle(String title){
        TextView tv = get(R.id.transfer_request_title);
        tv.setText("转让 " + title);
    }

    public void setAmountInputHint(String hint){
        EditText et = get(R.id.transfer_request_amount);
        et.setHint(hint);
    }

    public void setTransferAmount(BigDecimal amount){
        TextView tv = get(R.id.transfer_amount);
        tv.setText(amount.toPlainString());
    }
    public void setTransferIncome(BigDecimal income){
        TextView tv = get(R.id.transfer_income);
        tv.setText(income.toPlainString());
    }

    public void setTransferFee(BigDecimal fee){
        TextView tv = get(R.id.transfer_fee);
        tv.setText(fee.toPlainString());
    }

    public void setTransferDiscount(BigDecimal discount){
        TextView tv = get(R.id.transfer_discount);
        tv.setText(discount.toPlainString());
    }
}
