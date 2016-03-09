package com.xeehoo.health.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.presenter.MyAccountPresenter;
import com.xeehoo.health.view.MyAccountView;

/**
 * Created by wangzunhui on 2016/2/5.
 */
public class MyAccountFragment extends Fragment {
    private MyAccountView myAccountView;
    private MyAccountPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("YDZC", "onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        Context context = this.getActivity();
        myAccountView = new MyAccountView(context, container);
        presenter = new MyAccountPresenter();
        presenter.onCreate(context, myAccountView);

        return myAccountView.getView();
    }

    public void showLogin(){
        presenter.showLogin();
    }
}
