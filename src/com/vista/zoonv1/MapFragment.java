package com.vista.zoonv1;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.internal.ay;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tabs.adancc.desplazarview.TabsActivity;
import com.vista.GPSsingleton.GPS;
import com.vista.GPSsingleton.Pedido_Singleton;
import com.vista.adapter.Adapter_ExpandableList;
import com.vista.json.ParserFunction;
import com.vista.json.Protocolo_Comunicacion;
import com.vista.menuder.MenuDERFragment;
import com.vista.menuder.MyPagerAdapter;
import com.vista.menuizq.DetalleContactoActivity;
import com.vista.negocio.GoogleMapas;
import com.vista.util.AlertDialogManager_Progres;
import com.vista.util.Utils;

import android.annotation.SuppressLint;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;

import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;

import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

@SuppressLint("NewApi")
public class MapFragment extends Fragment {
	LayoutInflater inflater;
	View rootView;
	GoogleMap map;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.inflater = inflater;
		this.rootView = this.inflater.inflate(R.layout.fragment_map,

		container, false);
		initilizeMap();
		return rootView;
	}

	private void initilizeMap() {

		if (map == null) {
			map = ((SupportMapFragment) getFragmentManager().findFragmentById(
					R.id.map_direcciones)).getMap();

			if (map == null) {
				System.out.println("Mapfragment::   Null");
				Toast.makeText(inflater.getContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
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
