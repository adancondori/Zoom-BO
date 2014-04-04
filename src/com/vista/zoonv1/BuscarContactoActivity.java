package com.vista.zoonv1;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.widget.SearchView;
import com.vista.adapter.AdapterPerfil;
import com.vista.menu.NavDrawerItem;

import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class BuscarContactoActivity extends SherlockActivity implements
		SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

	LayoutInflater inflater;
	View rootView;
	// RelativeLayout item;
	ListView mDrawerList;
	AdapterPerfil drawerListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// BackgroundDrawable();
		rootView = getLayoutInflater().inflate(
				R.layout.activity_buscar_contacto, null);
		setContentView(rootView);
		drawerListAdapter = new AdapterPerfil(rootView.getContext());
		menuslidingInfo();
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
			// onCreateOptionsMenu
			break;
		}

		return true;
	}

	// Search busqueda start
	private static final String[] COLUMNS = { BaseColumns._ID,
			SearchManager.SUGGEST_COLUMN_TEXT_1, };

	private SuggestionsAdapter mSuggestionsAdapter;
	SearchView searchView;

	@Override
	public boolean onSuggestionSelect(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuggestionClick(int position) {
		Cursor c = (Cursor) mSuggestionsAdapter.getItem(position);
		String query = c.getString(c
				.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1));
		searchView.setVisibility(View.INVISIBLE);
		searchView.setVisibility(View.VISIBLE);
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String query) {
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Used to put dark icons on light action bar
		boolean isLight = false;
		// SampleList.THEME ==R.style.Theme_Sherlock_Light;

		// Create the search view
		searchView = new SearchView(getSupportActionBar().getThemedContext());
		searchView.setQueryHint("Buscar en");
		searchView.setOnQueryTextListener(this);
		searchView.setOnSuggestionListener(this);

		if (mSuggestionsAdapter == null) {
			MatrixCursor cursor = new MatrixCursor(COLUMNS);
			cursor.addRow(new String[] { "1", "Adan Condori C." });
			cursor.addRow(new String[] { "2", "Ronald L Villanueva" });
			cursor.addRow(new String[] { "3", "Jasmani Campos" });
			mSuggestionsAdapter = new SuggestionsAdapter(getSupportActionBar()
					.getThemedContext(), cursor);
		}

		searchView.setSuggestionsAdapter(mSuggestionsAdapter);

		menu.add("Search")
				.setIcon(
						isLight ? R.drawable.ic_search_inverse
								: R.drawable.abs__ic_search)
				.setActionView(searchView)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		return super.onCreateOptionsMenu(menu);
	}

	private class SuggestionsAdapter extends CursorAdapter {

		public SuggestionsAdapter(Context context, Cursor c) {
			super(context, c, 0);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
			return v;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv = (TextView) view;
			final int textIndex = cursor
					.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
			tv.setText(cursor.getString(textIndex));
		}
	}

	// Search busqueda end
	// adapter list
	// --------adapter list----------------------------------
	private String[] navMenuTitles;
	private TypedArray listevent;

	public void menuslidingInfo() {
		mDrawerList = (ListView) rootView
				.findViewById(R.id.listview_buscar_contactos);

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
	}
}
