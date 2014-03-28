package com.vista.negocio;

import java.util.ArrayList;

import com.vista.dato.Cliente;
import com.vista.sqlite.MyHelper;

import android.content.Context;
import android.database.Cursor;

public class NCliente {

	public NCliente() {

	}

	public ArrayList<Cliente> getTodo(Context context) {
		MyHelper helper = new MyHelper();
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		Cursor c = helper.getObjects("select * from cliente", context);
		c.moveToFirst();

		Cliente cli = null;
		while (!c.isAfterLast()) {
			cli = new Cliente();
			cli.setCodcl(c.getInt(c.getColumnIndex("cod")));
			cli.setNombre(c.getString(c.getColumnIndex("nombre")));
			cli.setCi(c.getString(c.getColumnIndex("ci")));
			cli.setDireccion(c.getString(c.getColumnIndex("direccion")));
			cli.setTelefono(c.getString(c.getColumnIndex("telefono")));
			c.moveToNext();
			clientes.add(cli);
		}
		c.close();

		return clientes;
	}

//	public ArrayList<Negocio> getTodoNegocio(Context context, String cod) {
//		MyHelper helper = new MyHelper();
//		ArrayList<Negocio> negocios = new ArrayList<Negocio>();
//		Cursor c = helper.getObjects("select * from negocio where cli=" + cod,
//				context);
//		c.moveToFirst();
//
//		Negocio n = null;
//		while (!c.isAfterLast()) {
//			n = new Negocio();
//			n.setCod(c.getInt(c.getColumnIndex("cod")));
//			n.setCliente(c.getString(c.getColumnIndex("cli")));
//			n.setLatitud(c.getString(c.getColumnIndex("latitud")));
//			n.setLongitud(c.getString(c.getColumnIndex("longitud")));
//			n.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
//			n.setObservacion(c.getString(c.getColumnIndex("observacion")));
//			n.setEstado(c.getString(c.getColumnIndex("estado")));
//			c.moveToNext();
//			negocios.add(n);
//		}
//		c.close();
//
//		return negocios;
//	}

	// public ArrayList<Negocio> getFullNegocios(Context context) {
	// MyHelper helper = new MyHelper();
	// ArrayList<Negocio> negocios = new ArrayList<Negocio>();
	// Cursor c = helper.getObjects("select * from negocio", context);
	// c.moveToFirst();
	//
	// Negocio n = null;
	// while (!c.isAfterLast()) {
	// n = new Negocio();
	// n.setCod(c.getInt(c.getColumnIndex("cod")));
	// n.setCliente(c.getString(c.getColumnIndex("cli")));
	// n.setLatitud(c.getString(c.getColumnIndex("latitud")));
	// n.setLongitud(c.getString(c.getColumnIndex("longitud")));
	// n.setUbicacion(c.getString(c.getColumnIndex("ubicacion")));
	// n.setObservacion(c.getString(c.getColumnIndex("observacion")));
	// n.setEstado(c.getString(c.getColumnIndex("estado")));
	// c.moveToNext();
	// negocios.add(n);
	// }
	// c.close();
	//
	// return negocios;
	// }

	public String getnombre(int ci, Context context) {
		String nombre = "error";
		MyHelper helper = new MyHelper();

		Cursor c = helper.getObjects("select * from cliente where ci=" + ci,
				context);
		c.moveToFirst();
		if (c != null && !c.isAfterLast()) {
			// System.out.println("Nusuario "+ c.getColumnCount());
			nombre = c.getString(c.getColumnIndex("nombre"));
		} else {
			System.out.println("Nusuario ---- Error");
		}
		return nombre;
	}

	public String getnombreCod(String cod, Context context) {
		String nombre = "error";
		MyHelper helper = new MyHelper();

		Cursor c = helper.getObjects("select * from cliente where cod=" + cod,
				context);

		c.moveToFirst();
		if (c != null && !c.isAfterLast()) {
			// System.out.println("Nusuario "+ c.getColumnCount());
			nombre = c.getString(c.getColumnIndex("nombre"));
		} else {
			System.out.println("Nusuario ---- Error");
		}
		return nombre;
	}

	public int getCICod(String ci, Context context) {
		int codcliente = -1;
		MyHelper helper = new MyHelper();

		Cursor c = helper.getObjects("select * from cliente where ci=" + ci,
				context);

		c.moveToFirst();
		if (c != null && !c.isAfterLast()) {
			// System.out.println("Nusuario "+ c.getColumnCount());
			codcliente = c.getInt(c.getColumnIndex("cod"));
		} else {
			System.out.println("Nusuario ---- Error");
		}
		return codcliente;
	}

	public int getCodNegocio(String codcliente, Context context) {
		int codnego = -1;
		MyHelper helper = new MyHelper();

		Cursor c = helper.getObjects("select * from negocio where cli="
				+ codcliente, context);

		c.moveToFirst();
		if (c != null && !c.isAfterLast()) {
			// System.out.println("Nusuario "+ c.getColumnCount());
			codnego = c.getInt(c.getColumnIndex("cod"));
		} else {
			System.out.println("NCliente ---- Error");
		}
		return codnego;
	}
}
