package com.xeehoo.health.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.common.presenter.TrackerNewPresenter;
import com.xeehoo.health.common.view.TrackerView;
import com.xeehoo.health.presenter.ProductPresenter;
import com.xeehoo.health.view.ProductView;


public class YdzcInvestFragment extends Fragment {
	private View rootView;
    private ProductPresenter presenter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		if (rootView == null) {
			Context context = this.getActivity().getBaseContext();
			ProductView view = new ProductView(context, container);
			rootView = view.getView();
			presenter = new ProductPresenter();
			presenter.onCreate(context, view);
		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.e("Ydzc", "onDestroyView");
        presenter.onDestroy();
    }
}
