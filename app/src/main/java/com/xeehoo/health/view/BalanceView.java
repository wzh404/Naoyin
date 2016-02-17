package com.xeehoo.health.view;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.AbstractView;

/**
 * Created by WIN10 on 2015/11/14.
 */
public class BalanceView extends AbstractView {
    public BalanceView(Context context, ViewGroup container) {
        super.init(context, container, R.layout.activity_balance);

        EditText editText = get(R.id.balance_money);
        editText.setFilters(new InputFilter[]{ lengthfilter  });
    }

    InputFilter lengthfilter = new InputFilter() {
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // 删除等特殊字符，直接返回
            if ("".equals(source.toString())) {
                return null;
            }
            String[] splitArray = dest.toString().split("\\.");
            if (splitArray.length > 1) {
                String dotValue = splitArray[1];
                int diff = dotValue.length() + 1 - 2;
                if (diff > 0) {
                    return source.subSequence(start, end - diff);
                }
            }
            return null;
        }
    };

    public String getMoney(){
        EditText editText = get(R.id.balance_money);
        return editText.getText().toString();
    }

    public void setTitle(String title){
        TextView textView = get(R.id.balance_title);
        textView.setText(title);
    }
}
