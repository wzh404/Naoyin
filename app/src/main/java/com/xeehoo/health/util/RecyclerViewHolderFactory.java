package com.xeehoo.health.util;

import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.xeehoo.health.common.view.ContentView1;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.common.view.PlanItemView;
import com.xeehoo.health.common.view.RecommendTitleView;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.common.view.TrainItemView;

/**
 * Created by wangzunhui on 2015/11/13.
 */
public class RecyclerViewHolderFactory {
    private Context context;
    private SparseArray<ViewFactory> factories = new SparseArray();

    public RecyclerViewHolderFactory(Context context){
        registerViewFactory(RecyclerViewType.RECOMMEND_TITLE.ordinal(), new RecommendTitleViewFactory());
        registerViewFactory(RecyclerViewType.TRAIN_ITEM.ordinal(), new TrainItemViewFactory());
        registerViewFactory(RecyclerViewType.PLAN_ITEM.ordinal(), new PlanItemViewFactory());

        this.context = context;
    }

    public RecyclerViewHolder getRecyclerViewHolder(ViewGroup parent, int viewType){
        if (factories.get(viewType) == null) {
            return null;
        }

        IView view = factories.get(viewType).create(context, parent);
        RecyclerViewHolder holder = new RecyclerViewHolder(view.getView());

        return holder;
    }

    public void registerViewFactory(int viewType, ViewFactory viewFactory){
        factories.put(viewType, viewFactory);
    }

    public interface ViewFactory{
        public IView create(Context context, ViewGroup parent);
    }

    public class RecommendTitleViewFactory implements  ViewFactory{
        @Override
        public IView create(Context context, ViewGroup parent) {
            RecommendTitleView view = new RecommendTitleView(context, parent);

            return view;
        }
    }

    public class ContentView1Factory implements  ViewFactory{
        @Override
        public IView create(Context context, ViewGroup parent) {
            ContentView1 view = new ContentView1(context, parent);

            return view;
        }
    }

    public class PlanItemViewFactory implements  ViewFactory{
        @Override
        public IView create(Context context, ViewGroup parent) {
            PlanItemView view = new PlanItemView(context, parent);

            return view;
        }
    }

    public class TrainItemViewFactory implements  ViewFactory{
        @Override
        public IView create(Context context, ViewGroup parent) {
            TrainItemView view = new TrainItemView(context, parent);

            return view;
        }
    }
}
