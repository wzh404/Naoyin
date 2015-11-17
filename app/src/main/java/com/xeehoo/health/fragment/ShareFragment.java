package com.xeehoo.health.fragment;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.xeehoo.health.R;
import com.xeehoo.health.share.activity.ShareActivity;
import com.xeehoo.health.share.adapter.ShareListAdapter;
import com.xeehoo.health.share.bean.ShareContent;
import com.xeehoo.health.share.bean.ShareService;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.common.view.MoMoRefreshListView;
import com.xeehoo.health.common.view.MoMoRefreshListView.OnCancelListener;
import com.xeehoo.health.common.view.MoMoRefreshListView.OnRefreshListener;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.RestAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class ShareFragment extends Fragment implements
		OnRefreshListener, OnCancelListener {

	//private HeaderLayout mHeaderLayout;
	private MoMoRefreshListView mMmrlvList;
//	private ShareListAdapter mAdapter;
	//private NearByPeople mPeople;
	//private NearByPeopleProfile mProfile;

//	private List<Feed> mFeeds;
//	private View chatView;
//	private Context context;
//	private List<Object> items = new ArrayList<Object>();
	
	protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View chatView = inflater.inflate(R.layout.fragment_share, container,
				false);
//		this.chatView = chatView;
//		this.context = inflater.getContext();
		mMmrlvList = (MoMoRefreshListView) chatView.findViewById(R.id.otherfeedlist_mmrlv_list);

//		initViews();
		initEvents();
		init();
		return chatView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	protected void initViews() {


//		RestAdapter restAdapter = new RestAdapter.Builder()
//				.setEndpoint("http://192.168.10.10:9000/zhiqiang/")
//				.setLogLevel(RestAdapter.LogLevel.FULL)
//				.build();
//
//		ShareService service = restAdapter.create(ShareService.class);
//		Observable<JsonObject> json = service.listRepos();
//		json.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(new Action1<JsonObject>() {
//					@Override
//					public void call(JsonObject jo) {
//						Log.e("retrofit", "***[" + jo.toString() + "]");
//					}
//				});

	}

	protected void initEvents() {
		mMmrlvList.setOnRefreshListener(this);
		mMmrlvList.setOnCancelListener(this);
	}

	private void init() {
		mMmrlvList.setItemsCanFocus(false);

		Context context = this.getActivity().getBaseContext();
		String json = AssetsUtils.getFromAssets(context, "share_content.json");
        final List<ShareContent> items = JSON.parseArray(json, ShareContent.class);

		ShareListAdapter mAdapter = new ShareListAdapter(context, items);
		mMmrlvList.setAdapter(mAdapter);
	}

	@Override
	public void onCancel() {
		mMmrlvList.onRefreshComplete();
	}
	
	protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
		mAsyncTasks.add(asyncTask.execute());
	}
	
	@Override
	public void onRefresh() {
		putAsyncTask(new AsyncTask<Void, Void, Boolean>() {

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
		});
	}
	
//	public void testListAdd(){
//		Intent intent = new Intent(getActivity(),ShareActivity.class);
//        getActivity().startActivityForResult(intent, 2);
//	}
}
