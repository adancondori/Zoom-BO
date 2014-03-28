package com.vista.GPSsingleton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;

public class GPS extends Service implements
// OnMyLocationChangeListener,
		LocationListener {

	private Context mContext = null;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	Location location; // location
	double latitude; // latitude
	double longitude; // longitude
	boolean currentubication = false;
	GoogleMap map = null;
	float zoomcurrent = 18;
	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 10 * 5; // 1
																		// minute

	// Declaring a Location Manager
	protected LocationManager locationManager;

	private static GPS instance = new GPS();

	private GPS() {

	}

	public static GPS getInstance() {
		return instance;
	}

	public static void setInstance(GPS instance) {
		GPS.instance = instance;
	}

	public GPS(Context context, GoogleMap map) {
		this.mContext = context;
		// getLocation();
		this.map = map;
	}

	/**
	 * @return the zoomcurrent
	 */
	public float getZoomcurrent() {
		return zoomcurrent;
	}

	/**
	 * @param zoomcurrent
	 *            the zoomcurrent to set
	 */
	public void setZoomcurrent(float zoomcurrent) {
		this.zoomcurrent = zoomcurrent;
	}

	/**
	 * @return the currentubication
	 */
	public boolean isCurrentubication() {
		return currentubication;
	}

	/**
	 * @param currentubication
	 *            the currentubication to set
	 */
	public void setCurrentubication(boolean currentubication) {
		this.currentubication = currentubication;
	}

	// @Override
	// public void onMyLocationChange(Location location) {
	// TODO Auto-generated method stub
	// Getting latitude of the current location
	// double latitude = location.getLatitude();
	// // Getting longitude of the current location
	// double longitude = location.getLongitude();
	// // Creating a LatLng object for the current location
	// LatLng latLng = new LatLng(latitude, longitude);
	// if (currentubication) {
	// System.out.println("GPS Atitude:   " + location.getAltitude());
	// System.out.println("GPS: Speed:  " + location.getSpeed());
	//
	//
	// // Showing the current location in Google Map
	// map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
	// // Zoom in the Google Map
	// map.animateCamera(CameraUpdateFactory.zoomTo(GPS.getInstance().getZoomcurrent()));
	// System.out.println("GPS:   " + currentubication);
	// }

	// }

	public Location getLocation() {
		try {
			locationManager = (LocationManager) mContext
					.getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				this.canGetLocation = true;

				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				} else if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	/**
	 * Stop using GPS listener Calling this function will stop using GPS in your
	 * app
	 * */
	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager.removeUpdates(GPS.this);
		}
	}

	/**
	 * Function to get latitude
	 * */
	public double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}

		// return latitude
		return latitude;
	}

	/**
	 * Function to get longitude
	 * */
	public double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}
		// return longitude
		return longitude;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * 
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	/**
	 * Function to show settings alert dialog On pressing Settings button will
	 * lauch Settings Options
	 * */
	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

		// Setting Dialog Title
		alertDialog.setTitle("GPS is settings");

		// Setting Dialog Message
		alertDialog
				.setMessage("GPS is not enabled. Do you want to go to settings menu?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						mContext.startActivity(intent);
					}
				});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		// Showing Alert Message
		alertDialog.show();
	}

	@Override
	public void onLocationChanged(Location location) {
		// Getting latitude of the current location
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();

	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
