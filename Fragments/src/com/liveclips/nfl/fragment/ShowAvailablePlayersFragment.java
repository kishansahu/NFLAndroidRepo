package com.liveclips.nfl.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.AddPlayerListAdapter;
import com.liveclips.nfl.adapter.AddPlayersFavouritList;
import com.liveclips.nfl.model.PlayerItem;

public class ShowAvailablePlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	ListView addPlayersToFavListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.addplayer_to_favorites_team_fragment,
				container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		AddPlayerListAdapter adapter = new AddPlayerListAdapter(getActivity(),
				getPlayersForAddition("teamName"));
		
		addPlayersToFavListView = (ListView) getActivity().findViewById(
				R.id.addPlayersToFavListView);
		addPlayersToFavListView.setAdapter(adapter);

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

		List<PlayerItem> playersList = null;

		final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
		String playerNames[] = { "Aiken, Danny", "Allen, Will",
				"Arrignton, Kyle", "Ballard, Jake", "Barrett, Josh",
				"Bequette, Jake", "Bolden, Brandon", "Brace, Ron" };
		String playerNumber[] = { "48", "26", "24", "88", "30", "92", "38",
				"97" };
		String playerPosition[] = { "LS", "DB", "CB", "TE", "SS", "DE", "RB",
				"DT" };

		boolean isPlayerFavouriteActive[] = {false,false,true,false,false,true,false,false}; 
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

}
