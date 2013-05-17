package com.liveclips.nfl.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.activity.GameActivity;
import com.liveclips.nfl.activity.NFLHighlightsActivity;
import com.liveclips.nfl.adapter.GameScheduleListViewAdaptor;
import com.liveclips.nfl.adapter.SeparatedListAdapter;
import com.liveclips.nfl.model.GameSchedule;

public class GameScheduleFragment extends Fragment {

	public List<GameSchedule> getGameScheduleForWeek5() {

		String firstTeamNames[] = { "SD", "SEA", "ALT", "GB", "IND" };
		String secondTeamNames[] = { "NYG", "JAC", "HOU", "NE", "TEN" };
		int firstTeamImageNames[] = { R.drawable.bills, R.drawable.seahawks,
				R.drawable.ravens, R.drawable.packers, R.drawable.rams };
		int secondTeamImageNames[] = { R.drawable.titans,
				R.drawable.gore_team_logo, R.drawable.cardinals,
				R.drawable.patriots, R.drawable.jets };
		int firstTeamScores[] = { 3, 34, 24, 21, 14 };
		int secondTeamsScores[] = { 28, 3, 24, 14, 14 };
		boolean isGameInFuture[] = { true, true, true, true, true };
		String timeLeft[] = { "4TH 8:14", "3RD 6:11", "4TH 3:12", "4TH 6:38",
				"3RD 5:23" };

		List<GameSchedule> gameScheduleList = new ArrayList<GameSchedule>();

		for (int i = 0; i < 5; i++) {
			GameSchedule gameSchedule = new GameSchedule();
			gameSchedule.setFirstTeamName(firstTeamNames[i]);
			gameSchedule.setFirstTeamImageId(firstTeamImageNames[i]);
			gameSchedule.setSecondTeamName(secondTeamNames[i]);
			gameSchedule.setSecondTeamImageId(secondTeamImageNames[i]);
			gameSchedule.setFirstTeamScore(firstTeamScores[i]);
			gameSchedule.setSecondTeamScore(secondTeamsScores[i]);
			gameSchedule.setGameInFuture(isGameInFuture[i]);
			gameSchedule.setTimeLeft(timeLeft[i]);
			gameScheduleList.add(gameSchedule);
		}

		return gameScheduleList;
	}

	public OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapterView, View view,
				int lineNo, long arg3) {
			TextView firstTeamNameTextView = (TextView) view
					.findViewById(R.id.first_team_shortname);
			TextView secondTeamNameTextView = (TextView) view
					.findViewById(R.id.second_team_shortname);
			String firstTeamName = (String) firstTeamNameTextView.getText();
			String secondTeamName = (String) secondTeamNameTextView.getText();
			Intent intent = new Intent(getActivity(), GameActivity.class);
			intent.putExtra("firstTeamName", firstTeamName);
			intent.putExtra("secondTeamName", secondTeamName);
			startActivity(intent);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("gameschedule", "gameschedulfragment");

		View gameScheduleView = inflater.inflate(
				R.layout.game_schedule_fragment_view, container, false);
		ListView listView = (ListView) gameScheduleView
				.findViewById(R.id.gameScheduleList);
		SeparatedListAdapter adapter = new SeparatedListAdapter(getActivity());
		adapter.addSection("WEEK5", new GameScheduleListViewAdaptor(
				getActivity(), R.layout.game_popover_list_row__item_player,
				getGameScheduleForWeek5()));
		listView.setOnItemClickListener(listItemListener);
		listView.setAdapter(adapter);
		return gameScheduleView;
	}

}
