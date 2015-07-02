package com.xeehoo.health.adapter;

import java.util.ArrayList;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class SlideImageAdapter extends PagerAdapter{
	private ArrayList<View> mImagePageViewList;
	
	public SlideImageAdapter(ArrayList<View> mImagePageViewList){
		this.mImagePageViewList = mImagePageViewList;
	}
	
	@Override
	public int getCount() {
		return mImagePageViewList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View view, int arg1, Object arg2) {
		((ViewPager) view).removeView(mImagePageViewList.get(arg1));
	}

	@Override
	public Object instantiateItem(View view, int position) {
		((ViewPager) view).addView(mImagePageViewList.get(position));

		return mImagePageViewList.get(position);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
	}

	@Override
	public void finishUpdate(View arg0) {
	}
}
