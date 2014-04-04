package com.vista.menuizq;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import com.vista.adapter.Adapter_Contacto;
import com.vista.dato.Contacto;
import com.vista.zoonv1.R;
import com.vista.zoonv1.BuscarContactoActivity;
import com.vista.zoonv1.SoliucitudesActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ContactosActivity extends SherlockActivity implements
		OnClickListener, OnItemClickListener {
	ListView listView_contactos;
	private Adapter_Contacto adapter_Contacto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		View v = getLayoutInflater().inflate(R.layout.activity_contactos, null);
		setContentView(v);
		IU_iniciar();
	}

	public void IU_iniciar() {
		adapter_Contacto = new Adapter_Contacto(getApplicationContext());
		rellenar_Datos();
		listView_contactos = (ListView) findViewById(R.id.list_contactos);
		listView_contactos.setAdapter(adapter_Contacto);
		listView_contactos.setOnItemClickListener(this);
	}

	public void rellenar_Datos() {
		String[] nombres = getResources().getStringArray(
				R.array.contactos_items);
		// nav drawer icons from resources
		String[] telefonos = getResources().getStringArray(
				R.array.contactos_telefono_items);
		for (int i = 0; i < nombres.length; i++) {
			adapter_Contacto.addItem(new Contacto(0, nombres[i], telefonos[i],
					"", telefonos[i], 0, Adapter_Contacto.TYPE_ELEMENTO));
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 1: { // Nuevo Grupo solicitudes
			// mostrarDialogo();
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void crearsolicitud(View view) {
		Intent intent = new Intent(getApplicationContext(),
				SoliucitudesActivity.class);
		startActivity(intent);
	}

	public void crearcontactos(View view) {
		Intent intent = new Intent(getApplicationContext(),
				BuscarContactoActivity.class);
		startActivity(intent);
	}

	// private void mostrarDialogo() {
	// LayoutInflater li = LayoutInflater.from(this);
	// View promptsView = li.inflate(R.layout.dialogo, null);
	//
	// AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	// alertDialogBuilder.setView(promptsView);
	// alertDialogBuilder.setTitle("Nueva Categoria");
	// alertDialogBuilder.setCancelable(true);
	// final EditText userInput = (EditText) promptsView
	// .findViewById(R.id.edit_Name);
	//
	// alertDialogBuilder
	// .setCancelable(false)
	// .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// actualizar(userInput.getText().toString());
	// }
	// })
	// .setNegativeButton("Cancel",
	// new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int id) {
	// dialog.cancel();
	// }
	// });
	//
	// // create alert dialog
	// AlertDialog alertDialog = alertDialogBuilder.create();
	// alertDialog.show();
	//
	// }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (resultCode == RESULT_OK) {
			// cargarExpandableList();
		}

	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			setResult(RESULT_OK, getIntent());
			finish();
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(RESULT_OK, getIntent());
		finish();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(),
				DetalleContactoActivity.class);
		intent.putExtra("CONTACTO", adapter_Contacto.getContacto(position));

		startActivity(intent);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	//

}
