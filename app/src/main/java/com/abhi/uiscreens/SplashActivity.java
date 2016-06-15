package com.abhi.uiscreens;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.abhi.R;
/**
 * Created by Abhishek on 10-09-2015.
 */
public class SplashActivity extends Activity {

	
	private int SPLASH_SCREEN_TIME_OUT = 2000;
	private Handler mHandler = null;
	private Runnable mRunnable = null;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
	
		

		mHandler = new Handler();
		mRunnable = new Runnable() {

			@Override
			public void run() {

				doDisplayNextScreen();
			}
		};
		mHandler.postDelayed(mRunnable, SPLASH_SCREEN_TIME_OUT);
	}

	private void doDisplayNextScreen() {

		Intent lIntent = new Intent();
		lIntent.setClass(this, ImageListActivity.class);
		startActivity(lIntent);
		finish();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		// Cancel the Handler object when back button is clicked .
		mHandler.removeCallbacks(mRunnable);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// Cancel the Handler object when activity is destroyed(orientation
		// changes) .
		mHandler.removeCallbacks(mRunnable);
	}

}
