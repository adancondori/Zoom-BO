package com.vista.dato;

import java.io.Serializable;

public class Lugares implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -731331739035340954L;
	public String titulo;
	public String direccion;
	public Double latitud;
	public Double longitud;
	public int img_delete;
	public int img_edit;
	public int tipo = 0;

	public Lugares() {
		tipo = 0;
		titulo = "";
		img_edit = 0;
		img_delete = 0;
		direccion = "";
		latitud = 0.0;
		longitud = 0.0;
	}

	/**
	 * @return the img_delete
	 */
	public int getImg_delete() {
		return img_delete;
	}

	/**
	 * @param img_delete
	 *            the img_delete to set
	 */
	public void setImg_delete(int img_delete) {
		this.img_delete = img_delete;
	}

	/**
	 * @return the img_edit
	 */
	public int getImg_edit() {
		return img_edit;
	}

	/**
	 * @param img_edit
	 *            the img_edit to set
	 */
	public void setImg_edit(int img_edit) {
		this.img_edit = img_edit;
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

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the latitud
	 */
	public Double getLatitud() {
		return latitud;
	}

	/**
	 * @param latitud
	 *            the latitud to set
	 */
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	/**
	 * @return the longitud
	 */
	public Double getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud
	 *            the longitud to set
	 */
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Lugares(String titulo, String direccion, Double latitud,
			Double longitud, int img_delete, int img_edit, int tipo) {
		super();
		this.titulo = titulo;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.img_delete = img_delete;
		this.img_edit = img_edit;
		this.tipo = tipo;
	}

}
