package com.lhp.yy2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lhp.yy2.utils.PrefUtils;

/**
 * 闪屏页面
 * 
 * @author Kevin
 * @date 2015-10-17
 */
public class SplashActivity extends Activity {

	private RelativeLayout rlRoot;
	private ImageView mImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
		mImageView = (ImageView) findViewById(R.id.iv);

		// 旋转动画
		RotateAnimation animRotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animRotate.setDuration(2500);// 动画时间
		animRotate.setFillAfter(true);// 保持动画结束状态

		// 缩放动画
		ScaleAnimation animScale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		animScale.setDuration(2400);
		animScale.setFillAfter(true);// 保持动画结束状态

		// 渐变动画
		AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
		animAlpha.setDuration(2600);// 动画时间
		animAlpha.setFillAfter(true);// 保持动画结束状态

		// 动画集合
		AnimationSet set = new AnimationSet(true);
		set.addAnimation(animScale);
		set.addAnimation(animAlpha);
		set.addAnimation(animRotate);

		// 启动动画
		mImageView.startAnimation(set);

		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				// 动画结束,跳转页面
				// 如果是第一次进入, 跳新手引导
				// 否则跳主页面
				boolean isFirstEnter = PrefUtils.getBoolean(
						SplashActivity.this, "is_first_enter", true);

				Intent intent;
				// if (isFirstEnter) {
				// // 新手引导
				// intent = new Intent(getApplicationContext(),
				// GuideActivity.class);
				// } else {
				// // 主页面
				// intent = new Intent(getApplicationContext(),
				// MainActivity.class);
				// }
				intent = new Intent(getApplicationContext(),
						GuideActivity.class);
				startActivity(intent);

				finish();// 结束当前页面
			}
		});
	}

}
