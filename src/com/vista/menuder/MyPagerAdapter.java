package com.vista.menuder;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tabs.adancc.desplazarview.TabsActivity.IconTabProvider;
import com.vista.negocio.SuperAwesomeCardFragment;
import com.vista.zoonv1.CategoriaFragment;
import com.vista.zoonv1.R;

public class MyPagerAdapter extends FragmentStatePagerAdapter
// implements IconTabProvider
{

	private final String[] TITLES = { "Todos", "Categorias"
	// , "Grupo 3","Grupo 4", "Grupo 5", "Grupo 6", "Grupo 7", "Grupo 8",
	// "Grupo 9",
	// "Grupo 10", "Grupo 11"
	};
	private final int[] IMG = { R.drawable.bus, R.drawable.car,
			R.drawable.cart, R.drawable.briefcase, R.drawable.creditcard,
			R.drawable.crossroads, R.drawable.cursor, R.drawable.dolly,
			R.drawable.fashion, R.drawable.gamecontroller, R.drawable.map };

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
		switch (position) {
		case 0:
			return new EventoFragment();
		case 1:
			return new CategoriaFragment().newInstance(position);
		case 2:
			return new CategoriaFragment().newInstance(position);
		case 3:
			return new CategoriaFragment().newInstance(position);
		case 4:
			return new CategoriaFragment().newInstance(position);
		case 5:
			return new CategoriaFragment().newInstance(position);
		case 6:
			return new CategoriaFragment().newInstance(position);
		case 7:
			return new CategoriaFragment().newInstance(position);
		case 8:
			return new CategoriaFragment().newInstance(position);
		case 9:
			return new CategoriaFragment().newInstance(position);
		case 10:
			return new CategoriaFragment().newInstance(position);
		case 11:
			return new CategoriaFragment().newInstance(position);
		default:
			throw new IllegalArgumentException(
					"The item position should be less or equal to:" + position);
		}
	}

	// @Override
	// public int getPageIconResId(int position) {
	// // TODO Auto-generated method stub
	// return IMG[position];
	// }

}
