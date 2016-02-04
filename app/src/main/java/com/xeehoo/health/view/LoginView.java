package com.xeehoo.health.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by wangzunhui on 2015/11/12.
 */
public class LoginView extends AbstractView {
    public LoginView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_login);
    }

    public String getMobile(){
        EditText editText = get(R.id.login_edit_account);
        return editText.getText().toString();
    }

    public String getPwd(){
        EditText editText = get(R.id.login_edit_password);
        return editText.getText().toString();
    }
}
