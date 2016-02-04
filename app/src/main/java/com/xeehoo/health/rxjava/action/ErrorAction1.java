package com.xeehoo.health.rxjava.action;

import android.support.annotation.NonNull;
import android.util.Log;

import com.xeehoo.health.rxjava.rxbus.RxBus;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/27.
 */
public class ErrorAction1  implements Action1 {
    private String tag;

    public ErrorAction1(@NonNull String tag){
        this.tag = tag;
    }

    @Override
    public void call(Object o) {
        if (o instanceof  Throwable){
            ((Throwable)o).printStackTrace();
            Result r = new Result();
            r.setTag(tag);
            r.setCode("EX");
            r.setMsg("请求失败");
            RxBus.get().post(tag, r);
        }
    }
}
