package com.xeehoo.health.bean;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.R;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolderV1 implements ViewHolder{
	private TextView mTitle;
	private ImageView mIcon;
	
	@Override
	public View getView(Context context, Object obj){
		View convertView = LayoutInflater.from(context).inflate(
				R.layout.listitem_content_v1, null);
		
		mTitle = (TextView)convertView.findViewById(R.id.item_v1_title);
		mIcon = (ImageView)convertView.findViewById(R.id.item_v1_icon);
		
		return convertView;
	}

	private void initViewHolderWithJson(Context context, JSONObject json){
		try {
			mTitle.setText(json.getString("title"));
			mIcon.setImageResource(ResourceUtils.getDrawableIdentifier(context, json.getString("icon")));
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
