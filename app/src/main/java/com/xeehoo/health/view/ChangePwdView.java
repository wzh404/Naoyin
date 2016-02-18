package com.xeehoo.health.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
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

    public void setPay(){
        EditText pwdEditText = get(R.id.change_edit_new_password);
        pwdEditText.setHint("请输入6位数字密码");
        pwdEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        pwdEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        pwdEditText = get(R.id.change_edit_password);
        pwdEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        pwdEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        pwdEditText = get(R.id.change_edit_retry_password);
        pwdEditText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        pwdEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
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
