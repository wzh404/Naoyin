package com.xeehoo.health.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.common.presenter.TrackerNewPresenter;
import com.xeehoo.health.common.view.TrackerView;
import com.xeehoo.health.presenter.ProductPresenter;
import com.xeehoo.health.view.ProductView;


public class YdzcInvestFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Context context = this.getActivity().getBaseContext();
        ProductView view = new ProductView(context, container);
        view.init(context, container);
		ProductPresenter presenter = new ProductPresenter();
        presenter.onCreate(context, view);

		return view.getView();
	}


}
