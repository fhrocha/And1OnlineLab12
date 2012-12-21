package br.com.globalcode.android.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.drawable.Drawable;
import android.util.Log;

public final class Util4Android {

	public static final Drawable loadImageFromNetwork(String url) {
		
		InputStream is = null;
		
		try {
			
			is = (InputStream) new URL(url).getContent();
			
		} catch (MalformedURLException e) {
			
			Log.e("And1OnlineLab12", e.getMessage());
		} catch (IOException e) {
			
			Log.e("And1OnlineLab12", e.getMessage());
		}
 
		return Drawable.createFromStream(is, "srcName");
	}
}
