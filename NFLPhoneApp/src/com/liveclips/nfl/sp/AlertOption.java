package com.liveclips.nfl.sp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class AlertOption extends Activity{

	private TextView headertext,mainseekBarinfo;
	private SeekBar seekbarmain,passingplay,rushingplay;
	private ToggleButton allplays,topplays,scoringplays,turnovers,redzoneplays,playofgame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_options);
		setContent();
		Button back=(Button)findViewById(R.id.alert_option_cancel);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
				overridePendingTransition( R.anim.slide_in_down, R.anim.slide_out_down );
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	private void setContent() {
		
		headertext=(TextView)findViewById(R.id.alert_options_header_text);
		mainseekBarinfo=(TextView)findViewById(R.id.alert_options_seekBar_main_info);
		
		seekbarmain=(SeekBar)findViewById(R.id.alert_options_seekBar_main);
		seekbarmain.setOnSeekBarChangeListener(seekbarlistenar);
		passingplay=(SeekBar)findViewById(R.id.alert_options_seekBar_passing_play);
		passingplay.setOnSeekBarChangeListener(seekbarlistenar);
		rushingplay=(SeekBar)findViewById(R.id.alert_options_seekBar_rushing_play);
		rushingplay.setOnSeekBarChangeListener(seekbarlistenar);
		
		allplays=(ToggleButton)findViewById(R.id.alert_options_togglebutton_allplays);
		topplays=(ToggleButton)findViewById(R.id.alert_options_togglebutton_topplays);
		scoringplays=(ToggleButton)findViewById(R.id.alert_options_togglebutton_scoringplays);
		turnovers=(ToggleButton)findViewById(R.id.alert_options_togglebutton_turnovers);
		redzoneplays=(ToggleButton)findViewById(R.id.alert_options_togglebutton_redzoneplays);
		playofgame=(ToggleButton)findViewById(R.id.alert_options_togglebutton_playofgame);
		
	}
	
	private OnSeekBarChangeListener seekbarlistenar= new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			switch (seekBar.getId()) {
			case R.id.alert_options_seekBar_main:
				if (progress==1) {
					headertext.setText("GB at NE Alerts: Low");
					mainseekBarinfo.setText("  5-10 Alerts per game");
					
					passingplay.setProgress(0);
					rushingplay.setProgress(0);
					allplays.setChecked(false);
					topplays.setChecked(true);
					scoringplays.setChecked(true);
					turnovers.setChecked(false);
					redzoneplays.setChecked(false);
                    playofgame.setChecked(true);
					
				}else if (progress==2) {
					headertext.setText("GB at NE Alerts: Medium");
					mainseekBarinfo.setText("  50-60 Alerts per game");
					
					passingplay.setProgress(1);
					rushingplay.setProgress(1);
					allplays.setChecked(false);
					topplays.setChecked(true);
					scoringplays.setChecked(true);
					turnovers.setChecked(true);
					redzoneplays.setChecked(true);
                    playofgame.setChecked(true);
				}else if (progress==3) {
					headertext.setText("GB at NE Alerts: High");
					mainseekBarinfo.setText("  100+ Alerts per game");
					
					passingplay.setProgress(2);
					rushingplay.setProgress(2);
					allplays.setChecked(true);
					topplays.setChecked(false);
					scoringplays.setChecked(false);
					turnovers.setChecked(false);
					redzoneplays.setChecked(false);
                    playofgame.setChecked(true);
				}
				break;
	        case R.id.alert_options_seekBar_passing_play:
				
				break;
	        case R.id.alert_options_seekBar_rushing_play:
		
	    	break;

			default:
				break;
			}
		}
	};
}
