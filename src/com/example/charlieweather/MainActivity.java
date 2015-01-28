package com.example.charlieweather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

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
    	dialog=ProgressDialog.show(this,"","Downloading json...");
    	JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog);
    	task.execute(dataBase.getURL());
    }
}
