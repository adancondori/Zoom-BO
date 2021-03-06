package com.vista.dato;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.vista.sqlite.MyHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private int cod;
     private String nombre;
     private String ci;
     private String direccion;
     private String telefono;
     private String nit;
     private String estado;
     private Set negocios = new HashSet(0);

    public Cliente() {
    }

	
    public Cliente(String nombre, String ci, String direccion, String telefono, String estado) {
        this.nombre = nombre;
        this.ci = ci;
        this.direccion = direccion;
        this.telefono = telefono;
        this.estado = estado;
    }
    public Cliente(String nombre, String ci, String direccion, String telefono, String nit, String estado, Set negocios) {
       this.nombre = nombre;
       this.ci = ci;
       this.direccion = direccion;
       this.telefono = telefono;
       this.nit = nit;
       this.estado = estado;
       this.negocios = negocios;
    }
   
    public int getCodcl() {
        return this.cod;
    }
    
    public void setCodcl(Integer codcl) {
        this.cod = codcl;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCi() {
        return this.ci;
    }
    
    public void setCi(String ci) {
        this.ci = ci;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getNit() {
        return this.nit;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getNegocios() {
        return this.negocios;
    }
    
    public void setNegocios(Set negocios) {
        this.negocios = negocios;
    }

	public ArrayList<Cliente> getClientes(Context context){
		ArrayList<Cliente> cli= new ArrayList<Cliente>();
		MyHelper helper= new MyHelper();
		Cursor cursor= helper.getObjects("SELECT * FROM cliente",context);
		if (cursor!=null) {
			cursor.moveToFirst();
			while (cursor.isAfterLast()) {
				Cliente cliente=new Cliente();
				
			}
		} 
		
		return cli;
	}
	public ContentValues getValues() {

		ContentValues values = new ContentValues();
		if (cod != -1) {
			values.put("cod", cod);
		}
		values.put("nombre", nombre);
		values.put("ci", ci);
		values.put("direccion", direccion);
		values.put("telefono", telefono);
		values.put("nit", nit);
		values.put("estado", estado);
		return values;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNombre()+"     \n"+getCi();
	}
}


