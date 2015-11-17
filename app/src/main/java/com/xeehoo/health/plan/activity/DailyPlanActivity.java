package com.xeehoo.health.plan.activity;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xeehoo.health.R;
import com.xeehoo.health.plan.adapter.DailyPlanAdapter;
import com.xeehoo.health.plan.bean.DailyPlan;
import com.xeehoo.health.util.AssetsUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

public class DailyPlanActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_daily_plan);
		
		ListView lv = (ListView)findViewById(R.id.lv_daily_plan);
		lv.setDividerHeight(0);
		LayoutInflater inflater = getLayoutInflater();
//		View hv = inflater.inflate(R.layout.lv_item_header, lv, false);
		
//		ImageView iv = (ImageView)hv.findViewById(R.id.lv_image);
//        iv.setImageResource(R.drawable.daily_plan);
//        lv.addHeaderView(hv);
        
		Context context = getBaseContext();
		String json = AssetsUtils.getFromAssets(context, "daily_plan.json");
        final List<DailyPlan> dailyPlan = JSON.parseArray(json, DailyPlan.class);
        
		lv.setAdapter(new DailyPlanAdapter(context, dailyPlan));
	}
}
