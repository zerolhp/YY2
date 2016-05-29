package com.lhp.yy2;

import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;

/**
 * HelloBaiduMapActivity继承自BaseActivity，主要用于展示百度地图的基本功能：放大、缩小、旋转、俯仰和移动。
 */

public class BaiduMapActivity extends BaseActivity {

	private int item_id;
	private MapStatusUpdate mapStatusUpdate = null;
	private MapStatus curMapStatus;
	private MapStatus newMapStatus;

	@Override
	public void init() {
		setTitle("BaiduMap"); // 设置界面标题
		MarkerOptions markerOptions = new MarkerOptions();
		BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
		markerOptions.position(zkyPos) // 位置
					 .title("张晔家") // 标题
					 .icon(icon); // 图标
		baiduMap.addOverlay(markerOptions);
	}

	/** 添加菜单项 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, "缩小地图"); // (groupID, itemID, orderID, title)
		menu.add(0, 2, 0, "放大地图");
		menu.add(0, 3, 0, "旋转地图");
		menu.add(0, 4, 0, "俯仰地图");
		menu.add(0, 5, 0, "移动地图");
		return true;
	}

	/** 处理菜单项 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		item_id = item.getItemId(); // 获取itemIDs
		switch (item_id) {
		case 1: // 缩小
			mapStatusUpdate = MapStatusUpdateFactory.zoomOut(); // zoomOut()是缩小而不是放大
			break;
		case 2: // 放大
			mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
			break;
		case 3: // 旋转
			// 获取当前的MapStatus
			curMapStatus = baiduMap.getMapStatus();
			// 获取当前旋转角度后再加30°
			float rotate = curMapStatus.rotate + 30;
			// 创建新的MapStatus对象
			newMapStatus = new MapStatus.Builder().rotate(rotate).build();
			// 将新的MapStatus对象添加进MapStatusUpdate对象
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(newMapStatus);
			break;
		case 4: // 仰俯(0 ~ -45°)
			curMapStatus = baiduMap.getMapStatus();
			float overlook = curMapStatus.overlook - 5;
			newMapStatus = new MapStatus.Builder().overlook(overlook).build();
			mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(newMapStatus);
			break;
		case 5: // 移动
			mapStatusUpdate = MapStatusUpdateFactory.newLatLng(zkyPos);
			// 以动画方式更新地图位置（持续时间2S）
			baiduMap.animateMapStatus(mapStatusUpdate, 2000);
			// 必须立即return，因为下面的setMapStatus()属于瞬时更新，与animateMapStatus()冲突。
			return true;
		default:
			break;
		}
		baiduMap.setMapStatus(mapStatusUpdate); // 更新Map状态
		return true;
	}

}
