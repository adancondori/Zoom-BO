package com.vista.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.text.Html;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.view.WindowManager;

public class Utils {
	/**
	 * Take screenshot of the activity including the action bar
	 * 
	 * @param activity
	 * @return The screenshot of the activity including the action bar
	 */
	public static Bitmap takeScreenshot(Activity activity) {
		ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
		ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
		decorChild.setDrawingCacheEnabled(true);
		decorChild.buildDrawingCache();
		Bitmap drawingCache = decorChild.getDrawingCache(true);
		Bitmap bitmap = Bitmap.createBitmap(drawingCache);
		decorChild.setDrawingCacheEnabled(false);
		return bitmap;
	}

	/**
	 * Print hash key
	 */
	public static void printHashKey(Context context) {
		try {
			// String TAG = "com.sromku.simple.fb.example";
			String TAG = "com.vista.zoonv1";
			PackageInfo info = context.getPackageManager().getPackageInfo(TAG,
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				String keyHash = Base64.encodeToString(md.digest(),
						Base64.DEFAULT);
				Log.d(TAG, "acc keyHash: " + keyHash);
				System.out.println("acc keyHash: " + keyHash);
			}
		} catch (NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {

		}
	}

	/**
	 * Update language
	 * 
	 * @param code
	 *            The language code. Like: en, cz, iw, ...
	 */
	public static void updateLanguage(Context context, String code) {
		Locale locale = new Locale(code);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		context.getResources().updateConfiguration(config,
				context.getResources().getDisplayMetrics());
	}

	/**
	 * Build alert dialog with properties and data
	 * 
	 * @param pairs
	 * @return {@link AlertDialog}
	 */
	public static AlertDialog buildProfileResultDialog(Activity activity,
			Pair<String, String>... pairs) {
		StringBuilder stringBuilder = new StringBuilder();

		for (Pair<String, String> pair : pairs) {
			stringBuilder.append(String.format("<h3>%s</h3>", pair.first));
			stringBuilder.append(String.valueOf(pair.second));
			stringBuilder.append("<br><br>");
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage(Html.fromHtml(stringBuilder.toString()))
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog alertDialog = builder.create();
		return alertDialog;
	}

	/**
	 * Bitmap
	 * 
	 * @param pairs
	 * @return {@link AlertDialog}
	 */
	public static Bitmap getCroppedBitmap(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		// canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
				bitmap.getWidth() / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		// Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
		// return _bmp;
		return output;
	}

	public static Display getsizeScreem(Context context) {
		Display display = ((WindowManager) context
				.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay();
		return display;
	}

	public static float dpToPx(float dp) {
		return (dp * Resources.getSystem().getDisplayMetrics().density);
	}

	public static float pxToDp(float px) {
		return (px / Resources.getSystem().getDisplayMetrics().density);
	}

	public static float converNro_to_dpi(float nro) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, nro,
				Resources.getSystem().getDisplayMetrics());
	}
}
