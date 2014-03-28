package com.vista.sqlite;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		System.out.println("insert");
		String cliente = "CREATE TABLE IF NOT EXISTS cliente(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT ,ci TEXT ,direccion TEXT ,telefono TEXT ,nit TEXT,estado TEXT );";
		String negocion = "CREATE TABLE IF NOT EXISTS negocio(cod INTEGER PRIMARY KEY AUTOINCREMENT,latitud TEXT ,longitud TEXT ,ubicacion ,observacion TEXT,cli INTEGER ,estado TEXT );";
		String transportador = "CREATE TABLE IF NOT EXISTS transportador(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT ,placa TEXT ,carga TEXT ,estado TEXT );";
		String producto = "CREATE TABLE IF NOT EXISTS producto(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT ,descripcion TEXT ,medida TEXT ,p_unitario TEXT ,cantidad TEXT ,estado TEXT );";
		String venta = "CREATE TABLE IF NOT EXISTS venta(" +
				"cod INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "nro_venta TEXT  ,"
				+ "fecha TEXT ,"
				+ "total_bs INTEGER,"
				+ "pago TEXT ,"
				+ "fecha_entrega TEXT,"
				+ "ciudad TEXT ,"
				+ "deposito TEXT ,"
				+ "tran INTEGER ,"
				+ "vend INTEGER ,"
				+ "negoc INTEGER ,"  
				+"entregado INTEGER," 
				+"estado TEXT );";
		String detalle_de_venta = "CREATE TABLE IF NOT EXISTS detalle_de_venta(" +
				"vent INTEGER ," +
				"prod INTEGER ," +
				"descuento INTEGER ," +
				"cantidad INTEGER ," +
				"importe INTEGER ," +
				"PRIMARY KEY(vent,prod));";
		String users = "CREATE TABLE IF NOT EXISTS users(cod INTEGER PRIMARY KEY AUTOINCREMENT,   unique_id TEXT  unique,   name TEXT ,   email TEXT  unique,   encrypted_password TEXT ,   salt TEXT ,   created_at TEXT,   updated_at TEXT null);";
		String usuario = "CREATE TABLE IF NOT EXISTS usuario(cod INTEGER PRIMARY KEY AUTOINCREMENT,nick TEXT  unique,email TEXT ,encrypted_password TEXT ,salt TEXT ,creado TEXT,actualizado TEXT,pers INTEGER ,estado TEXT );";
		String personal = "CREATE TABLE IF NOT EXISTS personal(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT ,direccion TEXT ,telefono TEXT ,cargo TEXT ,ci TEXT ,estado TEXT );";
		String imagen = "CREATE TABLE IF NOT EXISTS imagenes(" +
				"id INTEGER PRIMARY KEY AUTOINCREMENT ," +
				"cod INTEGER," +
				"direccion TEXT ," +
				"horafecha TEXT);";

		db.execSQL(cliente);
		db.execSQL(negocion);
		db.execSQL(transportador);
		db.execSQL(personal);
		db.execSQL(producto);
		db.execSQL(venta);
		db.execSQL(detalle_de_venta);
		db.execSQL(users);
		db.execSQL(usuario);
		db.execSQL(imagen);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
