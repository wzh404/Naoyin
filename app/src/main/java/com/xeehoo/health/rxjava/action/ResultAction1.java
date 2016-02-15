package com.xeehoo.health.rxjava.action;

import android.support.annotation.NonNull;
import android.util.Log;


import com.google.gson.JsonObject;
import com.xeehoo.health.rxjava.rxbus.RxBus;

import rx.functions.Action1;

/**
 * Created by wangzunhui on 2016/1/27.
 */
public class ResultAction1 implements Action1 {
    private String tag;

    public ResultAction1(@NonNull String tag){
        this.tag = tag;
    }

    @Override
    public void call(Object o) {
        if (o instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject)o;
            Result r = new Result();
            r.setTag(tag);

            if ("OK".equalsIgnoreCase(jsonObject.get("resultCode").getAsString())) {
                r.setCode("OK");
                if (jsonObject.get("resultMsg") != null){
                    r.setMsg(jsonObject.get("resultMsg").getAsString());
                }
                r.setObj(jsonObject);
            } else {
                r.setCode(jsonObject.get("resultCode").getAsString());
                r.setMsg(jsonObject.get("resultMsg").getAsString());
            }

            RxBus.get().post(tag, r);
        }
    }
}
