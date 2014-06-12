package com.fortmin.proshopapi.manager;

import com.fortmin.proshopapi.ble.ProShopBleMgr;
import com.fortmin.proshopapi.nfc.ProShopNFCMgr;

import android.content.Context;

/*
 * Clase principal a la cual se invoca para solicitar el Manager de la Tecnologia
 * correspondiente.
 */
public class ProShopMgr {
	
	/*
	 * Averiguo si el celular tiene soporte NFC
	 */
	public boolean soportaNFC(Context context) {
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();
		return nfcMgr.soportaNFC(context);
	}

	/*
	 * Averiguo si el celular tiene soporte NFC Host Card Emulation
	 */
	public boolean soportaNFCHce(Context context) {
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.soportaNFCHce(context);
	}
	
	/*
	 * Averiguo si el celular es capaz de comunicarse con dispositivos Bluetooth Low Energy
	 */
	public boolean soportaBLE(Context context) {
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.soportaBLE(context);
	}
	
	/*
	 * Averiguo si el celular es capaz de comunicarse por Bluetooth
	 */
	public boolean soportaBluetooth(Context context) {
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.soportaBluetooth(context);
	}
	

}
