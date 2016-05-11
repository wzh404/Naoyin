package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.model.Transfer;
import com.xeehoo.health.presenter.TransferPresenter;
import com.xeehoo.health.util.AppConfig;
import com.xeehoo.health.view.TransferView;

/**
 * Created by wangzunhui on 2016/5/11.
 */
public class TransferActivity extends Activity {
    private TransferView transferView;
    private TransferPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.transferView = new TransferView(this, null);
        setContentView(this.transferView.getView());

        this.presenter = new TransferPresenter();
        presenter.onCreate(this, transferView);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    public void exitOnClick(View view) {
        this.finish();
    }

    public void startTransferWebview(Transfer transfer){
        Bundle bundle = new Bundle();

        bundle.putString("url", AppConfig.WEB_URL + "/app/transfer/detail?transfer_id=" + transfer.getTransferId());
        bundle.putString("title", transfer.getProductName());
        bundle.putString("type", "transfer");
        bundle.putParcelable("transfer", transfer);
        Intent intent = new Intent(this, BaseWebActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
