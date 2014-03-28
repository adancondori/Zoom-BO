package com.vista.dato;

import java.util.Date;

import android.content.ContentValues;

// Generated 10-06-2013 10:34:05 PM by Hibernate Tools 3.2.1.GA

/**
 * DetalleDeVenta generated by hbm2java
 */
public class DetalleDeVenta implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5241802325710110956L;
	private int producto;
	private int venta;
	private int descuento;
	private int cantidad;
	private int importe;
	private Date date;

	public DetalleDeVenta() {
	}

	public DetalleDeVenta(int producto, int venta, int descuento, int cantidad,
			int importe) {
		this.producto = producto;
		this.venta = venta;
		this.descuento = descuento;
		this.cantidad = cantidad;
		this.importe = importe;
	}

	public int getProducto() {
		return this.producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getVenta() {
		return this.venta;
	}

	public void setVenta(int venta) {
		this.venta = venta;
	}

	public int getDescuento() {
		return this.descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getImporte() {
		return this.importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}

	public ContentValues getValues() {

		ContentValues values = new ContentValues();
		// if (cod != -1) {
		// values.put("cod", cod);
		// }
		values.put("vent", venta);
		values.put("prod", producto);
		values.put("descuento", descuento);
		values.put("cantidad", cantidad);
		values.put("importe", importe);
		return values;
	}
}