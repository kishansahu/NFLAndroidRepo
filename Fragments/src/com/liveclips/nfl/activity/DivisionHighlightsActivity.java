/**
 * 
 */
package com.liveclips.nfl.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.model.ConferenceItem;

/**
 * @author mohitkumar
 *
 */
public class DivisionHighlightsActivity extends Activity{
	
	private Context context;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_division_highlights);

		context = this;

		
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
		ConferenceItem conferenceItem = (ConferenceItem) getIntent().getSerializableExtra("conference");
		setCurrentConferenceInfo(conferenceItem);
		//TextView textView = (TextView) findViewById(R.id.conference_name);
		//textView.setText(conferenceItem.getConferenceName());
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

}
