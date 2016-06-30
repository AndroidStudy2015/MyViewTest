/*
package com.example.a.myviewtest.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.PlusMinusCount;
import com.example.a.myviewtest.View.ProgressCircle;
import com.example.a.myviewtest.View.TongJiTu;
import com.example.a.myviewtest.View.TopBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity1 extends AppCompatActivity {

    private TopBar mTopBar;
    private PlusMinusCount mPlusMinusCount;
    private Handler handler = new Handler();
    private int i;
    private Runnable runnable;
    private ListView mLv;
    private Button mButton;
    private MyAdapter myAdapter;
    private TongJiTu mTongJiTu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTopBar = (TopBar) findViewById(R.id.topBar);
        mTopBar.setmTopBarClickListener(new TopBar.TopBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity1.this, "左侧按钮被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity1.this, "右侧按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        mPlusMinusCount = (PlusMinusCount) findViewById(R.id.plus_minus_Count);
        mPlusMinusCount.setmPlusMinusCountClickListener(new PlusMinusCount.PlusMinusCountClickListener() {
            @Override
            public void plusClick() {
                Toast.makeText(MainActivity1.this, "加号按钮被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void minusClick() {
                Toast.makeText(MainActivity1.this, "减号按钮被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        final ProgressCircle progressCircle = (ProgressCircle) findViewById(R.id.progressCircle);
        progressCircle
                .setCount(0)
                .setmTextSize(150)
//                .setCenterCircleColor("#ff0000")
//                .setCenterTextColor("#ffea34")
//                .setOutCircleColor("#78fa19")
        ;

        progressCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //★特别注意，当再次点击时，要先清楚所有的handler事件，否则两次点击后定时任务会重叠，导致结果是乱的
                handler.removeCallbacksAndMessages(null);
                DingShiRenWu(progressCircle);
            }
        });

//        ShuXingDongHua(progressCircle);

//        DingShiRenWu(progressCircle);


        mTongJiTu = (TongJiTu) findViewById(R.id.tongJiTu);

        mLv = (ListView) findViewById(R.id.lv);
        myAdapter = new MyAdapter();
        mLv.setAdapter(myAdapter);
        for (int i = 1; i < columnCount; i++) {
            dataXs.add(i + "");
        }

        mButton = (Button) findViewById(R.id.bt_commit);
        mTongJiTu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("dataYs", dataYs.size() + "");
                mTongJiTu.setOffYs(dataYs);
            }
        });

    }

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

    int lineCount = 30;
    int columnCount = 30;
    List<String> dataXs = new ArrayList<>();
    List<String> dataYs = new ArrayList<>();


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return dataXs.size();
        }

        @Override
        public Object getItem(int position) {
            return dataXs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity1.this, R.layout.item_tongjitu, null);
                holder = new ViewHolder();
                holder.tv_X = (TextView) convertView.findViewById(R.id.tv_x);
                holder.et_Y = (EditText) convertView.findViewById(R.id.et_y);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_X.setText(dataXs.get(position));
            String radomY = new Random().nextInt(lineCount) + "";
            holder.et_Y.setText(radomY);
            dataYs.add(radomY);
            Log.e("dataYs执行了", position+ "");

            holder.et_Y.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String trim = holder.et_Y.getText().toString().trim();
                    dataYs.remove(position);
                    dataYs.add(position,trim);
                }
            });
            return convertView;
        }

        class ViewHolder {
            TextView tv_X;
            EditText et_Y;
        }
    }
}
*/
