package com.xeehoo.health.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.MyProductActivity;
import com.xeehoo.health.activity.ProductsActivity;
import com.xeehoo.health.adapter.MyProductRecyclerAdapter;
import com.xeehoo.health.adapter.ProductRecyclerAdapter;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.fragment.SpacesItemDecoration;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.rxjava.action.ErrorAction1;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.action.ResultAction1;
import com.xeehoo.health.rxjava.rxbus.RxBus;
import com.xeehoo.health.share.bean.ShareService;

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
public class ProductPresenter extends ServicePresenter{
//    private ShareService shareService;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
//    private Map<String, Observable> map = new HashMap<String, Observable>();
    private RecyclerView recyclerView;
    private List<Product> products = new ArrayList<Product>();
    private ProductRecyclerAdapter adapter;

    public void onCreate(Context context, IView view) {
        this.context = context;
        super.init(context);

        swipeRefreshLayout = view.get(R.id.product_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                    }
                });

        recyclerView = view.get(R.id.product_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(12));
//        ProductsActivity activity = (ProductsActivity)context;
//        BrainApplication app = (BrainApplication) activity.getApplication();
//        Retrofit retrofit = app.getRetrofit();
//        shareService = retrofit.create(ShareService.class);

        adapter = new ProductRecyclerAdapter(context, products);
        recyclerView.setAdapter(adapter);
//        JsonArray items = new JsonArray();
//        items.add(new JsonObject());
//        items.add(new JsonObject());
//        MyProductRecyclerAdapter adapter = new MyProductRecyclerAdapter(context, items);
//        recyclerView.setAdapter(adapter);

        register("product", productAction1);

        product();
    }

//    private void register(String tag, Action1 action1) {
//        Observable<Result> observable = RxBus.get().register(tag, Result.class);
//        observable.subscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(action1);
//
//        map.put(tag, observable);
//    }

    private Action1<Result> productAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            Toast.makeText(context, result.getCode() + " - " + result.getMsg(), Toast.LENGTH_SHORT).show();
            if ("OK".equalsIgnoreCase(result.getCode())){
                JsonArray items = result.getObj().getAsJsonArray("data");
                products.addAll(Arrays.asList(new Gson().fromJson(items, Product[].class)));
                adapter.notifyDataSetChanged();
            }
        }
    };

//    private void call(Observable<JsonObject> observable, String tag) {
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new ResultAction1(tag), new ErrorAction1(tag));
//    }
//
//    public void callProduct() {
//        Observable<JsonObject> observable = shareService.products(0);
//        call(observable, "product");
//    }
//
//    public void onDestroy() {
//        for (Map.Entry<String, Observable> entry : map.entrySet()) {
//            RxBus.get().unregister(entry.getKey(), entry.getValue());
//        }
//    }
}
