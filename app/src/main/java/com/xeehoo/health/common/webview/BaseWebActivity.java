package com.xeehoo.health.common.webview;

import com.xeehoo.health.R;
import com.xeehoo.health.activity.InvestActivity;
import com.xeehoo.health.activity.PayActivity;
import com.xeehoo.health.model.MyProduct;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.model.Transfer;
import com.xeehoo.health.util.CommonUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;


/**
 * @Description:WebView界面，带自定义进度条显示
 * @author 
 */ 
public class BaseWebActivity extends Activity {
	protected ProgressWebView mWebView;
    private String  type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseweb);

		mWebView = (ProgressWebView) findViewById(R.id.baseweb_webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JsOperation(), "client");
		
		TextView tv = (TextView)findViewById(R.id.webview_title);
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString("url");
		String title = bundle.getString("title");
        type = bundle.getString("type");
		tv.setText(title);

        if (type != null){
            TextView textView = (TextView)findViewById(R.id.bt_rengou);
            textView.setVisibility(View.VISIBLE);
            if ("myProduct".equalsIgnoreCase(type)){
                textView.setText("我要转让");
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mWebView.getLayoutParams();
            layoutParams.bottomMargin = CommonUtil.dip2px(this, 85.0f);
            mWebView.setLayoutParams(layoutParams);

            mWebView.setClickable(true);
            mWebView.setEnabled(true);
        }
		
		mWebView.loadUrl(url);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mWebView = null;
	}

	public void exitOnClick(View view) {
		this.finish();
	}

    public void webviewOnClick(View view){
//        Toast.makeText(this, "type is " + type, Toast.LENGTH_SHORT).show();
        if ("product".equalsIgnoreCase(type)){
            Product product = getIntent().getParcelableExtra("product");
            Intent intent = new Intent(BaseWebActivity.this, InvestActivity.class);
            intent.putExtra("product", product);

            startActivity(intent);
        }
        else if ("transfer".equalsIgnoreCase(type)){
            Transfer transfer = getIntent().getParcelableExtra("transfer");

            Intent intent = new Intent(BaseWebActivity.this, PayActivity.class);
            intent.putExtra("type", "transfer");
            intent.putExtra("name", "转让 " + transfer.getProductName());
            intent.putExtra("amount", transfer.getAmount().toPlainString());
            intent.putExtra("payId", transfer.getTransferId());

            startActivity(intent);
        }
        else if ("myProduct".equalsIgnoreCase(type)){
            MyProduct myProduct = getIntent().getParcelableExtra("myProduct");
            Toast.makeText(this, myProduct.getInvestRate().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class JsOperation {
        @JavascriptInterface
        public void pageClick(String paramString)
        {
            String[] paras = paramString.split("\\|");
            for (String p : paras){
                Log.e("webview", p);
            }
        }
    }

}
