package com.xeehoo.health.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.model.Transfer;
import com.xeehoo.health.presenter.TransferPresenter;
import com.xeehoo.health.presenter.TransferRequestPresenter;
import com.xeehoo.health.util.AppConfig;
import com.xeehoo.health.view.TransferRequestView;
import com.xeehoo.health.view.TransferView;

import java.math.BigDecimal;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wangzunhui on 2016/5/11.
 */
public class TransferRequestActivity extends Activity {
    private TransferRequestView view;
    private TransferRequestPresenter presenter;

    private BigDecimal amount;
    private BigDecimal maxAmount;
    private MyProduct myProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.myProduct = getIntent().getParcelableExtra("myProduct");
        this.amount = myProduct.getInvestAmount();
        this.maxAmount = this.amount.add(myProduct.getInvestIncome());

        this.view = new TransferRequestView(this, null, this.amount, this.maxAmount);
        setContentView(this.view.getView());

        view.setTitle(myProduct.getProductName());
        view.setTransferAmount(myProduct.getInvestAmount());
        view.setTransferIncome(myProduct.getInvestIncome());

        this.presenter = new TransferRequestPresenter();
        presenter.onCreate(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    public void exitOnClick(View v) {
        this.finish();
    }

    public void transferRequestOnClick(View v){
        BigDecimal val = view.getTransferRequestAmount();
        if (val.compareTo(this.amount) == -1 || val.compareTo(maxAmount) == 1) { // v < min, v > max
            Toast.makeText(this, "请输入正确的转让价格!", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.submitTransferRequest(this.myProduct.getInvestId(), val);
    }

    public void transferRequestOK(){
        SweetAlertDialog dialog = new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("申请成功");
        dialog.show();
    }
}
