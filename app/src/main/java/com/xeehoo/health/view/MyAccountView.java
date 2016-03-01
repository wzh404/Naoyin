package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class MyAccountView extends AbstractView {
    public MyAccountView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_my_account);
        layout();
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
