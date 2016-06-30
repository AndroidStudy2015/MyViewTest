package com.example.a.myviewtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.MyButton;

public class AndroidScrollTest extends AppCompatActivity {

    private MyButton bt;
    private int[] loactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_scroll_test);
        bt = (MyButton) findViewById(R.id.bt);
        loactions = new int[2];
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt.getLocationOnScreen(loactions);
                Log.e("qqq", "绝对坐标");
                Log.e("qqq", "x:" + loactions[0]);
                Log.e("qqq", "y:" + loactions[1]);
                Log.e("qqq", "相对父布局坐标");
                Log.e("qqq", "x:" + bt.getX());
                Log.e("qqq", "y:" + bt.getY());


                Log.e("qqq", "getTop" + bt.getTop());
                Log.e("qqq", "getLeft" + bt.getLeft());
                Log.e("qqq", "getRight" + bt.getRight());
                Log.e("qqq", "getBottom" + bt.getBottom());

            }
        });

    }
}
