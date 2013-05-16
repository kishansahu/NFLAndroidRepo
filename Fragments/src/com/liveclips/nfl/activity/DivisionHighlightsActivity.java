/**
 * 
 */
package com.liveclips.nfl.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.EmptyHeaderSeparatedListAdapter;
import com.liveclips.nfl.adapter.NFLHighlightsLeadersListAdapter;
import com.liveclips.nfl.adapter.NFLHighlightsWeekListAdapter;
import com.liveclips.nfl.adapter.PassingLeaderListViewAdapter;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.adapter.SeparatedSectionHeaderListAdapter;
import com.liveclips.nfl.adapter.StandingTeamListViewAdapter;
import com.liveclips.nfl.model.ConferenceItem;
import com.liveclips.nfl.model.LeadersTypeItem;
import com.liveclips.nfl.model.PassingLeaderItem;
import com.liveclips.nfl.model.SectionHeaderItem;
import com.liveclips.nfl.model.TeamItem;
import com.liveclips.nfl.model.WeekItem;
import com.liveclips.nfl.popover.PopoverView;
import com.liveclips.nfl.popover.PopoverView.PopoverViewDelegate;
import com.liveclips.nfl.utils.DownloadImagesThreadPool;
import com.liveclips.nfl.utils.PlayCardView;

/**
 * @author mohitkumar
 *
 */
public class DivisionHighlightsActivity extends BaseActivity implements
PopoverViewDelegate {
	
	private ArrayList<String> playCardTopDetail = new ArrayList<String>();

	private ArrayList<String> playCardBottomDetail = new ArrayList<String>();

	private Context context;

	private int playCardVideoId = 0;
	
	private PopoverView popoverView;
	
	private ListView listView;

	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_division_highlights);

		context = this;

		createCustomActionBar();
		
		prepareVideoView();

		
	}
	
	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.nfl_division_menu_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		LinearLayout weekLinearLayout = (LinearLayout) mActionBarView
				.findViewById(R.id.weekLayout);
		weekLinearLayout.setOnClickListener(weekOnClickListener);

		TextView leaderLinearLayout = (TextView) mActionBarView
				.findViewById(R.id.leadersLayout);
		leaderLinearLayout.setOnClickListener(leaderOnClickListener);

		TextView milestonesLinearLayout = (TextView) mActionBarView
				.findViewById(R.id.milestonesLayout);
		milestonesLinearLayout.setOnClickListener(milestonesOnClickListener);

		TextView standingLinearLayout = (TextView) mActionBarView
				.findViewById(R.id.standingLayout);
		standingLinearLayout.setOnClickListener(standingOnClickListener);
		
	}
	
	@Override
	protected void onResume() {
		ConferenceItem conferenceItem = (ConferenceItem) getIntent().getSerializableExtra("conference");
		setCurrentConferenceInfo(conferenceItem);
		super.onResume();
	}

	private void setCurrentConferenceInfo(ConferenceItem conferenceItem) {
		
		TextView conferenceTextView = (TextView) findViewById(R.id.conference_name);
		conferenceTextView.setText(conferenceItem.getConferenceName());
		
		ImageView team_1_imageView = (ImageView) findViewById(R.id.team_1_logo);
		//team_1_imageView.setImageResource(conferenceItem.getTeam_1().getTeamLogo());
		//team_1_imageView.setImageDrawable(conferenceItem.getTeam_1().getDrawable());
		TextView team_1_textView = (TextView) findViewById(R.id.team_1_name);
		team_1_textView.setText(conferenceItem.getTeam_1().getTeamName());
		
		ImageView team_2_imageView = (ImageView) findViewById(R.id.team_2_logo);
		//team_2_imageView.setImageResource(conferenceItem.getTeam_2().getTeamLogo());
		TextView team_2_textView = (TextView) findViewById(R.id.team_2_name);
		team_2_textView.setText(conferenceItem.getTeam_2().getTeamName());
	//	team_2_imageView.setImageDrawable(conferenceItem.getTeam_2().getDrawable());
		
		ImageView team_3_imageView = (ImageView) findViewById(R.id.team_3_logo);
		//team_3_imageView.setImageResource(conferenceItem.getTeam_3().getTeamLogo());
		TextView team_3_textView = (TextView) findViewById(R.id.team_3_name);
		team_3_textView.setText(conferenceItem.getTeam_3().getTeamName());
		//team_3_imageView.setImageDrawable(conferenceItem.getTeam_3().getDrawable());
		
		ImageView team_4_imageView = (ImageView) findViewById(R.id.team_4_logo);
		//team_4_imageView.setImageResource(conferenceItem.getTeam_4().getTeamLogo());
		TextView team_4_textView = (TextView) findViewById(R.id.team_4_name);
		team_4_textView.setText(conferenceItem.getTeam_4().getTeamName());
		//team_4_imageView.setImageDrawable(conferenceItem.getTeam_4().getDrawable());
	}
	
	private void prepareVideoView() {

		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		playCardParentLinearLayout.removeAllViews();

		DownloadImagesThreadPool downloadImagesThreadPool = new
				 DownloadImagesThreadPool();

				TextView selectedCategoryTextView = (TextView) findViewById(R.id.selectedCategoryTextViewId);
				selectedCategoryTextView.setText("Division Highlights");
		for (int index = 0; index < 4; index++) {
			playCardTopDetail.add("Video Text on Top " + index);

			playCardBottomDetail.add("Video Text on Bottom " + index);
			playCardParentLinearLayout.addView(getPlayCardView(context, index,
					playCardTopDetail.get(index),
					playCardBottomDetail.get(index), getResources(),downloadImagesThreadPool));
			View marginView = new View(context);
			marginView.setLayoutParams(new LayoutParams(20, 0));
			playCardParentLinearLayout.addView(marginView);
		}
	}
	
	private View getPlayCardView(Context context2, int index,
			String playCardTopDetail, String playCardBottomDetail,
			Resources resources,DownloadImagesThreadPool downloadImagesThreadPool) {

		return new PlayCardView(context2, index, playCardTopDetail,
				playCardBottomDetail, resources, this).getPlayCard(downloadImagesThreadPool);
	}
	
	/**
	 * Week listener
	 */
	private OnClickListener weekOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.divisionhighlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(DivisionHighlightsActivity.this,
					R.layout.nfl_highlights_popover_view_week);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(DivisionHighlightsActivity.this);
			View button = (View) findViewById(R.id.weekLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			ImageView doneImageView = (ImageView) popoverView.findViewById(R.id.doneButtonImage);
			doneImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();
					
				}
			});

		}
	};
	
	/**
	 *  leaders listener
	 */
	private OnClickListener leaderOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.divisionhighlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(DivisionHighlightsActivity.this,
					R.layout.nfl_highlights_popover_view_leaders);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(DivisionHighlightsActivity.this);
			View button = (View) findViewById(R.id.leadersLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			
			ImageView doneImageView = (ImageView) popoverView.findViewById(R.id.doneButtonImage);
			doneImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();
					
				}
			});

		}
	};
	
	private OnClickListener milestonesOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Toast.makeText(context, "Milestone Handler", Toast.LENGTH_SHORT);
			/*RelativeLayout rootView = (RelativeLayout) findViewById(R.id.highlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(NFLHighlightsActivity.this,
					R.layout.popover_nfl_week_item);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(NFLHighlightsActivity.this);
			View button = (View) findViewById(R.id.weekLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);*/

		}
	};
	
	/**
	 * standing listener
	 */
	private OnClickListener standingOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.divisionhighlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(DivisionHighlightsActivity.this,
					R.layout.nfl_highlights_popover_view_standing);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(DivisionHighlightsActivity.this);
			View button = (View) findViewById(R.id.standingLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			
			ImageView doneImageView = (ImageView) popoverView.findViewById(R.id.doneButtonImage);
			doneImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();
					
				}
			});

		}
	};

	

	

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.d("layoutId", String.valueOf(view.getLayoutId()));

	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		Log.d("layoutId", String.valueOf(view.getLayoutId()));
		if (view.getLayoutId() == R.layout.nfl_highlights_popover_view_week) {
			Log.d("id", String.valueOf(view.getId()));
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
			
			listView = (ListView) findViewById(R.id.week_list);
			NFLHighlightsWeekListAdapter weekListAdapter = new NFLHighlightsWeekListAdapter(
					context, R.layout.nfl_highlights_popover_list_row_item_week, weekItems);

			listView.setAdapter(weekListAdapter);

		}
		else if(view.getLayoutId() == R.layout.nfl_highlights_popover_view_leaders){
			
			SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(context);
			List<LeadersTypeItem> offenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();
			
			LeadersTypeItem item1 = new LeadersTypeItem();
			item1.setLeadersTypeName("Passing Yards");
			offenceLeadersTypeItems.add(item1);
			
			LeadersTypeItem item2= new LeadersTypeItem();
			item2.setLeadersTypeName("Rushing Yards");
			offenceLeadersTypeItems.add(item2);
			
			LeadersTypeItem item3 = new LeadersTypeItem();
			item3.setLeadersTypeName("Receiving Yards");
			offenceLeadersTypeItems.add(item3);
			
			LeadersTypeItem item4 = new LeadersTypeItem();
			item4.setLeadersTypeName("Touchdown");
			offenceLeadersTypeItems.add(item4);
			
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
			
			LeadersTypeItem item11 = new LeadersTypeItem();
			item11.setLeadersTypeName("Kick Returns");
			specialTeamLeadersTypeItems.add(item11);
			
			LeadersTypeItem item12 = new LeadersTypeItem();
			item12.setLeadersTypeName("Filed Goals");
			specialTeamLeadersTypeItems.add(item12);
			
			separatedListAdapter.addSection("Special Teams", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_popover_list_row_item_leader, specialTeamLeadersTypeItems));
			
			listView = (ListView) findViewById(R.id.leaders_list);
			
			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(leadersItemClickListener);
			
		}else if(view.getLayoutId() == R.layout.nfl_highlights_popover_view_passing_leaders){
			List<PassingLeaderItem> items = new ArrayList<PassingLeaderItem>();
			
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
					
			listView = (ListView) findViewById(R.id.passing_leaders_list);
			PassingLeaderListViewAdapter adapter = new PassingLeaderListViewAdapter(context, R.layout.nfl_highlights_popover_list_row_item_passing_leader, items);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(passingLeadersItemClickListener);
			
		}else if(view.getLayoutId() == R.layout.nfl_highlights_popover_view_standing){
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
			separatedListAdapter.addSection("1", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			
			List<SectionHeaderItem> north_popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem northItem = new SectionHeaderItem();
			northItem.setHeadingText1("North Division");
			northItem.setHeadingText2("W");
			northItem.setHeadingText3("L");
			north_popover_nfl_standing_header.add(northItem);
			SeparatedSectionHeaderListAdapter northHeaderListAdapter = new SeparatedSectionHeaderListAdapter( context, R.layout.nfl_highlights_popover_header_standing, north_popover_nfl_standing_header);
			separatedListAdapter.addSection("7", northHeaderListAdapter);
			separatedListAdapter.addSection("2", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			
			List<SectionHeaderItem> southPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem south_HeaderItem = new SectionHeaderItem();
			south_HeaderItem.setHeadingText1("South Division");
			south_HeaderItem.setHeadingText2("W");
			south_HeaderItem.setHeadingText3("L");
			southPopover_nfl_standing_header.add(south_HeaderItem);
			SeparatedSectionHeaderListAdapter southHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, southPopover_nfl_standing_header);
			separatedListAdapter.addSection("8", southHeaderListAdapter);
			separatedListAdapter.addSection("3", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			List<SectionHeaderItem> westPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem westHeaderItem = new SectionHeaderItem();
			westHeaderItem.setHeadingText1("West Division");
			westHeaderItem.setHeadingText2("W");
			westHeaderItem.setHeadingText3("L");
			westPopover_nfl_standing_header.add(westHeaderItem);
			SeparatedSectionHeaderListAdapter westHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.nfl_highlights_popover_header_standing, westPopover_nfl_standing_header);
			separatedListAdapter.addSection("9", westHeaderListAdapter);
			separatedListAdapter.addSection("4", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			listView = (ListView) findViewById(R.id.standing_list);
			
			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(standingItemClickListener);
		}

	}
	
	/**
	 * Leaders item listner
	 */
	private OnItemClickListener leadersItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.divisionhighlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(DivisionHighlightsActivity.this,
					R.layout.nfl_highlights_popover_view_passing_leaders);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(DivisionHighlightsActivity.this);
			View button = (View) findViewById(R.id.leadersLayout);
			popoverView.showPopoverFromRectInViewGroup(rootView,
					PopoverView.getFrameForView(button),
					PopoverView.PopoverArrowDirectionUp, true);
			
			ImageView backImageView = (ImageView) popoverView.findViewById(R.id.backButtonImage);
			
			backImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					leaderOnClickListener.onClick(popoverView);
					
				}
			});
			
			ImageView doneImageView = (ImageView) popoverView.findViewById(R.id.doneButtonImage);

			doneImageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					popoverView.removeAllViews();
					
				}
			});

			
		}
	
	};

	@Override
	public void popoverViewWillDismiss(PopoverView view) {
		

	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {
		

	}
	
	private OnItemClickListener passingLeadersItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(context, PlayersActivity.class);
			startActivity(intent);
		
		}
	
	};
	
	private OnItemClickListener standingItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Intent intent = new Intent(context, GameActivity.class);
			startActivity(intent);
		
		}
	
	};

	

}
