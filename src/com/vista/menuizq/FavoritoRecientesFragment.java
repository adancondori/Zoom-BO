package com.vista.menuizq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.vista.adapter.AdapterPerfil;
import com.vista.menu.NavDrawerItem;
import com.vista.zoonv1.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class FavoritoRecientesFragment extends Fragment {
	LayoutInflater inflater;
	View rootView;
	// RelativeLayout item;
	ListView mDrawerList;
	AdapterPerfil drawerListAdapter;

	public FavoritoRecientesFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.inflater = inflater;
		this.rootView = this.inflater.inflate(R.layout.fragment_recientes,
				container, false);
		mDrawerList = (ListView) rootView.findViewById(R.id.listview_reciente);
		drawerListAdapter = new AdapterPerfil(rootView.getContext());
		menuslidingInfo();
		mDrawerList.setAdapter(drawerListAdapter);

		return rootView;
	}

	// ////////////////////////////////////
	private String[] navMenuTitles;
	private TypedArray listevent;

	public void menuslidingInfo() {
		navMenuTitles = getResources().getStringArray(R.array.event_items);
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
			case AdapterPerfil.TYPE_SEPARATOR:
				drawerListAdapter.addSeparatorItem(new NavDrawerItem(aux[1],
						listevent.getResourceId(i, -1)),
						AdapterPerfil.TYPE_SEPARATOR);
				break;
			case AdapterPerfil.TYPE_COPIRIGHT:
				NavDrawerItem item = new NavDrawerItem(aux[1] + ":" + aux[2],
						listevent.getResourceId(i, -1));
				drawerListAdapter.addSeparatorItem(item,
						AdapterPerfil.TYPE_COPIRIGHT);
				break;
			}
		}
	}
}
