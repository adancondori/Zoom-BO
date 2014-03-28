package com.vista.menuder;

import java.util.ArrayList;

import com.vista.zoonv1.R;
import com.vista.zoonv1.R.layout;
import com.vista.zoonv1.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchActivity extends Activity {
	// * --- varialbles------------------
	private EditText _texttexto;
	private TextView _fecha;
	private TextView _categoria;
	static final int DATE_DIALOG_ID = 999;
	private int year = 2014;
	private int month = 01;
	private int day = 01;
	private int TIPO_TEXTO = 1;
	public static int TIPO_TEXTO_OFERTA = 1;
	public static int TIPO_TEXTO_VENTA = 2;

	// * --- sistema ------------------

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		IU_initility();

		if (getIntent().getExtras().get("TIPO_TEXTO") != null) {
			TIPO_TEXTO = (Integer) getIntent().getExtras().get("TIPO_TEXTO");
			if (TIPO_TEXTO == TIPO_TEXTO_OFERTA) {
				getActionBar().setTitle("Busqueda en Ofertas");
			} else {
				getActionBar().setTitle("Busqueda en Eventos");
			}
		}
		// savedInstanceState.getp

	}

	// *----------METODOS-----------------------
	public void IU_initility() {
		_texttexto = (EditText) findViewById(R.id.texttexto);
		_fecha = (TextView) findViewById(R.id.textfecha);
		_fecha.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				selectionsexo();
			}
		});
		_categoria = (TextView) findViewById(R.id.textcategoria);
		_categoria.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				selectioncategoria();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

	public void selectionsexo() {
		ListView modeList = new ListView(this);
		ArrayList<String> list = new ArrayList<String>();
		list.add("Cualquier Fecha");
		list.add("Última Hora");
		list.add("Última 24 Hora");
		list.add("Última Semana");
		list.add("Última Mes");
		list.add("Última Año");
		list.add("Personalizado");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle("Definir Foto de Perfil");

		// ListView modeList = new ListView(context);
		modeList.setBackgroundColor(Color.WHITE);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, list);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();
		dialog.setTitle("Seleccione Fecha");
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				switch (position) {
				case 0:
					_fecha.setText("Cualquier Fecha");
					break;
				case 1:
					_fecha.setText("Última Hora");
					break;
				case 2:
					_fecha.setText("Última 24 Hora");
					break;
				case 3:
					_fecha.setText("Última Semana");
					break;
				case 4:
					_fecha.setText("Última Mes");
					break;
				case 5:
					_fecha.setText("Última Año");
					break;
				case 6:
					showDialog(DATE_DIALOG_ID);
					break;
				}

				dialog.dismiss();
				// }
			}
		});
		dialog.show();
	}

	public void selectioncategoria() {
		ListView modeList = new ListView(this);
		ArrayList<String> list = new ArrayList<String>();
		list.add("Casas");
		list.add("Colegios");
		list.add("Surtidores");
		list.add("Mercados");
		list.add("Plazas");
		list.add("Buris");
		list.add("Peluquerias");

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		modeList.setBackgroundColor(Color.WHITE);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, android.R.id.text1, list);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();
		dialog.setTitle("Seleccione Categoria");
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// if (position != 0) {
				switch (position) {
				case 0:
					_categoria.setText("Casas");
					break;
				case 1:
					_categoria.setText("Colegios");
					break;
				case 2:
					_categoria.setText("Surtidores");
					break;
				case 3:
					_categoria.setText("Mercados");
					break;
				case 4:
					_categoria.setText("Plazas");
					break;
				case 5:
					_categoria.setText("Buris");
					break;
				case 6:
					_categoria.setText("Peluquerias");
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
		dialog.setTitle("Fecha de inicio");
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			_fecha.setText(new StringBuilder().append(day).append("/")
					.append(month + 1).append("/").append(year).append(" "));

		}
	};

	// ----En Show Dialog-------------------
}
