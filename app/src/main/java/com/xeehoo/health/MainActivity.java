package com.xeehoo.health;

import java.util.ArrayList;
import java.util.List;

import com.xeehoo.health.adapter.FragmentAdapter;
import com.xeehoo.health.fragment.HomeFragment;
import com.xeehoo.health.fragment.BrainFragment;
import com.xeehoo.health.fragment.NurseFragment;
import com.xeehoo.health.fragment.ShareFragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private ViewPager mPageVp;

	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private FragmentAdapter mFragmentAdapter;

	/**
	 * Tab显示内容TextView
	 */
	private TextView mTabChatTv, mTabContactsTv, mTabFriendTv,mTabContactsTv2;
	/**
	 * Tab的那个引导线
	 */
	private ImageView mTabLineIv;
	/**
	 * Fragment
	 */
	private HomeFragment homeFragment;
	private ShareFragment shareFragment;
	private BrainFragment planFragment;
	private NurseFragment nurseFragment;
	/**
	 * ViewPager的当前选中页
	 */
	private int currentIndex;
	/**
	 * 屏幕的宽度
	 */
	private int screenWidth;
	
	private static int tabSize = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_main);
//		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title); 
		
		findById();
		init();
		initTabLineWidth();

	}

	private void findById() {
		mTabContactsTv = (TextView) this.findViewById(R.id.id_contacts_tv);
		mTabContactsTv2 = (TextView) this.findViewById(R.id.id_contacts_tv2);
		mTabChatTv = (TextView) this.findViewById(R.id.id_chat_tv);
		mTabFriendTv = (TextView) this.findViewById(R.id.id_friend_tv);

		mTabLineIv = (ImageView) this.findViewById(R.id.id_tab_line_iv);
		mPageVp = (ViewPager) this.findViewById(R.id.id_page_vp);		
		mTabChatTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.e("click", "textView");
                mPageVp.setCurrentItem(0);
            }});
		mTabFriendTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPageVp.setCurrentItem(1);
            }});
		mTabContactsTv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPageVp.setCurrentItem(2);
            }});
		mTabContactsTv2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPageVp.setCurrentItem(3);
            }});
		
		LinearLayout llChat = (LinearLayout)this.findViewById(R.id.id_tab_chat_ll);
		llChat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Log.e("click", "linearout");
                mPageVp.setCurrentItem(0);
            }});
		
		LinearLayout llFriend = (LinearLayout)this.findViewById(R.id.id_tab_friend_ll);
		llFriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPageVp.setCurrentItem(1);
            }});
		
		LinearLayout llContacts = (LinearLayout)this.findViewById(R.id.id_tab_contacts_ll);
		llContacts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPageVp.setCurrentItem(2);
            }});
		
		LinearLayout llContacts2 = (LinearLayout)this.findViewById(R.id.id_tab_contacts_ll2);
		llContacts2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPageVp.setCurrentItem(3);
            }});
	}

	private void init() {
		shareFragment = new ShareFragment();
		planFragment = new BrainFragment();
		nurseFragment = new NurseFragment();
		homeFragment = new HomeFragment();
		
		mFragmentList.add(homeFragment);
		mFragmentList.add(shareFragment);
		mFragmentList.add(planFragment);
		mFragmentList.add(nurseFragment);

		mFragmentAdapter = new FragmentAdapter(
				this.getSupportFragmentManager(), mFragmentList);
		mPageVp.setAdapter(mFragmentAdapter);
		mPageVp.setCurrentItem(0);

		mPageVp.setOnPageChangeListener(new OnPageChangeListener() {
			/**
			 * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
			 */
			@Override
			public void onPageScrollStateChanged(int state) {

			}

			/**
			 * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
			 * offsetPixels:当前页面偏移的像素位置
			 */
			@Override
			public void onPageScrolled(int position, float offset,
					int offsetPixels) {
				LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
						.getLayoutParams();

//				Log.e("onPageScrolled:", currentIndex + ":" + position + " - " + offset + "");
				/**
				 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
				 * 设置mTabLineIv的左边距 滑动场景： 记3个页面, 从左到右分别为0,1,2 0->1; 1->2; 2->1;
				 * 1->0
				 */

				if (currentIndex == 0 && position == 0)// 0->1
				{
					lp.leftMargin = (int) (offset * (screenWidth * 1.0 / MainActivity.tabSize) + currentIndex
							* (screenWidth / MainActivity.tabSize));

				} else if (currentIndex == 1 && position == 0) // 1->0
				{
					lp.leftMargin = (int) (-(1 - offset)
							* (screenWidth * 1.0 / MainActivity.tabSize) + currentIndex
							* (screenWidth / MainActivity.tabSize));
				} else if (currentIndex == 1 && position == 1) // 1->2
				{
					lp.leftMargin = (int) (offset * (screenWidth * 1.0 / MainActivity.tabSize) + currentIndex
							* (screenWidth / MainActivity.tabSize));
				} else if (currentIndex == 2 && position == 1) // 2->1
				{
					lp.leftMargin = (int) (-(1 - offset)
							* (screenWidth * 1.0 / MainActivity.tabSize) + currentIndex
							* (screenWidth / MainActivity.tabSize));
				}else if (currentIndex == 2 && position == 2) // 2->3
				{
					lp.leftMargin = (int) (offset * (screenWidth * 1.0 / MainActivity.tabSize) + currentIndex
							* (screenWidth / MainActivity.tabSize));
				} else if (currentIndex == 3 && position == 2) // 3->2
				{
					lp.leftMargin = (int) (-(1 - offset)
							* (screenWidth * 1.0 / MainActivity.tabSize) + currentIndex
							* (screenWidth / MainActivity.tabSize));
				}
				mTabLineIv.setLayoutParams(lp);
			}

			@Override
			public void onPageSelected(int position) {
//				resetTextView();
//				switch (position) {
//				case 0:
//					mTabChatTv.setTextColor(Color.BLUE);
//					break;
//				case 1:
//					mTabFriendTv.setTextColor(Color.BLUE);
//					break;
//				case 2:
//					mTabContactsTv.setTextColor(Color.BLUE);
//					break;
//				case 3:
//					mTabContactsTv2.setTextColor(Color.BLUE);
//					break;
//				}
				currentIndex = position;
			}
		});
	}

	/**
	 * 设置滑动条的宽度为屏幕的1/tabSize
	 */
	private void initTabLineWidth() {
		DisplayMetrics dpMetrics = new DisplayMetrics();
		getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(dpMetrics);
		screenWidth = dpMetrics.widthPixels;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
				.getLayoutParams();
		lp.width = screenWidth / MainActivity.tabSize;
		mTabLineIv.setLayoutParams(lp);
	}

	/**
	 * 重置颜色
	 
	private void resetTextView() {
		mTabChatTv.setTextColor(Color.BLACK);
		mTabFriendTv.setTextColor(Color.BLACK);
		mTabContactsTv.setTextColor(Color.BLACK);
		mTabContactsTv2.setTextColor(Color.BLACK);
	}*/

	public void toggleMenu(View view) {
		shareFragment.testListAdd();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);  
        Log.e("result", " ok ");
	}
}
