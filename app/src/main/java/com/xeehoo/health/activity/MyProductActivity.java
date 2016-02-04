package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xeehoo.health.presenter.MyProductPresenter;
import com.xeehoo.health.view.MyProductView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProductActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyProductPresenter myProductPresenter = new MyProductPresenter();

        MyProductView mv = new MyProductView(this, null);
        setContentView(mv.getView());
        myProductPresenter.onCreate(this, mv);
    }
}
