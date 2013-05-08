package com.liveclips.nfl.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.adapter.PlayerListViewAdapter;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.model.PlayerItem;

public class AddPlayerSelectedCategoryMenuFragment extends Fragment {

	ListView findPLayerByCategoryListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		return inflater.inflate(R.layout.players_by_categories_menu_fragment,
				container, false);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onStart() {

		Button backToPlayerFragmentButton = (Button) getActivity()
				.findViewById(R.id.backToPlayerFragment);
		backToPlayerFragmentButton.setVisibility(View.VISIBLE);
		backToPlayerFragmentButton
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getFragmentManager();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();

						AddPlayersFragment addPlayersFragment = new AddPlayersFragment();
						ft.replace(R.id.menuFragment, addPlayersFragment);
						ft.commit();

					}
				});

		Button playerMenuDoneButton = (Button) getActivity().findViewById(
				R.id.playerMenuDone);
		playerMenuDoneButton.setVisibility(View.INVISIBLE);

		EditText searchBarPlayerByCategory = (EditText) getActivity()
				.findViewById(R.id.searchBarPlayerByCategory);
		searchBarPlayerByCategory.setVisibility(View.GONE);

		TextView playerMenuTitle = (TextView) getActivity().findViewById(
				R.id.playerMenuTitle);
		playerMenuTitle.setText("By Team");

		super.onStart();

		SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
		adapter.addSection("AFC EAST", new PlayerListViewAdapter(getActivity(),
				R.layout.addplayer_by_team_fragment,
				getPlayersByTeam("AFC EAST")));
		adapter.addSection("AFC NORTH", new PlayerListViewAdapter(
				getActivity(), R.layout.addplayer_by_team_fragment,
				getPlayersByTeam("AFC NORTH")));

		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);

		/* On click of category specific player selection*/
		findPLayerByCategoryListView
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						FragmentManager fragmentManager = getFragmentManager();
						FragmentTransaction ft = fragmentManager
								.beginTransaction();

						ShowAvailablePlayersFragment showAvailablePlayersFragment = new ShowAvailablePlayersFragment();
						ft.replace(R.id.menuFragment,
								showAvailablePlayersFragment);
						ft.commit();

					}
				});

		findPLayerByCategoryListView.setAdapter(adapter);

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

}