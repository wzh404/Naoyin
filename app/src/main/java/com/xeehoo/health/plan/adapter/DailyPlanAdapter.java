package com.xeehoo.health.plan.adapter;

import java.util.List;
import java.util.Map;

import com.xeehoo.health.R;
import com.xeehoo.health.plan.bean.DailyPlan;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DailyPlanAdapter extends BaseAdapter {

	private Context context;
	private List<DailyPlan> list;
	private LayoutInflater inflater;

	public DailyPlanAdapter(Context context, List<DailyPlan> list) {
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
			convertView = inflater.inflate(R.layout.lv_daily_plan, null);
			viewHolder = new ViewHolder();

			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.plan_time = (TextView) convertView.findViewById(R.id.tv_plan_time);
			viewHolder.live_type = (TextView) convertView.findViewById(R.id.tv_live_type);
//			viewHolder.layout = (RelativeLayout) convertView.findViewById(R.id.relative);
//			viewHolder.line = (View) convertView.findViewById(R.id.view_2);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		DailyPlan dp = list.get(position);
		//Map<String, Object> map = list.get(position);
		//String time = (String)map.get("time");
		
		if (dp.getClassifyCode() == null){
            Log.e("Time", "----NULL----");
		}
		
		if (dp.getTime() == null){
		    //Log.e("Time", "----NULL----");
			viewHolder.plan_time.setVisibility(View.GONE);
		}
		else{
		    viewHolder.plan_time.setVisibility(View.VISIBLE);
		    viewHolder.plan_time.setText(dp.getTime());
		//Log.e("Time", dp.getTime());
		}
//		String titleStr = list.get(position).get("title").toString();g
//		String liveType = list.get(position).get("type").toString();
//		int height  = viewHolder.layout.getMeasuredHeight();
//		Log.e("LINE", height + "");
//		//viewHolder.line.setLayoutParams(new LayoutParams(1, 100)); 
		viewHolder.title.setText(dp.getDesc());
		viewHolder.live_type.setText("");//dp.getClassify());
		
		return convertView;
	}

	static class ViewHolder {
		public TextView plan_time;
		public TextView live_type;
		public TextView title;
//		public RelativeLayout layout;
//		public View line;
	}
}
