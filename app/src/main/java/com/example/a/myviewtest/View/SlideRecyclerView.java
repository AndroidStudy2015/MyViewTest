package com.example.a.myviewtest.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by a on 2016/4/25.
 */
public class SlideRecyclerView extends RecyclerView {
    private int size = 768;
    public SlideRecyclerView(Context context) {
        this(context,null);

    }

    public SlideRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int lastX = 0, lastY = 0;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX - getWidth() / 2;
                int offsetY = y - lastY - getHeight() / 2;
                ViewGroup. MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
                lp.leftMargin=getLeft()+offsetX;
                if (lp.leftMargin>(size-getWidth())){
                    lp.leftMargin=size-getWidth();
                }else if(lp.leftMargin<0){
                    lp.leftMargin=0;
                }
                lp.topMargin=getTop()+offsetY;
                if (lp.topMargin>(600-getWidth())){
                    lp.topMargin=600-getWidth();
                }else if(lp.topMargin<0){
                    lp.topMargin=0;
                }
                setLayoutParams(lp);

                break;

            case MotionEvent.ACTION_UP:

        }


        return super.onTouchEvent(event);
    }

}
