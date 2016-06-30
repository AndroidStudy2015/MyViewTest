package com.example.a.myviewtest.View;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a.myviewtest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RollViewPager就是一个viewpager，用于轮播图片
 * 
 */
	public class RollViewPager extends ViewPager {
		private MyAdapter myAdapter;
		private RunnableTask runnableTask = new RunnableTask();
		protected static final String tag = "RollViewPager2";
		private Context context;
		/**
		 * 要显示的标题数组，★注意目前用的是数组，实际中应该用List，到时候记得切换
		 */
		private String[] titleArray;
	/**
	 * 要显示的图片资源数组
	 */
	private int[] imageIdArray;
	/**
	 * 放置点的集合
	 */
	private List<View> viewList = new ArrayList<View>();
	/**
	 * 轮播图显示的当前页
	 */
	private int currentPosition = 0;

	/**
	 * 自定义view必须重写带一个参数Context的构造方法（仅用于代码new 自定义veiw）<br>
	 * 可在Context后面添加需要的参数
	 * 
	 * @param context
	 */

	public RollViewPager(Context context, String[] titleArray,
						 int[] imageIdArray) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.titleArray = titleArray;
		this.imageIdArray = imageIdArray;
		this.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				currentPosition = arg0;

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		startRoll();
	}

	/**
	 * 让轮播图viewpager滚动起来
	 */
	public void startRoll() {
		// 滚动viewpager
		if (myAdapter == null) {
			// 1.第一次初始化适配器
			myAdapter = new MyAdapter();
			RollViewPager.this.setAdapter(myAdapter);
		} else {// 8.第二次，只需要通知适配器数据发生了变化，要刷新Ui
//			myAdapter.notifyDataSetChanged();
		}
		// 2.发送一个延时的消息，3秒后执行runnableTask类里run方法里的操作
		// ★（为什么执行的是runnableTask，而不是handleMessage呢？这里涉及到handler消息机制源码解析）
		handler.postDelayed(runnableTask, 3000);
	}

	class RunnableTask implements Runnable {
		@Override
		public void run() {
			// 3.变化轮播图当前要显示的页面位置，递增1，为了不使这个数字递增超过轮播图 图片的个数，取余数
			currentPosition = (currentPosition + 1) % titleArray.length;
			// 4.发送消息给主线程的handler
			handler.obtainMessage().sendToTarget();
		}
	}

	private Handler handler = new Handler() {
		// 5.接收并处理run方法发来的消息
		public void handleMessage(android.os.Message msg) {
			// 6.viewpager设置新的当前页
			RollViewPager.this.setCurrentItem(currentPosition);
			// 7.继续执行startRoll方法，成为一个循环
			startRoll();
		}
	};

	/**
	 * ★当手指按住轮播图不动时，轮播图停止滚动；当点击轮播图时，跳转到相关界面
	 */
	public void onTouchViewPager(View view, final int position) {
		// 给图片注册触摸事件监听器
		view.setOnTouchListener(new OnTouchListener() {

			private long downTime;
			private int downX;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:// 按下去时，记录按下的坐标和时间，用于判断是否是点击事件
					handler.removeCallbacksAndMessages(null);// 手指按下时，取消所有事件，即轮播图不在滚动了
					downX = (int) event.getX();
					downTime = System.currentTimeMillis();
					break;
				case MotionEvent.ACTION_UP:// 抬起手指时，判断落下抬起的时间差和坐标，符合以下条件为点击
					// Toast.makeText(context, "手指抬起了", 0).show();
					if (System.currentTimeMillis() - downTime < 500
							&& Math.abs(downX - event.getX()) < 30) {// ★考虑到手按下和抬起时的坐标不可能完全重合，这里给出30的坐标偏差
						// 点击事件被触发
						Toast.makeText(context,
								"这里就不弹出对应页面了，您打开的是第" + position + "张图片", Toast.LENGTH_SHORT)
								.show();
					}
					startRoll();
					break;
				case MotionEvent.ACTION_CANCEL:
					Toast.makeText(context, "滑动事件触发了", Toast.LENGTH_SHORT).show();
					// ★写这个的目的为了让用户在手指滑动完图片后，能够让轮播图继续自动滚动
					startRoll();
					break;
				}
				return true;
			}
		});
	}

	/**
	 * 处理小点点，使得小点点随着轮播图的位置而改变颜色
	 */
	private void initDot(LinearLayout dots_ll, int position) {
		// 必须每次进来清除线性布局里的所有小点点，不然，每次切换回页面，都运行initDot方法，会一直累加小点点，每次增加8个点
		dots_ll.removeAllViews();
		viewList.clear();
		// 遍历轮播图片的集合，每遍历一个，new一个view，给这个view设置背景图片，
		// 给包含小点点的父亲现形布局设置参数，设置间距，线性布局添加这些点，viewList也添加小点点
		for (int i = 0; i < imageIdArray.length; i++) {
			View view = new View(context);
			if (i == position) {
				view.setBackgroundResource(R.drawable.dot_focus);
			} else {
				view.setBackgroundResource(R.drawable.dot_normal);
			}

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					dip2px(context, 6),
					dip2px(context, 6));
			view.setLayoutParams(layoutParams);
			layoutParams.setMargins(5, 0, 5, 0);
			dots_ll.addView(view);
			viewList.add(view);
		}
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 当界面移出是调用的方法
	 */
	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		super.onDetachedFromWindow();
		// 移除所有的任务，当此控件不显示在界面上时，停止其scroll
		handler.removeCallbacksAndMessages(null);
	}

	/**
	 * 适配器，要重写下面四个方法
	 */
	class MyAdapter extends PagerAdapter {

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return imageIdArray.length;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(context, R.layout.layout_roll_view, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.image);
			imageView.setImageResource(imageIdArray[position]);
			TextView title = (TextView) view.findViewById(R.id.top_news_title);
			title.setText(titleArray[position]);
			LinearLayout dots_ll = (LinearLayout) view
					.findViewById(R.id.dots_ll);
			// 处理小点点的操作
			initDot(dots_ll, position);
			// onTouchViewPager方法一定要写在instantiateItem内部，表示触摸的是当前位置的页面
			onTouchViewPager(view, position);
			// ★★★这句话很重要！！！别忘了写！！！
			((RollViewPager) container).addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// ★★★这句话很重要！！！别忘了写！！！
			((RollViewPager) container).removeView((View) object);
		}
	}
}
