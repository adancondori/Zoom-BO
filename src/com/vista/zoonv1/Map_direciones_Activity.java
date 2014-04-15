package com.vista.zoonv1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.widget.ShareActionProvider;
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
import com.vista.GPSsingleton.Pedido_Singleton;
import com.vista.dato.Lugares;
import com.vista.menuizq.LugaresActivity;
import com.vista.menuizq.PerfilActivity;
import com.vista.util.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Map_direciones_Activity extends SherlockFragmentActivity implements
		OnClickListener {
	SupportMapFragment mapFragment;
	GoogleMap map;
	// *----------- IU view vista
	TextView txt_titulo;
	TextView txt_direccion;
	Button btn_guardar;
	ImageButton imgbtn_tipo_mapas;
	ImageButton imgbtn_location_direccion;
	ImageButton imgbtn_location;
	ImageButton imgbtn_micro;

	// *----------- VARIABLE DEL SYSTEMA
	boolean sw = true;
	boolean sw_location = true;
	LatLng latLngCurrent = null;
	LatLng latLngCurrent_position = null;
	public Lugares LUGARES;
	private int TIPO = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_direciones_);
		initilizeMap();
		IU_initilize();
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey("LUGARES_VER")) {
				dir_Ver();
			} else if (getIntent().getExtras().containsKey("LUGARES_MODIFICAR")) {
				dir_Modificar();
			} else {
				dir_Nuevo();
			}
		} else {
			dir_Nuevo();
		}
	}

	// *--METODOS DEL SISITEMA--------------
	public void dir_Nuevo() {
		map.animateCamera(CameraUpdateFactory.zoomTo(12), 1000, null);
		map.moveCamera(CameraUpdateFactory.newLatLng(latLngCurrent_position));
		// latLngCurrent_position = new LatLng(GPS.getInstance().getLatitude(),
		// GPS.getInstance().getLongitude());
		TIPO = LugaresActivity.DIR_NUEVO;
		LUGARES = new Lugares();
		map.animateCamera(CameraUpdateFactory.zoomTo(12), 1000, null);
	}

	public void dir_Ver() {
		TIPO = LugaresActivity.DIR_VER;
		LUGARES = (Lugares) getIntent().getExtras().getSerializable(
				"LUGARES_VER");
		rellenar();
		setTitle("Vista de Dirección");
		btn_guardar.setVisibility(View.INVISIBLE);
		imgbtn_location_direccion.setVisibility(View.GONE);
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) txt_direccion
				.getLayoutParams();
		layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
	}

	public void dir_Modificar() {
		TIPO = LugaresActivity.DIR_MODIFICAR;
		LUGARES = (Lugares) getIntent().getExtras().getSerializable(
				"LUGARES_MODIFICAR");
		rellenar();
		sw_location = false;
		setTitle("Modificar Dirección");
	}

	public void rellenar() {
		if (LUGARES != null) {
			txt_direccion.setText(LUGARES.getDireccion());
			txt_titulo.setText(LUGARES.getTitulo());
			latLngCurrent = new LatLng(LUGARES.getLatitud(),
					LUGARES.getLongitud());
			AddMarket(latLngCurrent);
			CameraPosition camPos = new CameraPosition.Builder()
					.target(latLngCurrent).zoom(15).bearing(45).build();
			CameraUpdate camUpd3 = CameraUpdateFactory
					.newCameraPosition(camPos);
			map.animateCamera(camUpd3);
			// map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
			imgbtn_micro.setVisibility(View.VISIBLE);
		}
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			Intent intent = new Intent();
			Bundle extras = new Bundle();
			intent.putExtras(extras);
			TIPO = LugaresActivity.DIR_VER;
			if (getParent() == null) {
				setResult(TIPO, intent);
			} else {
				getParent().setResult(TIPO, intent);
			}
			finish();
			break;
		}
		return true;
	}

	private void IU_initilize() {
		txt_titulo = (TextView) findViewById(R.id.txt_titulo_direcciones);

		txt_direccion = (TextView) findViewById(R.id.txt_nombre_direcciones);

		imgbtn_tipo_mapas = (ImageButton) findViewById(R.id.imgbtn_tipo_map);
		imgbtn_tipo_mapas.setOnClickListener(this);

		imgbtn_location_direccion = (ImageButton) findViewById(R.id.imgbtn_location_direccion);
		imgbtn_location_direccion.setOnClickListener(this);

		btn_guardar = (Button) findViewById(R.id.btn_guardar_direcciones);
		btn_guardar.setOnClickListener(this);

		imgbtn_location = (ImageButton) findViewById(R.id.imgbtn_location);
		imgbtn_location.setOnClickListener(this);

		imgbtn_micro = (ImageButton) findViewById(R.id.imgbtn_micro);
		imgbtn_micro.setVisibility(View.INVISIBLE);
		imgbtn_micro.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_guardar_direcciones:
			finalizar_actividad();
			break;
		case R.id.imgbtn_location:
			map.moveCamera(CameraUpdateFactory
					.newLatLng(latLngCurrent_position));
			break;
		case R.id.imgbtn_location_direccion:
			txt_habilitar_ping();
			break;
		case R.id.imgbtn_micro:

			break;
		case R.id.imgbtn_tipo_map:
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

	public void finalizar_actividad() {

		if (txt_direccion.getText().toString().trim().equals("")
				|| txt_titulo.getText().toString().trim().equals("")) {
			dialogo_confirmacion("Datos Vacios Ej:\nRevise Nombre o Direccion\n!!.  Introdusca Datos...");
			return;
		}
		if (sw_location == true) {
			dialogo_confirmacion("Punto en el Mapa vacio:\nSeleccione un punto en el mapa con el icono verde");
			return;
		}
		switch (TIPO) {
		case LugaresActivity.DIR_MODIFICAR:
			LUGARES.setDireccion(txt_direccion.getText().toString());
			LUGARES.setTitulo(txt_titulo.getText().toString());
			LUGARES.setLatitud(latLngCurrent.latitude);
			LUGARES.setLongitud(latLngCurrent.longitude);
			Intent intent_mod = new Intent();
			Bundle extras_mod = new Bundle();
			extras_mod.putSerializable("DIR_MODIFICAR", LUGARES);
			intent_mod.putExtras(extras_mod);

			if (getParent() == null) {
				setResult(TIPO, intent_mod);
			} else {
				getParent().setResult(TIPO, intent_mod);
			}
			break;
		case LugaresActivity.DIR_VER:

			break;
		case LugaresActivity.DIR_NUEVO:
			LUGARES = new Lugares();
			LUGARES.setDireccion(txt_direccion.getText().toString());
			LUGARES.setTitulo(txt_titulo.getText().toString());
			LUGARES.setLatitud(latLngCurrent.latitude);
			LUGARES.setLongitud(latLngCurrent.longitude);
			Intent intent = new Intent();
			Bundle extras = new Bundle();
			extras.putSerializable("DIR_NUEVO", LUGARES);
			intent.putExtras(extras);

			if (getParent() == null) {
				setResult(TIPO, intent);
			} else {
				getParent().setResult(TIPO, intent);
			}
			break;

		default:
			break;
		}
		finish();
	}

	private void initilizeMap() {

		if (map == null) {
			mapFragment = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map_direcciones);
			this.map = mapFragment.getMap();

			if (map == null) {
				System.out.println("Mapdirecciones::   Null");
				Toast.makeText(this, "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			} else {
				latLngCurrent_position = new LatLng(-17.7811645, -63.1862261);
				// CameraPosition camPos = new CameraPosition.Builder()
				// .target(latLngCurrent_position).zoom(18).bearing(45)
				// .build();

				// CameraUpdate camUpd3 = CameraUpdateFactory
				// .newCameraPosition(camPos);

				map.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
				this.map.setOnMyLocationChangeListener(new Myubication());
				this.map.setMyLocationEnabled(true);
				this.map.getUiSettings().setMyLocationButtonEnabled(false);
				this.map.getUiSettings().setZoomControlsEnabled(false);
				this.map.getUiSettings().setCompassEnabled(true);
				this.map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

					@Override
					public void onCameraChange(CameraPosition arg0) {
						CameraPosition camPos = map.getCameraPosition();
						latLngCurrent = camPos.target;
						sw_location = true;
						imgbtn_location_direccion
								.setImageResource(R.drawable.ubicacion);
						if (TIPO == LugaresActivity.DIR_NUEVO) {
							map.clear();
						}
					}
				});
			}
		}
	}

	public void AddMarket(LatLng latLng) {
		map.clear();
		Marker melbourne = map.addMarker(new MarkerOptions().position(latLng)
				.title("Pais: Bolivia").snippet("Mi ubicacion"));

		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(getApplicationContext(),
						"Marcador presionado:\n" + marker.getTitle(),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
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
		txt_direccion.setEnabled(true);
		txt_titulo.setEnabled(true);
		imgbtn_location_direccion
				.setImageResource(R.drawable.ubicacion_location);
	}

	public void txt_des_habilitar() {
		map.clear();
		txt_direccion.setEnabled(false);
		txt_titulo.setEnabled(false);
		imgbtn_location_direccion.setImageResource(R.drawable.ubicacion);
	}

	public void dialogo_confirmacion(String cad) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				Map_direciones_Activity.this);

		// Setting Dialog Title
		// alertDialog.setTitle("Confirma Dirección...");

		// Setting Dialog Message
		alertDialog.setMessage(cad);

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("SI",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});
		// Showing Alert Message
		alertDialog.show();
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		Bundle extras = new Bundle();
		intent.putExtras(extras);

		if (getParent() == null) {
			setResult(TIPO, intent);
		} else {
			getParent().setResult(TIPO, intent);
		}
		finish();
	}

	// *-----ACTION PROVIDER COMPARTIR
	private void copyPrivateRawResourceToPubliclyAccessibleFile() {
		// map.GET
		InputStream inputStream = null;
		FileOutputStream outputStream = null;
		try {
			inputStream = getResources().openRawResource(R.raw.robot);
			outputStream = openFileOutput(SHARED_FILE_NAME,
					Context.MODE_WORLD_READABLE | Context.MODE_APPEND);
			byte[] buffer = new byte[1024];
			int length = 0;
			try {
				while ((length = inputStream.read(buffer)) > 0) {
					outputStream.write(buffer, 0, length);
				}
			} catch (IOException ioe) {
				/* ignore */
			}
		} catch (FileNotFoundException fnfe) {
			/* ignore */
		} finally {
			try {
				inputStream.close();
			} catch (IOException ioe) {
				/* ignore */
			}
			try {
				outputStream.close();
			} catch (IOException ioe) {
				/* ignore */
			}
		}
	}

	// *------MENU CREACION Y SELECCION
	private static final String SHARED_FILE_NAME = "compartir.png";

	private Intent createShareIntent() {
		Intent shareIntent = new Intent(Intent.ACTION_SEND);
		shareIntent.setType("image/*");
		Uri uri = Uri.fromFile(new File(getFilesDir(), SHARED_FILE_NAME));
		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

		// Intent shareIntent = new Intent(Intent.ACTION_SEND);
		// shareIntent.setType("image/*");
		// Uri uri = Uri.fromFile(getFileStreamPath("SHARED_FILE_NAME"));
		// shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		return shareIntent;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (TIPO == LugaresActivity.DIR_VER) {
			SubMenu subMenu = menu.addSubMenu(Menu.NONE,
					R.id.direcciones_compartir, Menu.NONE, "");
			subMenu.setIcon(R.drawable.p_share);
			MenuItem subMenu1Item = subMenu.getItem();
			subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
					| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		return true;
	}

	public boolean onMenuItemSelected1(int featureId, MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.direcciones_compartir:
			Toast.makeText(getApplicationContext(), "PRUEBA",
					Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}

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
