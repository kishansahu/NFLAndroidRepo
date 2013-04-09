package com.example.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity{


	FragmentManager fragmentManager;
	Fragment maninMenuFragment;
	FragmentTransaction ft;
	ImageButton sliderButton;
    TextView headerTextView;
    ImageButton closeButtonHeader;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		fragmentManager = getFragmentManager();
		maninMenuFragment = fragmentManager.findFragmentById(R.id.topicMenuFragment);
		headerTextView = (TextView)findViewById(R.id.menuHeader);
        headerTextView.setText("LiveClips");
        closeButtonHeader = (ImageButton)findViewById(R.id.closeButtonHeader);
        closeButtonHeader.setVisibility(View.VISIBLE);
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
		/*case R.id.help:
			showHelp();
			return true;*/
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void performSliderAction() {
		
			ft = fragmentManager.beginTransaction();

			if (maninMenuFragment.isVisible()) {

				ft.hide(maninMenuFragment);

				/*
				 * Toast.makeText(AppContentActivity.this,
				 * "button clicked visible", Toast.LENGTH_SHORT) .show();
				 */

			} else {

				ft.show(maninMenuFragment);

				ft.addToBackStack(null);

			}

			ft.commit();

		
	};


}
