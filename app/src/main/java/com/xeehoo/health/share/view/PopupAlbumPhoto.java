package com.xeehoo.health.share.view;

import java.util.List;

import com.xeehoo.health.R;
import com.xeehoo.health.share.adapter.ImageBucketAdapter;
import com.xeehoo.health.share.bean.ImageBucket;
import com.xeehoo.health.util.ResourceUtils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class PopupAlbumPhoto extends PopupWindow {
	private Handler mHandler;
	
	public PopupAlbumPhoto(final Context context, View parent,
			final List<ImageBucket> dataList,
			final Handler mHandler) {
		super(context);
		
		this.mHandler = mHandler;
		View view = View.inflate(context, R.layout.image_buket_popup_windows,
				null);
		ListView lv = (ListView) view.findViewById(R.id.lv_image_bucket_1);
		ImageBucketAdapter adapter = new ImageBucketAdapter(context, dataList);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(context, "dismiss " + position,
						Toast.LENGTH_LONG).show();
				Message message = Message.obtain(mHandler, 9);
				Bundle b = new Bundle();
				b.putInt("position", position);
				message.setData(b);
				message.sendToTarget();
				dismiss();
			}
		});

//		view.startAnimation(AnimationUtils.loadAnimation(context,
//				R.anim.fade_ins));
//		LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
//		ll_popup.startAnimation(AnimationUtils.loadAnimation(context,
//				R.anim.push_bottom_in_2));
		int h = ResourceUtils.getScreenHeight(context);
		int w = ResourceUtils.getScreenWidth(context);
		
		Toast.makeText(context, "height is " + h, Toast.LENGTH_LONG).show();

		this.setWidth(w - 40);//LayoutParams.MATCH_PARENT);
		this.setHeight(h - 400);

		// setBackgroundDrawable(new BitmapDrawable());
		setFocusable(true);
		setOutsideTouchable(true);
		setContentView(view);
		showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		update();
	}

//	public static int getScreenWidth(Context context) {
//		WindowManager manager = (WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE);
//		Display display = manager.getDefaultDisplay();
//		return display.getWidth();
//	}
//
//	// 获取屏幕的高度
//	public static int getScreenHeight(Context context) {
//		WindowManager manager = (WindowManager) context
//				.getSystemService(Context.WINDOW_SERVICE);
//		Display display = manager.getDefaultDisplay();
//		return display.getHeight();
//	}
}
