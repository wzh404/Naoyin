package com.xeehoo.health.fragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xeehoo.health.R;
import com.xeehoo.health.common.presenter.HomePresenter;
import com.xeehoo.health.common.view.TrainView;
import com.xeehoo.health.home.adapter.HomeRecyclerAdapter;
import com.xeehoo.health.util.AssetsUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xeehoo.health.util.RecyclerViewHolderFactory;

public class HomeFragment extends Fragment{
    private View rootView;
    private Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (rootView == null) {
            Log.e("onCreateView", "-----in HomeFragment-------");
            this.context = getActivity().getBaseContext();
            TrainView trainView = new TrainView(context, container);
//            trainView.init(context, container);
            rootView = trainView.getView();
            HomePresenter presenter = new HomePresenter();
            presenter.onCreate(context, trainView);
        }

        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
