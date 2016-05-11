package com.xeehoo.health.presenter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.PayActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.rxbus.RxBus;
import com.xeehoo.health.view.MyAccountItemView;
import com.xeehoo.health.view.PayView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/2/6.
 */
public class PayPresenter  extends ServicePresenter {
    private Context context;
    private PayView payView;
    private Integer resultCode;

    public void onCreate(Context context, PayView payView) {
        this.context = context;
        this.payView = payView;
        this.resultCode = 0;
        super.init(context);

        register("pay", payAction1);
        register("transfer_complete", payAction1);
    }

    private Action1 payAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            PayActivity payActivity = (PayActivity) context;
            payView.dismissDialog();

            if (result.isResult("pay", "OK")) {
                Toast.makeText(context, "投资成功", Toast.LENGTH_SHORT).show();
                payActivity.setResult(1);
                payActivity.finish();
            }
            if (result.isResult("transfer_complete", "OK")) {
                Toast.makeText(context, "债权转让成功", Toast.LENGTH_SHORT).show();
                payActivity.setResult(1);
                payActivity.finish();
            }else {
                Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
