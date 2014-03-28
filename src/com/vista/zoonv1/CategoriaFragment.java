/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vista.zoonv1;

import java.util.Random;

import com.vista.zoonv1.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class CategoriaFragment extends Fragment {

	private static final String ARG_POSITION = "position";

	private final int[] _IMG_SUBCAT = { R.drawable.bus, R.drawable.car,
			R.drawable.cart, R.drawable.briefcase, R.drawable.creditcard,
			R.drawable.crossroads, R.drawable.cursor, R.drawable.dolly,
			R.drawable.fashion, R.drawable.gamecontroller, R.drawable.map };

	private int _position;

	public static CategoriaFragment newInstance(int position) {
		CategoriaFragment f = new CategoriaFragment();
		Bundle b = new Bundle();
		b.putInt(ARG_POSITION, position);
		f.setArguments(b);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		_position = getArguments().getInt(ARG_POSITION);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.categoriafragment, container,
				false);
		LinearLayout layout = (LinearLayout) rootView
				.findViewById(R.id.categoria_linerlayout);
		// ///////////////////////////////////////////

		// LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT);

		// FrameLayout fl = new FrameLayout(getActivity());
		// fl.setLayoutParams(params);

		// final int margin = (int) TypedValue.applyDimension(
		// TypedValue.COMPLEX_UNIT_DIP, 1, getResources()
		// .getDisplayMetrics());

		// TextView v = new TextView(getActivity());
		// params.setMargins(margin, margin, margin, margin);
		// v.setLayoutParams(params);
		// v.setLayoutParams(params);
		// v.setGravity(Gravity.CENTER);
		// v.setBackgroundResource(R.drawable.background_card);
		// v.setText("Aqui poner una listta de item:    Nro " + (position + 1));
		// ScrollView scrollView = new ScrollView(getActivity());
		int min = 0;
		int max = 10;
		Random r = new Random();

		for (int i = 0; i < _IMG_SUBCAT.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			int i1 = r.nextInt(max - min + 1) + min;
			if (i1 % 2 == 0) {

				imageView.setImageResource(_IMG_SUBCAT[i]);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						60, 60);
				params.setMargins(5, 20, 5, 3);
				imageView.setLayoutParams(params);
				layout.addView(imageView);

			}
		}

		// fl.addView(scrollView);
		return rootView;
	}

}