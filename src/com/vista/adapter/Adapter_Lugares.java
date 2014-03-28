package com.vista.adapter;

import java.util.ArrayList;
import java.util.TreeSet;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.vista.GPSsingleton.Pedido_Singleton;
import com.vista.dato.Lugares;
import com.vista.util.AlertDialogManager_Progres;
import com.vista.zoonv1.R;

public class Adapter_Lugares extends BaseAdapter {

	public static final int TYPE_ITEM = 0;
	private static final int TYPE_SEPARATOR = 1;
	private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

	private ArrayList<Lugares> mData = new ArrayList<Lugares>();
	private LayoutInflater mInflater;

	public Context context;

	public Adapter_Lugares(Context context) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}

	public void addItem(final Lugares item) {
		mData.add(item);
		notifyDataSetChanged();
	}

	public void addSeparatorItem(final Lugares item) {
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
	public Lugares getItem(int position) {
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
			case TYPE_ITEM:
				convertView = mInflater.inflate(R.layout.adapter_lugares, null);
				holder.txt_texto = (TextView) convertView
						.findViewById(R.id.txt_detalle);
				holder.txt_texto.setText(mData.get(position).getCad());

				break;
			case TYPE_SEPARATOR:
				// convertView = mInflater.inflate(R.layout.item2, null);
				// holder.textView =
				// (TextView)convertView.findViewById(R.id.textSeparator);
				break;
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.txt_texto.setText(mData.get(position).getCad());
		return convertView;
	}

	public static class ViewHolder {
		public TextView txt_texto;

	}
}
