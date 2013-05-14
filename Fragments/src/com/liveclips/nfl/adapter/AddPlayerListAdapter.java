package com.liveclips.nfl.adapter;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liveclips.nfl.R;
import com.liveclips.nfl.activity.PlayersActivity;
import com.liveclips.nfl.model.PlayerItem;

public class AddPlayerListAdapter extends BaseAdapter {
	
	Context context;
	List<PlayerItem> playerDetailsListForFavourite;

	public AddPlayerListAdapter(Context context,
			List<PlayerItem> playerDetailsListForFavourite) {
		this.context = context;
		this.playerDetailsListForFavourite = playerDetailsListForFavourite;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return playerDetailsListForFavourite.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View view = null;
		// TODO Auto-generated method stub
		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			view = inflater.inflate(R.layout.addplayer_details_fragment, arg2,
					false);
			PlayerItem PI = playerDetailsListForFavourite.get(arg0);
			int playerFav = PI.getPlayerFavourite();
			final String playerName = PI.getPlayerName();
			final String playerNum = PI.getPlayerNumber();
			final String playerPos = PI.getPlayerPosition();
			final boolean isplayerFavouriteActive = PI
					.isPLayerFavouriteActive();
			TextView playerNameView = (TextView) view
					.findViewById(R.id.playerNameInFragmentList);
			playerNameView.setText(playerName);

			TextView playerNumView = (TextView) view
					.findViewById(R.id.addplayerNumInFragmentList);
			playerNumView.setText(playerNum);

			TextView playerPosView = (TextView) view
					.findViewById(R.id.addplayerposInFragmentList);
			playerPosView.setText(playerPos);
			final String message;
			if (isplayerFavouriteActive) {
				message = "Do you really want to remove " + playerName
						+ " from My Players ?";
			} else {
				message = "Do you really want to add " + playerName
						+ " to My Players ?";
			}

			final ImageView playerFavImage = (ImageView) view
					.findViewById(R.id.addplayerFavInFragmentList);
			if (isplayerFavouriteActive) {
				playerFavImage.setImageResource(R.drawable.star_high);
			} else {
				playerFavImage.setImageResource(R.drawable.star_low);
			}

			playerFavImage.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								// Yes button clicked
								if (isplayerFavouriteActive) {
									// remove player
									playerFavImage
											.setImageResource(R.drawable.star_low);
									
								} else {
									// Add player
									playerFavImage
											.setImageResource(R.drawable.star_high);

									PlayersActivity pl = (PlayersActivity) context;
									LinearLayout wrapper = (LinearLayout) pl
											.findViewById(R.id.myPlayersContainer);

									final LinearLayout inflatedView;
									inflatedView = (LinearLayout) View.inflate(
											context, R.layout.players_detail,
											null);

									((TextView) inflatedView
											.findViewById(R.id.myindividualPlayerName)).setText(playerName);
									((TextView) inflatedView
											.findViewById(R.id.myindividualPlayerDetails))
											.setText("#" + playerNum +" | "  + playerPos);
									((TextView) inflatedView
											.findViewById(R.id.myindividualPlayerGameDetails))
											.setText( "20 / 29, 268 YDS,2 TD, 1 INT");
									((ImageView) inflatedView
											.findViewById(R.id.myindividualPlayerPic))
											.setImageResource(R.drawable.brad_jones);
									ImageView myindividualPlayerFavouritePic=(ImageView) inflatedView
											.findViewById(R.id.myindividualPlayerFavouritePic);
									myindividualPlayerFavouritePic.setOnClickListener(new View.OnClickListener() {
										
										@Override
										public void onClick(View v) {
											RelativeLayout favPlayerDetailHolder = (RelativeLayout) inflatedView.findViewById(R.id.favPlayerDetailHolder);
											favPlayerDetailHolder.setVisibility(View.GONE);
											View favPlayerDetailHolderSeperator = (View) inflatedView.findViewById(R.id.favPlayerDetailHolderSeperator);
											favPlayerDetailHolderSeperator.setVisibility(View.GONE);
										}
									});
									wrapper.addView(inflatedView);
								}
								break;

							case DialogInterface.BUTTON_NEGATIVE:
								// No button clicked
								break;
							}
						}
					};

					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setMessage(message)
							.setPositiveButton("Yes", dialogClickListener)
							.setNegativeButton("No", dialogClickListener)
							.show();

				}
			});
		}
		return view;
	}

}
