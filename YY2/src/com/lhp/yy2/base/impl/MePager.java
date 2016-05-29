package com.lhp.yy2.base.impl;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.lhp.yy2.R;
import com.lhp.yy2.base.BasePager;

/**
 * 智慧服务
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class MePager extends BasePager {

	private View view;

	public MePager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		view = View.inflate(mActivity, R.layout.page_me, null);

		return super.initView();
	}

	@Override
	public void initData() {

		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeAllViews();
		}
		flContent.addView(view);

		// 修改页面标题
		tvTitle.setText("登录");

		// 显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);
	}

}
