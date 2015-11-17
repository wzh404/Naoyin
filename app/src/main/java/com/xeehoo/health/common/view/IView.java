package com.xeehoo.health.common.view;

import android.view.View;

/**
 * Created by wangzunhui on 2015/11/11.
 */
public interface IView {
    public View getView();

    public <T extends View> T get(int id);
}
