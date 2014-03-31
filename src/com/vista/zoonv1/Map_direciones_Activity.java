package com.vista.zoonv1;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vista.GPSsingleton.GPS;
import com.vista.menuizq.PerfilActivity;
import com.vista.util.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Map_direciones_Activity extends SherlockFragmentActivity {
	SupportMapFragment mapFragment;
	GoogleMap map;
	TextView txt_etiqueta;
	TextView txt_nombre;
	Button btn_guardar;
	ImageView img_tipo_mapas;
	ImageView img_acetar_latlong;
	boolean sw = true;
	boolean sw_location = true;
	LatLng latLngCurrent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_direciones_);
		initilizeMap();
		IU_initilize();
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			finish();
			break;

		}
		return true;
	}

	private void IU_initilize() {
		txt_etiqueta = (TextView) findViewById(R.id.txt_etiqueta_direcciones);
		img_tipo_mapas = (ImageView) findViewById(R.id.img_tipo_mapas);
		img_tipo_mapas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (sw) {
					img_tipo_mapas.setImageResource(R.drawable.uv_barrios);
					map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
				} else {
					img_tipo_mapas.setImageResource(R.drawable.mapas);
					map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
				}
				sw = !sw;
			}
		});
		img_acetar_latlong = (ImageView) findViewById(R.id.img_location_direcciones);
		img_acetar_latlong.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				txt_habilitar_ping();
			}
		});
		txt_nombre = (TextView) findViewById(R.id.txt_nombre_direcciones);
		btn_guardar = (Button) findViewById(R.id.btn_guardar_direcciones);
		btn_guardar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle extras = new Bundle();
				extras.putString("nombre", txt_nombre.getText().toString()
						.trim());
				extras.putString("etiqueta", txt_etiqueta.getText().toString()
						.trim());
				intent.putExtras(extras);
				//
				// setResult(RESULT_OK, intent);
				// Intent data = new Intent();
				// intent.putExtra("Latitude", 45);
				// intent.putExtra("Longitude", 45);
				if (getParent() == null) {
					setResult(Activity.RESULT_OK, intent);
				} else {
					getParent().setResult(Activity.RESULT_OK, intent);
				}
				// startActivityForResult(intent, RESULT_OK);
				finish();
			}
		});

	}

	private void initilizeMap() {

		if (map == null) {
			mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_direcciones);
			map = mapFragment.getMap();
			map.setMyLocationEnabled(true);
			map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

				@Override
				public void onCameraChange(CameraPosition arg0) {
					// TODO Auto-generated method stub
					CameraPosition camPos = map.getCameraPosition();
					latLngCurrent = camPos.target;
					// Toast.makeText(
					// getApplicationContext(),
					// "lat: " + String.valueOf(latLngCurrent.latitude)
					// + "\nlong: "
					// + String.valueOf(latLngCurrent.longitude),
					// Toast.LENGTH_SHORT).show();

					// double latitud = coordenadas.latitude;
					// double longitud = coordenadas.longitude;
					sw_location = true;
					img_acetar_latlong.setImageResource(R.drawable.ubicacion);
					float zoom = camPos.zoom;
					float orientacion = camPos.bearing;
					float angulo = camPos.tilt;
				}
			});
			// MyLocationChangeListener(new Myubication());
			if (map == null) {
				System.out.println("Mapdirecciones::   Null");
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
				// map.setOnMapClickListener(new GoogleMap.OnMapClickListener()
				// {
				// @Override
				// public void onMapClick(LatLng arg0) {
				// // getBarriosWeb(String.valueOf(arg0.latitude) + ":"
				// // + String.valueOf(arg0.longitude));imglocation
				// }
				// });
			}
		}
	}

	public void AddMarket(LatLng latLng) {
		map.clear();
		Marker melbourne = map.addMarker(new MarkerOptions().position(latLng)
				.title("Pais: Bolivia").snippet("Mi Dettale"));
		// .icon(BitmapDescriptorFactory.fromResource(R.drawable.menos)));//cambia
		// de icono
		// melbourne.showInfoWindow(); //para mostrar la info del marcador

		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(getApplicationContext(),
						"Marcador presionado:\n" + marker.getTitle(),
						Toast.LENGTH_SHORT).show();

				return false;
			}
		});
		// map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		// map.moveCamera(CameraUpdateFactory.newLatLngZoom(SantaCruz, 15));
	}

	public void txt_habilitar_ping() {
		// sw_location
		if (sw_location == true) {
			txt_habilitar();
		} else {
			txt_des_habilitar();
		}
		sw_location = !sw_location;
	}

	public void txt_habilitar() {
		AddMarket(latLngCurrent);
		txt_etiqueta.setEnabled(true);
		txt_etiqueta.setEnabled(true);
		img_acetar_latlong.setImageResource(R.drawable.ubicacion_location);
	}

	public void txt_des_habilitar() {
		map.clear();
		txt_etiqueta.setEnabled(false);
		txt_etiqueta.setEnabled(false);
		img_acetar_latlong.setImageResource(R.drawable.ubicacion);
	}
}
