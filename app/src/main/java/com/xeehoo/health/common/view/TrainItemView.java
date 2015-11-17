package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/13.
 */
public class TrainItemView extends AbstractView {
    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.listitem_content_detail);
//        view = LayoutInflater.from(context).inflate(R.layout.listitem_content_detail, container, false);
    }
}
