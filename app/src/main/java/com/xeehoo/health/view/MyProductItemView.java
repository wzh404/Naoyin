package com.xeehoo.health.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProductItemView extends AbstractView {
    public MyProductItemView(Context context, ViewGroup container){
        super.init(context, container, R.layout.item_my_product);
    }

    public void setMyProductState(String state, String transferStatus){
        Button button = get(R.id.my_product_state);
        if ("U".equalsIgnoreCase(state)){
            if (transferStatus.equalsIgnoreCase("R")){
                button.setText("转让中");
            } else{
                button.setText("还款中");
            }
        }
        else if ("D".equalsIgnoreCase(state)){
            button.setText("已还款");
        }
        else if ("O".equalsIgnoreCase(state)){
            button.setText("逾期");
        }
    }
}
