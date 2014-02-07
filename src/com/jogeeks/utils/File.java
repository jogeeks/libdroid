package com.jogeeks.utils;

import java.io.FileOutputStream;
import java.io.IOError;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class File {
	
	private Activity activity;
	
	public File(Activity a){
		activity = a;
	}
	
	
	/**
	 * Save an image resource to a file.
	 *
	 * @param resource The image resource in your project.
	 * @param where The pathe where you want to save your image on your device.
	 * 
	 * Pass the full path whether you want to save it on the external storage or the internal one
	 * 
	 * The method will return a java.io.File object.
	 * The method throws an IOException and will return null on any IOException.
	 * 
	 * 	Environment.getExternalStorageDirectory().toString();
	 *  Environment.getDataDirectory().toString();
	 *  
	 *  Make sure to add the appropriate permission to your manifest.
	 *  
	 *  android.permission.WRITE_EXTERNAL_STORAGE
	 *  android.permission.WRITE_INTERNAL_STORAGE
	 */

	public java.io.File saveImageResourceToFile(int resource, java.io.File where) throws IOException{
		Bitmap bm = BitmapFactory.decodeResource(activity.getApplicationContext().getResources(), resource);
		try{
			java.io.File file = where;
			FileOutputStream outStream = new FileOutputStream(file);
		    bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
		    outStream.flush();
		    outStream.close();
		    return file;
		}catch(IOException s){

		}
		return null;
	}
	

}
