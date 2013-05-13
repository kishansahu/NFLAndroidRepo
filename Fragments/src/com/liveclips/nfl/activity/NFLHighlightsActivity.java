/**
 * 
 */
package com.liveclips.nfl.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.NFLHighlightsLeadersListAdapter;
import com.liveclips.nfl.adapter.NFLHighlightsWeekListAdapter;
import com.liveclips.nfl.adapter.PassingLeaderListViewAdapter;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.model.LeadersTypeItem;
import com.liveclips.nfl.model.PassingLeaderItem;
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

		}
	};

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

		}
	};

	private OnClickListener milestonesOnClickListener = new OnClickListener() {

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

		}
	};

	private OnClickListener standingOnClickListener = new OnClickListener() {

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
			WeekItem item;
			for (int index = 1; index <= 10; index++) {
				item = new WeekItem();
				item.setWeekName("Week " + index);
				weekItems.add(item);

			}
			listView = (ListView) findViewById(R.id.week_list);
			NFLHighlightsWeekListAdapter weekListAdapter = new NFLHighlightsWeekListAdapter(
					context, R.layout.nfl_highlights_week_menu, weekItems);

			listView.setAdapter(weekListAdapter);

		} else if (view.getLayoutId() == R.layout.popover_nfl_leaders_item) {

			SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(
					context);
			List<LeadersTypeItem> offenceLeadersTypeItems = new ArrayList<LeadersTypeItem>();

			LeadersTypeItem item1 = new LeadersTypeItem();
			item1.setLeadersTypeName("Passing Yards");
			offenceLeadersTypeItems.add(item1);

			LeadersTypeItem item2 = new LeadersTypeItem();
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

			separatedListAdapter.addSection("Offense",
					new NFLHighlightsLeadersListAdapter(context,
							R.layout.nfl_highlights_leader_menu_item,
							offenceLeadersTypeItems));

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

			separatedListAdapter.addSection("Defense",
					new NFLHighlightsLeadersListAdapter(context,
							R.layout.nfl_highlights_leader_menu_item,
							defenceLeadersTypeItems));

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

			separatedListAdapter.addSection("Special Teams",
					new NFLHighlightsLeadersListAdapter(context,
							R.layout.nfl_highlights_leader_menu_item,
							specialTeamLeadersTypeItems));
			// NFLHighlightsLeadersListAdapter adapter = new
			// NFLHighlightsLeadersListAdapter(context,
			// R.layout.nfl_highlights_leader_menu_item,
			// specialTeamLeadersTypeItems);
			listView = (ListView) findViewById(R.id.leaders_list);

			listView.setAdapter(separatedListAdapter);
			listView.setOnItemClickListener(leadersItemClickListener);

		} else if (view.getLayoutId() == R.layout.popover_nfl_passing_leaders_item) {
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
			PassingLeaderListViewAdapter adapter = new PassingLeaderListViewAdapter(
					context, R.layout.passing_leader_menu_row_layout, items);
			listView.setAdapter(adapter);
			// for()
		}

	}

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

		}

	};

	@Override
	public void popoverViewWillDismiss(PopoverView view) {

	}

	@Override
	public void popoverViewDidDismiss(PopoverView view) {
		// TODO Auto-generated method stub

	}

}
