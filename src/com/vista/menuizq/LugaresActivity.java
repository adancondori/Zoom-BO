package com.vista.menuizq;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.actionbarsherlock.widget.SearchView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import com.vista.adapter.Adapter_Lugares;
import com.vista.dato.Lugares;
import com.vista.util.Utils;
import com.vista.zoonv1.LugaresFragmentComent;
import com.vista.zoonv1.R;
import com.vista.zoonv1.MainActivity.SuggestionsAdapter;

import android.os.Bundle;
import android.view.Display;
//import android.view.View.OnClickListener;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LugaresActivity extends SherlockFragmentActivity implements
		OnItemClickListener {

	private Adapter_Lugares adapter_Lugares;
	private ListView listView;
	private RelativeLayout lyt_relative;
	public LugaresFragmentComent lugaresFragmentComent;

	public Display display = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lugares);
		getsizeScreem();
		IUview();

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

	private void IUview() {

		adapter_Lugares = new Adapter_Lugares(getApplicationContext());
		String[] titles = getResources().getStringArray(R.array.locations);
		for (int i = 0; i < titles.length; i++) {
			adapter_Lugares.addItem(new Lugares(Adapter_Lugares.TYPE_ITEM,
					titles[i], 0, 0));
		}
		listView = (ListView) findViewById(R.id.lvItems);
		listView.setAdapter(adapter_Lugares);
		listView.setOnItemClickListener(this);
		lyt_relative = (RelativeLayout) this.findViewById(R.id.rela);
	}

	public void addlugares(String nombre) {
		adapter_Lugares.addItem(new Lugares(Adapter_Lugares.TYPE_ITEM, nombre,
				0, 0));
	}

	public void agregarMarginlayout(int left, int top, int right, int bottom) {
		RelativeLayout.LayoutParams head_params = (RelativeLayout.LayoutParams) lyt_relative
				.getLayoutParams();
		head_params.setMargins(left, top, right, bottom);
		lyt_relative.setLayoutParams(head_params);
	}

	public void getsizeScreem() {
		display = Utils.getsizeScreem(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menuSubMenu(menu);
		return super.onCreateOptionsMenu(menu);
	}

	public void menuSubMenu(Menu menu) {

		//
		SubMenu subMenu = menu.addSubMenu(Menu.NONE, R.id.menu_categoria,
				Menu.NONE, "");
		subMenu.setIcon(R.drawable.vermas);
		MenuItem subMenu1Item = subMenu.getItem();
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Toast.makeText(
				getApplicationContext(),
				"Item " + position
						+ "   Info Systema: hacer una ventava con el mapa!!! ",
				Toast.LENGTH_SHORT).show();
		
	}
}
