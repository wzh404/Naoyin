package com.xeehoo.health.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.xeehoo.health.BrainApplication;
import com.xeehoo.health.MainActivity;
import com.xeehoo.health.R;
import com.xeehoo.health.presenter.LoginPresenter;
import com.xeehoo.health.view.LoginView;
import com.xeehoo.health.view.MyView;

public class MyFragment extends Fragment  {
//    private View view;

    @Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
        Log.e("MY", "----------onCreateView");
        MyView view = new MyView(inflater.getContext(), container);
        return view.getView();

//        view = inflater.inflate(R.layout.fragment_my, null);
//
//        renderView();
//        if ("0".equalsIgnoreCase(BrainApplication.token)) {
//            state = 0;
//            LinearLayout layout = (LinearLayout)view.findViewById(R.id.activity_login_layout);
//            layout.setVisibility(View.VISIBLE);
//        }
//        else{
//            state = 1;
//            LinearLayout layout = (LinearLayout)view.findViewById(R.id.activity_my_layout);
//            layout.setVisibility(View.GONE);
//        }

//		if ("0".equalsIgnoreCase(BrainApplication.token)){
//            LoginView loginView = new LoginView(inflater.getContext(), container);
//            final LoginPresenter presenter = new LoginPresenter();
//            presenter.onCreate(inflater.getContext(), null);
//
//            Button loginBtn = (Button)loginView.get(R.id.btn_login);
//            loginBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    presenter.login("18611330404", "123456");
//                }
//            });
//
//			return loginView.getView();
//		}else{
//            MyView view = new MyView(inflater.getContext(), container);
//			return view.getView();
//		}
//
//        return view;
	}

//    public void renderView(){
//        Log.e("MY", "----------renderView");
//        LinearLayout layout1 = (LinearLayout)view.findViewById(R.id.activity_login_layout);
//        LinearLayout layout2 = (LinearLayout)view.findViewById(R.id.activity_my_layout);
//        if ("0".equalsIgnoreCase(BrainApplication.token)) {
//            Log.e("MY", "----------renderView1");
//            layout1.setVisibility(View.VISIBLE);
//            layout2.setVisibility(View.GONE);
//        }
//        else{
//            Log.e("MY", "----------renderView2");
//            layout1.setVisibility(View.GONE);
//            layout2.setVisibility(View.VISIBLE);
//        }
//    }

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

//    public void update(){
//        Log.e("MY", "----------update");
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.)
//    }
}
