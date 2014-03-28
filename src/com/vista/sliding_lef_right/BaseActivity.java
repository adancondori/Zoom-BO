package com.vista.sliding_lef_right;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import com.vista.menuizq.MenuIZQFragment;
import com.vista.zoonv1.R;

@SuppressLint("NewApi")
public class BaseActivity extends SlidingSherlockFragmentActivity {

	private int mTitleRes;
	protected MenuIZQFragment mFrag;

	public BaseActivity(int titleRes) {
		mTitleRes = titleRes;
	}

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(mTitleRes);

		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		if (savedInstanceState == null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			mFrag = new MenuIZQFragment();
			ft.replace(R.id.menu_frame, mFrag);
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.commit();
			//
			// FragmentTransaction t = this.getSupportFragmentManager()
			// .beginTransaction();
			// mFrag = new PrincipalFragment();
			// t.replace(R.id.menu_frame, mFrag);
			// t.commit();
		} // else {
			// mFrag = (ListFragment) this.getSupportFragmentManager()
			// .findFragmentById(R.id.menu_frame);
			// } setTouchModeAbove

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(com.vista.zoonv1.R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow_sliding);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		// sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
