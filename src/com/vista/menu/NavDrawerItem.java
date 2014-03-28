package com.vista.menu;

import com.vista.adapter.AdapterPerfil;

public class NavDrawerItem {

	private String title="";
	private int icon;
	private String count = "0";
	// boolean to set visiblity of the counter
	public boolean isvisible = false;
	public int tipo = 0;

	public NavDrawerItem() {
		this.tipo = 0;
	}

	public NavDrawerItem(String title, int icon) {
		this.title = title;
		this.icon = icon;
		this.tipo = 0;
	}

	public NavDrawerItem(String title, int icon, boolean isCounterVisible,
			String count) {
		this.title = title;
		this.icon = icon;
		this.isvisible = isCounterVisible;
		this.count = count;
		this.tipo = 0;
	}

	public String getTitle() {
		return this.title;
	}

	public int getIcon() {
		return this.icon;
	}

	public String getCount() {
		return this.count;
	}

	public boolean getVisible() {
		return this.isvisible;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public void setvisible(boolean isCounterVisible) {
		this.isvisible = isCounterVisible;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int type) {
		this.tipo = type;
	}

}
