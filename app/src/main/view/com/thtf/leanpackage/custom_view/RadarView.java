package com.thtf.leanpackage.custom_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.thtf.leanpackage.common_view.BaseView;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LiShiChuang
 * @packageName com.thtf.leanpackage.custom_view
 * @date 2019-12-03 14:11
 * @描述
 */
public class RadarView extends BaseView {
    private String TAG = RadarView.class.getSimpleName();

    private Path mRdPath;
    private float mRadius = 0.0f;//半径
    private Paint mPaintLine;//雷达线画笔
    private int mCornerCount = 5;//角个数
    private Paint mPaintArea;//阴影区域画笔
    private List<String> mlables;//标签文字
    private int mRingCount = 5;//雷达圆环个数
    private Paint mPaintLabel;//每个点的标签文字画笔
    private float mCenterX, mCenterY;//中心X,Y点坐标
    private Float[] mArrayRadius = null;//每个圆环半径
    private LinkedList<Double> dataSeries;//每个标签点的百分比
    private Float[] mArrayLabelAgent = null;//每个标签节点归属的圆心角度
    private float[][] mArrayDotX = null, mArrayDotY = null;//雷达图每个点的X,Y坐标
    private float[][] mArrayLabelX = null, mArrayLabelY = null;//雷达图外围标签文字X,Y坐标

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        mRdPath = new Path();
        mlables = new LinkedList<>();
        mlables.add("数学");
        mlables.add("化学");
        mlables.add("英语");
        mlables.add("物理");
        mlables.add("语文");
        dataSeries = new LinkedList<Double>();
        dataSeries.add(80.0);
        dataSeries.add(50.0);
        dataSeries.add(60.0);
        dataSeries.add(30.0);
        dataSeries.add(90.0);
        //蜘蛛网和各个轴线的画笔
        mPaintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintLine.setColor(randomColor());
        mPaintLine.setStyle(Paint.Style.STROKE);
        mPaintLine.setStrokeWidth(2);
        //标签文字画笔
        mPaintLabel = new Paint();
        mPaintLabel.setColor(Color.BLACK);
        mPaintLabel.setTextSize(30);
        mPaintLabel.setAntiAlias(true);
        mPaintLabel.setTextAlign(Paint.Align.CENTER);
        //阴影区域画笔
        mPaintArea = new Paint();
        mPaintArea.setColor(randomColor());
        mPaintArea.setAntiAlias(true);
        mPaintArea.setStrokeWidth(5);
        mPaintArea.setAlpha(100);
    }

    /**
     * 改变屏幕尺寸
     * @param width
     * @param height
     * @param oldwidth
     * @param oldheight
     */
    @Override
    protected void onSizeChanged(int width, int height, int oldwidth, int oldheight) {
        mCenterX = Math.abs(div(width, 2f));
        mCenterY = Math.abs(div(height, 2f));
        mRadius = Math.min(div(width, 2f), div(height, 2f)) * 0.8f;
        super.onSizeChanged(width, height, oldwidth, oldheight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //计算各个定点的坐标
        calcAllPoints();
        //绘制蜘蛛网
        renderGridLinesRadar(canvas);
        //绘制圆心到定点的轴线
        renderAxisLines(canvas);
//        画标题
        renderAxisLabels(canvas);
//        画填充区域
        renderDataArea(canvas);
    }


    private void calcAllPoints() {
        //一条轴线上的点总数
        int dataAxisTickCount = mRingCount + 1;
        //扇形每个角的角度, 360/
        float pAngle = div(360f, mCornerCount); //   72f;
        //270为中轴线所处圆心角
        float initOffsetAgent = sub(270f, pAngle);  //270f-72f
        //当前圆心角偏移量
        float offsetAgent = 0.0f;
        //初始化雷达图各个点坐标和外围标签文字坐标
        //dataAxisTickCount=mRingCount + 1;  mRingCount=5;
        mArrayDotX = new float[dataAxisTickCount][mCornerCount];
        mArrayDotY = new float[dataAxisTickCount][mCornerCount];
        mArrayLabelX = new float[dataAxisTickCount][mCornerCount];
        mArrayLabelY = new float[dataAxisTickCount][mCornerCount];
        //保存每个环数的半径长度
        mArrayRadius = new Float[dataAxisTickCount];
        //由总环数和半径长度算出平均刻度值
        float avgRadius = div(mRadius, mRingCount); // 平均刻度值 = 半径 / 环数
        mArrayLabelAgent = new Float[mCornerCount];
        float currAgent = 0.0f;
        for (int i = 0; i < mRingCount + 1; i++) //数据轴(从圆心点开始)
        {
            //平均刻度值算出每个圆环的半径
            //圆环半径 = 平均刻度值 * i
            mArrayRadius[i] = avgRadius * i;
            for (int j = 0; j < mCornerCount; j++) {
                //270f + 72f * j
                offsetAgent = add(initOffsetAgent, pAngle * j);
                //  圆心角偏移量 + 72f
                currAgent = add(offsetAgent, pAngle);
                //计算位置
                if (Float.compare(0.f, mArrayRadius[i]) == 0) {
                    mArrayDotX[i][j] = mCenterX;
                    mArrayDotY[i][j] = mCenterY;
                } else {
                    //坐标位置
                    mArrayDotX[i][j] = calcArcEndPointXY(mCenterX, mCenterY, mArrayRadius[i], currAgent).x; // X 位置
                    mArrayDotY[i][j] = calcArcEndPointXY(mCenterX, mCenterY, mArrayRadius[i], currAgent).y; // Y 位置
                }
                //记下每个标签对应的圆心角
                if (0 == i) mArrayLabelAgent[j] = currAgent;
            }
        }
    }

    /**
     * 绘制蜘蛛网
     *
     * @param canvas
     */
    private void renderGridLinesRadar(Canvas canvas) {
        //重置路径
        mRdPath.reset();
        for (int i = 0; i < mRingCount + 1; i++) {
            for (int j = 0; j < mCornerCount; j++) {
                if (j == 0) {
                    //移动下一次操作的起点位置
                    mRdPath.moveTo(mArrayDotX[i][j], mArrayDotY[i][j]);
                } else {
                    //添加上一个点到当前点之间的直线到Path
                    mRdPath.lineTo(mArrayDotX[i][j], mArrayDotY[i][j]);
                }
            }
            mRdPath.close();
            canvas.drawPath(mRdPath, mPaintLine);
            mRdPath.reset();
        }
    }

    /*
     *绘制各个方向上的轴线
     */
    private void renderAxisLines(Canvas canvas) {
        for (int j = 0; j < mCornerCount; j++) {
            canvas.drawLine(mCenterX, mCenterY, mArrayDotX[mRingCount][j], mArrayDotY[mRingCount][j], mPaintLine);
        }
    }

    /*
     *绘制最外围的标签
     */
    private void renderAxisLabels(Canvas canvas) {
        for (int j = 0; j < mlables.size(); j++) {
            if (mArrayDotX[mRingCount][j] > mCenterX && mArrayDotY[mRingCount][j] < mCenterY) {
                mArrayLabelX[mRingCount][j] = mArrayDotX[mRingCount][j] + 30f;
                mArrayLabelY[mRingCount][j] = mArrayDotY[mRingCount][j];
            } else if (mArrayDotX[mRingCount][j] > mCenterX && mArrayDotY[mRingCount][j] > mCenterY) {
                mArrayLabelX[mRingCount][j] = mArrayDotX[mRingCount][j] + 30f;
                mArrayLabelY[mRingCount][j] = mArrayDotY[mRingCount][j] + 30f;
            } else if (mArrayDotX[mRingCount][j] <= mCenterX && mArrayDotY[mRingCount][j] > mCenterY) {
                mArrayLabelX[mRingCount][j] = mArrayDotX[mRingCount][j] - 30f;
                mArrayLabelY[mRingCount][j] = mArrayDotY[mRingCount][j] + 30f;
            } else if (mArrayDotX[mRingCount][j] < mCenterX && mArrayDotY[mRingCount][j] < mCenterY) {
                mArrayLabelX[mRingCount][j] = mArrayDotX[mRingCount][j] - 30f;
                mArrayLabelY[mRingCount][j] = mArrayDotY[mRingCount][j];
            } else {
                mArrayLabelX[mRingCount][j] = mArrayDotX[mRingCount][j];
                mArrayLabelY[mRingCount][j] = mArrayDotY[mRingCount][j];
            }
            canvas.drawText(mlables.get(j), mArrayLabelX[mRingCount][j], mArrayLabelY[mRingCount][j], mPaintLabel);
        }
    }


    /**
     * 绘制数据区网络
     *
     * @param canvas 画布
     */
    private void renderDataArea(Canvas canvas) {

        int dataSize = dataSeries.size();
        Float[] arrayDataX = new Float[dataSize];
        Float[] arrayDataY = new Float[dataSize];
        int i = 0;
        for (Double data : dataSeries) {
            if (Double.compare(data, 0.d) == 0) {
                arrayDataX[i] = mCenterX;
                arrayDataY[i] = mCenterY;
                i++; //标签
                continue;
            }
            float curRadius = (float) (mRadius * data / 100);
            //计算位置

            arrayDataX[i] = calcArcEndPointXY(mCenterX, mCenterY, curRadius, mArrayLabelAgent[i]).x;
            arrayDataY[i] = calcArcEndPointXY(mCenterX, mCenterY, curRadius, mArrayLabelAgent[i]).y;
            i++; //标签
        }

        float initX = 0.0f, initY = 0.0f;
        mRdPath.reset();
        for (int p = 0; p < arrayDataX.length; p++) {
            if (0 == p) {
                initX = arrayDataX[p];
                initY = arrayDataY[p];
                mRdPath.moveTo(initX, initY);
            } else {
                mRdPath.lineTo(arrayDataX[p], arrayDataY[p]);
            }
        }
        //收尾
        mRdPath.lineTo(initX, initY);
        mRdPath.close();
        canvas.drawPath(mRdPath, mPaintArea);
    }


    /*
     *设置标签
     */
    public void setLableList(List<String> lableList) {
        this.mlables = lableList;
        postInvalidate();
    }

    /*
     *设置百分比
     */
    public void setDataList(LinkedList<Double> dataList) {
        this.dataSeries = dataList;
        postInvalidate();
    }

    /**
     * 依圆心坐标，半径，扇形角度，计算出扇形终射线与圆弧交叉点的xy坐标
     *
     * @param cirX     centerX
     * @param cirY     centerY
     * @param radius   圆环半径 = 平均刻度值 * i
     * @param cirAngle 圆心角偏移量 + 72f
     * @return
     */
    public PointF calcArcEndPointXY(float cirX, float cirY, float radius, float cirAngle) {
        PointF mPointF = new PointF();

        if (Float.compare(cirAngle, 0.0f) == 0 || Float.compare(radius, 0.0f) == 0) { // 角度 == 0.0f || 半径 == 0.0f
            return mPointF;
        }

        //将角度转换为弧度
        float arcAngle = (float) (Math.PI * div(cirAngle, 180.0f)); // 弧度 = PI * 角度 / 180.0f

        if (Float.compare(arcAngle, 0.0f) == -1) // 当前角度 < 0.0f
            mPointF.x = mPointF.y = 0.0f;

        if (Float.compare(cirAngle, 90.0f) == -1) {  //当前角度 < 90.0f
            mPointF.x = add(cirX, (float) Math.cos(arcAngle) * radius); // centerX + cos(角度) * 半径
            mPointF.y = add(cirY, (float) Math.sin(arcAngle) * radius); // centerY + sin(角度) * 半径

        } else if (Float.compare(cirAngle, 90.0f) == 0) { //当前角度 == 90.0f
            mPointF.x = cirX;  //90.0f的时候 X坐标在圆心 X轴坐标上
            mPointF.y = add(cirY, radius); // centerY + 半径

        } else if (Float.compare(cirAngle, 90.0f) == 1 && Float.compare(cirAngle, 180.0f) == -1) { // 当前角度 > 90.0f && 当前角度 < 180.0f
            arcAngle = (float) (Math.PI * (sub(180f, cirAngle)) / 180.0f); // 弧度 = PI * ( 180.0f - 角度 ) /180.0f
            mPointF.x = sub(cirX, (float) (Math.cos(arcAngle) * radius));  // x = centerX - cos(角度) * 半径
            mPointF.y = add(cirY, (float) (Math.sin(arcAngle) * radius));  // y = centerY + sin(角度) * 半径

        } else if (Float.compare(cirAngle, 180.0f) == 0) { // 当前角度 == 180.0f
            mPointF.x = cirX - radius; // centerX - 半径
            mPointF.y = cirY; // 180.0f的时候 Y坐标在圆心 Y轴坐标上

        } else if (Float.compare(cirAngle, 180.0f) == 1 && Float.compare(cirAngle, 270.0f) == -1) { // 当前角度 > 180.0f && 当前角度 < 270.0f
            arcAngle = (float) (Math.PI * (sub(cirAngle, 180.0f)) / 180.0f); // 弧度 = PI * ( 角度 - 180.0f ) /180.0f
            mPointF.x = sub(cirX, (float) (Math.cos(arcAngle) * radius)); // centerX - cos(角度) * 半径
            mPointF.y = sub(cirY, (float) (Math.sin(arcAngle) * radius)); // centerY - sin(角度) * 半径

        } else if (Float.compare(cirAngle, 270.0f) == 0) { //当前角度 == 270.0f
            mPointF.x = cirX; // 270.0f的时候 X坐标在圆心 X轴坐标上
            mPointF.y = sub(cirY, radius); // centerY - 半径

        } else { // 当前角度 > 270.0f && 当前角度 < 360.0f
            arcAngle = (float) (Math.PI * (sub(360.0f, cirAngle)) / 180.0f); // 弧度 = PI * ( 360.0f - 角度 ) /180.0f
            mPointF.x = add(cirX, (float) (Math.cos(arcAngle) * radius)); // centerX + cos(角度) * 半径
            mPointF.y = sub(cirY, (float) (Math.sin(arcAngle) * radius)); // centerY - sin(角度) * 半径
        }

        return mPointF;
    }

    /*--------------------------------------------------------------------------------------------*/

    /**
     * 除法计算
     *
     * @param v1
     * @param v2
     * @return
     */
    public float div(float v1, float v2) {
        BigDecimal bgNum1 = new BigDecimal(Float.toString(v1));
        BigDecimal bgNum2 = new BigDecimal(Float.toString(v2));
        return bgNum1.divide(bgNum2, 5, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    /**
     * 加法计算
     *
     * @param v1
     * @param v2
     * @return
     */
    public float add(double v1, double v2) {
        BigDecimal bgNum1 = new BigDecimal(Double.toString(v1));
        BigDecimal bgNum2 = new BigDecimal(Double.toString(v2));
        return bgNum1.add(bgNum2).floatValue();
    }

    /**
     * 减法计算
     *
     * @param v1
     * @param v2
     * @return
     */
    public float sub(float v1, float v2) {
        BigDecimal bgNum1 = new BigDecimal(Float.toString(v1));
        BigDecimal bgNum2 = new BigDecimal(Float.toString(v2));
        return bgNum1.subtract(bgNum2).floatValue();
    }
    /*--------------------------------------------------------------------------------------------*/
}
