package com.xeehoo.health.fragment;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xeehoo.health.R;
import com.xeehoo.health.R.drawable;
import com.xeehoo.health.R.id;
import com.xeehoo.health.R.layout;
import com.xeehoo.health.adapter.HomeListAdapter;
import com.xeehoo.health.bean.SlidePage;
import com.xeehoo.health.brain.adapter.BrainListAdapter;
import com.xeehoo.health.plan.adapter.DailyPlanAdapter;
import com.xeehoo.health.plan.bean.DailyPlan;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.util.SlideImageUtil;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class BrainFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_brain, null);
		ListView lv = (ListView) view.findViewById(R.id.lv_health_brain);
		lv.setDividerHeight(0);

		String slide = AssetsUtils.getFromAssets(inflater.getContext(),
				"home_slide.json");
		List<SlidePage> slidePages = JSON.parseArray(slide, SlidePage.class);
		SlideImageUtil.addSlideImageHeaderView(inflater, lv, slidePages);

		Context context = this.getActivity().getBaseContext();
		String json = AssetsUtils.getFromAssets(context, "brain.json");
        JSONArray jsonArray = JSON.parseArray(json);
        
		lv.setAdapter(new BrainListAdapter(this.getActivity()
				.getApplicationContext(), jsonArray));

		return view;
	}
}
