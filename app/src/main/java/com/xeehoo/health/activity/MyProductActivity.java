package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.model.Transfer;
import com.xeehoo.health.presenter.MyProductPresenter;
import com.xeehoo.health.util.AppConfig;
import com.xeehoo.health.view.MyProductView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProductActivity extends Activity {
    private MyProductPresenter myProductPresenter;
    private MyProductView myProductView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myProductPresenter = new MyProductPresenter();

        myProductView = new MyProductView(this, null);
        setContentView(myProductView.getView());
        myProductPresenter.onCreate(this, myProductView);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        myProductPresenter.onDestroy();
        BrainApplication.investId = 0;
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void startTransferWebview(MyProduct myProduct){
        Bundle bundle = new Bundle();

        bundle.putString("url", AppConfig.WEB_URL + "/app/user/investment?invest_id=" + myProduct.getInvestId());
        bundle.putString("title", myProduct.getProductName());
        bundle.putString("type", "myProduct");
        bundle.putParcelable("myProduct", myProduct);
        Intent intent = new Intent(this, BaseWebActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
