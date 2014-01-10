package com.jogeeks.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.WindowManager;
import android.widget.Toast;


/**
 * @author Firas Al Kafri
 *
 */
public class Window {

	
	private Activity activity;
	private static long back_pressed;

	/**
	 *
	 * @param activity will be used in all functions that are called through this instance
	 */
	public Window(Activity activity){
		this.activity = activity;
	}
	
	/**
	 *
	 * Returns screen width
	 */
	public int getScreenWidth(){
		Context context = activity.getApplicationContext();
		return ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
	}
	
	/**
	 *
	 * Call function from the overrided onBackPressed method in your activity 
	 */
	public void doubleBackPressToExit(){
        if (back_pressed + 2000 > System.currentTimeMillis()) activity.onBackPressed();
        else Toast.makeText(activity.getBaseContext(), "Press once again to exit!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
	}
	
	/**
	 *
	 * Returns screen height
	 */
	public int getScreenHeight(){
		Context context = activity.getApplicationContext();
		return ((WindowManager) context.getSystemService(context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
	}
	
	/**
	 * The passed activity will be full-screened, must be called before setting content view
	 */
	public void setFullScreen() {
		activity.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
	/**
	 * A basic splash screen, pass the current activity and the activity you want to be shown after a certain amount of time
	 *
	 * @param to The target activity you want to be show after a certain amount of time.
	 * @param time The amount of time before the target activity is shown in milliseconds.
	 */
	public void setSplash(Class to, long time){
		new TimeOut().execute(true, time, to);
	}
	
	/**
	 * A basic splash screen, pass your intent to be started after a certain amount of time
	 * 
	 * @param intent The intent that you want to be started after a certain amount of time.
	 * @param time The amount of time before the target activity is shown in milliseconds.
	 */
	public void setSplash(Intent intent, long time){
		new TimeOut().execute(false, time, intent);
	}
	
	private class TimeOut extends AsyncTask<Object, Void, Void> {

		@Override
		protected Void doInBackground(Object... params) {
			try {
				Thread.sleep((Long) params[1]);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				
				if((Boolean) params[0]){
					activity.startActivity(new Intent( activity,
							(Class) params[2]));
					activity.finish();
				}else{
					activity.startActivity((Intent) params[2]);
					activity.finish();
				}
			}
			return null;
		}
	}
	
	/**
	 * Prevents the screen from going off, this function works per activity
	 * The WAKE_LOCK permission should be added to the manifest.
	 */
	public void keepScreenOn(Activity activity){
		activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
}
