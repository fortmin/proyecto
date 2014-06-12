package com.fortmin.proshopapi.nfc;

import android.content.Context;
import android.content.pm.PackageManager;

public class ProShopNFCMgr {

	public boolean soportaNFC(Context context) {
		PackageManager pckMgr = context.getPackageManager();
		return pckMgr.hasSystemFeature(PackageManager.FEATURE_NFC);
	}

	public boolean soportaNFCHce(Context context) {
		PackageManager pckMgr = context.getPackageManager();
		return pckMgr.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
	}

}
