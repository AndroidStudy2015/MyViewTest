package com.example.a.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by a on 2016/4/8.
 */
public class ViewGroupB extends RelativeLayout {
    public ViewGroupB(Context context) {
        this(context, null);
    }

    public ViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("B", "ViewGroupB--->dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("B", "ViewGroupB--->onInterceptTouchEvent:"+ev.getAction());
//        return super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("B", "ViewGroupB--->onTouchEvent:"+event.getAction());
//        return super.onTouchEvent(event);
        return true;
    }
}
