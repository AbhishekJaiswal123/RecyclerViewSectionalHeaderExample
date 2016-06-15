package com.abhi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 * Created by Abhishek on 10-09-2015.
 */
public class UtilClass {

	public static boolean isNetworkAvailable(Context ctx) {
		ConnectivityManager connectivityManager = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if ((connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
				|| (connectivityManager
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
						.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
						.getState() == NetworkInfo.State.CONNECTED)) {
			return true;
		} else {
			return false;
		}
	}



}
