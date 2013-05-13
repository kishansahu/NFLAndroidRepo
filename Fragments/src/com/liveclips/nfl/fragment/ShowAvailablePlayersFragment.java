package com.liveclips.nfl.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.AddPlayerListAdapter;
import com.liveclips.nfl.model.PlayerItem;

public class ShowAvailablePlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	ListView addPlayersToFavListView;
	List playerForAddition;
	List<PlayerItem> playersList = null;
	AddPlayerListAdapter adapter;
	private String nameColumnSortOrder = "none";
	private String playerNumColumnSortOrder = "none";
	ImageView playerNameSortArrow, playerNumSortArrow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.addplayer_to_favorites_team_fragment,
				container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		playerForAddition = getPlayersForAddition("teamName");
		adapter = new AddPlayerListAdapter(getActivity(), playerForAddition);

		addPlayersToFavListView = (ListView) getActivity().findViewById(
				R.id.addPlayersToFavListView);
		addPlayersToFavListView.setAdapter(adapter);
		RelativeLayout playerNameinAddInFavouriteBanner = (RelativeLayout) getActivity()
				.findViewById(R.id.playerNameinAddInFavouriteBanner);
		playerNameinAddInFavouriteBanner
				.setOnClickListener(nameColumnClickedListener);
		
		RelativeLayout playerNumSubheaderinAddInFavouriteBanner = (RelativeLayout) getActivity()
				.findViewById(R.id.playerNuminAddInFavouriteBanner);
		playerNumSubheaderinAddInFavouriteBanner
		.setOnClickListener(playerNumColumnClickedListener);
		
		 playerNameSortArrow = (ImageView) getActivity().findViewById(R.id.playerNameSortArrow);
		 playerNumSortArrow = (ImageView) getActivity().findViewById(R.id.playerNumSortArrow);
		
		Button playerMenuDoneButton = (Button) getActivity().findViewById(
				R.id.playerAddToFavDoneButton);
		playerMenuDoneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getActivity()
						.getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment frg = fragmentManager
						.findFragmentById(R.id.menuFragment);
				ft.hide(frg);
				ft.commit();
			}
		});

		Button backToPlayerCategoryFragmentButton = (Button) getActivity()
				.findViewById(R.id.backToPlayerCategoryFragment);
		backToPlayerCategoryFragmentButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getFragmentManager();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();
						Fragment addPlayerSelectedCategoryMenuFragment = new AddPlayerSelectedCategoryMenuFragment();
						ft.replace(R.id.menuFragment,
								addPlayerSelectedCategoryMenuFragment);
						ft.commit();

					}
				});

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
			if (nameColumnSortOrder.equalsIgnoreCase("none") || nameColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerNameInAscendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNameSortArrow.setImageResource(R.drawable.icon_popover_arrow_up);
				nameColumnSortOrder = "ascending";
				
			} else if (nameColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerNameInDescendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNameSortArrow.setImageResource(R.drawable.icon_popover_arrow_down);
				nameColumnSortOrder = "descending";
			}
		}
	};
	
	OnClickListener playerNumColumnClickedListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (playerNumColumnSortOrder.equalsIgnoreCase("none") || playerNumColumnSortOrder.equalsIgnoreCase("descending")) {
				// set in Ascending Order
				Collections.sort(playersList,
						new PlayerItem.playerNumInAscendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNumSortArrow.setImageResource(R.drawable.icon_popover_arrow_up);
				playerNumColumnSortOrder = "ascending";
				
			} else if (playerNumColumnSortOrder.equalsIgnoreCase("ascending")) {
				// set in Descending Order
				Collections.sort(playersList,
						new PlayerItem.playerNumInDescendingOrder());
				adapter = new AddPlayerListAdapter(getActivity(), playersList);
				addPlayersToFavListView.setAdapter(adapter);
				playerNumSortArrow.setImageResource(R.drawable.icon_popover_arrow_down);
				playerNumColumnSortOrder = "descending";
			}
		}
	};
}
