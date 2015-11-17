package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/13.
 */
public class TrainView extends AbstractView {
//    private View view;

    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.fragment_home);
//        view = LayoutInflater.from(context).inflate(R.layout.fragment_home, container, false);
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
