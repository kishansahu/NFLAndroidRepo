package com.liveclips.nfl.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.liveclips.nfl.R;
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
import com.liveclips.nfl.utils.DownloadImagesThreadPool;
import com.liveclips.nfl.utils.NflUtils;
import com.liveclips.nfl.utils.PlayCardView;

public class GameActivity extends BaseActivity implements PopoverViewDelegate {

	protected Context context = GameActivity.this;
	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	FragmentTransaction ft;
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
	TextView team1BtnPlayers;
	TextView team2BtnPlayers;
	LayoutInflater layoutInflater;
	View fragmentMenuHeaderView;
	View activityMenuHeaderView;
	int playCardVideoId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		context = this;

		allPlaysTextView = (TextView) findViewById(R.id.allPlaysId);
		topPlaysTextView = (TextView) findViewById(R.id.topPlaysId);
		topRatedTextView = (TextView) findViewById(R.id.topRatedId);
		watchAllTextView = (TextView) findViewById(R.id.watchAllId);

		allPlaysTextView.setOnClickListener(allPlaysClickListener);
		topPlaysTextView.setOnClickListener(topPlaysCilckListener);
		topRatedTextView.setOnClickListener(topRatedClickListener);
		watchAllTextView.setOnClickListener(watchAllClickListener);
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground
				.setOnClickListener(matchScoreBoardBackgroundClickListener);

		createCustomActionBar();

		playCards();

	}

	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.patriots_actionbar_layout, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF8B1D));
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		View scheduleView = mActionBarView.findViewById(R.id.scheduleView);

		scheduleView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.popover_game_schedule_view);

				popoverView.setContentSizeForViewInPopover(new Point(320, 400));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.scheduleView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});

		View statusView = mActionBarView.findViewById(R.id.statsView);

		statusView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.popover_game_stats_view);

				popoverView.setContentSizeForViewInPopover(new Point(320, 400));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.statsView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});

		View drivesView = mActionBarView.findViewById(R.id.drivesView);

		drivesView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.popover_game_drives_view);

				popoverView.setContentSizeForViewInPopover(new Point(320, 400));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.drivesView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});

		View playersView = mActionBarView.findViewById(R.id.playersView);

		playersView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				RelativeLayout rootView = (RelativeLayout) findViewById(R.id.gameRootView);
				if (popoverView != null) {
					popoverView.dissmissPopover(false);
				}
				popoverView = new PopoverView(GameActivity.this,
						R.layout.popover_game_player_view);

				popoverView.setContentSizeForViewInPopover(new Point(320, 400));
				popoverView.setDelegate(GameActivity.this);
				View button = (View) findViewById(R.id.playersView);
				popoverView.showPopoverFromRectInViewGroup(rootView,
						PopoverView.getFrameForView(button),
						PopoverView.PopoverArrowDirectionUp, true);

			}
		});

		Button alertButton = (Button) mActionBarView
				.findViewById(R.id.alertButton);
		alertButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("alert", "alert");

			}
		});

	}

	OnClickListener matchScoreBoardBackgroundClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			/*Toast.makeText(GameActivity.this, "Background clicked",
					Toast.LENGTH_SHORT).show();*/
			if (NflUtils.isScoreBannerShrinked()) {
				makeTabInvisibleForEnlargingScoreBoard();
				enlargeScoreBanner();
			}
		}
	};

	public void enlargeScoreBanner() {
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground
				.setLayoutParams(new RelativeLayout.LayoutParams(
						LayoutParams.FILL_PARENT, NflUtils
								.convertPixelToDensityPixel(context, 160)));

		ImageView firstTeamIconImage = (ImageView) findViewById(R.id.firstTeamLargeIcon);
		firstTeamIconImage.setImageResource(R.drawable.packers_helmet);

		firstTeamIconImage.setLayoutParams(new LinearLayout.LayoutParams(
				NflUtils.convertPixelToDensityPixel(context, 130),
				LayoutParams.WRAP_CONTENT));

		ImageView secondTeamIconImage = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		secondTeamIconImage.setImageResource(R.drawable.patriots_helmet);
		secondTeamIconImage.setLayoutParams(new LinearLayout.LayoutParams(
				NflUtils.convertPixelToDensityPixel(context, 130),
				LayoutParams.WRAP_CONTENT));

		TextView firstTeamScore = (TextView) findViewById(R.id.firstTeamScore);
		firstTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(context,
				45));

		TextView secondTeamScore = (TextView) findViewById(R.id.secondTeamScore);
		secondTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 45));

		TextView gameQuarterIndex = (TextView) findViewById(R.id.gameQuarterIndex);
		gameQuarterIndex.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 18));

		TextView gameQuarterTime = (TextView) findViewById(R.id.gameQuarterTime);
		gameQuarterTime.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 22));

		TextView gameScoreDescription = (TextView) findViewById(R.id.gameScoreDescription);
		gameScoreDescription.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 18));

		TextView firstTeamFirstName = (TextView) findViewById(R.id.firstTeamFirstName);
		firstTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView firstTeamSecondName = (TextView) findViewById(R.id.firstTeamSecondName);
		firstTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 26));

		TextView secondTeamFirstName = (TextView) findViewById(R.id.secondTeamFirstName);
		secondTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 26));
		//
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF,
				R.id.firstTeamLargeIconContainer);
		params.topMargin = (NflUtils.convertPixelToDensityPixel(context, 55));
		params.leftMargin = (NflUtils.convertPixelToDensityPixel(context, 25));
		LinearLayout firstTeamDescriptionContainer = (LinearLayout) findViewById(R.id.firstTeamDescriptionContainer);
		firstTeamDescriptionContainer.setLayoutParams(params);

		RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relParams.addRule(RelativeLayout.LEFT_OF,
				R.id.secondTeamLargeIconContainer);
		relParams.topMargin = (NflUtils.convertPixelToDensityPixel(context, 55));
		relParams.rightMargin = (NflUtils.convertPixelToDensityPixel(context,
				25));
		LinearLayout secondTeamDescriptionContainer = (LinearLayout) findViewById(R.id.secondTeamDescriptionContainer);
		secondTeamDescriptionContainer.setLayoutParams(relParams);
		NflUtils.setScoreBannerShrinked(false);
		NflUtils.stubActivatedName = "";
	}

	public void shrinkScoreBanner() {
		RelativeLayout matchScoreBoardBackground = (RelativeLayout) findViewById(R.id.matchScoreBoardBackground);
		matchScoreBoardBackground.getLayoutParams().width = NflUtils
				.convertDensityPixelToPixel(context, 470);

		ImageView firstTeamIconImage = (ImageView) findViewById(R.id.firstTeamLargeIcon);
		firstTeamIconImage.setImageResource(R.drawable.packers);
		firstTeamIconImage.getLayoutParams().width = NflUtils
				.convertPixelToDensityPixel(context, 65);
		firstTeamIconImage.getLayoutParams().height = NflUtils
				.convertPixelToDensityPixel(context, 65);

		ImageView secondTeamIconImage = (ImageView) findViewById(R.id.secondTeamLargeIcon);
		secondTeamIconImage.setImageResource(R.drawable.patriots);
		secondTeamIconImage.getLayoutParams().width = NflUtils
				.convertPixelToDensityPixel(context, 65);
		secondTeamIconImage.getLayoutParams().height = NflUtils
				.convertPixelToDensityPixel(context, 65);

		TextView firstTeamScore = (TextView) findViewById(R.id.firstTeamScore);
		firstTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(context,
				25));

		TextView secondTeamScore = (TextView) findViewById(R.id.secondTeamScore);
		secondTeamScore.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 25));

		TextView gameQuarterIndex = (TextView) findViewById(R.id.gameQuarterIndex);
		gameQuarterIndex.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView gameQuarterTime = (TextView) findViewById(R.id.gameQuarterTime);
		gameQuarterTime.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 22));

		TextView gameScoreDescription = (TextView) findViewById(R.id.gameScoreDescription);
		gameScoreDescription.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 15));

		TextView firstTeamFirstName = (TextView) findViewById(R.id.firstTeamFirstName);
		firstTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 13));

		TextView firstTeamSecondName = (TextView) findViewById(R.id.firstTeamSecondName);
		firstTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 20));

		TextView secondTeamFirstName = (TextView) findViewById(R.id.secondTeamFirstName);
		secondTeamFirstName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 13));

		TextView secondTeamSecondName = (TextView) findViewById(R.id.secondTeamSecondName);
		secondTeamSecondName.setTextSize(NflUtils.convertPixelToDensityPixel(
				context, 20));

		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.firstTeamLargeIconContainer);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				R.id.firstTeamLargeIconContainer);
		params.bottomMargin = (NflUtils.convertPixelToDensityPixel(context, 15));
		params.leftMargin = (NflUtils.convertPixelToDensityPixel(context, 5));

		LinearLayout firstTeamDescriptionContainer = (LinearLayout) findViewById(R.id.firstTeamDescriptionContainer);
		firstTeamDescriptionContainer.setLayoutParams(params);

		RelativeLayout.LayoutParams relParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		relParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				R.id.secondTeamLargeIconContainer);
		relParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				R.id.secondTeamLargeIconContainer);
		relParams.bottomMargin = (NflUtils.convertPixelToDensityPixel(context,
				15));
		params.rightMargin = (NflUtils.convertPixelToDensityPixel(context, 10));
		LinearLayout secondTeamDescriptionContainer = (LinearLayout) findViewById(R.id.secondTeamDescriptionContainer);
		secondTeamDescriptionContainer.setLayoutParams(relParams);
		NflUtils.setScoreBannerShrinked(true);
	}

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.i("POPOVER", "Will show : " + view.getChildCount());
	}

	public List<PlayerItem> getPlayers(String teamName, String teamType) {

		List<PlayerItem> playerList = null;

		if (teamName.equals("team1") && teamType.equals("offensive")) {
			final List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String offensivePlayerNamesForTeam1[] = { "Graham Harrell",
					"Alex Green", "Randall Cobb", "Andrew Quarless" };
			String offensivePlayerNumbersForTeam1[] = { "#6 | QB", "#20 | RB",
					"#83 | WR", "#87 | TE" };
			String offensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC", "8 REC" };
			String offensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS", "89 YDS" };
			String offensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD",
					"1 TD" };

			int offensivPlayerImagesForTeam1[] = { R.drawable.graham_herrell,
					R.drawable.alex_green, R.drawable.randall_cobb,
					R.drawable.andrew_quarless };

			PlayerItem item;

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				item.setPlayerName(offensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
				offensivePlayerList.add(item);
			}

			playerList = offensivePlayerList;
		} else if (teamName.equals("team1") && teamType.equals("defensive")) {
			final List<PlayerItem> defensivePlayerList = new ArrayList<PlayerItem>();
			String defensivePlayerNamesForTeam1[] = { "Mike Daniels",
					"Josh Bell", "Johnny Jolly" };
			String defensivePlayerNumbersForTeam1[] = { "#92 | DE", "#75 | DT",
					"#50 | DL" };
			String defensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"9 REC" };
			String defensivePlayerData2ForTeam1[] = { "329 YDS", "64 YDS",
					"53 YDS" };
			String defensivePlayerData3ForTeam1[] = { "2 TD", "1 TD", "0 TD" };

			int defensivePlayerImages[] = { R.drawable.mike_daniel,
					R.drawable.bell_josh, R.drawable.johnny_jolly };

			PlayerItem item = new PlayerItem();

			for (int i = 0; i < defensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(defensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(defensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(defensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(defensivePlayerImages[i]);
				item.setPlayerName(defensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(defensivePlayerNumbersForTeam1[i]);
				defensivePlayerList.add(item);
			}
			playerList = defensivePlayerList;
		} else if (teamName.equals("team2") && teamType.equals("offensive")) {
			final List<PlayerItem> offensivePlayerList = new ArrayList<PlayerItem>();
			String offensivePlayerNamesForTeam1[] = { "Tom Brady",
					"Stevan Ridley", "Wes Welker" };
			String offensivePlayerNumbersForTeam1[] = { "#91 | QB", "#22 | RB",
					"#83 | WR", "#87 | TE", "#43 | QB" };
			String offensivePlayerData1ForTeam1[] = { "20/29", "11 CAR",
					"8 REC" };
			String offensivePlayerData2ForTeam1[] = { "64 YDS", "53 YDS",
					"89 YDS" };
			String offensivePlayerData3ForTeam1[] = { "0 TD", "0 TD", "1 TD" };

			int offensivPlayerImagesForTeam1[] = { R.drawable.tom_brady,
					R.drawable.stevan_ridley, R.drawable.wes_welker };

			PlayerItem item;

			for (int i = 0; i < offensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(offensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(offensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(offensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(offensivPlayerImagesForTeam1[i]);
				item.setPlayerName(offensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
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
					R.drawable.rob_ninkovich };

			PlayerItem item = new PlayerItem();

			for (int i = 0; i < defensivePlayerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerdata1(defensivePlayerData1ForTeam1[i]);
				item.setPlayerdata2(defensivePlayerData2ForTeam1[i]);
				item.setPlayerdata3(defensivePlayerData3ForTeam1[i]);
				item.setPlayerImage(defensivePlayerImages[i]);
				item.setPlayerName(defensivePlayerNamesForTeam1[i]);
				item.setplayerDetails(defensivePlayerNumbersForTeam1[i]);
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
			TextView statFirstTeamLabel = (TextView) findViewById(R.id.statFirstTeam);
			statFirstTeamLabel.setText("Packers");

			TextView statSecondTeamLabel = (TextView) findViewById(R.id.statSecondTeam);
			statSecondTeamLabel.setText("Patriots");

			List<StatsItem> rowItemsForTeamStats = new ArrayList<StatsItem>();
			List<StatsItem> rowItemsForKeyPlays = new ArrayList<StatsItem>();

			String[] teamStatsType = { "Scoring", "Passing yards",
					"Rushing Yards", "First Downs", "Third Down Efficiency",
					"Red Zone Efficiency", "Turnovers" };
			String[] teamStatsScore1 = { "21", "232", "248", "3", "6/13",
					"2/3", "2" };
			String[] teamStatsScore2 = { "21", "22", "108", "3", "6/13", "2/3",
					"2" };

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

					if (!NflUtils.isScoreBannerShrinked()) {
						shrinkScoreBanner();
					}
					if (!NflUtils.stubActivatedName.equalsIgnoreCase("statTab")
							&& (NflUtils.stubActivatedName != null)) {
						LinearLayout statTab = (LinearLayout) findViewById(R.id.statTab);
						statTab.setVisibility(View.VISIBLE);
						NflUtils.stubActivatedName = "statTab";

						LinearLayout otherTab = (LinearLayout) findViewById(R.id.playerTab);
						otherTab.setVisibility(View.INVISIBLE);
					}
					// NflUtils.stubActivatedName =
					// "teamstatsyardsMatchScoreboardStub";
					TextView statType = (TextView) (arg1
							.findViewById(R.id.stat_type));

					TextView statScore1 = (TextView) (arg1
							.findViewById(R.id.statScore1));
					TextView statScore2 = (TextView) (arg1
							.findViewById(R.id.statScore2));

					TextView statCategoryTeamLabelInPopUp = (TextView) findViewById(R.id.statCategory);
					statCategoryTeamLabelInPopUp.setText(statType.getText());

					TextView firstTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.firstTeamStatYardsScore);
					firstTeamStatYardsScoreLabelInPopUp.setText(statScore1
							.getText() + " Yards");

					TextView secondTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.secondTeamStatYardsScore);
					secondTeamStatYardsScoreLabelInPopUp.setText(statScore2
							.getText() + " Yards");

					// String highestScore=
					// NflUtils.getHighestNumber(statScore1.getText().toString(),
					// statScore2.getText().toString());
					// Integer firstTeamBarWidth=
					// NflUtils.getYardsBarWidth(statScore1.getText().toString(),
					// highestScore);
					// Integer secondTeamBarWidth=
					// NflUtils.getYardsBarWidth(statScore2.getText().toString(),
					// highestScore);

					TextView firstTeamStatYardsWidthLabel = (TextView) findViewById(R.id.firstTeamStatYardsWidth);
					firstTeamStatYardsWidthLabel.setWidth(Integer
							.parseInt(statScore1.getText().toString()));

					TextView secondTeamStatYardsWidthLabel = (TextView) findViewById(R.id.secondTeamStatYardsWidth);
					secondTeamStatYardsWidthLabel.setWidth(Integer
							.parseInt(statScore2.getText().toString()));

					TextView statFirstTeamLabel = (TextView) findViewById(R.id.statFirstTeam);
					TextView firstTeamNameStatYardsCategoryLabel = (TextView) findViewById(R.id.firstTeamNameStatYardsCategory);
					// firstTeamNameStatYardsCategoryLabel.setText(statFirstTeamLabel.getText());
					firstTeamNameStatYardsCategoryLabel.setText("GB");

					TextView statSecondTeamLabel = (TextView) findViewById(R.id.statSecondTeam);
					TextView secondTeamNameStatYardsCategoryLabel = (TextView) findViewById(R.id.secondTeamNameStatYardsCategory);
					// secondTeamNameStatYardsCategoryLabel.setText(statSecondTeamLabel.getText());
					secondTeamNameStatYardsCategoryLabel.setText("NE");
					popoverView.dissmissPopover(false);
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

			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {

					if (!NflUtils.isScoreBannerShrinked()) {
						shrinkScoreBanner();
					}
					if (!NflUtils.stubActivatedName
							.equalsIgnoreCase("playerTab")
							&& (NflUtils.stubActivatedName != null)) {
						LinearLayout playerTab = (LinearLayout) findViewById(R.id.playerTab);
						playerTab.setVisibility(View.VISIBLE);
						NflUtils.stubActivatedName = "playerTab";
						LinearLayout otherTab = (LinearLayout) findViewById(R.id.statTab);
						otherTab.setVisibility(View.INVISIBLE);
					}

					TextView popoverPlayerName = (TextView) (arg1
							.findViewById(R.id.popover_player_name));

					TextView selectedPlayerName = (TextView) findViewById(R.id.selectedPlayerName);
					selectedPlayerName.setText(popoverPlayerName.getText());

					TextView popoverPlayerDetails = (TextView) (arg1
							.findViewById(R.id.popover_player_details));

					TextView firstTeamStatYardsScoreLabelInPopUp = (TextView) findViewById(R.id.selectedPlayerDetails);
					firstTeamStatYardsScoreLabelInPopUp
							.setText(popoverPlayerDetails.getText());

					TextView popoverPlayerData1 = (TextView) (arg1
							.findViewById(R.id.popover_player_data1));

					TextView selectedPlayerGameDetailsIndexFirst = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexFirst);
					selectedPlayerGameDetailsIndexFirst
							.setText(popoverPlayerData1.getText());

					TextView popoverPlayerData2 = (TextView) (arg1
							.findViewById(R.id.popover_player_data2));

					TextView selectedPlayerGameDetailsIndexTwo = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexSecond);
					selectedPlayerGameDetailsIndexTwo
							.setText(popoverPlayerData2.getText());

					TextView popoverPlayerData3 = (TextView) (arg1
							.findViewById(R.id.popover_player_data3));

					TextView selectedPlayerGameDetailsIndexThree = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexThird);
					selectedPlayerGameDetailsIndexThree
							.setText(popoverPlayerData3.getText());

					TextView popoverPlayerData4 = (TextView) (arg1
							.findViewById(R.id.popover_player_data4));

					TextView selectedPlayerGameDetailsIndexFour = (TextView) findViewById(R.id.selectedPlayerGameDetailsIndexFour);
					selectedPlayerGameDetailsIndexFour
							.setText(popoverPlayerData4.getText());

					ImageView popover_player_pic = (ImageView) arg1
							.findViewById(R.id.popover_player_pic);

					ImageView selectedPlayerPic = (ImageView) findViewById(R.id.selectedPlayerPic);
					selectedPlayerPic.setImageDrawable(popover_player_pic
							.getDrawable());

					popoverView.dissmissPopover(false);
				}
			});

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

		DownloadImagesThreadPool downloadImagesThreadPool = new DownloadImagesThreadPool();

		TextView selectedCategoryTextView = (TextView) findViewById(R.id.selectedCategoryTextViewId);
		selectedCategoryTextView.setText("NEW ENGLAND PATRIOTS");

		for (int index = 0; index < 6; index++) {

			playCardParentLinearLayout.addView(getPlayCardView(context, index,
					playCardTopDetail.get(index),
					playCardBottomDetail.get(index), getResources(),
					downloadImagesThreadPool));
			View marginView = new View(context);
			marginView.setLayoutParams(new LayoutParams(20, 0));
			playCardParentLinearLayout.addView(marginView);
		}

	}

	private View getPlayCardView(Context context2, int index,
			String playCardTopDetail, String playCardBottomDetail,
			Resources resources,
			DownloadImagesThreadPool downloadImagesThreadPool) {

		return new PlayCardView(context2, index, playCardTopDetail,
				playCardBottomDetail, resources, this)
				.getPlayCard(downloadImagesThreadPool);
	}

	public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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

	public void makeTabInvisibleForEnlargingScoreBoard() {
		LinearLayout playerDetailsTab = (LinearLayout) findViewById(R.id.playerTab);
		LinearLayout statisticsDetailsTab = (LinearLayout) findViewById(R.id.statTab);
		if (playerDetailsTab.getVisibility() == View.VISIBLE) {
			playerDetailsTab.setVisibility(View.INVISIBLE);
		} else if (statisticsDetailsTab.getVisibility() == View.VISIBLE) {
			statisticsDetailsTab.setVisibility(View.INVISIBLE);
		}
	}

}