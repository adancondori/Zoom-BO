package com.vista.dato;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "db_taller";

	// Login table name
	private static final String TABLE_LOGIN = "login";
	private static final String TABLE_CLIENTE = "cliente";
	private static final String TABLE_NEGOCION = "negocio";
	private static final String TABLE_TRANSPORTADOR = "transportador";
	private static final String TABLE_PRODUCTO = "producto";
	private static final String TABLE_VENTA = "venta";
	private static final String TABLE_DETALLE_DE_VENTA = "detalle_de_venta";

/*	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_UID = "uid";
	private static final String KEY_CREATED_AT = "created_at";
*/
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
	}

	// Creating Tables 
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
				+ KEY_CREATED_AT + " TEXT" + ")";*/

		String cliente="CREATE TABLE IF NOT EXISTS cliente(codcl INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL,ci TEXT NOT NULL,direccion TEXT NOT NULL,telefono TEXT NOT NULL,nit TEXT,estado TEXT NOT NULL);";
		String negocion="CREATE TABLE IF NOT EXISTS negocio(cod INTEGER PRIMARY KEY AUTOINCREMENT,latitud TEXT NOT NULL,longitud TEXT NOT NULL,ubicacion NOT NULL,observacion TEXT,cli INTEGER NOT NULL,estado TEXT NOT NULL);";
		String transportador="CREATE TABLE IF NOT EXISTS transportador(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL,placa TEXT NOT NULL,carga TEXT NOT NULL,estado TEXT NOT NULL);";
		String personal="CREATE TABLE IF NOT EXISTS personal(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL,direccion TEXT NOT NULL,telefono TEXT NOT NULL,cargo TEXT NOT NULL,ci TEXT NOT NULL,estado TEXT NOT NULL);";
		String producto="CREATE TABLE IF NOT EXISTS producto(cod INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT NOT NULL,descripcion TEXT NOT NULL,medida TEXT NOT NULL,p_unitario TEXT NOT NULL,cantidad TEXT NOT NULL,estado TEXT NOT NULL);";
		String venta="CREATE TABLE IF NOT EXISTS venta(cod INTEGER PRIMARY KEY AUTOINCREMENT,nro_venta TEXT NOT NULL ,fecha datetime NOT NULL,total_bs INTEGER,pago TEXT NOT NULL,fecha_entrega datetime,ciudad TEXT NOT NULL,deposito TEXT NOT NULL,tran INTEGER NOT NULL,vend INTEGER NOT NULL,negoc INTEGER NOT NULL,entre INTEGER,estado TEXT NOT NULL);";
		String detalle_de_venta="CREATE TABLE IF NOT EXISTS detalle_de_venta(vent INTEGER NOT NULL,prod INTEGER NOT NULL,descuento INTEGER NOT NULL,cantidad INTEGER NOT NULL,importe INTEGER NOT NULL,PRIMARY KEY(vent,prod));";
		String users="CREATE TABLE IF NOT EXISTS users(   uid INTEGER PRIMARY KEY AUTOINCREMENT,   unique_id TEXT NOT NULL unique,   name TEXT NOT NULL,   email TEXT NOT NULL unique,   encrypted_password TEXT NOT NULL,   salt TEXT NOT NULL,   created_at datetime,   updated_at datetime null);";
		String usuario="CREATE TABLE IF NOT EXISTS usuario(codus INTEGER PRIMARY KEY AUTOINCREMENT,nick TEXT NOT NULL unique,email TEXT NOT NULL,encrypted_password TEXT NOT NULL,salt TEXT NOT NULL,creado datetime,actualizado datetime,pers INTEGER NOT NULL unique,estado TEXT NOT NULL);";	
		Log.w("hOLA: ", cliente);
		db.execSQL(cliente);
		db.execSQL(negocion);
		db.execSQL(transportador);
		db.execSQL(personal);
		db.execSQL(producto);
		db.execSQL(venta);
		db.execSQL(detalle_de_venta);
		db.execSQL(users);
		db.execSQL(usuario);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLIENTE);
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETALLE_DE_VENTA);
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEGOCION);
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTO);
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTADOR);
		 db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENTA);

		 onCreate(db);
	}

	// Crud

	public void addContact(Producto producto) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("nombre", producto.getNombre());
		values.put("descripcion", producto.getDescripcion());
		values.put("medida", producto.getMedida());
		values.put("p_unitario", producto.getPUnitario());
		values.put("cantidad", producto.getCantidad());
		values.put("estado", producto.getEstado());
		// Inserting Row
		db.insert(TABLE_PRODUCTO, null, values);
		db.close(); // Closing database connection
	}

	public Producto getContact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PRODUCTO, new String[] { "cod",
				"nombre", "descripcion", "medida", "p_unitario", "cantidad",
				"estado" }, "cod" + "=?", new String[] { String.valueOf(id) },
				null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Producto producto = new Producto();
		producto.setCod(Integer.parseInt(cursor.getString(0)));
		producto.setNombre(cursor.getString(1));
		producto.setDescripcion(cursor.getString(2));
		producto.setMedida(cursor.getString(3));
		producto.setPUnitario(cursor.getString(4));
		producto.setCantidad(cursor.getString(5));
		producto.setEstado(cursor.getString(6));
		// return contact
		return producto;
	}
//
//	public List<Producto> getAllProductos() {
//		List<Producto> contactList = new ArrayList<Producto>();
//		String selectQuery = "SELECT  * FROM " + TABLE_PRODUCTO;
//
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		if (cursor.moveToFirst()) {
//			do {
//				Producto producto = new Producto();
//				producto.setCod(Integer.parseInt(cursor.getString(0)));
//				producto.setNombre(cursor.getString(1));
//				producto.setDescripcion(cursor.getString(2));
//				producto.setMedida(cursor.getString(3));
//				producto.setPUnitario(cursor.getString(4));
//				producto.setCantidad(cursor.getString(5));
//				producto.setEstado(cursor.getString(6));
//				contactList.add(producto);
//			} while (cursor.moveToNext());
//		}
//		return contactList;
//	}
//
//	public int getProductosCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_PRODUCTO;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		cursor.close();
//
//		// return count
//		return cursor.getCount();
//	}
//	public int updateContact(Producto producto) {
//	    SQLiteDatabase db = this.getWritableDatabase();
//	
//	    ContentValues values = new ContentValues(); 
//		values.put("nombre", producto.getNombre());
//		values.put("descripcion", producto.getDescripcion());
//		values.put("medida", producto.getMedida());
//		values.put("p_unitario", producto.getPUnitario());
//		values.put("cantidad", producto.getCantidad());
//		values.put("estado", producto.getEstado());
//	 
//	    // updating row
//	    return db.update(TABLE_PRODUCTO, values, "cod"+ " = ?",
//	            new String[] { String.valueOf(producto.getCod()) });
//	}
//	public void deleteContact(Producto producto) {
//	    SQLiteDatabase db = this.getWritableDatabase();
//	    db.delete(TABLE_PRODUCTO, "cod" + " = ?",
//	            new String[] { String.valueOf(producto.getCod()) });
//	    db.close();
//	}
/////////////////////
//	/**
//	 * Storing user details in database
//	 * */
//	public void addUser(String name, String email, String uid, String created_at) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
///*		values.put(KEY_NAME, name); // Name
//		values.put(KEY_EMAIL, email); // Email
//		values.put(KEY_UID, uid); // Email
//		values.put(KEY_CREATED_AT, created_at); // Created At
//*/
//		// Inserting Row
//		db.insert(TABLE_LOGIN, null, values);
//		db.close(); // Closing database connection
//	}
//
//	/**
//	 * Getting user data from database
//	 * */
//	public HashMap<String, String> getUserDetails() {
//		HashMap<String, String> user = new HashMap<String, String>();
//		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
//
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(selectQuery, null);
//		// Move to first row
//		cursor.moveToFirst();
//		if (cursor.getCount() > 0) {
//			user.put("name", cursor.getString(1));
//			user.put("email", cursor.getString(2));
//			user.put("uid", cursor.getString(3));
//			user.put("created_at", cursor.getString(4));
//		}
//		cursor.close();
//		db.close();
//		// return user
//		return user;
//	}
//
//	/**
//	 * Getting user login status return true if rows are there in table
//	 * */
//	public int getRowCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		int rowCount = cursor.getCount();
//		db.close();
//		cursor.close();
//
//		// return row count
//		return rowCount;
//	}
//	
//
//
//	/**
//	 * Re crate database Delete all tables and create them again
//	 * */
//	public void resetTables() {
//		SQLiteDatabase db = this.getWritableDatabase();
//		// Delete All Rows
//		db.delete(TABLE_LOGIN, null, null);
//		db.close();
//	}

}
