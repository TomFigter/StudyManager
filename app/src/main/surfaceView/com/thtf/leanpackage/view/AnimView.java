package com.thtf.leanpackage.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AnimView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder = null;

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);
        this.setZOrderOnTop(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public synchronized void doDraw(int tt, int width, int height, Paint p, int argb1, int argb2) {
        Canvas canvas = holder.lockCanvas();
        if (canvas == null)
            return;
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        p.setColor(argb1);
        canvas.drawCircle(width / 2, height, tt, p);
        if (tt - 300 > 0) {
            p.setColor(argb2);
            canvas.drawCircle(width / 2, height, tt - 300, p);
        }
        holder.unlockCanvasAndPost(canvas);
    }
    public void startAnimation(){
        try{
            Thread thread=new Thread(new MyLoop());
            thread.start();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    class  MyLoop implements Runnable {

        @Override
        public void run() {
            int width=getWidth();
            int height=getHeight();
            Paint p=new Paint();
            int argb1=Color.argb(80,250,0,0);
            int argb2=Color.argb(120,250,0,0);
            p.setAntiAlias(true);//反锯齿
            for (int i=0;i<height+500;i+=50){
                doDraw(i,width,height,p,argb1,argb2);
            }
        }
    }

}