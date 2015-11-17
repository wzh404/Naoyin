package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

/**
 * Created by WIN10 on 2015/11/14.
 */
public abstract class AbstractView  implements IView{
    protected View view;

    public void init(Context context, ViewGroup container, int res){
        view = LayoutInflater.from(context).inflate(res, container, false);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public <T extends View> T get(int id){
        return (T)getView().findViewById(id);
    }
}
