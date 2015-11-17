package com.xeehoo.health.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class AssetsUtils {
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader =
                    new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null){
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView view, String url) {
        Log.e("--imageloader", "---" + url);
        ImageLoader.getInstance().displayImage(url, view);
    }
}
