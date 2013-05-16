/**
 * 
 */
package com.liveclips.nfl.utils;

import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.liveclips.nfl.R;

/**
 * @author abhijitsrivastava
 * 
 */
public class PlayCardView {

	private Context context;
	private String playCardTopDetail;
	private String playCardBottomDetail;
	private int index;
	Resources resources;
	Activity activity;
	VideoView videoView;

	static RelativeLayout playCardFrontSidePlaySectionVideoViewLayout;

	public PlayCardView() {

	}

	public PlayCardView(Context context, int index, String playCardTopDetail,
			String playCardBottomDetail, Resources resources, Activity activity) {

		this.context = context;
		this.resources = resources;
		this.activity = activity;
		this.index = index;
		this.playCardTopDetail = playCardTopDetail;
		this.playCardBottomDetail = playCardBottomDetail;
	}

	public RelativeLayout getPlayCard(DownloadImagesThreadPool downloadImagesThreadPool ) {

		// Creating a new RelativeLayout
		final RelativeLayout playCardLayout = new RelativeLayout(context);

		// Defining the RelativeLayout layout parameters.
		RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
				500, 400);
		playCardLayoutParameters.addRule(RelativeLayout.CENTER_IN_PARENT);
		playCardLayoutParameters.setMargins(20, 10, 20, 10);
	
		playCardLayout.setId(index * 2);
		playCardLayout.setLayoutParams(playCardLayoutParameters);

		/*if (index % 2 == 0) {*/
			playCardLayout.setBackgroundResource(R.drawable.gray_background);
	/*	} else {
			playCardLayout
					.setBackgroundColor(resources.getColor(R.color.green));
		}*/

		playCardLayout.setPadding(15, 15, 15, 15);

		/*
		final LayoutInflater inflator = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		RelativeLayout parentOfPlayCard = (RelativeLayout) inflator.inflate(R.layout.play_card, null);
		final ViewAnimator playCardViewAnimator = (ViewAnimator) parentOfPlayCard.findViewById(R.id.playCardViewFlipperId);
		
		final RelativeLayout playCardLayout = (RelativeLayout) playCardViewAnimator.findViewById(R.id.playCardLayoutId);
		playCardLayout.setPadding(15, 15, 15, 15);
		*/
		// play card front side detail ********************
		final LayoutInflater inflator = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		final RelativeLayout playCardFrontSide = (RelativeLayout) inflator
				.inflate(R.layout.play_card_front_side, null);

		ImageView playCardFrontSideTopLayoutInfoButton = (ImageView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideTopLayoutInfoButtonId);

		playCardFrontSideTopLayoutInfoButton.setId(index * 200);
		TextView topDetail = (TextView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideTopLayoutTextViewId);
		topDetail.setText(playCardTopDetail);

		topDetail.setId(index * 4000);
		TextView bottomDetail = (TextView) playCardFrontSide
				.findViewById(R.id.playCardFrontSideBottomLayoutTextViewId);
		bottomDetail.setText(playCardBottomDetail);

		bottomDetail.setId(index * 60000);
		final RelativeLayout playCardFrontSidePlaySectionImageWithPlayButtonLayout = (RelativeLayout) inflator
				.inflate(
						R.layout.play_card_front_side_play_section_image_with_play_button,
						null);

		ImageView playCardFrontSidePlaySectionPlayButton = (ImageView) playCardFrontSidePlaySectionImageWithPlayButtonLayout
				.findViewById(R.id.playCardFrontSidePlaySectionImageWithPlayButtonPlayButtonId);

		ImageView playCardFrontSidePlaySectionImage = (ImageView) playCardFrontSidePlaySectionImageWithPlayButtonLayout
				.findViewById(R.id.playCardFrontSidePlaySectionImageWithPlayButtonImageViewId);
		
		final ImageButton videoSizeButton = (ImageButton) playCardFrontSide.findViewById(R.id.playCardFrontSideBottomLayoutImageButtonId);
		videoSizeButton.setBackgroundResource(R.drawable.full_screen_button);
		videoSizeButton.setVisibility(View.INVISIBLE);
		
		/*new DownloadImageTask(playCardFrontSidePlaySectionImage)
				.execute("http://si.wsj.net/public/resources/images/NA-BU548_NFL_G_20130111183225.jpg");*/

		//downloadImagesThreadPool.submit(playCardFrontSidePlaySectionImage, "http://si.wsj.net/public/resources/images/NA-BU548_NFL_G_20130111183225.jpg");
		playCardFrontSidePlaySectionImage.setId(index * 90000);
		final RelativeLayout playCardFrontSidePlaySectionLayout = (RelativeLayout) playCardFrontSide
				.findViewById(R.id.playCardFrontSidePlaySectionlayoutId);
		playCardFrontSidePlaySectionLayout
				.addView(playCardFrontSidePlaySectionImageWithPlayButtonLayout);

		//playCardLayout.addView(playCardFrontSide);
		playCardLayout.addView(playCardFrontSide);

		// play card back side details ****************

		final RelativeLayout playCardBackSide = (RelativeLayout) inflator
				.inflate(R.layout.play_card_back_side, null);

		ImageView playCardBackSideDoneButton = (ImageView) playCardBackSide
				.findViewById(R.id.playCardBackSideTopLayoutDoneButtonId);

		TextView playCardBackSideTopLayoutTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideTopLayoutTextViewId);
		playCardBackSideTopLayoutTextView.setText(playCardTopDetail);

		TextView playCardBackSideStartAndEndLayoutStartText = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideStartAndEndLayoutStartText);
		playCardBackSideStartAndEndLayoutStartText
				.setText("START 2ND & 10,NE 45");

		TextView playCardBackSideStartAndEndLayoutEndText = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideStartAndEndLayoutEndTextId);
		playCardBackSideStartAndEndLayoutEndText
				.setText("END 1ST & GOAL,GB 10");

		TextView playCardBackSidePlayerCardsLayoutCurrentPlayTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutCurrentPlayTextViewId);
		playCardBackSidePlayerCardsLayoutCurrentPlayTextView
				.setText("IN THIS PLAY");

		TextView playCardBackSidePlayerCardsLayoutMorePlayTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutMorePlayTextViewId);
		playCardBackSidePlayerCardsLayoutMorePlayTextView.setText("MORE PLAYS");

		TextView playCardBackSideMorePlaysThisDriveTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideMorePlaysThisDriveTextView);
		playCardBackSideMorePlaysThisDriveTextView.setText("THIS DRIVE");

		TextView playCardBackSideMorePlaysLongPassesTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideMorePlaysLongPassesTextViewId);
		playCardBackSideMorePlaysLongPassesTextView.setText("LONG PASSES");

		TextView playCardBackSideMorePlays1stDownTextView = (TextView) playCardBackSide
				.findViewById(R.id.playCardBackSideMorePlays1stDownTextViewId);
		playCardBackSideMorePlays1stDownTextView.setText("1ST DOWN");

		LinearLayout playCardBackSidePlayerCardLayout = (LinearLayout) playCardBackSide
				.findViewById(R.id.playCardBackSidePlayerCardsLayoutHorizontalScrollViewLinearLayoutId);
		
		final HorizontalScrollView playCardsHorizontalScrollView = (HorizontalScrollView) activity.findViewById(R.id.horizontalScrollViewForVideosId);
		final HorizontalScrollView playerCardsInsidePlayCardHorizontalScrollView = (HorizontalScrollView) playCardBackSide.findViewById(R.id.playCardBackSidePlayerCardsLayoutHorizontalScrollViewId);
		
		RatingBar playCardFrontSideTopLayoutRatingBar = (RatingBar) playCardFrontSide.findViewById(R.id.playCardFrontSideTopLayoutRatingBarId);
		
		
		playCardsHorizontalScrollView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                Log.v("playCardView","PARENT TOUCH");
                playerCardsInsidePlayCardHorizontalScrollView.requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
		playerCardsInsidePlayCardHorizontalScrollView.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event)
            {
                 Log.v("playCardView","CHILD TOUCH");
                 //  Disallow the touch request for parent scroll on touch of child view
                 playCardsHorizontalScrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
		

		int playerPic[] = { R.drawable.tom_brady, R.drawable.stevan_ridley,
				R.drawable.wes_welker, R.drawable.rob_gronkowski,
				R.drawable.brad_jones };
		String playerName[] = { "Tom Brady", "Stevan Ridley", "Wes Welker",
				"Rob Gronkowski", "Brad Jones" };
		String playerPosition[] = { "#12 | QB", "#22 | RB 7REC-",
				"#83 | WR 7REC-", "#87 | TR", "#59 | LB" };
		String playerDetail[] = { "YDS , 2TD , 1 INT", "83 YDS 0 TD",
				"YDS , 2TD , 1 INT", "83 YDS 0 TD", "YDS , 2TD , 1 INT" };

		for (int i = 0; i < 5; i++) {
			RelativeLayout playCardBackSidePlayerCards = (RelativeLayout) inflator
					.inflate(R.layout.play_card_back_side_player_card, null);

			ImageView playCardBackSidePlayerCardLayoutPlayerImageView = (ImageView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayerImageViewId);
			playCardBackSidePlayerCardLayoutPlayerImageView
					.setImageResource(playerPic[i]);

			TextView playCardBackSidePlayerCardLayoutPlayerName = (TextView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayerNameId);
			playCardBackSidePlayerCardLayoutPlayerName.setText(playerName[i]);

			TextView playCardBackSidePlayerCardLayoutPlayerInfo = (TextView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayerInfo);
			playCardBackSidePlayerCardLayoutPlayerInfo
					.setText(playerPosition[i]);

			TextView playCardBackSidePlayerCardLayoutPlayerPlayDetail = (TextView) playCardBackSidePlayerCards
					.findViewById(R.id.playCardBackSidePlayerCardLayoutPlayerPlayDetail);
			playCardBackSidePlayerCardLayoutPlayerPlayDetail
					.setText(playerDetail[i]);

			playCardBackSidePlayerCardLayout
					.addView(playCardBackSidePlayerCards);
		}
	
		

		playCardFrontSideTopLayoutInfoButton
				.setOnTouchListener(new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						Animation animation = AnimationUtils.loadAnimation(context, R.anim.alpha);
						playCardLayout.removeAllViews();
						playCardLayout.addView(playCardBackSide);
						playCardLayout.startAnimation(animation);
						

						//AnimationFactory.flipTransition(playCardViewAnimator, FlipDirection.LEFT_RIGHT,playCardLayout,playCardBackSide,playCardFrontSide);
						return false;
					}
				});

		playCardBackSideDoneButton.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Animation animation = AnimationUtils.loadAnimation(context,
						R.anim.alpha);
				playCardLayout.removeAllViews();
				playCardLayout.addView(playCardFrontSide);
				playCardLayout.startAnimation(animation);
				
				//AnimationFactory.flipTransition(playCardViewAnimator, FlipDirection.RIGHT_LEFT,playCardLayout,playCardFrontSide,playCardBackSide);
				
				return false;
			}
		});
		

		playCardFrontSidePlaySectionPlayButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						playCardFrontSidePlaySectionLayout
								.removeView(playCardFrontSidePlaySectionImageWithPlayButtonLayout);
						
						
						playCardFrontSidePlaySectionVideoViewLayout = getPlayCardFrontSidePlaySectionVideoLayout();

					
						playCardFrontSidePlaySectionLayout.addView(playCardFrontSidePlaySectionVideoViewLayout);
						videoSizeButton.setVisibility(View.VISIBLE);
						

					}
				});

		playCardFrontSideTopLayoutRatingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				//new AlertDialog.Builder(context).setMessage("\t\t\t\t\t\t\t\t You Rated : " + rating).setPositiveButton("OK", null).setTitle("\t\t\t\t\t\t\t Thank You For Rating").show();
				showDialog("You Rated : " + rating);
				
			}
		});
		
		videoSizeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DisplayMetrics dm = new DisplayMetrics();
				activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
				//RelativeLayout.LayoutParams playCardFrontSidePlaySectionLayoutParams = new RelativeLayout.LayoutParams( dm.widthPixels,dm.heightPixels);
				RelativeLayout relativeLayout = (RelativeLayout) activity.findViewById(R.id.playCardFrontSidePlaySectionVideoLayoutId);
				
				RelativeLayout gameRootView = (RelativeLayout) activity.findViewById(R.id.gameRootView);
				videoView.pause();
				relativeLayout.removeView(videoView);
				RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
						dm.widthPixels, dm.heightPixels);
				Log.d("Width ::: ", "" + dm.widthPixels);
				playCardLayoutParameters.setMargins(0, 0, 0, 0);
				playCardLayoutParameters.leftMargin = 0;
				videoView.setLayoutParams(playCardLayoutParameters);
				
				gameRootView.addView(videoView);
				videoView.resume();
				//videoView = new VideoView(context); //playCardFrontSidePlaySectionVideoViewLayout.findViewById(R.id.playCardFrontSidePlaySectionVideoLayoutVideoViewId);
				//videoView.getParent().
				
				
				
				//params.width = dm.widthPixels;
				
				//params.height = dm.heightPixels;
				
				
				
				//Log.d("height ::: ", "" + dm.heightPixels);
				
				//if(videoView!=null){
					
				
				//}
				//Log.d("","")
				//RelativeLayout parent = (RelativeLayout)playCardFrontSidePlaySectionLayout.getParent();
				//parent.removeView(playCardFrontSidePlaySectionLayout);
				//playCardFrontSidePlaySectionLayout.setLayoutParams(playCardFrontSidePlaySectionLayoutParams);
				//view.addView(playCardFrontSidePlaySectionLayout);
				
				
				
			}
		});
		
		return playCardLayout;
	}
	
	

	protected RelativeLayout getPlayCardFrontSidePlaySectionVideoLayout() {
		final LayoutInflater inflator = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout playCardFrontSidePlaySectionVideoLayout = (RelativeLayout) inflator
				.inflate(R.layout.play_card_front_side_play_section_video, null);
		videoView = new VideoView(context);//(VideoView) playCardFrontSidePlaySectionVideoLayout
				//.findViewById(R.id.playCardFrontSidePlaySectionVideoLayoutVideoViewId);
		//playCardFrontSidePlaySectionVideoView.setId(index * 390000);
		MediaController mc = new MyMediaController(
				videoView.getContext());

		mc.setMediaPlayer(videoView);
		videoView.setMediaController(mc);
		RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
				500, 400);
		
		
		videoView.setLayoutParams(playCardLayoutParameters);
		playCardFrontSidePlaySectionVideoLayout.addView(videoView);
		videoView.setVideoURI(Uri
				.parse("http://commonsware.com/misc/test2.3gp"));

		// playCardFrontSidePlaySectionVideoView.setVideoURI(Uri.parse("http://localhost:8080/nflvideo.3gp"));
		videoView.start();

		return playCardFrontSidePlaySectionVideoLayout;
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

	public class MyMediaController extends MediaController {
		public MyMediaController(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public MyMediaController(Context context, boolean useFastForward) {
			super(context, useFastForward);
		}

		public MyMediaController(Context context) {
			super(context);
		}

		@Override
		public void show(int timeout) {
			super.show(0);
		}

	}
	
	private void showDialog(String rating)
	{
	    final Custom_Dialog dialog = new Custom_Dialog(activity, R.style.myCoolDialog);

	    dialog.setContentView(R.layout.custom_dialog);
	    dialog.setTitle("  Thanks For Your Rating");

	    TextView text = (TextView) dialog.findViewById(R.id.customDialogTextViewId);
	    text.setText(rating );
	    
	    ImageView okButton = (ImageView) dialog.findViewById(R.id.customDialogOkButtonId);
	    okButton.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				dialog.hide();
				return false;
			}
		});
	    dialog.show();  
	}
	// playCardParentLinearLayout.addView(playCardLayout);

	// Commenting this part today 2/5/13

	/*
	 * // *********** Adding top and bottom strip *****************
	 * 
	 * RelativeLayout playCardTopLayout = new RelativeLayout(context);
	 * RelativeLayout.LayoutParams playCardTopLayoutParameter = new
	 * RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
	 * 50); playCardTopLayout.setLayoutParams(playCardTopLayoutParameter); //
	 * playCardTopLayout
	 * .setBackgroundColor(getResources().getColor(R.color.white));
	 * playCardLayout.addView(playCardTopLayout);
	 * 
	 * // Creating a new topTextView TextView playCardTopTextView = new
	 * TextView(this);
	 * playCardTopTextView.setText(playCardTopDetail.get(index));
	 * 
	 * // Defining the layout parameters of the TextView
	 * RelativeLayout.LayoutParams layoutParametersForPlayCardTopTextView = new
	 * RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT,
	 * RelativeLayout.LayoutParams.WRAP_CONTENT);
	 * layoutParametersForPlayCardTopTextView
	 * .addRule(RelativeLayout.ALIGN_PARENT_LEFT);
	 * layoutParametersForPlayCardTopTextView
	 * .addRule(RelativeLayout.CENTER_VERTICAL);
	 * 
	 * // Setting the parameters on the TextView playCardTopTextView
	 * .setLayoutParams(layoutParametersForPlayCardTopTextView);
	 * 
	 * // Adding the TextView to the RelativeLayout as a child
	 * playCardTopLayout.addView(playCardTopTextView);
	 * 
	 * RatingBar playCardRatingBar = new RatingBar(context);
	 * RelativeLayout.LayoutParams ratingBarLayoutParameter = new
	 * RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT,
	 * RelativeLayout.LayoutParams.WRAP_CONTENT); ratingBarLayoutParameter
	 * .addRule(RelativeLayout.ALIGN_PARENT_RIGHT); ratingBarLayoutParameter
	 * .addRule(RelativeLayout.ALIGN_PARENT_TOP);
	 * ratingBarLayoutParameter.setMargins(0, 0, 30, 0);
	 * 
	 * playCardRatingBar.setMax(5); playCardRatingBar.setRating(3);
	 * playCardRatingBar.setNumStars(5);
	 * playCardRatingBar.setLayoutParams(ratingBarLayoutParameter);
	 * playCardTopLayout.addView(playCardRatingBar);
	 * 
	 * ImageView playCardInfoButton = new ImageView(context);
	 * RelativeLayout.LayoutParams playCardInfoButtonLayoutParams = new
	 * RelativeLayout .LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
	 * ,RelativeLayout.LayoutParams.WRAP_CONTENT);
	 * playCardInfoButtonLayoutParams .addRule(RelativeLayout.ALIGN_PARENT_TOP);
	 * playCardInfoButtonLayoutParams
	 * .addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	 * playCardInfoButton.setImageResource (R.drawable.play_card_info_button);
	 * playCardInfoButton.setLayoutParams (playCardInfoButtonLayoutParams);
	 * playCardTopLayout.addView(playCardInfoButton);
	 * 
	 * playCardInfoButton.setOnTouchListener(new View.OnTouchListener() {
	 * 
	 * @Override public boolean onTouch(View v, MotionEvent event) { Animation
	 * animation = AnimationUtils.loadAnimation(getApplicationContext(),
	 * R.anim.alpha); playCardLayout.startAnimation(animation);
	 * 
	 * if (playCardLayout.getChildCount() > 0) {
	 * playCardLayout.removeAllViewsInLayout(); playCardLayout.invalidate(); }
	 * // playCardLayout.removeAllViewsInLayout();
	 * playCardLayout.bringToFront(); final RelativeLayout
	 * playCardBackSideLayout = (RelativeLayout)
	 * findViewById(R.id.playCardBackSideId);
	 * 
	 * Button playCardBackSideDoneButton = (Button)
	 * findViewById(R.id.playCardBackSideTopLayoutDoneButtonId);
	 * playCardBackSideDoneButton.setOnClickListener(new View.OnClickListener()
	 * {
	 * 
	 * @Override public void onClick(View v) {
	 * playCardLayout.removeViewInLayout(playCardBackSideLayout);
	 * 
	 * } });
	 * 
	 * 
	 * TextView playCardBackSideTopLayoutTextView = (TextView)
	 * findViewById(R.id.playCardBackSideTopLayoutTextViewId);
	 * playCardBackSideTopLayoutTextView .setText("45-Yard Pass , 1st Down");
	 * 
	 * TextView playCardBackSideStartAndEndLayoutStartText = (TextView)
	 * findViewById(R.id.playCardBackSideStartAndEndLayoutStartText);
	 * playCardBackSideStartAndEndLayoutStartText
	 * .setText("Start 2ND & 10, NE 45");
	 * 
	 * TextView playCardBackSideStartAndEndLayoutEndText = (TextView)
	 * findViewById(R.id.playCardBackSideStartAndEndLayoutEndTextId);
	 * playCardBackSideStartAndEndLayoutEndText
	 * .setText("END 1st & Goal, GB 10");
	 * 
	 * TextView playCardBackSidePlayerCardsLayoutCurrentPlayTextView =
	 * (TextView) findViewById(R.id.
	 * playCardBackSidePlayerCardsLayoutCurrentPlayTextViewId);
	 * playCardBackSidePlayerCardsLayoutCurrentPlayTextView
	 * .setText("In This Play");
	 * 
	 * TextView playCardBackSidePlayerCardsLayoutMorePlayTextView = (TextView)
	 * findViewById(R.id.playCardBackSidePlayerCardsLayoutMorePlayTextViewId );
	 * playCardBackSidePlayerCardsLayoutMorePlayTextView.setText( "More Plays");
	 * 
	 * TextView playCardBackSideMorePlaysThisDriveTextView =(TextView)
	 * findViewById(R.id.playCardBackSideMorePlaysThisDriveTextView);
	 * playCardBackSideMorePlaysThisDriveTextView.setText("This Drive");
	 * 
	 * TextView playCardBackSideMorePlaysLongPassesTextView =
	 * (TextView)findViewById
	 * (R.id.playCardBackSideMorePlaysLongPassesTextViewId);
	 * playCardBackSideMorePlaysLongPassesTextView .setText("Long Passes");
	 * 
	 * TextView playCardBackSideMorePlays1stDownTextView =
	 * (TextView)findViewById (R.id.playCardBackSideMorePlays1stDownTextViewId);
	 * playCardBackSideMorePlays1stDownTextView.setText("1st Down");
	 * 
	 * playCardLayout.addView(playCardBackSideLayout); return false; } });
	 * 
	 * //****************************************
	 * 
	 * //**** play card play-section design ****
	 * 
	 * final RelativeLayout playCardPlaySectionLayout = new
	 * RelativeLayout(context); RelativeLayout.LayoutParams
	 * playCardPlaySectionLayoutParams = new RelativeLayout.LayoutParams(
	 * RelativeLayout.LayoutParams.MATCH_PARENT,250);
	 * playCardPlaySectionLayoutParams
	 * .addRule(RelativeLayout.CENTER_IN_PARENT); playCardPlaySectionLayout
	 * .setLayoutParams(playCardPlaySectionLayoutParams);
	 * playCardPlaySectionLayout
	 * .setBackgroundColor(getResources().getColor(R.color.black));
	 * playCardLayout.addView(playCardPlaySectionLayout);
	 * 
	 * 
	 * 
	 * 
	 * final ImageView playCardImageView = new ImageView(this);
	 * RelativeLayout.LayoutParams layoutParameterForPlayCardImageView = new
	 * RelativeLayout.LayoutParams( 450, 250);
	 * layoutParameterForPlayCardImageView
	 * .addRule(RelativeLayout.CENTER_IN_PARENT); //
	 * playCardImageView.setVisibility(View.VISIBLE); playCardImageView
	 * .setLayoutParams(layoutParameterForPlayCardImageView);
	 * 
	 * // playCardImageView.setImageResource(R.drawable.nflimagefour); //
	 * playCardImageView.setImageURI(Uri.parse(
	 * "http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"
	 * )); // playCardImageView.setImageBitmap(loadBitmap(
	 * "http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"
	 * ));
	 * 
	 * 
	 * NetworkOnMainThreadException is handled using these two lines for Android
	 * 3.0 and above.
	 * 
	 * // Start handling NetworkOnMainThreadException StrictMode.ThreadPolicy
	 * policy = new StrictMode.ThreadPolicy.Builder() .permitAll().build();
	 * StrictMode.setThreadPolicy(policy); // End Handling
	 * NetworkOnMainThreadException // downloadImageTask = new //
	 * DownloadImageTask(playCardImageView);//.execute(
	 * "http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"
	 * );
	 * 
	 * // downloadImageTask.execute(
	 * "http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"
	 * );
	 * 
	 * // downloadImagesThreadPool.submit(playCardImageView, //
	 * "http://x.pio.lc/nfl/week05/20121009_001_20121011114555_007_001_96ce5227.jpg"
	 * );
	 * 
	 * new DownloadImageTask(playCardImageView).execute(
	 * "http://si.wsj.net/public/resources/images/NA-BU548_NFL_G_20130111183225.jpg"
	 * );
	 * 
	 * playCardPlaySectionLayout.addView(playCardImageView);
	 * 
	 * playCardImageView.setOnTouchListener(new View.OnTouchListener() {
	 * 
	 * @Override public boolean onTouch(final View v, MotionEvent arg1) { //
	 * Creating a new VideoView VideoView
	 * 
	 * if (playCardVideoView != null && playCardVideoView.isPlaying()) {
	 * playCardVideoView.stopPlayback();
	 * playCardVideoView.setVisibility(View.INVISIBLE); //
	 * v.setVisibility(View.VISIBLE); } playCardVideoView = new
	 * VideoView(context); playCardPlaySectionLayout.addView(playCardVideoView);
	 * // Defining the layout parameters of the VideoView
	 * RelativeLayout.LayoutParams layoutParametersForPlayCardVideoView = new
	 * RelativeLayout.LayoutParams( 450, 250);
	 * layoutParametersForPlayCardVideoView
	 * .addRule(RelativeLayout.CENTER_IN_PARENT); // RelativeLayout.LayoutParams
	 * // layoutParametersForPlayCardVideoView = new //
	 * RelativeLayout.LayoutParams (WindowManager.LayoutParams.FLAG_FULLSCREEN
	 * ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 * playCardVideoView.setId(playCardVideoId++);
	 * 
	 * MediaController mediaController = new MediaController( context);
	 * mediaController.setAnchorView(playCardVideoView);
	 * playCardVideoView.setMediaController(mediaController);
	 * 
	 * playCardVideoView .setLayoutParams(layoutParametersForPlayCardVideoView);
	 * 
	 * //String path =
	 * "http://x.pio.lc/nfl/week05/20121009_001_20121011115406_027_3_241b_d02a361b.3gp"
	 * ; String path = "http://commonsware.com/misc/test2.3gp";
	 * 
	 * playCardVideoView.setVideoURI(Uri.parse(path));
	 * playCardVideoView.setZOrderOnTop(false); v.setVisibility(View.INVISIBLE);
	 * playCardVideoView .setOnCompletionListener(new
	 * MediaPlayer.OnCompletionListener() {
	 * 
	 * @Override public void onCompletion(MediaPlayer vmp) { //
	 * playCardImageView.setVisibility(View.VISIBLE); playCardVideoView
	 * .setVisibility(View.INVISIBLE); v.setVisibility(View.VISIBLE);
	 * 
	 * } });
	 * 
	 * playCardVideoView.start();
	 * 
	 * return false; } });
	 * 
	 * ImageView playCardPlaySectionPlayButton = new ImageView(context);
	 * RelativeLayout.LayoutParams playCardPlaySectionPlayButtonParams = new
	 * RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
	 * ,RelativeLayout.LayoutParams.WRAP_CONTENT);
	 * playCardPlaySectionPlayButtonParams
	 * .addRule(RelativeLayout.CENTER_IN_PARENT); playCardPlaySectionPlayButton
	 * .setLayoutParams(playCardPlaySectionPlayButtonParams);
	 * playCardPlaySectionPlayButton.setAlpha(0.5f);
	 * playCardPlaySectionPlayButton
	 * .setImageResource(R.drawable.click_to_play_button);
	 * playCardPlaySectionLayout.addView(playCardPlaySectionPlayButton);
	 * 
	 * 
	 * RelativeLayout playCardBottomLayout = new RelativeLayout(context);
	 * RelativeLayout.LayoutParams playCardButtomLayoutParameter = new
	 * RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT,
	 * 50); playCardButtomLayoutParameter
	 * .addRule(RelativeLayout.ALIGN_PARENT_BOTTOM); playCardBottomLayout
	 * .setLayoutParams(playCardButtomLayoutParameter);
	 * playCardLayout.addView(playCardBottomLayout);
	 * 
	 * // Creating a new bottomTextView TextView playCardBottomTextView = new
	 * TextView(context);
	 * playCardBottomTextView.setText(playCardBottomDetail.get(index)); //
	 * Defining the layout parameters of the TextView
	 * RelativeLayout.LayoutParams layoutParametersForPlayCardBottomTextView =
	 * new RelativeLayout.LayoutParams(
	 * RelativeLayout.LayoutParams.WRAP_CONTENT,
	 * RelativeLayout.LayoutParams.WRAP_CONTENT);
	 * layoutParametersForPlayCardBottomTextView
	 * .addRule(RelativeLayout.CENTER_VERTICAL);
	 * 
	 * // Setting the parameters on the TextView playCardBottomTextView
	 * .setLayoutParams(layoutParametersForPlayCardBottomTextView); // Adding
	 * the TextView to the RelativeLayout as a child
	 * playCardBottomLayout.addView(playCardBottomTextView);
	 * 
	 * playCardParentLinearLayout.addView(playCardLayout);
	 * 
	 * downloadImageTask = null;
	 */
}
