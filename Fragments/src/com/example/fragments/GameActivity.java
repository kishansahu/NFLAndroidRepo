package com.example.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fragments.PopoverView.PopoverViewDelegate;

public class GameActivity extends Activity implements PopoverViewDelegate {

	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;
	ImageButton sliderButton;
	ImageButton closeButtonHeader;
	TextView headerTextView;
	boolean showSlider;
	ListView listView;
	List<MyCourseRowItem> rowItems;
	CourseListViewAdapter adapter;
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
		case R.id.game_drives_menu:
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);

			PopoverView popoverView = new PopoverView(this,
					R.layout.popover_showed_view);

			popoverView.setContentSizeForViewInPopover(new Point(320, 600));
			popoverView.setDelegate(this);
			View button = (View) findViewById(R.id.game_drives_menu);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			/*
			 * case R.id.help: showHelp(); return true;
			 */
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.i("POPOVER", "Will show : " + view.getChildCount());
		// ListView list = (ListView) view.getChildCount();
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1,FRUITS);
		// list.setAdapter(adapter);
	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		// View list = null;

		/*
		 * ListView list = (ListView) findViewById(R.id.my_lesson_list);
		 * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1,FRUITS);
		 * list.setAdapter(adapter);
		 */
		rowItems = new ArrayList<MyCourseRowItem>();
		// Log.d("size of array", String.valueOf(courseTitles.length));
		for (int i = 0; i < 2; i++) {
			MyCourseRowItem item = new MyCourseRowItem("jgbnj", "ngbng",
					"gngn", "gngn", "gngn", "ngng", "gngn", "");
			rowItems.add(item);

		}
		listView = (ListView) findViewById(R.id.my_lesson_list);
		adapter = new CourseListViewAdapter(this, R.layout.my_course_list_item,
				rowItems);
		listView.setAdapter(adapter);

		/*
		 * Log.i("POPOVER", "Did show : " + view.getChildCount()); for(int i=0;
		 * i<view.getChildCount(); i++) { Log.i("POPOVER",
		 * view.getChildAt(i).getClass().toString()); for(int j=0; j<
		 * ((ViewGroup)view.getChildAt(0)).getChildCount(); j++) {
		 * Log.i("Child",
		 * ((ViewGroup)view.getChildAt(0)).getChildAt(j).getClass().toString());
		 * 
		 * for(int k=0; k<
		 * ((ViewGroup)((ViewGroup)view.getChildAt(0)).getChildAt
		 * (0)).getChildCount(); k++) { Log.i("Child",
		 * ((ViewGroup)((ViewGroup)view
		 * .getChildAt(0)).getChildAt(0)).getChildAt(k).getClass().toString());
		 * }
		 * 
		 * } }
		 */
	}

	@Override
	public void popoverViewWillDismiss(PopoverView view) {
		Log.i("POPOVER", "Will dismiss");
	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {
		Log.i("POPOVER", "Did dismiss");
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
			menu.getItem(0).setVisible(true);
			showSlider = false;
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
