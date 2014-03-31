package com.vista.menuder;

import com.tabs.adancc.desplazarview.TabsActivity;
import com.vista.GPSsingleton.Pedido_Singleton;
import com.vista.negocio.SuperAwesomeCardFragment;
import com.vista.zoonv1.R;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class MenuDERFragment extends Fragment {
	LayoutInflater inflater;
	View rootView;

	boolean visible = false;
	private TabsActivity tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.inflater = inflater;
		this.rootView = this.inflater.inflate(R.layout.fragment_menuizq,
				container, false);

		tabs = (TabsActivity) rootView.findViewById(R.id.tabs_der);
		pager = (ViewPager) rootView.findViewById(R.id.pager_der);
		adapter = new MyPagerAdapter(getFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	public class MyPagerAdapter extends FragmentStatePagerAdapter {

		private final String[] TITLES = { "Ofertas", "Eventos" };

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			if (position == 0) {
				return new OfertaFragment();
			} else
				// if (position == 1) {
				return new EventoFragment();
		}
		// else {
		// return new onlyMap();
		// }
		// }
	}

}
