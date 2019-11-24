package com.thtf.leanpackage.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Date;

public class SparkView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean isRun;
    private SparkManager sparkManager;
    //当前触摸的X,Y坐标
    private double x, y;
    //屏幕高宽
    public static int WIDTH, HEIGHT;

    public SparkView(Context context) {
        super(context, null);

    }

    public SparkView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        init(context);
    }

    public SparkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        // 设置视图宽高（像素）
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        WIDTH = metric.widthPixels;
        HEIGHT = metric.heightPixels;

        //火花管理器
        sparkManager = new SparkManager();
        mHolder = this.getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void run() {

        // 火花数组
        int[][] sparks = new int[400][100];
        Date date = null;
        while (isRun) {
            date = new Date();
            try {
                mCanvas = mHolder.lockCanvas(null);
                if (mCanvas != null) {
                    synchronized (mHolder) {
                        //清屏
                        mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                        //循环绘制所有火花
                        for (int[] n : sparks) {
                            sparkManager.drawSpark(mCanvas, (int) x, (int) y, n);
                        }
                        //控制帧数
                        Thread.sleep(Math.max(0, 30 - (new Date().getTime() - date.getTime())));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mCanvas != null) {
                    mHolder.unlockCanvasAndPost(mCanvas);
                }
            }
        }
    }

    /**
     * 获取当前X,Y坐标
     * 拦截Touch事件
     *
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取触摸点
        switch (event.getPointerCount()) {
            //单点触摸
            case 1:
                switch (event.getAction()) {
                    //只获取点击和移动事件
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        sparkManager.isActive = true;
                        x = event.getX();
                        y = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        sparkManager.isActive = false;
                        break;
                    default:
                        break;
                }
                break;
        }
        return true;
    }

    // Surface的大小发生改变时调用
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawBackground();
    }

    // Surface创建时激发，一般在这里调用画面的线程
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRun = true;
        new Thread(this).start();
    }

    // 销毁时激发，一般在这里将画面的线程停止、释放。
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRun = false;
    }

    private void drawBackground() {
        mCanvas = mHolder.lockCanvas();
        mCanvas.drawColor(Color.GRAY);
        mHolder.unlockCanvasAndPost(mCanvas);
    }
}
