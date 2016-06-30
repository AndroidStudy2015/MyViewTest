package com.example.a.myviewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by a on 2016/4/5.
 */
public class YiBiaoPan extends View {

    private Paint paintCircle;
    private int mWidth;
    private int mHeight;
    private Paint paintDegree;
    private Paint paintHour;
    private Paint paintMinute;

    public YiBiaoPan(Context context) {
        this(context, null);
    }

    public YiBiaoPan(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);

        paintDegree = new Paint();
        paintDegree.setStyle(Paint.Style.FILL);
        paintDegree.setAntiAlias(true);
        paintDegree.setStrokeWidth(3);

        paintHour = new Paint();
        paintHour.setStyle(Paint.Style.STROKE);
        paintHour.setAntiAlias(true);
        paintHour.setStrokeWidth(9);

        paintMinute = new Paint();
        paintMinute.setStyle(Paint.Style.STROKE);
        paintMinute.setAntiAlias(true);
        paintHour.setStrokeWidth(7);


    }
int bigText=50;
int smallText=30;
int longLineLength=60;
int shortLineLength=15;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = canvas.getWidth();
        mHeight = canvas.getHeight();
//      画外圆
        canvas.drawCircle(mWidth / 2, mHeight / 2,
                mWidth / 2, paintCircle
        );
//      画刻度
        for (int i = 0; i < 60; i++) {
//            区分整点与非整点
            if (i % 5 == 0) {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(bigText);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + longLineLength,
                        paintDegree
                );

                String degree = String.valueOf(i/5);
                if (degree.equals("0")){
                    degree="12";
                }

                canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + bigText+longLineLength, paintDegree);

            } else {
                paintDegree.setStrokeWidth(3);
                paintDegree.setTextSize(smallText);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + shortLineLength,
                        paintDegree
                );

                String degree = String.valueOf(i);

               /* canvas.drawText(degree, mWidth / 2 - paintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + smallText+shortLineLength, paintDegree);*/

            }
//            通过旋转画布简化坐标运算
            canvas.rotate(6, mWidth / 2, mHeight / 2);//以指定的坐标为圆心旋转（看源码可知，先移动到该圆心点，旋转后，再回到原来的坐标原点）
//            canvas.rotate(15);相当于 canvas.rotate(15,0,0);
        }

//        画指针
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();

    }
}
