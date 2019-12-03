package com.thtf.leanpackage.common_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.common_view
 * @date 2019-12-03 11:14
 * @描述
 */
public class PathBaseView extends BaseView {

    public PathBaseView(Context context) {
        super(context);
    }

    public PathBaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        centerX = mWidth / 2;
        centerY = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCommon(canvas);
//        drawLine(canvas);
//        drawRect(canvas);
        drawMixture(canvas);
    }

    /**
     * Path画线
     * 坐标移动
     * 顶点移动
     * 封闭图形
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Path path = new Path();
        path.lineTo(200, 200);
//        path.moveTo(200,100);   //移动坐标，不变绘制顶点
//        path.setLastPoint(200,100);  //移动顶点到指定位置
        path.lineTo(200, 0);
        path.close();   //自动封闭图形
        mPaint.setColor(randomColor());
        canvas.drawPath(path, mPaint);
    }

    /**
     *
     * @param canvas
     */
    private void drawRect(Canvas canvas) {
        Path path = new Path();
        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);
        path.setLastPoint(-300, 300);                // <-- 重置最后一个点的位置
        canvas.drawPath(path, mPaint);
    }
    private void drawMixture(Canvas canvas){
        canvas.scale(1,-1);
        Path path=new Path();
        Path circlePath=new Path();
        path.addRect(-200,-200,200,200, Path.Direction.CW);
        circlePath.addCircle(0,0,100, Path.Direction.CW);
        path.addPath(circlePath,0,200);
        mPaint.setColor(randomColor());
        canvas.drawPath(path,mPaint);
    }
}
