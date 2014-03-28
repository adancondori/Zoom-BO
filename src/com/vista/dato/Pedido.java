package com.vista.dato;
// Generated 10-06-2013 10:34:05 PM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.Set;

import android.content.ContentValues;

public class Pedido implements Serializable   {


     private int cod;
     private String nombre;
     private String descripcion;
     private String PUnitario;
     private String cantidad;
     private String estado;
     //private Set detalleDeVentas = new HashSet(0);

    public Pedido() {
    }

	
    public Pedido(String nombre, String descripcion, String medida, String PUnitario, String cantidad, String estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.PUnitario = PUnitario;
        this.cantidad = cantidad;
        this.estado = estado;
    }
    public Pedido(String nombre, String descripcion, String medida, String PUnitario, String cantidad, String estado, Set detalleDeVentas) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.PUnitario = PUnitario;
       this.cantidad = cantidad;
       this.estado = estado;
       //this.detalleDeVentas = detalleDeVentas;
    }
   
    public int getCod() {
        return this.cod;
    }
    
    public void setCod(int cod) {
        this.cod = cod;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPUnitario() {
        return this.PUnitario;
    }
    
    public void setPUnitario(String PUnitario) {
        this.PUnitario = PUnitario;
    }
    public String getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
	public ContentValues getValues() {

		ContentValues values = new ContentValues();
		if (cod != -1) {
			values.put("cod", cod);
		}

		values.put("nombre", nombre);
		values.put("descripcion", descripcion);
		values.put("p_unitario", PUnitario);
		values.put("cantidad", cantidad);
		values.put("estado", estado);
		return values;
	}
 	@Override
 	public String toString() {
 		// TODO Auto-generated method stub
 		return getNombre()+"      \n"+getPUnitario();
 	}
   /* public Set getDetalleDeVentas() {
        return this.detalleDeVentas;
    }
    
    public void setDetalleDeVentas(Set detalleDeVentas) {
        this.detalleDeVentas = detalleDeVentas;
    }*/




}


