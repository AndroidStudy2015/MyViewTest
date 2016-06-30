package com.example.a.myviewtest.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a.myviewtest.R;

/**
 * Created by a on 2016/4/5.
 */
public class PlusMinusCount extends LinearLayout {

    private int picWidth;
    private int midTextWidth;
    private int midTextColor;
    private Drawable minusPic;
    private Drawable plusPic;
    private float midTextSize;
    private String midText;
    private ImageView mLeftImageView;
    private ImageView mRightImageView;
    private TextView mMidText;
    private PlusMinusCountClickListener mPlusMinusCountClickListener;
    private String mCount;

    public void setmPlusMinusCountClickListener(PlusMinusCountClickListener mPlusMinusCountClickListener) {
        this.mPlusMinusCountClickListener = mPlusMinusCountClickListener;
    }

    public PlusMinusCount(Context context) {
        this(context, null);
    }

    public PlusMinusCount(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        setUI(context);
        setListener();
    }

    private void setListener() {
        mLeftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlusMinusCountClickListener.minusClick();
                int count;
                if (getCount() - 1 > 0) {
                    count = getCount() - 1;
                } else {
                    count = 1;
                }
                mMidText.setText(count + "");
            }
        });
        mRightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlusMinusCountClickListener.plusClick();
                mMidText.setText((getCount() + 1) + "");
            }
        });
    }

    private void setUI(Context context) {
        this.setOrientation(HORIZONTAL);
        this.setBackgroundResource(R.drawable.bg1);

        mLeftImageView = new ImageView(context);
        mRightImageView = new ImageView(context);
        mMidText = new TextView(context);
        mCount = mMidText.getText().toString().trim();

        mLeftImageView.setImageDrawable(minusPic);
        mRightImageView.setImageDrawable(plusPic);
        mMidText.setText(midText);
        mMidText.setGravity(Gravity.CENTER);
        mMidText.setTextSize(midTextSize);
        mMidText.setTextColor(midTextColor);

        LayoutParams mLeftImageViewParams = new LayoutParams(picWidth, LayoutParams.MATCH_PARENT);
        mLeftImageViewParams.gravity = Gravity.CENTER_VERTICAL;
        addView(mLeftImageView, mLeftImageViewParams);

        LayoutParams mMidTextParams = new LayoutParams(midTextWidth, LayoutParams.MATCH_PARENT);
        mMidTextParams.gravity = Gravity.CENTER;
        addView(mMidText, mMidTextParams);

        LayoutParams mRightImageViewParams = new LayoutParams(picWidth, LayoutParams.MATCH_PARENT);
        mRightImageViewParams.gravity = Gravity.CENTER_VERTICAL;
        addView(mRightImageView, mRightImageViewParams);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PlusMinusCount);

        minusPic = ta.getDrawable(R.styleable.PlusMinusCount_minusPic);
        plusPic = ta.getDrawable(R.styleable.PlusMinusCount_plusPic);
        picWidth = (int) ta.getDimension(R.styleable.PlusMinusCount_picWidth, 70);
        midTextWidth = (int) ta.getDimension(R.styleable.PlusMinusCount_midTextWidth, 150);
        midTextColor = ta.getColor(R.styleable.PlusMinusCount_midTextColor, 0);
        midTextSize = ta.getDimension(R.styleable.PlusMinusCount_midTextSize, 10);
        midText = ta.getString(R.styleable.PlusMinusCount_midText);


        ta.recycle();
    }

    public int getCount() {
        return Integer.parseInt(mMidText.getText().toString());
    }

    public interface PlusMinusCountClickListener {
        void plusClick();

        void minusClick();
    }
}
