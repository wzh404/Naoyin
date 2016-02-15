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
public class MobileSmsView extends AbstractView {
    public MobileSmsView(Context context, ViewGroup container){
        super.init(context, container, R.layout.activity_mobile_sms);
    }

    public String getSmsCode(){
        EditText editText = get(R.id.register_edit_sms);
        return editText.getText().toString();
    }

    public void setMobile(String mobile){
        TextView textView = get(R.id.send_sms_mobile);
        textView.setText(mobile);
    }
}
