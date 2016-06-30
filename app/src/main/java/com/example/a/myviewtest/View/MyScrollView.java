package com.example.a.myviewtest.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.example.a.myviewtest.utils.DensityUtil;

/**
 * Created by a on 2016/4/7.
 */
public class MyScrollView extends ViewGroup {
    int mScreenHeight;
    int mScreenWidth;
    private int mLastY;
    private int mStart;
    private Scroller mScroller;
    private int mEnd;

    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        mScreenHeight = DensityUtil.getScreenHeight(context);
        mScreenWidth = DensityUtil.getScreenWidth(context);
        mScreenHeight = 1683;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("q", "初始左l:" + l);
        Log.e("q", "初始上t:" + t);
        Log.e("q", "初始右r:" + r);
        Log.e("q", "初始下b:" + b);
        Log.e("q", "————————————————————————————————————————————————————");
        int childCount = getChildCount();
//        设置ViewGroup的高度
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);
        Log.e("q", "mScreenHeight:" + mScreenHeight);
        Log.e("q", "mScreenWidth:" + mScreenWidth);
        /*for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(l+10, i * mScreenHeight, r, (i + 1) * mScreenHeight);
                Log.e("q", "左l:" + l);
                Log.e("q","上t:"+i * mScreenHeight);
                Log.e("q","右r:"+r);
                Log.e("q","下b:"+((i + 1) * mScreenHeight));
                Log.e("q","————————————————————————————————————————————————————");
            }
        }*/

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int ml ;
            int mt ;
            int mr ;
            int mb ;
            if (child.getVisibility() != View.GONE) {
                if (i % 2 == 0) {

                    ml = l;
                    mt = i / 2 * mScreenHeight;
                    mr = r / 2;
                    mb = (i / 2+ 1) * mScreenHeight;

                    child.layout(ml, mt, mr, mb);
                } else {
                    ml = l + r / 2;
                    mt = i / 2* mScreenHeight;
                    mr = r;
                    mb = (i / 2 + 1) * mScreenHeight;


                    child.layout(ml, mt, mr, mb);
                }
                Log.e("q", "左l:" + ml);
                Log.e("q", "上t:" + mt);
                Log.e("q", "右r:" + mr);
                Log.e("q", "下b:" + mb);
                Log.e("q", "————————————————————————————————————————————————————");
            }
        }
    }

        @Override
        protected void onMeasure ( int widthMeasureSpec, int heightMeasureSpec){
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View childView = getChildAt(i);
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            }


        }



    @Override
        public boolean onTouchEvent (MotionEvent event){
            int y = (int) event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mLastY = y;
                    mStart = getScrollY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (!mScroller.isFinished()) {
                        mScroller.abortAnimation();
                    }
                    int dy = mLastY - y;
                    if (getScrollY() < 0) {
                        dy = 0;
                    }
                    if (getScrollY() > getHeight() - mScreenHeight) {
                        dy = 0;
                    }
                    scrollBy(0, dy);
                    mLastY = y;
                    break;

                case MotionEvent.ACTION_UP:
                    mEnd = getScrollY();
                    int dScrollY = mEnd - mStart;
                    if (dScrollY > 0) {
                        if (dScrollY < mScreenHeight / 3) {
                            mScroller.startScroll(0, getScrollY(), 0, -dScrollY);

                        } else {
                            mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                        }
                    } else {

                        if (-dScrollY < mScreenHeight / 3) {

                            mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                        } else {
                            mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);
                        }
                    }
                    break;
            }
            postInvalidate();
            return true;

        }

        @Override
        public void computeScroll () {
            super.computeScroll();
            if (mScroller.computeScrollOffset()) {
                scrollTo(0, mScroller.getCurrY());
                postInvalidate();
            }
        }
    }
