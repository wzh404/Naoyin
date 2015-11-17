package com.xeehoo.health.common.view;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/12.
 */
public class MainView extends AbstractView {
    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.ac_main);
    }
}
