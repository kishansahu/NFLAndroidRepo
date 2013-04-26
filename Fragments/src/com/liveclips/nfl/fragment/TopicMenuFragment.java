package com.liveclips.nfl.fragment;

import android.app.Fragment;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.fragments.R;
import com.liveclips.nfl.activity.NFLHighlightsActivity;

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
		public void onItemClick(AdapterView<?> adapterView, View view, int position,
				long arg3) {
			switch(position){
			
			case 0: 
				Toast.makeText(getActivity().getApplicationContext(), topics[position], Toast.LENGTH_SHORT).show();
				///startActivity(new Intent("com.liveclips.nfl.activity.GameActivity"));
				break;
			case 1 : 
				Toast.makeText(getActivity().getApplicationContext(), topics[position], Toast.LENGTH_SHORT).show();
				//startActivity(new Intent("com.liveclips.nfl.activity.GameActivity"));
				break;
			case 2 :
				Toast.makeText(getActivity().getApplicationContext(), topics[position], Toast.LENGTH_SHORT).show();
				startActivity(new Intent(getActivity(), NFLHighlightsActivity.class));
				break;
			case 3:
				Toast.makeText(getActivity().getApplicationContext(), topics[position], Toast.LENGTH_SHORT).show();
				//startActivity(new Intent("com.liveclips.nfl.activity.GameActivity"));
				break;
			case 4 : 
				Toast.makeText(getActivity().getApplicationContext(), topics[position], Toast.LENGTH_SHORT).show();
				//startActivity(new Intent("com.liveclips.nfl.activity.GameActivity"));
				break;
			case 5 : 
				Toast.makeText(getActivity().getApplicationContext(), topics[position], Toast.LENGTH_SHORT).show();
				//startActivity(new Intent("com.liveclips.nfl.activity.GameActivity"));
				break;
			}
			
			
			// startActivity(new Intent(getActivity(), GameActivity.class));

		}

	};

}
