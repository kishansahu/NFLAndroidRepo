package com.liveclips.nfl.sp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.R.id;
import com.liveclips.nfl.sp.R.layout;
import com.liveclips.nfl.sp.VideoClip.Activity.DivisionsMenuActivity;
import com.liveclips.nfl.sp.VideoClip.Activity.MyPlayerActivity;
import com.liveclips.nfl.sp.VideoClip.Activity.NewEnglandPatriotsActivity;
import com.liveclips.nfl.sp.VideoClip.Activity.NflHighlightActivity;

public class LiveClips extends Activity{
private	Button back,newenglandpatriots,myplayer,nflhighlights,divison,teams,game_schedule;
private Intent i;		
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.videoclip_menu);
	        setContent();
	        
	
	 }
	 private void setContent() {
		 back=(Button)findViewById(R.id.videoclip_menu_back);
		 back.setOnClickListener(listener);
		 newenglandpatriots=(Button)findViewById(R.id.videoclip_menu_new_england_patriots);
		 newenglandpatriots.setOnClickListener(listener);
		 myplayer=(Button)findViewById(R.id.videoclip_menu_my_player);
		 myplayer.setOnClickListener(listener);
		 nflhighlights=(Button)findViewById(R.id.videoclip_menu_nfl_highlights);
		 nflhighlights.setOnClickListener(listener);
		 divison=(Button)findViewById(R.id.videoclip_menu_divison);
		 divison.setOnClickListener(listener);
		 teams=(Button)findViewById(R.id.videoclip_menu_divison);
		 teams.setOnClickListener(listener);
		 game_schedule=(Button)findViewById(R.id.videoclip_menu_divison);
		 game_schedule.setOnClickListener(listener);
		
		
	}
	 private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.videoclip_menu_back:
				onBackPressed();
				break;
	    case R.id.videoclip_menu_new_england_patriots:
		startActivity(new Intent(getApplicationContext(), NewEnglandPatriotsActivity.class));		
				break;
	    case R.id.videoclip_menu_my_player:
	    	i=new Intent(getApplicationContext(), MyPlayerActivity.class);
	    
			startActivity(i);		
					break;
	    case R.id.videoclip_menu_nfl_highlights:
	    	i=new Intent(getApplicationContext(), NflHighlightActivity.class);
	    
			startActivity(i);		
					break;
	    case R.id.videoclip_menu_divison:
	    	i=new Intent(getApplicationContext(), DivisionsMenuActivity.class);
	    
			startActivity(i);		
					break;
			default:
				break;
			}
		}
	};
}
