package com.fortmin.proshopapi.ble;

import android.content.Context;
import android.content.pm.PackageManager;

public class ProShopBleMgr {
	
	public boolean soportaBLE(Context context) {
		PackageManager pckMgr = context.getPackageManager();
		return pckMgr.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
	}

	public boolean soportaBluetooth(Context context) {
		PackageManager pckMgr = context.getPackageManager();
		return pckMgr.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
	}

}
