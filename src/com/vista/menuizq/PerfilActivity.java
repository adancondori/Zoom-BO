package com.vista.menuizq;

import java.util.ArrayList;
import java.util.Calendar;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.vista.iucamara.CamaraActivity;
import com.vista.util.Utils;
import com.vista.zoonv1.R;
import com.vista.zoonv1.R.id;
import com.vista.zoonv1.R.layout;
import com.vista.zoonv1.R.style;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
//import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.preference.Preference;
//import android.preference.PreferenceActivity;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class PerfilActivity extends SherlockActivity {
	// private AlertDialog diag_F = null;
	public static final int HEIGHT_FOTO = 100;
	public static final int WIDHT_FOTO = 100;
	public static final int IMG_CAMARA = 0;
	public static final int IMG_GALERIA_FROM_FILE = 1;
	public static final int IMG_DENIED = 2;
	private ImageView foto;
	private ImageView img_camara;
	private ImageView img_galeria;
	private ImageView img_denied;
	// private Button img_dir_trabajo;
	// private Button img_dir_domicilio;
	private TextView sexo;
	private TextView fechanac;
	static final int DATE_DIALOG_ID = 999;
	private int year;
	private int month;
	private int day;

	// img_dir_domicilio
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// setTheme(R.style.Theme_Sherlock_Light);
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.trans_abajo_hacia_arriba_activity,
				R.anim.trans_no_trasmicion_activity);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_perfil);

		inicializar();
		getSupportActionBar().show();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// BackgroundDrawable();

	}

	public void BackgroundDrawable() {
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#99C9CED0")));
	}

	private void inicializar() {
		// TODO Auto-generated method stub
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		foto = (ImageView) findViewById(R.id.foto);
		foto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// selectionfoto();
			}
		});
		img_camara = (ImageView) findViewById(R.id.camara);
		img_camara.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selecttipofoto(IMG_CAMARA);
			}
		});
		// img_dir_domicilio = (Button) findViewById(R.id.btndomicilio);
		// img_dir_domicilio.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent(getApplicationContext(),
		// LugaresActivity.class);
		// startActivity(intent);
		// }
		// });
		// img_dir_trabajo = (Button) findViewById(R.id.btntrabajo);
		// img_dir_trabajo.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// Intent intent = new Intent(getApplicationContext(),
		// LugaresActivity.class);
		// startActivity(intent);
		// }
		// });
		img_galeria = (ImageView) findViewById(R.id.galeria);
		img_galeria.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecttipofoto(IMG_GALERIA_FROM_FILE);
			}
		});
		img_denied = (ImageView) findViewById(R.id.noimagen);
		img_denied.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selecttipofoto(IMG_DENIED);
			}
		});

		sexo = (TextView) findViewById(R.id.sexo);
		sexo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				selectionsexo();
			}
		});

		fechanac = (TextView) findViewById(R.id.fechanac);
		fechanac.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});
	}

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
		finish();
		setResult(RESULT_OK, getIntent());
		overridePendingTransition(R.anim.trans_no_trasmicion_activity,
				R.anim.trans_arriba_hacia_abajo_activity);

	}

	// public void selectionfoto() {
	// ListView modeList = new ListView(this);
	// ArrayList<String> list = new ArrayList<String>();
	// list.add("Tomar de Camara");
	// list.add("Seleccionar de Galeria");
	//
	// AlertDialog.Builder builder = new AlertDialog.Builder(this);
	// builder.setTitle("Definir Foto de Perfil");
	//
	// // ListView modeList = new ListView(context);
	// modeList.setBackgroundColor(Color.WHITE);
	// ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,
	// android.R.layout.simple_list_item_1, android.R.id.text1, list);
	//
	// modeList.setAdapter(modeAdapter);
	//
	// builder.setView(modeList);
	// final Dialog dialog = builder.create();
	//
	// dialog.show();
	// modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	//
	// @Override
	// public void onItemClick(AdapterView<?> arg0, View arg1,
	// int position, long arg3) {
	//
	// dialog.dismiss();
	// }
	// });
	//
	// }

	public void selecttipofoto(int tipo) {
		Intent intent = null;
		Bundle b = null;
		switch (tipo) {
		case IMG_CAMARA:
			intent = new Intent(PerfilActivity.this, CamaraActivity.class);
			b = new Bundle();
			b.putInt("modo", CamaraActivity.PICK_FROM_CAMERA);
			intent.putExtras(b);
			startActivityForResult(intent, CamaraActivity.PICK_FROM_CAMERA);
			break;
		case IMG_GALERIA_FROM_FILE:
			intent = new Intent(PerfilActivity.this, CamaraActivity.class);
			b = new Bundle();
			b.putInt("modo", CamaraActivity.PICK_FROM_FILE);
			intent.putExtras(b);
			startActivityForResult(intent, CamaraActivity.PICK_FROM_FILE);
			break;
		case IMG_DENIED:
			foto.setImageResource(R.drawable.profle);
			break;
		}
	}

	public void selectionsexo() {
		ListView modeList = new ListView(this);
		ArrayList<String> list = new ArrayList<String>();
		list.add("-");
		list.add("Hombre");
		list.add("Mujer");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle("Definir Foto de Perfil");

		// ListView modeList = new ListView(context);
		modeList.setBackgroundColor(Color.WHITE);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, list);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();
		dialog.setTitle("Sexo (Género)");
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// if (position != 0) {
				switch (position) {
				case 0:
					sexo.setText("-");
					break;
				case 1:
					sexo.setText("Hombre");
					break;
				case 2:
					sexo.setText("Mujer");
					break;
				}

				dialog.dismiss();
				// }
			}
		});
		dialog.show();
	}

	// ------Show Dialog Pincker------------------------------------

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date

			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		super.onPrepareDialog(id, dialog);
		dialog.setTitle("Fecha de Nacimiento");
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			System.out.println(day + "/" + month + 1 + "/" + year);
			fechanac.setText(new StringBuilder().append(day).append("/")
					.append(month + 1).append("/").append(year).append(" "));

			// set selected date into datepicker also
			// dpResult.init(year, month, day, null);

		}
	};

	// ----En Show Dialog-------------------

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// vuelta de la camara o galeria de imagenes
		if (resultCode == RESULT_OK) {

			Bundle ext = data.getExtras();
			Bitmap bmp = ext.getParcelable("data");

			Bitmap foto = Bitmap
					.createScaledBitmap(bmp, PerfilActivity.WIDHT_FOTO,
							PerfilActivity.HEIGHT_FOTO, true);
			this.foto.setImageBitmap(Utils.getCroppedBitmap(foto));
		} else if (resultCode == 3) {

		}
	}

}
