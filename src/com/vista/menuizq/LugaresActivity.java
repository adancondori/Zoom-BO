package com.vista.menuizq;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
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
	private Lugares LUGARES_ELIMINAR;
	public final static int DIR_NUEVO = 10;
	public final static int DIR_MODIFICAR = 11;
	public final static int DIR_VER = 12;

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
			Intent intent = new Intent(getApplicationContext(),
					Map_direciones_Activity.class);
			startActivityForResult(intent, DIR_NUEVO);

			break;
		}
		return true;
	}

	private void IUview() {

		adapter_Lugares = new Adapter_Lugares(getApplicationContext());
		String[] titles = getResources().getStringArray(
				R.array.locations_lugares_titulo);
		String[] title_direccion = getResources().getStringArray(
				R.array.locations_lugares_direccion);
		for (int i = 0; i < titles.length; i++) {
			Lugares lugares = new Lugares(titles[i], title_direccion[i],
					-17.4704965, -63.1122724, 0, 0, Adapter_Lugares.TYPE_ITEM);
			adapter_Lugares.addItem(lugares);
		}
		listView = (ListView) findViewById(R.id.lvItems);
		listView.setAdapter(adapter_Lugares);
		listView.setOnItemClickListener(this);
		lyt_relative = (RelativeLayout) this.findViewById(R.id.rela);
	}

	public void addlugares(Lugares lugares) {
		adapter_Lugares.addItem(lugares);
	}

	public void modificarlugares(Lugares lugares) {
		adapter_Lugares.modificar_lugares(LUGARES_ELIMINAR, lugares);
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
		selection_opcion(position);
	}

	public void selection_opcion(final int pos) {
		LUGARES_ELIMINAR = adapter_Lugares.getItem(pos);

		ListView modeList = new ListView(this);
		ArrayList<String> list = new ArrayList<String>();
		list.add("Ver");
		list.add("Modificar");
		list.add("Eliminar");
		list.add("Compartir");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 builder.setTitle("¿Qué desea hacer?");

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
					Intent intent = new Intent(getApplicationContext(),
							Map_direciones_Activity.class);
					intent.putExtra("LUGARES_VER", LUGARES_ELIMINAR);
					startActivityForResult(intent, DIR_VER);
					break;
				case 1:
					Intent intent_modi = new Intent(getApplicationContext(),
							Map_direciones_Activity.class);
					intent_modi.putExtra("LUGARES_MODIFICAR", LUGARES_ELIMINAR);
					startActivityForResult(intent_modi, DIR_MODIFICAR);
					break;
				case 2:
					dialogo_confirmacion(pos);
					break;
				case 3:
					// dialogo_confirmacion(pos);
					break;
				}

				dialog.dismiss();
				// }
			}
		});
		dialog.show();
	}

	public void dialogo_confirmacion(final int pos) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				LugaresActivity.this);

		// Setting Dialog Title
		alertDialog.setTitle("Desea eliminar direción");

		// Setting Dialog Message
		alertDialog.setMessage(LUGARES_ELIMINAR.getTitulo());

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("SI",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						adapter_Lugares.remover_item_object(LUGARES_ELIMINAR);
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
		Bundle ext = data.getExtras();
		switch (resultCode) {
		case DIR_NUEVO:
			if (ext != null && ext.containsKey("DIR_NUEVO")) {
				LUGARES_ELIMINAR = (Lugares) ext.getSerializable("DIR_NUEVO");
				LUGARES_ELIMINAR.setTipo(Adapter_Lugares.TYPE_ITEM);
				addlugares(LUGARES_ELIMINAR);
			}
			break;
		case DIR_MODIFICAR:
			if (ext != null && ext.containsKey("DIR_MODIFICAR")) {
				LUGARES_ELIMINAR = (Lugares) ext
						.getSerializable("DIR_MODIFICAR");
				LUGARES_ELIMINAR.setTipo(Adapter_Lugares.TYPE_ITEM);
				modificarlugares(LUGARES_ELIMINAR);
			}
			break;
		case DIR_VER:

			break;

		default:
			break;
		}
	}
}
