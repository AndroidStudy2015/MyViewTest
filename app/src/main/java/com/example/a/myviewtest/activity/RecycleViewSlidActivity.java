package com.example.a.myviewtest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a.myviewtest.R;
import com.example.a.myviewtest.View.SlideRecyclerView;
import com.example.a.myviewtest.adapter.MySlideAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewSlidActivity extends AppCompatActivity {


    private SlideRecyclerView recycleView;
    private List<String> urls =new ArrayList<>();
    private String url1 = "http://p0.so.qhimg.com/t019e5c36e98c133c90.jpg";
    private String url2 = "http://p4.so.qhimg.com/t017f0fcdc31a678714.jpg";
    private String url3 = "http://p1.so.qhimg.com/t0164a671b87683f1bb.jpg";
    private String url4 = "http://p4.so.qhimg.com/t01c8836980fbf25fb4.jpg";
    private String url5 = "http://pic.58pic.com/58pic/11/10/80/20X58PICzs8.jpg";
    private String url6 = "http://pic.58pic.com/58pic/13/08/67/39W58PIC4pd_1024.jpg";
    private String url7 = "http://pic1.ooopic.com/uploadfilepic/sheji/2010-01-13/OOOPIC_1982zpwang407_201001136c7dcf1171883f2b.jpg";
    private String url8 = "http://p1.so.qhimg.com/t0177b9ae061405075b.jpg";
    private String url9 = "http://5.26923.com/download/pic/000/288/ed06bdc51d6a83df5919dcea5d261707.jpg";
    private String url10 = "http://pic16.nipic.com/20110919/8371256_191420594000_2.jpg";
    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler handler = new Handler();
    private MySlideAdapter myAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_slid);
        initData();
        initView();
        setData();
    }

    private void initView() {
        recycleView = (SlideRecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
    }

    private void setData() {
        setadapter();
        setSwipeRefreshLayout();
    }

    private void setadapter() {
        myAdapter = new MySlideAdapter(this, urls);
        gridLayoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recycleView.setLayoutManager(gridLayoutManager);
        recycleView.setAdapter(myAdapter);
        //设置显示到recycleView的倒数第二项目时，开始请求网络加载跟多
        recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItemPosition;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //  写在这里，快速滑下去，到一次加载的位置会有停顿
                if (gridLayoutManager.getItemCount() - lastVisibleItemPosition <= 2) {
                    loadMore(myAdapter);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void setSwipeRefreshLayout() {
        //★1.设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);


        //★2.设置刷新监听事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh(myAdapter);
            }
        });
    }

    private void loadMore(final MySlideAdapter myAdapter) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                initMoreData();
                myAdapter.notifyDataSetChanged();
            }
        };
        handler.postDelayed(runnable, 1000);
    }

    private void refresh(final MySlideAdapter myAdapter) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                urls.clear();
                urls.add(url5);
                urls.add(url6);
                urls.add(url7);
                urls.add(url8);
                urls.add(url9);
                myAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        };
        handler.postDelayed(runnable, 1000);
    }


    private void initData() {
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        urls.add(url4);
        urls.add(url5);
    }

    private void initMoreData() {
        urls.add(url8);
        urls.add(url9);
        urls.add(url10);
        urls.add(url6);
        urls.add(url7);
    }


}
