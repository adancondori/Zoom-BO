package com.vista.dato;

import com.vista.adapter.Adapter_Lugares;

public class Lugares {

	public String cad;
	public int img_delete;
	public int img_edit;
	public int tipo = 0;

	public Lugares() {
		tipo = 0;
		cad = "";
		img_edit = 0;
		img_delete = 0;
	}

	public Lugares(int tipo, String cadena, int img_delete, int img_edit) {
		this.cad = cadena;
		this.img_delete = img_delete;
		this.img_edit = img_edit;
		this.tipo = Adapter_Lugares.TYPE_ITEM;
	}

	/**
	 * @return the cad
	 */
	public String getCad() {
		return cad;
	}

	/**
	 * @param cad
	 *            the cad to set
	 */
	public void setCad(String cad) {
		this.cad = cad;
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

}
