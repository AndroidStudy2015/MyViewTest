package com.example.a.myviewtest.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a.myviewtest.R;

/**
 * Created by a on 2016/4/5.
 */
public class TopBar extends RelativeLayout {

    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;
    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;
    private boolean mLeftTextIsVisible;
    private boolean mRightTextIsVisible;

    public void setmTopBarClickListener(TopBarClickListener mTopBarClickListener) {
        this.mTopBarClickListener = mTopBarClickListener;
    }

    TopBarClickListener mTopBarClickListener;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        setUI(context);
        setListener();
    }

    private void setListener() {
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mTopBarClickListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                mTopBarClickListener.rightClick();
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        mTitle = ta.getString(R.styleable.TopBar_titles);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColoe, 0);
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mLeftTextIsVisible = ta.getBoolean(R.styleable.TopBar_leftTextIsVisible, true);
        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mRightTextIsVisible = ta.getBoolean(R.styleable.TopBar_rightTextIsVisible, true);

        ta.recycle();
    }

    private void setUI(Context context) {

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackgroundDrawable(mLeftBackground);
        mLeftButton.setText(mLeftText);
        if (mLeftTextIsVisible) {
            mLeftButton.setVisibility(View.VISIBLE);
        } else {
            mLeftButton.setVisibility(View.GONE);
        }

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackgroundDrawable(mRightBackground);
        mRightButton.setText(mRightText);
        if (mRightTextIsVisible) {
            mRightButton.setVisibility(View.VISIBLE);
        } else {
            mRightButton.setVisibility(View.GONE);
        }

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);
    }

    public interface TopBarClickListener {
        void leftClick();

        void rightClick();
    }

}
