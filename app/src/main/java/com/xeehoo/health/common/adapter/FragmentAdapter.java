package com.xeehoo.health.common.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import com.xeehoo.health.fragment.MyFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> fragmentList = new ArrayList<Fragment>();

	public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
		super(fm);
		this.fragmentList = fragmentList;
	}

	@Override
	public Fragment getItem(int position) {
		return fragmentList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentList.size();
	}

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Fragment f = (Fragment)super.instantiateItem(container, position);
//        if (position == 2){
//            MyFragment my = (MyFragment)f;
////            if (my.getState() == 1){
//                FragmentTransaction transaction = my.getFragmentManager().beginTransaction();
//                transaction.remove(f);
//                transaction.add(container.getId(), f, f.getTag());
//                transaction.attach(f);
////                transaction.replace(container.getId(), new MyFragment());
////                transaction.addToBackStack(null);
//                transaction.commit();
////            }
//        }
//        return f;
//    }
//
//
//    public void remove(){
//        fragmentList.remove(2);
//    }
//
//    public void add(Fragment f){
//
//        fragmentList.add(f);
//    }
//
	@Override
	public int getItemPosition(Object object) {
////        if (object instanceof MyFragment){
////            MyFragment f = (MyFragment)object;
//////            if (f.getState() == 1){
////                Log.e("adaptor", "------------------state");
//////                f.setState(0);
////                return POSITION_NONE;
//////            }
////        }
////		return super.getItemPosition(object);
        return POSITION_NONE;
	}
}
