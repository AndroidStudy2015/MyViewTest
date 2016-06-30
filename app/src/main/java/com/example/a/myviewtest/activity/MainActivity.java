package com.example.a.myviewtest.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.DispatchTouchEventActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView mLv;
    private MyAdapter myAdapter;
    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        mLv = (ListView) findViewById(R.id.lv);
        myAdapter = new MyAdapter();
        mLv.setAdapter(myAdapter);
        names.add("TopBar");
        names.add("闪烁的TextView");
        names.add("进度圈");
        names.add("加减数量");
        names.add("仪表盘");
        names.add("滚动的View（未完成）");
        names.add("事件分发演示");
        names.add("ScrollView");
        names.add("不同TextView的ViewGroup");
        names.add("仿照腾讯手机管家");
        names.add("Android滚动事件");
        names.add("RecycleView下拉加载");
        names.add("RecycleView下拉加载左右滑动");
        names.add("轮播图");
        names.add("PaletteActivity");
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, TopBarActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ShanShuoTextViewActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, ProgressCircleActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this, PlusMinusCountActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this, YiBiaoPanActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this, MyScrollViewActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MainActivity.this, DispatchTouchEventActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MainActivity.this, ScrollViewActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MainActivity.this, DifferentTextViewGroupActivity.class));
                        break;
                    case 9:
                        startActivity(new Intent(MainActivity.this, TencentActivity.class));
                        break;
                    case 10:
                        startActivity(new Intent(MainActivity.this, AndroidScrollTest.class));
                        break;
                    case 11:
                        startActivity(new Intent(MainActivity.this, RecycleViewActivity.class));
                        break;
                    case 12:
                        startActivity(new Intent(MainActivity.this, RecycleViewSlidActivity.class));
                        break;
                    case 13:
                        startActivity(new Intent(MainActivity.this, LunBoTuActivity.class));
                        break;
                    case 14:
                        startActivity(new Intent(MainActivity.this, PaletteActivity1.class));
                        break;
                }
            }
        });

    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return names.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(names.get(position));

            return convertView;
        }

        class ViewHolder {
            TextView name;
        }
    }
}
