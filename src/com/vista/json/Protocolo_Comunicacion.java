package com.vista.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.os.AsyncTask;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.vista.util.AlertDialogManager_Progres;

public class Protocolo_Comunicacion {

	private static Protocolo_Comunicacion instance = new Protocolo_Comunicacion();

	private int MODO_HILO = -1;
	private int MODO_PUENTE = -1;
	public final static int MODO_NNGUNO = -1;
	public final static int MODO_ENVIOPEDIDO = 0;
	public final static int MODO_UPDATEBD = 1;
	public final static int MODO_DESCARGAIMAGENES = 2;
	public final static int MODO_LOGINJSON = 3;
	public final static int MODO_LOGINBACKPRESS = 4;
	public final static int MODO_BARRIOS = 5;
	public boolean sw = true;
	private ParserFunction function;
	private Activity context;
	private ProgressDialog dialog;

	public Protocolo_Comunicacion() {
	}

	public static Protocolo_Comunicacion getInstance() {
		return instance;
	}

	public String getBarrios(Activity context, String latitud, String longitud) {
		this.MODO_HILO = MODO_BARRIOS;
		this.MODO_PUENTE = MODO_BARRIOS;
		this.context = context;
		String latitud_longitud = latitud + ":" + longitud;
		return getBarriosWeb(latitud_longitud);
	}

	private String getBarriosWeb(String latitud_longitud) {

		System.out.println("CONSTRUCCION getBarriosWeb");
		dialog = AlertDialogManager_Progres.showProgreDialog(context,
				"Obteniendo Informacion...", "Barrios",
				AlertDialogManager_Progres.STYLE_SPINNER);
		dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				// function.cancelar();
			}
		});

		System.out.println("dialog getBarriosWeb");
		// MiTarea mitarea = new MiTarea();
		new MiTarea().execute(latitud_longitud);
		return "";
	}

	// ////////////////Float
	private class MiTarea extends AsyncTask<String, Integer, String> {

		protected void onPreExecute() {

			dialog.setProgress(0);
			dialog.setMax(100);
			dialog.show(); // Mostramos el diálogo antes de comenza
		}

		protected String doInBackground(String... params) {

			System.out.println("modo " + params[0] + " doInBackground");
			switch (MODO_PUENTE) {
			case MODO_ENVIOPEDIDO:
				break;
			case MODO_UPDATEBD:
				break;
			case MODO_DESCARGAIMAGENES:
				break;
			case MODO_LOGINJSON:
				break;
			case MODO_LOGINBACKPRESS:
				break;
			case MODO_BARRIOS:
				System.out.println("ENTRO modo " + params[0]
						+ " doInBackground");

				try {

					function = new ParserFunction();
					JSONObject jsonObject = function.getBarrios(params[0]);
					// function.LoginUser("a", "a");
					// Thread.sleep(1000 * 5);
					String cod = jsonObject.getString("id");
					String res2 = jsonObject.getString("nom");
					String res3 = jsonObject.getString("polig");
					List<LatLng> list = decodePoly(res3);
					System.out.println("Mi list:  " + list);
					System.out
							.println("cod   " + cod + "   poligono   " + res3);
				} catch (ClientProtocolException e) {
					sw = false;
					puente_error.sendMessage(new Message());
					e.printStackTrace();
				} catch (IOException e) {
					sw = false;
					puente_error.sendMessage(new Message());
					e.printStackTrace();
				} catch (JSONException e) {
					sw = false;
					puente_error.sendMessage(new Message());
					e.printStackTrace();
				}
				// catch (InterruptedException e) {
				// // TODO Auto-generated catch block
				// puente_error.sendMessage(new Message());
				// e.printStackTrace();
				// }
				MODO_PUENTE = MODO_NNGUNO;
				break;
			default:
				break;
			}

			return "Hola";
		}

		protected void onProgressUpdate(Integer... valores) {
			// int p = Math.round(100 * valores[0]);
			dialog.setProgress(valores[0]);
			System.out.println("onProgressUpdate " + valores[0]);

		}

		protected void onPostExecute(String respuestaweb) {
			dialog.dismiss();
			System.out.println("onPostExecute ");
		}
	}

	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	/*
	 * 
	 * */
	private Handler puente_error = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (!sw) {
				// Toast.makeText(context, "Verifique su conexion a internet",
				// Toast.LENGTH_LONG).show();
				sw = !sw;
			}
		}
	};

	// /////////////

	// @Override
	// public boolean onKey(View v, int keyCode, KeyEvent event) {
	// // TODO Auto-generated method stub
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// // sw = true;
	// return true;
	// } else {
	// return false;
	// }
	// }

}
