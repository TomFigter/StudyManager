package com.thtf.leanpackage.base_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.base_view
 * @date 2019-11-28 16:10
 * @描述 坐标轴
 */
public class CoordinateBaseView extends View {
    private final Context context;
    private Paint paint;
    private int mHeight;
    private int mWidth;
    private Random mRandom = new Random();
    private int centerY;
    private int centerX;

    public CoordinateBaseView(Context context) {
        this(context, null);
    }

    public CoordinateBaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoordinateBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    /**
     * 初始init操作
     */
    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        centerX = mWidth / 2;
        centerY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawCommon(canvas);
//        drawSpinSquare(canvas);
//        drawRotate(canvas, 0, 0);
        drawCircle(canvas);
    }

    /**
     * 通用绘制, 坐标轴
     *
     * @param canvas
     */
    private void drawCommon(Canvas canvas) {
        paint.setColor(randomColor());
        canvas.drawLine(0, centerY, mWidth, centerY, paint);
        canvas.drawLine(centerX, 0, centerX, mHeight, paint);
    }

    /**
     * 螺旋圆
     * scale  --> 按比例缩放
     * rotate --> 旋转角度，可叠加
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        canvas.translate(centerX, centerY);
        for (int count = 0; count < 10; count++) {
            canvas.scale(0.8f, 0.8f);
            paint.setColor(randomColor());
            canvas.drawCircle(0, 0, 400, paint);
            paint.setColor(randomColor());
            canvas.drawCircle(0, 0, 360, paint);
            for (int index = 0; index <= 36; index++) {
                paint.setColor(randomColor());
                canvas.drawLine(0, 360, 0, 400, paint);
                canvas.rotate(10);
            }
        }
    }

    /**
     * 沿中心坐标点 绘制回旋正方形
     *
     * @param canvas
     */
    private void drawSpinSquare(Canvas canvas) {
        canvas.translate(centerX, centerY);
        @SuppressLint("DrawAllocation") RectF rectF = new RectF(-400, -400, 400, 400);
        paint.setColor(Color.BLACK);
        canvas.drawRect(rectF, paint);
        for (int count = 0; count < 50; count++) {
            canvas.scale(0.9f, 0.9f);
            paint.setColor(randomColor());
            canvas.drawRect(rectF, paint);
        }
    }

    /**
     * 旋转叠加 中心点也叠加
     *
     * @param canvas
     * @param offsetX 旋转中心 X轴偏移量
     * @param offsetY 旋转中心 Y轴偏移量
     */
    private void drawRotate(Canvas canvas, int offsetX, int offsetY) {
        canvas.translate(centerX, centerY);
        RectF rectF = new RectF(0, -400, 400, 0);
        paint.setColor(randomColor());
        canvas.drawRect(rectF, paint);
        canvas.rotate(180, offsetX, offsetY);  //旋转180度--> 旋转中心向右偏移200个单位
        paint.setColor(randomColor());
        canvas.drawRect(rectF, paint);

        //绘制旋转中心点
        paint.setColor(randomColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(offsetX, offsetY, 8, paint);
    }

    /**
     * 随机颜色值
     *
     * @return
     */
    private int randomColor() {
        return Color.argb(255,
                mRandom.nextInt(128) + 128,
                mRandom.nextInt(128) + 128,
                mRandom.nextInt(128) + 128);
    }
}
