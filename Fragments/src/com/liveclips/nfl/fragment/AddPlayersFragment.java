package com.liveclips.nfl.fragment;

import android.app.ActionBar;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.nfl.R;

public class AddPlayersFragment extends Fragment {

	View currentView;
	Activity currentActivity;
	static ArrayAdapter<String> adapterForCategoryList, adapterForSearchList;

	String[] playersCategoriesMenuItems = { "Top Players", "By Team ",
			"By Position", "By College" };

	String[] anySearchForPlayersMenuItems = { "Tom Brady", "Rob Gronkowski", };
	ListView findPLayerByCategoryListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle saveInstantState) {

		return inflater.inflate(R.layout.players_fragment_view_by_categories,
				container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		final View newfragMenuHeader = getActivity().getLayoutInflater()
				.inflate(R.layout.players_fragment_actionbar_header_find_player, null);
		ActionBar actionBar = getActivity().getActionBar();
		final RelativeLayout actionBarLayout = (RelativeLayout) actionBar
				.getCustomView();
		final RelativeLayout activityHeaderLayout = (RelativeLayout) getActivity()
				.findViewById(R.id.activityMenuHeader);
		activityHeaderLayout.setVisibility(View.INVISIBLE);
		final RelativeLayout fragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.fragmentMenuHeader);
		if (fragMenuHeader != null) {
			actionBarLayout.removeView(fragMenuHeader);
		}
		RelativeLayout commonfragMenuHeader = (RelativeLayout) actionBarLayout
				.findViewById(R.id.commonFragmentMenuHeader);
		if (commonfragMenuHeader != null) {
			commonfragMenuHeader.setVisibility(View.INVISIBLE);
		}
		actionBarLayout.addView(newfragMenuHeader, 300, 52);
		Button playerMenuDoneButton = (Button) actionBarLayout
				.findViewById(R.id.playerMenuDone);
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
				actionBarLayout.removeView(newfragMenuHeader);
				View sliderView = activityHeaderLayout
						.findViewById(R.id.sliderView);
				if (sliderView.getVisibility() != View.VISIBLE)
					sliderView.setVisibility(View.VISIBLE);
				activityHeaderLayout.setVisibility(View.VISIBLE);
				/*View commonfragMenuHeader = getActivity().getLayoutInflater()
						.inflate(R.layout.common_fragment_menu_header, null);*/
				//actionBarLayout.addView(commonfragMenuHeader, 300, 52);
			}
		});
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		currentActivity = getActivity();
		findPLayerByCategoryListView = (ListView) getActivity().findViewById(
				R.id.findPLayerByCategoryListView);

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

		ActionBar actionBar = currentActivity.getActionBar();
		View customView = actionBar.getCustomView();

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
			ft.addToBackStack(null);
			ft.commit();
		}

	};

}
