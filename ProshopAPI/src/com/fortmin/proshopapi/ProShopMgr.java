package com.fortmin.proshopapi;

import com.fortmin.proshopapi.ble.ProShopBleMgr;
import com.fortmin.proshopapi.nfc.ProShopNFCMgr;

import android.content.Context;

/*
 * Clase principal a la cual se invoca para solicitar el Manager de la Tecnologia
 * correspondiente.
 */
public class ProShopMgr {
	
	private Context context = null;
	
	public ProShopMgr(Context context) {
		this.context = context;
	}
	
	/*
	 * Averiguo si el celular tiene soporte NFC
	 */
	public boolean soportaNFC() {
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();
		return nfcMgr.soportaNFC(context);
	}

	/*
	 * Averiguo si el celular tiene soporte NFC Host Card Emulation
	 */
	public boolean soportaNFCHce() {
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.soportaNFCHce(context);
	}
	
	/*
	 * Chequear si el NFC se encuentra habilitado en este momento
	 */
	public boolean nfcHabilitado() {
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.nfcHabilitado(context);
	}
	
	/*
	 * Averiguo si el celular es capaz de comunicarse con dispositivos Bluetooth Low Energy
	 */
	public boolean soportaBLE() {
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.soportaBLE(context);
	}
	
	/*
	 * Averiguo si el celular es capaz de comunicarse por Bluetooth
	 */
	public boolean soportaBluetooth() {
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.soportaBluetooth(context);
	}
	
	/*
	 * Chequear si el Bluetooth se encuentra habilitado en este momento
	 */
	public boolean bluetoothHabilitado() {
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.bluetoothHabilitado(context);
	}

	/*
	 * Tabla de referencia entre API Level y Version Codes:
		9                  GINGERBREAD               Android 2.3 Gingerbread
	   10                  GINGERBREAD_MR1           Android 2.3.3 Gingerbread
	   11                  HONEYCOMB                 Android 3.0 Honeycomb
	   12                  HONEYCOMB_MR1             Android 3.1 Honeycomb
	   13                  HONEYCOMB_MR2             Android 3.2 Honeycomb
	   14                  ICE_CREAM_SANDWICH        Android 4.0 Ice Cream Sandwich
	   15                  ICE_CREAM_SANDWICH_MR1    Android 4.0.3 Ice Cream Sandwich
	   16                  JELLY_BEAN                Android 4.1 Jellybean
	   17                  JELLY_BEAN_MR1            Android 4.2 Jellybean
	   18                  JELLY_BEAN_MR2            Android 4.3 Jellybean
	   19                  KITKAT                    Android 4.4 KitKat
	   */

}
