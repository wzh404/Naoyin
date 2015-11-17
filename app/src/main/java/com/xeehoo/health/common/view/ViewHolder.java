package com.xeehoo.health.common.view;

import android.content.Context;
import android.view.View;

public interface ViewHolder {
	public View getView(Context context, Object obj);
	public void initViewHolder(Context context, Object bean);
}
