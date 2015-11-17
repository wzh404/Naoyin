package com.xeehoo.health.common.adapter;

import java.util.ArrayList;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xeehoo.health.common.view.SlideImageView;

public class SlideImageAdapter extends PagerAdapter{
	private SparseArray<ImageView> imagePageViews;

	public SlideImageAdapter(SlideImageView slideImageView){
		this.imagePageViews = slideImageView.getImageViews();
	}
	
	@Override
	public int getCount() {
		return imagePageViews.size();
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
	public void destroyItem(ViewGroup view, int arg1, Object arg2) {
		((ViewPager) view).removeView(imagePageViews.get(arg1));
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		((ViewPager) view).addView(imagePageViews.get(position));

		return imagePageViews.get(position);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}
}
