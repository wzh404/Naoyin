package com.xeehoo.health.bean;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.R;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHolderV2 implements ViewHolder {
	private TextView mHeader;
	private TextView mFooter;
	private LinearLayout mLinear;

	private void initViewHolderWithJson(Context context, JSONObject json) {
		try {
			mHeader.setText(json.getString("header"));
			mFooter.setText(json.getString("footer"));
			JSONArray array = json.getJSONArray("item");
			for (int i = 0; i < array.size(); i++){
				JSONObject obj = array.getJSONObject(i);
				
				View view = (View)mLinear.getChildAt(i);
				
				TextView t = (TextView)view.findViewById(R.id.item_v2_detail_title);
				TextView d = (TextView)view.findViewById(R.id.item_v2_detail_desc);
				ImageView iv = (ImageView)view.findViewById(R.id.item_v2_detail_icon);
				
				t.setText(obj.getString("title"));
				d.setText(obj.getString("desc"));
				iv.setImageResource(ResourceUtils.getDrawableIdentifier(context, obj.getString("icon")));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public View getView(Context context, Object obj) {
		View convertView = LayoutInflater.from(context).inflate(
				R.layout.listitem_content_v2, null);
		mLinear = (LinearLayout) convertView
				.findViewById(R.id.item_v2_layout);
		JSONObject json = (JSONObject)obj;
		JSONArray array = json.getJSONArray("item");
		createViewDetail(context, mLinear, array.size());

		mHeader = (TextView) convertView.findViewById(R.id.item_v2_header);
		mFooter = (TextView) convertView.findViewById(R.id.item_v2_more);

		return convertView;
	}

	@Override
	public void initViewHolder(Context context,Object bean) {
		if (bean instanceof JSONObject) {
			initViewHolderWithJson(context, (JSONObject) bean);
		}
	}

	private void createViewDetail(final Context context, LinearLayout ll, int itemSize) {
		for (int i = 0; i < itemSize; i++) {
			View detailView = LayoutInflater.from(context).inflate(
					R.layout.listitem_content_detail, null);
			detailView.setTag(i + 1);
			detailView.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View arg0) {
					Integer index = (Integer)arg0.getTag();
					Toast.makeText(context, index + "clicked", Toast.LENGTH_LONG).show();
				}
				
			});
			ll.addView(detailView);
		}
	}
}
