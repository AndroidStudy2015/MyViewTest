package com.example.a.myviewtest.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.Tencent;
import com.example.a.myviewtest.View.TencentRotation;
import com.example.a.myviewtest.View.TencentTransferText;

import java.util.Random;

public class TencentActivity extends AppCompatActivity {

    private TencentRotation tencentRotation;
    private Tencent tencent;
    private TencentTransferText tencentTransferText;
    private Random random;
    private int score = 100;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    private int r;
    private int yCenter;
    private int yCenterDes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencent);
        tencentRotation = (TencentRotation) findViewById(R.id.tencentRotation);
        tencent = (Tencent) findViewById(R.id.tencent);
        tencentTransferText = (TencentTransferText) findViewById(R.id.tencentTransferText);








        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                dong();
            }
        };
        new Handler().postDelayed(runnable1, 100);
    }

    private void dong() {
        random = new Random();
        yCenter = tencentTransferText.getyCenter();
        Log.e("yCenter", yCenter + "");
        yCenterDes = yCenter - 200;
        Log.e("yCenterDes", yCenterDes + "");

        r = random.nextInt(50);
        tencentRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                yuandong();
                //★特别注意，当再次点击时，要先清楚所有的handler事件，否则两次点击后定时任务会重叠，导致结果是乱的
                handler.removeCallbacksAndMessages(null);
                tencentRotation.setRotation(0);
                AnimatorSet set = new AnimatorSet();

                ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(tencentRotation, "rotation", 360 * r);
                rotationAnimator.setDuration(10000).start();

                Runnable r1=new Runnable(){
                    @Override
                    public void run() {
                        if (score <= 91) {
                            handler.removeCallbacks(this);//停止定时任务
                            ObjectAnimator.ofInt(tencentTransferText, "yPos", yCenter, yCenter).setDuration(10).start();
                            return;
                        }
                        ObjectAnimator yPosAnimator = ObjectAnimator.ofInt(tencentTransferText, "yPos", yCenter, yCenterDes);
                        yPosAnimator.setDuration(1000).start();
                        handler.postDelayed(this, 1000);
                        yPosAnimator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                tencentTransferText.setScore(score = score - 1);
                            }
                        });
                    }
                };
                handler.postDelayed(r1, 1000);




            }


        });
    }

    private void yuandong() {
        random = new Random();
        yCenter = tencentTransferText.getyCenter();
        Log.e("yCenter", yCenter + "");
        yCenterDes = yCenter - 200;
        Log.e("yCenterDes", yCenterDes + "");
        //★特别注意，当再次点击时，要先清楚所有的handler事件，否则两次点击后定时任务会重叠，导致结果是乱的
        handler.removeCallbacksAndMessages(null);
        tencentRotation.setRotation(0);
        int r = random.nextInt(5);
        ObjectAnimator.ofFloat(tencentRotation, "rotation", 360 * r).setDuration(5000).start();

        Runnable runnable3 = new Runnable() {
            @Override
            public void run() {
                handler.removeCallbacksAndMessages(null);
                tencent.setVisibility(View.VISIBLE);
                tencentTransferText.setVisibility(View.GONE);
                tencentRotation.setVisibility(View.GONE);
            }
        };
        new Handler().postDelayed(runnable3, 5000);

        ObjectAnimator.ofInt(tencentTransferText, "yPos", yCenter, yCenterDes).setDuration(1000).start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tencentTransferText.setScore(score=score-3);
                ObjectAnimator.ofInt(tencentTransferText, "yPos", yCenter, yCenterDes).setDuration(1000).start();
                handler.postDelayed(this, 1000);
            }
        };

        handler.postDelayed(runnable, 1000);
    }
}
