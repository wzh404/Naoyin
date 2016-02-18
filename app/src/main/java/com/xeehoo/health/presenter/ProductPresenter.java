package com.xeehoo.health.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
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
import com.xeehoo.health.view.ProductView;

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
    private Context context;
    private List<Product> products = new ArrayList<Product>();
    private ProductRecyclerAdapter adapter;
//    private ProductView productView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public void onCreate(Context context, ProductView view) {
        this.context = context;
//        this.productView = view;
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
                        callProduct();
                    }
                });

        RecyclerView recyclerView = view.get(R.id.product_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SpacesItemDecoration(12));

        adapter = new ProductRecyclerAdapter(context, products);
        recyclerView.setAdapter(adapter);
        register("product", productAction1);

        callProduct();
    }

    private void callProduct(){
        if (products.size() == 0){
            BrainApplication.productId = 0;
        }
        product();
    }

    private Action1<Result> productAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
//            Log.e("Product", BrainApplication.productId + " - Product2 size " + products.size());
            Toast.makeText(context, result.getCode() + " - " + result.getTag(), Toast.LENGTH_SHORT).show();
            if (result.isResult("product", "OK")){
                if (products.size() == 1 && products.get(0).getProductId() == 0){
                    products.clear();
                }

                JsonArray items = result.getObj().getAsJsonArray("data");
//                Log.e("Action1", "items size is " + items.size());
                products.addAll(0, Arrays.asList(new Gson().fromJson(items, Product[].class)));
                adapter.notifyDataSetChanged();

                if (products.size() > 0) {
                    BrainApplication.productId = products.get(0).getProductId().intValue();
                }
            }

            if (products.size() == 0){
                Product product = new Product();
                product.setProductId(0);
                products.add(product);
                adapter.notifyDataSetChanged();
            }

            if (swipeRefreshLayout.isRefreshing())
                swipeRefreshLayout.setRefreshing(false);
        }
    };
}
