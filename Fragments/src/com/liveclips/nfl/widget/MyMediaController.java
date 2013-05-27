/**
 * 
 */
package com.liveclips.nfl.widget;

import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.VideoView;

import com.liveclips.nfl.R;

/**
 * @author abhijitsrivastava
 * 
 */
public class MyMediaController extends MediaController {

	private ImageButton screenSizeButton;
	private ImageButton pauseButton;
	private ImageButton fastForwardButton;
	private ImageButton rewindButton;
	private ImageButton previousButton;
	private ImageButton nextButton;

	private ProgressBar progressBar;
	private TextView currentTime, endTime;

	private View customMediaController;
	private VideoView videoView;

	private int length = 0;
	private int duration = 0;
	private static final int FADE_OUT = 1;
	private static final int SHOW_PROGRESS = 2;
	private static final int sDefaultTimeout = 3000;

	StringBuilder mFormatBuilder;
	Formatter mFormatter;

	private boolean fullScreen = false;
	private boolean progressBarDragging;
	private boolean mShowing;

	private Handler mHandler = new MessageHandler(this);

	Activity activity;
	Context context;

	LayoutInflater inflate;

	/**
	 * 
	 * @param context
	 * @param attrs
	 */
	public MyMediaController(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	/**
	 * 
	 * @param context
	 * @param useFastForward
	 */
	public MyMediaController(Context context, boolean useFastForward) {
		super(context, useFastForward);
		this.context = context;
	}

	/**
	 * 
	 * @param context
	 */
	public MyMediaController(Context context) {
		super(context);
		this.context = context;
	}

	/**
	 * Custom media controller for nfl
	 * 
	 * @param context
	 * @param activity
	 * @param videoView
	 */
	public MyMediaController(Context context, Activity activity,
			VideoView videoView) {
		super(context);
		this.context = context;
		this.activity = activity;
		this.videoView = videoView;
		inflate = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// super.setAnchorView(videoView);

		//makeControllerView();
	}

	/*
	 * @Override public void setAnchorView(VideoView vView){
	 * super.setAnchorView(vView); //RelativeLayout
	 * playCardPlaySectionParentLayout = (RelativeLayout)
	 * inflate.inflate(R.layout.play_card_front_side_play_section_video,null);
	 * View v = makeControllerView(); //vView.addView(v); }
	 */



	protected View makeControllerView() {
		
		customMediaController = inflate
				.inflate(R.layout.media_controller, null);
		// disableUnsupportedButton(customMediaController);
		//initControllerView(customMediaController);
		return customMediaController;
	}
	
	@Override
	public void onFinishInflate() {
		if (customMediaController != null)
			initControllerView(customMediaController);
	}

	/**
	 * set the events on nfl media contoller
	 * 
	 * @param customMediaController
	 */
	private void initControllerView(View customMediaController) {

		// full screen button event
		screenSizeButton = (ImageButton) customMediaController
				.findViewById(R.id.screenSize);
		if (screenSizeButton != null) {
			screenSizeButton.requestFocus();
			screenSizeButton.setOnClickListener(mFullscreenListener);
		}

		// pause button

		pauseButton = (ImageButton) customMediaController
				.findViewById(R.id.pause);
		if (pauseButton != null) {
			pauseButton.requestFocus();
			pauseButton.setOnClickListener(mPlayPauseListener);
		}

		// ffwrd
		fastForwardButton = (ImageButton) customMediaController
				.findViewById(R.id.ffwd);
		if (fastForwardButton != null) {
			fastForwardButton.setOnClickListener(fastForwardListener);
		}

		rewindButton = (ImageButton) customMediaController
				.findViewById(R.id.rew);
		if (rewindButton != null) {
			rewindButton.setOnClickListener(rewindButtonListener);
		}

		previousButton = (ImageButton) customMediaController
				.findViewById(R.id.prev);
		if (previousButton != null) {
			previousButton.setVisibility(View.GONE);
		}

		nextButton = (ImageButton) customMediaController
				.findViewById(R.id.next);
		if (nextButton != null) {
			nextButton.setVisibility(View.GONE);
		}

		progressBar = (ProgressBar) customMediaController
				.findViewById(R.id.mediacontroller_progress);
		if (progressBar != null) {
			if (progressBar instanceof SeekBar) {
				SeekBar seeker = (SeekBar) progressBar;
				seeker.setOnSeekBarChangeListener(seekBarChangeListener);
			}
			progressBar.setMax(1000);
		}

		currentTime = (TextView) customMediaController
				.findViewById(R.id.time_current);
		endTime = (TextView) customMediaController.findViewById(R.id.time);

		mFormatBuilder = new StringBuilder();
		mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

		Log.d("End Time", " " + stringForTime(duration));

	}

	public void show() {
		show(sDefaultTimeout);
	}

	@Override
	public void show(int timeout) {
		setProgress();
		if (pauseButton != null) {
			pauseButton.requestFocus();
			mShowing = true;
		}
		// disableUnsupportedButton(customMediaController);
		updatePausePlay();

		// cause the progress bar to be updated even if mShowing
		// was already true. This happens, for example, if we're
		// paused with the progress bar showing the user hits play.
		mHandler.sendEmptyMessage(SHOW_PROGRESS);

		Message msg = mHandler.obtainMessage(FADE_OUT);
		if (timeout != 0) {
			mHandler.removeMessages(FADE_OUT);
			mHandler.sendMessageDelayed(msg, timeout);
		}
	}

	public void hide() {
		if (videoView == null)
			return;

		if (mShowing) {
			try {
				mHandler.removeMessages(SHOW_PROGRESS);
				// mWindowManager.removeView(mDecor);
			} catch (IllegalArgumentException ex) {
				Log.w("MediaController", "already removed");
			}
			mShowing = false;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		show(sDefaultTimeout);
		return true;
	}

	private OnSeekBarChangeListener seekBarChangeListener = new OnSeekBarChangeListener() {
		public void onStartTrackingTouch(SeekBar bar) {
			show(3600000);

			progressBarDragging = true;

			// By removing these pending progress messages we make sure
			// that a) we won't update the progress while the user adjusts
			// the seekbar and b) once the user is done dragging the thumb
			// we will post one of these messages to the queue again and
			// this ensures that there will be exactly one message queued up.
			mHandler.removeMessages(SHOW_PROGRESS);
		}

		public void onProgressChanged(SeekBar bar, int progress,
				boolean fromuser) {
			if (!fromuser) {
				// We're not interested in programmatically generated changes to
				// the progress bar's position.
				return;
			}

			long duration = videoView.getDuration();
			long newposition = (duration * progress) / 1000L;
			videoView.seekTo((int) newposition);
			if (currentTime != null)
				currentTime.setText(stringForTime((int) newposition));
		}

		public void onStopTrackingTouch(SeekBar bar) {
			progressBarDragging = false;
			setProgress();
			updatePausePlay();
			show(sDefaultTimeout);

			// Ensure that progress is properly updated in the future,
			// the call to show() does not guarantee this because it is a
			// no-op if we are already showing.
			mHandler.sendEmptyMessage(SHOW_PROGRESS);
		}
	};

	/*
	 * private OnSeekBarChangeListener seekBarChangeListener = new
	 * OnSeekBarChangeListener() {
	 * 
	 * @Override public void onStopTrackingTouch(SeekBar arg0) { // TODO
	 * Auto-generated method stub
	 * 
	 * progressBarDragging = false; setProgress(); // updatePausePlay(); //
	 * show(sDefaultTimeout); videoView.start();
	 * 
	 * // Ensure that progress is properly updated in the future, // the call to
	 * show() does not guarantee this because it is a // no-op if we are already
	 * showing. mHandler.sendEmptyMessage(SHOW_PROGRESS);
	 * 
	 * }
	 * 
	 * @Override public void onStartTrackingTouch(SeekBar arg0) { // TODO
	 * Auto-generated method stub Log.d("OnStartTracking", "start");
	 * show(3600000); progressBarDragging = true;
	 * 
	 * videoView.pause(); // By removing these pending progress messages we make
	 * sure // that a) we won't update the progress while the user adjusts //
	 * the seekbar and b) once the user is done dragging the thumb // we will
	 * post one of these messages to the queue again and // this ensures that
	 * there will be exactly one message queued up.
	 * mHandler.removeMessages(SHOW_PROGRESS);
	 * 
	 * }
	 * 
	 * @Override public void onProgressChanged(SeekBar bar, int progress,
	 * boolean fromuser) { // TODO Auto-generated method stub long newposition =
	 * (duration * progress) / 1000L; Log.d("newposition", " " + newposition);
	 * videoView.seekTo((int) newposition);
	 * 
	 * if (currentTime != null) currentTime.setText(stringForTime((int)
	 * newposition)); } };
	 */

	private View.OnClickListener rewindButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
			int pos = videoView.getCurrentPosition();
			pos -= 1500;
			videoView.seekTo(pos);
			setProgress();
			videoView.start();
			show(sDefaultTimeout);
		}
	};

	private View.OnClickListener fastForwardListener = new View.OnClickListener() {
		public void onClick(View v) {
			int pos = videoView.getCurrentPosition();
			pos += 1500;
			videoView.seekTo(pos);
			setProgress();
			videoView.start();
			show(sDefaultTimeout);
		}
	};

	private View.OnClickListener mPlayPauseListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			updatePausePlay();
			if (videoView.isPlaying()) {
				length = videoView.getCurrentPosition();
				videoView.pause();
			} else {
				videoView.seekTo(length);
				videoView.start();
			}

		}
	};

	private View.OnClickListener mFullscreenListener = new View.OnClickListener() {
		public void onClick(View v) {
			if (fullScreen) {
				RelativeLayout gameRootView = (RelativeLayout) activity
						.findViewById(R.id.gameRootView);
				videoView.pause();
				gameRootView.removeView(videoView);

				activity.getActionBar().show();
				for (int i = 0; i < gameRootView.getChildCount(); i++) {
					gameRootView.getChildAt(i).setVisibility(View.VISIBLE);
				}

				RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.MATCH_PARENT, 250);
				videoView.setLayoutParams(playCardLayoutParameters);
				RelativeLayout relativeLayout = (RelativeLayout) activity
						.findViewById(R.id.playCardFrontSidePlaySectionVideoLayoutId);
				relativeLayout.addView(videoView);
				videoView.seekTo(length);
				videoView.start();
				fullScreen = false;
			} else {
				DisplayMetrics dm = new DisplayMetrics();
				activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
				RelativeLayout relativeLayout = (RelativeLayout) activity
						.findViewById(R.id.playCardFrontSidePlaySectionVideoLayoutId);

				RelativeLayout gameRootView = (RelativeLayout) activity
						.findViewById(R.id.gameRootView);
				videoView.pause();
				relativeLayout.removeView(videoView);
				activity.getActionBar().hide();
				for (int i = 0; i < gameRootView.getChildCount(); i++) {
					gameRootView.getChildAt(i).setVisibility(View.INVISIBLE);
				}

				RelativeLayout.LayoutParams playCardLayoutParameters = new RelativeLayout.LayoutParams(
						dm.widthPixels, dm.heightPixels);
				playCardLayoutParameters
						.addRule(RelativeLayout.CENTER_IN_PARENT);
				Log.d("Width ::: ", "" + dm.widthPixels);
				// playCardLayoutParameters.setMargins(0, 0, 0, 0);
				playCardLayoutParameters.leftMargin = 0;
				videoView.setLayoutParams(playCardLayoutParameters);

				gameRootView.addView(videoView);
				videoView.seekTo(length);
				videoView.start();
				fullScreen = true;
			}
		}
	};

	private void updatePausePlay() {
		if (customMediaController == null || pauseButton == null) {
			return;
		}

		if (videoView.isPlaying()) {
			pauseButton.setImageResource(R.drawable.media_player_play_button);
		} else {
			pauseButton.setImageResource(R.drawable.media_player_pause_button);
		}
	}

	/*
	 * private void disableUnsupportedButton(View customMediaControllerView) {
	 * previousButton = (ImageButton) customMediaController
	 * .findViewById(R.id.prev); previousButton.setVisibility(View.GONE);
	 * 
	 * nextButton = (ImageButton) customMediaControllerView
	 * .findViewById(R.id.next); nextButton.setVisibility(View.GONE);
	 * 
	 * }
	 */

	private String stringForTime(int timeMs) {
		int totalSeconds = timeMs / 1000;

		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds / 60) % 60;
		int hours = totalSeconds / 3600;

		mFormatBuilder.setLength(0);
		if (hours > 0) {
			return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds)
					.toString();
		} else {
			return mFormatter.format("%02d:%02d", minutes, seconds).toString();
		}
	}

	private int setProgress() {
		if (videoView == null || progressBarDragging) {
			return 0;
		}

		int position = videoView.getCurrentPosition();
		int duration = videoView.getDuration();
		if (progressBar != null) {
			if (duration > 0) {
				// use long to avoid overflow
				long pos = 1000L * position / duration;
				progressBar.setProgress((int) pos);
			}
			int percent = videoView.getBufferPercentage();
			progressBar.setSecondaryProgress(percent * 10);
		}

		if (endTime != null)
			endTime.setText(stringForTime(duration));
		if (currentTime != null)
			currentTime.setText(stringForTime(position));

		return position;
	}

	private static class MessageHandler extends Handler {
		private final WeakReference<MyMediaController> mView;

		MessageHandler(MyMediaController view) {
			mView = new WeakReference<MyMediaController>(view);
		}

		@Override
		public void handleMessage(Message msg) {
			MyMediaController view = mView.get();
			if (view == null || view.videoView == null) {
				return;
			}

			int pos;
			switch (msg.what) {
			case FADE_OUT:
				view.hide();
				break;
			case SHOW_PROGRESS:
				pos = view.setProgress();
				if (!view.progressBarDragging /* && view.Showing */
						&& view.videoView.isPlaying()) {
					msg = obtainMessage(SHOW_PROGRESS);
					sendMessageDelayed(msg, 1000 - (pos % 1000));
				}
				break;
			}
		}
	}

}
