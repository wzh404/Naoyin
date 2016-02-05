package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountTelView extends AbstractView {
    public MyAccountTelView(Context context, ViewGroup container){
        super.init(context, container, R.layout.item_tel_my_account);
    }
}
