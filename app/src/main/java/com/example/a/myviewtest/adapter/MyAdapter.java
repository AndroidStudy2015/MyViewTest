package com.example.a.myviewtest.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a.myviewtest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyVH> {
        private static final int TYPE_ITEM = 0;
        private static final int TYPE_FOOTER = 1;
        private Context context;
        private List<String> urls;

        public MyAdapter(Context context, List<String> urls) {
            this.context = context;
            this.urls = urls;
        }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == TYPE_ITEM) {
            View view = inflater.inflate(R.layout.item_recycleview, null);
            return new MyVH(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = inflater.inflate(R.layout.load_more, null);
            return new MyVH(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyVH holder, int position) {
        if (position + 1 < getItemCount()) {
            if (position == 0 || position == 1) {
                holder.ll.setPadding(0, 18, 0, 18);
            } else {
                holder.ll.setPadding(0, 0, 0, 18);
            }
            Picasso.with(context).load(urls.get(position)).into(holder.iv);
        } else {
            holder.tv.setText("加载更多...");
            ObjectAnimator anim = ObjectAnimator.ofFloat( holder.loadImg, "rotation", 0f, 360f*5);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(500*5).setRepeatCount(5);
            anim.start();
        }
    }

    @Override
    public int getItemCount() {
        return urls.size() + 1;
    }

    class MyVH extends RecyclerView.ViewHolder {

        public ImageView iv;
        public LinearLayout ll;
        public TextView tv;
        public  ImageView loadImg;

        public MyVH(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
            tv = (TextView) itemView.findViewById(R.id.tv);
            loadImg = (ImageView) itemView.findViewById(R.id.load_more);
        }
    }
    }