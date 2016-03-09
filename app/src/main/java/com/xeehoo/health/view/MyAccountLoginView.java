package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountLoginView extends AbstractView {
    public MyAccountLoginView(Context context, ViewGroup container){
        super.init(context, container, R.layout.item_login_my_account);
        layout();
        setItemOnClick();
    }

    public void setItemOnClick() {
        Button button = get(R.id.user_need_login);
        button.setOnClickListener(v -> {
                MainActivity mainActivity = (MainActivity) view.getContext();
                if (!BrainApplication.isLogin) {
                    mainActivity.loginOnClick(null);
                }
            });
    }

    public void layout(){
        LinearLayout unloginLayout = get(R.id.user_unlogin_layout);
        RelativeLayout loginLayout = get(R.id.user_login_layout);
        TextView textView = get(R.id.user_name);

        if (BrainApplication.isLogin){
            loginLayout.setVisibility(View.VISIBLE);
            unloginLayout.setVisibility(View.GONE);
            textView.setText(BrainApplication.mobile);
        }
        else{
            loginLayout.setVisibility(View.GONE);
            unloginLayout.setVisibility(View.VISIBLE);
        }
    }
}
