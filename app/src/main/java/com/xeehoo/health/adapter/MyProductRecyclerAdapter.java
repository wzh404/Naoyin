package com.xeehoo.health.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.xeehoo.health.BR;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.MyProductActivity;
import com.xeehoo.health.activity.TransferActivity;
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
        if (holder.getItemViewType() == 0)
            return;

        MyProduct myProduct = items.get(position);

//        if (holder.getIView() instanceof MyProductItemView){
//
//
//
//        }
//        MyProductItemView view = (MyProductItemView)holder.getIView();
//        view.setMyProductState(myProduct.getInvestStatus());

        holder.getBinding().setVariable(BR.my, myProduct);
        holder.getBinding().executePendingBindings();


        final MyProductActivity activity = (MyProductActivity)holder.itemView.getContext();
        LinearLayout linearLayout = (LinearLayout)holder.itemView.findViewById(R.id.item_my_product);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myProduct.getInvestId() + "", Toast.LENGTH_SHORT).show();
                activity.startTransferWebview(myProduct);
            }
        });
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
