package com.xeehoo.health.home.adapter;

import java.util.List;

import com.xeehoo.health.common.view.ViewHolder;
import com.xeehoo.health.home.bean.HomeViewFactory;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeListAdapter extends BaseAdapter {
	private Context context;
	private List<? extends Object> items;

	public HomeListAdapter(Context context, List<? extends Object> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = HomeViewFactory.createViewWithJson(context,
					getItem(position));
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		holder.initViewHolder(context, getItem(position));

		return convertView;
	}
}
