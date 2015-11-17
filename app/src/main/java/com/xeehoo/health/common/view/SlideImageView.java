package com.xeehoo.health.common.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.SlideImageAdapter;
import com.xeehoo.health.common.bean.SlidePage;
import com.xeehoo.health.common.layout.SlideImageLayout;
import com.xeehoo.health.common.listener.SlideImagePageChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzunhui on 2015/11/11.
 */
public class SlideImageView implements IView{
    private View view;
    private SparseArray<ImageView> imageViews = new SparseArray<ImageView>();
    private SparseArray<ImageView> circleImageViews = new SparseArray<ImageView>();
    private Context context;
    private ViewGroup circleImageViewGroup;

    public void init(LayoutInflater inflater, ViewGroup container){
        this.context = inflater.getContext();
        view = inflater.inflate(R.layout.slide_image, null);
        ViewPager viewPager = get(R.id.image_slide_page);
        this.circleImageViewGroup = get(R.id.layout_circle_images);

        viewPager.setOnPageChangeListener(new SlideImagePageChangeListener(this));
    }

    public ImageView addImageView(){
        int key = imageViews.size();

        ImageView iv = createImageView();
        imageViews.put(key, iv);

        ImageView circleImageView = createCircleImageView();
        circleImageViews.put(key, circleImageView);

        if (key == 0){
            setSelectedCircleImageView(key);
        }
        else{
            setUnselectedCircleImageView(key);
        }
        circleImageViewGroup.addView(circleImageView);

        return iv;
    }

    public SparseArray<ImageView> getImageViews(){
        return this.imageViews;
    }

    public ViewPager getViewPager(){
        return get(R.id.image_slide_page);
    }

    private ImageView createImageView(){
        LinearLayout.LayoutParams imageLinerLayoutParameters = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1);

        ImageView iv = new ImageView(context);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setLayoutParams(imageLinerLayoutParameters);

        return iv;
    }

    private ImageView createCircleImageView(){
        ImageView iv = new ImageView(context);
        iv.setLayoutParams(new ViewGroup.LayoutParams(24, 24));
        iv.setPadding(5, 0, 5, 0);
        iv.setScaleType(ImageView.ScaleType.FIT_XY);

        return iv;
    }

    public void setUnselectedCircleImageView(int index) {
        if (circleImageViews.size() < index)
            return;

        circleImageViews.get(index)
                .setBackgroundResource(R.drawable.dot_none1);
    }

    public void setOtherUnselectedCircleImageView(int index){
        if (circleImageViews.size() < index)
            return;

        for (int i = 0; i < circleImageViews.size(); i++){
            if (i != index) {
                setUnselectedCircleImageView(i);
            }
        }
    }

    public void setSelectedCircleImageView(int index){
        if (circleImageViews.size() < index)
            return;

        circleImageViews.get(index)
                .setBackgroundResource(R.drawable.dot_selected1);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public <T extends View> T get(int id){
        return (T)getView().findViewById(id);
    }
}
