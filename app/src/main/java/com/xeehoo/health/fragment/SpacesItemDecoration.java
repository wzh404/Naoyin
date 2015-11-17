package com.xeehoo.health.fragment;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wangzunhui on 2015/11/10.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = 0;
        outRect.right = 0;
        outRect.bottom = 0;
        outRect.top = 0;
//        int pos = parent.getChildAdapterPosition(view);
//        if (pos == 0) {
//            outRect.bottom = 0;
//        }
//
//        if (pos >= 3){
//            outRect.bottom = 0;
//        }

    }
}
