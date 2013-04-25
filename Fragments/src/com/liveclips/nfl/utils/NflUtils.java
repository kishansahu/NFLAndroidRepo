package com.liveclips.nfl.utils;

import android.content.Context;

public class NflUtils {

	public static int convertDensityPixelToPixel(Context context, int i) {
		return (int) ((i * context.getResources().getDisplayMetrics().density) + 0.5);
	}

	public static int convertPixelToDensityPixel(Context context, Double px) {
	return (int) ((px/context.getResources().getDisplayMetrics().density)+0.5);
	}
}
