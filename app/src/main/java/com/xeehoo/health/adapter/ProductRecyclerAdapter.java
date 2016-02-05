package com.xeehoo.health.adapter;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.BR;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.util.RecyclerViewType;
import com.xeehoo.health.view.CircleProgressBar;
import com.xeehoo.health.view.MyProductItemView;
import com.xeehoo.health.view.ProductEmptyView;
import com.xeehoo.health.view.ProductItemView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class ProductRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Product> items;
    private Context context;

    public ProductRecyclerAdapter(Context context, List<Product> items){
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        if (viewType == 0){
            v = new ProductEmptyView(context, parent).getView();
        }
        else {
            v = new ProductItemView(context, parent).getView();
        }

        RecyclerViewHolder holder = new RecyclerViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Product product =  items.get(position);

        holder.getBinding().setVariable(BR.product, product);
        holder.getBinding().executePendingBindings();

        if (product.getProductId() == 0)
            return;

        CircleProgressBar circleProgressBar = (CircleProgressBar)holder.itemView.findViewById(R.id.circleProgressbar);
        try {
            int progress = product.getResidualAmount().divide(product.getTotalAmount(), 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).intValue();
            Log.e("Circle", " circle is " + progress);
            circleProgressBar.setProgress(progress);
        }catch (Exception e){
            e.printStackTrace();
        }

        String amount = product.getResidualAmount().divide(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_DOWN).toString();
        circleProgressBar.setAmount(amount);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Product product =  items.get(position);
        if (product.getProductId().intValue() == 0){
            return 0;
        }
        else{
            return 1;
        }
    }
}
