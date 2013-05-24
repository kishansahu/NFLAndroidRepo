package com.liveclips.nfl.sp.VideoClip.Activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.liveclips.nfl.sp.R;
import com.liveclips.nfl.sp.adapter.ConferenceListViewAdapter;
import com.liveclips.nfl.sp.adapter.DivisionsListViewAdapter;
import com.liveclips.nfl.sp.adapter.EmptyHeaderSeparatedListAdapter;
import com.liveclips.nfl.sp.model.ConferenceItem;
import com.liveclips.nfl.sp.model.DivisionHeader;
import com.liveclips.nfl.sp.model.DivisionsItem;
import com.liveclips.nfl.sp.model.TeamItem;

public class DivisionsMenuActivity extends Activity{
	
	
    List<DivisionsItem> divisionItems = new ArrayList<DivisionsItem>();
	
	List<DivisionsItem> divisionItems1 = new ArrayList<DivisionsItem>();
	
	List<ConferenceItem> afcConferenceItems = new ArrayList<ConferenceItem>();
	
	List<ConferenceItem> nfcConferenceItems = new ArrayList<ConferenceItem>();
	ListView listView1;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.divisions_menu);
		context=this;
		setTestData();
		
	}
     private void setTestData() {
		
		ConferenceItem conferenceItem = new ConferenceItem();
		conferenceItem.setConferenceName("AFC East");
		TeamItem teamItem = new TeamItem();
		teamItem.setTeamLogo(R.drawable.bills);
		teamItem.setTeamName("Buffalo Bills");
		conferenceItem.setTeam_1(teamItem);
		
		TeamItem teamItem1 = new TeamItem();
		teamItem1.setTeamLogo(R.drawable.cardinals);
		teamItem1.setTeamName("Miami Dolphins");
		conferenceItem.setTeam_2(teamItem1);
		
		TeamItem teamItem2 = new TeamItem();
		teamItem2.setTeamLogo(R.drawable.jets);
		teamItem2.setTeamName("New England Patriots");
		conferenceItem.setTeam_3(teamItem2);
		
		TeamItem teamItem3 = new TeamItem();
		teamItem3.setTeamLogo(R.drawable.titans);
		teamItem3.setTeamName("New York Jets");
		conferenceItem.setTeam_4(teamItem3);
		
		afcConferenceItems.add(conferenceItem);
		
		ConferenceItem conferenceItem1 = new ConferenceItem();
		conferenceItem1.setConferenceName("AFC West");
		conferenceItem1.setTeam_1(teamItem1);
		conferenceItem1.setTeam_2(teamItem);
		conferenceItem1.setTeam_3(teamItem3);
		conferenceItem1.setTeam_4(teamItem2);
		afcConferenceItems.add(conferenceItem1);
		
		ConferenceItem conferenceItem2 = new ConferenceItem();
		conferenceItem2.setConferenceName("AFC North");
		conferenceItem2.setTeam_1(teamItem);
		conferenceItem2.setTeam_2(teamItem3);
		conferenceItem2.setTeam_3(teamItem1);
		conferenceItem2.setTeam_4(teamItem2);
		afcConferenceItems.add(conferenceItem2);
		
		ConferenceItem conferenceItem3 = new ConferenceItem();
		conferenceItem3.setConferenceName("AFC South");
		conferenceItem3.setTeam_1(teamItem);
		conferenceItem3.setTeam_2(teamItem2);
		conferenceItem3.setTeam_3(teamItem3);
		conferenceItem3.setTeam_4(teamItem2);
		afcConferenceItems.add(conferenceItem1);
		//for(int index = 0; index < 4; index++){
			DivisionsItem item = new DivisionsItem();
			item.setDivisionName("AFC");
			item.setDivisionLogo(R.drawable.icon_players);
			//item.setConferenceItems(conferenceItems);
			divisionItems.add(item);
			DivisionsItem item1 = new DivisionsItem();
			item1.setDivisionName("NFC");
			item1.setDivisionLogo(R.drawable.icon_players);
			//item1.setConferenceItems(conferenceItems);
			divisionItems1.add(item1);
			
		secondSetData();
		
	}

	private void secondSetData() {
		ConferenceItem conferenceItem = new ConferenceItem();
		conferenceItem.setConferenceName("AFC East");
		TeamItem teamItem = new TeamItem();
		teamItem.setTeamLogo(R.drawable.bills);
		teamItem.setTeamName("Buffalo Bills");
		conferenceItem.setTeam_1(teamItem);
		
		TeamItem teamItem1 = new TeamItem();
		teamItem1.setTeamLogo(R.drawable.cardinals);
		teamItem1.setTeamName("Miami Dolphins");
		conferenceItem.setTeam_2(teamItem1);
		
		TeamItem teamItem2 = new TeamItem();
		teamItem2.setTeamLogo(R.drawable.jets);
		teamItem2.setTeamName("New England Patriots");
		conferenceItem.setTeam_3(teamItem2);
		
		TeamItem teamItem3 = new TeamItem();
		teamItem3.setTeamLogo(R.drawable.titans);
		teamItem3.setTeamName("New York Jets");
		conferenceItem.setTeam_4(teamItem3);
		
		nfcConferenceItems.add(conferenceItem);
		
		ConferenceItem conferenceItem1 = new ConferenceItem();
		conferenceItem1.setConferenceName("NFC West");
		conferenceItem1.setTeam_1(teamItem1);
		conferenceItem1.setTeam_2(teamItem);
		conferenceItem1.setTeam_3(teamItem3);
		conferenceItem1.setTeam_4(teamItem2);
		nfcConferenceItems.add(conferenceItem1);
		
		ConferenceItem conferenceItem2 = new ConferenceItem();
		conferenceItem2.setConferenceName("NFC North");
		conferenceItem2.setTeam_1(teamItem2);
		conferenceItem2.setTeam_2(teamItem3);
		conferenceItem2.setTeam_3(teamItem1);
		conferenceItem2.setTeam_4(teamItem);
		nfcConferenceItems.add(conferenceItem2);
		
		ConferenceItem conferenceItem3 = new ConferenceItem();
		conferenceItem3.setConferenceName("NFC South");
		conferenceItem3.setTeam_1(teamItem);
		conferenceItem3.setTeam_2(teamItem2);
		conferenceItem3.setTeam_3(teamItem3);
		conferenceItem3.setTeam_4(teamItem1);
		nfcConferenceItems.add(conferenceItem1);
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Button back=(Button)findViewById(R.id.divisions_menu_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		listView1=(ListView)findViewById(R.id.divisions_menu_ListView);
		
		EmptyHeaderSeparatedListAdapter adapter = new EmptyHeaderSeparatedListAdapter(context, false);
		List<DivisionHeader> objects = new ArrayList<DivisionHeader>();
		DivisionHeader divisionHeader = new DivisionHeader();
		divisionHeader.setDivisionHeader("Divisions");
		
		objects.add(divisionHeader);
		
		//adapter.addSection("1", new DivisionHeaderAdapter(context, R.layout.division_fragment_header, objects));
		
		adapter.addSection("2", new DivisionsListViewAdapter(context, R.layout.division_fragment_list_row_item_header, divisionItems));
		
		adapter.addSection("3", new ConferenceListViewAdapter(context, R.layout.conference_menu_row_layout, afcConferenceItems));
		
		adapter.addSection("4", new DivisionsListViewAdapter(context, R.layout.division_fragment_list_row_item_header, divisionItems1));
		
		adapter.addSection("5", new ConferenceListViewAdapter(context, R.layout.conference_menu_row_layout, nfcConferenceItems));
		
		
		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(listItemListener);
	}
	private OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			Object object = arg1.getTag();
			/*if(DivisionHeaderAdapter.DivisionHeaderHolder.class.isInstance(object)){
				Toast.makeText(context,  "Id : DivisionHeader" , Toast.LENGTH_SHORT).show();
			}else if(DivisionsListViewAdapter.DivisionViewHolder.class.isInstance(object)){
				Toast.makeText(context,  "Id : DivisionsItem" , Toast.LENGTH_SHORT).show();
			}*/
			 if(ConferenceListViewAdapter.ConferenceHolder.class.isInstance(object)){
				Intent intent = new Intent(context,DivisionHighlightsActivity.class);
				ConferenceItem conferenceItem = new ConferenceItem();
				setConferenceData(conferenceItem, arg1);
				intent.putExtra("conference", conferenceItem);
				startActivity(intent);
			//	Toast.makeText(context,  "Id : ConferenceItem", Toast.LENGTH_SHORT).show();
			}
			
	

		}

		private void setConferenceData(ConferenceItem conferenceItem, View view) {
			TextView textView = (TextView) view.findViewById(R.id.conferenceName);
			conferenceItem.setConferenceName((String) textView.getText());
			
			ImageView team_1 = (ImageView) view.findViewById(R.id.team_1_logo);
			TextView team_Name1 = (TextView) view.findViewById(R.id.team_1_name);
			TeamItem teamItem1 = new TeamItem();
			//teamItem1.setTeamLogo(team_1.);
			//teamItem1.setDrawable(team_1.getDrawable());
			teamItem1.setTeamName((String) team_Name1.getText());
			conferenceItem.setTeam_1(teamItem1);
			
			ImageView team_2 = (ImageView) view.findViewById(R.id.team_2_logo);
			TextView team_Name2 = (TextView) view.findViewById(R.id.team_2_name);
			TeamItem teamItem2 = new TeamItem();
			//teamItem2.setDrawable(team_2.getDrawable());
			//teamItem2.setTeamLogo(team_2.getImageAlpha());
			teamItem2.setTeamName((String) team_Name2.getText());
			conferenceItem.setTeam_2(teamItem2);
			
			ImageView team_3 = (ImageView) view.findViewById(R.id.team_3_logo);
			TextView team_Name3 = (TextView) view.findViewById(R.id.team_3_name);
			TeamItem teamItem3 = new TeamItem();
			//teamItem3.setDrawable(team_3.getDrawable());
			//teamItem3.setTeamLogo(team_3.getImageAlpha());
			teamItem3.setTeamName((String) team_Name3.getText());
			conferenceItem.setTeam_3(teamItem3);
			
			ImageView team_4 = (ImageView) view.findViewById(R.id.team_4_logo);
			TextView team_Name4 = (TextView) view.findViewById(R.id.team_4_name);
			TeamItem teamItem4 = new TeamItem();
			//teamItem4.setDrawable(team_4.getDrawable());
			//teamItem4.setTeamLogo(team_4.getImageAlpha());
			teamItem4.setTeamName((String) team_Name4.getText());
			conferenceItem.setTeam_4(teamItem4);
			//ConferenceHolder ConferenceHolder = (ConferenceHolder)arg1;
		}


	};
}
