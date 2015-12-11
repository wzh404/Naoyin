package com.xeehoo.health.common.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.FragmentAdapter;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.common.view.TabshotContentView;
import com.xeehoo.health.fragment.BrainFragment;
import com.xeehoo.health.fragment.HomeFragment;
import com.xeehoo.health.fragment.MyFragment;
import com.xeehoo.health.fragment.NurseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzunhui on 2015/11/17.
 */
public class MainPresenter implements Presenter {
    private Context context;
    private FragmentManager fragmentManager;

    @Override
    public void onCreate(Context context, IView view) {
        this.context = context;

        final ViewPager viewPager = view.get(R.id.pager);
        initViewPager(viewPager);

        final FragmentTabHost fragmentTabHost = view.get(android.R.id.tabhost);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabWidget widget = fragmentTabHost.getTabWidget();
                int oldFocusability = widget.getDescendantFocusability();
                widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
                fragmentTabHost.setCurrentTab(position);
                widget.setDescendantFocusability(oldFocusability);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                viewPager.setCurrentItem(fragmentTabHost.getCurrentTab());
            }
        });

        initTabs(fragmentTabHost, viewPager);
    }

    public void setFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }

    private void initTabs(final FragmentTabHost mTabHost, final ViewPager vp){
        mTabHost.setup(context, fragmentManager, R.id.pager);

        int[] selectors = {R.drawable.selector_tab_home,
                R.drawable.selector_tab_train,
//                R.drawable.selector_tab_share,
                R.drawable.selector_tab_me};
        String[] names = {"训练", "动态", "我的"};

        for (int i = 0; i < names.length; i++) {
            TabshotContentView v = new TabshotContentView();
            v.init(context, null);
            v.setText(names[i]);
            v.setImageSelector(selectors[i]);

            mTabHost.addTab(mTabHost.newTabSpec("").setIndicator(v.getView()), Fragment.class, null);
            mTabHost.setTag(i);
            mTabHost.setBackgroundResource(R.drawable.selector_tab_background);
        }
        //设置tabs之间的分隔线不显示
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    private void initViewPager(ViewPager vp) {
        MyFragment myFragment = new MyFragment();
        BrainFragment brainFragment = new BrainFragment();
        NurseFragment nurseFragment = new NurseFragment();
        HomeFragment homeFragment = new HomeFragment();

        List<Fragment> mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(homeFragment);
        mFragmentList.add(nurseFragment);
//        mFragmentList.add(brainFragment);
        mFragmentList.add(myFragment);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(fragmentManager, mFragmentList);
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(mFragmentList.size());
    }
}
