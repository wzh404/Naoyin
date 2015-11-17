package com.xeehoo.health.share.bean;

import com.google.gson.JsonObject;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by wangzunhui on 2015/7/25.
 */
public interface ShareService {
    @GET("/brainKnowledge.do?version=1")
    Observable<JsonObject> listRepos();
}
