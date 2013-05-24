package com.liveclips.nfl.sp.VideoClip.Activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.adapter.DriveListViewAdapter;
import com.liveclips.nfl.sp.adapter.PlayerListViewAdapter;
import com.liveclips.nfl.sp.adapter.ScheduleListViewAdapter;
import com.liveclips.nfl.sp.adapter.SeparatedListAdapter;
import com.liveclips.nfl.sp.adapter.StatsListViewAdapter;
import com.liveclips.nfl.sp.model.DriveItem;
import com.liveclips.nfl.sp.model.PlayerItem;
import com.liveclips.nfl.sp.model.ScheduleItem;
import com.liveclips.nfl.sp.model.StatsItem;
import com.liveclips.nfl.sp.popup.QuickAction;

public class NewEnglandPatriotsActivity extends BaseActivity{

	private Button  team1BtnPlayers;
	private Button team2BtnPlayers ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=this;
		setContentView(R.layout.activity_game);
		setContent("New England Patriots");
		setBanner();
		setPopupMenu();
	
		for (int i = 0; i < 5; i++) {
			setVideoList();
		}
		  
		
	}

	private OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				switch (v.getId()) {
				
			
	     	case R.id.activity_game_schedule:
			
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
		
			   container = mInflater.inflate(R.layout.popover_game_schedule_view, null);
			//container = mInflater.inflate(R.layout.main, null);
			
			 listView = (ListView)container.findViewById(R.id.game_schedule_list);
			ScheduleListViewAdapter scheduleListViewAdapter = new ScheduleListViewAdapter(context,
					R.layout.popover_game_schedule_list_item, rowItems);
			listView.setAdapter(scheduleListViewAdapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					quickAction.dismiss();
				}
			});
         	 quickAction=new QuickAction(context,  QuickAction.VERTICAL);
			   quickAction.addActionItem(container);
	        quickAction.show(v);
					break;
		
		
		
		case R.id.activity_game_drive:
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

			SeparatedListAdapter adapter = new SeparatedListAdapter(context);
			adapter.addSection("4TH QUARTER", new DriveListViewAdapter(context,
					R.layout.popover_game_drive_list_item, rowItemsForQ4));
			adapter.addSection("3TH QUARTER", new DriveListViewAdapter(context,
					R.layout.popover_game_drive_list_item, rowItemsForQ3));
			adapter.addSection("2TH QUARTER", new DriveListViewAdapter(context,
					R.layout.popover_game_drive_list_item, rowItemsForQ2));
			adapter.addSection("1TH QUARTER", new DriveListViewAdapter(context,
					R.layout.popover_game_drive_list_item, rowItemsForQ1));
			
			 container = mInflater.inflate(R.layout.popover_game_drives_view, null);
			
			
			listView = (ListView)container.findViewById(R.id.game_drive_list);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// Toast.makeText(this, "text", Toast.LENGTH_LONG).show();
					Log.d("lineno", String.valueOf(arg2));
					TextView txt = (TextView) (arg1
							.findViewById(R.id.team_shortname));

					Log.d("lineno", txt.getText().toString());
					quickAction.dismiss();

				}
			});
			listView.setAdapter(adapter);	
			 quickAction=new QuickAction(context,  QuickAction.VERTICAL);
			   quickAction.addActionItem(container);
	        quickAction.show(v);
			break;
		case R.id.activity_game_stats:
			
			
			 container = mInflater.inflate(R.layout.popover_game_stats_view, null);
			
			TextView statFirstTeamLabel = (TextView)container.findViewById(R.id.statFirstTeam);
			statFirstTeamLabel.setText("Packers");
			
			TextView statSecondTeamLabel = (TextView)container.findViewById(R.id.statSecondTeam);
			statSecondTeamLabel.setText("Patriots");

			List<StatsItem> rowItemsForTeamStats = new ArrayList<StatsItem>();
			List<StatsItem> rowItemsForKeyPlays = new ArrayList<StatsItem>();

			String[] teamStatsType = { "Scoring", "Passing yards",
					"Rushing Yards", "First Downs", "Third Down Efficiency",
					"Red Zone Efficiency", "Turnovers" };
			String[] teamStatsScore1 = { "21", "132", "248", "3", "13",
					"20", "2" };
			String[] teamStatsScore2 = { "41", "232", "148", "30", "6",
					"3", "9" };

			String[] keyPlaysStatstype = { "Long Passes", "Long Rushes",
					"Defensive Plays" };
			String[] keyPlaysStatsScore1 = { "12", "3", "80" };
			String[] keyPlaysStatsScore2 = { "60", "70", "7" };

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
			

			SeparatedListAdapter statsadapter = new SeparatedListAdapter(context);
			statsadapter.addSection("Team Plays",
					new StatsListViewAdapter(context,
							R.layout.popover_game_stats_list_item,
							rowItemsForTeamStats));
			statsadapter.addSection("Key Plays", new StatsListViewAdapter(context,
					R.layout.popover_game_stats_list_item, rowItemsForKeyPlays));
			listView = (ListView)container.findViewById(R.id.game_stats_list);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// Toast.makeText(this, "text", Toast.LENGTH_LONG).show();
					Log.d("lineno", String.valueOf(arg2)+" ");
					
					
					
				
					TextView statType = (TextView) (arg1
							.findViewById(R.id.stat_type));
					
					TextView statScore1 = (TextView) (arg1
							.findViewById(R.id.statScore1));
					TextView statScore2 = (TextView) (arg1
							.findViewById(R.id.statScore2));
					
					linearLayout=(LinearLayout)findViewById(R.id.score_board_layout);
					linearLayout.removeAllViews();
					container=mInflater.inflate(R.layout.teamstatsyards_match_scoreboard, null);
				
					
					
					TextView statCategoryTeamLabelInPopUp = (TextView) container.findViewById(R.id.statCategory);
					statCategoryTeamLabelInPopUp.setText(statType.getText());
					
					TextView firstTeamStatYardsScoreLabelInPopUp = (TextView) container.findViewById(R.id.firstTeamStatYardsScore);
					firstTeamStatYardsScoreLabelInPopUp.setText(statScore1.getText() + " Yards");
					
					TextView secondTeamStatYardsScoreLabelInPopUp = (TextView) container.findViewById(R.id.secondTeamStatYardsScore);
					secondTeamStatYardsScoreLabelInPopUp.setText(statScore2.getText() + " Yards");
			
					TextView firstTeamStatYardsWidthLabel = (TextView) container.findViewById(R.id.firstTeamStatYardsWidth);
					firstTeamStatYardsWidthLabel.setWidth(Integer.parseInt(statScore1.getText().toString()));
					
					TextView secondTeamStatYardsWidthLabel = (TextView) container.findViewById(R.id.secondTeamStatYardsWidth);
					secondTeamStatYardsWidthLabel.setWidth(Integer.parseInt(statScore2.getText().toString()));
					linearLayout.addView(container);
					quickAction.dismiss();
					
			}
			});
			listView.setAdapter(statsadapter);
			 quickAction=new QuickAction(context,  QuickAction.VERTICAL);
			   quickAction.addActionItem(container);
	        quickAction.show(v);
			break;
		case R.id.activity_game_players:
			
			container = mInflater.inflate(R.layout.popover_game_player_view, null);
			
			
			final SeparatedListAdapter adapter1 = new SeparatedListAdapter(context);
			adapter1.addSection(
					"OFFENSE",
					new PlayerListViewAdapter(context,
							R.layout.popover_game_player_list_item, getPlayers(
									"team1", "offensive")));
			adapter1.addSection("DEFENSE", new PlayerListViewAdapter(
					NewEnglandPatriotsActivity.this, R.layout.popover_game_player_list_item,
					getPlayers("team1", "defensive")));

			listView = (ListView) container.findViewById(R.id.game_player_list);
			
			listView.setOnItemClickListener(new OnItemClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					
					linearLayout=(LinearLayout)findViewById(R.id.score_board_layout);
					linearLayout.removeAllViews();
					container=mInflater.inflate(R.layout.players_performance_match_scoreboard, null);
					
					
				//******	
					TextView popoverPlayerName = (TextView) (arg1
							.findViewById(R.id.popover_player_name));
					
					TextView selectedPlayerName = (TextView) container.findViewById(R.id.selectedPlayerName);
					selectedPlayerName.setText(popoverPlayerName.getText());
				//******	
					TextView popoverPlayerDetails = (TextView) (arg1
							.findViewById(R.id.popover_player_details));
				
					
					TextView firstTeamStatYardsScoreLabelInPopUp = (TextView) container.findViewById(R.id.selectedPlayerDetails);
					firstTeamStatYardsScoreLabelInPopUp.setText(popoverPlayerDetails.getText());
				//*****	
					TextView popoverPlayerData1 = (TextView) (arg1
							.findViewById(R.id.popover_player_data1));
					
					TextView selectedPlayerGameDetailsIndexFirst = (TextView) container.findViewById(R.id.selectedPlayerGameDetailsIndexFirst);
					selectedPlayerGameDetailsIndexFirst.setText(popoverPlayerData1.getText());
				//*****	
					TextView popoverPlayerData2 = (TextView) (arg1
							.findViewById(R.id.popover_player_data2));
					
					TextView selectedPlayerGameDetailsIndexTwo = (TextView) container.findViewById(R.id.selectedPlayerGameDetailsIndexSecond);
					selectedPlayerGameDetailsIndexTwo.setText(popoverPlayerData2.getText());
				//*****	
					TextView popoverPlayerData3 = (TextView) (arg1
							.findViewById(R.id.popover_player_data3));
					
					TextView selectedPlayerGameDetailsIndexThree = (TextView) container.findViewById(R.id.selectedPlayerGameDetailsIndexThird);
					selectedPlayerGameDetailsIndexThree.setText(popoverPlayerData3.getText());
				//*****	
					TextView popoverPlayerData4 = (TextView) (arg1
							.findViewById(R.id.popover_player_data4));
					
					TextView selectedPlayerGameDetailsIndexFour = (TextView) container.findViewById(R.id.selectedPlayerGameDetailsIndexFour);
					selectedPlayerGameDetailsIndexFour.setText(popoverPlayerData4.getText());
			 //******
					ImageView playerImage = (ImageView)(arg1
							.findViewById(R.id.popover_player_pic));
					ImageView selectedPlayerGameDetailsImage = (ImageView)
							container.findViewById(R.id.selectedPlayerPic);
					selectedPlayerGameDetailsImage.setImageDrawable(playerImage.getDrawable());
					
					linearLayout.addView(container);
					quickAction.dismiss();
				}
			});
			
			
			listView.setAdapter(adapter1);

			
		    team1BtnPlayers = (Button) container.findViewById(R.id.team1BtnPlayers);
		    team1BtnPlayers.setSelected(true);
			team1BtnPlayers.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					adapter1.removeAllSections();
					adapter1.addSection("OFFENSE",
							new PlayerListViewAdapter(context,
									R.layout.popover_game_player_list_item,
									getPlayers("team1", "offensive")));
					adapter1.addSection("DEFENSE",
							new PlayerListViewAdapter(context,
									R.layout.popover_game_player_list_item,
									getPlayers("team1", "defensive")));
					adapter1.notifyDataSetChanged();
					listView.setSelection(0);
					
					team1BtnPlayers.setBackgroundResource(R.drawable.popup_player_team_button_selected_background);
					team2BtnPlayers.setBackgroundResource(R.drawable.popup_player_team_button_background);
					  	
					/* team1Btn.setText("PACKERS"); */
				}
			});
			team2BtnPlayers = (Button) container.findViewById(R.id.team2BtnPlayers);
			team2BtnPlayers.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					adapter1.removeAllSections();
					adapter1.addSection("OFFENSE",
							new PlayerListViewAdapter(context,
									R.layout.popover_game_player_list_item,
									getPlayers("team2", "offensive")));
					adapter1.addSection("DEFENSE",
							new PlayerListViewAdapter(context,
									R.layout.popover_game_player_list_item,
									getPlayers("team2", "defensive")));
					adapter1.notifyDataSetChanged();
					listView.setSelection(0);
                    
					team2BtnPlayers.setBackgroundResource(R.drawable.popup_player_team_button_selected_background);
					team1BtnPlayers.setBackgroundResource(R.drawable.popup_player_team_button_background);
					  
				}
			});

			quickAction=new QuickAction(context,  QuickAction.VERTICAL);
			quickAction.addActionItem(container);
	        quickAction.show(v);
			
			
			
			break;
				default:
					break;
				}
			}
		};
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

				int offensivPlayerImagesForTeam1[] = { R.drawable.brandonspikes,
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
					item.setplayerDetails(offensivePlayerNumbersForTeam1[i]);
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
					item.setplayerDetails(defensivePlayerNumbersForTeam1[i]);
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
						R.drawable.brandonspikes };

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
		protected void setVideoList() {
			// TODO Auto-generated method stub
			linearLayout=(LinearLayout)findViewById(R.id.video_list_container);
			//scoreboard.removeAllViews();
			LayoutInflater inflater = getLayoutInflater();
			linearLayout.addView(inflater.inflate(R.layout.video_list_item, null));
		}

		@Override
		protected void setPopupMenu() {
			// TODO Auto-generated method stub
			linearLayout=(LinearLayout)findViewById(R.id.popup_tab_layout);
			linearLayout.removeAllViews();
			container=mInflater.inflate(R.layout.new_england_patriots_popup_menubar, null);
			Button schedule = (Button)container.findViewById(R.id.activity_game_schedule);
			schedule.setOnClickListener(listener);
			Button drives = (Button)container.findViewById(R.id.activity_game_drive);
			drives.setOnClickListener(listener);
			Button stats = (Button)container.findViewById(R.id.activity_game_stats);
			stats.setOnClickListener(listener);
			Button players = (Button)container.findViewById(R.id.activity_game_players);
			players.setOnClickListener(listener);
			
			linearLayout.addView(container);
		}

		@Override
		protected void setBanner() {
			// TODO Auto-generated method stub
			linearLayout=(LinearLayout)findViewById(R.id.video_list_container);
			//scoreboard.removeAllViews();
			LayoutInflater inflater = getLayoutInflater();
			linearLayout.addView(inflater.inflate(R.layout.video_list_item, null));
		}
		
		
}
