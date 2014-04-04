package com.vista.dato;

import java.io.Serializable;

import com.vista.adapter.Adapter_Contacto;

public class Contacto implements Serializable {

	private int id = 0;
	private String nombre = "";
	private String apmaterno = "";
	private String appaterno = "";
	private String telefono = "";

	private int icon;
	public int tipo = Adapter_Contacto.TYPE_ELEMENTO;

	public Contacto(int id, String nombre, String apmaterno, String appaterno,
			String telefono, int icon, int tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apmaterno = apmaterno;
		this.appaterno = appaterno;
		this.telefono = telefono;
		this.icon = icon;
		this.tipo = tipo;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apmaterno
	 */
	public String getApmaterno() {
		return apmaterno;
	}

	/**
	 * @param apmaterno
	 *            the apmaterno to set
	 */
	public void setApmaterno(String apmaterno) {
		this.apmaterno = apmaterno;
	}

	/**
	 * @return the appaterno
	 */
	public String getAppaterno() {
		return appaterno;
	}

	/**
	 * @param appaterno
	 *            the appaterno to set
	 */
	public void setAppaterno(String appaterno) {
		this.appaterno = appaterno;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the icon
	 */
	public int getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            the icon to set
	 */
	public void setIcon(int icon) {
		this.icon = icon;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

}
