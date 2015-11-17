package com.xeehoo.health.common.view;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.R;
import com.xeehoo.health.plan.activity.DailyPlanActivity;
import com.xeehoo.health.share.activity.ShareActivity;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHolderV1 implements ViewHolder{
	private TextView mTitle;
	private ImageView mIcon;
	
	@Override
	public View getView(final Context context, Object obj){
		View convertView = LayoutInflater.from(context).inflate(
				R.layout.listitem_content_v1, null);

        RelativeLayout layout = (RelativeLayout)convertView.findViewById(R.id.item_v1_layout_root);
		mTitle = (TextView)convertView.findViewById(R.id.item_v1_title);
		mIcon = (ImageView)convertView.findViewById(R.id.item_v1_icon);
        layout.setTag(((JSONObject)obj).get("code"));
        layout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String code = (String)view.getTag();
                if ("1000".equals(code)){
                    Intent intent = new Intent(context, DailyPlanActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
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
