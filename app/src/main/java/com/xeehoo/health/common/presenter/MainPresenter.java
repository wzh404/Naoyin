package com.xeehoo.health.common.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.FragmentAdapter;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.common.view.TabshotContentView;
import com.xeehoo.health.fragment.BrainFragment;
import com.xeehoo.health.fragment.HomeFragment;
import com.xeehoo.health.fragment.MyAccountFragment;
import com.xeehoo.health.fragment.MyFragment;
import com.xeehoo.health.fragment.NurseFragment;
import com.xeehoo.health.fragment.YdzcHomeFragment;
import com.xeehoo.health.fragment.YdzcInvestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzunhui on 2015/11/17.
 */
public class MainPresenter implements Presenter {
    private Context context;
    private FragmentManager fragmentManager;
    private FragmentAdapter mFragmentAdapter;
    private MyAccountFragment myAccountFragment;

    @Override
    public void onCreate(final Context context, IView view) {
        this.context = context;

        List<TabBean> tabs = new ArrayList<TabBean>();
        tabs.add(new TabBean("首页", R.drawable.selector_tab_home, new YdzcHomeFragment()));
        tabs.add(new TabBean("投资理财", R.drawable.selector_tab_share, new YdzcInvestFragment()));
        this.myAccountFragment = new MyAccountFragment();
        tabs.add(new TabBean("我的账户", R.drawable.selector_tab_me, myAccountFragment));

        final ViewPager viewPager = view.get(R.id.pager);
        initViewPager(viewPager, tabs);

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

        initTabs(fragmentTabHost, viewPager, tabs);
    }

    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private void initTabs(final FragmentTabHost mTabHost, final ViewPager vp, List<TabBean> tbs) {
        mTabHost.setup(context, fragmentManager, R.id.pager);

        for (TabBean tb : tbs) {
            TabshotContentView v = new TabshotContentView();
            v.init(context, null);
            v.setText(tb.name);
            v.setImageSelector(tb.selector);

            mTabHost.addTab(mTabHost.newTabSpec("").setIndicator(v.getView()), Fragment.class, null);
            mTabHost.setTag(tb.name);
            mTabHost.setBackgroundResource(R.drawable.selector_tab_background);
        }
        //设置tabs之间的分隔线不显示
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    private void initViewPager(ViewPager vp, List<TabBean> tabs) {
        List<Fragment> mFragmentList = new ArrayList<Fragment>();
        for (TabBean tab : tabs) {
            mFragmentList.add(tab.fragment);
        }

        mFragmentAdapter = new FragmentAdapter(fragmentManager, mFragmentList);
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);
        vp.setOffscreenPageLimit(mFragmentList.size());
    }

    public void showLogin(){
        if (myAccountFragment != null){
            myAccountFragment.showLogin();
        }
    }

    private class TabBean {
        public TabBean(String name, int selector, Fragment fragment) {
            this.name = name;
            this.selector = selector;
            this.fragment = fragment;
        }

        public String name;
        public int selector;
        public Fragment fragment;
    }
}
