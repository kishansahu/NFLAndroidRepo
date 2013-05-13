package com.liveclips.nfl.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.activity.AppContentActivity;
import com.liveclips.nfl.activity.GameActivity;

public class MainMenuFragment extends Fragment {

	View currentView;
	Activity currentActivity;

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
	public void onResume() {
		super.onResume();
		currentActivity = getActivity();
		listView1 = (ListView) getActivity().findViewById(
				R.id.globalNavigationListView);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, globalNavigationMenuItems);

		listView1.setAdapter(adapter);

		listView1.setOnItemClickListener(listItemListener);

	}

	private OnItemClickListener listItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			TextView selectedtextView = (TextView) arg1;
			if (selectedtextView.getText().equals("LiveClips")) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment mainMenuFragment = new TopicMenuFragment();
				ft.replace(R.id.menuFragment, mainMenuFragment);

				ft.commit();
				TextView menuTitle = (TextView) getActivity().findViewById(
						R.id.menuTitle);
				menuTitle.setText(selectedtextView.getText());
				ImageView closeBtnImageView = (ImageView) getActivity().getActionBar().getCustomView()
						.findViewById(R.id.closeButtonHeader);
				closeBtnImageView.setVisibility(View.VISIBLE);
				View sliderView = getActivity().findViewById(R.id.sliderView);
				if (sliderView.getVisibility() == View.VISIBLE) {
					sliderView.setVisibility(View.INVISIBLE);
				}

				// startActivity(new Intent(getActivity(), GameActivity.class));
			}

		}

	};

}
