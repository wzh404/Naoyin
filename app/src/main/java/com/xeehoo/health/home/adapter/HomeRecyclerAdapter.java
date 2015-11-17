package com.xeehoo.health.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.BR;

import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.util.RecyclerViewHolderFactory;
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
        Log.e("---onCreate-----", "----viewType-----" + viewType);
        return recyclerViewHolderFactory.getRecyclerViewHolder(viewType);
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
        if ("V0".equalsIgnoreCase(type)){
            return RecyclerViewHolderFactory.VIEW_TYPE_RECOMMEND_TITLE;
        }
        else if ("V1".equalsIgnoreCase(type)){
            return RecyclerViewHolderFactory.VIEW_TYPE_CONTENT_1;
        }
        else if ("V2".equalsIgnoreCase(type)){
            return RecyclerViewHolderFactory.VIEW_TYPE_CONTENT_2;
        }
        else if ("V3".equalsIgnoreCase(type)){
            return RecyclerViewHolderFactory.VIEW_TYPE_TRAIN_ITEM;
        }
        else
            return RecyclerViewHolderFactory.VIEW_TYPE_INVALID;
    }

    private void convert(Context context, JSONObject obj){
        String icon = (String)obj.get("icon");
        if (icon != null){
            Drawable drawable = ResourceUtils.getDrawable(context, icon);
            obj.put("icon", drawable);
        }
    }
}
