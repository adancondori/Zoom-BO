package com.vista.zoonv1;

import java.security.PublicKey;
import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.vista.adapter.AdapterPerfil;
import com.vista.adapter.Adapter_Generico;
import com.vista.menu.NavDrawerItem;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.StaticLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SoliucitudesActivity extends SherlockActivity {
	// LayoutInflater inflater;
	View rootView;
	// RelativeLayout item;
	ListView mDrawerList;
	Adapter_Generico drawerListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// BackgroundDrawable();
		rootView = getLayoutInflater().inflate(R.layout.activity_soliucitudes,
				null);
		setContentView(rootView);

		menuslidingInfo();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == RESULT_OK) {
			// cargarExpandableList();
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(RESULT_OK, getIntent());
		finish();
	}

	public void BackgroundDrawable() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#99C9CED0")));
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			finish();
			// Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
			break;
		}
		return true;
	}

	// ////////////////////////////////////
	private String[] navMenuTitles;
	private TypedArray listevent;

	public void menuslidingInfo() {

		mDrawerList = (ListView) findViewById(R.id.listview_solicitudes);
		drawerListAdapter = new Adapter_Generico(getApplicationContext());
		navMenuTitles = getResources().getStringArray(
				R.array.entries_list_contacto);
		// nav drawer icons from resources
		listevent = getResources().obtainTypedArray(R.array.event_icons);
		for (int i = 0; i < navMenuTitles.length; i++) {
			String aux[] = navMenuTitles[i].split(":");
			switch (Integer.valueOf(aux[0])) {
			// case AdapterPerfil.TYPE_PERFIL:
			// drawerListAdapter.addSeparatorItem(new Contacto(aux[1],
			// navMenuIcons.getResourceId(i, -1)),
			// AdapterPerfil.TYPE_PERFIL);
			// break;
			case AdapterPerfil.TYPE_ITEM:
				drawerListAdapter.addItem(new NavDrawerItem(aux[1], listevent
						.getResourceId(i, -1)));
				break;
			// case AdapterPerfil.TYPE_SEPARATOR:
			// drawerListAdapter.addSeparatorItem(new Contacto(aux[1],
			// listevent.getResourceId(i, -1)),
			// AdapterPerfil.TYPE_SEPARATOR);
			// break;
			// case AdapterPerfil.TYPE_COPIRIGHT:
			// Contacto item = new Contacto(aux[1] + ":" + aux[2],
			// listevent.getResourceId(i, -1));
			// drawerListAdapter.addSeparatorItem(item,
			// AdapterPerfil.TYPE_COPIRIGHT);
			// break;
			}
		}
		mDrawerList.setAdapter(drawerListAdapter);
		mDrawerList.setOnItemClickListener(new MyOnItemClickListener());
	}

	ArrayList<String> list = new ArrayList<String>();

	public int getSize() {
		return list.size() - 1;
	}

	public void selectiontipemapa() {
		ListView modeList = new ListView(rootView.getContext());
		if (list.size() < 2) {
			list.add("Amigos");
			list.add("Casas");
			list.add("Otros");
			list.add("<<Crear Nuevo Grupo>>");
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(
				rootView.getContext());
		builder.setTitle("Seleccione Categoria");

		modeList.setBackgroundColor(Color.WHITE);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, list);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();

		dialog.show();
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (getSize() == position) {
					mostrarDialogo();
				} else {

				}
				dialog.dismiss();
			}
		});

	}

	private void mostrarDialogo() {
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialogo, null);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setView(promptsView);
		alertDialogBuilder.setTitle("Nueva Categoria");
		alertDialogBuilder.setCancelable(true);
		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.edit_Name);

		alertDialogBuilder
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// actualizar(userInput.getText().toString());
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
		alertDialog.show();

	}

	public class MyOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			selectiontipemapa();
		}
	}
}
