package com.xeehoo.health.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.MoMoRefreshListView;
import com.xeehoo.health.common.view.MoMoRefreshListView.OnCancelListener;
import com.xeehoo.health.common.view.MoMoRefreshListView.OnRefreshListener;
import com.xeehoo.health.common.view.MyView;
import com.xeehoo.health.share.adapter.ShareListAdapter;
import com.xeehoo.health.share.bean.ShareContent;
import com.xeehoo.health.util.AssetsUtils;

import java.util.ArrayList;
import java.util.List;


public class MyFragment extends Fragment  {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		MyView view = new MyView();
		view.init(inflater.getContext(), container);
		return view.getView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
