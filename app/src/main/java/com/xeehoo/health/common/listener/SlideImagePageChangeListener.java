package com.xeehoo.health.common.listener;

import com.xeehoo.health.R;
import com.xeehoo.health.common.view.SlideImageView;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class SlideImagePageChangeListener implements OnPageChangeListener{
	private SlideImageView slideImageView;

	public SlideImagePageChangeListener(SlideImageView slideImageView){
		this.slideImageView = slideImageView;
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int index) {
        slideImageView.setSelectedCircleImageView(index);
        slideImageView.setOtherUnselectedCircleImageView(index);
	}
}
