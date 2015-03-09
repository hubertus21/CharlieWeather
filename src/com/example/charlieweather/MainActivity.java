package com.example.charlieweather;
import java.util.ArrayList;
import java.util.List;

import tabsswipe.adapter.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.charlieweather.data.CityInfo;
import com.example.charlieweather.data.DataBase;
public class MainActivity extends FragmentActivity implements TabListener {

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    public static List<City> cities;
    //public static List<CityInfo> citiesInfo;
    // Tab titles
    private String[] tabs = {"1","2","3","4","5"};
    
    
    private DataBase dataBase;
	private ProgressDialog dialog;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        dataBase=dataBase.getInstance();
        dataBase.setCords();
        loadData();
        
        
        
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        setup();
        
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
       
        
        
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                      actionBar = getActionBar();
                      actionBar.setSelectedNavigationItem(position);              
                      }
                });
        Toast.makeText(getApplicationContext(), Integer.toString(dataBase.getList().size()), Toast.LENGTH_LONG).show();
        
}

	private void setup() {
		cities = new ArrayList<City>();
		for(int i=0;i<5;i++){
			//tabs[i] = Integer.toString(dataBase.getList().size());
			cities.add(new City(tabs[i]));
		}
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++){
			tabs[i] = tabs[i]+1;
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	private void loadData(){
	    	if(isNetworkAvailable()){
	    	dialog=ProgressDialog.show(this,"","Downloading json...");
	    	JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog/*,new Runnable() {
				public void run() {
					SetContent();					==czekam na commit z nowym konstruktorem od huberta
				}
			}*/);
	    	task.execute(dataBase.getURL());}
	    	
	}
	private boolean isNetworkAvailable(){
    	ConnectivityManager connectivityManager 
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
  NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
  return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	public void SetContent(){
		for(int i=0;i<5;i++){
			actionBar.getTabAt(i).setText(dataBase.getList().get(i).getName().toString());
			tabs[i] = dataBase.getList().get(i).getName().toString();
			
		}
	}
	
}
