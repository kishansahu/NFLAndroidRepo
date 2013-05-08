package com.liveclips.nfl.fragment;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.R;

public class AddPlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;

	String[] playersCategoriesMenuItems = { "Top Players", "By Team ", "By Position",
			"By College"};
	ListView findPLayerByCategoryListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.players_by_categories_menu_fragment, container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		currentActivity = getActivity();
		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);
		
		Button playerMenuDoneButton = (Button) getActivity().findViewById(R.id.playerMenuDone);
		playerMenuDoneButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fragmentManager = getActivity().getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment frg = fragmentManager.findFragmentById(R.id.menuFragment);
				ft.hide(frg);
				ft.commit();
			}
		});

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, playersCategoriesMenuItems);

		findPLayerByCategoryListView.setAdapter(adapter);

		findPLayerByCategoryListView.setOnItemClickListener(findPLayerByCategoryListViewItemListener);

	}

	/**
	 * Category selected to choose player, to add player
	 */
	
	private OnItemClickListener findPLayerByCategoryListViewItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			TextView selectedtextView = (TextView) arg1;
	//		if (selectedtextView.getText().equals("By Team")) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment addPlayerSelectedCategoryMenuFragment = new AddPlayerSelectedCategoryMenuFragment();
				ft.replace(R.id.menuFragment, addPlayerSelectedCategoryMenuFragment);
				ft.commit();
		}

	};

}
