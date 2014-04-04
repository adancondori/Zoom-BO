package com.vista.zoonv1;

import java.util.Timer;
import java.util.TimerTask;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.view.Window;
import com.actionbarsherlock.widget.SearchView;
import com.actionbarsherlock.widget.SearchView.OnQueryTextListener;
import com.actionbarsherlock.widget.SearchView.OnSuggestionListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.vista.GPSsingleton.GPS;
import com.vista.GPSsingleton.Pedido_Singleton;
import com.vista.menuder.MenuDERFragment;

import com.vista.sliding_lef_right.BaseActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.BaseColumns;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.CursorAdapter;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements OnQueryTextListener,
		OnSuggestionListener {
	// ------Variables--------------------------------------
	public static final int SELECT_MENU_DER = 1;
	private static final long SPLASH_SCREEN_DELAY = 3000;
	Fragment _homefragment = null;
	Fragment _menuDERFragment = null;
	public static boolean morecategoria = false;
	public static boolean menuderecho = true;
	public int _id_panelderecho = 0;

	// --------End Variables -------------------------------

	public MainActivity() {
		super(R.string.app_name);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			// Entra si hay datos nuevos k cargas de una sesion stop
		}
		_homefragment = new HomeFragment();
		_menuDERFragment = new MenuDERFragment();
		overridePendingTransition(R.anim.trans_abajo_hacia_arriba_activity,
				R.anim.trans_no_trasmicion_activity);
		getWindow().requestFeature((int) Window.FEATURE_ACTION_BAR_OVERLAY);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_UNSPECIFIED);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		// BackgroundDrawable();
		setContentView(R.layout.content_frame);

		// singleton guartdar getSupportFragmentManager menuDERFragmenrt
		Pedido_Singleton.getInstance().setManager(getSupportFragmentManager());
		Activity activity = MainActivity.this;
		Pedido_Singleton.getInstance().setActivity(activity);

		//

		// FragmentManager fm0 = getSupportFragmentManager();
		// FragmentTransaction ft0 = fm0.beginTransaction();
		// ft0.replace(R.id.content_frame, _homefragment);
		// ft0.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// ft0.commit();
		inicioFragmentPrincipal();

		// --------------
		getSlidingMenu().setSecondaryMenu(R.layout.menu_frame_two);
		getSlidingMenu().setSecondaryShadowDrawable(
				R.drawable.shadowright_sliding);

		FragmentManager fm1 = getSupportFragmentManager();
		FragmentTransaction ft1 = fm1.beginTransaction();
		ft1.replace(R.id.menu_frame_two, _menuDERFragment);
		ft1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft1.commit();

		// ImageView imgview = (ImageView) findViewById(android.R.id.home);
		// imgview.setPadding(0, 0, 0, 0);
	}

	// --------------Metodos-------------------------------------------
	public void BackgroundDrawable() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(getResources().getColor(
						R.color.background_abajo_rela)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.actionbarsherlock.app.SherlockFragmentActivity#onOptionsItemSelected
	 * (com.actionbarsherlock.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			break;
		case R.id.menu_categoria:
			// if (!searchView.isIconified()) {
			// } else {
			// System.out.println("--- 2");
			// getSlidingMenu().showSecondaryMenu();
			// }
			getSlidingMenu().showSecondaryMenu();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case SELECT_MENU_DER:
			if (resultCode == RESULT_OK) {
				toggle();
			} else if (resultCode == RESULT_CANCELED) {
				toggle();
			}
			break;
		default:
			break;
		}
	}

	//
	public void menuSubMenu(Menu menu) {

		//
		SubMenu subMenu = menu.addSubMenu(Menu.NONE, R.id.menu_categoria,
				Menu.NONE, "");
		subMenu.setIcon(R.drawable.notificaciones_menu_derecho);
		// subMenu.setIcon(R.drawable.ic_launcher);
		// MenuItem.SHOW_AS_ACTION_IF_ROOM |
		MenuItem subMenu1Item = subMenu.getItem();
		// subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
		// | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

	}

	// Touch para poder desplazar el mapa y cambia la imagen my track
	private static final long SCROLL_TIME = 100; // 200 Milliseconds, but you
	private long lastTouched = 0; // can adjust that to your

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastTouched = SystemClock.uptimeMillis();
			break;
		case MotionEvent.ACTION_UP:
			final long now = SystemClock.uptimeMillis();
			if ((now - lastTouched) > SCROLL_TIME) {
				if (_homefragment != null
						&& ((HomeFragment) _homefragment) != null) {
					((HomeFragment) _homefragment).miubicacion();
				}
			}
			lastTouched = 0;
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onKeyDown(int,
	 * android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {
			toggle();
			return true;
		} else if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			salir_de_app();
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	// --------------------------------
	public void salir_de_app() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				MainActivity.this);

		// Setting Dialog Title
		alertDialog.setTitle("Confirma Salir...");

		// Setting Dialog Message
		alertDialog.setMessage("Desea salir de la aplicación?");

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("SI",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						finish();

					}
				});

		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Message
		alertDialog.show();
	}

	// --------------------------------
	// ---------------------------------
	private static final String[] COLUMNS = { BaseColumns._ID,
			SearchManager.SUGGEST_COLUMN_TEXT_1, };

	SearchView searchView;

	// /////////
	private SuggestionsAdapter suggestionsAdapter;

	public class SuggestionsAdapter extends CursorAdapter {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.actionbarsherlock.app.SherlockFragmentActivity#onCreateOptionsMenu
	 * (com.actionbarsherlock.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// Used to put dark icons on light action bar
		boolean isLight = false;// SampleList.THEME ==
								// R.style.Theme_Sherlock_Light;

		// Create the search view
		searchView = new SearchView(getSupportActionBar().getThemedContext());
		searchView.setQueryHint("Buscar en");
		searchView.setOnQueryTextListener(this);
		searchView.setOnSuggestionListener(this);

		if (suggestionsAdapter == null) {
			MatrixCursor cursor = new MatrixCursor(COLUMNS);
			cursor.addRow(new String[] { "1", "Ramada" });
			cursor.addRow(new String[] { "2", "Pozos" });
			cursor.addRow(new String[] { "3", "Parque Urbano" });
			suggestionsAdapter = new SuggestionsAdapter(getSupportActionBar()
					.getThemedContext(), cursor);
		} // else {
		searchView.setSuggestionsAdapter(suggestionsAdapter);
		// }

		menu.add("Search")
				.setIcon(
						isLight ? R.drawable.ic_search_inverse
								: R.drawable.abs__ic_search)
				.setActionView(searchView)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		menuSubMenu(menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		COLOCAR();
		InputMethodManager inputManager = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(this.getCurrentFocus()
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

		((HomeFragment) _homefragment).layoutSlidingLayoutUpPanel
				.expandPane(0.50f);
		((HomeFragment) _homefragment).layoutSlidingLayoutUpPanel
				.expandPane(0.50f);

		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuggestionSelect(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuggestionClick(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	public void COLOCAR() {
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		System.out.println("width " + width + "Y " + height);
	}

	private void inicioFragmentPrincipal() {
		getSupportActionBar().hide();
		Pedido_Singleton.getInstance().setManager(getSupportFragmentManager());

		Fragment splashScreenFragment = new SplashScreenFragment();

		if (splashScreenFragment != null) {

			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(R.id.content_frame, splashScreenFragment);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.commit();

			final FragmentManager fragmentManager111 = getSupportFragmentManager();
			TimerTask timerTask = new TimerTask() {
				@Override
				public void run() {
					_homefragment = new HomeFragment();
					FragmentTransaction ft2 = fragmentManager111
							.beginTransaction();
					ft2.replace(R.id.content_frame, _homefragment);
					ft2.commit();
					Message msg = new Message();
					puente.sendMessage(msg);
				}
			};
			Timer timer = new Timer();
			timer.schedule(timerTask, SPLASH_SCREEN_DELAY);
		}
	}

	// Creado para hacer un puente entre timer y al activity principal ok

	private Handler puente = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			showbar();
		}
	};

	public void showbar() {
		getSupportActionBar().show();
	}

	// --------------End Metodos-------------------------------------------
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		GPS.getInstance().setCurrentubication(false);
		super.onStop();
	}
}
