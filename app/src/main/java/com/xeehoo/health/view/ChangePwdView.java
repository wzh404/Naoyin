package com.xeehoo.health.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class ChangePwdView extends AbstractView {
    public ChangePwdView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_change_pwd);
    }

    public void setTitle(String title){
        TextView titleView = get(R.id.change_pwd_title);
        titleView.setText(title);
    }

    public String getPwd(){
        EditText editText = get(R.id.change_edit_password);
        return editText.getText().toString();
    }

    public String getNewPwd(){
        EditText editText = get(R.id.change_edit_new_password);
        return editText.getText().toString();
    }

    public String getNewRetryPwd(){
        EditText editText = get(R.id.change_edit_retry_password);
        return editText.getText().toString();
    }
}
