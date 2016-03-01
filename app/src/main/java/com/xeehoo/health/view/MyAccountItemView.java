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
import com.xeehoo.health.util.CommonUtil;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountItemView extends AbstractView {
    public final static String TAG_LOGIN = "tag_my_account_login";
    private Context context;
    private String code;

    public MyAccountItemView(Context context, ViewGroup container) {
        super.init(context, container, R.layout.item_detail_my_account);
        this.context = context;
    }

    public void setMarginBottom() {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getView().getLayoutParams();
        layoutParams.setMargins(0, 0, 0, CommonUtil.dip2px(context, 10.0f));
    }

    public void initData(JSONObject obj) {
        this.code = obj.getString("code");

        setItemOnClick();
//        setItem();
    }

    public String getCode(){
        return code;
    }

    public void setItemOnClick() {
        RelativeLayout relativeLayout = get(R.id.item_my_account_layout);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) view.getContext();

                if (!BrainApplication.isLogin && ! "0500".equalsIgnoreCase(code)) {
//                    if ("0101".equalsIgnoreCase(code)) {
//                        Observable<Result> observable = RxBus.get().register(MyAccountItemView.TAG_LOGIN, Result.class);
//                        observable.subscribeOn(AndroidSchedulers.mainThread())
//                                .subscribe(loginAction1);
//
//                        mainActivity.loginOnClick(view);
//                    } else {
                        Toast.makeText(context, "请登录！", Toast.LENGTH_SHORT).show();
//                    }
                } else {
//                    Toast.makeText(context, BrainApplication.mobile, Toast.LENGTH_SHORT).show();
                    mainActivity.route(code);
                }
            }
        });
    }

//    private Action1 loginAction1 = new Action1<Result>() {
//        @Override
//        public void call(Result result) {
//            if (result.isResult(MyAccountItemView.TAG_LOGIN, "OK")) {
//                setItem();
//            }
//
//            RxBus.get().unregister(MyAccountItemView.TAG_LOGIN);
//        }
//    };

//    public void setItem() {
//        if ("0101".equalsIgnoreCase(code)) { // 用户信息
//            if (BrainApplication.isLogin) {
//                TextView textView = get(R.id.item_my_name);
//                textView.setText(BrainApplication.mobile);
//                textView.setTextColor(Color.parseColor("#000000"));
//            } else {
//                TextView textView = get(R.id.item_my_name);
//                textView.setText("请登录/注册新用户");
//                textView.setTextColor(Color.rgb(0xcc, 0xcc, 0xcc));
//            }
//
//            MainActivity mainActivity = (MainActivity) view.getContext();
//            mainActivity.setAccountItemView(this);
//        }
//    }
}
