package com.xeehoo.health.home.bean;

import android.content.Context;
import android.view.View;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.common.view.ViewHolder;
import com.xeehoo.health.common.view.ViewHolderV1;
import com.xeehoo.health.common.view.ViewHolderV2;

public class HomeViewFactory {
	public static View createViewWithJson(Context context, Object obj) {
		ViewHolder holder = null;
		
		if (obj instanceof JSONObject){
			JSONObject jsonObject = (JSONObject) obj;
			try {
				String code = jsonObject.getString("type");
				if ("V1".equals(code)){
					holder = new ViewHolderV1();
				} else if ("V2".equals(code)){
					holder = new ViewHolderV2();
				}
				else{
					return null;
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
