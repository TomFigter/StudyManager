package com.thtf.leanpackage.custom;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.custom
 * @date 2019-11-22 17:07
 * @描述
 */
public class MyGiftView extends RelativeLayout {
    private int screenWidth;
    private int screenHeight;
    private LayoutParams layoutParams;
    private Drawable[] drawables = new Drawable[5];

    public MyGiftView(Context context) {
        this(context, null);
    }

    public MyGiftView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyGiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawables[0] = ContextCompat.getDrawable(getContext(), android.R.mipmap.sym_def_app_icon);
        drawables[1] = ContextCompat.getDrawable(getContext(), android.R.mipmap.sym_def_app_icon);
        drawables[2] = ContextCompat.getDrawable(getContext(), android.R.mipmap.sym_def_app_icon);
        drawables[3] = ContextCompat.getDrawable(getContext(), android.R.mipmap.sym_def_app_icon);
        drawables[4] = ContextCompat.getDrawable(getContext(), android.R.mipmap.sym_def_app_icon);
        layoutParams = new LayoutParams(100, 100);
        //代码设置布局方式，底部居中
        layoutParams.addRule(CENTER_HORIZONTAL, TRUE);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);
    }

    public void addImageView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[(int) (Math.random() * drawables.length)]);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
        setAnim(imageView).start();
        getBezierValueAnimator(imageView).start();
    }

    private ValueAnimator getBezierValueAnimator(View target) {
        //初始化一个贝塞尔计算器
        BezierEvaluator evaluator = new BezierEvaluator(getPointF(), getPointF());
        //这里最好画个图 理解一下 传入起点和终点
        PointF randomEndPoint = new PointF((float) (Math.random() * screenWidth), (float) (Math.random() * 50));
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, new PointF(screenWidth / 2, screenHeight), randomEndPoint);
        animator.addUpdateListener(new BezierListener(target));
        animator.setTarget(target);
        animator.setDuration(3000);
        return animator;
    }

    /**
     * 生产随机控制点
     *
     * @return
     */
    private PointF getPointF() {
        PointF pointF = new PointF();
        pointF.x = (float) (Math.random() * screenWidth);
        pointF.y = (float) (Math.random() * screenHeight / 4);
        return pointF;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        screenWidth = getMeasuredWidth();
        screenHeight = getMeasuredHeight();
    }

    private AnimatorSet setAnim(View view) {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.2f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.2f, 1f);

        AnimatorSet enter = new AnimatorSet();
        enter.setDuration(500); //持续时间
        enter.setInterpolator(new LinearInterpolator()); //线性变化
        enter.playTogether(scaleX, scaleY);
        enter.setTarget(view);
        return enter;
    }

    private class BezierListener implements ValueAnimator.AnimatorUpdateListener {
        private View view;

        public BezierListener(View view) {
            this.view = view;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            //这里获取到贝塞尔曲线计算出来的的x y值 赋值给view 这样就能让爱心随着曲线走啦
            PointF pointF = (PointF) animation.getAnimatedValue();
            view.setX(pointF.x);
            view.setY(pointF.y);
            view.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    public class BezierEvaluator implements TypeEvaluator<PointF> {
        private PointF pointF1;
        private PointF pointF2;

        public BezierEvaluator(PointF pointF1, PointF pointF2) {
            this.pointF1 = pointF1;
            this.pointF2 = pointF2;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            float timeLeft = 1.0f - fraction;
            PointF pointF = new PointF();
            pointF.x = (float) (Math.pow(timeLeft, 3) * startValue.x +
                    3 * Math.pow(timeLeft, 2) * fraction * pointF1.x +
                    3 * timeLeft * Math.pow(fraction, 2) * pointF2.x +
                    Math.pow(fraction, 3) * endValue.x);
            pointF.y = (float) (Math.pow(timeLeft, 3) * startValue.y +
                    3 * Math.pow(timeLeft, 2) * fraction * pointF1.y +
                    3 * timeLeft * Math.pow(fraction, 2) * pointF2.y +
                    Math.pow(fraction, 3) * endValue.y);
            return pointF;
        }
    }
}
