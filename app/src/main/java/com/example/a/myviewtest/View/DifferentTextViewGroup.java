package com.example.a.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.a.myviewtest.utils.DensityUtil;

/**
 * Created by a on 2016/4/12.
 */
public class DifferentTextViewGroup extends ViewGroup {

    private int childCount;
    private int screenWidth;
    private int screenHeight;

    public DifferentTextViewGroup(Context context) {
        this(context, null);
    }

    public DifferentTextViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        screenWidth = DensityUtil.getScreenWidth(context);
        screenHeight = DensityUtil.getScreenHeight(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        childCount=getChildCount();
        Log.e("q", "childCount:" + childCount);
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("q", "childCountlayout:" + childCount);
        Log.e("q", "初始左l:" + l);
        Log.e("q", "初始上t:" + t);
        Log.e("q", "初始右r:" + r);
        Log.e("q", "初始下b:" + b);
        Log.e("q", "————————————————————————————————————————————————————");
        int left=l;
//       设置viewgroup的宽高
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) getLayoutParams();
        marginLayoutParams.width = screenWidth;
        marginLayoutParams.height = screenHeight;
        setLayoutParams(marginLayoutParams);
//        开始循环布局每个子view
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility()!=View.GONE){
                child.layout(left,t,left+child.getMeasuredWidth(),child.getMeasuredHeight()*10);
                left+=child.getMeasuredWidth();
            }
        }

    }
}
