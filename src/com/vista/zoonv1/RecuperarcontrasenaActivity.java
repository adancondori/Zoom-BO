package com.vista.zoonv1;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.vista.util.AlertDialogManager_Progres;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;

public class RecuperarcontrasenaActivity extends SherlockActivity {
	public Button recuperar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recuperarcontrasena);
		inicialiarView();
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//		BackgroundDrawable();
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
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	public void inicialiarView() {
		// TODO Auto-generated method stub recuperar
		recuperar = (Button) findViewById(R.id.recuperar);
		recuperar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Message message = new Message();
				Bundle data = new Bundle();
				data.putInt("data", 0);
				message.setData(data);
				puente.sendMessage(message);
			}
		});
	}

	public Activity getactivityf() {
		return this;
	}

	public Handler puente = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			int nro = msg.getData().getInt("data");
			switch (nro) {
			case 0:
				AlertDialogManager_Progres.showAlertDialog(
						getactivityf(),
						getResources().getString(R.string.title_informacion),
						getResources().getString(
								R.string.title_recuperarcontracena_msj_envio),
						true);
				break;
			case 1:
				AlertDialogManager_Progres
						.showAlertDialog(
								getactivityf(),
								getResources().getString(
										R.string.title_informacion),
								getResources()
										.getString(
												R.string.title_recuperarcontracena_msj_no_envio),
								true);
				break;
			default:
				break;
			}
		}
	};

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.recuperarcontrasena, menu);
	// return true;
	// }

}
