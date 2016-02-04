package com.xeehoo.health.common.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.event.DefaultMessageEvent;
import com.xeehoo.health.event.LoadMessageEvent;
import com.xeehoo.health.fragment.SpacesItemDecoration;
import com.xeehoo.health.home.adapter.HomeRecyclerAdapter;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.util.RecyclerViewHolderFactory;

//import de.greenrobot.event.EventBus;

/**
 * Created by wangzunhui on 2015/11/17.
 */
public class HomePresenter implements Presenter {
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Context context, IView view) {
//        EventBus.getDefault().register(this);

        swipeRefreshLayout = view.get(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
//                        EventBus.getDefault().post(new DefaultMessageEvent("loding data..."));
                    }
                });

        RecyclerView recyclerView = view.get(R.id.rv_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(12));

        String json = AssetsUtils.getFromAssets(context, "home.json");
        JSONArray jsonArray = JSON.parseArray(json);

        RecyclerViewHolderFactory viewHolderAction = new RecyclerViewHolderFactory(context);
        HomeRecyclerAdapter homeRecyclerAdapter = new HomeRecyclerAdapter(viewHolderAction, jsonArray);

        recyclerView.setAdapter(homeRecyclerAdapter);
    }

    public void onEventMainThread(LoadMessageEvent event) {
        Log.e("Event", Thread.currentThread().getId() + " - [main] - " + event.getMessage());
        swipeRefreshLayout.setRefreshing(false);
    }

//    public void onEventBackgroundThread(DefaultMessageEvent event) {
//        Log.e("Event", Thread.currentThread().getId() + " - [back] - " + event.getMessage());
//    }
    public void onEventAsync(DefaultMessageEvent event) {
        Log.e("Event", Thread.currentThread().getId() + " - [async] - " + event.getMessage());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        EventBus.getDefault().post(new LoadMessageEvent("load ok"));
    }
}
