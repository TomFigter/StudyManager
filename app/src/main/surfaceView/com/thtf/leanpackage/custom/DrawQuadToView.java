package com.thtf.leanpackage.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.custom
 * @date 2019-11-22 15:24
 * @描述 二阶贝塞尔曲线
 */
public class DrawQuadToView extends View {
    private Paint paint;
    private int centerX;
    private int centerY;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int eventX;
    private int eventY;
    //圆的半径
    private float radius;

    public DrawQuadToView(Context context) {
        this(context, null);
    }

    public DrawQuadToView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawQuadToView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
    }

    //测量大小完成后回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        startX = centerX - 250;
        startY = centerY;
        endX = centerX + 250;
        endY = centerY;
        eventX = centerX;
        eventY = centerY - 250;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GRAY);
        //画3个点
        canvas.drawCircle(startX, startY, 8, paint);
        canvas.drawCircle(endX, endY, 8, paint);
        canvas.drawCircle(eventX, eventY, 8, paint);

        //绘制曲线
        paint.setStrokeWidth(3);
        canvas.drawLine(startX, centerY, eventX, eventY, paint);
        canvas.drawLine(endX, centerY, eventX, eventY, paint);

        //画二阶贝塞尔曲线
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.STROKE);
        @SuppressLint("DrawAllocation") Path path = new Path();
        radius = (float) Math.sqrt((eventX - endX) * (eventX - endX) + (eventY - endY) * (eventY - endY));
        path.moveTo(startX, startY);
        path.quadTo(eventX, eventY, endX, endY);
        path.addCircle(startX+Math.abs(endX-startX)/2, startY,radius, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                eventX = (int) event.getX();
                eventY = (int) event.getY();
                //局部刷新
                invalidate();
                break;
        }
        return true;
    }

}