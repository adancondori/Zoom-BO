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
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Map_direciones_Activity extends SherlockFragmentActivity {
	SupportMapFragment mapFragment;
	GoogleMap map;
	TextView txt_titulo;
	TextView txt_direccion;
	Button btn_guardar;
	ImageView img_tipo_mapas;
	ImageView img_acetar_latlong;
	boolean sw = true;
	boolean sw_location = true;
	LatLng latLngCurrent = null;
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
				TIPO = LugaresActivity.DIR_VER;
				LUGARES = (Lugares) getIntent().getExtras().getSerializable(
						"LUGARES_VER");
				rellenar();
				setTitle("Vista de Direcci�n");
				btn_guardar.setVisibility(View.INVISIBLE);
				// btn_guardar.setHeight(0);
				// android:layout_alignParentBottom="true"
				RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) txt_direccion
						.getLayoutParams();
				layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			} else if (getIntent().getExtras().containsKey("LUGARES_MODIFICAR")) {
				LUGARES = (Lugares) getIntent().getExtras().getSerializable(
						"LUGARES_MODIFICAR");
				rellenar();
				setTitle("Modificar Direcci�n");
				TIPO = LugaresActivity.DIR_MODIFICAR;
			} else {
				TIPO = LugaresActivity.DIR_NUEVO;
				LUGARES = new Lugares();
			}
		} else {
			TIPO = LugaresActivity.DIR_NUEVO;
			LUGARES = new Lugares();
		}
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
		img_tipo_mapas = (ImageView) findViewById(R.id.img_tipo_mapas);
		img_tipo_mapas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
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
		txt_direccion = (TextView) findViewById(R.id.txt_nombre_direcciones);
		btn_guardar = (Button) findViewById(R.id.btn_guardar_direcciones);
		btn_guardar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finalizar_actividad();
			}
		});

	}

	public void finalizar_actividad() {
		if (txt_direccion.getText().toString().trim().equals("")
				|| txt_titulo.getText().toString().trim().equals("")) {
			dialogo_confirmacion("El nombre o direccion esta en Blanco!!.  Introdusca Datos...");
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
			map = mapFragment.getMap();
			map.setMyLocationEnabled(true);
			map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

				@Override
				public void onCameraChange(CameraPosition arg0) {
					CameraPosition camPos = map.getCameraPosition();
					latLngCurrent = camPos.target;
					sw_location = true;
					img_acetar_latlong.setImageResource(R.drawable.ubicacion);
					// map.clear();
				}
			});
			if (map == null) {
				System.out.println("Mapdirecciones::   Null");
				Toast.makeText(this, "Sorry! unable to create maps",
						Toast.LENGTH_SHORT).show();
			} else {
				LatLng SantaCruz = new LatLng(-17.39379, -66.156972);
				CameraPosition camPos = new CameraPosition.Builder()
						.target(SantaCruz).zoom(15).bearing(45).build();

				CameraUpdate camUpd3 = CameraUpdateFactory
						.newCameraPosition(camPos);
				map.animateCamera(camUpd3);
				map.setMyLocationEnabled(true);
				map.getUiSettings().setMyLocationButtonEnabled(true);
				map.getUiSettings().setZoomControlsEnabled(false);

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
		img_acetar_latlong.setImageResource(R.drawable.ubicacion_location);
	}

	public void txt_des_habilitar() {
		map.clear();
		txt_direccion.setEnabled(false);
		txt_titulo.setEnabled(false);
		img_acetar_latlong.setImageResource(R.drawable.ubicacion);
	}

	public void dialogo_confirmacion(String cad) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				Map_direciones_Activity.this);

		// Setting Dialog Title
		// alertDialog.setTitle("Confirma Direcci�n...");

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
//		map.GET
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
		 
//		Intent shareIntent = new Intent(Intent.ACTION_SEND);
//		shareIntent.setType("image/*");
//		Uri uri = Uri.fromFile(getFileStreamPath("SHARED_FILE_NAME"));
//		shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
		return shareIntent;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (TIPO==LugaresActivity.DIR_VER) {
			 SubMenu subMenu = menu.addSubMenu(Menu.NONE,
					 R.id.direcciones_compartir, Menu.NONE, "");
					 subMenu.setIcon(R.drawable.p_share);
					 MenuItem subMenu1Item = subMenu.getItem();
					 subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
					 | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		}
		//
		//*----- esta parte es del la libreira Action Sherlock
//		getSupportMenuInflater().inflate(R.menu.direciones_, menu);
//
//		// Set file with share history to the provider and set the share intent.
//		MenuItem actionItem = menu.findItem(R.id.direcciones_compartir);
//		ShareActionProvider actionProvider = (ShareActionProvider) actionItem
//				.getActionProvider();
//		actionProvider
//				.setShareHistoryFileName(ShareActionProvider.DEFAULT_SHARE_HISTORY_FILE_NAME);
//		// Note that you can set/change the intent any time,
//		// say when the user has selected an image.
//		actionProvider.setShareIntent(createShareIntent());
		return true;
	}

	public boolean onMenuItemSelected1(int featureId, MenuItem item) {
		int itemId = item.getItemId();
		switch (itemId) {
		case R.id.direcciones_compartir:
			Toast.makeText(getApplicationContext(), "PRUEBA", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}
}
