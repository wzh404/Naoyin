package com.xeehoo.health.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.MyProductActivity;
import com.xeehoo.health.adapter.MyProductRecyclerAdapter;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.fragment.SpacesItemDecoration;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.rxjava.action.ErrorAction1;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.action.ResultAction1;
import com.xeehoo.health.rxjava.rxbus.RxBus;
import com.xeehoo.health.share.bean.ShareService;
import com.xeehoo.health.util.CommonUtil;
import com.xeehoo.health.util.User;
import com.xeehoo.health.view.MyProductView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProductPresenter extends ServicePresenter {
    private Context context;
    private List<MyProduct> myProducts = new ArrayList<MyProduct>();
    private MyProductRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyProductView myProductView;

    public void onCreate(Context context, MyProductView view) {
        this.context = context;
        this.myProductView = view;
        super.init(context);

        swipeRefreshLayout = view.get(R.id.my_product_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        myProduct();
                    }
                });

        RecyclerView recyclerView = view.get(R.id.my_product_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(CommonUtil.dip2px(context,10.0f)));

        adapter = new MyProductRecyclerAdapter(context, myProducts);
        recyclerView.setAdapter(adapter);

        BrainApplication.investId = 0;
        register("my_product", myProductAction1);
        myProductView.showDialog();
        myProduct();
    }

    private Action1<Result> myProductAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            Log.e("Product", BrainApplication.investId + " - Product2 size " + myProducts.size());
            myProductView.dismissDialog();
//            Toast.makeText(context, result.getCode() + " - " + result.getTag(), Toast.LENGTH_SHORT).show();
            if (result.isResult("my_product", "OK")){
                if (myProducts.size() == 1 && myProducts.get(0).getInvestId() == 0){
                    myProducts.clear();
                }

                JsonArray items = result.getObj().getAsJsonArray("data");
                myProducts.addAll(0, Arrays.asList(new Gson().fromJson(items, MyProduct[].class)));
                adapter.notifyDataSetChanged();

                if (myProducts.size() > 0) {
                    BrainApplication.investId = myProducts.get(0).getInvestId().intValue();
                }
            }

            if (myProducts.size() == 0){
                MyProduct product = new MyProduct();
                product.setInvestId(0);
                myProducts.add(product);
                adapter.notifyDataSetChanged();
            }

            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
        }
    };
}
