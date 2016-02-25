package com.xeehoo.health.common.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xeehoo.health.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by WIN10 on 2015/11/14.
 */
public abstract class AbstractView  implements IView{
    private SweetAlertDialog dialog;
    protected View view;
    private Context context;

    public void init(Context context, ViewGroup container, int res){
        this.context = context;
        view = LayoutInflater.from(context).inflate(res, container, false);
    }

    public void initDialog(){
        dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        dialog.setTitleText("Loading");
        dialog.setCancelable(false);
    }

    public void showDialog(){
        dialog.show();
    }

    public void dismissDialog(){
        dialog.dismiss();
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public <T extends View> T get(int id){
        return (T)getView().findViewById(id);
    }
}
