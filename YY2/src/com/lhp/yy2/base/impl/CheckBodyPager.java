package com.lhp.yy2.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhp.yy2.MainActivity;
import com.lhp.yy2.R;
import com.lhp.yy2.base.BasePager;
import com.lhp.yy2.fragment.ContentFragment;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class CheckBodyPager extends BasePager implements OnClickListener{

	private View view;
	private MainActivity mainUI;

	public CheckBodyPager(Activity activity) {
		super(activity);
		mainUI = (MainActivity) mActivity;
	}

	@Override
	public View initView() {
		view = View.inflate(mActivity, R.layout.page_checkbody, null);
		view.findViewById(R.id.iv).setOnClickListener(this);
		view.findViewById(R.id.btn1).setOnClickListener(this);
		view.findViewById(R.id.btn2).setOnClickListener(this);
		view.findViewById(R.id.btn3).setOnClickListener(this);
		view.findViewById(R.id.btn4).setOnClickListener(this);
		view.findViewById(R.id.btn5).setOnClickListener(this);
		return super.initView();
	}

	@Override
	public void initData() {
		flContent.removeAllViews();
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeAllViews();
		}
		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("体检");

		// 显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);
	}

	@Override
	public void onClick(View v) {
		ContentFragment mContentFragment = mainUI.getContentFragment();
		mContentFragment.mViewPager.setCurrentItem(2, false);
		mContentFragment.rbMe.setChecked(true);		
	}

}
