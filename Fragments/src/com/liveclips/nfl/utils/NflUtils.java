package com.liveclips.nfl.utils;

import android.content.Context;

public class NflUtils {
	
	public static boolean isScoreBannerShrinked = false; 
	public static String stubActivatedName ="";
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
}
