package com.liveclips.nfl.sp.VideoClip.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.model.ConferenceItem;

public class DivisionHighlightsActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
		setContent("Divisions");
		setPopupMenu();
		setBanner();
		setVideoList();
	}

       private void setCurrentConferenceInfo(ConferenceItem conferenceItem) {
		
		TextView conferenceTextView = (TextView) findViewById(R.id.divisions_banner_header);
		conferenceTextView.setText(conferenceItem.getConferenceName());
		
			}
	
	
		@Override
		protected void onResume() {
			ConferenceItem conferenceItem = (ConferenceItem) getIntent().getSerializableExtra("conference");
			setCurrentConferenceInfo(conferenceItem);
			super.onResume();
		}


		@Override
		protected void setVideoList() {
			
			linearLayout=(LinearLayout)findViewById(R.id.video_list_container);
			LayoutInflater inflater = getLayoutInflater();
			linearLayout.addView(inflater.inflate(R.layout.video_list_item, null));
			
		}


		@Override
		protected void setPopupMenu() {
			// TODO Auto-generated method stub
			linearLayout=(LinearLayout)findViewById(R.id.popup_tab_layout);
			linearLayout.removeAllViews();
			container=mInflater.inflate(R.layout.divisions_popup_menubar, null);
			
			Button week = (Button) container.findViewById(R.id.divisions_week);
			//week.setOnClickListener(listener);
			Button leader = (Button)container.findViewById(R.id.divisions_leaders);
		//	leader.setOnClickListener(listener);
			
			Button standing = (Button)container.findViewById(R.id.divisions_standing);
			//standing.setOnClickListener(listener);
			
			linearLayout.addView(container);
		}


		@Override
		protected void setBanner() {
			
			linearLayout=(LinearLayout)findViewById(R.id.score_board_container);
			linearLayout.removeAllViews();
			linearLayout.addView(mInflater.inflate(R.layout.divisions_banner, null));
			
		}	
	
}
