package com.xeehoo.health.common.layout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xeehoo.health.R;
import com.xeehoo.health.common.bean.SlidePage;
import com.xeehoo.health.util.ResourceUtils;
import com.xeehoo.health.common.webview.BaseWebActivity;

public class SlideImageLayout {
	//private ArrayList<ImageView> mImageList = null;
	private Context mContext = null;
	private ImageView[] mImageViews = null; 
//	private ImageView mImageView = null;
	//private int pageIndex = 0;
	
	public SlideImageLayout(Context context) {
		this.mContext = context;
//		mImageList = new ArrayList<ImageView>();
	}
	
	public View getSlideImageLayout(SlidePage slidePage){
		LinearLayout imageLinerLayout = new LinearLayout(mContext);
		LinearLayout.LayoutParams imageLinerLayoutParameters = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.MATCH_PARENT,
				1);
		
		ImageView iv = new ImageView(mContext);
		iv.setScaleType(ScaleType.FIT_XY);
		iv.setTag(slidePage);
		
//		int id = ResourceUtils.getDrawableIdentifier(mContext, slidePage.getImage());
//		iv.setBackgroundResource(id);
		ImageLoader.getInstance().displayImage(slidePage.getImage(), iv);
		iv.setOnClickListener(new ImageOnClickListener());
		imageLinerLayout.addView(iv, imageLinerLayoutParameters);
//		mImageList.add(iv);
		
		return imageLinerLayout;
	}
	
	public View getLinearLayout(View view,int width,int height){
		LinearLayout linerLayout = new LinearLayout(mContext);
		LinearLayout.LayoutParams linerLayoutParames = new LinearLayout.LayoutParams(
				width, 
				height,
				1);
		linerLayout.setPadding(2, 0, 2, 0);
		linerLayout.addView(view, linerLayoutParames);
		
		return linerLayout;
	}
	
	public void setCircleImageLayout(int size){
		mImageViews = new ImageView[size];
	}
	
	public ImageView getCircleImageLayout(int index){
		ImageView mImageView = new ImageView(mContext);  
		mImageView.setLayoutParams(new LayoutParams(16,16));
        mImageView.setScaleType(ScaleType.FIT_XY);
        
        mImageViews[index] = mImageView;
         
        if (index == 0) {  
            mImageViews[index].setBackgroundResource(R.drawable.dot_selected1);  
        } else {  
            mImageViews[index].setBackgroundResource(R.drawable.dot_none1);  
        }  
         
        return mImageViews[index];
	}
	
//	public void setPageIndex(int index){
//		pageIndex = index;
//	}
	
    private class ImageOnClickListener implements OnClickListener{
    	@Override
    	public void onClick(View v) {
    		Bundle bundle = new Bundle();
    		
    		SlidePage slidePage = (SlidePage)v.getTag();
			bundle.putString("url", slidePage.getUrl());
			bundle.putString("title", slidePage.getTitle());
			Intent intent = new Intent(mContext, BaseWebActivity.class);
			intent.putExtras(bundle);

			mContext.startActivity(intent);
//    		Toast.makeText(mContext, "我点击了第"+"["+pageIndex+"]几个", Toast.LENGTH_SHORT).show();
    	}
    }
 
}
