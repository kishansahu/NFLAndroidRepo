package com.liveclips.nfl.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.liveclips.nfl.R;

public class AppContentActivity extends Activity {

	FragmentManager fragmentManager;
	Fragment maninMenuFragment;
	FragmentTransaction ft;
	ImageButton sliderButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		maninMenuFragment = fragmentManager
				.findFragmentById(R.id.mainMenuFragment);
		// maninMenuFragment.setHasOptionsMenu(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.sliderButton:
			performSliderAction();
			return true;
			/*
			 * case R.id.help: showHelp(); return true;
			 */
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void performSliderAction() {

		ft = fragmentManager.beginTransaction();

		if (maninMenuFragment.isVisible()) {

			ft.hide(maninMenuFragment);

			/*
			 * Toast.makeText(AppContentActivity.this, "button clicked visible",
			 * Toast.LENGTH_SHORT) .show();
			 */

		} else {

			ft.show(maninMenuFragment);

			ft.addToBackStack(null);

		}

		ft.commit();

	};

}
