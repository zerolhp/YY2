package com.lhp.yy2;

import java.util.ArrayList;

import com.lhp.yy2.utils.PrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 新手引导页面
 * 
 * @author Kevin
 * @date 2015-10-17
 */
public class GuideActivity extends Activity {

	private ViewPager mViewPager;
	private LinearLayout llContainer;
	private Button btnStart;
	private ArrayList<ImageView> points; // 指示圆点集
	private ArrayList<ImageView> mImageViewList; // imageView集合

	// 引导页图片id数组
	private int[] mImageIds = new int[] { R.drawable.guide_vp1,
			R.drawable.guide_vp2, R.drawable.guide_vp3, R.drawable.guide_vp4 };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题,
														// 必须在setContentView之前调用
		setContentView(R.layout.activity_guide);

		mViewPager = (ViewPager) findViewById(R.id.vp_guide);
		llContainer = (LinearLayout) findViewById(R.id.ll_container);
		btnStart = (Button) findViewById(R.id.btn_start);

		initData();// 先初始化数据
		
		mViewPager.setAdapter(new GuideAdapter());// 设置数据

		// 默认第一个圆点为红色
		points.get(0).setImageResource(R.drawable.shape_point_red);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// 某个页面被选中
				if (position == mImageViewList.size() - 1) {// 最后一个页面显示开始体验的按钮
					btnStart.setVisibility(View.VISIBLE);
				} else {
					btnStart.setVisibility(View.INVISIBLE);
				}
				// 根据不同的滑动情况来改变圆点的颜色
				if (position == 0) {
					points.get(0).setImageResource(R.drawable.shape_point_red);
					points.get(position + 1).setImageResource(
							R.drawable.shape_point_gray);
				} else {
					points.get(position).setImageResource(
							R.drawable.shape_point_red);
					points.get(position - 1).setImageResource(
							R.drawable.shape_point_gray);
					if (position < mImageViewList.size() - 1) {
						points.get(position + 1).setImageResource(
								R.drawable.shape_point_gray);
					}
				}
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// 页面状态发生变化的回调
			}
		});

		btnStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 更新sp, 已经不是第一次进入了
				PrefUtils.setBoolean(getApplicationContext(), "is_first_enter",
						false);

				// 跳到主页面
				startActivity(new Intent(getApplicationContext(),
						MainActivity.class));
				finish();
			}
		});
	}

	// 初始化数据
	private void initData() {
		mImageViewList = new ArrayList<ImageView>();
		points = new ArrayList<ImageView>();
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(this);
			view.setBackgroundResource(mImageIds[i]);// 通过设置背景,可以让宽高填充布局
			mImageViewList.add(view);

			// 初始化小圆点
			ImageView point = new ImageView(this);
			point.setImageResource(R.drawable.shape_point_gray);// 设置图片(shape形状)

			// 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				// 从第二个点开始设置左边距
				params.leftMargin = 20;
			}

			point.setLayoutParams(params);// 设置布局参数
			
			llContainer.addView(point);// 给容器添加圆点
			points.add(point); // 加入指示圆点集
		}
	}

	class GuideAdapter extends PagerAdapter {

		// item的个数
		@Override
		public int getCount() {
			return mImageViewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		// 初始化item布局
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = mImageViewList.get(position);
			container.addView(view);
			return view;
		}

		// 销毁item
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}
}
