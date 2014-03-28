package com.vista.dato;
// Generated 10-06-2013 10:34:05 PM by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

import android.content.ContentValues;

/**
 * Negocio generated by hbm2java
 */
public class Negocio  implements java.io.Serializable {


     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cod;
     private String cliente;
     private String latitud;
     private String longitud;
     private String ubicacion;
     private String observacion;
     private String estado;
     private Set ventas = new HashSet(0);

    public Negocio() {
    }

	
    public Negocio(String cliente, String latitud, String longitud, String ubicacion, String estado) {
        this.cliente = cliente;
        this.latitud = latitud;
        this.longitud = longitud;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }
    public Negocio(String cliente, String latitud, String longitud, String ubicacion, String observacion, String estado, Set ventas) {
       this.cliente = cliente;
       this.latitud = latitud;
       this.longitud = longitud;
       this.ubicacion = ubicacion;
       this.observacion = observacion;
       this.estado = estado;
       this.ventas = ventas;
    }
   
    public Integer getCod() {
        return this.cod;
    }
    
    public void setCod(Integer cod) {
        this.cod = cod;
    }
    public String getCliente() {
        return this.cliente;
    }
    
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public String getLatitud() {
        return this.latitud;
    }
    
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return this.longitud;
    }
    
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public String getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }
	public ContentValues getValues() {

		ContentValues values = new ContentValues();
		if (cod != -1) {
			values.put("cod", cod);
		}

		values.put("latitud", latitud);
		values.put("longitud", longitud);
		values.put("ubicacion", ubicacion);
		values.put("observacion", observacion);
		values.put("cli", cliente);
		values.put("estado", estado);
		return values;
	}



}

