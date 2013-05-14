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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.EmptyHeaderSeparatedListAdapter;
import com.liveclips.nfl.adapter.NFLHighlightsLeadersListAdapter;
import com.liveclips.nfl.adapter.NFLHighlightsWeekListAdapter;
import com.liveclips.nfl.adapter.PassingLeaderListViewAdapter;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.adapter.SeparatedSectionHeaderListAdapter;
import com.liveclips.nfl.adapter.StandingTeamListViewAdapter;
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
public class NFLHighlightsActivity extends BaseActivity implements
		PopoverViewDelegate {

	private ArrayList<String> playCardTopDetail = new ArrayList<String>();

	private ArrayList<String> playCardBottomDetail = new ArrayList<String>();

	private Context context;

	private ListView listView;

	private PopoverView popoverView;

	private DownloadImagesThreadPool downloadImagesThreadPool;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_nfl_highlights);

		context = this;

		downloadImagesThreadPool = new DownloadImagesThreadPool();
		
		createCustomActionBar();
		
		prepareVideoView();

	}
	
	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.nfl_highlights_menu_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF8B1D));
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		LinearLayout weekLinearLayout = (LinearLayout) mActionBarView
				.findViewById(R.id.weekLayout);
		weekLinearLayout.setOnClickListener(weekOnClickListener);

		LinearLayout leaderLinearLayout = (LinearLayout) mActionBarView
				.findViewById(R.id.leadersLayout);
		leaderLinearLayout.setOnClickListener(leaderOnClickListener);

		LinearLayout milestonesLinearLayout = (LinearLayout) mActionBarView
				.findViewById(R.id.milestonesLayout);
		milestonesLinearLayout.setOnClickListener(milestonesOnClickListener);

		LinearLayout standingLinearLayout = (LinearLayout) mActionBarView
				.findViewById(R.id.standingLayout);
		standingLinearLayout.setOnClickListener(standingOnClickListener);
	}

	

	/**
	 * Week listener
	 */
	private OnClickListener weekOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.highlightsRootView);
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
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.highlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(NFLHighlightsActivity.this,
					R.layout.popover_nfl_leaders_item);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(NFLHighlightsActivity.this);
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
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.highlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(NFLHighlightsActivity.this,
					R.layout.popover_nfl_standing_view);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(NFLHighlightsActivity.this);
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

	

	

	private void prepareVideoView() {

		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		playCardParentLinearLayout.removeAllViews();

		DownloadImagesThreadPool downloadImagesThreadPool = new DownloadImagesThreadPool();

		TextView selectedCategoryTextView = (TextView) findViewById(R.id.selectedCategoryTextViewId);
		selectedCategoryTextView.setText("NFL Highlights");
		for (int index = 0; index < 4; index++) {
			playCardTopDetail.add("Video Text on Top " + index);

			playCardBottomDetail.add("Video Text on Bottom " + index);
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

	@Override
	public void popoverViewWillShow(PopoverView view) {
		Log.d("layoutId", String.valueOf(view.getLayoutId()));

	}

	@Override
	public void popoverViewDidShow(PopoverView view) {
		Log.d("layoutId", String.valueOf(view.getLayoutId()));
		if (view.getLayoutId() == R.layout.popover_nfl_week_item) {
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
					context, R.layout.nfl_highlights_week_menu, weekItems);

			listView.setAdapter(weekListAdapter);

		}
		else if(view.getLayoutId() == R.layout.popover_nfl_leaders_item){
			
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
			
			separatedListAdapter.addSection("Offense", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_leader_menu_item, offenceLeadersTypeItems));
			
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
			
			separatedListAdapter.addSection("Defense", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_leader_menu_item, defenceLeadersTypeItems));
			
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
			
			separatedListAdapter.addSection("Special Teams", new NFLHighlightsLeadersListAdapter(context, R.layout.nfl_highlights_leader_menu_item, specialTeamLeadersTypeItems));
			
			listView = (ListView) findViewById(R.id.leaders_list);
			
			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(leadersItemClickListener);
			
		}else if(view.getLayoutId() == R.layout.popover_nfl_passing_leaders_item){
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
			PassingLeaderListViewAdapter adapter = new PassingLeaderListViewAdapter(context, R.layout.passing_leader_menu_row_layout, items);
			listView.setAdapter(adapter);
			
			listView.setOnItemClickListener(passingLeadersItemClickListener);
			
		}else if(view.getLayoutId() == R.layout.popover_nfl_standing_view){
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
			
					
			
			SeparatedSectionHeaderListAdapter eastHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.popover_nfl_standing_header, popover_nfl_standing_header);
			
			EmptyHeaderSeparatedListAdapter separatedListAdapter = new EmptyHeaderSeparatedListAdapter(context, true);
			
			separatedListAdapter.addSection("0", eastHeaderListAdapter);
			separatedListAdapter.addSection("1", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			
			List<SectionHeaderItem> north_popover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem northItem = new SectionHeaderItem();
			northItem.setHeadingText1("North Division");
			northItem.setHeadingText2("W");
			northItem.setHeadingText3("L");
			north_popover_nfl_standing_header.add(northItem);
			SeparatedSectionHeaderListAdapter northHeaderListAdapter = new SeparatedSectionHeaderListAdapter( context, R.layout.popover_nfl_standing_header, north_popover_nfl_standing_header);
			separatedListAdapter.addSection("7", northHeaderListAdapter);
			separatedListAdapter.addSection("2", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			
			List<SectionHeaderItem> southPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem south_HeaderItem = new SectionHeaderItem();
			south_HeaderItem.setHeadingText1("South Division");
			south_HeaderItem.setHeadingText2("W");
			south_HeaderItem.setHeadingText3("L");
			southPopover_nfl_standing_header.add(south_HeaderItem);
			SeparatedSectionHeaderListAdapter southHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.popover_nfl_standing_header, southPopover_nfl_standing_header);
			separatedListAdapter.addSection("8", southHeaderListAdapter);
			separatedListAdapter.addSection("3", new StandingTeamListViewAdapter(context, R.layout.standing_team_menu_row_layout, conferenceTeamItems));
			
			List<SectionHeaderItem> westPopover_nfl_standing_header = new ArrayList<SectionHeaderItem>();
			SectionHeaderItem westHeaderItem = new SectionHeaderItem();
			westHeaderItem.setHeadingText1("West Division");
			westHeaderItem.setHeadingText2("W");
			westHeaderItem.setHeadingText3("L");
			westPopover_nfl_standing_header.add(westHeaderItem);
			SeparatedSectionHeaderListAdapter westHeaderListAdapter = new SeparatedSectionHeaderListAdapter(context, R.layout.popover_nfl_standing_header, westPopover_nfl_standing_header);
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
			RelativeLayout rootView = (RelativeLayout) findViewById(R.id.highlightsRootView);
			if (popoverView != null) {
				popoverView.removeAllViews();
			}
			popoverView = new PopoverView(NFLHighlightsActivity.this,
					R.layout.popover_nfl_passing_leaders_item);

			popoverView.setContentSizeForViewInPopover(new Point(320, 400));
			popoverView.setDelegate(NFLHighlightsActivity.this);
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
