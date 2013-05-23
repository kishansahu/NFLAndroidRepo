package com.liveclips.nfl.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NflUtils {
	
	public static boolean isScoreBannerShrinked; 
		
	public static boolean isScoreBannerShrinked() {
		return isScoreBannerShrinked;
	}

	public static void setScoreBannerShrinked(boolean isScoreBannerShrinked) {
		NflUtils.isScoreBannerShrinked = isScoreBannerShrinked;
	}

	public static int convertDensityPixelToPixel(Context context, int i) {
		return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5);
	}

	public static int convertPixelToDensityPixel(Context context, int px) {
		return (int) ((px / context.getResources().getDisplayMetrics().density) + 0.5);
	}

	/**
	 * Method calculates the width of the bar which is used to indicate the
	 * YardsBarWidth.
	 */
	public static Integer getYardsBarWidth(String score, String highestScore) {

		Float barWidthNum = 1 + (425 / Float.parseFloat(highestScore))
				* (Float.parseFloat(score));
		Integer barWidth = Math.round(barWidthNum);
		return barWidth;
	}

	public static String getHighestNumber(String score1, String score2) {
		Float scoreFirst = Float.parseFloat(score1);
		Float scoreSecond = Float.parseFloat(score2);
		Float highestScore;
		if (scoreFirst >= scoreSecond) {
			highestScore = scoreFirst;
		} else {
			highestScore = scoreSecond;
		}
		return highestScore.toString();
	}
	
	public static Bitmap getBitmapFromURL(String src) {
	    try {
	        URL url = new URL(src);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setDoInput(true);
	        connection.connect();
	        InputStream input = connection.getInputStream();
	        Bitmap myBitmap = BitmapFactory.decodeStream(input);
	        return myBitmap;
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


}
