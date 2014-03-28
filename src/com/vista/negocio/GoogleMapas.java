package com.vista.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.data.c;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vista.GPSsingleton.GPS;
import com.vista.GPSsingleton.Pedido_Singleton;

import com.vista.adapter.Marcador;
import com.vista.json.ParserFunction;
import com.vista.util.AlertDialogManager_Progres;
import com.vista.zoonv1.R;

@SuppressLint("NewApi")
public class GoogleMapas implements OnInfoWindowClickListener {
	View rootView;
	// Context context; map.setMyLocationEnabled ic_action_location_found1
	GoogleMap map;
	// GPS gps;
	LatLng SantaCruz = new LatLng(-17.7831265, -63.1820324);
	LatLng SantaCruz1 = new LatLng(-17.4704965, -63.1122724);
	LatLng SantaCruz2 = new LatLng(-17.4682765, -63.1124124);
	LatLng SantaCruz3 = new LatLng(-17.4676765, -63.1085624);
	ArrayList<LatLng> markerPoints;

	// ViewGroup container;

	public GoogleMapas(View rootView, GoogleMap map) {
		// TODO Auto-generated constructor stub
		// this.container = container;
		this.rootView = rootView;
		// initilizeMap();
		// this.context=context;
		this.map = map;
		markerPoints = new ArrayList<LatLng>();
		// this.map.getUiSettings().
		// this.map.setPadding(left, top, right, bottom)
		this.map.setPadding(0, 10, 0, 0);
		// this.map.setMyLocationEnabled(true);

		this.map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		// this.map.setMyLocationEnabled(true);
		this.map.getUiSettings().setCompassEnabled(true);

		// gps = new GPS(rootView.getContext(), this.map);
		// GPS.getInstance().setInstance(gps);
		// gps.getLocation();
		// SantaCruz = new LatLng(gps.getLatitude(), gps.getLongitude());
		// map.setOnMyLocationChangeListener(gps);

		miubicacion();
		// AddMarket();
		setUpEventSpots(SantaCruz);

	}

	// public void setCurrentubication(boolean sw) {
	// gps.setCurrentubication(sw);
	// }
	public final static int GOOGLE_MAP_TYPE_HYBRID=0;
	public final static int GOOGLE_MAP_TYPE_NORMAL=1;
	public void type(int type) {

		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

		switch (type) {
		case GOOGLE_MAP_TYPE_NORMAL:
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case GOOGLE_MAP_TYPE_HYBRID:
			map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

			break;
//		case 2:
//			map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//			break;
//		case 3:
//			map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//			break;
//		case 4:
//			map.setMapType(GoogleMap.MAP_TYPE_NONE);
//			break;
		}
		//
	}

	public void miubicacion() {
		// SantaCruz = new LatLng(-17.39379, -66.156972);

		CameraPosition camPos = new CameraPosition.Builder().target(SantaCruz)
				.zoom(18) // Establecemos el zoom en 19
				.bearing(45) // Establecemos la orientación con el noreste
				// arriba
				// .tilt(70) // Bajamos el punto de vista de la cámara 70
				// grados
				.build();

		CameraUpdate camUpd3 = CameraUpdateFactory.newCameraPosition(camPos);
		map.animateCamera(camUpd3);

	}

	public void AddMarket() {

		Marker melbourne = map.addMarker(new MarkerOptions()
				.position(SantaCruz).title("Pais: Bolivia")
				.snippet("Mi Dettale"));
		// .icon(BitmapDescriptorFactory.fromResource(R.drawable.menos)));//cambia
		// de icono
		// melbourne.showInfoWindow(); //para mostrar la info del marcador
		map.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(rootView.getContext(),
						"Marcador presionado:\n" + marker.getTitle(),
						Toast.LENGTH_SHORT).show();

				return false;
			}
		});
		map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(SantaCruz, 15));

	}

	public void IndoorMaps() {
		// Some buildings have indoor maps. Center the camera over
		// the building, and a floor picker will automatically appear.
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-33.86997,
				151.2089), 18));
	}

	public void market() {
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.889,
				-87.622), 16));

		// You can customize the marker image using images bundled with
		// your app, or dynamically generated bitmaps.
		map.addMarker(new MarkerOptions()
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher))
				.anchor(0.0f, 1.0f) // Anchors the marker on the bottom left
				.position(new LatLng(41.889, -87.622)));
	}

	public void FlatMarkers() {
		// LatLng mapCenter = new LatLng(41.889, -87.622);

		map.moveCamera(CameraUpdateFactory.newLatLngZoom(SantaCruz, 13));

		// Flat markers will rotate when the map is rotated,
		// and change perspective when the map is tilted.
		map.addMarker(new MarkerOptions()
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.mas))
				.position(SantaCruz).flat(true).rotation(245));

		CameraPosition cameraPosition = CameraPosition.builder()
				.target(SantaCruz).zoom(19).bearing(90).build();

		// Animate the change in camera view over 2 seconds
		map.animateCamera(
				CameraUpdateFactory.newCameraPosition(cameraPosition), 2000,
				null);
	}

	public void zoommas() {
		//
		// CameraPosition camPos = map.getCameraPosition();
		// float zoom = camPos.zoom - 1;
		// if (zoom < 0)
		// return;
		// LatLng coordenadas = camPos.target;
		// double latitud = coordenadas.latitude;
		// double longitud = coordenadas.longitude;
		// float orientacion = camPos.bearing;
		// float angulo = camPos.tilt;
		// SantaCruz = new LatLng(latitud, longitud);
		// CameraPosition camPosnuevo = new CameraPosition.Builder()
		// .target(SantaCruz).zoom(zoom) // Establecemos el zoom en 19
		// .bearing(orientacion) // Establecemos la orientación con el
		// // noreste
		// // arriba
		// .tilt(angulo) // Bajamos el punto de vista de la cámara 70
		// // grados
		// .build();
		//
		// CameraUpdate camUpd3 = CameraUpdateFactory
		// .newCameraPosition(camPosnuevo);
		// map.animateCamera(camUpd3);
		synchronized (map) {
			map.animateCamera(CameraUpdateFactory.zoomOut());
		}
	}

	public void zoommenos() {
		// CameraPosition camPos = map.getCameraPosition();
		// float zoom = camPos.zoom + 1;
		// if (zoom > 20)
		// return;
		// LatLng coordenadas = camPos.target;
		// double latitud = coordenadas.latitude;
		// double longitud = coordenadas.longitude;
		// float orientacion = camPos.bearing;
		// float angulo = camPos.tilt;
		// SantaCruz = new LatLng(latitud, longitud);
		// CameraPosition camPosnuevo = new CameraPosition.Builder()
		// .target(SantaCruz).zoom(zoom) // Establecemos el zoom en 19
		// .bearing(orientacion) // Establecemos la orientación con el
		// // noreste
		// // arriba
		// .tilt(angulo) // Bajamos el punto de vista de la cámara 70
		// // grados
		// .build();
		//
		// CameraUpdate camUpd3 = CameraUpdateFactory
		// .newCameraPosition(camPosnuevo);
		// map.animateCamera(camUpd3);

		map.animateCamera(CameraUpdateFactory.zoomIn());

	}

	public void Polylines(List<LatLng> list) {
		// map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.142,
		// 178.431), 2));
		PolylineOptions polylineOptions = new PolylineOptions();
		for (LatLng latLng : list) {
			polylineOptions.geodesic(true).add(latLng);
		}
		// Polylines are useful for marking paths and routes on the map.
		map.clear();
		polylineOptions.width(5);
		polylineOptions.color(Color.RED);
		// setUpEventSpots(list.get(0));
		map.addPolyline(polylineOptions);
		// map.addPolyline(new PolylineOptions().geodesic(true)
		// .add(new LatLng(-33.866, 151.195)) // Sydney
		// .add(new LatLng(-18.142, 178.431)) // Fiji
		// .add(new LatLng(21.291, -157.821)) // Hawaii
		// .add(new LatLng(37.423, -122.091)) // Mountain View
		// );
	}

	public void obtenerCoordenadasActuales() {
		CameraPosition camPos = map.getCameraPosition();

		LatLng coordenadas = camPos.target;
		double latitud = coordenadas.latitude;
		double longitud = coordenadas.longitude;
		float zoom = camPos.zoom;
		float orientacion = camPos.bearing;
		float angulo = camPos.tilt;
	}

	public void mostrarLineas() {
		// Dibujo con Lineas

		PolylineOptions lineas = new PolylineOptions().add(SantaCruz)
				.add(SantaCruz1).add(SantaCruz2).add(SantaCruz3).add(SantaCruz);
		lineas.width(8);
		lineas.color(Color.RED);

		map.addPolyline(lineas);
	}

	// ////////////////
	public Marker placeMarker(EventInfo eventInfo) {
		Marker m = map.addMarker(new MarkerOptions().position(
				eventInfo.getLatLong()).title(eventInfo.getName()));

		return m;
	}

	// private MainMapFragement mapFragment;
	private HashMap<Marker, EventInfo> eventMarkerMap;

	// metodo para dibujar marcadores con layout mi estilo
	public void setUpEventSpots(LatLng SantaCruz) {

		EventInfo firstEventInfo = new EventInfo(SantaCruz,
				"Zoom - Bolivia - By Adan", new Date(), "Fiestas - Eventos");

		Marker firstMarker = placeMarker(firstEventInfo);

		eventMarkerMap = new HashMap<Marker, EventInfo>();
		eventMarkerMap.put(firstMarker, firstEventInfo);

		// add the onClickInfoWindowListener
		map.setOnInfoWindowClickListener(this);

		// map.setInfoWindowAdapter(new Marcador(rootView.getContext()));
		map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

			LayoutInflater li = LayoutInflater.from(rootView.getContext());
			private final View window = li.inflate(R.layout.marcador, null);

			@Override
			public View getInfoWindow(Marker marker) {
				EventInfo eventInfo = eventMarkerMap.get(marker);

				String title = marker.getTitle();
				TextView txtTitle = ((TextView) window
						.findViewById(R.id.txtInfoWindowTitle));
				if (eventInfo != null) {
					if (title != null) {
						// Spannable string allows us to edit the formatting
						SpannableString titleText = new SpannableString(title);
						titleText.setSpan(new ForegroundColorSpan(Color.RED),
								0, titleText.length(), 0);
						txtTitle.setText(titleText);
					} else {
						txtTitle.setText("");
					}

					TextView txtType = ((TextView) window
							.findViewById(R.id.txtInfoWindowEventType));
					if (eventInfo.getType() != null)
						txtType.setText(eventInfo.getType());

				}

				return window;
			}

			@Override
			public View getInfoContents(Marker marker) {
				// this method is not called if getInfoWindow(Marker)
				// does not return null
				return null;
			}
		});

	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		if (eventMarkerMap.containsKey(marker)) {
			EventInfo eventInfo = eventMarkerMap.get(marker);
			Toast.makeText(
					rootView.getContext(),
					"The date of " + eventInfo.getName() + " is "
							+ eventInfo.getSomeDate().toLocaleString(),
					Toast.LENGTH_LONG).show();
		} else {
			System.out.println("entro click event marcador");
		}
	}

}
