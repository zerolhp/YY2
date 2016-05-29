package com.lhp.yy2;
import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(getApplicationContext());
	}

}
