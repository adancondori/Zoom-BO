package com.vista.zoonv1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class PrincipalActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// esto quita el título de la activity en la parte superior
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// y esto para pantalla completa (oculta incluso la barra de estado)
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_principal);
		inicializarcomponentes();
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(intent);
		finish();
	}

	// ////////////
	EditText editlogin;
	EditText editpass;

	public void inicializarcomponentes() {
		editlogin = (EditText) findViewById(R.id.editLogin);
		editpass = (EditText) findViewById(R.id.editPass);
		editpass.setOnKeyListener(new View.OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_BACK:
						finish();
					case KeyEvent.KEYCODE_ENTER:
						validardatos();
						return true;
					}
				}
				return true;
			}

		});
	}

	public void validardatos(View v) {
		String cad = editlogin.getText().toString();
		if (cad.equals("admin")) {
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
		} else {
		}
	}

	public void validardatos() {
		String cad = editlogin.getText().toString();
		InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editpass.getWindowToken(), 0);
		if (cad.equals("admin")) {
			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Error: Verifique su contraseña",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void registrarse(View view) {
		Intent intent = new Intent(getApplicationContext(),
				RegistrarseActivity.class);
		startActivity(intent);
	}

	public void olvidoContracena(View view) {
		Intent intent = new Intent(getApplicationContext(),
				RecuperarcontrasenaActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}
