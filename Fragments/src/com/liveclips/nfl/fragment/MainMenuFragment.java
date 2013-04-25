package com.liveclips.nfl.fragment;

import com.example.fragments.R;
import com.liveclips.nfl.activity.GameActivity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.LiveFolders;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenuFragment extends Fragment {

	String[] globalNavigationMenuItems = { "Content", "Content", "LiveClips",
			"Content", "Content", "Content", "Content", "Content", "Content" };
	ListView listView1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {
		Log.d("Fragment 1", "onCreateView");
		
		return inflater.inflate(R.layout.main_menu_fragment, container, false);
	}

	

	@Override
	public void onStart() {
		super.onStart();
		Log.d("Fragment 1", "onStart");

		/*listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, globalNavigationMenuItems);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(listItemListener);*/
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, globalNavigationMenuItems);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(listItemListener);
		
	}

	private OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt,
				long mylng) {
			
			String selectedFromList =(String) (listView1.getItemAtPosition(myItemInt));
			
			if(selectedFromList=="LiveClips"){
				startActivity(new Intent(getActivity(), GameActivity.class));			
			}
		}
		
	};

	

}
