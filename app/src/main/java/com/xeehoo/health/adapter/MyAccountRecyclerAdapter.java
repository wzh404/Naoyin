package com.xeehoo.health.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.BR;
import com.xeehoo.health.R;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.util.RecyclerViewType;
import com.xeehoo.health.util.ResourceUtils;
import com.xeehoo.health.view.CircleProgressBar;
import com.xeehoo.health.view.MyAccountItemView;
import com.xeehoo.health.view.MyAccountLogoutView;
import com.xeehoo.health.view.ProductEmptyView;
import com.xeehoo.health.view.ProductItemView;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by WIN10 on 2016/2/1.
 */
public class MyAccountRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private JSONArray items;
    private Context context;
    private int currentPosition = 0;

    public MyAccountRecyclerAdapter(Context context, JSONArray items){
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RecyclerViewType.ITEM_MARGIN.ordinal()){
            MyAccountItemView view = new MyAccountItemView(context, parent);
            view.setMarginBottom();
            JSONObject obj =  (JSONObject)items.get(currentPosition);
            view.setOnClick(obj);

            RecyclerViewHolder holder = new RecyclerViewHolder(view.getView());

            return holder;
        }
        else if (viewType == RecyclerViewType.ITEM.ordinal()){
            MyAccountItemView view = new MyAccountItemView(context, parent);
            RecyclerViewHolder holder = new RecyclerViewHolder(view.getView());
            JSONObject obj =  (JSONObject)items.get(currentPosition);
            view.setOnClick(obj);

            return holder;
        }
        else if (viewType == RecyclerViewType.LOGOUT.ordinal()){
            MyAccountLogoutView view = new MyAccountLogoutView(context, parent);
            RecyclerViewHolder holder = new RecyclerViewHolder(view.getView());
            return holder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        currentPosition = position;
        JSONObject obj =  (JSONObject)items.get(position);

        convert(obj);
        holder.getBinding().setVariable(BR.obj, obj);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        JSONObject obj = (JSONObject) items.get(position);
        String type = obj.getString("type");
        return RecyclerViewType.valueOf(type).ordinal();
    }

    private void convert(JSONObject obj){
        Object icon = obj.get("icon");
        if (icon != null && icon.getClass() == java.lang.String.class){
            Drawable drawable = ResourceUtils.getDrawable(context, icon.toString());
            obj.put("icon", drawable);
        }
    }
}
