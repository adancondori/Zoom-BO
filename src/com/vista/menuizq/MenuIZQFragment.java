package com.vista.menuizq;

import com.vista.adapter.AdapterPerfil;
import com.vista.menu.NavDrawerItem;
import com.vista.zoonv1.FaceActivity;
import com.vista.zoonv1.R;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MenuIZQFragment extends ListFragment {
	public AdapterPerfil drawerListAdapter;
	public static final int SELECT_MENU_DER = 1;
	ListView listView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.list, container, false);
		listView = (ListView) v.findViewById(android.R.id.list);
		// listView.setOnClickListener(new );
		return v;
		// return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		drawerListAdapter = new AdapterPerfil(getActivity());
		menuslidingInfo();
		setListAdapter(drawerListAdapter);
	}

	// ////////////////////////////////////
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	public void menuslidingInfo() {
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);
		for (int i = 0; i < navMenuTitles.length; i++) {
			String aux[] = navMenuTitles[i].split(":");
			switch (Integer.valueOf(aux[0])) {
			case AdapterPerfil.TYPE_PERFIL:
				drawerListAdapter.addSeparatorItem(new NavDrawerItem(aux[1],
						navMenuIcons.getResourceId(i, -1)),
						AdapterPerfil.TYPE_PERFIL);
				break;
			case AdapterPerfil.TYPE_ITEM:
				drawerListAdapter.addItem(new NavDrawerItem(aux[1],
						navMenuIcons.getResourceId(i, -1)));
				break;
			case AdapterPerfil.TYPE_SEPARATOR:
				drawerListAdapter.addSeparatorItem(new NavDrawerItem(aux[1],
						navMenuIcons.getResourceId(i, -1)),
						AdapterPerfil.TYPE_SEPARATOR);
				break;
			case AdapterPerfil.TYPE_COPIRIGHT:
				NavDrawerItem item = new NavDrawerItem(aux[1],
						navMenuIcons.getResourceId(i, -1));
				item.setTipo(AdapterPerfil.TYPE_COPIRIGHT);
				drawerListAdapter.addSeparatorItem(item,
						AdapterPerfil.TYPE_COPIRIGHT);
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView
	 * , android.view.View, int, long)
	 */
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		selectItem(position);
	}

	private void selectItem(int position) {
		Fragment fragment = null;
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(getActivity(), FaceActivity.class);
			break;
		case 2:
			intent = new Intent(getActivity(), PerfilActivity.class);
			break;
		case 3:
			intent = new Intent(getActivity(), LugaresActivity.class);

			break;
		case 4:
			intent = new Intent(getActivity(), FavoritosActivity.class);
			break;
		case 5:
			intent = new Intent(getActivity(), ContactosActivity.class);
			break;
		case 6:
			intent = new Intent(getActivity(), EventosActivity.class);
			break;
		case 7:
			intent = new Intent(getActivity(), PreferenciasActivity.class);
			break;
		case 11:
			intent = new Intent(getActivity(), ServicioAyudaActivity.class);
			break;
		case 12:
			intent = new Intent(getActivity(), AcercaZoomActivity.class);
			break;

		default:

			// SELECT = SELECT_MENU_DER;
			break;
		}
		if (intent != null) {
			startActivityForResult(intent, SELECT_MENU_DER);
		}

	}
}
