package com.xeehoo.health.fragment;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.xeehoo.health.R;
import com.xeehoo.health.plan.bean.DailyPlan;
import com.xeehoo.health.share.activity.ShareActivity;
import com.xeehoo.health.share.adapter.ShareListAdapter;
import com.xeehoo.health.share.bean.ShareContent;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.view.MoMoRefreshListView;
import com.xeehoo.health.view.MoMoRefreshListView.OnCancelListener;
import com.xeehoo.health.view.MoMoRefreshListView.OnRefreshListener;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ShareFragment extends Fragment implements
		OnRefreshListener, OnCancelListener {

	//private HeaderLayout mHeaderLayout;
	private MoMoRefreshListView mMmrlvList;
	private ShareListAdapter mAdapter;
	//private NearByPeople mPeople;
	//private NearByPeopleProfile mProfile;

//	private List<Feed> mFeeds;
	private View chatView;
	private Context context;
//	private List<Object> items = new ArrayList<Object>();
	
	protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View chatView = inflater.inflate(R.layout.fragment_share, container,
				false);
		this.chatView = chatView;
		this.context = inflater.getContext();
		
		initViews();
		initEvents();
		init();
		return chatView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_otherfeedlist);
//		initViews();
//		initEvents();
//		init();
//	}

//	@Override
	protected void initViews() {
		//mHeaderLayout = (HeaderLayout) findViewById(R.id.otherfeedlist_header);
		//mHeaderLayout.init(HeaderStyle.DEFAULT_TITLE);
		mMmrlvList = (MoMoRefreshListView) chatView.findViewById(R.id.otherfeedlist_mmrlv_list);
	}

//	@Override
	protected void initEvents() {
		mMmrlvList.setOnRefreshListener(this);
		mMmrlvList.setOnCancelListener(this);
	}

	private void init() {
		mMmrlvList.setItemsCanFocus(false);
		//mProfile = getIntent().getParcelableExtra("entity_profile");
		//mPeople = getIntent().getParcelableExtra("entity_people");
		//mHeaderLayout.setDefaultTitle(mProfile.getName() + "的动态", null);
//		getStatus();
		
//		items.add("abc");
//		items.add("abc");
		Context context = this.getActivity().getBaseContext();
		String json = AssetsUtils.getFromAssets(context, "share_content.json");
        final List<ShareContent> items = JSON.parseArray(json, ShareContent.class);
        
		mAdapter = new ShareListAdapter(context, items);
		mMmrlvList.setAdapter(mAdapter);
	}

//	private void getStatus() {
//		if (mFeeds == null) {
//			putAsyncTask(new AsyncTask<Void, Void, Boolean>() {
//
//				@Override
//				protected void onPreExecute() {
//					super.onPreExecute();
//					//showLoadingDialog("正在加载,请稍后...");
//				}
//
//				@Override
//				protected Boolean doInBackground(Void... params) {
//					mFeeds = new ArrayList<Feed>();
//					return JsonResolveUtils.resolveNearbyStatus(
//							OtherFeedListActivity.this, mFeeds,
//							mProfile.getUid());
//				}
//
//				@Override
//				protected void onPostExecute(Boolean result) {
//					super.onPostExecute(result);
//					dismissLoadingDialog();
//					if (!result) {
//						showCustomToast("数据加载失败...");
//					} else {
//						mAdapter = new OtherFeedListAdapter(mProfile, mPeople,
//								mApplication, OtherFeedListActivity.this,
//								mFeeds);
//						mMmrlvList.setAdapter(mAdapter);
//					}
//				}
//
//			});
//		}
//	}

	@Override
	public void onCancel() {
		//clearAsyncTask();
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
	
	public void testListAdd(){
		//items.add("aaa");
		//mAdapter.notifyDataSetChanged();
		Intent intent = new Intent(getActivity(),ShareActivity.class);  
        getActivity().startActivityForResult(intent, 2); 
	}
}
