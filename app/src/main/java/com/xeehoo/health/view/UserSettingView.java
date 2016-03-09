package com.xeehoo.health.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by wangzunhui on 2015/11/12.
 */
public class UserSettingView extends AbstractView {
    public UserSettingView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_user_setting);
        RelativeLayout relativeLayout = get(R.id.setting_logout);

        if (BrainApplication.isLogin){
            TextView textView = get(R.id.log_txt);
            relativeLayout.setVisibility(View.VISIBLE);
            textView.setText("退出登录");
        }
        else{
            relativeLayout.setVisibility(View.GONE);
        }
    }
}
