package com.vista.menuizq;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.vista.zoonv1.R;
import com.vista.zoonv1.R.layout;
import com.vista.zoonv1.R.style;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;

public class SitiosActivity extends SherlockActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_sitios);
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		BackgroundDrawable();
	}

	public void BackgroundDrawable() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#99C9CED0")));
	}
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.sitios, menu);
	// return true;
	// }
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		int itemId = item.getItemId();
		switch (itemId) {
		case android.R.id.home:
			setResult(RESULT_OK, getIntent());
			finish();
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		setResult(RESULT_OK, getIntent());
		finish();
	}

}
