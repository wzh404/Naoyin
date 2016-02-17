package com.xeehoo.health.presenter;

import android.content.Context;
import android.graphics.Color;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.util.CommonUtil;
import com.xeehoo.health.view.BalanceView;
import com.xeehoo.health.view.UserView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/2/6.
 */
public class BalancePresenter extends ServicePresenter {
    private Context context;
    private BalanceView balanceView;

    public void onCreate(Context context, BalanceView view) {
        this.context = context;
        this.balanceView = view;
        super.init(context);
    }
}
