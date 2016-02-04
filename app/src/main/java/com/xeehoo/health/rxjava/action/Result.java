package com.xeehoo.health.rxjava.action;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;

/**
 * Created by wangzunhui on 2016/1/27.
 */
public class Result{
    private String tag;
    private String code;
    private String msg;
    private JsonObject obj;

    public JsonObject getObj() {
        return obj;
    }

    public void setObj(JsonObject obj) {
        this.obj = obj;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult(@NonNull String tag, @NonNull String code){
        return this.tag.equalsIgnoreCase(tag) && this.code.equalsIgnoreCase(code);
    }
}
