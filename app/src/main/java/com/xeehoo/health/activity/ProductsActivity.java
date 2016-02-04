package com.xeehoo.health.activity;

import android.app.Activity;
import android.os.Bundle;

import com.xeehoo.health.R;
import com.xeehoo.health.presenter.ProductPresenter;
import com.xeehoo.health.view.ProductView;

/**
 * Created by WIN10 on 2016/2/2.
 */
public class ProductsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProductPresenter productPresenter = new ProductPresenter();

        ProductView mv = new ProductView(this, null);
        setContentView(mv.getView());
        productPresenter.onCreate(this, mv);
    }
}
