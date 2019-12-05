package com.thtf.leanpackage.one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.one
 * @date 2019-12-04 17:07
 * @描述
 */
public class ParentLayout extends FrameLayout {
    public ParentLayout(@NonNull Context context) {
        this(context, null);
    }

    public ParentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParentLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("警告", "Down事件要流转到子View来处理,由Ta判定是否需要父View拦截");
        if (ev.getAction() == MotionEvent.ACTION_DOWN)
            return false;
        else
            return true;

    }

    private float rowY = -1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("警告", "parent onTouchEvent" + event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                LayoutParams l = (LayoutParams) getLayoutParams();
                // 计算当前位置离父view顶部的距离
                rowY = event.getRawY() - l.topMargin;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                LayoutParams l = (LayoutParams) getLayoutParams();
                if (rowY == -1) {
                    //从子View流转过来，交由父View进行后续处理
                    rowY = event.getRawY() - l.topMargin;
                }
                // 当前位置减去距离顶部的距离，就是marginTop
                l.topMargin = (int)(event.getRawY() - rowY);
                setLayoutParams(l);
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                //事件结束的时候把坐标还原，否则影响下一次事件的处理
                rowY = -1;
                break;
            default:
                break;

        }
        return true;
    }
}
