package com.vista.menuizq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import com.vista.adapter.Adapter_ExpandableList;
import com.vista.zoonv1.R;
import com.vista.zoonv1.BuscarContactoActivity;
import com.vista.zoonv1.SoliucitudesActivity;

import android.os.Bundle;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;

import android.view.View;

import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class ContactosActivity extends SherlockActivity {
//	ArrayList<ArrayList<HashMap<String, String>>> result = null;
	ExpandableListView expandableListView;

	Adapter_ExpandableList listAdapter;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		View v = getLayoutInflater().inflate(R.layout.activity_contactos, null);
		setContentView(v);
		expandableListView = (ExpandableListView) v
				.findViewById(R.id.expandableListView1);
		IU_expandableListView();
		// expandableListView.setOnChildClickListener(new
		// SelectChildExpandable());
		// ////////

	}

	// private void cargarExpandableList() {
	//
	// SimpleExpandableListAdapter expListAdapter = new
	// SimpleExpandableListAdapter(
	// this, CrearGrupos(), // groupData describes the first-level
	// // entries
	// R.layout.grupo, // Layout for the first-level entries
	// new String[] { "Grupo" }, // Key in the groupData maps to
	// // display
	// new int[] { R.id.paternname }, // Data under "colorName" key
	// // goes
	// // into this TextView
	// CrearHijos(), // childData describes second-level entries
	// R.layout.grupohijo, // Layout for second-level entries
	// new String[] { "hijo" }, // Keys in childData maps to display
	// new int[] { R.id.childname } // Data under the keys above go
	// // into these TextViews
	// );
	// expandableListView.setAdapter(expListAdapter);
	// }

	// private List<HashMap<String, String>> CrearGrupos() {
	// ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String,
	// String>>();
	//
	// HashMap<String, String> m = null;
	// for (int i = 0; i < groups.length; ++i) {
	// m = new HashMap<String, String>();
	// m.put("Grupo", groups[i]);
	// result.add(m);
	// }
	//
	// return result;
	// }

	// private List<ArrayList<HashMap<String, String>>> CrearHijos() {
	//
	// result = new ArrayList<ArrayList<HashMap<String, String>>>();
	// // ControlTarjeta CT = new ControlTarjeta(this);
	// for (int i = 0; i < groups.length; ++i) {
	//
	// ArrayList<HashMap<String, String>> secList = new
	// ArrayList<HashMap<String, String>>();
	//
	// for (int n = 0; n < children[i].length; ++n) {
	// HashMap<String, String> child = new HashMap<String, String>();
	// child.put("hijo", children[i][n]);
	// secList.add(child);
	// }
	// result.add(secList);
	// }
	// return result;
	// }

	// public class SelectChildExpandable implements OnChildClickListener {
	//
	// @Override
	// public boolean onChildClick(ExpandableListView parent, View v,
	// int groupPosition, int childPosition, long id) {
	// String nombre = result.get(groupPosition).get(childPosition)
	// .get("hijo");
	// System.out.println(nombre);
	// return false;
	// }
	//
	// }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case 1: { // Nuevo Grupo solicitudes
			mostrarDialogo();
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void validardatos(View view) {
		mostrarDialogo();
	}

	public void crearsolicitud(View view) {
		Intent intent = new Intent(getApplicationContext(),
				SoliucitudesActivity.class);
		startActivity(intent);
	}

	public void crearcontactos(View view) {
		Intent intent = new Intent(getApplicationContext(),
				BuscarContactoActivity.class);
		startActivity(intent);
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
						actualizar(userInput.getText().toString());
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub

		if (resultCode == RESULT_OK) {
			// cargarExpandableList();
		}

	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			setResult(RESULT_OK, getIntent());
			finish();
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		setResult(RESULT_OK, getIntent());
		finish();
	}

	//
	public void IU_expandableListView() {
		prepareListData();
		listAdapter = new Adapter_ExpandableList(this, listDataHeader,
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
								getApplicationContext(),
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
								getApplicationContext(),
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
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " : " + cad,
						Toast.LENGTH_SHORT).show();
				Bundle bundle = new Bundle();
				bundle.putString("dato", cad);
				Intent intent = new Intent(getApplicationContext(),
						DetalleContactoActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
				return false;
			}
		});
	}

	public void actualizar(String cad) {
		listDataHeader.add(cad);
		ArrayList<String> list = new ArrayList<String>();
		list.add("vacio");
		listDataChild.put(listDataHeader.get(listDataHeader.size() - 1), list);

		listAdapter = new Adapter_ExpandableList(this, listDataHeader,
				listDataChild);
		expandableListView.setAdapter(listAdapter);
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

		// Adding child data
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
}
