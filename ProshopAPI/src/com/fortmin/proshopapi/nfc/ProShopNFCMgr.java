package com.fortmin.proshopapi.nfc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Build;
import android.util.Log;

public class ProShopNFCMgr {
	
	private final String TAG = "PSHAPI";	
	
	private void log(String logtxt) {
		String mens = this.getClass().getName()+"->";
		if (logtxt != null) mens = mens.concat("->"+logtxt);
		Log.i(TAG,mens);
	}

	/*
	 * Averiguo si el celular tiene soporte NFC
	 */
	public boolean soportaNFC(Context context) {
		log("soportaNFC");
		PackageManager pckMgr = context.getPackageManager();
		return pckMgr.hasSystemFeature(PackageManager.FEATURE_NFC);
	}

	/*
	 * Averiguo si el celular tiene soporte NFC Host Card Emulation
	 */
	public boolean soportaNFCHce(Context context) {
		log("soportaNFCHce");		
		boolean soporta = false;
		PackageManager pckMgr = context.getPackageManager();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {	
			soporta = pckMgr.hasSystemFeature(PackageManager.FEATURE_NFC_HOST_CARD_EMULATION);
		}
		return soporta;
	}
	
	/*
	 * Chequear si el NFC se encuentra habilitado en este momento
	 */
	public boolean nfcHabilitado(Context context) {
		log("nfcHabilitado");		
		boolean habilitado = false;
		if (soportaNFC(context)) {
			NfcManager nfcMgr = (NfcManager) context.getSystemService(Context.NFC_SERVICE);
			if (nfcMgr != null) {
				NfcAdapter nfcAdapter = nfcMgr.getDefaultAdapter();
				if (nfcAdapter != null) {
					habilitado = nfcAdapter.isEnabled();
				}
			}
		}
		return habilitado;
	}
	
	/*
	 * Habilita la escucha del Tag para escritura o grabacion del mismo
	 */
	public boolean escucharTagNdefEscribir(Activity activity, Context context, Object clase) {
		log("escucharTagNdefEscribir");
		boolean result = false;
		if (nfcHabilitado(context)) {
	    	NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(context); 
	    	if (mNfcAdapter != null) {
	    		log("enableForegroundDispatch");
		    	PendingIntent mPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, (Class<?>) clase).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		   	 	IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
		   	 	IntentFilter[] mFilters = new IntentFilter[] { ndef, };
		   	 	String[][] mTechLists = new String[][] { new String[] { Ndef.class.getName() }, new String[] { NdefFormatable.class.getName() }};
		   	 	mNfcAdapter.enableForegroundDispatch(activity, mPendingIntent, mFilters, mTechLists);
		   	 	result = true;
	    	}
		}
		return result;
	}
	
	/*
	 * Deshabilita la escucha del Tag para escritura o grabacion del mismo
	 */
	public void noEscucharTagNdefGrabar(Activity activity, Context context) {
		log("noEscucharTagNdefGrabar");
		if (nfcHabilitado(context)) {
	    	NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(context); 
	    	if (mNfcAdapter != null) {
	    		log("enableForegroundDispatch");	    		
	            mNfcAdapter.disableForegroundDispatch(activity);
	    	}
		}
	}
	
	/*
	 * Escribe el mensaje NDEF en el Tag detectado
	 */
    public String escribirNdefMessageToTag(NdefMessage message, Tag detectedTag) {
    	log("escribirNdefMessageToTag");
    	String respuesta = "OK";
        int size = message.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(detectedTag);
            if (ndef != null) {
                ndef.connect();
                if (!ndef.isWritable()) {
                	log("TAG_READ_ONLY");
                	respuesta = "TAG_READ_ONLY";
                }
                else {
                    if (ndef.getMaxSize() < size) {
                    	log("TAG_LLENO");
                    	respuesta = "TAG_LLENO";
                    }
                    else {
                    	log("writeNdefMessage");
                        ndef.writeNdefMessage(message);
                        ndef.close();                
                    }
                }
            } else {
                NdefFormatable ndefFormat = NdefFormatable.get(detectedTag);
                if (ndefFormat != null) {
                    try {
                    	log("format message");
                    	ndefFormat.connect();
                    	ndefFormat.format(message);
                    	ndefFormat.close();
                    } catch (IOException e) {
                    	log("TAG_FORMATO_INVALIDO");
                    	respuesta = "TAG_FORMATO_INVALIDO";
                    }
                } else {
                	log("TAG_NDEF_NO_SOPORTADO");
                    respuesta = "TAG_NDEF_NO_SOPORTADO";
                }
            }
        } catch (Exception e) {
        	log("TAG_FALLO_ESCRITURA");
        	respuesta = "TAG_FALLO_ESCRITURA";
        }
        return respuesta;
    }

	/*
	 * Obtiene el Tag descubierto a partir del Intent
	 */
	public Tag obtenerTagDescubierto(Intent intent) {
		Tag tag = null;
		if (intent != null) {
			log("obtener tag");
			tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);			
		}
		return tag;
	}
	
	/*
	 * Preparar mensaje NDEF para URL
	 */
	public NdefMessage prepararMensNdefUrl(String textoUrl) throws URISyntaxException {
		log("");
		NdefMessage nMessage = null;
		String url = (new URI(textoUrl)).normalize().toString();
		NdefRecord extRecord1 = NdefRecord.createUri(url);
		nMessage = new NdefMessage(new NdefRecord[] { extRecord1 });
		return nMessage;
	}

}
