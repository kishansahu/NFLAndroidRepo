package com.liveclips.nfl.sp.VideoClip.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.liveclips.nfl.sp.AddPlayersActivity;
import com.liveclips.nfl.sp.R;

public class MyPlayerActivity extends BaseActivity{
	



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context=this;
		setContent("My Player");
		setBanner();
			
		
	
		for (int i = 0; i < 5; i++) {
			setVideoList();
		}
		  
		
	}

	   private void addFevoratePlayer() {
		linearLayout=(LinearLayout)findViewById(R.id.my_player_player_detail);
		//scoreboard.removeAllViews();
		
		linearLayout.addView(mInflater.inflate(R.layout.common_players_detail, null));
	}

		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode==RESULT_OK&&requestCode==10003) {
				addFevoratePlayer();
			}
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
			
		}

		@Override
		protected void setBanner() {
			// TODO Auto-generated method stub
			linearLayout=(LinearLayout)findViewById(R.id.score_board_container);
			linearLayout.removeAllViews();
			container=mInflater.inflate(R.layout.my_player, null);
			ImageButton addplayer=(ImageButton)container.findViewById(R.id.addaplayerButton);
			addplayer.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					startActivityForResult(new Intent(context,AddPlayersActivity.class),10003);
				}
			});
			linearLayout.addView(container);
		}
		
}
