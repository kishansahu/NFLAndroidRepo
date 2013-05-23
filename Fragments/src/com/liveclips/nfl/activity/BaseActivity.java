package com.liveclips.nfl.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.liveclips.nfl.R;
import com.liveclips.nfl.fragment.MainMenuFragment;

public class BaseActivity extends Activity {

	View fragmentMenuHeaderView;
	View activityMenuHeaderView;
	View sliderView;
	FragmentManager fragmentManager;
	FragmentTransaction fragmentTransaction;
	Fragment mainMenuFragment;

	private void performSliderAction() {
		Log.d("slider", "clickee");
		fragmentManager = getFragmentManager();
		fragmentTransaction = fragmentManager.beginTransaction();
		mainMenuFragment = new MainMenuFragment();

		fragmentTransaction.replace(R.id.menuFragment, mainMenuFragment);
		fragmentTransaction.commit();
		fragmentMenuHeaderView = getActionBar().getCustomView().findViewById(R.id.commonFragmentMenuHeader);
		TextView menuTitle = (TextView) fragmentMenuHeaderView
				.findViewById(R.id.menuTitle);
		menuTitle.setText("Menu");
		ImageView closeBtnImage = (ImageView) fragmentMenuHeaderView
				.findViewById(R.id.closeButtonHeader);
		closeBtnImage.setVisibility(View.INVISIBLE);
		fragmentMenuHeaderView.setVisibility(View.VISIBLE);
		activityMenuHeaderView.setVisibility(View.INVISIBLE);

	};

	private OnClickListener closeButtonListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Log.d("gameclose", "closeclick");
			fragmentTransaction = fragmentManager.beginTransaction();
			mainMenuFragment = getFragmentManager().findFragmentById(
					R.id.menuFragment);
			if (mainMenuFragment.isVisible()) {

				fragmentTransaction.hide(mainMenuFragment);
				// showSlider = true;
				fragmentMenuHeaderView.setVisibility(View.INVISIBLE);
				sliderView.setVisibility(View.VISIBLE);
				activityMenuHeaderView.setVisibility(View.VISIBLE);

			} else {

				fragmentTransaction.show(mainMenuFragment);

				fragmentTransaction.addToBackStack(null);

			}

			fragmentTransaction.commit();

		}

	};

	@Override
	protected void onStart() {
		super.onStart();
		ActionBar actionBar = getActionBar();
		View mActionBarView = actionBar.getCustomView();
		Drawable d = getResources().getDrawable(R.drawable.orange_gradient_background);
		actionBar.setBackgroundDrawable(d);
		fragmentMenuHeaderView = mActionBarView
				.findViewById(R.id.commonFragmentMenuHeader);
		System.out.println(fragmentMenuHeaderView);
		activityMenuHeaderView = mActionBarView
				.findViewById(R.id.activityMenuHeader);
		sliderView = mActionBarView.findViewById(R.id.sliderView);
		sliderView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				performSliderAction();

			}
		});

		View closeButtonView = mActionBarView
				.findViewById(R.id.closeButtonHeader);
		closeButtonView.setOnClickListener(closeButtonListener);
	}

}
