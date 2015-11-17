package com.xeehoo.health.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.SlideImageAdapter;
import com.xeehoo.health.common.bean.SlidePage;
import com.xeehoo.health.common.listener.SlideImagePageChangeListener;
import com.xeehoo.health.common.layout.SlideImageLayout;

public class SlideImageUtil {
	public static View getSlideImageHeaderView(Context context,  List<SlidePage> slidePages) {
        View view = LayoutInflater.from(context).inflate(R.layout.slide_image, null, false);
		ViewPager mViewPager = (ViewPager) view.findViewById(R.id.image_slide_page);

		SlideImageLayout mSlideLayout = new SlideImageLayout(context);
		int length = slidePages.size();
		ImageView[] mImageCircleViews = new ImageView[length];
		ViewGroup mImageCircleView = (ViewGroup) view
				.findViewById(R.id.layout_circle_images);

		mSlideLayout.setCircleImageLayout(length);
		ArrayList<View> mImagePageViewList = new ArrayList<View>();
		for (int i = 0; i < length; i++) {
			// 图片
			mImagePageViewList.add(mSlideLayout
					.getSlideImageLayout(slidePages.get(i)));

			// 圆圈图
			mImageCircleViews[i] = mSlideLayout.getCircleImageLayout(i);

			// 创建线性布局存放圆圈图
			mImageCircleView.addView(mSlideLayout.getLinearLayout(
					mImageCircleViews[i], 16, 16));
		}

		// 设置ViewPager
//		mViewPager.setAdapter(new SlideImageAdapter(mImagePageViewList));
//		mViewPager.setOnPageChangeListener(new SlideImagePageChangeListener(mImageCircleViews));
		
		return view;
	}
}
