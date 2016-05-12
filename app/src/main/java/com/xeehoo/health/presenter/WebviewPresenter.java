package com.xeehoo.health.presenter;

import android.content.Context;
import android.widget.Toast;

import com.xeehoo.health.activity.PayActivity;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.view.PayView;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/2/6.
 */
public class WebviewPresenter extends ServicePresenter {
    private Context context;

    public void onCreate(Context context) {
        this.context = context;
        super.init(context);

        register("transfer_request_cancel", transferRequestCancelAction1);
    }

    public void cancelTransferRequest(Integer investId){
        super.cancelTransferRequest(investId);
    }

    private Action1 transferRequestCancelAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            BaseWebActivity activity = (BaseWebActivity) context;

            if (result.isResult("transfer_request_cancel", "OK")) {
                Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
            }
        }
    };
}
