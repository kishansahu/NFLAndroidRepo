package com.liveclips.nfl.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.liveclips.nfl.R;
import com.liveclips.nfl.activity.GameActivity;
import com.liveclips.nfl.activity.NFLHighlightsActivity;
import com.liveclips.nfl.activity.PlayersActivity;
import com.liveclips.nfl.adapter.TopicMenuArrayAdapter;

public class TopicMenuFragment extends Fragment {

	private int selectedIndex = 0;
	
	String[] topics = { "New England Patriots", "My Players", "NFL Highlights",
			"Divisions", "Teams", "Game Schedule" };

	ListView listView1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("Fragment 1", "onCreateView");

		return inflater.inflate(R.layout.main_fragment_view, container, false);

	}


	@Override
	public void onStart() {
		super.onStart();
		Log.d("Fragment 1", "onStart");

		listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, topics);*/

		TopicMenuArrayAdapter adapter = new TopicMenuArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, topics);
		
		listView1.setAdapter(adapter);
		
		listView1.setOnItemClickListener(listItemListener);

	}

	private OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			switch (position) {

			case 0:
				arg1.setBackgroundDrawable((new ColorDrawable(Color.CYAN)));
				startActivity(new Intent(getActivity(), GameActivity.class));
				break;
			case 1:
				startActivity(new Intent(getActivity(), PlayersActivity.class));
				break;
			case 2:
				startActivity(new Intent(getActivity(),
						NFLHighlightsActivity.class));
				break;
			case 3:

				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment mainMenuFragment = new DivisionMenuFragment();
				// mainMenuFragment.//new TopicMenuFragment();
				ft.replace(R.id.menuFragment, mainMenuFragment);

				ft.commit();
				break;
			case 4:

				fragmentManager = getFragmentManager();
				ft = fragmentManager.beginTransaction();
				Fragment teamMenuFragment = new TeamsMenuFragment();
				ft.replace(R.id.menuFragment, teamMenuFragment);

				ft.commit();

				break;
			case 5:
				
				fragmentManager = getFragmentManager();
				ft = fragmentManager.beginTransaction();
				Fragment gameScheduleFragment = new GameScheduleFragment();
				ft.replace(R.id.menuFragment, gameScheduleFragment);
				
				ft.commit();

				break;
			}

		}

	};

}
