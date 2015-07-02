package com.xeehoo.health.brain.bean;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.bean.ViewHolder;

public class BrainViewFactory {
	public static View createViewWithJson(Context context, Object obj) {
		ViewHolder holder = null;
		
		if (obj instanceof JSONObject){
			JSONObject jsonObject = (JSONObject) obj;
			try {
				String code = jsonObject.getString("code");
				if ("01".equals(code)){
					holder = new ViewHolderBrainV1();
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
