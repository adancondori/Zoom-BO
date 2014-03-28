package com.vista.dato;

import android.R.integer;
import android.content.ContentValues;

public class Imagen implements java.io.Serializable  {
	private Integer id = 0;
	private Integer cod = 0;
	private String direccion = "ninguna";
	private String horafecha = "ninguna";
	private static String DIRECCION="http://phantro.6te.net/uploads/";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getHorafecha() {
		return horafecha;
	}

	public void setHorafecha(String horafecha) {
		this.horafecha = horafecha;
	}

	public ContentValues getValues() {

		ContentValues values = new ContentValues();
		
		values.put("id", id);
		values.put("cod", cod);
		values.put("direccion", direccion);
		values.put("horafecha", horafecha);
		return values;
	}
}
