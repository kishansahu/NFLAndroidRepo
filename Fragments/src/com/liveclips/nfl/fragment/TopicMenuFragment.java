package com.liveclips.nfl.fragment;

import com.example.fragments.R;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TopicMenuFragment extends Fragment {

	String[] topics = { "New England Patriots", "My Players", "NFL Highlights",
			"Divisions", "Teams", "Game Schedule" };

	ListView listView1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("Fragment 1", "onCreateView");
	
		
		return inflater.inflate(R.layout.main_menu_fragment, container, false);

	}

	/*
	 * @Override public void onCreateOptionsMenu(Menu menu, MenuInflater
	 * inflater) { MenuItem menuItem1 = menu.add(0, 1, 0, "Live Clips");
	 * menuItem1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); MenuItem
	 * menuItem2 = menu.add(0, 1, 0, "close");
	 * menuItem2.setIcon(R.drawable.close);
	 * menuItem2.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); }
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.sliderButton:
			// getActivity().performSliderAction();
			return true;
			/*
			 * case R.id.help: showHelp(); return true;
			 */
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d("Fragment 1", "onStart");

		listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, topics);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(listItemListener);

	}

	
	
	private OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// startActivity(new Intent(getActivity(), GameActivity.class));

		}

	};

}
