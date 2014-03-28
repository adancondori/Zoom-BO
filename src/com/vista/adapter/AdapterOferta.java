package com.vista.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import com.vista.dato.Perfil;
import com.vista.menu.NavDrawerItem;
import com.vista.menuder.SearchActivity;
import com.vista.zoonv1.BuildConfig;
import com.vista.zoonv1.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterOferta extends BaseAdapter {

	public static final int TYPE_ITEM = 0;
	public static final int TYPE_PERFIL = 1;
	public static final int TYPE_SEPARATOR = 2;
	public static final int TYPE_COPIRIGHT = 3;
	public static final int TYPE_MAX_COUNT = TYPE_COPIRIGHT + 1;

	private LinkedList<NavDrawerItem> mData = new LinkedList<NavDrawerItem>();
	private LayoutInflater mInflater;

	// private TreeSet<Integer> mSeparatorsSet = new TreeSet<Integer>();
	private Context context;

	public AdapterOferta(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}

	public void addItem(final NavDrawerItem item) {
		mData.addFirst(item);
		notifyDataSetChanged();
	}

	public void addSeparatorItem(final NavDrawerItem item, int tipo) {
		item.setTipo(tipo);
		mData.addFirst(item);
		notifyDataSetChanged();
	}

	public void eliminar_primero() {
		if (mData.size() > 0) {
			mData.remove(0);
			notifyDataSetChanged();
		}
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
			case TYPE_SEPARATOR:
				convertView = mInflater.inflate(R.layout.onlybutton, null);
				convertView.setBackgroundResource(R.color.blanco);
				holder.text_buscar = (TextView) convertView
						.findViewById(R.id.textsearch);
				final View convertView1 = convertView;
				holder.text_buscar
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(convertView1
										.getContext(), SearchActivity.class);
								intent.putExtra("TIPO_TEXTO",
										SearchActivity.TIPO_TEXTO_OFERTA);
								convertView1.getContext().startActivity(intent);
							}
						});
				break;
			case TYPE_PERFIL:
				convertView = mInflater.inflate(R.layout.adapter_item_oferta,
						null);
				convertView.setBackgroundResource(R.color.blanco);

				holder.title = (TextView) convertView
						.findViewById(R.id.titleofertas);
				break;
			case TYPE_ITEM:
				convertView = mInflater.inflate(R.layout.adapter_item_oferta,
						null);
				convertView.setBackgroundResource(R.color.background_zebra);
				holder.title = (TextView) convertView
						.findViewById(R.id.titleofertas);
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (type == TYPE_PERFIL) {
			convertView.setBackgroundResource(R.color.blanco);
			holder.title.setText(mData.get(position).getTitle());
		}
		if (type == TYPE_ITEM) {
			convertView.setBackgroundResource(R.color.background_zebra);
			holder.title.setText(mData.get(position).getTitle());
		}
		if (type == TYPE_SEPARATOR) {
			convertView.setBackgroundResource(R.color.blanco);
		}

		return convertView;
	}

	public static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView title2;
		public TextView counter;
		public TextView text_buscar;
	}

}
