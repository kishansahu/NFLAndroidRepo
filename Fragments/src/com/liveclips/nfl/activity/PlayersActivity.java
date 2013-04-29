package com.liveclips.nfl.activity;

import com.liveclips.nfl.R;
import com.liveclips.nfl.fragment.MainMenuFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Spinner;

public class PlayersActivity extends Activity {

	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_players);
		fragmentManager = getFragmentManager();
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { MenuInflater
	 * inflater = getMenuInflater();
	 * inflater.inflate(R.menu.players_menu_actionbar, menu); MenuItem menuItem
	 * = menu.findItem(R.id.spinnerMenuItem); Spinner spinnerMenuItem =
	 * (Spinner) menuItem.getActionView(); spinnerMenuItem.setSelection(1);
	 * MenuItem sliderMenuItem = menu.findItem(R.id.sliderButton);
	 * sliderMenuItem.setVisible(true); return true; }
	 */

	@Override
	protected void onStart() {
		super.onStart();
		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.players_actionbar_layout, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF8B1D));
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		View sliderView = mActionBarView.findViewById(R.id.sliderView);
		sliderView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				performSliderAction();

			}
		});

	}

	private void performSliderAction() {

		ft = fragmentManager.beginTransaction();
		mainMenuFragment = new MainMenuFragment();
		ft.replace(R.id.menuFragment, mainMenuFragment);
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.sliderButton:
			performSliderAction();
			return true;
		}
		return false;
	}
}
