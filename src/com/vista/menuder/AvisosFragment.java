package com.vista.menuder;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import com.vista.adapter.AdapterAviso;
import com.vista.adapter.AdapterPerfil;
import com.vista.menu.NavDrawerItem;
import com.vista.zoonv1.R;

import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class AvisosFragment extends ListFragment {
	// *------Variales-------------------------------
	private PullToRefreshListView mDrawerList;
	private String[] navMenuTitles;
	private TypedArray listevent;
	LayoutInflater inflater;
	AdapterAviso drawerListAdapter;

	// *--------sitema-----------------------------
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.inflater = inflater;

		View v = inflater.inflate(R.layout.list_con_margin, container, false);

		mDrawerList = (PullToRefreshListView) v
				.findViewById(R.id.pull_refresh_list);
		drawerListAdapter = new AdapterAviso(getActivity());
		menuslidingInfo();
		mDrawerList.setAdapter(drawerListAdapter);

		// *--Set a listener to be invoked when the list should be refreshed.
		mDrawerList.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getActivity(),
						System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME
								| DateUtils.FORMAT_SHOW_DATE
								| DateUtils.FORMAT_ABBREV_ALL);

				// *---Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		// Add an end-of-list listener
		mDrawerList
				.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

					@Override
					public void onLastItemVisible() {
						Toast.makeText(getActivity(), "End of List!",
								Toast.LENGTH_SHORT).show();
					}
				});

		ListView actualListView = mDrawerList.getRefreshableView();
		registerForContextMenu(actualListView);
		actualListView.setAdapter(drawerListAdapter);
		return v;
	}

	// *--------------- METODOS --------------------------------
	public void menuslidingInfo() {
		navMenuTitles = getResources().getStringArray(R.array.aviso_items);
		listevent = getResources().obtainTypedArray(R.array.oferta_icons);

		for (int i = 0; i < navMenuTitles.length; i++) {
			String aux[] = navMenuTitles[i].split(":");
			if ((i % 2) == 0) {
				drawerListAdapter.addSeparatorItem(new NavDrawerItem(aux[1],
						listevent.getResourceId(i, -1)),
						AdapterPerfil.TYPE_ITEM);
			} else {
				drawerListAdapter.addSeparatorItem(new NavDrawerItem(aux[1],
						listevent.getResourceId(i, -1)),
						AdapterPerfil.TYPE_PERFIL);
			}
		}
		drawerListAdapter.addSeparatorItem(
				new NavDrawerItem("", listevent.getResourceId(20, -1)),
				AdapterPerfil.TYPE_SEPARATOR);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		selectItem(position);
	}

	private void selectItem(int position) {
		Toast.makeText(getActivity(), "press " + position, Toast.LENGTH_SHORT)
				.show();
	}

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
			}
			return navMenuTitles;
		}

		@Override
		protected void onPostExecute(String[] result) {
			// mListItems.addFirst("Added after refresh...");
			drawerListAdapter.eliminar_primero();
			if (drawerListAdapter.getCount() % 2 == 0) {
				drawerListAdapter.addSeparatorItem(
						new NavDrawerItem("Hola como estas "
								+ String.valueOf(drawerListAdapter.getCount()),
								0), AdapterPerfil.TYPE_ITEM);
			} else {
				drawerListAdapter.addSeparatorItem(
						new NavDrawerItem("Hola como estas "
								+ String.valueOf(drawerListAdapter.getCount()),
								0), AdapterPerfil.TYPE_PERFIL);
			}
			drawerListAdapter.addSeparatorItem(
					new NavDrawerItem("", listevent.getResourceId(20, -1)),
					AdapterPerfil.TYPE_SEPARATOR);
			drawerListAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mDrawerList.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
	// END libreria Pull -------------------------------
}
