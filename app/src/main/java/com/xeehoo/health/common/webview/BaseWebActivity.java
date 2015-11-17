package com.xeehoo.health.common.webview;

import com.xeehoo.health.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;



/**
 * @Description:WebView界面，带自定义进度条显示
 * @author 
 */ 
public class BaseWebActivity extends Activity {

	protected ProgressWebView mWebView;
//	private ProgressBar web_progressbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseweb);

		mWebView = (ProgressWebView) findViewById(R.id.baseweb_webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		TextView tv = (TextView)findViewById(R.id.webview_title);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString("url");
		String title = bundle.getString("title");
		tv.setText(title);
		
		mWebView.loadUrl(url);
	}

//	protected void initData() {
//		Intent intent = getIntent();
//		Bundle bundle = intent.getExtras();
//		String url = bundle.getString("url");
//
//		// if(!TextUtils.isEmpty(url)&&TextUtils.isEmpty(title)){
//		mWebView.loadUrl(url);
//
//		// }
//
//	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mWebView = null;
	}
}
