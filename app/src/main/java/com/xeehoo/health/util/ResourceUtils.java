package com.xeehoo.health.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ResourceUtils {
	public static int getIdentifier(Context context, String type, String field) {
		if (field == null || context == null) {
			return -1;
		}

		Resources res = context.getResources();
		return res.getIdentifier(field, type, context.getPackageName());
	}

	public static Drawable getDrawable(Context context, String name){
		int id = ResourceUtils.getDrawableIdentifier(context, name);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getResources().getDrawable(id, context.getTheme());
        } else {
            return context.getResources().getDrawable(id);
        }
	}

	public static int getDrawableIdentifier(Context context, String field) {
		return getIdentifier(context, "drawable", field);
	}

	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(mDisplayMetrics);
		int w = mDisplayMetrics.widthPixels;
		return w;
	}

	// 获取屏幕的高度
	public static int getScreenHeight(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(mDisplayMetrics);
		int h = mDisplayMetrics.heightPixels;
		return h;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
