package com.xeehoo.health.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.common.view.AbstractView;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.rxbus.RxBus;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountItemView extends AbstractView {
    private Context context;

    public MyAccountItemView(Context context, ViewGroup container){
        super.init(context, container, R.layout.item_detail_my_account);
        this.context = context;
    }

    public void setMarginBottom(){
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)getView().getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 20);
    }

    public void setOnClick(final JSONObject obj){
        RelativeLayout relativeLayout = get(R.id.item_my_account_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  code = obj.getString("code");
                if ("0101".equalsIgnoreCase(code)){
                    Observable<Result> observable = RxBus.get().register("my_fragment_login", Result.class);
                    observable.subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe(loginAction1);
                    MainActivity mainActivity = (MainActivity)view.getContext();
                    mainActivity.loginOnClick(view);
                }
            }
        });
    }

    private Action1 loginAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            if (result.isResult("my_fragment_login", "OK")) {
                initLoginMobile();
            }
        }
    };

    private void initNologinMobile(){
        TextView textView = get(R.id.item_my_account);
        textView.setText("请登录!");
        textView.setTextColor(Color.rgb(0xcc, 0xcc, 0xcc));

        Button logout = get(R.id.my_account_logout);
        logout.setVisibility(View.GONE);

        RelativeLayout relativeLayout = get(R.id.my_name);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<Result> observable = RxBus.get().register("my_fragment_login", Result.class);
                observable.subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(loginAction1);
                MainActivity mainActivity = (MainActivity)context;
                mainActivity.loginOnClick(view);
            }
        });
    }

    private void initLoginMobile(){
        TextView textView = get(R.id.item_my_name);
        textView.setText(BrainApplication.mobile);
        textView.setTextColor(Color.parseColor("#000000"));

//        Button logout = get(R.id.my_account_logout);
//        logout.setVisibility(View.VISIBLE);

//        RelativeLayout relativeLayout = get(R.id.my_name);
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, BrainApplication.mobile, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
