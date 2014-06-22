package com.fortmin.nfc;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class Opciones extends Activity {
    private Button btn_grabar_email;
    private Button btn_grabar_url;
    private Button btn_grabar_telefono;
    private Button btn_grabar_sms;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opciones);
        btn_grabar_email=(Button)findViewById(R.id.grabarEmail);
        btn_grabar_url=(Button)findViewById(R.id.grabarUrl);
        btn_grabar_telefono=(Button)findViewById(R.id.grabarTelefono);
        btn_grabar_sms=(Button)findViewById(R.id.grabarSms);
        //cambio la fuente del botón
        Typeface fuente=Typeface.createFromAsset(getAssets(),"gloriahallelujah.ttf");
        // agrego la fuente al botón
        btn_grabar_email.setTypeface(fuente);
        btn_grabar_url.setTypeface(fuente);
        btn_grabar_telefono.setTypeface(fuente);
        btn_grabar_sms.setTypeface(fuente);
        //Le pongo degrade plateado al botón
        btn_grabar_email.setBackgroundResource(R.drawable.degradado);
        btn_grabar_url.setBackgroundResource(R.drawable.degradado);
        btn_grabar_telefono.setBackgroundResource(R.drawable.degradado);
        btn_grabar_sms.setBackgroundResource(R.drawable.degradado);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opciones, menu);
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

	

}
