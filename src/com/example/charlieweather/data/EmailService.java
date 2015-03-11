package com.example.charlieweather.data;

import android.content.Intent;

public class EmailService {

	static public void sendMail(String text){
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND); 
		emailIntent.setType("application/image");
		emailIntent.putExtra(Intent.EXTRA_TEXT,text);
		Helper.context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
	}
}
