package com.example.a.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by a on 2016/4/8.
 */
public class ViewC extends View {
    public ViewC(Context context) {
        this(context, null);
    }

    public ViewC(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("C", "ViewC--->dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("C", "ViewC--->onTouchEvent"+event.getAction());
//        return super.onTouchEvent(event);
        return true;//返回true ，表示点击事件完成了，即会出现Action_Up，否则只是执行了Action_Down

    }
}
