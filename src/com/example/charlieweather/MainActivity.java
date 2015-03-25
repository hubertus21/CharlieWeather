package com.example.charlieweather;
import java.util.ArrayList;
import java.util.List;

import tabsswipe.adapter.TabsPagerAdapter;
import SQL.CityDataSource;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.charlieweather.data.DataBase;
import com.example.charlieweather.data.EmailService;
import com.example.charlieweather.data.Helper;
import com.example.charlieweather.data.IsNetworkAvailable;
public class MainActivity extends FragmentActivity implements TabListener {

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    public static List<City> cities;
    //public static List<CityInfo> citiesInfo;
    // Tab titles
    private String[] tabs = { "Kraków", "Nowy Jork", "Szikago", "Tokyo", "Smoleñsk" };
 
    
    
    private DataBase dataBase;
	private ProgressDialog dialog;
	private CityDataSource dataSource;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        Helper.setContext(this);
        IsNetworkAvailable.context=getApplicationContext();
        dataBase=dataBase.getInstance();
        dataBase.setCords();
        dataSource=new CityDataSource(this);
		dataSource.open();
		if(dataBase.getList().isEmpty())
			loadData();
        
        
        
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        setup();
        
        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                      actionBar = getActionBar();
                      actionBar.setSelectedNavigationItem(position);              
                      }
                });
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
	    	if(IsNetworkAvailable.isNetworkAvailable()){
	    		if(dataSource.checkDate())
	    		{
	    			dataBase.setList(dataSource.getAllInfo());
	    		}
	    		else{
	    		dialog=ProgressDialog.show(this,"","Downloading json...");
	    		JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog,dataSource, new Runnable() {
					
					@Override
					public void run() {
						
					}
				});
	    		task.execute(dataBase.getURL());
	    		}}else
	    			dataBase.setList(dataSource.getAllInfo());
	}
	private void refreshData(){
		if(IsNetworkAvailable.isNetworkAvailable()){
			dialog=ProgressDialog.show(this,"","Downloading json...");
    		JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog,dataSource,new Runnable() {
				
				@Override
				public void run() {
					
				}
			});
    		task.execute(dataBase.getURL());
		}else
			Toast.makeText(getApplicationContext(),getResources().getText(R.string.Internet_Is_Disabled),Toast.LENGTH_LONG).show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.game_menu, menu);
	    return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.add_city:
	        	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
	    				this);
	    			alertDialogBuilder.setTitle(getResources().getString(R.string.addCity));
	    			final EditText input = new EditText(this);
	    			alertDialogBuilder.setView(input);
	    			alertDialogBuilder
	    				.setMessage(getResources().getString(R.string.enter_city))
	    				.setCancelable(false)
	    				.setPositiveButton(getResources().getString(R.string.confirm),new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						if(input.getText().toString().length()>2){
	    							dataBase.addNewCity(input.getText().toString());
	    							}
	    						else
	    							Toast.makeText(Helper.context,getResources().getString(R.string.word_too_short),Toast.LENGTH_SHORT).show();
	    					}
	    				  })
	    				.setNegativeButton(getResources().getString(R.string.cancel),new DialogInterface.OnClickListener() {
	    					public void onClick(DialogInterface dialog,int id) {
	    						dialog.cancel();
	    					}
	    				});
	    				AlertDialog alertDialog = alertDialogBuilder.create();
	    				alertDialog.show();	
	            return true;
	        case R.id.refresh:
	            refreshData();
	            return true;
	        case R.id.send_email:
	        	EmailService.sendMail(dataBase.getInfoInString(viewPager.getCurrentItem()));
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
	
