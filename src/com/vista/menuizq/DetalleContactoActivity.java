package com.vista.menuizq;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.vista.dato.Contacto;
import com.vista.zoonv1.R;
import com.vista.zoonv1.Map_direciones_Activity.Myubication;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetalleContactoActivity extends SherlockFragmentActivity implements
		OnClickListener {
	GoogleMap map;
	Contacto contacto;
	public TextView txt_nombre_contacto;
	public TextView txt_telefono_contacto;
	public ImageButton imgbtn_location;
	public ImageButton imgbtn_tipo_mapas;
	public ImageButton imgbtn_zoom;
	public ImageButton imgbtn_micro;
	// *-------------VARIABLES DE SISTEMA
	private LatLng latLngCurrent_position = null;
	boolean sw = true;

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

	// *-----METODOS DEL SISTEMA
	private void IU_iniciar() {
		txt_nombre_contacto = (TextView) findViewById(R.id.txt_nombre_contacto);
		txt_telefono_contacto = (TextView) findViewById(R.id.txt_telefono_contacto);
		imgbtn_location = (ImageButton) findViewById(R.id.imgbtn_location);
		imgbtn_location.setOnClickListener(this);

		imgbtn_tipo_mapas = (ImageButton) findViewById(R.id.imgbtn_tipo_mapas);
		imgbtn_tipo_mapas.setOnClickListener(this);

		imgbtn_zoom = (ImageButton) findViewById(R.id.imgbtn_zoom);
		imgbtn_zoom.setOnClickListener(this);

		imgbtn_micro = (ImageButton) findViewById(R.id.imgbtn_micro);
		imgbtn_micro.setOnClickListener(this);
	}

	// *--------EVENTOS DE SISTEMA
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imgbtn_location:
			map.moveCamera(CameraUpdateFactory
					.newLatLng(latLngCurrent_position));
			break;
		case R.id.imgbtn_micro:

			break;
		case R.id.imgbtn_tipo_mapas:
			if (sw) {
				imgbtn_tipo_mapas.setImageResource(R.drawable.uv_barrios);
				map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			} else {
				imgbtn_tipo_mapas.setImageResource(R.drawable.mapas);
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			}
			sw = !sw;
			break;
		default:
			break;
		}

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

	@Override
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
				latLngCurrent_position = new LatLng(-17.39379, -66.156972);
				CameraPosition camPos = new CameraPosition.Builder()
						.target(latLngCurrent_position).zoom(15).bearing(45)
						.build();

				CameraUpdate camUpd3 = CameraUpdateFactory
						.newCameraPosition(camPos);
				map.animateCamera(camUpd3);
				this.map.setOnMyLocationChangeListener(new Myubication());
				this.map.setMyLocationEnabled(true);
				this.map.getUiSettings().setMyLocationButtonEnabled(false);
				this.map.getUiSettings().setZoomControlsEnabled(false);
				this.map.getUiSettings().setCompassEnabled(true);
			}
		}
	}

	// *----CLASES AUXILIARES PARA EL SISTEMAS
	public class Myubication implements OnMyLocationChangeListener {

		@Override
		public void onMyLocationChange(Location location) {
			// Getting longitude of the current location
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			// Creating a LatLng object for the current location
			latLngCurrent_position = new LatLng(latitude, longitude);
		}

	}

}
