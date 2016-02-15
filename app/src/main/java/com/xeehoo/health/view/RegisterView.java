package com.xeehoo.health.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class RegisterView extends AbstractView {
    public RegisterView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_register);
    }

    public String getAccount(){
        EditText editText = get(R.id.register_edit_account);
        return editText.getText().toString();
    }

    public String getPwd(){
        EditText editText = get(R.id.register_edit_password);
        return editText.getText().toString();
    }
}
