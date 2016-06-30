package com.example.a.myviewtest.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.TopBar;

public class TopBarActivity extends AppCompatActivity {

    private TopBar mTopBar;
    private Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_bar);
        mTopBar = (TopBar) findViewById(R.id.topBar);
        mTopBar.setmTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopBarActivity.this, "左侧按钮被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopBarActivity.this, "右侧按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        });


        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Animator circularReveal = ViewAnimationUtils.createCircularReveal(bt1, bt1.getWidth() / 2, bt1.getHeight() / 2,
//                        bt1.getWidth() / 2, 0);
                Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                        bt1,0, bt1.getHeight(),0,(float)Math.hypot(bt1.getWidth(),bt1.getHeight()));
                circularReveal.setInterpolator(new LinearInterpolator());
                circularReveal.setDuration(500);
                circularReveal.start();
            }
        });
    }
}
