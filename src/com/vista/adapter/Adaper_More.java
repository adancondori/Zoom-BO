package com.vista.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.vista.dato.Perfil;
import com.vista.menu.NavDrawerItem;
import com.vista.zoonv1.BuildConfig;
import com.vista.zoonv1.R;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adaper_More extends BaseAdapter {

	public static final int TYPE_ITEM = 0;
	public static final int TYPE_PERFIL = 1;
	public static final int TYPE_SEPARATOR = 2;
	public static final int TYPE_COPIRIGHT = 3;
	public static final int TYPE_MAX_COUNT = TYPE_COPIRIGHT + 1;

	private ArrayList<NavDrawerItem> mData = new ArrayList<NavDrawerItem>();
	private LayoutInflater mInflater;

	public Adaper_More(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void addItem(final NavDrawerItem item) {
		mData.add(item);
		notifyDataSetChanged();
	}

	public void addSeparatorItem(final NavDrawerItem item, int tipo) {
		item.setTipo(tipo);
		mData.add(item);
		notifyDataSetChanged();
	}

	@Override
	public int getItemViewType(int position) {
		return mData.get(position).getTipo();
	}

	@Override
	public int getViewTypeCount() {
		return TYPE_MAX_COUNT;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public NavDrawerItem getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		int type = getItemViewType(position);
		System.out.println("getView " + position + " " + convertView
				+ " type = " + type);
		if (convertView == null) {
			holder = new ViewHolder();
			switch (type) {
			case TYPE_PERFIL:
				convertView = mInflater.inflate(R.layout.adapter_more, null);
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	public static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView title2;
		public TextView counter;
	}

}
