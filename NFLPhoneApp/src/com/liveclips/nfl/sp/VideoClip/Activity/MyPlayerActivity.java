package com.liveclips.nfl.sp.VideoClip.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.sp.AddPlayersActivity;
import com.liveclips.nfl.sp.AlertOption;
import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.R.anim;
import com.liveclips.nfl.sp.R.drawable;
import com.liveclips.nfl.sp.R.id;
import com.liveclips.nfl.sp.R.layout;
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

public class MyPlayerActivity extends Activity{
	
	LinearLayout scoreboard;
	private Button back,alertoption;
	private Context context;
	private LayoutInflater mInflater; 
	private View container;
	private ListView listView;
	private Button  team1BtnPlayers;
	private Button team2BtnPlayers ;
	private QuickAction quickAction;
	private Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		setContent();
		
			
			scoreboard=(LinearLayout)findViewById(R.id.score_board_container);
			scoreboard.removeAllViews();
			container=mInflater.inflate(R.layout.my_player, null);
			ImageButton addplayer=(ImageButton)container.findViewById(R.id.addaplayerButton);
			addplayer.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					startActivityForResult(new Intent(context,AddPlayersActivity.class),10003);
				}
			});
			scoreboard.addView(container);
			//addFevoratePlayer();
		
		
	
		for (int i = 0; i < 5; i++) {
			setVideoList();
		}
		  
		
	}
	
	private void setContent() {
		context=this;
		mInflater=getLayoutInflater();
	back=(Button)findViewById(R.id.activity_game_back)	;
	back.setOnClickListener(listener);

	alertoption=(Button)findViewById(R.id.alert_control);
	alertoption.setOnClickListener(listener);
		
	}
	
	private void setVideoList() {
		scoreboard=(LinearLayout)findViewById(R.id.video_list_container);
		//scoreboard.removeAllViews();
		LayoutInflater inflater = getLayoutInflater();
		scoreboard.addView(inflater.inflate(R.layout.video_list_item, null));
		Log.d("count", ""+scoreboard.getChildCount());
		
	}
	private void addFevoratePlayer() {
		scoreboard=(LinearLayout)findViewById(R.id.my_player_player_detail);
		//scoreboard.removeAllViews();
		
		scoreboard.addView(mInflater.inflate(R.layout.common_players_detail, null));
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
				default:
					break;
				}
			}
		};
		
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			if (resultCode==RESULT_OK&&requestCode==10003) {
				addFevoratePlayer();
			}
		}
		
}
