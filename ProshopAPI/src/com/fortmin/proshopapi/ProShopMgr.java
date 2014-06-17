package com.fortmin.proshopapi;

import java.net.URISyntaxException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.util.Log;

import com.fortmin.proshopapi.ble.ProShopBleMgr;
import com.fortmin.proshopapi.nfc.ProShopNFCMgr;

/*
 * Clase principal a la cual se invoca para solicitar el Manager de la Tecnologia
 * correspondiente.
 */
public class ProShopMgr {
	
	private Context context = null;
	private final String TAG = "PSHAPI";
	
	public ProShopMgr(Context context) {
		this.context = context;
	}
	
	private void log(String logtxt) {
		String mens = this.getClass().getName()+"->";
		mens = mens.concat(Thread.currentThread().getStackTrace()[2].getMethodName());
		if (logtxt != null) mens = mens.concat("->"+logtxt);
		Log.i(TAG,mens);
	}
	
	/* --------------------------------------------------------------------------------------------
	 * ***************** FUNCIONES NEAR FIELD COMMUNICATIONS **************************************
	 * -------------------------------------------------------------------------------------------- 
	 */

	/*
	 * Averiguo si el celular tiene soporte NFC
	 */
	public boolean soportaNFC() {
		log("");
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();
		return nfcMgr.soportaNFC(context);
	}

	/*
	 * Averiguo si el celular tiene soporte NFC Host Card Emulation
	 */
	public boolean soportaNFCHce() {
		log("");		
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.soportaNFCHce(context);
	}
	
	/*
	 * Chequear si el NFC se encuentra habilitado en este momento
	 */
	public boolean nfcHabilitado() {
		log("");		
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.nfcHabilitado(context);
	}

	/*
	 * Habilita la escucha del Tag para escritura o grabacion del mismo
	 */
	public boolean escucharTagNdefGrabar(Activity activity, Context context) {
		log("");		
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.escucharTagNdefEscribir(activity, context);
	}
	
	/*
	 * Deshabilita la escucha del Tag para escritura o grabacion del mismo
	 */
	public void noEscucharTagNdefGrabar(Activity activity, Context context) {
		log("");		
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		nfcMgr.noEscucharTagNdefGrabar(activity, context);
	}
	
	/*
	 * Escribe el mensaje NDEF en el Tag detectado
	 */
	public String escribirNdefMessageToTag(NdefMessage message, Tag detectedTag) {
		log("");
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.escribirNdefMessageToTag(message, detectedTag);
	}
	
	/*
	 * Obtiene el Tag descubierto a partir del Intent
	 */
	public Tag obtenerTagDescubierto(Intent intent) {
		log("");				
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.obtenerTagDescubierto(intent);
	}
	
	/*
	 * Preparar mensaje NDEF para URL
	 * Devuelve la excepcion URISyntaxException si la url no esta bien formada
	 */
	public NdefMessage prepararMensNdefUrl(String url) throws URISyntaxException {
		log("");				
		ProShopNFCMgr nfcMgr = new ProShopNFCMgr();		
		return nfcMgr.prepararMensNdefUrl(url);
	}
	
	/* --------------------------------------------------------------------------------------------
	 * ***************** FUNCIONES BLUETOOTH Y BLUETOOTH LOW ENERGY *******************************
	 * -------------------------------------------------------------------------------------------- 
	 */
	
	/*
	 * Averiguo si el celular es capaz de comunicarse con dispositivos Bluetooth Low Energy
	 */
	public boolean soportaBLE() {
		log("");				
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.soportaBLE(context);
	}
	
	/*
	 * Averiguo si el celular es capaz de comunicarse por Bluetooth
	 */
	public boolean soportaBluetooth() {
		log("");				
		ProShopBleMgr bleMgr = new ProShopBleMgr();
		return bleMgr.soportaBluetooth(context);
	}
	
	/*
	 * Chequear si el Bluetooth se encuentra habilitado en este momento
	 */
	public boolean bluetoothHabilitado() {
		log("");				
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
