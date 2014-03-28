package com.vista.menuizq;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.tabs.adancc.desplazarview.TabsActivity;
import com.vista.negocio.SuperAwesomeCardFragment;
import com.vista.zoonv1.LugaresFragmentComent;
import com.vista.zoonv1.R;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;

@SuppressLint("NewApi")
public class FavoritosActivity extends SherlockFragmentActivity {

	private final Handler handler = new Handler();

	private TabsActivity tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private Drawable oldBackground = null;

	private int currentColor = 0xFF666666;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_favoritos);

		tabs = (TabsActivity) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new MyPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);

		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		int color = Color.parseColor("#3F9FE0");
		// BackgroundDrawable();
		changeColor(color);

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
			break;
		}
		return true;
	}

	private void changeColor(int newColor) {

		tabs.setIndicatorColor(newColor);

		// change ActionBar color just if an ActionBar is available
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		//
		// Drawable colorDrawable = new ColorDrawable(newColor);
		// Drawable bottomDrawable = getResources().getDrawable(
		// R.drawable.actionbar_bottom);
		// LayerDrawable ld = new LayerDrawable(new Drawable[] {
		// colorDrawable, bottomDrawable });
		//
		// if (oldBackground == null) {
		//
		// if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
		// ld.setCallback(drawableCallback);
		// } else {
		// getActionBar().setBackgroundDrawable(ld);
		// }
		//
		// } else {
		//
		// TransitionDrawable td = new TransitionDrawable(new Drawable[] {
		// oldBackground, ld });
		// if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
		// td.setCallback(drawableCallback);
		// } else {
		// getActionBar().setBackgroundDrawable(td);
		// }
		// td.startTransition(200);
		// }
		//
		// oldBackground = ld;
		// getActionBar().setDisplayShowTitleEnabled(true);
		//
		// }
		//
		// currentColor = newColor;

	}

	private Drawable.Callback drawableCallback = new Drawable.Callback() {
		@Override
		public void invalidateDrawable(Drawable who) {
			getActionBar().setBackgroundDrawable(who);
		}

		@Override
		public void scheduleDrawable(Drawable who, Runnable what, long when) {
			handler.postAtTime(what, when);
		}

		@Override
		public void unscheduleDrawable(Drawable who, Runnable what) {
			handler.removeCallbacks(what);
		}
	};

	// //////////////////////////////////////////
	public class MyPagerAdapter extends FragmentPagerAdapter {

		private final String[] TITLES = { "Categorias", "Reciente" };

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
		public Fragment getItem(int position) {
			if (position == 0) {
				return new FavoritoCategoriaFragment();
			} else {
				// return CategoriaFragment.newInstance(position);
				return new FavoritoRecientesFragment();
			}
		}
	}
}
