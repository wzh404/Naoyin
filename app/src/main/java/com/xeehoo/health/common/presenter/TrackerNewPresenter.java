package com.xeehoo.health.common.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;
import com.xeehoo.health.R;
import com.xeehoo.health.common.adapter.ArticleListAdapter;
import com.xeehoo.health.common.bean.Article;
import com.xeehoo.health.common.view.IView;
import com.xeehoo.health.common.view.MoMoRefreshListView;
import com.xeehoo.health.util.AssetsUtils;

import java.util.List;

/**
 * Created by wangzunhui on 2015/11/17.
 */
public class TrackerNewPresenter implements Presenter,
        MoMoRefreshListView.OnRefreshListener, MoMoRefreshListView.OnCancelListener{
    private MoMoRefreshListView mMmrlvList;

    @Override
    public void onCreate(Context context, IView view) {
        mMmrlvList = view.get(R.id.lv_nurse);
        mMmrlvList.setDividerHeight(0);
        mMmrlvList.setOnRefreshListener(this);
        mMmrlvList.setOnCancelListener(this);
        mMmrlvList.setItemsCanFocus(false);

        String json = AssetsUtils.getFromAssets(context, "nurses.json");
        final List<Article> items = JSON.parseArray(json, Article.class);
        mMmrlvList.setAdapter(new ArticleListAdapter(context, items));
    }

    @Override
    public void onCancel() {
        mMmrlvList.onRefreshComplete();
    }

    @Override
    public void onRefresh() {
        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                super.onPostExecute(result);
                mMmrlvList.onRefreshComplete();
            }
        }.execute();
    }
}
