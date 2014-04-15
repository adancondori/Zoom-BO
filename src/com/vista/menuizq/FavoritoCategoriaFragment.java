package com.vista.menuizq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vista.zoonv1.R;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class FavoritoCategoriaFragment extends Fragment {
	ExpandableListView elv;
	ArrayList<ArrayList<HashMap<String, String>>> result = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_favorito, null);
		elv = (ExpandableListView) v.findViewById(R.id.expandableListView1);
		cargarExpandableList();
		return v;
	}

	public void cargarExpandableList() {

		SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(
				FavoritoCategoriaFragment.this.getActivity(), CrearGrupos(), // groupData
																				// describes
																				// the
																				// first-level
				// entries
				R.layout.grupo, // Layout for the first-level entries
				new String[] { "Grupo" }, // Key in the groupData maps to
											// display
				new int[] { R.id.paternname }, // Data under "colorName" key
												// goes
												// into this TextView
				CrearHijos(), // childData describes second-level entries
				R.layout.grupohijo_check, // Layout for second-level entries
				new String[] { "hijo" }, // Keys in childData maps to display
				new int[] { R.id.childname } // Data under the keys above go
												// into these TextViews
		);

		elv.setAdapter(expListAdapter);
	}

	private String[] groups = { "People Names", "Prueba Dog Names",
			"Prueba Cat Names", "Fish Names" };

	private String[][] children = { { "Adan", "Juan", "Chuck", "David" },
			{ "Ace", "Bandit", "Cha-Cha", "Deuce" }, { "Fluffy", "Snuggles" },
			{ "Goldy", "Bubbles" } };

	private List<HashMap<String, String>> CrearGrupos() {

		// ListGruposClient = new ArrayList<String>();
		// ListCliente = cliente.getTodo(getApplicationContext());
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> m = null;
		for (int i = 0; i < groups.length; ++i) {
			m = new HashMap<String, String>();
			m.put("Grupo", groups[i]);
			// ListGruposClient.add(ListCliente.get(i).getNombre());
			result.add(m);
		}
		return result;
	}

	private List<ArrayList<HashMap<String, String>>> CrearHijos() {

		result = new ArrayList<ArrayList<HashMap<String, String>>>();
		for (int i = 0; i < groups.length; ++i) {

			ArrayList<HashMap<String, String>> secList = new ArrayList<HashMap<String, String>>();
			// List<Negocio> listhijo = null;
			// listhijo = cliente.getTodoNegocio(this,
			// String.valueOf(ListCliente.get(i).getCodcl()));

			for (int n = 0; n < children[i].length; ++n) {
				HashMap<String, String> child = new HashMap<String, String>();
				child.put("hijo", children[i][n]);
				secList.add(child);
			}
			result.add(secList);
		}
		return result;
	}

}
