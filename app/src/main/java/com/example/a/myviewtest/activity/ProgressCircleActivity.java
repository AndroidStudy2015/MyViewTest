package com.example.a.myviewtest.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.ProgressCircle;

public class ProgressCircleActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private int i;
    private Runnable runnable;
    private ProgressCircle progressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_circle);
        progressCircle = (ProgressCircle) findViewById(R.id.progressCircle);
        progressCircle
                .setCount(0)
                .setmTextSize(150)
//                .setCenterCircleColor("#ff0000")//可以设置颜色
//                .setCenterTextColor("#ffea34")
//                .setOutCircleColor("#78fa19")
        ;

        progressCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //★特别注意，当再次点击时，要先清楚所有的handler事件，否则两次点击后定时任务会重叠，导致结果是乱的
                handler.removeCallbacksAndMessages(null);
                Log.e("a", "sfgsgsg");
//                DingShiRenWu(progressCircle);
                ShuXingDongHua(progressCircle);
            }
        });

//        ShuXingDongHua(progressCircle);

//        DingShiRenWu(progressCircle);


    }

    //属性动画的实质就是DingShiRenWu不停的改变setCount这个属性
    private void ShuXingDongHua(ProgressCircle progressCircle) {
        ObjectAnimator.ofInt(progressCircle, "count", 100).setDuration(3000).start();
    }

    private void DingShiRenWu(final ProgressCircle progressCircle) {

        i = 0;

        runnable = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // 进度先慢再快再慢，进度加载到90时，停止加载
                progressCircle.setCount(i++);

                if (i < 20) {
                    handler.postDelayed(this, 10);
                } else if (i >= 20 & i <= 50) {
                    handler.postDelayed(this, 10);
                } else {
                    handler.postDelayed(this, 50);
                }
                if (i == 91) {
                    handler.removeCallbacks(this);//停止定时任务
                }
            }
        };

        handler.postDelayed(runnable, 100);//每两秒执行一次runnable.
    }
}

