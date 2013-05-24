package com.liveclips.nfl.sp.VideoClip.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.liveclips.nfl.sp.AlertOption;
import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.adapter.NFLHighlightsLeadersListAdapter;
import com.liveclips.nfl.sp.adapter.NFLHighlightsWeekListAdapter;
import com.liveclips.nfl.sp.adapter.PassingLeaderListViewAdapter;
import com.liveclips.nfl.sp.adapter.SeparatedListAdapter;
import com.liveclips.nfl.sp.adapter.SeparatedSectionHeaderListAdapter;
import com.liveclips.nfl.sp.adapter.EmptyHeaderSeparatedListAdapter;
import com.liveclips.nfl.sp.adapter.StandingTeamListViewAdapter;

import com.liveclips.nfl.sp.model.LeadersTypeItem;
import com.liveclips.nfl.sp.model.PassingLeaderItem;
import com.liveclips.nfl.sp.model.SectionHeaderItem;
import com.liveclips.nfl.sp.model.TeamItem;
import com.liveclips.nfl.sp.model.WeekItem;
import com.liveclips.nfl.sp.popup.QuickAction;

public class NflHighlightActivity extends BaseActivity{
;
	private View vtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContent("NFL Highlights");
		setPopupMenu();
	   
	    setBanner();	
	
		for (int i = 0; i < 5; i++) {
			setVideoList();
		}
		  
		
	}
	
	/*
	     void setPopupMenu() {
		scoreboard=(LinearLayout)findViewById(R.id.popup_tab_layout);
		scoreboard.removeAllViews();
		container=mInflater.inflate(R.layout.nfl_highlighit_popup_menubar, null);
		
		Button week = (Button) container.findViewById(R.id.week);
		week.setOnClickListener(listener);
		
		Button leader = (Button)container.findViewById(R.id.leaders);
		leader.setOnClickListener(listener);
		
		Button milestones = (Button)container.findViewById(R.id.milestones);
		milestones.setOnClickListener(listener);
		
		Button standing = (Button)container.findViewById(R.id.standing);
		standing.setOnClickListener(listener);
		
		scoreboard.addView(container);
		Log.d("count", ""+scoreboard.getChildCount());
		
	}
	private void setBanner() {
		scoreboard=(LinearLayout)findViewById(R.id.score_board_container);
		scoreboard.removeAllViews();
		
		scoreboard.addView(mInflater.inflate(R.layout.nfl_highlight_banner, null));
		Log.d("count", ""+scoreboard.getChildCount());
		
	}
	private void setVideoList() {
		scoreboard=(LinearLayout)findViewById(R.id.video_list_container);
		//scoreboard.removeAllViews();
		LayoutInflater inflater = getLayoutInflater();
		scoreboard.addView(inflater.inflate(R.layout.video_list_item, null));
		Log.d("count", ""+scoreboard.getChildCount());
		
	}
	
	 private OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				switch (v.getId()) {
				
				case R.id.alert_control:
				
					
				Intent i2 = new Intent(context,AlertOption.class);
				startActivity(i2);
				overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
			
				break;
				case R.id.activity_game_back:
					onBackPressed();
					break;
		       case R.id.week:
		    	   container = mInflater.inflate(R.layout.nfl_highlights_popover_view_week,
							null);
		    	   ImageView doneImageView = (ImageView) container.findViewById(R.id.doneButtonImage);
					doneImageView.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							quickAction.dismiss();
							
							
						}
					});
		    	   
		    	   List<WeekItem> weekItems = new ArrayList<WeekItem>();
					WeekItem item1 = new WeekItem();
					item1.setWeekName("Week 1");
					weekItems.add(item1);
					
					WeekItem item2 = new WeekItem();
					item2.setWeekName("Week 2");
					weekItems.add(item2);
					
					WeekItem item3 = new WeekItem();
					item3.setWeekName("Week 3");
					weekItems.add(item3);
					
					WeekItem item4 = new WeekItem();
					item4.setWeekName("Week 4");
					weekItems.add(item4);
					
					listView = (ListView)container.findViewById(R.id.week_list);
					NFLHighlightsWeekListAdapter weekListAdapter = new NFLHighlightsWeekListAdapter(
							context, R.layout.nfl_highlights_popover_list_row_item_week, weekItems);

					listView.setAdapter(weekListAdapter);

		    	   
		    	   
		    	   
				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
				quickAction.show(v);
				break;

			  case R.id.leaders:
				  leadersPopup(v);
				  vtn=v;

				break;
		    	case R.id.milestones:

				//container = mInflater.inflate(R.layout.nfl_highlights_popover_view_passing_leaders,null);

				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
			//quickAction.show(v);
				break;
			 case R.id.standing:

				container = mInflater.inflate(
						R.layout.nfl_highlights_popover_view_standing, null);
				List<TeamItem> conferenceTeamItems = new ArrayList<TeamItem>();
				
				TeamItem teamItem1 = new TeamItem();
				teamItem1.setTeamName("New England");
				teamItem1.setLosses(0);
				teamItem1.setWins(5);
				teamItem1.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem1);
				
				TeamItem teamItem2 = new TeamItem();
				teamItem2.setTeamName("Ny Jets");
				teamItem2.setLosses(1);
				teamItem2.setWins(4);
				teamItem2.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem2);
				
				TeamItem teamItem3 = new TeamItem();
				teamItem3.setTeamName("Miami");
				teamItem3.setLosses(2);
				teamItem3.setWins(3);
				teamItem3.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem3);
				
				TeamItem teamItem4 = new TeamItem();
				teamItem4.setTeamName("Buffalo");
				teamItem4.setLosses(1);
				teamItem4.setWins(0);
				teamItem4.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem4);
				
				List<SectionHeaderItem> popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem headerItem = new SectionHeaderItem();
				headerItem.setHeadingText1("East Division");
				headerItem.setHeadingText2("W");
				headerItem.setHeadingText3("L");
				popover_nfl_standing_header.add(headerItem);
				
						
				
				SeparatedSectionHeaderListAdapter eastHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, popover_nfl_standing_header);
				
				EmptyHeaderSeparatedListAdapter separatedListAdapter = new EmptyHeaderSeparatedListAdapter(context, true);
				
				separatedListAdapter.addSection("0", eastHeaderListAdapter);
				separatedListAdapter.addSection("1", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				
				List<SectionHeaderItem> north_popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem northItem = new SectionHeaderItem();
				northItem.setHeadingText1("North Division");
				northItem.setHeadingText2("W");
				northItem.setHeadingText3("L");
				north_popover_nfl_standing_header.add(northItem);
				SeparatedSectionHeaderListAdapter northHeaderListAdapter = new SeparatedSectionHeaderListAdapter( context, R.layout.nfl_highlights_popover_header_standing, north_popover_nfl_standing_header);
				separatedListAdapter.addSection("7", northHeaderListAdapter);
				separatedListAdapter.addSection("2", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				
				List<SectionHeaderItem> southPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem south_HeaderItem = new SectionHeaderItem();
				south_HeaderItem.setHeadingText1("South Division");
				south_HeaderItem.setHeadingText2("W");
				south_HeaderItem.setHeadingText3("L");
				southPopover_nfl_standing_header.add(south_HeaderItem);
				SeparatedSectionHeaderListAdapter southHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, southPopover_nfl_standing_header);
				separatedListAdapter.addSection("8", southHeaderListAdapter);
				separatedListAdapter.addSection("3", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				List<SectionHeaderItem> westPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem westHeaderItem = new SectionHeaderItem();
				westHeaderItem.setHeadingText1("West Division");
				westHeaderItem.setHeadingText2("W");
				westHeaderItem.setHeadingText3("L");
				westPopover_nfl_standing_header.add(westHeaderItem);
				SeparatedSectionHeaderListAdapter westHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, westPopover_nfl_standing_header);
				separatedListAdapter.addSection("9", westHeaderListAdapter);
				separatedListAdapter.addSection("4", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				listView = (ListView) container.findViewById(R.id.standing_list);
				
				listView.setAdapter(separatedListAdapter);
			//	listView.setOnItemClickListener(standingItemClickListener);
				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
				quickAction.show(v);
			
			
			break;
				default:
					break;
				}
			}
		};
		
	private void leadersPopup(View v) {
		  container = mInflater.inflate(R.layout.nfl_highlights_popover_view_leaders,
					null);
		SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(context);
		List<LeadersTypeItem> offenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
		
		LeadersTypeItem item11 = new LeadersTypeItem();
		item11.setLeadersTypeName("Passing Yards");
		offenceLeadersTypeItems.add(item11);
		
		LeadersTypeItem item21= new LeadersTypeItem();
		item21.setLeadersTypeName("Rushing Yards");
		offenceLeadersTypeItems.add(item21);
		
		LeadersTypeItem item31 = new LeadersTypeItem();
		item31.setLeadersTypeName("Receiving Yards");
		offenceLeadersTypeItems.add(item31);
		
		LeadersTypeItem item41 = new LeadersTypeItem();
		item41.setLeadersTypeName("Touchdown");
		offenceLeadersTypeItems.add(item41);
		
		LeadersTypeItem item5 = new LeadersTypeItem();
		item5.setLeadersTypeName("Scoring");
		offenceLeadersTypeItems.add(item5);
		
		separatedListAdapter.addSection("Offense", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, offenceLeadersTypeItems));
		
		List<LeadersTypeItem> defenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
		
		LeadersTypeItem item6 = new LeadersTypeItem();
		item6.setLeadersTypeName("Sacks");
		defenceLeadersTypeItems.add(item6);
		
		LeadersTypeItem item7 = new LeadersTypeItem();
		item7.setLeadersTypeName("Interceptions");
		defenceLeadersTypeItems.add(item7);
		
		LeadersTypeItem item8 = new LeadersTypeItem();
		item8.setLeadersTypeName("Tackels");
		defenceLeadersTypeItems.add(item8);
		
		separatedListAdapter.addSection("Defense", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, defenceLeadersTypeItems));
		
		List<LeadersTypeItem> specialTeamLeadersTypeItems = new ArrayList<LeadersTypeItem>();
		
		LeadersTypeItem item9 = new LeadersTypeItem();
		item9.setLeadersTypeName("Punts");
		specialTeamLeadersTypeItems.add(item9);
		
		LeadersTypeItem item10 = new LeadersTypeItem();
		item10.setLeadersTypeName("Punts Returns");
		specialTeamLeadersTypeItems.add(item10);
		
		LeadersTypeItem item111 = new LeadersTypeItem();
		item111.setLeadersTypeName("Kick Returns");
		specialTeamLeadersTypeItems.add(item111);
		
		LeadersTypeItem item12 = new LeadersTypeItem();
		item12.setLeadersTypeName("Filed Goals");
		specialTeamLeadersTypeItems.add(item12);
		
		separatedListAdapter.addSection("Special Teams", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, specialTeamLeadersTypeItems));
		
		listView = (ListView) container.findViewById(R.id.leaders_list);
		
		listView.setAdapter(separatedListAdapter);
		listView.setOnItemClickListener(leadersItemClickListener);
		
		ImageView doneImageView = (ImageView) container.findViewById(R.id.doneButtonImage);

		doneImageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				quickAction.dismiss();
			}
		});
	  
	  
	  
	  
	quickAction = new QuickAction(context, QuickAction.VERTICAL);
	quickAction.addActionItem(container);
	quickAction.show(v);
	}
		private OnItemClickListener leadersItemClickListener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				quickAction.dismiss();
				leadersPopup2(vtn);
				
				
				
				
			}
		
		};
		private void leadersPopup2(View v) {
			
			
List<PassingLeaderItem> items = new ArrayList<PassingLeaderItem>();
container = mInflater.inflate(R.layout.nfl_highlights_popover_view_passing_leaders,
		null); 
			PassingLeaderItem leaderItem1 = new PassingLeaderItem();
			leaderItem1.setLeaderName("Loe Flacco");
			leaderItem1.setYards(221);
			TeamItem leaderTeam1 = new TeamItem();
			leaderTeam1.setTeamName("BAL");
			leaderItem1.setLeaderTeam(leaderTeam1);
			items.add(leaderItem1);
			
			PassingLeaderItem leaderItem2 = new PassingLeaderItem();
			leaderItem2.setLeaderName("Tom Brady");
			leaderItem2.setYards(201);
			TeamItem leaderTeam2 = new TeamItem();
			leaderTeam2.setTeamName("NE");
			leaderItem2.setLeaderTeam(leaderTeam2);
			items.add(leaderItem2);
			
			PassingLeaderItem leaderItem3 = new PassingLeaderItem();
			leaderItem3.setLeaderName("Mat Rayana");
			leaderItem3.setYards(181);
			TeamItem leaderTeam3 = new TeamItem();
			leaderTeam3.setTeamName("EAI");
			leaderItem3.setLeaderTeam(leaderTeam3);
			items.add(leaderItem3);
					
			listView = (ListView) container.findViewById(R.id.passing_leaders_list);
			PassingLeaderListViewAdapter adapter = new PassingLeaderListViewAdapter(context, R.layout.nfl_highlights_popover_list_row_item_passing_leader, items);
			listView.setAdapter(adapter);

			ImageView backImageView = (ImageView) container.findViewById(R.id.nfl_highlights_popover_view_passing_leaders_backButtonImage);
			
			backImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					quickAction.dismiss(); 
					//leadersPopup(vtn);
				}
			});
			
			ImageView doneImageView = (ImageView) container.findViewById(R.id.doneButtonImage);

			doneImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					quickAction.dismiss();
				}
			});
			quickAction = new QuickAction(context, QuickAction.VERTICAL);
			quickAction.addActionItem(container);
			quickAction.show(v);
			
		//	listView.setOnItemClickListener(passingLeadersItemClickListener);
				*/
		
	@Override
	protected void setVideoList() {
		// TODO Auto-generated method stub
		linearLayout=(LinearLayout)findViewById(R.id.video_list_container);
		//scoreboard.removeAllViews();
		//LayoutInflater inflater = getLayoutInflater();
		linearLayout.addView(mInflater.inflate(R.layout.video_list_item, null));
	}
	@Override
	protected void setPopupMenu() {
		// TODO Auto-generated method stub
		linearLayout=(LinearLayout)findViewById(R.id.popup_tab_layout);
		linearLayout.removeAllViews();
		//LayoutInflater inflater = getLayoutInflater();
		container=mInflater.inflate(R.layout.nfl_highlighit_popup_menubar, null);
		
		Button week = (Button) container.findViewById(R.id.week);
		week.setOnClickListener(listener);
		
		Button leader = (Button)container.findViewById(R.id.leaders);
		leader.setOnClickListener(listener);
		
		Button milestones = (Button)container.findViewById(R.id.milestones);
		milestones.setOnClickListener(listener);
		
		Button standing = (Button)container.findViewById(R.id.standing);
		standing.setOnClickListener(listener);
		
		linearLayout.addView(container);
	}
	@Override
	protected void setBanner() {
		// TODO Auto-generated method stub
		linearLayout=(LinearLayout)findViewById(R.id.score_board_container);
		linearLayout.removeAllViews();
		
		linearLayout.addView(mInflater.inflate(R.layout.nfl_highlight_banner, null));
	}
	 private OnClickListener listener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				switch (v.getId()) {
				
				case R.id.alert_control:
				
					
				Intent i2 = new Intent(context,AlertOption.class);
				startActivity(i2);
				overridePendingTransition( R.anim.slide_in_up, R.anim.slide_out_up );
			
				break;
				case R.id.activity_game_back:
					onBackPressed();
					break;
		       case R.id.week:
		    	   container = mInflater.inflate(R.layout.nfl_highlights_popover_view_week,
							null);
		    	   ImageView doneImageView = (ImageView) container.findViewById(R.id.doneButtonImage);
					doneImageView.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							quickAction.dismiss();
							
							
						}
					});
		    	   
		    	   List<WeekItem> weekItems = new ArrayList<WeekItem>();
					WeekItem item1 = new WeekItem();
					item1.setWeekName("Week 1");
					weekItems.add(item1);
					
					WeekItem item2 = new WeekItem();
					item2.setWeekName("Week 2");
					weekItems.add(item2);
					
					WeekItem item3 = new WeekItem();
					item3.setWeekName("Week 3");
					weekItems.add(item3);
					
					WeekItem item4 = new WeekItem();
					item4.setWeekName("Week 4");
					weekItems.add(item4);
					
					listView = (ListView)container.findViewById(R.id.week_list);
					NFLHighlightsWeekListAdapter weekListAdapter = new NFLHighlightsWeekListAdapter(
							context, R.layout.nfl_highlights_popover_list_row_item_week, weekItems);

					listView.setAdapter(weekListAdapter);

		    	   
		    	   
		    	   
				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
				quickAction.show(v);
				break;

			  case R.id.leaders:
				  leadersPopup(v);
				  vtn=v;

				break;
		    	case R.id.milestones:

				//container = mInflater.inflate(R.layout.nfl_highlights_popover_view_passing_leaders,null);

				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
			//quickAction.show(v);
				break;
			 case R.id.standing:

				container = mInflater.inflate(
						R.layout.nfl_highlights_popover_view_standing, null);
				List<TeamItem> conferenceTeamItems = new ArrayList<TeamItem>();
				
				TeamItem teamItem1 = new TeamItem();
				teamItem1.setTeamName("New England");
				teamItem1.setLosses(0);
				teamItem1.setWins(5);
				teamItem1.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem1);
				
				TeamItem teamItem2 = new TeamItem();
				teamItem2.setTeamName("Ny Jets");
				teamItem2.setLosses(1);
				teamItem2.setWins(4);
				teamItem2.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem2);
				
				TeamItem teamItem3 = new TeamItem();
				teamItem3.setTeamName("Miami");
				teamItem3.setLosses(2);
				teamItem3.setWins(3);
				teamItem3.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem3);
				
				TeamItem teamItem4 = new TeamItem();
				teamItem4.setTeamName("Buffalo");
				teamItem4.setLosses(1);
				teamItem4.setWins(0);
				teamItem4.setTeamLogo(R.drawable.bills);
				conferenceTeamItems.add(teamItem4);
				
				List<SectionHeaderItem> popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem headerItem = new SectionHeaderItem();
				headerItem.setHeadingText1("East Division");
				headerItem.setHeadingText2("W");
				headerItem.setHeadingText3("L");
				popover_nfl_standing_header.add(headerItem);
				
						
				
				SeparatedSectionHeaderListAdapter eastHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, popover_nfl_standing_header);
				
				EmptyHeaderSeparatedListAdapter separatedListAdapter = new EmptyHeaderSeparatedListAdapter(context, true);
				
				separatedListAdapter.addSection("0", eastHeaderListAdapter);
				separatedListAdapter.addSection("1", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				
				List<SectionHeaderItem> north_popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem northItem = new SectionHeaderItem();
				northItem.setHeadingText1("North Division");
				northItem.setHeadingText2("W");
				northItem.setHeadingText3("L");
				north_popover_nfl_standing_header.add(northItem);
				SeparatedSectionHeaderListAdapter northHeaderListAdapter = new SeparatedSectionHeaderListAdapter( context, R.layout.nfl_highlights_popover_header_standing, north_popover_nfl_standing_header);
				separatedListAdapter.addSection("7", northHeaderListAdapter);
				separatedListAdapter.addSection("2", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				
				List<SectionHeaderItem> southPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem south_HeaderItem = new SectionHeaderItem();
				south_HeaderItem.setHeadingText1("South Division");
				south_HeaderItem.setHeadingText2("W");
				south_HeaderItem.setHeadingText3("L");
				southPopover_nfl_standing_header.add(south_HeaderItem);
				SeparatedSectionHeaderListAdapter southHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, southPopover_nfl_standing_header);
				separatedListAdapter.addSection("8", southHeaderListAdapter);
				separatedListAdapter.addSection("3", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				List<SectionHeaderItem> westPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
				SectionHeaderItem westHeaderItem = new SectionHeaderItem();
				westHeaderItem.setHeadingText1("West Division");
				westHeaderItem.setHeadingText2("W");
				westHeaderItem.setHeadingText3("L");
				westPopover_nfl_standing_header.add(westHeaderItem);
				SeparatedSectionHeaderListAdapter westHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, westPopover_nfl_standing_header);
				separatedListAdapter.addSection("9", westHeaderListAdapter);
				separatedListAdapter.addSection("4", new StandingTeamListViewAdapter(context, R.layout.common_division_nfl_highlight_list_row_item_standing, conferenceTeamItems));
				
				listView = (ListView) container.findViewById(R.id.standing_list);
				
				listView.setAdapter(separatedListAdapter);
			//	listView.setOnItemClickListener(standingItemClickListener);
				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
				quickAction.show(v);
			
			
			break;
				default:
					break;
				}
			}
		};	
		
		
		
		private void leadersPopup(View v) {
			  container = mInflater.inflate(R.layout.nfl_highlights_popover_view_leaders,
						null);
			SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(context);
			List<LeadersTypeItem> offenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
			
			LeadersTypeItem item11 = new LeadersTypeItem();
			item11.setLeadersTypeName("Passing Yards");
			offenceLeadersTypeItems.add(item11);
			
			LeadersTypeItem item21= new LeadersTypeItem();
			item21.setLeadersTypeName("Rushing Yards");
			offenceLeadersTypeItems.add(item21);
			
			LeadersTypeItem item31 = new LeadersTypeItem();
			item31.setLeadersTypeName("Receiving Yards");
			offenceLeadersTypeItems.add(item31);
			
			LeadersTypeItem item41 = new LeadersTypeItem();
			item41.setLeadersTypeName("Touchdown");
			offenceLeadersTypeItems.add(item41);
			
			LeadersTypeItem item5 = new LeadersTypeItem();
			item5.setLeadersTypeName("Scoring");
			offenceLeadersTypeItems.add(item5);
			
			separatedListAdapter.addSection("Offense", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, offenceLeadersTypeItems));
			
			List<LeadersTypeItem> defenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
			
			LeadersTypeItem item6 = new LeadersTypeItem();
			item6.setLeadersTypeName("Sacks");
			defenceLeadersTypeItems.add(item6);
			
			LeadersTypeItem item7 = new LeadersTypeItem();
			item7.setLeadersTypeName("Interceptions");
			defenceLeadersTypeItems.add(item7);
			
			LeadersTypeItem item8 = new LeadersTypeItem();
			item8.setLeadersTypeName("Tackels");
			defenceLeadersTypeItems.add(item8);
			
			separatedListAdapter.addSection("Defense", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, defenceLeadersTypeItems));
			
			List<LeadersTypeItem> specialTeamLeadersTypeItems = new ArrayList<LeadersTypeItem>();
			
			LeadersTypeItem item9 = new LeadersTypeItem();
			item9.setLeadersTypeName("Punts");
			specialTeamLeadersTypeItems.add(item9);
			
			LeadersTypeItem item10 = new LeadersTypeItem();
			item10.setLeadersTypeName("Punts Returns");
			specialTeamLeadersTypeItems.add(item10);
			
			LeadersTypeItem item111 = new LeadersTypeItem();
			item111.setLeadersTypeName("Kick Returns");
			specialTeamLeadersTypeItems.add(item111);
			
			LeadersTypeItem item12 = new LeadersTypeItem();
			item12.setLeadersTypeName("Filed Goals");
			specialTeamLeadersTypeItems.add(item12);
			
			separatedListAdapter.addSection("Special Teams", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, specialTeamLeadersTypeItems));
			
			listView = (ListView) container.findViewById(R.id.leaders_list);
			
			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(leadersItemClickListener);
			
			ImageView doneImageView = (ImageView) container.findViewById(R.id.doneButtonImage);

			doneImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					quickAction.dismiss();
				}
			});
		  
		  
		  
		  
		quickAction = new QuickAction(context, QuickAction.VERTICAL);
		quickAction.addActionItem(container);
		quickAction.show(v);
		}
			private OnItemClickListener leadersItemClickListener = new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					quickAction.dismiss();
					leadersPopup2(vtn);
					
					
					
					
				}
			
			};
			private void leadersPopup2(View v) {
				
				
	List<PassingLeaderItem> items = new ArrayList<PassingLeaderItem>();
	container = mInflater.inflate(R.layout.nfl_highlights_popover_view_passing_leaders,
			null); 
				PassingLeaderItem leaderItem1 = new PassingLeaderItem();
				leaderItem1.setLeaderName("Loe Flacco");
				leaderItem1.setYards(221);
				TeamItem leaderTeam1 = new TeamItem();
				leaderTeam1.setTeamName("BAL");
				leaderItem1.setLeaderTeam(leaderTeam1);
				items.add(leaderItem1);
				
				PassingLeaderItem leaderItem2 = new PassingLeaderItem();
				leaderItem2.setLeaderName("Tom Brady");
				leaderItem2.setYards(201);
				TeamItem leaderTeam2 = new TeamItem();
				leaderTeam2.setTeamName("NE");
				leaderItem2.setLeaderTeam(leaderTeam2);
				items.add(leaderItem2);
				
				PassingLeaderItem leaderItem3 = new PassingLeaderItem();
				leaderItem3.setLeaderName("Mat Rayana");
				leaderItem3.setYards(181);
				TeamItem leaderTeam3 = new TeamItem();
				leaderTeam3.setTeamName("EAI");
				leaderItem3.setLeaderTeam(leaderTeam3);
				items.add(leaderItem3);
						
				listView = (ListView) container.findViewById(R.id.passing_leaders_list);
				PassingLeaderListViewAdapter adapter = new PassingLeaderListViewAdapter(context, R.layout.nfl_highlights_popover_list_row_item_passing_leader, items);
				listView.setAdapter(adapter);

				ImageView backImageView = (ImageView) container.findViewById(R.id.nfl_highlights_popover_view_passing_leaders_backButtonImage);
				
				backImageView.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						quickAction.dismiss(); 
						//leadersPopup(vtn);
					}
				});
				
				ImageView doneImageView = (ImageView) container.findViewById(R.id.doneButtonImage);

				doneImageView.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						quickAction.dismiss();
					}
				});
				quickAction = new QuickAction(context, QuickAction.VERTICAL);
				quickAction.addActionItem(container);
				quickAction.show(v);
			}
}
