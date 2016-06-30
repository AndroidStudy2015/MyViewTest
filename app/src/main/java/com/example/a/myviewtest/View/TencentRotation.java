package com.example.a.myviewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by a on 2016/4/15.
 */
public class TencentRotation extends View {

    private int screenWidth;
    private int screenHeight;
    private int mWidth;
    private int mHeight;
    private int mMeasuredWidth;
    private int mMeasuredHeight;
    private Paint paintText;
    private int score = 35;
    private int textColor = Color.WHITE;


    public TencentRotation(Context context) {
        this(context, null);
    }

    public TencentRotation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        获取测量后的整个控件的宽、高
        mMeasuredWidth = getMeasuredWidth();
        mMeasuredHeight = getMeasuredHeight();
        Log.e("1", "mMeasuredWidth:" + mMeasuredWidth + "----------mMeasuredHeight:" + mMeasuredHeight);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
//        这个宽、高是要绘制的圆圈点的宽、高，考虑了Padding值
        mWidth = mMeasuredWidth - paddingLeft - paddingRight;
        mHeight = mMeasuredHeight - paddingTop - paddingBottom;
        int center = Math.min(mWidth / 2, mHeight / 2);


//        绘制外围的点点构成的圆圈
        Paint paintPoint = new Paint();
        paintPoint.setAntiAlias(true);
        paintPoint.setColor(Color.WHITE);
        paintPoint.setStrokeWidth(5);
        paintPoint.setStyle(Paint.Style.FILL);

        int radiusPoint = 8;

        for (int i = 0; i < 36; i++) {

//            前面减去了Padding，这里别忘了加上对应的Padding

            canvas.drawCircle(center + paddingLeft, radiusPoint + paddingTop, radiusPoint, paintPoint);
            canvas.rotate(10, center + paddingLeft, center + paddingTop);
        }
        canvas.save();
/*

//        绘制中间的文字
        paintText = new Paint();
        paintText.setStrokeWidth(1);
        paintText.setAntiAlias(true);
        int mTextSize = 200;
        paintText.setTextSize(mTextSize);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setColor(textColor);
        int xPos = center + paddingLeft;
        int yPos = (int) (center + paddingTop - (paintText.ascent() + paintText.descent()) / 2);
        canvas.drawText("", xPos, yPos, paintText);*/
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measuredWidth(widthMeasureSpec), measuredHeight(heightMeasureSpec));
    }

    private int measuredWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        screenWidth = size;

        int result = 0;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = 100;//默认值是100px
            result = Math.min(result, size);
        }
        return result;
    }

    private int measuredHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        screenHeight = size;

        int result = 0;
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            result = 100;
            result = Math.min(result, size);
        }
        return result;
    }


}
