package com.vista.adapter;

import java.util.LinkedList;

import com.vista.dato.Contacto;
import com.vista.menu.NavDrawerItem;
import com.vista.menuder.SearchActivity;
import com.vista.zoonv1.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_Contacto extends BaseAdapter {

	public static final int TYPE_ELEMENTO = 0;
	public static final int TYPE_SEPARATOR_LETRA = 1;
	public static final int TYPE_SEPARATOR = 2;
	public static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

	private LinkedList<Contacto> mData = new LinkedList<Contacto>();
	private LayoutInflater mInflater;

	public Adapter_Contacto(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void addItem(final Contacto item) {
		mData.addFirst(item);
		notifyDataSetChanged();
	}

	public void addSeparatorItem(final Contacto item, int tipo) {
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
	public Contacto getItem(int position) {
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

		if (convertView == null) {
			holder = new ViewHolder();
			switch (type) {
			case TYPE_SEPARATOR_LETRA:
				convertView = mInflater.inflate(
						R.layout.adapter_separator_letra, null);
				holder.title = (TextView) convertView.findViewById(R.id.title);

				break;

			case TYPE_ELEMENTO:
				convertView = mInflater.inflate(R.layout.adapter_item_contacto,
						null);

				holder.title = (TextView) convertView
						.findViewById(R.id.txt_nombre);
				holder.phone = (TextView) convertView
						.findViewById(R.id.txt_telefono);
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.title.setText(mData.get(position).getNombre());
		if (mData.get(position).getTipo() == TYPE_ELEMENTO) {
			holder.phone.setText(mData.get(position).getTelefono());
		}

		return convertView;
	}

	public Contacto getContacto(int pos) {
		return mData.get(pos);
	}

	public static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView phone;
	}

}
