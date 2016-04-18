package com.xeehoo.health;

import com.xeehoo.health.activity.BalanceActivity;
import com.xeehoo.health.activity.ChangePwdActivity;
import com.xeehoo.health.activity.InvestActivity;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.activity.MyProductActivity;
import com.xeehoo.health.activity.UserActivity;
import com.xeehoo.health.activity.UserSettingActivity;
import com.xeehoo.health.common.presenter.MainPresenter;
import com.xeehoo.health.common.view.MainView;
import com.xeehoo.health.common.webview.BaseWebActivity;
import com.xeehoo.health.model.Product;
import com.xeehoo.health.util.AppConfig;
import com.xeehoo.health.util.AssetsUtils;
import com.xeehoo.health.view.MyAccountItemView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
    public final static int ACTIVITY_REQUEST_LOGIN = 1;
    public final static int ACTIVITY_REQUEST_LOGOUT = 2;

	private MainPresenter presenter;
    private MainView mainView;
//    private MyAccountItemView accountItemView;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        AssetsUtils.initParas(this);
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
//        Log.e("result", " resultCode " + resultCode);
//        if (resultCode == 2){
//            FragmentTabHost fragmentTabHost = mainView.get(android.R.id.tabhost);
//            fragmentTabHost.setCurrentTab(2);
//        }
//        else
        if (resultCode == 9){
            presenter.showLogin();
        }
        else if (resultCode == 10){
            presenter.showLogin();
        }
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
	}

	public void loginOnClick(View view){
		Intent saveIntent = new Intent(MainActivity.this, LoginActivity.class);
		startActivityForResult(saveIntent, MainActivity.ACTIVITY_REQUEST_LOGIN);
	}

    public void call(View view){
        Intent intent=new Intent("android.intent.action.CALL", Uri.parse("tel:400-411-7777"));
        startActivity(intent);
    }

//	public void myProductClick(View view){
//		Intent saveIntent = new Intent(MainActivity.this, MyProductActivity.class);
//		startActivity(saveIntent);
//	}

	public void payProduct(Product product){
        startWebview(
                product.getProductName(),
                AppConfig.WEB_URL + "/app/product/details?product_id=" + product.getProductId(),
                product);
	}

//    public void settingOnClick(View view){
////        Toast.makeText(this, getAccountItemView().getCode(), Toast.LENGTH_SHORT).show();
//        BrainApplication.isLogin = false;
//        BrainApplication.token = null;
////        getAccountItemView().setItem();
//    }

	public void route(String code){
        if ("0201".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(saveIntent);
        }
		else if ("0202".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, ChangePwdActivity.class);
            saveIntent.putExtra("type", "login");
            startActivity(saveIntent);
		}
        else if ("0203".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, ChangePwdActivity.class);
            saveIntent.putExtra("type", "pay");
            startActivity(saveIntent);
        }
        else if ("0301".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, MyProductActivity.class);
            startActivity(saveIntent);
        }
        else if ("0302".equalsIgnoreCase(code)) {
            if (BrainApplication.isAccount) {
                Intent saveIntent = new Intent(MainActivity.this, BalanceActivity.class);
                saveIntent.putExtra("type", "recharge");
                startActivity(saveIntent);
            }
            else{
                Toast.makeText(this, "还没有注册托管账户！", Toast.LENGTH_SHORT).show();
            }
        }
        else if ("0303".equalsIgnoreCase(code)){
            if (BrainApplication.isAccount) {
                Intent saveIntent = new Intent(MainActivity.this, BalanceActivity.class);
                saveIntent.putExtra("type", "withdraw");
                startActivity(saveIntent);
            }
            else{
                Toast.makeText(this, "还没有注册托管账户！", Toast.LENGTH_SHORT).show();
            }
        }
        else if ("0500".equalsIgnoreCase(code)){
            Intent saveIntent = new Intent(MainActivity.this, UserSettingActivity.class);
            startActivityForResult(saveIntent, MainActivity.ACTIVITY_REQUEST_LOGOUT);
        }
	}

//    public MyAccountItemView getAccountItemView() {
//        return accountItemView;
//    }
//
//    public void setAccountItemView(MyAccountItemView accountItemView) {
//        this.accountItemView = accountItemView;
//    }

    private void startWebview(String title, String url, Product product) {
        Bundle bundle = new Bundle();

        bundle.putString("url", url);
        bundle.putString("title", title);
        bundle.putString("type", "invest");
        bundle.putParcelable("product", product);
        Intent intent = new Intent(this, BaseWebActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
