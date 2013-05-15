package com.liveclips.nfl.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.fragment.MainMenuFragment;
import com.liveclips.nfl.fragment.TopicMenuFragment;
import com.liveclips.nfl.session.ApplicationSession;

public class AppContentActivity extends Activity {

	FragmentManager fragmentManager;
	public Fragment mainMenuFragment;
	FragmentTransaction ft;
	ImageView sliderBtnImageView;
	ImageView closeBtnImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fragmentManager = getFragmentManager();
		ft = fragmentManager.beginTransaction();
		mainMenuFragment = new MainMenuFragment();
		((ApplicationSession) AppContentActivity.this.getApplication())
				.setMainMenuFragment(mainMenuFragment);
		ft.add(R.id.menuFragment, mainMenuFragment);
		ft.commit();

	}

	@Override
	protected void onStart() {
		super.onStart();
		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.app_actionbar_layout, null);
		actionBar.setCustomView(mActionBarView);
		Drawable d = getResources().getDrawable(R.drawable.orange_background);
		actionBar.setBackgroundDrawable(d);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

	}

	@Override
	protected void onResume() {
		super.onResume();
		closeBtnImageView = (ImageView) findViewById(R.id.closeButtonHeader);
		closeBtnImageView.setOnClickListener(closeButtonListener);
		sliderBtnImageView = (ImageView) findViewById(R.id.sliderView);
		sliderBtnImageView.setOnClickListener(sliderButtonListener);
		// TopicMenuFragment.selectedIndex = -1;
	}

	private OnClickListener closeButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("closeclcik", "closeclickonamainmenu");
			/*
			 * Fragment mainMenuFragment = ((AppContentActivity)
			 * getActivity()).mainMenuFragment; FragmentManager fragmentManager
			 * = getFragmentManager(); FragmentTransaction ft =
			 * fragmentManager.beginTransaction(); ft.hide(mainMenuFragment);
			 * ft.commit();
			 */
			View view = findViewById(R.id.menuFragment);
			view.setVisibility(View.INVISIBLE);
			View fragmentMenuHeader = findViewById(R.id.fragmentMenuHeaderForAppStart);
			fragmentMenuHeader.setVisibility(View.GONE);
			View sliderView = findViewById(R.id.sliderView);
			sliderView.setVisibility(View.VISIBLE);

		}

	};

	private OnClickListener sliderButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("closeclcik", "closeclickonamainmenu");

			View fragmentMenuHeader = findViewById(R.id.fragmentMenuHeaderForAppStart);
			if (fragmentMenuHeader.getVisibility() == View.VISIBLE) {
				fragmentMenuHeader.setVisibility(View.GONE);
				View sliderView = findViewById(R.id.sliderView);
				sliderView.setVisibility(View.VISIBLE);
				View view = findViewById(R.id.menuFragment);
				view.setVisibility(View.INVISIBLE);
			} else {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();

				ApplicationSession session = ((ApplicationSession) AppContentActivity.this
						.getApplication());
				Fragment mainMenuFragment = session.getMainMenuFragment();

				ft.replace(R.id.menuFragment, mainMenuFragment);
				ft.commit();
				TextView menuTitle = (TextView) findViewById(R.id.menuTitle);
				menuTitle.setText("Menu");
				fragmentMenuHeader.setVisibility(View.VISIBLE);
				closeBtnImageView.setVisibility(View.INVISIBLE);
				View view = findViewById(R.id.menuFragment);
				view.setVisibility(View.VISIBLE);
			}
		}

	};

	/*
	 * private void performSliderAction() {
	 * 
	 * ft = fragmentManager.beginTransaction();
	 * 
	 * if (maninMenuFragment.isVisible()) {
	 * 
	 * ft.hide(maninMenuFragment);
	 * 
	 * } else {
	 * 
	 * ft.show(maninMenuFragment);
	 * 
	 * ft.addToBackStack(null);
	 * 
	 * }
	 * 
	 * ft.commit();
	 * 
	 * };
	 */
}
