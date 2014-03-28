package com.vista.zoonv1;

import com.vista.adapter.Adaper_More;
import com.vista.menu.NavDrawerItem;
import com.vista.zoonv1.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MoreFragment extends ListFragment {
	public Adaper_More adaper_More;
	public static final int SELECT_MENU_DER = 1;
	ListView listView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.list, container, false);
		adaper_More = new Adaper_More(getActivity());
		adaper_More.addSeparatorItem(new NavDrawerItem("hola", 0),
				Adaper_More.TYPE_PERFIL);
		listView = (ListView) v.findViewById(android.R.id.list);
		return v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(adaper_More);
	}

}
