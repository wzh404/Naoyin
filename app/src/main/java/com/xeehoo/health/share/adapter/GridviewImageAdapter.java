package com.xeehoo.health.share.adapter;

import java.util.List;

import com.xeehoo.health.R;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GridviewImageAdapter extends BaseAdapter {
	private Context context;
	private String[] items;

	GridviewImageAdapter(Context context, String[] items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return items[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);

			convertView = inflater.inflate(R.layout.gridview_share_image, null);
			holder = new Holder();
			holder.img = (ImageView) convertView
					.findViewById(R.id.gridview_img);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		
		if (getCount() == 1){
			ViewGroup.LayoutParams layoutParams =
					holder.img.getLayoutParams();
			layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;//ResourceUtils.dip2px(context, 200);
			layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;//ResourceUtils.dip2px(context, 140);
			holder.img.setScaleType(ScaleType.FIT_XY);
			holder.img.setLayoutParams(layoutParams);
		}
		
		int resID = ResourceUtils.getDrawableIdentifier(context, (String)getItem(position));
		holder.img.setImageResource(resID);
		return convertView;
	}

	private class Holder {
		ImageView img = null;
	}
}
