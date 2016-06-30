package com.example.a.myviewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a on 2016/4/7.
 */
public class TongJiTu extends View {

    private Paint paintBianKuang;
    private int canvasWidth;
    private int canvasHeight;
    private int lineCount;
    private float lineHeight;
    private Paint paintHengFenGeXian;
    private int columnCount;
    private float columnWidth;

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }


    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }


    private Paint paintDiBuZheXian;

    public TongJiTu(Context context) {
        this(context, null);
    }

    public TongJiTu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
//        画坐标系边框的笔
        paintBianKuang = new Paint();
        paintBianKuang.setColor(Color.BLACK);
        paintBianKuang.setStrokeWidth(5);
        paintBianKuang.setAntiAlias(true);


//        画坐标系横向分割线的笔
        paintHengFenGeXian = new Paint();
        paintHengFenGeXian.setColor(Color.GRAY);
        paintHengFenGeXian.setStrokeWidth(2);
        paintHengFenGeXian.setAntiAlias(true);
        paintHengFenGeXian.setStyle(Paint.Style.FILL);


//        画底部的折线
        paintDiBuZheXian = new Paint();
        paintDiBuZheXian.setColor(Color.RED);
        paintDiBuZheXian.setStrokeWidth(4);
        paintDiBuZheXian.setAntiAlias(true);
        paintDiBuZheXian.setStyle(Paint.Style.STROKE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
//*************************************************************************************************************************
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

//        几个重要点的坐标


//        左上角横坐标：x左上
        float leftUpX = (float) (canvasWidth * 0.1);
//        左上角纵坐标：y左上
        float leftUpY = (float) (canvasHeight * 0.1);

//        左下角横坐标：x左下
        float leftDownX = (float) (canvasWidth * 0.1);
//        左下角纵坐标：y左下
        float leftDownY = (float) (canvasHeight * 0.9);

//        右上角横坐标：x右上
        float rightUpX = (float) (canvasWidth * 0.9);
//        右上角纵坐标：y右上
        float rightUpY = (float) (canvasHeight * 0.1);

//        右下角横坐标：x右下
        float rightDownX = (float) (canvasWidth * 0.9);
//        右下角纵坐标：y右下
        float rightDownY = (float) (canvasHeight * 0.9);


//        画坐标系边框


        canvas.drawLine(leftDownX, leftDownY, rightDownX, rightDownY, paintBianKuang);
        canvas.drawLine(leftDownX, leftDownY, leftUpX, leftUpY, paintBianKuang);
        canvas.drawLine(rightDownX, rightDownY, rightUpX, rightUpY, paintBianKuang);
//*************************************************************************************************************************
//        画横向的分割线
        lineCount = 30;
        lineHeight = (float) (canvasHeight * 0.8 / lineCount);
//        开始画横向分割线
        for (int i = 1; i < lineCount + 1; i++) {
            canvas.drawLine(leftDownX, (float) (canvasHeight * 0.9 - lineHeight * i), rightDownX, (float) (canvasHeight * 0.9 - lineHeight * i), paintHengFenGeXian);
        }
//*************************************************************************************************************************
//        画竖向的分割线
        columnCount = 30;
        columnWidth = (float) (canvasWidth * 0.8 / columnCount);
//        开始画竖向分割线
        for (int i = 1; i < columnCount; i++) {
            canvas.drawLine((float) (canvasWidth * 0.1 + columnWidth * i), leftDownY, (float) (canvasWidth * 0.1 + columnWidth * i), leftUpY, paintHengFenGeXian);
        }

//*************************************************************************************************************************
        //        画底部的折线
        if (offYs.size()!=0){
        Path path = new Path(); //定义一条路径
        path.moveTo(leftDownX + columnWidth * 0, leftDownY-lineHeight*Integer.parseInt(offYs.get(0))); //移动到 坐标10,10
//        path.lineTo(leftDownX + columnWidth * 1, leftDownY - lineHeight);
//        path.lineTo(leftDownX + columnWidth * 2, leftDownY - lineHeight * 2);
//        path.lineTo(leftDownX + columnWidth * 3, leftDownY - lineHeight * 5);
//        path.lineTo(leftDownX + columnWidth * 4, leftDownY - lineHeight * 1);
//        path.lineTo(leftDownX + columnWidth * 5, leftDownY - lineHeight * 3);
//        path.lineTo(leftDownX + columnWidth * 6, leftDownY - lineHeight * 3);
//        path.lineTo(leftDownX + columnWidth * 7, leftDownY - lineHeight * 5);

            Log.e("1", offYs.size() + "");
        for (int i = 1; i <columnCount-1 ; i++) {

            path.lineTo(leftDownX + columnWidth * i, leftDownY - lineHeight*Integer.parseInt(offYs.get(i)));
        }

        canvas.drawPath(path, paintDiBuZheXian);
    }}
    List<String> offYs=new ArrayList<>();

    public void setOffYs(List<String> offYs) {
        this.offYs = offYs;
        this.invalidate();
    }
}
