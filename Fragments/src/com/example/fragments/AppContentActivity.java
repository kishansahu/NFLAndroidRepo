package com.example.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class AppContentActivity extends Activity {

	FragmentManager fm;
	Fragment frag;
	FragmentTransaction ft;
	View b;
	ImageButton sliderButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sliderButton = (ImageButton) findViewById(R.id.sliderButton);
		sliderButton.setOnClickListener(globalNavigationListener);

		b = findViewById(R.id.fragment1);
		fm = getFragmentManager();
		frag = fm.findFragmentById(R.id.fragment1);

	}

	private OnClickListener globalNavigationListener = new OnClickListener() {
		public void onClick(View v) {
			ft = fm.beginTransaction();

			if (frag.isVisible()) {

				ft.hide(frag);
				// ft.addToBackStack(null);
				/*
				 * Toast.makeText(AppContentActivity.this,
				 * "button clicked visible", Toast.LENGTH_SHORT) .show();
				 */

			} else {

				ft.show(frag);

			}

			ft.commit();

		}
	};
}
