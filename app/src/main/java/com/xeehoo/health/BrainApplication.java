package com.xeehoo.health;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * Created by wangzunhui on 2015/9/18.
 */
public class BrainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
            .Builder(getApplicationContext())
            .memoryCacheExtraOptions(720, 1280) // 即保存的每个缓存文件的最大长宽
            .threadPoolSize(3)// 线程池内加载的数量
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .denyCacheImageMultipleSizesInMemory()
            .memoryCache(new WeakMemoryCache()) // 你可以通过自己的内存缓存实现
            .memoryCacheSize(2 * 1024 * 1024)
            .diskCacheSize(50 * 1024 * 1024)
            .diskCacheFileNameGenerator(new Md5FileNameGenerator())// 将保存的时候的URI名称用MD5 加密
            .tasksProcessingOrder(QueueProcessingType.LIFO)
            .diskCacheFileCount(100) // 缓存的文件数量
//          .diskCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
            .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
            .writeDebugLogs() // Remove for release app
            .build();// 开始构建
        ImageLoader.getInstance().init(config);
    }
}
