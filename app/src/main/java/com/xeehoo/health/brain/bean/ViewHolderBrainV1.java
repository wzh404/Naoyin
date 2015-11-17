package com.xeehoo.health.brain.bean;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

public class ViewHolderBrainV1 implements ViewHolder{
	private GridView gridView;
	
	@Override
	public View getView(Context context, Object obj){
		View convertView = LayoutInflater.from(context).inflate(
				R.layout.item_brain_v1, null);
		
		return convertView;
	}

	private void initViewHolderWithJson(Context context, JSONObject json){
		try {
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initViewHolder(Context context,Object bean) {
		if (bean instanceof JSONObject){
			initViewHolderWithJson(context, (JSONObject)bean);
		}
	}
}
