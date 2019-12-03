package com.thtf.leanpackage.common_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.common_view
 * @date 2019-12-03 11:15
 * @描述
 */
public abstract class BaseView extends View {
    protected Context context;
    protected Random mRandom;
    protected Paint mPaint;
    protected float mHeight;
    protected float centerX;
    protected float centerY;
    protected float mWidth;

    public BaseView(Context context) {
        this(context,null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init();
    }

    protected void init(){
        mRandom=new Random();
        mPaint=new Paint();
        mPaint.setColor(randomColor());
        mPaint.setAntiAlias(true);
    }
    /**
     * 通用绘制, 坐标轴
     *
     * @param canvas
     */
    protected void drawCommon(Canvas canvas) {
        mPaint.setColor(randomColor());
        canvas.drawLine(0, centerY, mWidth, centerY, mPaint);
        canvas.drawLine(centerX, 0, centerX, mHeight, mPaint);
        canvas.translate(centerX,centerY);
    }
    /**
     * 随机颜色值
     *
     * @return
     */
    public int randomColor() {
        return Color.argb(255,
                mRandom.nextInt(128) + 128,
                mRandom.nextInt(128) + 128,
                mRandom.nextInt(128) + 128);
    }
}
