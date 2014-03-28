package com.vista.zoonv1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class SplashScreenFragment extends Fragment {

	ProgressBar progressBar;
	protected boolean mbActive;
	View rootView;
	protected static final int TIMER_RUNTIME = 3000; // in ms --> 10s

	public SplashScreenFragment() {

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_splash_screen, container,
				false);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar1);
		hilo();
		return rootView;
	}

	// ////////Hilo
	public void hilo() {
		final Thread timerThread = new Thread() {
			@Override
			public void run() {
				mbActive = true;
				try {
					int waited = 0;
					while (mbActive && (waited < TIMER_RUNTIME)) {
						sleep(100);
						if (mbActive) {
							waited += 100;
							updateProgress(waited);
						}
					}
				} catch (InterruptedException e) {
					// do nothing
				} finally {
					onContinue();
				}
			}
		};
		timerThread.start();
	}

	public void updateProgress(final int timePassed) {
		if (null != progressBar) {
			// Ignore rounding error here
			final int progress = progressBar.getMax() * timePassed
					/ TIMER_RUNTIME;
			progressBar.setProgress(progress);
		}
	}

	public void onContinue() {
		// perform any final actions here
	}

}
