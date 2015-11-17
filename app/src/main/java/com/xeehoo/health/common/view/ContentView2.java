package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/12.
 */
public class ContentView2 implements IView{
    private View view;

    public void init(Context context, ViewGroup container){
        view = LayoutInflater.from(context).inflate(R.layout.listitem_content_v2, container, false);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public <T extends View> T get(int id){
        return (T)getView().findViewById(id);
    }

    public void addView(View view){
        LinearLayout linearLayout = (LinearLayout) get(R.id.item_v2_layout);
        linearLayout.addView(view);
    }
}
