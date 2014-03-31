package com.vista.menuizq;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
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
import com.vista.zoonv1.Map_direciones_Activity;
import com.vista.zoonv1.R;

public class LugaresActivity extends SherlockFragmentActivity implements
		OnItemClickListener {

	private Adapter_Lugares adapter_Lugares;
	private ListView listView;
	private RelativeLayout lyt_relative;
	public LugaresFragmentComent lugaresFragmentComent;

	// private GoogleMap googleMap;

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
		case R.id.menu_categoria:
			// dialog_direccion();
			Intent intent = new Intent(getApplicationContext(),
					Map_direciones_Activity.class);
			startActivity(intent);
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		selectionsexo();
	}

	public void dialog_direccion() {
		// get prompts.xml view
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li
				.inflate(R.layout.layout_agregar_direcciones, null);
		// initilizeMap(promptsView);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);

		// final TextView etiqueta = (TextView) promptsView
		// .findViewById(R.id.txt_etiqueta);
		// final TextView nombre = (TextView) promptsView
		// .findViewById(R.id.txt_nombre);

		// Fragment content_frame = new MapFragment();
		// FragmentManager fm = getSupportFragmentManager();
		// FragmentTransaction ft = fm.beginTransaction();
		// ft.replace(R.id.content_frame_map, content_frame);
		// ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		// ft.commit();
		// set dialog message

		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("Aceptar",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// ///////////
								Toast.makeText(getApplicationContext(),
										"colocar mas datos", Toast.LENGTH_SHORT)
										.show();
								dialog.dismiss();
							}
						})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	public void selectionsexo() {
		ListView modeList = new ListView(this);
		ArrayList<String> list = new ArrayList<String>();
		list.add("Ver");
		list.add("Modificar");
		list.add("Compartir");
		list.add("Eliminar");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle("Definir Foto de Perfil");

		// ListView modeList = new ListView(context);
		modeList.setBackgroundColor(Color.WHITE);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, list);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();
		// dialog.setTitle("Sexo (Género)");
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// if (position != 0) {
				switch (position) {
				case 0:
					// sexo.setText("-");
					break;
				case 1:
					// sexo.setText("Hombre");
					break;
				case 2:
					// sexo.setText("Mujer");
					break;
				case 3:
					// sexo.setText("Mujer");
					break;
				}

				dialog.dismiss();
				// }
			}
		});
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu subMenu = menu.addSubMenu(Menu.NONE, R.id.menu_categoria,
				Menu.NONE, "");
		subMenu.setIcon(R.drawable.more50);
		MenuItem subMenu1Item = subMenu.getItem();
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		System.out.println("adan onresulto");
		if (resultCode == RESULT_OK) {
			Bundle ext = data.getExtras();
			String nombre = ext.getString("nombre");
			String etiqueta = ext.getString("etiqueta");
			addlugares(nombre);
		} else if (resultCode == 3) {

		}
	}
}
