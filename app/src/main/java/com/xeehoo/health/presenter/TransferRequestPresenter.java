package com.xeehoo.health.presenter;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.TransferRequestActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.view.LoginView;

import java.math.BigDecimal;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/28.
 */
public class TransferRequestPresenter extends ServicePresenter {
    private Context context;

    public void onCreate(Context context) {
        this.context = context;
        super.init(context);

        register("transfer_request", transferRequestAction1);
    }

    public void submitTransferRequest(Integer investId, BigDecimal amount){
        transferRequest(investId, amount);
    }

    private Action1 transferRequestAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            if (result.isResult("transfer_request", "OK")) {
                TransferRequestActivity activity = (TransferRequestActivity)context;
                activity.transferRequestOK();
            }
            else {
                Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
