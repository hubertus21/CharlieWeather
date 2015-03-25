package com.example.charlieweather.data;

import com.example.charlieweather.MainActivity;

import android.content.Intent;

public class EmailService {

	static public void sendMail(String text){
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		emailIntent.setType("application/image");
		emailIntent.putExtra(Intent.EXTRA_TEXT,text);
		Helper.context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
}
