package com.xeehoo.health.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xeehoo.health.util.CommonUtil;

/**
 * Created by WIN10 on 2016/2/2.
 */
public class CircleProgressBar extends View {
    private int maxProgress = 100;
    private int progress = 30;
    private int progressStrokeWidth = 10;
    private String unit = "%";
    private Context context;

    //画圆所在的距形区域
    RectF oval;
    Paint paint;
    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO 自动生成的构造函数存根
        this.context = context;
        oval = new RectF();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 自动生成的方法存根
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        if(width!=height)
        {
            int min=Math.min(width, height);
            width=min;
            height=min;
        }

        paint.setAntiAlias(true); // 设置画笔为抗锯齿
        paint.setColor(Color.rgb(0xcc, 0xcc, 0xcc)); // 设置画笔颜色
        canvas.drawColor(Color.TRANSPARENT); // 白色背景
        paint.setStrokeWidth(progressStrokeWidth); //线宽
        paint.setStyle(Paint.Style.STROKE);

        oval.left = progressStrokeWidth / 2; // 左上角x
        oval.top = progressStrokeWidth / 2; // 左上角y
        oval.right = width - progressStrokeWidth / 2; // 左下角x
        oval.bottom = height - progressStrokeWidth / 2; // 右下角y

        canvas.drawArc(oval, -90, 360, false, paint); // 绘制白色圆圈，即进度条背景
        paint.setColor(Color.rgb(0x11, 0x72, 0xbd));//(0x57, 0x87, 0xb6));//ff9f08 //1172bd
        canvas.drawArc(oval, -90, ((float) progress / maxProgress) * 360, false, paint); // 绘制进度圆弧，这里是蓝色

        paint.setStrokeWidth(1);
        String text2 = progress + unit; //amount + "万";// + progress + "%";
        int textHeight = height / 4;
        paint.setTextSize(CommonUtil.sp2px(context, 16.0f));

//        String text1 = "可投金额";
//        int textWidth1 = (int) paint.measureText(text1, 0, text1.length());
        int textWidth2 = (int) paint.measureText(text2, 0, text2.length());
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(0x99, 0x99, 0x99));
//        canvas.drawText(text1, width / 2 - textWidth1 / 2, height / 2 + textHeight / 2 - 15, paint);
        canvas.drawText(text2, width / 2 - textWidth2 / 2, height / 2 + textHeight/2 , paint);
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        this.invalidate();
    }

    public void setUnit(String unit){
        this.unit = unit;
        this.invalidate();
    }

    /**
     * 非ＵＩ线程调用
     */
    public void setProgressNotInUiThread(int progress) {
        this.progress = progress;
        this.postInvalidate();
    }
}
