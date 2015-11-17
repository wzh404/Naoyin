package com.xeehoo.health;

import java.util.ArrayList;
import java.util.List;

import com.xeehoo.health.common.adapter.FragmentAdapter;
import com.xeehoo.health.common.presenter.MainPresenter;
import com.xeehoo.health.common.view.MainView;
import com.xeehoo.health.common.view.TabshotContentView;
import com.xeehoo.health.fragment.HomeFragment;
import com.xeehoo.health.fragment.BrainFragment;
import com.xeehoo.health.fragment.MyFragment;
import com.xeehoo.health.fragment.NurseFragment;
import com.xeehoo.health.fragment.ShareFragment;
import com.xeehoo.health.util.User;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        MainView mv = new MainView();
        mv.init(this, null);
		setContentView(mv.getView());
        MainPresenter presenter = new MainPresenter();
        presenter.setFragmentManager(this.getSupportFragmentManager());
        presenter.onCreate(this, mv);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("result", " ok ");
	}
}
