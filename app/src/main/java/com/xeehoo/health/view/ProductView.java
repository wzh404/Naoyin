package com.xeehoo.health.view;

import android.content.Context;
import android.view.ViewGroup;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class ProductView extends AbstractView {
    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_products);
    }

    public ProductView(Context context, ViewGroup container){
        init(context, container);
    }
}
