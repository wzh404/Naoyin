package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/13.
 */
public class PlanItemView extends AbstractView {
//    private View view;

    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.item_mytrain);
//        view = LayoutInflater.from(context).inflate(R.layout.item_mytrain, container, false);
    }

//    @Override
//    public View getView() {
//        return view;
//    }
//
//    @Override
//    public <T extends View> T get(int id){
//        return (T)getView().findViewById(id);
//    }
}
