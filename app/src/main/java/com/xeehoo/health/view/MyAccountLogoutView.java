package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountLogoutView extends AbstractView {
    public MyAccountLogoutView(Context context, ViewGroup container){
        super.init(context, container, R.layout.item_logout_my_account);

        setItemOnClick();
    }

    public void setItemOnClick() {
        LinearLayout linearLayout = get(R.id.my_account_logout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) view.getContext();
                if (!BrainApplication.isLogin) {
//                    mainActivity.getAccountItemView().setItem();
                }
            }
        });
    }
}
