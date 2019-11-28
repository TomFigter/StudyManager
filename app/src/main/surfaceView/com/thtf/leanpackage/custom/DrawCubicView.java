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
 * @date 2019-11-22 16:07
 * @描述 三阶贝塞尔曲线
 */
public class DrawCubicView extends View {
    private Paint paint;
    private int centerX;
    private int centerY;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int leftX;
    private int leftY;
    private int rightX;
    private int rightY;
    private boolean isMoveLeft;
    private int startTopX;
    private int startTopY;
    private int centerLeftX;
    private int centerLeftY;
    private int centerRightX;
    private int centerRightY;
    private int bottomLeftX;
    private int bottomLeftY;
    private int bottomRightX;
    private int bottomRightY;

    public DrawCubicView(Context context) {
        this(context, null);
    }

    public DrawCubicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawCubicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    //测量大小完成以后回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;

        startTopX = centerX;
        startTopY = centerY - 200;
        centerLeftX = centerX - 250;
        centerLeftY = centerY;
        centerRightX = centerX + 250;
        centerRightY = centerY;
        bottomLeftX = centerX - 125;
        bottomLeftY = centerY + 250;
        bottomRightX = centerX + 125;
        bottomRightY = centerY + 250;


        startX = centerX - 250;
        startY = centerY;
        endX = centerX + 250;
        endY = centerY;
        leftX = startX;
        leftY = centerY - 250;
        rightX = endX;
        rightY = endY - 250;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GRAY);
        //画个点
        canvas.drawCircle(startX, startY, 8, paint);
        canvas.drawCircle(endX, endY, 8, paint);
        canvas.drawCircle(leftX, leftY, 8, paint);
        canvas.drawCircle(rightX, rightY, 8, paint);

        paint.setStrokeWidth(3);
        canvas.drawLine(startX, startY, leftX, leftY, paint);
        canvas.drawLine(leftX, leftY, rightX, rightY, paint);
        canvas.drawLine(rightX, rightY, endX, endY, paint);
        //偷偷的画个五角星，展示一下子 嘿。。
        canvas.drawLine(startTopX,startTopY,bottomLeftX,bottomLeftY,paint);
        canvas.drawLine(bottomLeftX,bottomLeftY,centerRightX,centerRightY,paint);
        canvas.drawLine(centerRightX,centerRightY,centerLeftX,centerLeftY,paint);
        canvas.drawLine(centerLeftX,centerLeftY,bottomRightX,bottomRightY,paint);
        canvas.drawLine(bottomRightX,bottomRightY,startTopX,startTopY,paint);
        //画二阶贝塞尔曲线
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        @SuppressLint("DrawAllocation") Path path = new Path();
        path.moveTo(startX, startY);
        path.cubicTo(leftX, leftY, rightX, rightY, endX, endY);
        canvas.drawPath(path, paint);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                decideTochPoint((int) event.getX(), (int) event.getY());
                if (isMoveLeft) {
                    leftX = (int) event.getX();
                    leftY = (int) event.getY();
                } else {
                    rightX = (int) event.getX();
                    rightY = (int) event.getY();
                }
                invalidate();
                break;
        }
        return true;
    }

    private void decideTochPoint(int eventX, int eventY) {
        //计算的是坐标轴的距离
//        double distanceLeft = Math.sqrt(Math.pow((eventX - leftX), 2) + Math.pow((eventY - leftY), 2));
//        double distanceRight = Math.sqrt(Math.pow((eventX - rightX), 2) + Math.pow((eventY - rightY), 2));
        //计算 X 坐标距离
        double distanceLeft = Math.abs(eventX - leftX);
        double distanceRight = Math.abs(eventX - rightX);
        if (distanceLeft > distanceRight)
            isMoveLeft = false;
        else
            isMoveLeft = true;
    }
}
