package com.xeehoo.health.listener;

import com.xeehoo.health.R;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class SlideImagePageChangeListener implements OnPageChangeListener{
	private ImageView[] mImageCircleViews;
	
	public SlideImagePageChangeListener(ImageView[] mImageCircleViews){
		this.mImageCircleViews = mImageCircleViews;
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int index) {
		mImageCircleViews[index]
				.setBackgroundResource(R.drawable.dot_selected1);
		for (int i = 0; i < mImageCircleViews.length; i++) {
			if (index != i) {
				mImageCircleViews[i]
						.setBackgroundResource(R.drawable.dot_none1);
			}
		}
	}
}
