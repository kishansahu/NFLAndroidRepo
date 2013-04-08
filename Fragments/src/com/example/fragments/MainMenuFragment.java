package com.example.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		MenuItem menuItem = menu.add(0, 1, 1, "Menu");
		menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d("Fragment 1", "onAttach");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("Fragment 1", "onCreate");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("Fragment 1", "onActivityCreated");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d("Fragment 1", "onStart");

		listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, globalNavigationMenuItems);

		listView1.setAdapter(adapter);

	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("Fragment 1", "onResume");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d("Fragment 1", "onPause");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d("Fragment 1", "onStop");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d("Fragment 1", "onDestroyView");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("Fragment 1", "onDestroy");
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d("Fragment 1", "onDetach");
	}

}
