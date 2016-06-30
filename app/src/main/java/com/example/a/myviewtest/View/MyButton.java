package com.example.a.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by a on 2016/4/19.
 */
public class MyButton extends View {

    private Scroller scroller;
    private int size = 768;

    public MyButton(Context context) {
        this(context, null);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);
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
//                ViewGroup. MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                lp.leftMargin=getLeft()+offsetX;
//                lp.topMargin=getTop()+offsetY;
//if (!(lp.leftMargin<0||lp.leftMargin>size-getWidth()||lp.topMargin<0||lp.topMargin>(600-getHeight()))){
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
//        }
               /* ViewGroup. MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
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
                setLayoutParams(lp);*/

                  /*  offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);*/
//                layout(getLeft()+offsetX-getWidth()/2,getTop()+offsetY-getHeight()/2,getRight()+offsetX-getWidth()/2,getBottom()+offsetY-getHeight()/2);
                break;

            case MotionEvent.ACTION_UP:
                View parent = (View) getParent();
                scroller.startScroll(parent.getScrollX(), parent.getScrollY(),
                        -parent.getScrollX(), -parent.getScrollY(), 1000);

                invalidate();
        }


        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
//        判断Scroller是否执行完
        if (scroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(scroller.getCurrX(), scroller.getCurrY());
        }

        invalidate();


    }


}
