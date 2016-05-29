package com.lhp.yy2.fragment;

import android.view.View;
import android.view.View.OnClickListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lhp.yy2.MainActivity;
import com.lhp.yy2.R;

/**
 * 侧边栏fragment
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class LeftMenuFragment extends BaseFragment implements OnClickListener{

	private MainActivity mainUI;
	
	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
		view.findViewById(R.id.iv).setOnClickListener(this);
		view.findViewById(R.id.tv).setOnClickListener(this);
		return view;
	}

	@Override
	public void initData() {
		mainUI = (MainActivity) mActivity;
	}

	/**
	 * 打开或者关闭侧边栏
	 */
	protected void toggle() {
		MainActivity mainUI = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainUI.getSlidingMenu();
		slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
	}

	@Override
	public void onClick(View v) {
		toggle();
		ContentFragment mContentFragment = mainUI.getContentFragment();
		mContentFragment.mViewPager.setCurrentItem(2, false);
		mContentFragment.rbMe.setChecked(true);	
	}

}
