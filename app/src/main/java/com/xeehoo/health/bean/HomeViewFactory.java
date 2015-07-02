package com.xeehoo.health.bean;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class HomeViewFactory {
	public static View createViewWithJson(Context context, Object obj) {
		ViewHolder holder = null;
		
		if (obj instanceof JSONObject){
			JSONObject jsonObject = (JSONObject) obj;
			try {
				String code = jsonObject.getString("code");
				if ("01".equals(code)){
					holder = new ViewHolderV1();
				} else {
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
