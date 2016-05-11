package com.xeehoo.health.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.adapter.TransferRecyclerAdapter;
import com.xeehoo.health.fragment.SpacesItemDecoration;
import com.xeehoo.health.model.Transfer;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.util.CommonUtil;
import com.xeehoo.health.view.TransferView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class TransferPresenter extends ServicePresenter{
    private Context context;
    private List<Transfer> transfers = new ArrayList<Transfer>();
    private TransferRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public void onCreate(Context context, TransferView view) {
        this.context = context;
        super.init(context);

        swipeRefreshLayout = view.get(R.id.transfer_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        transfer();
                    }
                });

        RecyclerView recyclerView = view.get(R.id.transfer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(CommonUtil.dip2px(context,12.0f)));

        adapter = new TransferRecyclerAdapter(context, transfers);
        recyclerView.setAdapter(adapter);
        register("transfer", transferAction1);

        transfer();
    }

    private Action1<Result> transferAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
           if (result.isResult("transfer", "OK")){
                transfers.clear();
                JsonArray items = result.getObj().getAsJsonArray("data");
                transfers.addAll(0, Arrays.asList(new Gson().fromJson(items, Transfer[].class)));
                adapter.notifyDataSetChanged();
            }

            if (transfers.size() == 0){
                Transfer transfer = new Transfer();
                transfer.setTransferId(0);
                transfers.add(transfer);
                adapter.notifyDataSetChanged();
            }

            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
        }
    };
}
