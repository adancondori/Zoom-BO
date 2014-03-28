package com.vista.GPSsingleton;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

public class Pedido_Singleton {
	private FragmentManager manager;
	private Activity activity;

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	private static Pedido_Singleton instance = new Pedido_Singleton();

	private Pedido_Singleton() {

	}

	public static Pedido_Singleton getInstance() {
		return instance;
	}

	public static void setInstance(Pedido_Singleton instance) {
		Pedido_Singleton.instance = instance;
	}

	/**
	 * @return the manager
	 */
	public FragmentManager getManager() {
		return manager;
	}

	/**
	 * @param manager
	 *            the manager to set
	 */
	public void setManager(FragmentManager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Pedido Singleton Codigo de promotor" + String.valueOf("hoal");
	}

}
