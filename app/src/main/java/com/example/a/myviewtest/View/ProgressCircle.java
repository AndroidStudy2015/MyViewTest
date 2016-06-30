package com.example.a.myviewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.a.myviewtest.utils.DensityUtil;

/**
 * Created by a on 2016/4/6.
 */
public class ProgressCircle extends View {

    private Paint paintCircle;
    private Paint paintArc;
    private Paint paintText;
    private int mCanvasWideth;
    private int mTextSize = 100;
    private int count = 25;
    private Context context;

    private float sweepValue;
    private String centerCircleColor = "#3F51B5";
    private String centerTextColor = "#ffffffff";
    private String outCircleColor = "#FF4081";
    private int mCanvasHeight;

    public ProgressCircle setCenterCircleColor(String centerCircleColor) {
        this.centerCircleColor = centerCircleColor;
        paintCircle.setColor(Color.parseColor(centerCircleColor));
        this.invalidate();
        return this;
    }

    public ProgressCircle setCenterTextColor(String centerTextColor) {
        this.centerTextColor = centerTextColor;
        paintText.setColor(Color.parseColor(centerTextColor));
        this.invalidate();
        return this;
    }

    public ProgressCircle setOutCircleColor(String outCircleColor) {
        this.outCircleColor = outCircleColor;
        paintArc.setColor(Color.parseColor(outCircleColor));
        this.invalidate();
        return this;
    }

    public ProgressCircle(Context context) {
        this(context, null);
    }

    public ProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public ProgressCircle setmTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        paintText.setTextSize(mTextSize);
        this.invalidate();
        return this;
    }

    public ProgressCircle setCount(int count) {

        if (count > 100) {
            count = 100;
        }
        this.count = count;
        sweepValue = (360 * count) / 100;
        this.invalidate();//重新绘图
        return this;
    }

    private void init() {

//      设置画笔（中间的圆圈）
        paintCircle = new Paint();
        paintCircle.setStrokeWidth(5);
        paintCircle.setStyle(Paint.Style.FILL);
        paintCircle.setAntiAlias(true);
        paintCircle.setColor(Color.parseColor(centerCircleColor));


//      设置画笔（外围的进度圆环）
        paintArc = new Paint();
        paintArc.setStrokeWidth(55);
        paintArc.setAntiAlias(true);
        paintArc.setStyle(Paint.Style.STROKE);
        paintArc.setColor(Color.parseColor(outCircleColor));

//      设置画笔（中间的圆圈内的数字）
        paintText = new Paint();
        paintText.setStrokeWidth(1);
        paintText.setAntiAlias(true);
        paintText.setTextSize(mTextSize);
        sweepValue = (360 * count) / 100;
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setColor(Color.parseColor(centerTextColor));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("1",  "走");
        mCanvasWideth = canvas.getWidth();
        mCanvasHeight = canvas.getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
//        计算高宽时，先减去padding值
        int width=mCanvasWideth-paddingLeft-paddingRight;
        int height=mCanvasHeight-paddingTop-paddingBottom;
        int radius=Math.min(width, height)/4;
//        设置画布颜色
        canvas.drawColor(Color.YELLOW);
//        1.画圆圈
        canvas.drawCircle(width/ 2+paddingLeft, height / 2+paddingTop, radius, paintCircle);

//        2.画中间的文本
        int xPos = (width / 2+paddingLeft);
//        这里计算yPos时用的是getWidth，因为怕有的人把这个View的高宽赋为不等的值时，导致文本不在圆心（其实这个控件本来就必须时正方形）
        int yPos = (int) ((width / 2+paddingLeft) - ((paintText.descent() + paintText.ascent()) / 2));
        canvas.drawText(count + "", xPos, yPos, paintText);

//        3.要画圆弧，先定义圆弧的外切矩形
        RectF rectF = new RectF(
                (float) ((width) * 0.1+paddingLeft),//left为距画布左侧0.1倍的画布宽的距离
                (float) ((width) * 0.1+paddingTop),//top为距画布顶部0.1倍的画布宽的距离
                (float) ((width) * 0.9+paddingRight),//right为距画布右侧0.1倍的画布宽的距离
                (float) ((width) * 0.9+paddingBottom)//bottom为距画布底部0.1倍的画布宽的距离
        );
//        再画圆弧
        canvas.drawArc(rectF, -90, sweepValue, false
                , paintArc);


    }


    //    以下为模板代码，如果不重写onMeasure方法，无法使用wrap_content这个属性，系统不知道该默认什么尺寸，
//    就会默认填充整个父布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = DensityUtil.dip2px(context, 200);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = DensityUtil.dip2px(context,200);
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        return result;
    }
}
