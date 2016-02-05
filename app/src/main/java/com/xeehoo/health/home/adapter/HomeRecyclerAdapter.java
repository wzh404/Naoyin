package com.xeehoo.health.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.xeehoo.health.BR;

import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.util.RecyclerViewHolderFactory;
import com.xeehoo.health.util.RecyclerViewType;
import com.xeehoo.health.util.ResourceUtils;

/**
 * Created by wangzunhui on 2015/11/10.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private JSONArray items;
    private RecyclerViewHolderFactory recyclerViewHolderFactory;

    public HomeRecyclerAdapter(RecyclerViewHolderFactory recyclerViewHolderFactory,JSONArray items) {
        this.items = items;
        this.recyclerViewHolderFactory = recyclerViewHolderFactory;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return recyclerViewHolderFactory.getRecyclerViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final JSONObject obj = (JSONObject) items.get(position);

        convert(holder.itemView.getContext(), obj);
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

    private void convert(Context context, JSONObject obj){
        Object icon = obj.get("icon");
        if (icon != null && icon.getClass() == java.lang.String.class){
            Drawable drawable = ResourceUtils.getDrawable(context, icon.toString());
            obj.put("icon", drawable);
        }
    }
}
