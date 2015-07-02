package com.xeehoo.health.fragment;


import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xeehoo.health.R;
import com.xeehoo.health.R.layout;
import com.xeehoo.health.nurse.adapter.NurseListAdapter;
import com.xeehoo.health.nurse.bean.Nurse;
import com.xeehoo.health.plan.activity.DailyPlanActivity;
import com.xeehoo.health.plan.adapter.DailyPlanAdapter;
import com.xeehoo.health.share.activity.PhotoActivity;
import com.xeehoo.health.share.activity.ShareActivity;
import com.xeehoo.health.share.bean.ShareContent;
import com.xeehoo.health.util.AssetsUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class NurseFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_nurse,
				container, false);
		final Context context = this.getActivity().getBaseContext();
		
		ListView lv = (ListView)view.findViewById(R.id.lv_nurse);
		lv.setDividerHeight(0);
		
		Button b = (Button)view.findViewById(R.id.button_plan);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context,
						DailyPlanActivity.class);
				startActivity(intent);
			}
		});
		
//		List<Object> list = new ArrayList<Object>();
//		list.add("a");
//		list.add("a");
		String json = AssetsUtils.getFromAssets(context, "nurses.json");
        final List<Nurse> items = JSON.parseArray(json, Nurse.class);
		lv.setAdapter(new NurseListAdapter(context, items));
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
