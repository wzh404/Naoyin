package com.xeehoo.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.InvestActivity;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.MyProductActivity;
import com.xeehoo.health.activity.PayActivity;
import com.xeehoo.health.common.adapter.SlideImageAdapter;
import com.xeehoo.health.common.bean.SlidePage;
import com.xeehoo.health.common.view.SlideImageView;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.util.CircleMenuLayout;

import java.util.List;

/**
 * Created by wangzunhui on 2016/2/2.
 */
public class YdzcHomeFragment extends Fragment {
    private Context context;
    private View rootView;

//    private CircleMenuLayout mCircleMenuLayout;

    //    private String[] mItemTexts = new String[] { "安全中心 ", "特色服务", "投资理财",
//            "转账汇款", "我的账户", "信用卡" };
//    private int[] mItemImgs = new int[] { R.drawable.home_mbank_1_normal,
//            R.drawable.home_mbank_2_normal, R.drawable.home_mbank_3_normal,
//            R.drawable.home_mbank_4_normal, R.drawable.home_mbank_5_normal,
//            R.drawable.home_mbank_6_normal };
    private String[] mItemTexts = new String[]{"首页 ", "扩展功能", "投资攻略",
            "债权转让", "其他", "交易纪录"};
    private int[] mItemImgs = new int[]{R.drawable.ic_home,
            R.drawable.ic_ext, R.drawable.ic_invest_help,
            R.drawable.ic_credit, R.drawable.ic_other,
            R.drawable.ic_trans};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (rootView == null) {
            this.context = getActivity().getBaseContext();
            rootView = LayoutInflater.from(context).inflate(R.layout.activity_main02, container, false);
            //自已切换布局文件看效果

//        setContentView(R.layout.activity_main02);
//		setContentView(R.layout.activity_main);

            CircleMenuLayout mCircleMenuLayout = (CircleMenuLayout) rootView.findViewById(R.id.id_menulayout);
            mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

		/* 创建RecyclerView头 */
            String slide = AssetsUtils.getFromAssets(context, "home_slide.json");
            List<SlidePage> slidePages = JSON.parseArray(slide, SlidePage.class);
            SlideImageView slideImageView = new SlideImageView();
            slideImageView.init(inflater, null);
            for (int i = 0; i < slidePages.size(); i++) {
                SlidePage slidePage = slidePages.get(i);
                ImageView iv = slideImageView.addImageView();
                ImageLoader.getInstance().displayImage(slidePage.getImage(), iv);
            }
            slideImageView.getViewPager().setAdapter(new SlideImageAdapter(slideImageView));
            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.linearlayout_images_slide);
            linearLayout.addView(slideImageView.getView());

//            homeRecyclerAdapter.setHeaderViewHolder(slideImageView.getView());


            mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
                @Override
                public void itemClick(View view, int pos) {
//                    Toast.makeText(context, mItemTexts[pos],
//                            Toast.LENGTH_SHORT).show();
                    if (pos == 5) {
                        if (BrainApplication.isLogin){
                            Intent intent = new Intent(context, MyProductActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(context,
                                    "请登录后查看",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void itemCenterClick(View view) {
                    Toast.makeText(context,
                            "you can do something just like ccb  ",
                            Toast.LENGTH_SHORT).show();

                }
            });
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }
}
