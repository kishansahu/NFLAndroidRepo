package com.liveclips.nfl.activity;

import java.util.ArrayList;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.liveclips.nfl.R;
import com.liveclips.nfl.fragment.AddPlayersFragment;
import com.liveclips.nfl.imageutils.ImageLoader;
import com.liveclips.nfl.utils.DownloadImagesThreadPool;
import com.liveclips.nfl.utils.PlayCardView;

public class PlayersActivity extends BaseActivity {

	protected Context context = PlayersActivity.this;

	FragmentManager fragmentManager;
	Fragment mainMenuFragment;
	Fragment addPlayersFragment;
	FragmentTransaction ft;
	ImageLoader imgLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.players_activity);
		imgLoader = new ImageLoader(this);

		fragmentManager = getFragmentManager();
		playCards();

		String[] playersName = { "Tom Brady", "Rob Gronkowski" };
		String[] playersDetails = { "#12 | QB", "#87 | TE" };
		String[] playerGameDetails = { "20 / 29, 268 YDS,2 TD, 1 INT",
				"5 REC, 56 YDS, 2 TD" };
		String[] playerPicUrl = {
				"http://172.16.3.210:8081/nfl-images/nfl_players_images/00-0000108.png",
				"http://a.espncdn.com/combiner/i?img=/i/headshots/nfl/players/full/13229.png&w=350&h=254" };

		/**
		 * include a xml multiple time in a parent xml
		 */

		LinearLayout wrapper = (LinearLayout) findViewById(R.id.myPlayersContainer);
		// LinearLayout inflatedView;

		if (playersName.length != 0) {
			int i;
			for (i = 0; i < playersName.length; i++) {

				final LinearLayout inflatedView = (LinearLayout) View.inflate(
						this, R.layout.common_players_detail, null);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerName))
						.setText(playersName[i]);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerDetails))
						.setText(playersDetails[i]);
				((TextView) inflatedView
						.findViewById(R.id.myindividualPlayerGameDetails))
						.setText(playerGameDetails[i]);
				ImageView playerImage = ((ImageView) inflatedView
						.findViewById(R.id.myindividualPlayerPic));
				imgLoader.DisplayImage(playerPicUrl[i], playerImage);
				ImageView myindividualPlayerFavouritePic = (ImageView) inflatedView
						.findViewById(R.id.myindividualPlayerFavouritePic);
				myindividualPlayerFavouritePic
						.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								RelativeLayout favPlayerDetailHolder = (RelativeLayout) inflatedView
										.findViewById(R.id.favPlayerDetailHolder);
								favPlayerDetailHolder.setVisibility(View.GONE);
								View favPlayerDetailHolderSeperator = (View) inflatedView
										.findViewById(R.id.favPlayerDetailHolderSeperator);
								favPlayerDetailHolderSeperator
										.setVisibility(View.GONE);
							}
						});

				wrapper.addView(inflatedView);

			}
		} else {
			ImageView allMyPlayersIcon = (ImageView) findViewById(R.id.allmyplayersIcon);
			allMyPlayersIcon.setVisibility(View.GONE);
			LinearLayout inflatedView = (LinearLayout) View.inflate(this,
					R.layout.players_activity_empty_myplayers_banner, null);
			wrapper.addView(inflatedView);

		}

		ImageButton addaplayerButton = (ImageButton) findViewById(R.id.addaplayerButton);
		addaplayerButton.setOnClickListener((new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showAddPlayersFragment();

			}
		}));

		createCustomActionBar();

	}

	protected void createCustomActionBar() {

		ActionBar actionBar = getActionBar();
		View mActionBarView = getLayoutInflater().inflate(
				R.layout.players_actionbar, null);
		actionBar.setCustomView(mActionBarView);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		Drawable d = getResources().getDrawable(R.drawable.orange_gradient_background);
		actionBar.setBackgroundDrawable(d);
	}

	public void showAddPlayersFragment() {

		ft = fragmentManager.beginTransaction();
		addPlayersFragment = new AddPlayersFragment();
		ft.replace(R.id.menuFragment, addPlayersFragment);
		ft.commit();

	}

	private void playCards() {

		ArrayList<String> playCardTopDetail = new ArrayList<String>();
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");
		playCardTopDetail.add("45-Yard Pass, 1st Down");
		playCardTopDetail.add("2-Yard Run");
		playCardTopDetail.add("16-Yard, TouchDown");
		playCardTopDetail.add("18-Yard, TouchDown");

		ArrayList<String> playCardBottomDetail = new ArrayList<String>();
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");
		playCardBottomDetail
				.add("Tom Brady pass complete to Wes Welker for 45-yards to the GB 10 for a 1st down (6:38 4th)");
		playCardBottomDetail
				.add("Stevan Ridley rush for 2-yards to the NE 45 (6:42 4th)");
		playCardBottomDetail
				.add("Arian Foster rush for 16-yards for a TOUCHDOWN (3:17 4th)");
		playCardBottomDetail
				.add("Shayne Graham kicks 63-yards from HOU 35 to ATL 2.Jacquizz Rodgers to ATL 20 for 18-yards (3:09 4th)");

		// The parent LinearLayout for playCards
		LinearLayout playCardParentLinearLayout = (LinearLayout) findViewById(R.id.parentLayoutOfPlayCardsId);

		DownloadImagesThreadPool downloadImagesThreadPool = new DownloadImagesThreadPool();

		TextView selectedCategoryTextView = (TextView) findViewById(R.id.selectedCategoryTextViewId);
		selectedCategoryTextView.setText("NEW ENGLAND PATRIOTS");

		for (int index = 0; index < 6; index++) {

			playCardParentLinearLayout.addView(getPlayCardView(context, index,
					playCardTopDetail.get(index),
					playCardBottomDetail.get(index), getResources(),
					downloadImagesThreadPool));
			View marginView = new View(context);
			marginView.setLayoutParams(new LayoutParams(20, 0));
			playCardParentLinearLayout.addView(marginView);
		}

	}

	private View getPlayCardView(Context context2, int index,
			String playCardTopDetail, String playCardBottomDetail,
			Resources resources,
			DownloadImagesThreadPool downloadImagesThreadPool) {

		return new PlayCardView(context2, index, playCardTopDetail,
				playCardBottomDetail, resources, this)
				.getPlayCard(downloadImagesThreadPool);
	}
}
