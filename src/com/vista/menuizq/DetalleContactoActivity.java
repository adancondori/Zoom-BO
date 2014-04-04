package com.vista.menuizq;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.vista.dato.Contacto;
import com.vista.zoonv1.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleContactoActivity extends SherlockFragmentActivity {
	GoogleMap map;
	Contacto contacto;
	TextView txt_nombre_contacto;
	TextView txt_telefono_contacto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detalle_contacto);
		initilizeMap();
		IU_iniciar();
		if (getIntent().getExtras() != null
				&& getIntent().getExtras().containsKey("CONTACTO")) {
			contacto = (Contacto) getIntent().getExtras().getSerializable(
					"CONTACTO");
			rellenar_datos();

		}
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void IU_iniciar() {
		txt_nombre_contacto = (TextView) findViewById(R.id.txt_nombre_contacto);
		txt_telefono_contacto = (TextView) findViewById(R.id.txt_telefono_contacto);
	}

	public void rellenar_datos() {
		txt_nombre_contacto.setText(contacto.getNombre());
		txt_telefono_contacto.setText(contacto.getTelefono());

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

	private void initilizeMap() {
		if (map == null) {
			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.mapdetallecontacto)).getMap();

			if (map == null) {
				System.out.println("DetalleContacto::   Null");
				Toast.makeText(this, "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			} else {
				LatLng SantaCruz = new LatLng(-17.39379, -66.156972);
				CameraPosition camPos = new CameraPosition.Builder()
						.target(SantaCruz).zoom(18).bearing(45).build();

				CameraUpdate camUpd3 = CameraUpdateFactory
						.newCameraPosition(camPos);
				map.animateCamera(camUpd3);
				map.setMyLocationEnabled(true);
				map.getUiSettings().setMyLocationButtonEnabled(true);
				map.getUiSettings().setZoomControlsEnabled(false);
			}
		}
	}
}
