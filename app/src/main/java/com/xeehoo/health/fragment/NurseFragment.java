package com.xeehoo.health.fragment;


import java.util.ArrayList;
import java.util.List;

import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.FragmentAdapter;
import com.xeehoo.health.common.presenter.TrackerPresenter;
import com.xeehoo.health.common.view.StateView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NurseFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		final Context context = this.getActivity().getBaseContext();
        final StateView view = new StateView();
        view.init(context, container);
        TrackerPresenter presenter = new TrackerPresenter();
        presenter.setFragmentManager(this.getChildFragmentManager());
        presenter.onCreate(context, view);

		return view.getView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
}
