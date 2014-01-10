package com.jogeeks.social;

import android.app.Activity;
import android.content.Intent;

public class Share {

	private Activity activity;
	
	public Share(Activity a){
		activity = a;
	}
	
	/**
	 * Pass a string to be share through share Intent.
	 *
	 * @param msg The text you want to share.
	 */
	public  void shareText(String msg){
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
		sendIntent.setType("text/plain");
		activity.startActivity(sendIntent);
	}
}
