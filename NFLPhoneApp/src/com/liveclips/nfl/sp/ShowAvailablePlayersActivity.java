package com.liveclips.nfl.sp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.liveclips.nfl.sp.adapter.AddPlayerListAdapter;
import com.liveclips.nfl.sp.model.PlayerItem;

public class ShowAvailablePlayersActivity extends Activity{

	
	public static String t="ShowAvailablePlayersActivity";
	View currentView;
	Activity currentActivity;
	ListView addPlayersToFavListView;
	List playerForAddition;
	List<PlayerItem> playersList = null;
	AddPlayerListAdapter adapter;
	private String nameColumnSortOrder = "none";
	private String playerNumColumnSortOrder = "none";
	private String playerPosColumnSortOrder = "none";
	private String playerFavColumnSortOrder = "none";
	ImageView playerNameSortArrow, playerNumSortArrow, playerPosSortArrow,
			playerFavSortArrow;
	RelativeLayout playerNameinAddInFavouriteBanner,
			playerNumSubheaderinAddInFavouriteBanner,
			playerPosSubheaderinAddInFavouriteBanner,
			playerFavSubheaderinAddInFavouriteBanner;
	Context context;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players_fragment_view_favorites_team_player);
		context=this;
		
		playerForAddition = getPlayersForAddition("teamName");
		adapter = new AddPlayerListAdapter(context, playerForAddition);

		addPlayersToFavListView = (ListView)findViewById(
				R.id.addPlayersToFavListView);
		addPlayersToFavListView.setAdapter(adapter);
		playerNameinAddInFavouriteBanner = (RelativeLayout)findViewById(R.id.playerNameinAddInFavouriteBanner);
		playerNameinAddInFavouriteBanner
				.setOnClickListener(nameColumnClickedListener);

		playerNumSubheaderinAddInFavouriteBanner = (RelativeLayout)findViewById(R.id.playerNuminAddInFavouriteBanner);
		playerNumSubheaderinAddInFavouriteBanner
				.setOnClickListener(playerNumColumnClickedListener);

		playerPosSubheaderinAddInFavouriteBanner = (RelativeLayout)findViewById(R.id.playerPosinAddInFavouriteBanner);
		playerPosSubheaderinAddInFavouriteBanner
				.setOnClickListener(playerPosColumnClickedListener);

		playerFavSubheaderinAddInFavouriteBanner = (RelativeLayout)findViewById(R.id.playerFavinAddInFavouriteBanner);
		playerFavSubheaderinAddInFavouriteBanner
				.setOnClickListener(playerFavColumnClickedListener);

		playerNameSortArrow = (ImageView)findViewById(
				R.id.playerNameSortArrow);
		playerNumSortArrow = (ImageView)findViewById(
				R.id.playerNumSortArrow);
		playerPosSortArrow = (ImageView)findViewById(
				R.id.playerPosSortArrow);
		playerFavSortArrow = (ImageView)findViewById(
				R.id.playerFavSortArrow);
}
	public List<PlayerItem> getPlayersForAddition(String teamName) {

		final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
		String playerNames[] = { "Ballard, Jake", "Aiken, Danny",
				"Allen, Will", "Arrignton, Kyle", "Barrett, Josh",
				"Bequette, Jake", "Bolden, Brandon", "Brace, Ron" };
		String playerNumber[] = { "48", "26", "24", "88", "30", "92", "38",
				"97" };
		String playerPosition[] = { "LS", "DB", "CB", "TE", "SS", "DE", "RB",
				"DT" };

		boolean isPlayerFavouriteActive[] = { false, false, true, false, false,
				true, false, false };
		PlayerItem item;

		for (int i = 0; i < playerNames.length; i++) {
			item = new PlayerItem();
			item.setPlayerName(playerNames[i]);
			item.setPlayerPosition(playerPosition[i]);
			item.setPlayerNumber(playerNumber[i]);
			item.setPLayerFavouriteActive(isPlayerFavouriteActive[i]);
			playerList.add(item);
		}
		playersList = playerList;
		return playersList;
	}

	OnClickListener nameColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerNameSortArrow.setVisibility(View.VISIBLE);
			if (nameColumnSortOrder.equalsIgnoreCase("none")
					|| nameColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerNameInAscendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNameSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				nameColumnSortOrder = "ascending";

			} else if (nameColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerNameInDescendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNameSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				nameColumnSortOrder = "descending";
			}
		}
	};

	OnClickListener playerNumColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerNumSortArrow.setVisibility(View.VISIBLE);
			// set player Name in alphabetical order
			Collections.sort(playersList,
					new PlayerItem.playerNameInAscendingOrder());

			if (playerNumColumnSortOrder.equalsIgnoreCase("none")
					|| playerNumColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerNumInAscendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNumSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				playerNumColumnSortOrder = "ascending";

			} else if (playerNumColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerNumInDescendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNumSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				playerNumColumnSortOrder = "descending";
			}

		}
	};

	OnClickListener playerPosColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerPosSortArrow.setVisibility(View.VISIBLE);
			// set player Name in alphabetical order
			Collections.sort(playersList,
					new PlayerItem.playerNameInAscendingOrder());

			if (playerPosColumnSortOrder.equalsIgnoreCase("none")
					|| playerPosColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerPosInAscendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerPosSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				playerPosColumnSortOrder = "ascending";

			} else if (playerPosColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerPosInDescendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerPosSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				playerPosColumnSortOrder = "descending";
			}

		}
	};

	OnClickListener playerFavColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			hideSortingIcons();
			playerFavSortArrow.setVisibility(View.VISIBLE);
			// set player Name in alphabetical order
			Collections.sort(playersList,
					new PlayerItem.playerNameInAscendingOrder());

			if (playerFavColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerFavInAscendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerFavSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_up);
				playerFavColumnSortOrder = "ascending";

			} else if (playerFavColumnSortOrder.equalsIgnoreCase("none")
					|| playerFavColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerFavInDescendingOrder());
				adapter = new AddPlayerListAdapter(context, playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerFavSortArrow
						.setImageResource(R.drawable.icon_popover_arrow_down);
				playerFavColumnSortOrder = "descending";
			}

		}
	};

	private void hideSortingIcons() {
		if (playerNameSortArrow.getVisibility() == View.VISIBLE) {
			playerNameSortArrow.setVisibility(View.GONE);
		}
		if (playerNumSortArrow.getVisibility() == View.VISIBLE) {
			playerNumSortArrow.setVisibility(View.GONE);
		}
		if (playerPosSortArrow.getVisibility() == View.VISIBLE) {
			playerPosSortArrow.setVisibility(View.GONE);
		}
		if (playerFavSortArrow.getVisibility() == View.VISIBLE) {
			playerFavSortArrow.setVisibility(View.GONE);
		}
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("t", "onDestroy");
		// setResult(RESULT_OK, getIntent());
	}
	
}
