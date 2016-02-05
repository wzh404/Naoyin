package com.xeehoo.health.view;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class ProductEmptyView extends AbstractView {
    public ProductEmptyView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_product_empty);

        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)getView().getLayoutParams();
        layoutParams.height = point.y;
        getView().setLayoutParams(layoutParams);
    }
}
