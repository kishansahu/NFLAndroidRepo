/**
 * 
 */
package com.liveclips.nfl.activity;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.ConferenceItem;
import com.liveclips.nfl.utils.DownloadImagesThreadPool;
import com.liveclips.nfl.utils.PlayCardView;

/**
 * @author mohitkumar
 *
 */
public class DivisionHighlightsActivity extends Activity{
	
	private ArrayList<String> playCardTopDetail = new ArrayList<String>();

	private ArrayList<String> playCardBottomDetail = new ArrayList<String>();

	private Context context;

	private int playCardVideoId = 0;

	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_division_highlights);

		context = this;

		prepareVideoView();

		
	}
	
	@Override
	protected void onStart() {

		super.onStart();
		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.nfl_highlights_menu_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF8B1D));
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		//TextView textView = (TextView) findViewById(R.id.conference_name);
		//textView.setText(conferenceItem.getConferenceName());
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

}
