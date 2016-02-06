package com.xeehoo.health.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class PayView extends AbstractView {
    public PayView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_pay);
    }

    public void setPayName(String name){
        TextView textName = get(R.id.online_pay_name);
        textName.setText(name);
    }

    public void setPayAmount(String amount){
        TextView textName = get(R.id.online_pay_amount);
        textName.setText(amount);
    }
}
