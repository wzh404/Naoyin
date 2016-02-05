package com.xeehoo.health.fragment;


import android.content.Context;
import android.databinding.DataBindingUtil;
//import android.databinding.layouts.DataBindingInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.ArticleListAdapter;
import com.xeehoo.health.common.bean.Article;
import com.xeehoo.health.common.presenter.TrackerNewPresenter;
import com.xeehoo.health.common.view.MoMoRefreshListView;
import com.xeehoo.health.common.view.StateView;
import com.xeehoo.health.common.view.TrackerNewView;
import com.xeehoo.health.util.AssetsUtils;

import java.util.ArrayList;
import java.util.List;

public class TrackerNewFragment extends Fragment  {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		final Context context = this.getActivity().getBaseContext();

		TrackerNewView view = new TrackerNewView();
		view.init(context, container);
        TrackerNewPresenter presenter = new TrackerNewPresenter();
        presenter.onCreate(context, view);

		return view.getView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
