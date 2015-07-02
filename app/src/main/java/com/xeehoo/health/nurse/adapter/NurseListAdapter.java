package com.xeehoo.health.nurse.adapter;

import java.util.List;
import java.util.Map;

import com.xeehoo.health.R;
import com.xeehoo.health.nurse.bean.Nurse;
import com.xeehoo.health.plan.bean.DailyPlan;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NurseListAdapter extends BaseAdapter {

	private Context context;
	private List<Nurse> list;
	private LayoutInflater inflater;

	public NurseListAdapter(Context context, List<Nurse> list) {
		super();
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.lv_nurse, null);
			viewHolder = new ViewHolder();

			viewHolder.title = (TextView) convertView.findViewById(R.id.item_nurse_title);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.lv_nurse_image);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		Nurse nurse = list.get(position);

		viewHolder.title.setText(nurse.getTitle());
		viewHolder.image.setImageResource(ResourceUtils.getDrawableIdentifier(context, nurse.getImage()));
		
		return convertView;
	}

	static class ViewHolder {
		public ImageView image;
		public TextView title;
	}
}
