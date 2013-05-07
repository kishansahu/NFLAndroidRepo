package com.liveclips.nfl.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.AddPlayersFavouritList;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.model.PlayerItem;

public class ShowAvailablePlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	ListView addPlayersToFavListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("Fragment 1", "onCreateView");

		return inflater.inflate(R.layout.addplayer_to_favorites_team_fragment,
				container, false);
	}

	@Override
	public void onStart() {
		super.onStart();
		SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity(),false);
		adapter.addSection("", new AddPlayersFavouritList(getActivity(),
				R.layout.addplayer_details_fragment,
				getPlayersForAddition("teamName")));

		addPlayersToFavListView = (ListView) getActivity().findViewById(
				R.id.addPlayersToFavListView);
		addPlayersToFavListView.setAdapter(adapter);
	}

	public List<PlayerItem> getPlayersForAddition(String teamName) {

		List<PlayerItem> playersList = null;

		final List<PlayerItem> playerList = new ArrayList<PlayerItem>();
		String playerNames[] = { "Aiken, Danny", "Allen, Will","Arrignton, Kyle","Ballard, Jake",  "Barrett, Josh", "Bequette, Jake","Bolden, Brandon","Brace, Ron"};
		String playerNumber[] = { "48", "26",
				"24", "88","30","92","38","97"};
		String playerPosition[] = { "LS", "DB",
				"CB", "TE","SS","DE","RB","DT"};

		int playerImages[] = { R.drawable.favourite,
				R.drawable.favourite, R.drawable.favourite,
				R.drawable.favourite, R.drawable.favourite,R.drawable.favourite,
				R.drawable.favourite, R.drawable.favourite, };

		PlayerItem item;

		for (int i = 0; i < playerNames.length; i++) {
			item = new PlayerItem();
			item.setPlayerFavourite(playerImages[i]);
			item.setPlayerName(playerNames[i]);
			item.setPlayerPosition(playerPosition[i]);
			item.setPlayerNumber(playerNumber[i]);
			playerList.add(item);
		}
		playersList = playerList;
		return playersList;
	}

}
