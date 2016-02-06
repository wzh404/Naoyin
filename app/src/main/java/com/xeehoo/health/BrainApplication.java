package com.xeehoo.health;

import android.app.Application;
import android.content.pm.PackageManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by wangzunhui on 2015/9/18.
 */
public class BrainApplication extends Application {
    private Retrofit retrofit;
    private Integer versionCode = 0;

    public static String token = "0";
    public static String mobile = "0";
    public static boolean isLogin = false;
    public static int productId = 0;
    public static int investId = 0;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        initRetrofit();
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

    private void initRetrofit(){
        try {
            versionCode = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request().newBuilder()
                                .addHeader("platform", "android")
                                .addHeader("appVersion", versionCode.toString())
                                .addHeader("authorization", token == null ? "0" : token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm")
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://192.168.0.173:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
