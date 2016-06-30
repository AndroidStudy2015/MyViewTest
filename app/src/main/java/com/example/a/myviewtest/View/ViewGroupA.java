package com.example.a.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by a on 2016/4/8.
 */
public class ViewGroupA extends RelativeLayout {
    public ViewGroupA(Context context) {
        this(context, null);
    }

    public ViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }




    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("A", "ViewGroupA--->dispatchTouchEvent:"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("A", "ViewGroupA--->onInterceptTouchEvent:"+ev.getAction());
        return super.onInterceptTouchEvent(ev);//返回true，表示拦截事件，自己处理
//        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("A", "ViewGroupA--->onTouchEvent:"+event.getAction());
        return super.onTouchEvent(event);//返回true ，表示我处理该触摸事件，点击事件完成了，即会出现Action_Up，否则只是执行了Action_Down
//        return true;
    }
}
