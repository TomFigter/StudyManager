package com.thtf.leanpackage.spark;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.custom
 * @date 2019-11-22 08:47
 * @描述 我也不知道写啥 就先打个基础吧
 */
public class SparkSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    public static int WIDTH;
    private SurfaceHolder mHolder;
    //    private Canvas mCanvas;
    private Canvas mCanvasLine;
    private boolean isRun;
    private float X, Y;
    private Paint mSparkPaint;
    private float mLastY;
    private float mLastX;
    private SparkManager sparkManager;


    public SparkSurfaceView(Context context) {
        this(context, null);
    }

    public SparkSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SparkSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        WIDTH = displayMetrics.widthPixels;
        init();
    }

    /**
     * 初始操作
     */
    private void init() {
        mSparkPaint = new Paint();
        sparkManager = new SparkManager();
        mSparkPaint.setAntiAlias(true);
        mHolder = this.getHolder();
        mHolder.addCallback(this);
    }

    Thread mThread = null;

    /**
     * 事件分发，事件监控
     *
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getPointerCount()) {
            case 1:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isRun = true;
                        sparkManager.isActive = true;
                        mLastX = event.getX();
                        mLastY = event.getY();
                        Log.d("此次点击", "X -> " + mLastX + ",Y -> " + mLastY);
                        mThread = new Thread(this);
                        mThread.start();
                    case MotionEvent.ACTION_MOVE:
                        X = event.getX();
                        Y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        sparkManager.isActive = false;
                        isRun = false;
                        mThread = null;
                        break;
                }
                break;
            case 2:
                break;
        }

        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawBackground();
    }

    /**
     * 清 屏
     */
    private void drawBackground() {
        Canvas mCanvas = mHolder.lockCanvas();
        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
//        mCanvas.drawColor(Color.BLACK);
        mHolder.unlockCanvasAndPost(mCanvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRun = false;
    }

    /**
     * Thread
     */
    @Override
    public void run() {
        int[][] sparkArray = new int[400][10];
        while (isRun) {
            Canvas mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            synchronized (mHolder) {
                for (int[] n : sparkArray) {
                    sparkManager.drawSpark(mCanvas, (int) X, (int) Y, n);
                }
                mHolder.unlockCanvasAndPost(mCanvas);
            }

            mCanvasLine = mHolder.lockCanvas();
            synchronized (mHolder) {
                sparkManager.drawSparkLine(mCanvas, mLastX, mLastY, X, Y);
                mLastX = X;
                mLastY = Y;
                mHolder.unlockCanvasAndPost(mCanvasLine);
            }
        }
    }
}
