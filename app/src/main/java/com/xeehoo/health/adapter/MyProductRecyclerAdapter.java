package com.xeehoo.health.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xeehoo.health.BR;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.view.MyProductItemView;
import com.xeehoo.health.view.ProductEmptyView;

import java.util.List;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyProductRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<MyProduct> items;
    private Context context;

    public MyProductRecyclerAdapter(Context context, List<MyProduct> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0){
            View v = new ProductEmptyView(context, parent).getView();

            RecyclerViewHolder holder = new RecyclerViewHolder(v);
            return holder;
        } else {
            View v = new MyProductItemView(context, parent).getView();

            RecyclerViewHolder holder = new RecyclerViewHolder(v);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        MyProduct myProduct = items.get(position);

        holder.getBinding().setVariable(BR.my, myProduct);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        MyProduct product =  items.get(position);
        if (product.getInvestId().intValue() == 0){
            return 0;
        }
        else{
            return 1;
        }
    }
}
