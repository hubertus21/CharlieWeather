package com.example.charlieweather.data;

import android.content.Context;

public class Helper {
	public static Context context;
	public static void setContext(Context c){
		if(context==null)
			context=c;
	}
}
