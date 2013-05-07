package com.liveclips.nfl.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.fragment.AddPlayersFragment;
import com.liveclips.nfl.fragment.MainMenuFragment;

public class PlayersActivity extends Activity {

	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	Fragment addPlayersFragment;
	FragmentTransaction ft;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_players);
		fragmentManager = getFragmentManager();

	}

	
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
		String[] playersName = { };

		/**
		 * include a xml multiple time in a parent xml
		 */

		LinearLayout wrapper = (LinearLayout) findViewById(R.id.myPlayersContainer);
		LinearLayout inflatedView;

		if (playersName.length != 0) {
			int i;
			for (i = 0; i < playersName.length; i++) {

				inflatedView = (LinearLayout) View.inflate(this,
						R.layout.players_detail, null);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerName))
						.setText(playersName[i]);
				wrapper.addView(inflatedView);

			}
		} else {
			ImageView allMyPlayersIcon = (ImageView) findViewById(R.id.allmyplayersIcon);
			allMyPlayersIcon.setVisibility(View.GONE);
			inflatedView = (LinearLayout) View.inflate(this,
					R.layout.empty_myplayers_banner, null);
			wrapper.addView(inflatedView);

		}

		ImageButton addaplayerButton = (ImageButton) findViewById(R.id.addaplayerButton);
		addaplayerButton.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				showAddPlayersFragment();
				return true;
			}
		});

		sliderView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				performSliderAction();

			}
		});

	}

	public void showAddPlayersFragment() {

		ft = fragmentManager.beginTransaction();
		addPlayersFragment = new AddPlayersFragment();
		ft.replace(R.id.menuFragment, addPlayersFragment);
		ft.commit();

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
