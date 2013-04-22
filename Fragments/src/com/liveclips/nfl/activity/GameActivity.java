package com.liveclips.nfl.activity;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.fragments.R;
import com.liveclips.nfl.adapter.DriveListViewAdapter;
import com.liveclips.nfl.adapter.PlayerListViewAdapter;
import com.liveclips.nfl.adapter.ScheduleListViewAdapter;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.adapter.StatsListViewAdapter;
import com.liveclips.nfl.model.DriveItem;
import com.liveclips.nfl.model.PlayerItem;
import com.liveclips.nfl.model.ScheduleItem;
import com.liveclips.nfl.model.StatsItem;
import com.liveclips.nfl.popover.PopoverView;
import com.liveclips.nfl.popover.PopoverView.PopoverViewDelegate;

public class GameActivity extends Activity implements PopoverViewDelegate {

	protected static final Context Context = null;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;
	ImageButton sliderButton;
	ImageButton closeButtonHeader;
	TextView headerTextView;
	boolean showSlider;
	ListView listView;
	PopoverView popoverView;
	PopoverView popoverViewDrive;
	TextView allPlaysTextView;
	TextView topPlaysTextView;
	TextView topRatedTextView;
	TextView watchAllTextView;
	RelativeLayout playCardLayout;
	VideoView playCardVideoView;
	ImageView playCardImageView;

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

		playCards();

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
		case R.id.game_schedule_menu:
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(this,
					R.layout.popover_game_schedule_view);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(this);
			View button = (View) findViewById(R.id.game_schedule_menu);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			return super.onOptionsItemSelected(item);
			/*
			 * case R.id.help: showHelp(); return true;
			 */
		case R.id.game_drives_menu:
			rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(this,
					R.layout.popover_game_drives_view);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(this);
			button = (View) findViewById(R.id.game_drives_menu);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);

			return super.onOptionsItemSelected(item);

		case R.id.game_stats_menu:

			rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(this,
					R.layout.popover_game_stats_view);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(this);
			button = (View) findViewById(R.id.game_stats_menu);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			return super.onOptionsItemSelected(item);

		case R.id.game_players_menu:
			rootView = (RelativeLayout) findViewById(R.id.gameRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(this,
					R.layout.popover_game_player_view);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(this);
			button = (View) findViewById(R.id.game_players_menu);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);

			return super.onOptionsItemSelected(item);

		}
		return super.onOptionsItemSelected(item);
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

		Log.d("layoutId", String.valueOf(view.getLayoutId()));
		if (view.getLayoutId() == R.layout.popover_game_schedule_view) {
			Log.d("id", String.valueOf(view.getId()));
			List<ScheduleItem> rowItems = new ArrayList<ScheduleItem>();
			String[] teamNames = { "Titans", "Cardinals", "Ravens", "Bills",
					"Packers", "Seehawks", "Jets", "Rams" };
			int[] teamLogo = { R.drawable.titans, R.drawable.cardinals,
					R.drawable.ravens, R.drawable.bills, R.drawable.packers,
					R.drawable.seahawks, R.drawable.jets, R.drawable.rams };
			String[] weekText = { "WEEK 1", "WEEK 2", "WEEK 3", "WEEK 4",
					"WEEK 5", "WEEK 6", "WEEK 7", "WEEK 8" };
			String[] teamStatus = { "W 34-13", "L 20-18", "L 31-30", "W 52-28",
					"Live 21-17", "10/14 4:05 PM", "10/21 4/25 PM",
					"10/21 1:00 PM" };
			String[] versusTexts = { "@", "vs", "@", "@", "vs", "@", "vs", "@" };
			for (int i = 0; i < 8; i++) {

				ScheduleItem item = new ScheduleItem(teamNames[i], teamLogo[i],
						teamStatus[i], weekText[i], versusTexts[i]);

				rowItems.add(item);

			}
			listView = (ListView) findViewById(R.id.game_schedule_list);
			ScheduleListViewAdapter adapter = new ScheduleListViewAdapter(this,
					R.layout.popover_game_schedule_list_item, rowItems);
			listView.setAdapter(adapter);

			/*
			 * listView = (ListView) findViewById(R.id.game_schedule_list2);
			 * adapter = new CourseListViewAdapter(this,
			 * R.layout.my_course_list_item, rowItems);
			 * listView.setAdapter(adapter);
			 */

		} else if (view.getLayoutId() == R.layout.popover_game_drives_view) {

			List<DriveItem> rowItemsForQ1 = new ArrayList<DriveItem>();
			List<DriveItem> rowItemsForQ2 = new ArrayList<DriveItem>();
			List<DriveItem> rowItemsForQ3 = new ArrayList<DriveItem>();
			List<DriveItem> rowItemsForQ4 = new ArrayList<DriveItem>();

			String[] teamShortNamesForQ1 = { "NE", "GB", "NE", "NE" };
			String[] teamShortNamesForQ2 = { "NE", "GB", "NE", "NE" };
			String[] teamShortNamesForQ3 = { "NE", "GB", "NE", "NE", "GB" };
			String[] teamShortNamesForQ4 = { "NE", "GB", "NE", "NE" };

			String[] driveEventsForQ1 = { "LIVE", "TOUCHDOWN", "PUNT", "PUNT" };
			String[] driveEventsForQ2 = { "LIVE", "TOUCHDOWN", "PUNT", "PUNT" };
			String[] driveEventsForQ3 = { "TOUCHDOWN", "INTERCEPTION", "PUNT",
					"FIELD GOAL", "FUMBLE" };
			String[] driveEventsForQ4 = { "LIVE", "TOUCHDOWN", "PUNT", "PUNT" };

			String[] driveDetailsForQ1 = { "53 YDS 8:32", "52 YDS,8:55",
					"31 YDS,2:46", "22 YDS 1:32" };
			String[] driveDetailsForQ2 = { "53 YDS 8:32", "52 YDS,8:55",
					"31 YDS,2:46", "22 YDS 1:32" };
			String[] driveDetailsForQ3 = { "75 YDS 9:55", "28 YDS,3:26",
					"18 YDS,1:15", "82 YDS 7:11", "56 YDS,5:41" };
			String[] driveDetailsForQ4 = { "53 YDS 8:32", "52 YDS,8:55",
					"31 YDS,2:46", "22 YDS 1:32" };

			int[] teamLogoForQ1 = { R.drawable.patriots, R.drawable.packers,
					R.drawable.patriots, R.drawable.packers, };
			int[] teamLogoForQ2 = { R.drawable.patriots, R.drawable.packers,
					R.drawable.patriots, R.drawable.packers, };
			int[] teamLogoForQ3 = { R.drawable.patriots, R.drawable.packers,
					R.drawable.patriots, R.drawable.packers, R.drawable.packers };
			int[] teamLogoForQ4 = { R.drawable.patriots, R.drawable.packers,
					R.drawable.patriots, R.drawable.packers, };

			for (int i = 0; i < teamShortNamesForQ1.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ1[i],
						driveEventsForQ1[i], driveDetailsForQ1[i],
						teamLogoForQ1[i]);

				rowItemsForQ1.add(item);
			}
			for (int i = 0; i < teamShortNamesForQ2.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ2[i],
						driveEventsForQ2[i], driveDetailsForQ2[i],
						teamLogoForQ2[i]);

				rowItemsForQ2.add(item);
			}
			for (int i = 0; i < teamShortNamesForQ3.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ3[i],
						driveEventsForQ3[i], driveDetailsForQ3[i],
						teamLogoForQ3[i]);

				rowItemsForQ3.add(item);
			}
			for (int i = 0; i < teamShortNamesForQ4.length; i++) {
				DriveItem item = new DriveItem(teamShortNamesForQ4[i],
						driveEventsForQ4[i], driveDetailsForQ4[i],
						teamLogoForQ4[i]);

				rowItemsForQ4.add(item);
			}
			/*
			 * listView = (ListView) findViewById(R.id.game_schedule_list);
			 * adapter = new CourseListViewAdapter(this,
			 * R.layout.my_course_list_item, rowItems);
			 * listView.setAdapter(adapter);
			 */

			SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection("4TH QUARTER", new DriveListViewAdapter(this,
					R.layout.popover_game_drive_list_item, rowItemsForQ4));
			adapter.addSection("3TH QUARTER", new DriveListViewAdapter(this,
					R.layout.popover_game_drive_list_item, rowItemsForQ3));
			adapter.addSection("2TH QUARTER", new DriveListViewAdapter(this,
					R.layout.popover_game_drive_list_item, rowItemsForQ2));
			adapter.addSection("1TH QUARTER", new DriveListViewAdapter(this,
					R.layout.popover_game_drive_list_item, rowItemsForQ1));
			listView = (ListView) findViewById(R.id.game_drive_list);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// Toast.makeText(this, "text", Toast.LENGTH_LONG).show();
					Log.d("lineno", String.valueOf(arg2));
					TextView txt = (TextView) (arg1
							.findViewById(R.id.team_shortname));

					Log.d("lineno", txt.getText().toString());

				}
			});
			listView.setAdapter(adapter);

		}

		else if (view.getLayoutId() == R.layout.popover_game_stats_view) {
			TextView teamNamesLabel = (TextView) findViewById(R.id.teamNames);
			teamNamesLabel.setText("Packers    Patriots");

			List<StatsItem> rowItemsForTeamStats = new ArrayList<StatsItem>();
			List<StatsItem> rowItemsForKeyPlays = new ArrayList<StatsItem>();

			String[] teamStatsType = { "Scoring", "Passing yards",
					"Rushing Yards", "First Downs", "Third Down Efficiency",
					"Red Zone Efficiency", "Turnovers" };
			String[] teamStatsScore1 = { "21", "232", "248", "3", "6/13",
					"2/3", "2" };
			String[] teamStatsScore2 = { "21", "232", "248", "3", "6/13",
					"2/3", "2" };

			String[] keyPlaysStatstype = { "Long Passes", "Long Rushes",
					"Defensive Plays" };
			String[] keyPlaysStatsScore1 = { "12", "3", "8" };
			String[] keyPlaysStatsScore2 = { "6", "7", "7" };

			for (int i = 0; i < teamStatsType.length; i++) {
				StatsItem item = new StatsItem(teamStatsType[i],
						teamStatsScore1[i], teamStatsScore2[i],
						R.drawable.disclosure);

				rowItemsForTeamStats.add(item);
			}
			for (int i = 0; i < keyPlaysStatstype.length; i++) {
				StatsItem item = new StatsItem(keyPlaysStatstype[i],
						keyPlaysStatsScore1[i], keyPlaysStatsScore2[i],
						R.drawable.disclosure);

				rowItemsForKeyPlays.add(item);
			}
			/*
			 * listView = (ListView) findViewById(R.id.game_schedule_list);
			 * adapter = new CourseListViewAdapter(this,
			 * R.layout.my_course_list_item, rowItems);
			 * listView.setAdapter(adapter);
			 */

			SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection("Team Plays",
					new StatsListViewAdapter(this,
							R.layout.popover_game_stats_list_item,
							rowItemsForTeamStats));
			adapter.addSection("Key Plays", new StatsListViewAdapter(this,
					R.layout.popover_game_stats_list_item, rowItemsForKeyPlays));
			listView = (ListView) findViewById(R.id.game_stats_list);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// Toast.makeText(this, "text", Toast.LENGTH_LONG).show();
					Log.d("lineno", String.valueOf(arg2));
					TextView txt = (TextView) (arg1
							.findViewById(R.id.stat_type));

					Log.d("lineno", txt.getText().toString());

				}
			});
			listView.setAdapter(adapter);

		}

		else if (view.getLayoutId() == R.layout.popover_game_player_view) {

			List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String playerNames[] = { "Tom Brady", "Stevan Ridley",
					"Wes Welker", "Rob Gronkowski", "Brandon Lloyd" };
			String playerNumbers[] = { "#12 | QB", "#22 | RB", "#83 | WR",
					"#87 | TE", "#43 | QB" };
			String playerData1[] = { "20/29", "11 CAR", "9 REC", "8 REC",
					"7 REC" };
			String playerData2[] = { "329 YDS", "64 YDS", "53 YDS", "89 YDS",
					"38 YDS" };
			String playerData3[] = { "2 TD", "1 TD", "0 TD", "1 TD", "0 TD" };
			String playerData4[] = { "1 NT" };

			int playerImages[] = { R.drawable.tom_brady,
					R.drawable.stevan_ridley, R.drawable.wes_welker,
					R.drawable.rob_gronkowski, R.drawable.stevan_ridley };

			PlayerItem item = new PlayerItem("Tom Brady", "#12/QB",
					R.drawable.tom_brady, "20/29", "329 YDS", "2TD", "1 NT");
			offensivePlayerList.add(item);

			for (int i = 1; i < playerNames.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(playerData1[i]);
				item.setPlayerdata2(playerData2[i]);
				item.setPlayerdata3(playerData3[i]);
				item.setPlayerImage(playerImages[i]);
				item.setPlayerName(playerNames[i]);
				item.setPlayerNumber(playerNumbers[i]);
				offensivePlayerList.add(item);
			}

			SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection("OFFENSE",
					new PlayerListViewAdapter(this,
							R.layout.popover_game_player_list_item,
							offensivePlayerList));

			listView = (ListView) findViewById(R.id.game_player_list);

			listView.setAdapter(adapter);

		}

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

	private void playCards() {

		ArrayList<String> playCardTopDetail = new ArrayList<String>();
		playCardTopDetail.add("Tom Brady");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");

		ArrayList<String> playCardBottomDetail = new ArrayList<String>();
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");

		// The parent LinearLayout for playCards
		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		for (int index = 0; index < playCardTopDetail.size(); index++) {

			// Creating a new RelativeLayout
			playCardLayout = new RelativeLayout(this);

			// Defining the RelativeLayout layout parameters.
			// In this case I want to fill its parent
			RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			playCardLayout.setLayoutParams(playCardLayoutParameters);

			if (index % 2 == 0) {
				playCardLayout.setBackgroundColor(getResources().getColor(
						R.color.orange));
			} else {
				playCardLayout.setBackgroundColor(getResources().getColor(
						R.color.green));
			}

			playCardLayout.setPadding(30, 30, 30, 30);

			// Creating a new topTextView
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

			// Adding the TextView to the RelativeLayout as a child
			playCardLayout.addView(playCardTopTextView);

			/*
			 * //****************************************************************
			 * ********* // Creating a webView For video WebView playCardWebView
			 * = (WebView) findViewById(R.id.webViewId);
			 * RelativeLayout.LayoutParams layoutParametersForPlayCardWebView =
			 * new
			 * RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
			 * ,RelativeLayout.LayoutParams.WRAP_CONTENT);
			 * layoutParametersForPlayCardWebView
			 * .addRule(RelativeLayout.CENTER_IN_PARENT);
			 * playCardWebView.setLayoutParams
			 * (layoutParametersForPlayCardWebView); playCardWebView.loadUrl(
			 * "http://www.youtube.com/watch?feature=player_detailpage&v=C59w2wP6esc"
			 * ); playCardWebView.getSettings().setJavaScriptEnabled(true);
			 * playCardWebView.loadUrl(
			 * "http://vimeo.com/couchmode/dreamcorepics/videos/sort:date/33110953"
			 * ); playCardLayout.addView(playCardWebView);
			 * //********************
			 * ********************************************
			 * ***********************
			 */

			// Creating a new VideoView VideoView

			playCardVideoView = new VideoView(this);
			// Defining the layout parameters of the VideoView
			RelativeLayout.LayoutParams layoutParametersForPlayCardVideoView = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);

			MediaController mediaController = new MediaController(this);

			mediaController.setAnchorView(playCardVideoView);
			playCardVideoView.setMediaController(mediaController);

			playCardVideoView
					.setLayoutParams(layoutParametersForPlayCardVideoView);
			playCardVideoView.setVideoURI(Uri
					.parse("http://commonsware.com/misc/test2.3gp"));
			layoutParametersForPlayCardVideoView
					.addRule(RelativeLayout.CENTER_IN_PARENT);

			playCardLayout.addView(playCardVideoView);

			// **************************************************************************

			/*
			 * Bitmap bMap = ThumbnailUtils.createVideoThumbnail(
			 * playCardVideoDetail.get(index),
			 * MediaStore.Video.Thumbnails.MICRO_KIND);
			 */

			playCardImageView = new ImageView(this);
			RelativeLayout.LayoutParams layoutParametersForPlayCardImageView = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			layoutParametersForPlayCardImageView
					.addRule(RelativeLayout.CENTER_IN_PARENT);
			playCardImageView
					.setLayoutParams(layoutParametersForPlayCardImageView);
			playCardImageView.setImageResource(R.drawable.nflimageone);
			playCardLayout.addView(playCardImageView);
			playCardImageView
					.setOnClickListener(playCardImageViewClickListener);

			// *******************************************************************************

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

	private OnClickListener playCardImageViewClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			// playCardImageView.setVisibility(View.GONE);
			playCardLayout.removeView(playCardImageView);

			// Creating a new VideoView
			// playCardVideoView = new VideoView(Context);
			playCardVideoView = new VideoView(getApplicationContext());

			// Defining the layout parameters of the VideoView
			RelativeLayout.LayoutParams layoutParametersForPlayCardVideoView = new RelativeLayout.LayoutParams(
					RelativeLayout.LayoutParams.WRAP_CONTENT,
					RelativeLayout.LayoutParams.WRAP_CONTENT);
			playCardVideoView
					.setLayoutParams(layoutParametersForPlayCardVideoView);

			layoutParametersForPlayCardVideoView
					.addRule(RelativeLayout.CENTER_IN_PARENT);

			MediaController mediaController = new MediaController(
					getApplicationContext());
			mediaController.setAnchorView(playCardVideoView);
			playCardVideoView.setMediaController(mediaController);
			// playCardVideoView.setVideoPath(R.raw.nflvideoone);

			playCardVideoView.setVideoURI(Uri.parse("android.Resource://"
					+ getPackageName() + "/" + R.raw.nflvideoone));
			playCardVideoView.start();

			playCardLayout.addView(playCardVideoView);

			Toast.makeText(GameActivity.this, "play card image clicked",
					Toast.LENGTH_SHORT).show();
		}
	};

}
