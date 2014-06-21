package com.fortmin.nfc;

import java.net.URISyntaxException;

import com.fortmin.proshopapi.ProShopMgr;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	// DECLARACION DE VARIABLES
	protected String textoUrl;
	ProShopMgr proshopmgr;
	private Context context;
	private Tag tag = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText editUrl = (EditText) findViewById(R.id.Texturl);

		Button botonWriteUrl = (Button) findViewById(R.id.botonWriteUrl);
		Button botonWriteMail = (Button) findViewById(R.id.botonWriteMail);
		Button botonWriteTel = (Button) findViewById(R.id.botonWriteTel);

		botonWriteUrl.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				textoUrl = editUrl.getText().toString();
				Mensaje(view, "Toque el Tag NFC Tag para grabar\n");
			}
		});
		
		botonWriteMail.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				textoUrl = editUrl.getText().toString();
				Mensaje(view, "Toque el Tag NFC Tag para grabar\n");
			}
		});
		
		botonWriteTel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				textoUrl = editUrl.getText().toString();
				Mensaje(view, "Toque el Tag NFC Tag para grabar\n");
			}
		});

		context = getApplicationContext();
		Button salirButton = (Button) this.findViewById(R.id.botonSalir);
		salirButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				finish();
			}
		});
		proshopmgr = new ProShopMgr(context);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void Mensaje(View v, String mensaje) {
		Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
		toast.show();
	}

	public void Mensaje(Activity act, String mensaje) {
		Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	public void onResume() {
		super.onResume();
		proshopmgr.escucharTagNdefGrabar(this, context, getClass());
	}

	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		tag = proshopmgr.obtenerTagDescubierto(intent);
		if (tag != null) {
			try {
				NdefMessage newMessage = proshopmgr
						.prepararMensNdefUrl(textoUrl);
				proshopmgr.escribirNdefMessageToTag(newMessage, tag);
			} catch (URISyntaxException e) {
				Mensaje(this, "URL con formato incorrecto");
			}
		}

	}

	@Override
	public void onPause() {
		super.onPause();
		proshopmgr.noEscucharTagNdefGrabar(this, context);
	}

}
