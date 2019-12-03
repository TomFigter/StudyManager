package com.thtf.leanpackage.base_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.thtf.leanpackage.R;

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
    private Picture picture;

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
        picture = new Picture();
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
//        drawCircle(canvas);
//        drawSkew(canvas);
        drawPicture(canvas);
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
     * 绘制图片
     */
    private void drawPicture() {
        Canvas canvas = picture.beginRecording(mWidth, mHeight);
        canvas.translate(centerX, centerY);
        canvas.drawCircle(0, 0, 100, paint);
        picture.endRecording();
    }
    private void drawPicture(Canvas canvas) {
        canvas.translate(centerX, centerY);
        Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(),R.drawable.__leak_canary_icon);
//        canvas.drawBitmap(bitmap,new Matrix(),paint);
//        canvas.drawBitmap(bitmap,200,500,paint);
        Rect src=new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight()/2);
        Rect dst=new Rect(100,100,400,400);
        paint.setColor(randomColor());
        canvas.drawBitmap(bitmap,src,dst,paint);
    }

    /**
     * 图形错切
     * 已做的动作，都已存在 并会进行叠加
     *
     * @param canvas
     */
    private void drawSkew(Canvas canvas) {
        canvas.translate(centerX, centerY);
        RectF rectF = new RectF(0, 0, 200, 200);
        paint.setColor(randomColor());
        canvas.drawRect(rectF, paint);
        paint.setTextSize(36);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("完美世界", 100, 100, paint);
        canvas.skew(1, 0);  //水平错切 45度
        canvas.skew(0, 1);  //垂直错切 45度
        paint.setColor(randomColor());
        canvas.drawRect(rectF, paint);
    }

    /**
     * 螺旋圆
     * scale  --> 按比例缩放
     * rotate --> 旋转角度，可叠加
     *
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
