package com.xeehoo.health.fragment;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xeehoo.health.R;
import com.xeehoo.health.adapter.HomeListAdapter;
import com.xeehoo.health.bean.SlidePage;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.util.SlideImageUtil;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class HomeFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		final Context context = getActivity().getBaseContext();
		View chatView = inflater.inflate(R.layout.fragment_home, container,
				false);
		ListView lv = (ListView) chatView.findViewById(R.id.lv_home);

		String slide = AssetsUtils.getFromAssets(inflater.getContext(), "home_slide.json");
		List<SlidePage> slidePages = JSON.parseArray(slide, SlidePage.class);

		SlideImageUtil.addSlideImageHeaderView(inflater, lv, slidePages);
		String json = AssetsUtils.getFromAssets(context, "home.json");
        JSONArray jsonArray = JSON.parseArray(json);
        
		lv.setAdapter(new HomeListAdapter(this.getActivity()
				.getApplicationContext(), jsonArray));
		return chatView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
