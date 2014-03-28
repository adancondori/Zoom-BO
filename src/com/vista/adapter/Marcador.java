package com.vista.adapter;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.vista.negocio.EventInfo;
import com.vista.zoonv1.R;

public class Marcador implements InfoWindowAdapter {
	private HashMap<Marker, EventInfo> eventMarkerMap;
	public Context context;

	public Marcador(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		eventMarkerMap = new HashMap<Marker, EventInfo>();
	}

	@Override
	public View getInfoContents(Marker marker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public View getInfoWindow(Marker marker) {
		// TODO Auto-generated method stub
		LayoutInflater li = LayoutInflater.from(context);
		final View window = li.inflate(R.layout.marcador, null);
		EventInfo eventInfo = eventMarkerMap.get(marker);

		String title = marker.getTitle();
		TextView txtTitle = ((TextView) window
				.findViewById(R.id.txtInfoWindowTitle));
		if (title != null) {
			// Spannable string allows us to edit the formatting
			// of the text.
			SpannableString titleText = new SpannableString(title);
			titleText.setSpan(new ForegroundColorSpan(Color.RED), 0,
					titleText.length(), 0);
			txtTitle.setText(titleText);
		} else {
			txtTitle.setText("");
		}

		TextView txtType = ((TextView) window
				.findViewById(R.id.txtInfoWindowEventType));
		if (eventInfo.getType() != null)
			txtType.setText(eventInfo.getType());

		return window;
	}

}
