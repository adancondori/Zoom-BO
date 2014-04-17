package com.vista.zoonv1;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.adancc.panelupsliding.SlidingLayoutUpPanel;
import com.android.adancc.panelupsliding.SlidingLayoutUpPanel.PanelSlideListener;

import com.google.android.gms.internal.ay;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.tabs.adancc.desplazarview.TabsActivity;
import com.vista.GPSsingleton.GPS;
import com.vista.GPSsingleton.Pedido_Singleton;
import com.vista.adapter.Adapter_ExpandableList_static;
import com.vista.json.ParserFunction;
import com.vista.json.Protocolo_Comunicacion;
import com.vista.menuder.MenuDERFragment;
import com.vista.menuder.MyPagerAdapter;
import com.vista.menuizq.DetalleContactoActivity;
import com.vista.negocio.GoogleMapas;
import com.vista.util.AlertDialogManager_Progres;
import com.vista.util.MyCompassView;
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
import android.util.Log;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

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
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

@SuppressLint("NewApi")
public class HomeFragment extends Fragment {
	LayoutInflater inflater;
	View rootView;
	// View viewcompas;
	// LinearLayout layoutAnimadotoolhijo;
	// RelativeLayout layoutAnimadotool; 
	LinearLayout layoutAnimado;
	HorizontalScrollView scrollView;
	GoogleMapas mapas;
	GoogleMap map;
	LatLng SantaCruz1 = new LatLng(-17.4704965, -63.1122724);
	boolean visible = false;
	LatLng latLngCurrent;
	boolean visiblesearch = true;
	// ExpandableListView imglocation typemap _search imgspeed
	ExpandableListView expandableListView;

	Adapter_ExpandableList_static listAdapter;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	public final static int _BARRA = 1;
	public final static int _BARRA_ICONOS = 2;
	public final static int _UN_CUARTOS = 3;
	public final static int _MEDIO = 4;
	public final static int _FULL = 5;
	public int _TIPO_sliding = -1;
	public int _TIPO_expandablelist = -1;

	public boolean _ISCollapce_Animation = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.inflater = inflater;
		this.rootView = this.inflater.inflate(R.layout.fragment_home,

		container, false);
		// imgaltitude imglocation
		clickmiubicacion();

		inicializarcomponentes();
		initilizeMap();

		mapas = new GoogleMapas(rootView, this.map);

		changezoom();

		SlidinUP();
		// ExpandableListView categorias
		expandableListView = (ExpandableListView) rootView
				.findViewById(R.id._categoria_expandableListV);
		IU_expandableListView();
		// crearSearch();
		// *-----------------------
		compassView = new MyCompassView(rootView.getContext());
		// viewcompas =(View) rootView.findViewById(R.id.view_compas);
		// viewcompas=compassView;

		sensorService = (SensorManager) getActivity().getSystemService(
				Context.SENSOR_SERVICE);
		sensor = sensorService.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (sensor != null) {
			sensorService.registerListener(mySensorEventListener, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
			Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");

		} else {
			Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
			Toast.makeText(getActivity(), "ORIENTATION Sensor not found",
					Toast.LENGTH_LONG).show();
			// finish();
		}
		return rootView;
	}

	public void change_size() {
		int tamaninio = 0;
		DisplayMetrics metrics = new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		switch (_TIPO_expandablelist) {
		case _FULL:
			float ht_px = Utils.converNro_to_dpi(150);
			TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150,
					getResources().getDisplayMetrics());
			tamaninio = (int) ((float) metrics.heightPixels - ht_px);
			break;
		case _MEDIO:
			float ht_pm = TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 25, getResources()
							.getDisplayMetrics());
			tamaninio = (metrics.heightPixels / 3) - (int) ht_pm;
			break;
		case _UN_CUARTOS:
			float ht_pquarto = TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 50, getResources()
							.getDisplayMetrics());
			tamaninio = (metrics.heightPixels / 5) - (int) ht_pquarto;
			break;

		default:
			break;
		}
		ViewGroup.LayoutParams params = expandableListView.getLayoutParams();
		params.height = tamaninio;
		expandableListView.setLayoutParams(params);
		expandableListView.requestLayout();
	}

	private void initilizeMap() {

		if (map == null) {
			map = ((SupportMapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();

			if (map == null) {
				System.out.println("Homefragment::   Null");
				Toast.makeText(inflater.getContext(),
						"Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			} else {
				LatLng SantaCruz = new LatLng(-17.7811645,-63.1862261);
				CameraPosition camPos = new CameraPosition.Builder()
						.target(SantaCruz).zoom(18).bearing(45).build();

				CameraUpdate camUpd3 = CameraUpdateFactory
						.newCameraPosition(camPos);
				map.animateCamera(camUpd3);
				map.setMyLocationEnabled(true);
				map.setOnMyLocationChangeListener(new Myubication());
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

	// ------- Fragment _TabsCateborias;
	// private TabsActivity _tabs;
	// private ViewPager _pager;
	// private MyPagerAdapter _adapter;

	// public void __start_TabsCateborias() {
	// _tabs = (TabsActivity) rootView.findViewById(R.id.tabs_abajo);
	// _pager = (ViewPager) rootView.findViewById(R.id.pager_abajo);
	// _adapter = new com.vista.menuder.MyPagerAdapter(getFragmentManager());
	//
	// _pager.setAdapter(_adapter);
	// final int pageMargin = (int) TypedValue.applyDimension(
	// TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
	// .getDisplayMetrics());
	// _pager.setPageMargin(pageMargin);
	// _tabs.setViewPager(_pager);
	// }

	// ///////////////////////////////////
	// public void visibleproiedades() {
	// if (visible) {
	// ocultartool(); imgspeed
	// } else {
	// mostrartool();
	// }
	// this.visible = !this.visible;
	// }

	// ///////////////////////////////////////////////imghome
	// ImageView imgizq;
	// ImageView imgder;
	ImageView typemap;
	// ImageView flapmap;
	ImageView imgzoom1;
	ImageView imgzoom2;
	ImageView imglocation;
	// ImageView imgtrack;
	// ImageView imgposalt;
	// TextView imgaltitude;
	TextView imgspeed;
	// ImageView home;
	// ImageView faceuploadscreem;
	// ImageView barrio;
	ImageView _img_categoria;
	ImageView _img_expandalble;

	//
	// SearchView searchView;

	// RelativeLayout relativeLayout;

	// private Handler puente = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// try {
	// Thread.sleep(500);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// searchView.setVisibility(View.VISIBLE);
	// }
	// };

	// ---- Este metodo realiza un deslizamiento de der a izq
	// /**
	// *
	// */
	// private void crearSearch() {
	// relativeLayout = (RelativeLayout) this.rootView
	// .findViewById(R.id._search);
	// searchView = new SearchView(getActivity());
	// searchView.onActionViewExpanded();
	// Animation animation2 = AnimationUtils.loadAnimation(getActivity()
	// .getApplicationContext(), R.anim.slidelayout_search_right);
	// relativeLayout.setAnimation(animation2);
	// int searchIconId = searchView.getContext().getResources()
	// .getIdentifier("android:id/search_button", null, null);
	// ImageView searchIcon = (ImageView) searchView
	// .findViewById(searchIconId);
	// searchIcon.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// searchView.onActionViewExpanded();
	// }
	// });
	// searchIcon.setImageResource(R.drawable.buscar);
	//
	// //
	// RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
	// RelativeLayout.LayoutParams.WRAP_CONTENT,
	// RelativeLayout.LayoutParams.WRAP_CONTENT);
	// // params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
	// // RelativeLayout.TRUE);
	// // params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
	// // RelativeLayout.TRUE);
	// // params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	// // params.addRule(RelativeLayout.ALIGN_RIGHT, layoutAnimado.getId());
	// // relativeLayout.setVisibility(View.VISIBLE);
	// searchView.setVisibility(View.VISIBLE);
	//
	// // RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
	// // RelativeLayout.LayoutParams.MATCH_PARENT,
	// // RelativeLayout.LayoutParams.WRAP_CONTENT);
	// // lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
	// // lp.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);
	// // lp.addRule(RelativeLayout.ALIGN_RIGHT, layoutAnimado.getId());
	// // relativeLayout.setLayoutParams(lp);
	// relativeLayout.addView(searchView, params);
	// // relativeLayout.setBackgroundResource(R.color.red);
	// }

	// RelativeLayout _categoria_relative; img_expandalble

	// SlidingDrawer slidingDrawer;
	// ImageView img_handle;

	public void deslizarizquierda() {
		if (0 < scrollView.getScrollX()) {
			scrollView.scrollTo(scrollView.getScrollX(),
					scrollView.getScrollX() - 20);
		}
	}

	public void clickmiubicacion() {
		imglocation = (ImageView) rootView.findViewById(R.id.imglocation);
		imglocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				GPS.getInstance().setCurrentubication(true);
				imglocation.setImageResource(R.drawable.gps);
				if (latLngCurrent != null) {
					map.moveCamera(CameraUpdateFactory.newLatLng(latLngCurrent));
				}
			}
		});
	}

	/**
 * 
 */
	public int idimg = R.drawable.mapas;

	public void inicializarcomponentes() {
		this.layoutAnimado = (LinearLayout) rootView
				.findViewById(R.id._categoria_relative);

		typemap = (ImageView) rootView.findViewById(R.id.typemap);
		typemap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println(typemap.getId() + "  =  " + R.id.typemap);
				if (R.drawable.mapas == idimg) {
					typemap.setImageResource(R.drawable.map);
					idimg = R.drawable.map;
					mapas.type(GoogleMapas.GOOGLE_MAP_TYPE_NORMAL);
				} else {
					typemap.setImageResource(R.drawable.mapas);
					idimg = R.drawable.mapas;
					mapas.type(GoogleMapas.GOOGLE_MAP_TYPE_HYBRID);
				}
				// selectiontipemapa();
			}
		});

		imgzoom1 = (ImageView) rootView.findViewById(R.id.imgzoom1);
		imgzoom1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mapas.zoommas();

			}
		});
		imgzoom2 = (ImageView) rootView.findViewById(R.id.imgzoom2);
		imgzoom2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mapas.zoommenos();
			}
		});

		imgspeed = (TextView) rootView.findViewById(R.id.imgspeed);
		// imgaltitude = (TextView) rootView.findViewById(R.id.imgaltitude);
		// imgaltitude.setVisibility(View.INVISIBLE);
		imgspeed.setVisibility(View.INVISIBLE);

		_img_categoria = (ImageView) rootView
				.findViewById(R.id._img_categorias);
		_img_categoria.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				layoutSlidingLayoutUpPanel.expandPane();
				// layoutSlidingLayoutUpPanel.showPane();
				// layoutSlidingLayoutUpPanel.collapsePane();
				System.out.println("layoutSlidingLayoutUpPanel ");
				// panelSlideListener.onPanelExpanded(panel)
			}
		});
		// LayoutParams layoutParams = slidingDrawer.getLayoutParams();
		// LayoutParams layoutParams_new = new LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		// layoutParams_new.//setMargins(5, 20, 5, 3);
		// faceuploadscreem = (ImageView) rootView
		// .findViewById(R.id.faceuploadscreem);
		// faceuploadscreem.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		//
		// map.snapshot(new GoogleMap.SnapshotReadyCallback() {
		// public void onSnapshotReady(Bitmap bitmap) {
		// // ((MainActivity) Pedido_Singleton.getInstance()
		// // .getActivity()).storyfhoto(bitmap);
		// }
		// });
		// }
		//
		// });
		_img_categoria = (ImageView) rootView
				.findViewById(R.id.img_expandalble);

		// searchView = (SearchView) rootView.findViewById(R.id.searchView1);
		// int searchIconId = searchView.getContext().getResources()
		// .getIdentifier("android:id/search_button", null, null);
		// ImageView searchIcon = (ImageView) searchView
		// .findViewById(searchIconId);
		// searchIcon.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// searchView.onActionViewExpanded();
		// }
		// });
		// searchIcon.setImageResource(R.drawable.buscar);
		// searchView.setQueryHint("Buscar en");
		//
		// if (suggestionsAdapter == null) {
		// MatrixCursor cursor = new MatrixCursor(COLUMNS);
		// cursor.addRow(new String[] { "1", "Ramada" });
		// cursor.addRow(new String[] { "2", "Pozos" });
		// cursor.addRow(new String[] { "3", "Los Lotes" });
		// cursor.addRow(new String[] { "4", "Pampa de la isla" });
		// cursor.addRow(new String[] { "5", "Plan 3000" });
		// cursor.addRow(new String[] { "6", "Abasto" });
		// cursor.addRow(new String[] { "7", "Parque Urbano" });
		// cursor.addRow(new String[] { "8", "Micros" });
		// cursor.addRow(new String[] { "9", "Cementerio General" });
		// cursor.addRow(new String[] { "10", "Hot Bueguer" });
		// cursor.addRow(new String[] { "11", "Cine center" });
		// suggestionsAdapter = new SuggestionsAdapter(rootView.getContext(),
		// cursor);
		// }
		// searchView.setSuggestionsAdapter(suggestionsAdapter);
		// searchView.setVisibility(View.INVISIBLE);

		// barrio.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // img_desactivar();
		//
		// }
		// });
		// more = (ImageView) rootView.findViewById(R.id.more);
		// more.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// ((MainActivity) Pedido_Singleton.getInstance().getActivity())
		// .__showMenuDerecho_More(MainActivity.morecategoria);
		// }
		// });
	}

	/**
	 * Para Buscar Categorias
	 * 
	 * @param v
	 */
	// /////////
	private static final String[] COLUMNS = { BaseColumns._ID,
			SearchManager.SUGGEST_COLUMN_TEXT_1, };
	private SuggestionsAdapter suggestionsAdapter;

	public class SuggestionsAdapter extends android.widget.CursorAdapter {

		public SuggestionsAdapter(Context context, Cursor c) {
			super(context, c, 0);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
			return v;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv = (TextView) view;
			final int textIndex = cursor
					.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
			tv.setText(cursor.getString(textIndex));
		}
	}

	// public void __visible(boolean b) {
	// if (b) {
	// _categoria_relative.setVisibility(View.VISIBLE);
	// _tabs.setVisibility(View.VISIBLE);
	// _pager.setVisibility(View.VISIBLE);
	// } else {
	// _categoria_relative.setVisibility(View.INVISIBLE);
	// _tabs.setVisibility(View.INVISIBLE);
	// _pager.setVisibility(View.INVISIBLE);
	// }
	// }

	public static void setGrayScale(ImageView v) {
		ColorMatrix matrix = new ColorMatrix();
		matrix.setSaturation(0); // 0 means grayscale
		ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
		v.setColorFilter(cf);
	}

	// public void img_visible() {
	// if (visiblesearch) {
	// img_activar();
	// } else {
	// img_desactivar();
	// }
	// visiblesearch = !visiblesearch;
	// }

	// public void img_desactivar() {
	// imgizq.setEnabled(false); imageView9
	// imgder.setEnabled(false);
	// typemap.setVisibility(View.GONE);
	// flapmap.setVisibility(View.GONE);
	// imgposalt.setVisibility(View.GONE);
	// faceuploadscreem.setVisibility(View.GONE);
	// barrio.setVisibility(View.GONE);
	// other.setVisibility(View.GONE);
	// other.setVisibility(View.INVISIBLE);
	// searchView.setVisibility(View.VISIBLE);
	// searchView.setIconified(false);
	// searchView.searchView();

	// imgzoom1.setEnabled(false);
	// imgzoom2.setEnabled(false);
	// imglocation.setEnabled(false);
	// imgtrack.setEnabled(false);
	// imgaltitude.setEnabled(false);
	// imgspeed.setEnabled(false);
	// home.setEnabled(false);
	// home.setImageResource(R.drawable.gear);
	// }

	public void img_activar() {
		// imgizq.setEnabled(false);
		// imgder.setEnabled(false);
		// typemap.setVisibility(View.VISIBLE); -
		// flapmap.setVisibility(View.VISIBLE);
		// imgposalt.setVisibility(View.VISIBLE);
		// faceuploadscreem.setVisibility(View.VISIBLE);
		// barrio.setVisibility(View.VISIBLE);
		// other.setVisibility(View.VISIBLE);
		// searchView.onActionViewCollapsed();
		// searchView.setVisibility(View.GONE);
		// imgzoom1.setEnabled(false);
		// imgzoom2.setEnabled(false);
		// imglocation.setEnabled(false);
		// imgtrack.setEnabled(false);
		// imgaltitude.setEnabled(false);
		// imgspeed.setEnabled(false);
		// home.setEnabled(false);
		// home.setImageResource(R.drawable.buscar);
	}

	// Ubicacion actual en cada movimiento
	// public boolean sw = false;

	// Thread r = new Thread(new Runnable() {
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// if (sw && (map.getMyLocation().getAltitude() != 0.0)
	// && (map.getMyLocation().getSpeed() != 0.0)) {
	// imgaltitude.setText(String.valueOf(map.getMyLocation()
	// .getAltitude()));
	// imgspeed.setText(String.valueOf(map.getMyLocation().getSpeed()));
	// }
	// // if () {
	// // System.out.println("GPS Atitude:   "
	// // + map.getMyLocation().getAltitude());
	// // System.out.println("GPS: Speed:  "
	// // + map.getMyLocation().getSpeed());
	// // }
	// }
	// });

	public void miubicacion() {
		imglocation.setImageResource(R.drawable.focus);
		GPS.getInstance().setCurrentubication(false);
	}

	// public void track() {
	// if (sw) {
	//
	// map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
	//
	// @Override
	// public void onCameraChange(CameraPosition position) {
	// // TODO Auto-generated method stub
	// CameraPosition camPos = map.getCameraPosition();
	//
	// LatLng coordenadas = camPos.target;
	// // double latitud = coordenadas.latitude;
	// // double longitud = coordenadas.longitude;
	// // float zoom = camPos.zoom;
	// // float orientacion = camPos.bearing;
	// // float angulo = camPos.tilt;
	//
	// LatLng SantaCruz = new LatLng(coordenadas.latitude,
	// coordenadas.longitude);
	// map.moveCamera(CameraUpdateFactory.newLatLngZoom(SantaCruz,
	// GPS.getInstance().getZoomcurrent()));
	// // Remove listener to prevent position reset on camera move.
	// // map.setOnCameraChangeListener(null);
	// }
	// });
	// imgtrack.setBackgroundColor(Color.RED);
	// } else {
	// map.setOnCameraChangeListener(null);
	// imgtrack.setBackgroundColor(0x0000FF00);
	// }
	// sw = !sw;
	//
	// }

	public void changezoom() {
		map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

			@Override
			public void onCameraChange(CameraPosition position) {
				if (position.zoom != GPS.getInstance().getZoomcurrent()) {
					synchronized (map) {
						GPS.getInstance().setZoomcurrent(position.zoom);
					}
				}
			}
		});
	}

	public void selectiontipemapa() {

		ListView modeList = new ListView(rootView.getContext());
		ArrayList<String> list = new ArrayList<String>();
		list.add("Normal");
		list.add("Hibrido");
		list.add("Satelite");
		list.add("Terrain");
		list.add("None");
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(((MainActivity) Pedido_Singleton
						.getInstance().getActivity()),
						R.style.Theme_Sherlock_Light));
		builder.setTitle("Tipos de mapa");

		// modeList.setBackgroundColor(getResources().getColor(R.color.background));
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, list);
		// ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(
		// rootView.getContext(), R.layout.dialog_generico,
		// R.id.textdialog, list);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();

		dialog.show();
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				mapas.type(position);
				dialog.dismiss();
			}
		});

	}

	// para el relative layotu up down
	// public void mostrar_ocultar() {
	// if (layoutAnimado.getVisibility() == View.GONE) {
	// // animar(true);
	// // layoutAnimado.setVisibility(View.VISIBLE);
	// } else if (layoutAnimado.getVisibility() == View.VISIBLE) {
	// // animar(false);
	// // layoutAnimado.setVisibility(View.GONE);
	// }
	// }

	// public void mostrar() {
	// if (layoutAnimado.getVisibility() == View.GONE) {
	// animar(true);
	// layoutAnimado.setVisibility(View.VISIBLE);
	// }
	// }

	// public void ocultar() {
	// if (layoutAnimado.getVisibility() == View.VISIBLE) {
	// animar(false);
	// layoutAnimado.setVisibility(View.GONE);
	// }
	// }

	private void animar() {
		change_size();
		Animation animation = null;
		switch (_TIPO_sliding) {
		case _BARRA:
			if (_ISCollapce_Animation) {
				// Animation animation2 = AnimationUtils.loadAnimation(
				// getActivity().getApplicationContext(),
				// R.anim.slidelayout_search_right);
				// relativeLayout.setAnimation(animation2);
				// searchView.setVisibility(View.INVISIBLE);
				// searchView.onActionViewCollapsed();
				animation = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slidelayout_right);
				_ISCollapce_Animation = false;
				// searchView.setVisibility(View.INVISIBLE);
			}
			break;
		case _BARRA_ICONOS:
			if (_ISCollapce_Animation) {
				// Animation animation2 = AnimationUtils.loADANIMATION(
				// GETACTIVITY().GETAPPLICATIONCONTEXT(),
				// R.ANIM.SLIDELAYOUT_SEARCH_RIGHT);
				// RELATIVELAYOUT.SETANIMATION(ANIMATION2);
				// searchView.setVisibility(View.INVISIBLE);
				// searchView.onActionViewCollapsed();
				animation = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slidelayout_right);
				_ISCollapce_Animation = false;
				// searchView.setVisibility(View.INVISIBLE);
			}
			break;
		case _UN_CUARTOS:
			// if (!_ISCollapce_Animation) {
			// animation = AnimationUtils.loadAnimation(getActivity()
			// .getApplicationContext(), R.anim.slidelayout_left);
			// _ISCollapce_Animation = true;
			// }
			break;
		case _MEDIO:
			// if (!_ISCollapce_Animation) {
			// animation = AnimationUtils.loadAnimation(getActivity()
			// .getApplicationContext(), R.anim.slidelayout_left);
			// _ISCollapce_Animation = true;
			// }
			break;
		case _FULL:
			if (!_ISCollapce_Animation) {
				animation = AnimationUtils.loadAnimation(getActivity()
						.getApplicationContext(), R.anim.slidelayout_left);
				_ISCollapce_Animation = true;
				// Animation animation2 = AnimationUtils.loadAnimation(
				// getActivity().getApplicationContext(),
				// R.anim.slidelayout_search);

				// searchView.setVisibility(View.VISIBLE);
				// relativeLayout.setAnimation(animation2);
				// puente.sendMessage(new Message());

			}
			break;

		default:
			break;
		}
		if (animation != null) {
			layoutAnimado.startAnimation(animation);
		}
	}

	/*
	 * segundo relative layout trasmicion animacion
	 */
	// public void mostrartool() {
	// if (layoutAnimadotool.getVisibility() == View.GONE) {
	// animartool(true);
	// layoutAnimadotool.setVisibility(View.VISIBLE);
	// }
	// }
	//
	// public void ocultartool() {
	// if (layoutAnimadotool.getVisibility() == View.VISIBLE) {
	// animartool(false);
	// layoutAnimadotool.setVisibility(View.GONE);
	// }
	// }

	private void animartool(boolean mostrar) {
		AnimationSet set = new AnimationSet(true);
		Animation animation = null;

		if (mostrar) {
			// desde la esquina inferior derecha a la superior izquierda
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
					0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 1.0f,
					Animation.RELATIVE_TO_SELF, 0.0f);

		} else { // desde la esquina superior izquierda a la esquina inferior
					// derecha
			animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
					0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 0.0f,
					Animation.RELATIVE_TO_SELF, 1.0f);
		}
		// duración en milisegundos
		animation.setDuration(500);
		set.addAnimation(animation);

		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.25f);

		// layoutAnimadotool.setLayoutAnimation(controller);
		// layoutAnimadotool.startAnimation(animation);
	}

	//
	//
	// public void dialog(Context context, String title, String message,
	// Boolean status) {
	// final AlertDialog alertDialog = new AlertDialog.Builder(context)
	// .create();
	//
	// // Setting Dialog Title
	// // alertDialog.setTitle(title);
	//
	// // Setting Dialog Message
	// alertDialog.setMessage(message);
	//
	// if (status != null)
	// // Setting alert dialog icon
	// alertDialog
	// .setIcon((status) ? R.drawable.success : R.drawable.fail);
	//
	// // Setting OK Button
	// alertDialog.setButton("Aceptar", new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int which) {
	// alertDialog.dismiss();
	// MarkerOptions options = new MarkerOptions();
	// options.icon(BitmapDescriptorFactory
	// .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
	//
	// ParserFunction function = new ParserFunction();
	// String cad = String.valueOf(SantaCruz1.latitude) + ":"
	// + String.valueOf(SantaCruz1.longitude);
	// JSONObject jsonObject = null;
	// try {
	// jsonObject = function.getBarrios(cad);
	// String cod = jsonObject.getString("id");
	// String res2 = jsonObject.getString("nom");
	// String res3 = jsonObject.getString("polig");
	// List<LatLng> list = decodePoly(res3);
	// mapas.setUpEventSpots(SantaCruz1);
	// mapas.Polylines(list);
	//
	// } catch (ClientProtocolException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (JSONException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// // function.LoginUser("a", "a");
	// // Thread.sleep(1000 * 5);
	//
	// // puente.sendMessage(new Message());
	// }
	// });
	// // Showing Alert Message
	// alertDialog.show(); imglocation
	// }
	private ProgressDialog dialog;

	private String getBarriosWeb(String latitud_longitud) {

		dialog = AlertDialogManager_Progres.showProgreDialog(Pedido_Singleton
				.getInstance().getActivity(), "Obteniendo Informacion...",
				"Barrios", AlertDialogManager_Progres.STYLE_SPINNER);

		new MiTarea().execute(latitud_longitud);
		return "";
	}

	private class MiTarea extends AsyncTask<String, Integer, String> {

		protected void onPreExecute() {

			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.show(); // Mostramos el diálogo antes de comenza
		}

		protected String doInBackground(String... params) {

			System.out.println("modo " + params[0] + " doInBackground");

			System.out.println("ENTRO modo " + params[0] + " doInBackground");
			String res3 = "";
			try {

				ParserFunction function = new ParserFunction();
				JSONObject jsonObject = function.getBarrios(params[0]);
				if (jsonObject.getString("id") != null) {
					String cod = jsonObject.getString("id");
					String res2 = jsonObject.getString("nom");
					res3 = jsonObject.getString("polig");
				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return res3;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (result != null && !result.equals("") && !result.equals("null")) {
				System.out.println("Entro: " + result);
				List<LatLng> list = decodePoly(result);
				list.add(list.get(0));
				// System.out.println(list);
				mapas.Polylines(list);
			} else {
				Toast.makeText(
						Pedido_Singleton.getInstance().getActivity(),
						"No se tiene informacion de esta zona, o esta muy alejano.",
						Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();

		}
	}

	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	// / escuchador de mapa
	public class Myubication implements OnMyLocationChangeListener {

		@Override
		public void onMyLocationChange(Location location) {
			// Getting longitude of the current location
			double latitude = location.getLatitude();
			double longitude = location.getLongitude();
			// Creating a LatLng object for the current location
			latLngCurrent = new LatLng(latitude, longitude);
			// System.out.println(latLngCurrent);
			if (GPS.getInstance().isCurrentubication()) {
				synchronized (map) {
					map.moveCamera(CameraUpdateFactory.newLatLng(latLngCurrent));
					show_velocidad_altitud();
				}
			} else {
				// imgaltitude.setVisibility(View.INVISIBLE);
				imgspeed.setVisibility(View.INVISIBLE);
			}
		}

	}

	public void show_velocidad_altitud() {

		// imgaltitude.setVisibility(View.VISIBLE);

		if (map != null
		// && ((map.getMyLocation().getAltitude() != 0.0) || (map
		// .getMyLocation().getSpeed() != 0.0))
		) {
			imgspeed.setVisibility(View.VISIBLE);
			// *-- speed
			float d = map.getMyLocation().getSpeed();
			DecimalFormat df = new DecimalFormat("###");
			String res = String.valueOf(df.format((double) (d * 3.6)));
			String speed = "";
			if ((d * 3.6) >= 1) {
				speed = "V= " + res + " km/h" + " · ";
			}
			// *--- altitud
			String altitud = "h= "
					+ String.valueOf((int) map.getMyLocation().getAltitude())
					+ " m";

			// imgaltitude.setText("h= "
			// + String.valueOf((int) map.getMyLocation().getAltitude())
			// + " m");

			// System.out.print(df.format((double) d));
			// imgspeed.setText("V= "
			// + String.valueOf(df.format((double) (d * 3.6))) + " KPH");
			imgspeed.setText(speed + altitud);
		} else {

		}
	}

	public int convertToPixelFromDp(int dpInput) {
		// get the screen's density scale
		final float scale = getResources().getDisplayMetrics().density;
		// convert the dps to pixels, based on density scale
		return (int) (dpInput * scale + 0.5f);
	}

	public void isactivadoteclado() {

		((MainActivity) Pedido_Singleton.getInstance().getActivity()).searchView
				.getViewTreeObserver().addOnGlobalLayoutListener(
						new ViewTreeObserver.OnGlobalLayoutListener() {

							@Override
							public void onGlobalLayout() {
								// TODO Auto-generated method stub
								Rect r = new Rect();
								// r will be populated with the coordinates of
								// your view
								// that areastill visible.
								((MainActivity) Pedido_Singleton.getInstance()
										.getActivity()).searchView
										.getWindowVisibleDisplayFrame(r);

								int heightDiff = ((MainActivity) Pedido_Singleton
										.getInstance().getActivity()).searchView
										.getRootView().getHeight()
										- (r.bottom - r.top);
								if (heightDiff > 100) { // if more than 100
														// pixels, its
														// probably a
									DisplayMetrics metrics = new DisplayMetrics();
									getActivity().getWindowManager()
											.getDefaultDisplay()
											.getMetrics(metrics);
									float ht_px = TypedValue.applyDimension(
											TypedValue.COMPLEX_UNIT_DIP, 60,
											getResources().getDisplayMetrics());
									//
									_pos1_herramienta = (1.0 - (ht_px / metrics.heightPixels));
									System.out.println("HOLA");
								} else {
									DisplayMetrics metrics = new DisplayMetrics();
									getActivity().getWindowManager()
											.getDefaultDisplay()
											.getMetrics(metrics);
									float ht_px = TypedValue.applyDimension(
											TypedValue.COMPLEX_UNIT_DIP, 60,
											getResources().getDisplayMetrics());
									//
									_pos1_herramienta = (1.0 - (ht_px / metrics.heightPixels));
									System.out.println("HOLA  2");
								}

							}
						});

		// InputMethodManager imm = (InputMethodManager) Pedido_Singleton
		// .getInstance().getActivity().getApplicationContext()
		// .getSystemService(Context.INPUT_METHOD_SERVICE);
		// DisplayMetrics metrics = new DisplayMetrics();
		// this.getActivity().getWindowManager().getDefaultDisplay()
		// .getMetrics(metrics);
		// float ht_px = 0.0f;
		// if (!imm.isActive()) {
		//
		// ht_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
		// getResources().getDisplayMetrics());
		// _pos1_herramienta = (1.0 - (ht_px / metrics.heightPixels));
		// layoutSlidingLayoutUpPanel
		// .setAnchorPoint((float) _pos1_herramienta);
		// // _pos1_herramienta = 0.75f;
		// System.out.println(" ---" + 100);
		//
		// } else {
		// System.out.println(" ---" + 60);
		// ht_px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60,
		// getResources().getDisplayMetrics());
		// _pos1_herramienta = (1.0 - (ht_px / metrics.heightPixels));
		// layoutSlidingLayoutUpPanel
		// .setAnchorPoint((float) _pos1_herramienta);
		//
		// }
		// System.out.println("constante= 60" + "  60 en px = " + ht_px
		// + " alto de pant= " + metrics.heightPixels + "  resp= "
		// + _pos1_herramienta);

	}

	public SlidingLayoutUpPanel layoutSlidingLayoutUpPanel;
	public double _pos1_herramienta = -1;
	float _rango = 0.05f;
	public PanelSlideListener panelSlideListener;

	/**
	 * Muestas el Sliding menu
	 * */
	public void SlidinUP() {

		DisplayMetrics metrics = new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);

		float dp = Utils.converNro_to_dpi(60);
		//
		_pos1_herramienta = (1.0 - (dp / metrics.heightPixels));

		// System.out.println(" otropx= " + metrics.heightPixels + "   otrodp= "
		// + convertToPixelFromDp(metrics.heightPixels) + "  otro100=  "
		// + convertToPixelFromDp(100));

		// System.out.println(" 3= " + _pos1_herramienta);
		layoutSlidingLayoutUpPanel = (SlidingLayoutUpPanel) rootView
				.findViewById(R.id.sliding_layout);
		layoutSlidingLayoutUpPanel.setShadowDrawable(getResources()
				.getDrawable(R.drawable.shadow_sliding));
		// isactivadoteclado();
		// .getDrawable(R.drawable.shadow_sliding_principal_up));
		layoutSlidingLayoutUpPanel.setAnchorPoint((float) _pos1_herramienta);

		panelSlideListener = new PanelSlideListener() {

			@Override
			public void onPanelSlide(View panel, float slideOffset) {
				// isactivadoteclado();
				if ((_pos1_herramienta - _rango) < slideOffset
						&& slideOffset < (_pos1_herramienta + _rango)) {

					layoutSlidingLayoutUpPanel
							.setAnchorPoint((float) _pos1_herramienta);
					_TIPO_sliding = _BARRA_ICONOS;
					_TIPO_expandablelist = _BARRA_ICONOS;
					panelSlideListener.onPanelAnchored(panel);

				}
				if ((0.72f - _rango) < slideOffset
						&& slideOffset < (0.72f + _rango)) {
					layoutSlidingLayoutUpPanel.setAnchorPoint(0.72f);
					_TIPO_sliding = _FULL;
					_TIPO_expandablelist = _UN_CUARTOS;
					panelSlideListener.onPanelAnchored(panel);
				}
				if ((0.50f - _rango) < slideOffset
						&& slideOffset < (0.50f + _rango)) {
					layoutSlidingLayoutUpPanel.setAnchorPoint(0.50f);
					_TIPO_sliding = _FULL;
					_TIPO_expandablelist = _MEDIO;
					panelSlideListener.onPanelAnchored(panel);
				}
				// System.out.println(slideOffset);
			}

			@Override
			public void onPanelExpanded(View panel) {
				// Log.i(TAG, "onPanelExpanded"); _categoria_expandableListV
				// System.out.println("onPanelExpanded");
				_TIPO_sliding = _FULL;
				_TIPO_expandablelist = _FULL;
				animar();
				// searchView.onActionViewExpanded();
				// _img_categoria.setImageResource(R.drawable.lengueta_full);
				// crearSearch();
			}

			@Override
			public void onPanelCollapsed(View panel) {

				_TIPO_sliding = _BARRA;
				animar();
				// _img_categoria.setImageResource(R.drawable.lengueta_min);
				// System.out.println("onPanelCollapsed");
			}

			@Override
			public void onPanelAnchored(View panel) {

				animar();
				// _img_categoria.setImageResource(R.drawable.lengueta_med);
				// System.out.println("onPanelAnchored");
			}
		};
		layoutSlidingLayoutUpPanel.setBackgroundColor(Color.TRANSPARENT);
		// layoutSlidingLayoutUpPanel.setCacheColorHint(Color.TRANSPARENT);
		layoutSlidingLayoutUpPanel.setPanelSlideListener(panelSlideListener);
	}

	//
	public void IU_expandableListView() {
		prepareListData();
		listAdapter = new Adapter_ExpandableList_static(getActivity(), listDataHeader,
				listDataChild);
		// setting list adapter
		expandableListView.setAdapter(listAdapter);
		// Listview Group click listener
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		// Listview Group expanded listener
		expandableListView
				.setOnGroupExpandListener(new OnGroupExpandListener() {

					@Override
					public void onGroupExpand(int groupPosition) {

						Toast.makeText(
								getActivity(),
								listDataHeader.get(groupPosition) + " Expanded",
								Toast.LENGTH_SHORT).show();

					}
				});

		// Listview Group collasped listener
		expandableListView
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					@Override
					public void onGroupCollapse(int groupPosition) {
						Toast.makeText(
								getActivity(),
								listDataHeader.get(groupPosition)
										+ " Collapsed", Toast.LENGTH_SHORT)
								.show();
					}
				});

		// Listview on child click listener
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				String cad = listDataChild.get(
						listDataHeader.get(groupPosition)).get(childPosition);
				Toast.makeText(getActivity(),
						listDataHeader.get(groupPosition) + " : " + cad,
						Toast.LENGTH_SHORT).show();
				Bundle bundle = new Bundle();
				bundle.putString("dato", cad);
				Intent intent = new Intent(getActivity(),
						DetalleContactoActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Amigos");
		listDataHeader.add("Casas");
		listDataHeader.add("Otros");

		// Adding child data _categoria_relative
		List<String> top250 = new ArrayList<String>();
		top250.add("Jasmani campos");
		top250.add("Luis Carmona");
		top250.add("Edilberto Mamani");
		top250.add("Jose Luis F");
		top250.add("Ronald Villanueva L");
		top250.add("nombre prueba");
		top250.add("Huna Barba");

		List<String> nowShowing = new ArrayList<String>();
		nowShowing.add("Barracas");
		nowShowing.add("Peluquerias");
		nowShowing.add("Mecanico");

		List<String> comingSoon = new ArrayList<String>();
		comingSoon.add("2 Guns");
		comingSoon.add("The Smurfs 2");
		comingSoon.add("The Spectacular Now");
		comingSoon.add("The Canyons");
		comingSoon.add("Europa Report");

		listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
		listDataChild.put(listDataHeader.get(1), nowShowing);
		listDataChild.put(listDataHeader.get(2), comingSoon);
	}

	// *----------------------------------------------------------------------
	private static SensorManager sensorService;
	private MyCompassView compassView;
	private Sensor sensor;
	private SensorEventListener mySensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			// angle between the magnetic north direction
			// 0=North, 90=East, 180=South, 270=West
			float azimuth = event.values[0];
			compassView.updateData(azimuth);
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (sensor != null) {
			sensorService.unregisterListener(mySensorEventListener);
		}
	}
}
