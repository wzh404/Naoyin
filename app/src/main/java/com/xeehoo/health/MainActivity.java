package com.xeehoo.health;

import java.io.DataOutputStream;

import com.xeehoo.health.activity.ChangePwdActivity;
import com.xeehoo.health.activity.InvestActivity;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.MyProductActivity;
import com.xeehoo.health.activity.PayActivity;
import com.xeehoo.health.activity.ProductsActivity;
import com.xeehoo.health.common.presenter.MainPresenter;
import com.xeehoo.health.common.view.MainView;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.model.Product;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private MainPresenter presenter;
    private MainView mainView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        mainView = new MainView();
        mainView.init(this, null);
		setContentView(mainView.getView());
        presenter = new MainPresenter();
        presenter.setFragmentManager(this.getSupportFragmentManager());
        presenter.onCreate(this, mainView);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("result", " resultCode " + resultCode);
        if (resultCode == 2){
            FragmentTabHost fragmentTabHost = mainView.get(android.R.id.tabhost);
            fragmentTabHost.setCurrentTab(2);
        }
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.e("Main", "onDestroy MainActivity");
	}

	public void loginOnClick(View view){
		Intent saveIntent = new Intent(MainActivity.this, LoginActivity.class);
		startActivityForResult(saveIntent, 1);
	}

//	public void myProductClick(View view){
//		Intent saveIntent = new Intent(MainActivity.this, MyProductActivity.class);
//		startActivity(saveIntent);
//	}

	public void payProduct(Product product){
		Intent intent = new Intent(MainActivity.this, InvestActivity.class);
		intent.putExtra("product", product);

		startActivity(intent);
	}

	public void route(String code){
        if ("0201".equalsIgnoreCase(code)){
            startWebview("注册第三方托管账户", "http://192.168.0.173:8080/app/fuiou/register?token=" + BrainApplication.token);
        }
		else if ("0202".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, ChangePwdActivity.class);
            startActivity(saveIntent);
		}
        else if ("0203".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, ChangePwdActivity.class);
            startActivity(saveIntent);
        }
        else if ("0301".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, MyProductActivity.class);
            startActivity(saveIntent);
        }
        else if ("0302".equalsIgnoreCase(code)){
            startWebview("在线充值", "http://192.168.0.173:8080/app/fuiou/recharge?amt=10000&token=" + BrainApplication.token);
        }
        else if ("0303".equalsIgnoreCase(code)){
            startWebview("在线提现", "http://192.168.0.173:8080/app/fuiou/withdraw?amt=10000&token=" + BrainApplication.token);
        }
	}

    private void startWebview(String title, String url) {
        Bundle bundle = new Bundle();

        bundle.putString("url", url);
        bundle.putString("title", title);
        Intent intent = new Intent(this, BaseWebActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

}
