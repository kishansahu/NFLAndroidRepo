package com.liveclips.nfl.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.R;

public class AddPlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	static ArrayAdapter<String> adapterForCategoryList, adapterForSearchList;

	String[] playersCategoriesMenuItems = { "Top Players", "By Team ",
			"By Position", "By College" };

	String[] anySearchForPlayersMenuItems = { "anuj", "Ron ", "Micheal", "amit" };
	ListView findPLayerByCategoryListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.players_by_categories_menu_fragment,
				container, false);
	}

	@Override
	public void onResume() {
		super.onResume();
		currentActivity = getActivity();
		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);

		Button playerMenuDoneButton = (Button) getActivity().findViewById(
				R.id.playerMenuDone);
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

		adapterForCategoryList = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, playersCategoriesMenuItems);

		findPLayerByCategoryListView.setAdapter(adapterForCategoryList);

		adapterForSearchList = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1,
				anySearchForPlayersMenuItems);

		findPLayerByCategoryListView
				.setOnItemClickListener(findPLayerByCategoryListViewItemListener);

		EditText searchBarPlayer = (EditText) getActivity().findViewById(
				R.id.searchBarPlayerByCategory);
		searchBarPlayer.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence charSequence, int arg1,
					int arg2, int arg3) {
				// When user changed the Text
				AddPlayersFragment.adapterForSearchList.getFilter().filter(
						charSequence);
				findPLayerByCategoryListView.setAdapter(adapterForSearchList);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {

				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				if (arg0.length() == 0) {
					findPLayerByCategoryListView
							.setAdapter(adapterForCategoryList);
				}
			}
		});

	}

	/**
	 * Category selected to choose player, to add player
	 */

	private OnItemClickListener findPLayerByCategoryListViewItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			TextView selectedtextView = (TextView) arg1;
			// if (selectedtextView.getText().equals("By Team")) {
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			Fragment addPlayerSelectedCategoryMenuFragment = new AddPlayerSelectedCategoryMenuFragment();
			ft.replace(R.id.menuFragment, addPlayerSelectedCategoryMenuFragment);
			ft.commit();
		}

	};

}
