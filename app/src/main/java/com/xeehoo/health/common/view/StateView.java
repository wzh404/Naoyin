package com.xeehoo.health.common.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xeehoo.health.R;

/**
 * Created by wangzunhui on 2015/11/16.
 */
public class StateView extends AbstractView {
    private int[] res = {R.id.watcher_me, R.id.watcher_tuijian, R.id.watcher_new};
    private Resources resource;
    private SparseArray<TextView> array = new SparseArray<TextView>();

    public void init(Context context, ViewGroup container){
        super.init(context, container, R.layout.fragment_nurse);
        resource = (Resources) context.getResources();
        for (int i = 0; i < res.length; i++){
            TextView tv = get(res[i]);
            array.put(i, tv);
        }
    }

    public SparseArray<TextView> getTextViews(){
        return this.array;
    }

    public void setState(int position){
        array.get(position).setTextColor(resource.getColor(R.color.white));

        for (int i = 0; i < res.length; i++){
            if (i != position){
                array.get(i).setTextColor(resource.getColor(R.color.gray9));
            }
        }
    }

    public void setOnclickListener(final ViewPager viewPager){
        for (int i = 0; i < res.length; i++){
            final int k = i;
            array.get(k).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(k);
                }
            });
        }
    }
}
