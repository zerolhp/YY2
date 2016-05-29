package com.lhp.yy2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;


public abstract class BaseActivity extends Activity {

	public LatLng zkyPos = new LatLng(39.9165860000,116.3423760000); // 北京黑马经纬度坐标
	protected MapView mMapView = null; // 地图视图对象
	private MapStatusUpdate mapStatusUpdate; // 地图更新器对象
	public BaiduMap baiduMap; // 百度地图管理器对象

	/** final onCreate()是为了不让子类覆盖，以防止基类对象还没初始化完就被子类所用而出现空指针。 */
	@Override
	protected final void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baidumap); // 加载布局文件
		mMapView = (MapView) findViewById(R.id.bmapView); // 获取Activity中地图视图控件的引用
		baiduMap = mMapView.getMap(); // 获取地图管理器对象
		// 设置地图初始中心点
		mapStatusUpdate = MapStatusUpdateFactory.newLatLng(zkyPos);
		baiduMap.setMapStatus(mapStatusUpdate);
		// 设置地图的初始缩放级别
		mapStatusUpdate = MapStatusUpdateFactory.zoomTo(15); // 3~20
		baiduMap.setMapStatus(mapStatusUpdate);

		// mMapView.showScaleControl(false); // 隐藏比例按钮
		// mMapView.showZoomControls(false); // 隐藏缩放按钮

		// // 当发生旋转或俯仰时，指南针就会出现，我们可以设置指南针的可见性和位置。
		// UiSettings uiSettings = baiduMap.getUiSettings(); // 获取UI控制器
		// uiSettings.setCompassEnabled(false); // 不显示指南针
		// uiSettings.setCompassPosition(Point arg0); // 设置指南针的位置

		init(); // 由子类实现的其他初始化操作
	}

	/** 子类初始化操作 */
	public abstract void init();

	/** MapView具有完整的生命周期，应与Activity的生命周期相对应。 */
	@Override
	protected void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	protected void onDestroy() {
		mMapView.onDestroy();
		super.onDestroy();
	}

	/** 基类已加载menu的布局文件，子类只需添加item即可。 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

}