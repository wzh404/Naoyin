package com.xeehoo.health.common.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangzunhui on 2015/11/10.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    private View view;

    public RecyclerViewHolder(View v) {
        super(v);
        binding = DataBindingUtil.bind(v);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
