package com.vista.zoonv1;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.vista.menuizq.LugaresActivity;
import com.vista.zoonv1.R;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class LugaresFragmentComent extends Fragment {
	View rootView;
	LayoutInflater inflater;
	GoogleMap map;
	ImageView img_ping;
	LugaresActivity lugaresActivity;

	TextView txt_nombre;

	public LugaresFragmentComent() {
	}

	public LugaresFragmentComent(LugaresActivity activity) {
		this.lugaresActivity = activity;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;
		this.rootView = this.inflater.inflate(R.layout.lugarescoment,
				container, false);
		// initilizeMap();
		IU_view();
		return rootView;

	}

	private void IU_view() {
		// TODO Auto-generated method stub
		txt_nombre = (TextView) rootView.findViewById(R.id.txt_edit);
		txt_nombre.setEnabled(false);
	}

	public void setitle(String tittle) {
		txt_nombre.setText(tittle);
	}

	// private void initilizeMap() {
	//
	// if (map == null) {
	// map = ((SupportMapFragment) getFragmentManager().findFragmentById(
	// R.id.maplugarescoment)).getMap();
	//
	// if (map == null) {
	// System.out.println("Homefragment::   Null");
	// Toast.makeText(inflater.getContext(),
	// "Sorry! unable to create maps", Toast.LENGTH_SHORT)
	// .show();
	// } else {
	// LatLng SantaCruz = new LatLng(-17.39379, -66.156972);
	// CameraPosition camPos = new CameraPosition.Builder()
	// .target(SantaCruz).zoom(18).bearing(45).build();
	//
	// CameraUpdate camUpd3 = CameraUpdateFactory
	// .newCameraPosition(camPos);
	// map.animateCamera(camUpd3);
	// map.setMyLocationEnabled(true);
	// map.getUiSettings().setMyLocationButtonEnabled(true);
	// map.getUiSettings().setZoomControlsEnabled(false);
	// // map.setOnMapClickListener(new GoogleMap.OnMapClickListener()
	// // {
	// // @Override
	// // public void onMapClick(LatLng arg0) {
	// // //
	// // }
	// // });
	// }
	// }
	// }

}
