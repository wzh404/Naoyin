package com.xeehoo.health.common.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.FragmentAdapter;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.common.view.StateView;
import com.xeehoo.health.fragment.TrackerNewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzunhui on 2015/11/17.
 */
public class TrackerPresenter implements Presenter {
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Context context, final IView view) {
        ViewPager viewPager = view.get(R.id.state_vp);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((StateView)view).setState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ((StateView)view).setOnclickListener(viewPager);

        TrackerNewFragment newFragment = new TrackerNewFragment();
        TrackerNewFragment myFragment = new TrackerNewFragment();
        TrackerNewFragment advFragment = new TrackerNewFragment();
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(myFragment);
        list.add(advFragment);
        list.add(newFragment);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(fragmentManager, list);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(list.size());

    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

}
