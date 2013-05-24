package com.liveclips.nfl.sp.VideoClip.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.sp.AlertOption;
import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.model.ConferenceItem;
import com.liveclips.nfl.sp.popup.QuickAction;

public abstract class BaseActivity extends Activity{
	
	protected LinearLayout linearLayout;
	
	protected Context context;
	protected LayoutInflater mInflater; 
	protected View container;
	protected ListView listView;
	
	protected QuickAction quickAction;
	protected Intent i;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		  
		
	}
	
	protected void setContent(String title) {
	
	mInflater=getLayoutInflater();
	
	Button back=(Button)findViewById(R.id.activity_game_back)	;
	back.setOnClickListener(baselistener);
	Button alertoption=(Button)findViewById(R.id.alert_control);
	alertoption.setOnClickListener(baselistener);
	TextView headerText=(TextView)findViewById(R.id.activity_game_title);
	headerText.setText(title);
		
	}
	
	protected abstract void setVideoList(); 
	protected abstract void setPopupMenu(); 
	protected abstract void setBanner();
	
   private OnClickListener baselistener=new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				switch (v.getId()) {
				
				case R.id.alert_control:
					
				Intent i = new Intent(context,AlertOption.class);
				startActivity(i);
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
		
	
}
