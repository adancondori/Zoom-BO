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
import com.vista.menuizq.DetalleContactoActivity.Myubication;
import com.vista.zoonv1.R;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EventosActivity extends SherlockFragmentActivity implements
		OnClickListener {
	// *-----VARIABLES DEL SISTEMA
	GoogleMap map;
	private LatLng latLngCurrent_position = null;
	public ImageButton imgbtn_location;
	public ImageButton imgbtn_tipo_mapas;
	public ImageButton imgbtn_zoom;
	public ImageButton imgbtn_micro;
	boolean sw = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_eventos);
		setContentView(R.layout.activity_eventos);
		IU_iniciar();
		initilizeMap();
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	// *-----METODOS DEL SISTEMA
	private void IU_iniciar() {
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

	private void initilizeMap() {
		if (map == null) {
			map = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_evento)).getMap();

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
				this.map.setMyLocationEnabled(true);
				this.map.setOnMyLocationChangeListener(new Myubication());
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
		// TODO Auto-generated method stub
		super.onBackPressed();
		setResult(RESULT_OK, getIntent());
		finish();
	}

}
