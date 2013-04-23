package com.liveclips.nfl.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.fragments.R;
import com.liveclips.nfl.adapter.DriveListViewAdapter;
import com.liveclips.nfl.adapter.PlayerListViewAdapter;
import com.liveclips.nfl.adapter.ScheduleListViewAdapter;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.adapter.StatsListViewAdapter;
import com.liveclips.nfl.fragment.MainMenuFragment;
import com.liveclips.nfl.fragment.TopicMenuFragment;
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
	TextView team1BtnPlayers;
	TextView team2BtnPlayers;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		fragmentManager = getFragmentManager();
		ft = fragmentManager.beginTransaction();
		mainMenuFragment = new TopicMenuFragment();
		ft.add(R.id.menuFragment, mainMenuFragment);

		ft.commit();

		/*
		 * headerTextView = (TextView) findViewById(R.id.menuHeader);
		 * headerTextView.setText("LiveClips"); closeButtonHeader =
		 * (ImageButton) findViewById(R.id.closeButtonHeader);
		 * closeButtonHeader.setVisibility(View.VISIBLE);
		 * closeButtonHeader.setOnClickListener(closeButtonListener);
		 */

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
	protected void onResume() {
		
	
		headerTextView = (TextView) mainMenuFragment.getView().findViewById(R.id.menuHeader);
		headerTextView.setText("LiveClips");
		closeButtonHeader = (ImageButton) mainMenuFragment.getView().findViewById(R.id.closeButtonHeader);
		closeButtonHeader.setVisibility(View.VISIBLE);
		closeButtonHeader.setOnClickListener(closeButtonListener);
		super.onStart();
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

	public List<PlayerItem> getPlayers(String teamName, String teamType) {

		List<PlayerItem> playerList = null;

		if (teamName.equals("team1") && teamType.equals("offensive")) {
			final List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String offensivePlayerNamesForTeam1[] = { "Tom Brady",
					"Stevan Ridley", "Wes Welker", "Rob Gronkowski",
					"Brandon Lloyd" };
			String offensivePlayerNumbersForTeam1[] = { "#12 | QB", "#22 | RB",
					"#83 | WR", "#87 | TE", "#43 | QB" };
			String offensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC", "8 REC", "7 REC" };
			String offensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS", "89 YDS", "38 YDS" };
			String offensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD",
					"1 TD", "0 TD" };

			int offensivPlayerImagesForTeam1[] = { R.drawable.tom_brady,
					R.drawable.stevan_ridley, R.drawable.wes_welker,
					R.drawable.rob_gronkowski, R.drawable.stevan_ridley };

			PlayerItem item = new PlayerItem("Tom Brady", "#12/QB",
					R.drawable.tom_brady, "20/29", "329 YDS", "2TD", "1 NT");
			offensivePlayerList.add(item);

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				item.setPlayerName(offensivePlayerNamesForTeam1[i]);
				item.setPlayerNumber(offensivePlayerNumbersForTeam1[i]);
				offensivePlayerList.add(item);
			}

			playerList = offensivePlayerList;
		} else if (teamName.equals("team1") && teamType.equals("defensive")) {
			final List<PlayerItem> defensivePlayerList = new ArrayList<PlayerItem>();
			String defensivePlayerNamesForTeam1[] = { "Jake Bequette",
					"Vince Wilfork", "Rob Ninkovich" };
			String defensivePlayerNumbersForTeam1[] = { "#92 | DE", "#75 | DT",
					"#50 | DL" };
			String defensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC" };
			String defensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS" };
			String defensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD" };

			int defensivePlayerImages[] = { R.drawable.jake, R.drawable.vince,
					R.drawable.brandonspikes };

			PlayerItem item = new PlayerItem();

			for (int i = 0; i < defensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(defensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(defensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(defensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(defensivePlayerImages[i]);
				item.setPlayerName(defensivePlayerNamesForTeam1[i]);
				item.setPlayerNumber(defensivePlayerNumbersForTeam1[i]);
				defensivePlayerList.add(item);
			}
			playerList = defensivePlayerList;
		} else if (teamName.equals("team2") && teamType.equals("offensive")) {
			final List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String offensivePlayerNamesForTeam1[] = { "Jordan Miller",
					"Casey Hayward", "Brad Jones" };
			String offensivePlayerNumbersForTeam1[] = { "#91 | DT", "#29 | CB",
					"#59 | LB", "#87 | TE", "#43 | QB" };
			String offensivePlayerData1ForTeam1[] = { "11 CAR", "9 REC",
					"8 REC" };
			String offensivePlayerData2ForTeam1[] = { "64 YDS", "53 YDS",
					"89 YDS" };
			String offensivePlayerData3ForTeam1[] = { "0 TD", "0 TD", "1 TD" };

			int offensivPlayerImagesForTeam1[] = { R.drawable.jorden_miller,
					R.drawable.casey_hayward, R.drawable.brad_jones };

			PlayerItem item = new PlayerItem("Mike Daniels", "#76 | DE",
					R.drawable.mike_daniel, "20/29", "329 YDS", "2TD", "1 NT");
			offensivePlayerList.add(item);

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				item.setPlayerName(offensivePlayerNamesForTeam1[i]);
				item.setPlayerNumber(offensivePlayerNumbersForTeam1[i]);
				offensivePlayerList.add(item);
			}

			playerList = offensivePlayerList;
		} else if (teamName.equals("team2") && teamType.equals("defensive")) {
			final List<PlayerItem> defensivePlayerList = new ArrayList<PlayerItem>();
			String defensivePlayerNamesForTeam1[] = { "Jake Bequette",
					"Vince Wilfork", "Rob Ninkovich" };
			String defensivePlayerNumbersForTeam1[] = { "#92 | DE", "#75 | DT",
					"#50 | DL" };
			String defensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC" };
			String defensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS" };
			String defensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD" };

			int defensivePlayerImages[] = { R.drawable.jake, R.drawable.vince,
					R.drawable.brandonspikes };

			PlayerItem item = new PlayerItem();

			for (int i = 0; i < defensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(defensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(defensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(defensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(defensivePlayerImages[i]);
				item.setPlayerName(defensivePlayerNamesForTeam1[i]);
				item.setPlayerNumber(defensivePlayerNumbersForTeam1[i]);
				defensivePlayerList.add(item);
			}
			playerList = defensivePlayerList;
		}
		return playerList;
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

			// Offensive players for team1

			// Defensive playes for team1

			final SeparatedListAdapter adapter = new SeparatedListAdapter(this);
			adapter.addSection(
					"OFFENSE",
					new PlayerListViewAdapter(this,
							R.layout.popover_game_player_list_item, getPlayers(
									"team1", "offensive")));
			adapter.addSection("DEFENSE", new PlayerListViewAdapter(
					GameActivity.this, R.layout.popover_game_player_list_item,
					getPlayers("team1", "defensive")));

			listView = (ListView) findViewById(R.id.game_player_list);

			listView.setAdapter(adapter);

			team1BtnPlayers = (TextView) findViewById(R.id.team1BtnPlayers);
			team1BtnPlayers.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					adapter.removeAllSections();
					adapter.addSection("OFFENSE",
							new PlayerListViewAdapter(GameActivity.this,
									R.layout.popover_game_player_list_item,
									getPlayers("team1", "offensive")));
					adapter.addSection("DEFENSE",
							new PlayerListViewAdapter(GameActivity.this,
									R.layout.popover_game_player_list_item,
									getPlayers("team1", "defensive")));
					adapter.notifyDataSetChanged();
					listView.setSelection(0);
					team1BtnPlayers.setBackgroundColor(0xFFC0C0C0);
					team1BtnPlayers.setTextColor(0xFF0000FF);
					team2BtnPlayers.setBackgroundColor(0xFFFFFFFF);
					team2BtnPlayers.setTextColor(Color.BLACK);
					/* team1Btn.setText("PACKERS"); */
				}
			});

			team2BtnPlayers = (TextView) findViewById(R.id.team2BtnPlayers);
			team2BtnPlayers.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					adapter.removeAllSections();
					adapter.addSection("OFFENSE",
							new PlayerListViewAdapter(GameActivity.this,
									R.layout.popover_game_player_list_item,
									getPlayers("team2", "offensive")));
					adapter.addSection("DEFENSE",
							new PlayerListViewAdapter(GameActivity.this,
									R.layout.popover_game_player_list_item,
									getPlayers("team2", "defensive")));
					adapter.notifyDataSetChanged();
					listView.setSelection(0);
					team2BtnPlayers.setBackgroundColor(0xFFC0C0C0);
					team2BtnPlayers.setTextColor(0xFF0000FF);
					team1BtnPlayers.setBackgroundColor(0xFFFFFFFF);
					team1BtnPlayers.setTextColor(Color.BLACK);
					// team2Btn.setText("PATRIOTS");
				}
			});

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
		Log.d("slider", "clickee");
		ft = fragmentManager.beginTransaction();
		mainMenuFragment = new MainMenuFragment();

		ft.replace(R.id.menuFragment, mainMenuFragment);
		ft.commit();

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

		for (int index = 0; index < 5; index++) {

			/*if((index+1)%3==0){
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			// Creating a new RelativeLayout
			RelativeLayout playCardLayout = new RelativeLayout(this);

			// Defining the RelativeLayout layout parameters.
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


			
			
			
			// Creating a new VideoView VideoView
			final VideoView playCardVideoView = new VideoView(this);

			// Defining the layout parameters of the VideoView
			RelativeLayout.LayoutParams layoutParametersForPlayCardVideoView = new RelativeLayout.LayoutParams(250, 150);
			layoutParametersForPlayCardVideoView.addRule(RelativeLayout.CENTER_IN_PARENT);
			//RelativeLayout.LayoutParams layoutParametersForPlayCardVideoView = new RelativeLayout.LayoutParams(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
			playCardVideoView.setId(index);
			
			MediaController mediaController = new MediaController(this);
			mediaController.setAnchorView(playCardVideoView);
			playCardVideoView.setMediaController(mediaController);

			playCardVideoView
					.setLayoutParams(layoutParametersForPlayCardVideoView);
			
			String path = "http://x.pio.lc/nfl/week05/20121009_001_20121011115406_027_3_241b_d02a361b.3gp";
			playCardVideoView.setVideoURI(Uri.parse(path));
			playCardVideoView.setZOrderOnTop(false);
			playCardLayout.addView(playCardVideoView);
			
			
			
			final ImageView playCardImageView = new ImageView(this);
			RelativeLayout.LayoutParams layoutParameterForPlayCardImageView = new RelativeLayout.LayoutParams(250, 150);
			layoutParameterForPlayCardImageView.addRule(RelativeLayout.CENTER_IN_PARENT);
			//playCardImageView.setVisibility(View.VISIBLE);
			playCardImageView.setLayoutParams(layoutParameterForPlayCardImageView);
			
			//playCardImageView.setImageResource(R.drawable.nflimagefour);
			//playCardImageView.setImageURI(Uri.parse("http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"));
			//playCardImageView.setImageBitmap(loadBitmap("http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"));
			
			
			 /*
             * NetworkOnMainThreadException is handled using these two lines for
             * Android 3.0 and above.
             */
            // Start handling NetworkOnMainThreadException
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // End Handling NetworkOnMainThreadException
			new DownloadImageTask(playCardImageView).execute("http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg");
			
			
			
			
			
			playCardLayout.addView(playCardImageView);
			
			
			playCardImageView.setOnTouchListener(new View.OnTouchListener()
		    {
				@Override
				public boolean onTouch(View v, MotionEvent arg1) {
					v.setVisibility(View.INVISIBLE);
					playCardVideoView.start();
					return false;
				}
		    });
			
			playCardVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			    @Override
			    public void onCompletion(MediaPlayer vmp) {
			    	playCardImageView.setVisibility(View.VISIBLE); 
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
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	}
}
