package com.xeehoo.health.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.adapter.MyAccountRecyclerAdapter;
import com.xeehoo.health.adapter.ProductRecyclerAdapter;
import com.xeehoo.health.fragment.SpacesItemDecoration;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.view.MyAccountView;
import com.xeehoo.health.view.ProductView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountPresenter extends ServicePresenter{
    private Context context;
    private MyAccountRecyclerAdapter adapter;
    private MyAccountView myAccountView;

    public void onCreate(Context context, MyAccountView view) {
        this.context = context;
        this.myAccountView = view;
        super.init(context);

        RecyclerView recyclerView = view.get(R.id.my_account_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0));

        String json = AssetsUtils.getFromAssets(context, "my_account.json");
        JSONArray jsonArray = JSON.parseArray(json);
        adapter = new MyAccountRecyclerAdapter(context, jsonArray);
        recyclerView.setAdapter(adapter);
    }
}
