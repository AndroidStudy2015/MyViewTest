package com.example.a.myviewtest.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by a on 2016/4/20.
 */
public class CanvasLayer extends View {

    private Paint paint;

    public CanvasLayer(Context context) {
        this(context,null);
    }

    public CanvasLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, paint);

//        canvas.saveLayerAlpha(0, 0, 400, 400, 255, Canvas.CLIP_TO_LAYER_SAVE_FLAG);
//        canvas.save();
        paint.setColor(Color.RED);
        paint.setAlpha(127);
        canvas.drawCircle(200, 200, 100, paint);
//        canvas.restore();
    }
















}
