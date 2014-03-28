package com.vista.menu;

import java.util.ArrayList;

import com.tabs.adancc.desplazarview.TabsActivity;
import com.google.android.gms.maps.internal.m;

import com.vista.menuder.MenuDERFragment;
//import com.astuetz.viewpager.extensions.PagerSlidingTabStrip;

import com.vista.zoonv1.R;

import android.annotation.SuppressLint;
//import android.app.Fragment;
//import android.app.FragmentManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuListAdapter extends BaseAdapter {

	public static final int TYPE_ITEM = 0;
	public static final int TYPE_PERFIL = 1;
	public static final int TYPE_SEPARATOR = 2;
	public static final int TYPE_COPIRIGHT = 3;
	public static final int TYPE_MAX_COUNT = TYPE_COPIRIGHT + 1;

	private ArrayList<NavDrawerItem> mData = new ArrayList<NavDrawerItem>();
	private LayoutInflater mInflater;
	// private MyPagerAdapter adapter;
	private Context context;
	FragmentManager fragmentManager;

	public MenuListAdapter(Context context, FragmentManager fragmentManager) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
		this.fragmentManager = fragmentManager;
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

	@SuppressLint("NewApi")
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
				convertView = mInflater
						.inflate(R.layout.adapter_tabvista, null);

				Fragment fragment = new MenuDERFragment();
				// FragmentManager fragmentManager = ((MainActivity) context)
				// .getFragmentManager();
				// fragmentManager = convertView.getContext().
				fragmentManager
						.beginTransaction()
						.replace(
								convertView.findViewById(R.id.content_frame2)
										.getId(), fragment).commit();
				break;

			case TYPE_COPIRIGHT:
				convertView = mInflater.inflate(R.layout.adapter_corporation,
						null);
				holder.title = (TextView) convertView.findViewById(R.id.title);
				holder.title2 = (TextView) convertView
						.findViewById(R.id.title2);
				String cad[] = mData.get(position).getTitle().split(":");
				if (cad.length > 1) {
					holder.title.setText(cad[0]);
					holder.title2.setText(cad[1]);
				}
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
