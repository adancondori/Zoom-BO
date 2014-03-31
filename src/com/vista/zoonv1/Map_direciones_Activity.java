package com.vista.zoonv1;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class Map_direciones_Activity extends SherlockFragmentActivity {
	SupportMapFragment mapFragment;
	GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_direciones_);
		initilizeMap();
	}

	private void initilizeMap() {

		if (map == null) {
			mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_direcciones);
			map = mapFragment.getMap();
			map.setMyLocationEnabled(true);
			if (map == null) {
				System.out.println("Mapfragment::   Null");
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
				map.getUiSettings().setMyLocationButtonEnabled(false);
				map.getUiSettings().setZoomControlsEnabled(false);
				map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
					@Override
					public void onMapClick(LatLng arg0) {
						// getBarriosWeb(String.valueOf(arg0.latitude) + ":"
						// + String.valueOf(arg0.longitude));imglocation
					}
				});
			}
		}
	}

}
