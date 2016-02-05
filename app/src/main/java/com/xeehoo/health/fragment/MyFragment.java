package com.xeehoo.health.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.activity.LoginActivity;
import com.xeehoo.health.presenter.LoginPresenter;
import com.xeehoo.health.rxjava.action.Result;
import com.xeehoo.health.rxjava.rxbus.RxBus;
import com.xeehoo.health.view.LoginView;
import com.xeehoo.health.view.MyView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MyFragment extends Fragment  {
    private MyView view;
    private Context context;

    @Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
        this.context = inflater.getContext();
        view = new MyView(context, container);

        if (!BrainApplication.isLogin) {
            initNologinMobile();
        }
        else{
            initLoginMobile();
        }
		return view.getView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

    private Action1 loginAction1 = new Action1<Result>() {
        @Override
        public void call(Result result) {
            if (result.isResult("my_fragment_login", "OK")) {
                initLoginMobile();
            }
        }
    };

    private void initNologinMobile(){
        TextView textView = view.get(R.id.item_my_account);
        textView.setText("请登录!");
        textView.setTextColor(Color.rgb(0xcc, 0xcc, 0xcc));

        Button logout = view.get(R.id.my_account_logout);
        logout.setVisibility(View.GONE);

        RelativeLayout relativeLayout = view.get(R.id.my_name);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<Result> observable = RxBus.get().register("my_fragment_login", Result.class);
                observable.subscribeOn(AndroidSchedulers.mainThread())
                        .subscribe(loginAction1);
                Intent saveIntent = new Intent(context, LoginActivity.class);
                startActivityForResult(saveIntent, 1);
            }
        });
    }

    private void initLoginMobile(){
        TextView textView = view.get(R.id.item_my_account);
        textView.setText(BrainApplication.mobile);
        textView.setTextColor(Color.parseColor("#000000"));

        Button logout = view.get(R.id.my_account_logout);
        logout.setVisibility(View.VISIBLE);

        RelativeLayout relativeLayout = view.get(R.id.my_name);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, BrainApplication.mobile, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
