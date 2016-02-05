package com.xeehoo.health.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.presenter.MyAccountPresenter;
import com.xeehoo.health.view.MyAccountView;

/**
 * Created by wangzunhui on 2016/2/5.
 */
public class MyAccountFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Context context = this.getActivity().getBaseContext();
        MyAccountView view = new MyAccountView(context, container);
        MyAccountPresenter presenter = new MyAccountPresenter();
        presenter.onCreate(context, view);

        return view.getView();
    }
}
