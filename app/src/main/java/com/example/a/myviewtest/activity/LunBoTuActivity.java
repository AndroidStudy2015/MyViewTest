package com.example.a.myviewtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.RollViewPager;

public class LunBoTuActivity extends AppCompatActivity {
    // 准备要显示的图片资源
    private int[] imageIdArray = {R.drawable.a, R.drawable.b,
            R.drawable.c, R.drawable.d};
    // 准备title,如果不想显示title，可以将其初始化为与图片相同数量的空字符串，否则报错
    private String[] titleArray = {"", "", "", ""};
    private LinearLayout ll_viewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lun_bo_tu);

        // 找到ViewPager控件
        ll_viewpager = (LinearLayout) findViewById(R.id.ll_viewpager);
        RollViewPager rollViewPager = new RollViewPager(getApplicationContext(), titleArray,
                imageIdArray);
        ll_viewpager.addView(rollViewPager);
    }
}
