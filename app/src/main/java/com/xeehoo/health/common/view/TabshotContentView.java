package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/13.
 */
public class TabshotContentView extends AbstractView {
    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.tab_content);
    }

    public void setText(String name){
        TextView tv = get(R.id.tabshot_text);
        tv.setText(name);
    }

    public void setImageSelector(int resId){
        ImageView iv = get(R.id.tabshot_image);
        iv.setImageResource(resId);
    }

}
