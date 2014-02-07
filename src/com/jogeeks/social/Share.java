package com.jogeeks.social;

import java.util.List;

import com.jogeeks.libdroid.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.widget.Toast;

public class Share {

	private Activity activity;

	public Share(Activity a) {
		activity = a;
	}

	/**
	 * Pass a string to be share through share Intent.
	 * 
	 * @param msg
	 *            The text you want to share.
	 */
	public void shareText(String text) {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, text);
		sendIntent.setType("text/plain");
		activity.startActivity(sendIntent);
	}

	public void shareLinkFacebook(String link) {
		String urlToShare = link;
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, urlToShare);

		// See if official Facebook app is found
		boolean facebookAppFound = false;
		List<ResolveInfo> matches = activity.getPackageManager().queryIntentActivities(intent, 0);
		for (ResolveInfo info : matches) {
		    if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
		        intent.setPackage(info.activityInfo.packageName);
		        facebookAppFound = true;
		        break;
		    }
		}

		// As fallback, launch sharer.php in a browser
		if (!facebookAppFound) {
		    String sharerUrl = ("https://www.facebook.com/sharer/sharer.php?u=" + urlToShare);
		    intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
		}

		activity.startActivity(intent);
	}
	
	public void shareWhatsApp(String text){
	       Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
	        whatsappIntent.setType("text/plain");
	        whatsappIntent.setPackage("com.whatsapp");
	        whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
	        try {
	            activity.startActivity(whatsappIntent);
	        } catch (android.content.ActivityNotFoundException ex) {
	            Toast.makeText(activity.getApplicationContext(),"Whatsapp has not been installed.", Toast.LENGTH_LONG).show();
	        }
		
	}
	
	
	public void shareGooglePlus(String text){
	       Intent googleplus = new Intent(Intent.ACTION_SEND);
	       googleplus.setType("text/plain");
	       googleplus.setPackage("com.google.android.apps.plus");
	       googleplus.putExtra(Intent.EXTRA_TEXT, text);

	        try {
	            activity.startActivity(googleplus);
	        } catch (android.content.ActivityNotFoundException ex) {
	            Toast.makeText(activity.getApplicationContext(),"Google+ has not been installed.", Toast.LENGTH_LONG).show();
	        }
	}

	public void shareTwitter(String text){
	       Intent twitter = new Intent(Intent.ACTION_SEND);
	       twitter.setType("text/plain");
	       twitter.setPackage("com.twitter.android");
	       twitter.putExtra(Intent.EXTRA_TEXT, text);

	        try {
	            activity.startActivity(twitter);
	        } catch (android.content.ActivityNotFoundException ex) {
	            Toast.makeText(activity.getApplicationContext(),"Twitter has not been installed.", Toast.LENGTH_LONG).show();
	        }
	}
	
	public void shareGmail(String text){
	       Intent gmail = new Intent(Intent.ACTION_SEND);
	       gmail.setType("text/plain");
	       gmail.setPackage("com.google.android.gm");
	       gmail.putExtra(Intent.EXTRA_TEXT, text);

	        try {
	            activity.startActivity(gmail);
	        } catch (android.content.ActivityNotFoundException ex) {
	            Toast.makeText(activity.getApplicationContext(),"Gmail has not been installed.", Toast.LENGTH_LONG).show();
	        }
	}
}
