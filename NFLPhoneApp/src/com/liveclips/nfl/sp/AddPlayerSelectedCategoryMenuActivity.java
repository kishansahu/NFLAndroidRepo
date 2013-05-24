package com.liveclips.nfl.sp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.liveclips.nfl.sp.adapter.PlayerListViewAdapter;
import com.liveclips.nfl.sp.adapter.SeparatedListAdapter;
import com.liveclips.nfl.sp.model.PlayerItem;

public class AddPlayerSelectedCategoryMenuActivity extends Activity{

	public static String t="AddPlayerSelectedCategoryMenuActivity";
	SeparatedListAdapter adapter;
	ListView findPLayerByCategoryListView;
	Context context;
	Intent i;
	int selected;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_player);
		context=this;
		i=getIntent();
		selected=i.getIntExtra("selectedcategory", 0);
		setContent();
		Button back = (Button)findViewById(R.id.add_player_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		 
		
}
	private void setContent() {
		

		 findPLayerByCategoryListView = (ListView)findViewById(
				R.id.findPLayerByCategoryListView);
		 EditText searchBarPlayer = (EditText)findViewById(
					R.id.searchBarPlayerByCategory);
		 searchBarPlayer.setVisibility(View.GONE);

		/* On click of category specific player selection
		findPLayerByCategoryListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
					

					}
				});*/
		
	}
	public List<PlayerItem> getPlayersByTeam(String teamName) {

		List<PlayerItem> playerListByTeam = null;

		if (teamName.equals("AFC EAST")) {
			final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
			String playerNamesForTeam1[] = { "Buffalo Bills", "Miami Dolphins",
					"New England Patriots", "New York Jets", };

			int playerImagesForTeam1[] = { R.drawable.bills,
					R.drawable.miami_dolphins, R.drawable.patriots,
					R.drawable.jets };

			PlayerItem item;

			for (int i = 0; i < playerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerImage(playerImagesForTeam1[i]);
				item.setPlayerName(playerNamesForTeam1[i]);
				playerList.add(item);
			}
			playerListByTeam = playerList;
		} else if (teamName.equals("AFC NORTH")) {
			final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
			String playerNamesForTeam1[] = { "Baltimore Ravens",
					"Cincinnati Bengals", "Cleveland Browns",
					"Pittsburgh Steelers" };

			int playerImagesForTeam1[] = { R.drawable.ravens,
					R.drawable.bengal, R.drawable.browns, R.drawable.pittsburgh };

			PlayerItem item;

			for (int i = 0; i < playerNamesForTeam1.length; i++) {
				item = new PlayerItem();
				item.setPlayerImage(playerImagesForTeam1[i]);
				item.setPlayerName(playerNamesForTeam1[i]);
				playerList.add(item);
			}
			playerListByTeam = playerList;
		}
		return playerListByTeam;
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		bindList(selected,findPLayerByCategoryListView);
		
	}
	private void bindList(int selected,ListView listView) {
		if (selected==0) {
			
		}else if (selected==1) {
			 adapter = new SeparatedListAdapter(context);
			 adapter.addSection("AFC EAST", new PlayerListViewAdapter(context,
					R.layout.players_fragment_list_row_item_addplayer,
					getPlayersByTeam("AFC EAST")));
			 adapter.addSection("AFC NORTH", new PlayerListViewAdapter(
					context, R.layout.players_fragment_list_row_item_addplayer,
					getPlayersByTeam("AFC NORTH")));
			 listView.setAdapter(adapter);
			 listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
				Intent i= new Intent(context,ShowAvailablePlayersActivity.class);
				startActivityForResult(i, 10009);
				}
			});
		}else if (selected==2) {
			
		}else if (selected==3) {
			
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("onActivityResult", requestCode+"   "+ resultCode);
		Log.d("t", "onActivityResult");
		if (resultCode==RESULT_OK&&requestCode==10009) {
			Log.d("t", "onActivityResult");
			setResult(RESULT_OK, getIntent());
			finish();
		}
	}	
}