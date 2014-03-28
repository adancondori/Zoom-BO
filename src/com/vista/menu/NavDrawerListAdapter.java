//package com.vista.menu;
//
//import java.util.ArrayList;
//
//import com.vista.zoonv1.R;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.RelativeLayout.LayoutParams;
//import android.widget.TextView;
//
//public class NavDrawerListAdapter extends ArrayAdapter<NavDrawerItem> {
//
//	private Context context;
//	private ArrayList<NavDrawerItem> navDrawerItems;
//
//	public NavDrawerListAdapter(Context context,
//			ArrayList<NavDrawerItem> navDrawerItems) {
//		super(context, R.layout.drawer_list_item, navDrawerItems);
//		this.context = context;
//		this.navDrawerItems = navDrawerItems;
//	}
//
//	@Override
//	public int getCount() {
//		return navDrawerItems.size();
//	}
//
//	@Override
//	public NavDrawerItem getItem(int position) {
//		return navDrawerItems.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		if (convertView == null) {
//			LayoutInflater mInflater = (LayoutInflater) context
//					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//			convertView = mInflater.inflate(R.layout.draw_list_item_der, null);
//		}
//
//		ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
//		TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
//		TextView txtCount = (TextView) convertView.findViewById(R.id.counter);// counter
//
//		// switch (navDrawerItems.get(position).getType()) {
//		// case NavDrawerItem.type_normal: {
//		// if (position == 0 && navDrawerItems.get(position).getType() ==
//		// NavDrawerItem.type_Persona) {
//		// Bitmap bmp;
//		// int width = 150;
//		// int height = 150;
//		// bmp = BitmapFactory.decodeResource(context.getResources(),
//		// R.drawable.business_user);
//		// bmp = Bitmap.createScaledBitmap(bmp, width, height, true);
//		// imgIcon.getLayoutParams().height = height;
//		// imgIcon.getLayoutParams().width = width;
//		// imgIcon.setImageBitmap(bmp);
//		// txtTitle.setText("Adan Condori Condori");
//		// } else {
//		imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
//		txtTitle.setText(navDrawerItems.get(position).getTitle());
//		// }
//
//		// displaying count
//		// check whether it set visible or not
//		if (navDrawerItems.get(position).getVisible()) {
//			txtCount.setText(navDrawerItems.get(position).getCount());
//		} else {
//			// hide the counter view
//			txtCount.setVisibility(View.GONE);
//		}
//		// break;
//		// }
//
//		// case NavDrawerItem.type_cabecera: {
//		//
//		// LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT,
//		// LayoutParams.FILL_PARENT);
//		// lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
//		// lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
//		// // lp.bottomMargin=20;
//		// lp.leftMargin = 10;
//		// txtTitle.setText("  "
//		// + navDrawerItems.get(position).getTitle().toUpperCase());
//		// txtTitle.setLayoutParams(lp);
//		// txtTitle.setBackgroundColor(Color.parseColor("#303030"));
//		// txtTitle.setGravity(Gravity.BOTTOM);
//		// imgIcon.setVisibility(View.GONE);
//		// txtCount.setVisibility(View.GONE);
//		// // convertView.setBackgroundResource(R.drawable.cabecera_list);
//		//
//		// break;
//		// }
//		// default:
//		// break;
//		// }
//
//		return convertView;
//	}
//}
