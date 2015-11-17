package com.xeehoo.health.common.presenter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.fragment.SpacesItemDecoration;
import com.xeehoo.health.home.adapter.HomeRecyclerAdapter;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.util.RecyclerViewHolderFactory;

/**
 * Created by wangzunhui on 2015/11/17.
 */
public class HomePresenter implements Presenter {
    @Override
    public void onCreate(Context context, IView view) {
        RecyclerView recyclerView = view.get(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(12));

        String json = AssetsUtils.getFromAssets(context, "home.json");
        JSONArray jsonArray = JSON.parseArray(json);

        RecyclerViewHolderFactory viewHolderAction = new RecyclerViewHolderFactory(context);
        HomeRecyclerAdapter homeRecyclerAdapter = new HomeRecyclerAdapter(viewHolderAction, jsonArray);

        recyclerView.setAdapter(homeRecyclerAdapter);
    }
}
