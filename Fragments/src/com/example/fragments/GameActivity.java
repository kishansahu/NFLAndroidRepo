package com.example.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Toast;

public class GameActivity extends Activity {

	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;
	ImageButton sliderButton;
	ImageButton closeButtonHeader;
	TextView headerTextView;
	boolean showSlider;

	TextView allPlaysTextView;
	TextView topPlaysTextView;
	TextView topRatedTextView;
	TextView watchAllTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		fragmentManager = getFragmentManager();
		mainMenuFragment = fragmentManager
				.findFragmentById(R.id.topicMenuFragment);
		headerTextView = (TextView) findViewById(R.id.menuHeader);
		headerTextView.setText("LiveClips");
		closeButtonHeader = (ImageButton) findViewById(R.id.closeButtonHeader);
		closeButtonHeader.setVisibility(View.VISIBLE);
		closeButtonHeader.setOnClickListener(closeButtonListener);

		allPlaysTextView = (TextView) findViewById(R.id.allPlaysId);
		topPlaysTextView = (TextView) findViewById(R.id.topPlaysId);
		topRatedTextView = (TextView) findViewById(R.id.topRatedId);
		watchAllTextView = (TextView) findViewById(R.id.watchAllId);
		
		
		allPlaysTextView.setOnClickListener(allPlaysClickListener);
		topPlaysTextView.setOnClickListener(topPlaysCilckListener);
		topRatedTextView.setOnClickListener(topRatedClickListener);
		watchAllTextView.setOnClickListener(watchAllClickListener);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_menu_actionbar, menu);
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

		/*
		 * ft = fragmentManager.beginTransaction();
		 * 
		 * if (maninMenuFragment.isVisible()) {
		 * 
		 * ft.hide(maninMenuFragment);
		 * 
		 * 
		 * Toast.makeText(AppContentActivity.this, "button clicked visible",
		 * Toast.LENGTH_SHORT) .show();
		 * 
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
		 */

	};

	private OnClickListener closeButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			ft = fragmentManager.beginTransaction();

			if (mainMenuFragment.isVisible()) {

				ft.hide(mainMenuFragment);
				showSlider = true;
				invalidateOptionsMenu();
				/*
				 * Toast.makeText(AppContentActivity.this,
				 * "button clicked visible", Toast.LENGTH_SHORT) .show();
				 */

			} else {

				ft.show(mainMenuFragment);

				ft.addToBackStack(null);

			}

			ft.commit();

		}

	};

	public boolean onPrepareOptionsMenu(Menu menu) {
		if (showSlider) {
			MenuItem menuItem = menu.add(0, Menu.FIRST, 0, "Slider");
			menuItem.setIcon(R.drawable.pic4);
		}
		return super.onPrepareOptionsMenu(menu);
	};

	private OnClickListener allPlaysClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// allPlaysTextView.setBackgroundColor(2);
			allPlaysTextView.setBackgroundResource(R.color.white);
			
			topPlaysTextView.setBackgroundResource(R.color.orange);
			topRatedTextView.setBackgroundResource(R.color.orange);
			watchAllTextView.setBackgroundResource(R.color.orange);
			
			Toast.makeText(GameActivity.this, "all plays button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};
	
	private OnClickListener topPlaysCilckListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			topPlaysTextView.setBackgroundResource(R.color.white);
			
			
			allPlaysTextView.setBackgroundResource(R.color.orange);
			topRatedTextView.setBackgroundResource(R.color.orange);
			watchAllTextView.setBackgroundResource(R.color.orange);
			
			Toast.makeText(GameActivity.this, "top plays button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};
	
	private OnClickListener topRatedClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			
			topRatedTextView.setBackgroundResource(R.color.white);
			
			
			allPlaysTextView.setBackgroundResource(R.color.orange);
			topPlaysTextView.setBackgroundResource(R.color.orange);
			watchAllTextView.setBackgroundResource(R.color.orange);
			
			Toast.makeText(GameActivity.this, "top Rated button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};
	
	private OnClickListener watchAllClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			watchAllTextView.setBackgroundResource(R.color.white);
			
			allPlaysTextView.setBackgroundResource(R.color.orange);
			topPlaysTextView.setBackgroundResource(R.color.orange);
			topRatedTextView.setBackgroundResource(R.color.orange);
			
			Toast.makeText(GameActivity.this, "watch all button clicked",
					Toast.LENGTH_SHORT).show();
		}
	};
	
	
}
