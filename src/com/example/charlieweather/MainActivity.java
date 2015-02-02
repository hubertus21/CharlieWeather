package com.example.charlieweather;

import android.R.bool;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.TextView;

import com.example.charlieweather.data.DataBase;


public class MainActivity extends Activity {
	private DataBase dataBase;
	private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase=dataBase.getInstance();
        dataBase.setCords();
        setup();
    }
    public void setup(){
    	if(isNetworkAvailable()){
    	dialog=ProgressDialog.show(this,"","Downloading json...");
    	JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog);
    	task.execute(dataBase.getURL());}
    	TextView t=(TextView)findViewById(R.id.textView1);
    }
    private boolean isNetworkAvailable(){
    	ConnectivityManager connectivityManager 
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
  return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
