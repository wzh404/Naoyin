package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/12.
 */
public class ContentView1 extends AbstractView {
//    private View view;

    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.listitem_content_v1);
        //LayoutInflater.from(context).inflate(R.layout.listitem_content_v1, container, false);
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