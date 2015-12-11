package com.xeehoo.health.util;

import android.content.Context;
import android.util.SparseArray;

import com.xeehoo.health.common.view.ContentView1;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.common.view.PlanItemView;
import com.xeehoo.health.common.view.RecommendTitleView;
import com.xeehoo.health.common.view.RecyclerViewHolder;
import com.xeehoo.health.common.view.TrainItemView;

/**
 * Created by WIN10 on 2015/11/13.
 */
public class RecyclerViewHolderFactory {
    public final static int VIEW_TYPE_CONTENT_1 = 1;
    public final static int VIEW_TYPE_CONTENT_2 = 2;
    public final static int VIEW_TYPE_TRAIN_ITEM = 3;
    public final static int VIEW_TYPE_RECOMMEND_TITLE = 4;
    public final static int VIEW_TYPE_INVALID = -1;

    private Context context;
    private SparseArray<ViewFactory> actions = new SparseArray();

    public RecyclerViewHolderFactory(Context context){
        actions.put(RecyclerViewHolderFactory.VIEW_TYPE_RECOMMEND_TITLE, new RecommendTitleViewFactory());
        actions.put(RecyclerViewHolderFactory.VIEW_TYPE_CONTENT_1, new ContentView1Factory());
        actions.put(RecyclerViewHolderFactory.VIEW_TYPE_TRAIN_ITEM, new PlanItemViewFactory());
        actions.put(RecyclerViewHolderFactory.VIEW_TYPE_CONTENT_2, new TrainItemViewFactory());

        this.context = context;
    }

    public RecyclerViewHolder getRecyclerViewHolder(int viewType){
        if (actions.get(viewType) == null)
            return null;

        IView view = actions.get(viewType).create(context);
        RecyclerViewHolder holder = new RecyclerViewHolder(view.getView());

        return holder;
    }

    public interface ViewFactory{
        public IView create(Context context);
    }

    public class RecommendTitleViewFactory implements  ViewFactory{
        @Override
        public IView create(Context context) {
            RecommendTitleView view = new RecommendTitleView();
            view.init(context, null);

            return view;
        }
    }

    public class ContentView1Factory implements  ViewFactory{
        @Override
        public IView create(Context context) {
            ContentView1 view = new ContentView1();
            view.init(context, null);

            return view;
        }
    }

    public class PlanItemViewFactory implements  ViewFactory{
        @Override
        public IView create(Context context) {
            PlanItemView view = new PlanItemView();
            view.init(context, null);

            return view;
        }
    }

    public class TrainItemViewFactory implements  ViewFactory{
        @Override
        public IView create(Context context) {
            TrainItemView view = new TrainItemView();
            view.init(context, null);

            return view;
        }
    }
}
