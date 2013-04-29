/**
 * 
 */
package com.liveclips.nfl.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.utils.DownloadImagesThreadPool;

/**
 * @author mohitkumar
 * 
 */
public class NFLHighlightsActivity extends Activity {

	private VideoView playCardVideoView;

	private ArrayList<String> playCardTopDetail = new ArrayList<String>();

	private ArrayList<String> playCardBottomDetail = new ArrayList<String>();

	private Context context;

	private int playCardVideoId = 0;

	private DownloadImagesThreadPool downloadImagesThreadPool;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_nfl_highlights);

		context = this;

		downloadImagesThreadPool = new DownloadImagesThreadPool();

		prepareVideoView();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.nfl_highlights_menu_actionbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.nfl_highlights:

			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * private void performSliderAction() { Log.d("slider", "clickee"); ft =
	 * fragmentManager.beginTransaction(); mainMenuFragment = new
	 * MainMenuFragment();
	 * 
	 * ft.replace(R.id.menuFragment, mainMenuFragment); ft.commit(); }
	 */
	private void prepareVideoView() {

		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		playCardParentLinearLayout.removeAllViews();

		for (int index = 0; index < 4; index++) {

			playCardTopDetail.add("Video Text on Top " + index);

			playCardBottomDetail.add("Video Text on Bottom " + index);

			final RelativeLayout playCardLayout = getPlayCardLayout(index, this);

			final ImageView playCardImageView = getPlayCardImageView();

			playCardLayout.addView(playCardImageView);

			playCardImageView.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(final View v, MotionEvent arg1) {
					// Creating a new VideoView VideoView

					if (playCardVideoView != null
							&& playCardVideoView.isPlaying()) {
						playCardVideoView.stopPlayback();
						playCardVideoView.setVisibility(View.INVISIBLE);
						// v.setVisibility(View.VISIBLE);
					}
					playCardVideoView = new VideoView(context);
					playCardLayout.addView(playCardVideoView);
					// Defining the layout parameters of the VideoView
					RelativeLayout.LayoutParams layoutParametersForPlayCardVideoView = new RelativeLayout.LayoutParams(
							250, 150);
					layoutParametersForPlayCardVideoView
							.addRule(RelativeLayout.CENTER_IN_PARENT);

					playCardVideoView.setId(playCardVideoId++);

					MediaController mediaController = new MediaController(
							context);
					mediaController.setAnchorView(playCardVideoView);
					playCardVideoView.setMediaController(mediaController);

					playCardVideoView
							.setLayoutParams(layoutParametersForPlayCardVideoView);

					// String path =
					// "http://x.pio.lc/nfl/week05/20121009_001_20121011115406_027_3_241b_d02a361b.3gp";
					String path = "http://commonsware.com/misc/test2.3gp";
					playCardVideoView.setVideoURI(Uri.parse(path));
					playCardVideoView.setZOrderOnTop(false);
					v.setVisibility(View.INVISIBLE);
					playCardVideoView
							.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

								@Override
								public void onCompletion(MediaPlayer vmp) {
									// playCardImageView.setVisibility(View.VISIBLE);
									playCardVideoView
											.setVisibility(View.INVISIBLE);
									v.setVisibility(View.VISIBLE);

								}
							});
					playCardVideoView.start();

					return false;
				}
			});

			// Creating a new bottomTextView
			TextView playCardBottomTextView = new TextView(this);
			playCardBottomTextView.setText(playCardBottomDetail.get(index));
			// Defining the layout parameters of the TextView
			RelativeLayout.LayoutParams layoutParametersForPlayCardBottomTextView = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			layoutParametersForPlayCardBottomTextView
					.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			// Setting the parameters on the TextView
			playCardBottomTextView
					.setLayoutParams(layoutParametersForPlayCardBottomTextView);
			// Adding the TextView to the RelativeLayout as a child
			playCardLayout.addView(playCardBottomTextView);

			playCardParentLinearLayout.addView(playCardLayout);

		}

	}

	private ImageView getPlayCardImageView() {

		ImageView playCardImageView = new ImageView(this);

		RelativeLayout.LayoutParams layoutParameterForPlayCardImageView = new RelativeLayout.LayoutParams(
				250, 150);
		layoutParameterForPlayCardImageView
				.addRule(RelativeLayout.CENTER_IN_PARENT);

		playCardImageView.setLayoutParams(layoutParameterForPlayCardImageView);

		downloadImagesThreadPool
				.submit(playCardImageView,
						"http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg");// .execute("http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg");

		return playCardImageView;
	}

	private RelativeLayout getPlayCardLayout(int index,
			NFLHighlightsActivity nflHighlightsActivity) {
		RelativeLayout playCardLayout = new RelativeLayout(this);

		RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
				300, 250);
		playCardLayoutParameters.addRule(RelativeLayout.CENTER_IN_PARENT);
		playCardLayoutParameters.setMargins(10, 10, 10, 10);

		playCardLayout.setLayoutParams(playCardLayoutParameters);

		if (index % 2 == 0) {
			playCardLayout.setBackgroundColor(getResources().getColor(
					R.color.orange));
		} else {
			playCardLayout.setBackgroundColor(getResources().getColor(
					R.color.green));
		}

		playCardLayout.setPadding(5, 5, 5, 5);
		// Adding the TextView to the RelativeLayout as a child
		playCardLayout.addView(getPlayCardTopTextView(index));
		return playCardLayout;
	}

	private TextView getPlayCardTopTextView(int index) {

		TextView playCardTopTextView = new TextView(this);
		playCardTopTextView.setText(playCardTopDetail.get(index));

		// Defining the layout parameters of the TextView
		RelativeLayout.LayoutParams layoutParametersForPlayCardTopTextView = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		layoutParametersForPlayCardTopTextView
				.addRule(RelativeLayout.ALIGN_PARENT_TOP);

		// Setting the parameters on the TextView
		playCardTopTextView
				.setLayoutParams(layoutParametersForPlayCardTopTextView);
		return playCardTopTextView;
	}
}
