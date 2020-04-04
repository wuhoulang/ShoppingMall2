package com.atguigu.shoppingmall.utils;

/**
 * @version 1.0  
 * @author XiaoXun  
 * @date 2020年3月24日 下午4:51:13
 * @Description: TODO 
 */
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class ScreenUtils {
	public static boolean isVerticalScreen(Activity activity) {
		// return (activity =
		// activity.getResources().getConfiguration()).orientation == 1;
		return activity.getResources().getConfiguration().orientation == 1;
	}

	public static boolean isHorizontalScreen(Activity activity) {
		// return (activity =
		// activity.getResources().getConfiguration()).orientation == 2;
		return activity.getResources().getConfiguration().orientation == 2;
	}

	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId;
		if ((resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public static int getScreenWidth(Context context) {
		int screenWith = -1;
		try {
			screenWith = context.getResources().getDisplayMetrics().widthPixels;
		} catch (Exception localException) {
			// (context = localException).printStackTrace();
			localException.printStackTrace();
		}
		return screenWith;
	}

	public static int getScreenHeight(Context context) {
		int screenHeight = -1;
		try {
			screenHeight = context.getResources().getDisplayMetrics().heightPixels;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return screenHeight;
	}
}
