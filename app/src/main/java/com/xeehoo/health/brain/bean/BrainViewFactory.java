package com.xeehoo.health.brain.bean;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.common.view.ViewHolder;
import com.xeehoo.health.common.view.ViewHolderV2;

public class BrainViewFactory {
	public static View createViewWithJson(Context context, Object obj) {
		ViewHolder holder = null;
		
		if (obj instanceof JSONObject){
			JSONObject jsonObject = (JSONObject) obj;
			try {
				String code = jsonObject.getString("type");
				if ("V3".equals(code)){
					holder = new ViewHolderBrainV1();
				}
				if ("V2".equals(code)){
					holder = new ViewHolderV2();
				}
				
				View convertView = holder.getView(context, obj);
				convertView.setTag(holder);
				return convertView;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
