package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class MyAccountView extends AbstractView {
    public MyAccountView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_my_account);
    }
}
