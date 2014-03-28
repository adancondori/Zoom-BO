package com.vista.util;

import java.util.ArrayList;

import com.vista.zoonv1.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlertDialogManager_Progres {
	/**
	 * Function to display simple Alert Dialog
	 * 
	 * @param context
	 *            - application context
	 * @param title
	 *            - alert dialog title
	 * @param message
	 *            - alert message
	 * @param status
	 *            - success/failure (used to set icon) - pass null if you don't
	 *            want icon
	 * */
	public static void showAlertDialog(Activity activity, String title,
			String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		if (status != null)
			// Setting alert dialog icon
			alertDialog
					.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// alertDialog.dismiss();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	public static int dialogo(Context context, String title,
			ArrayList<String> arrayList2, ListView modeList) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);

		// ListView modeList = new ListView(context);
		modeList.setBackgroundColor(Color.WHITE);
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, android.R.id.text1,
				arrayList2);

		modeList.setAdapter(modeAdapter);

		builder.setView(modeList);
		final Dialog dialog = builder.create();
		int pos = 0;
		dialog.show();
		modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			int h = 0;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				dialog.dismiss();

			}
		});
		return 0;
	}

	/*
	 * Progres Dialog acc
	 */
	public static int STYLE_SPINNER = ProgressDialog.STYLE_SPINNER;
	public static int STYLE_HORIZONTAL = ProgressDialog.STYLE_HORIZONTAL;

	public static ProgressDialog showProgreDialog(Activity context,
			String Mensage, String Title, int Tipo) {
		ProgressDialog dialog;
		dialog = new ProgressDialog(context);
		dialog.setMessage(Mensage);
		dialog.setTitle(Title);
		// switch (Tipo) {
		// case ProgressDialog.STYLE_SPINNER:
		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		// break;
		// case ProgressDialog.STYLE_HORIZONTAL:
		// dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		// break;
		// default:
		// break;
		// }
		dialog.setCancelable(true);
		return dialog;
	}

}
