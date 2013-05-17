package com.liveclips.nfl.sp;

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

public class LiveClips extends Activity{
private	Button back,newenglandpatriots,myplayer,nflhighlights,divison,teams,game_schedule;
		
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
		startActivity(new Intent(getApplicationContext(), GameActivity.class));		
				break;

			default:
				break;
			}
		}
	};
}
