package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class UserView extends AbstractView {
    public UserView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_fuiou_user);
    }

    public void setTextView(int id, String name){
        TextView textName = get(id);
        textName.setText(name);
    }

    public void setUserName(String name){
        setTextView(R.id.fuiou_user_name, name);
    }

    public void setUserMobile(String name){
        setTextView(R.id.fuiou_user_mobile, name);
    }

    public void setUserCt(String name){
        setTextView(R.id.fuiou_user_ct, name);
    }

    public void setUserCa(String name){
        setTextView(R.id.fuiou_user_ca, name);
    }

    public void setUserCf(String name){
        setTextView(R.id.fuiou_user_cf, name);
    }

    public void setUserCu(String name){
        setTextView(R.id.fuiou_user_cu, name);
    }

    public void showRegister(){
        LinearLayout linearLayout = get(R.id.fuiou_user_register);
        linearLayout.setVisibility(View.VISIBLE);

        linearLayout = get(R.id.fuiou_user_recharge);
        linearLayout.setVisibility(View.GONE);
    }

    public void showRecharge(){
        LinearLayout linearLayout = get(R.id.fuiou_user_recharge);
        linearLayout.setVisibility(View.VISIBLE);

        linearLayout = get(R.id.fuiou_user_register);
        linearLayout.setVisibility(View.GONE);
    }
}
