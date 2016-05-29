package com.lhp.yy2.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.lhp.yy2.BaiduMapActivity;
import com.lhp.yy2.R;
import com.lhp.yy2.base.BasePager;

/**
 * 设置
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class SettingPager extends BasePager {
	private View view;
	public SettingPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		view = View.inflate(mActivity, R.layout.page_set, null);
		RadioButton rbMap = (RadioButton) view.findViewById(R.id.rb_map);
		rbMap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mActivity.startActivity(new Intent(mActivity, BaiduMapActivity.class));
			}
		});
		
		Button btnCall_ = (Button) view.findViewById(R.id.rb_call);

		btnCall_.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// 设置Intent的action
				intent.setAction(Intent.ACTION_CALL); 
				// 设置Intent的data
				intent.setData(Uri.parse("tel:" + "18610020187")); // "tel:"易漏
				mActivity.startActivity(intent); // 启动系统的拨号界面
			}
		});
		
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
		tvTitle.setText("设置");

		// 显示菜单按钮
		btnMenu.setVisibility(View.VISIBLE);
	}

}
