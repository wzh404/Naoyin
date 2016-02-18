package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.presenter.MyProductPresenter;
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
}
