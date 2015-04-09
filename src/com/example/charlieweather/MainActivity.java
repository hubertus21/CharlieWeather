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

import com.example.charlieweather.data.CityInfo;
import com.example.charlieweather.data.DataBase;
import com.example.charlieweather.data.EmailService;
import com.example.charlieweather.data.Helper;
import com.example.charlieweather.data.IsNetworkAvailable;
public class MainActivity extends FragmentActivity implements TabListener {

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    public static List<City> cities;
    
    private DataBase dataBase;
	private ProgressDialog2 dialog;
	private CityDataSource dataSource;
	
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        Helper.setContext(this);
        IsNetworkAvailable.context=getApplicationContext();
        dataBase = dataBase.getInstance();
        dataSource=new CityDataSource(this);
		dataSource.open();
		dataBase.setCords(dataSource);
        if(dataBase.getList().isEmpty())
			loadData();      
}

	private void setup() {
		cities = new ArrayList<City>();
		for(int i=0;i<dataBase.getList().size();i++){
			cities.add(new City(dataBase.getList().get(i).getName(),dataBase.getList().get(i).getList()));
		}
	}
	
	private void init(){
        setup();
		viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        mAdapter.setSize(cities.size());
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        actionBar.removeAllTabs();
        // Adding Tabs
        for (CityInfo city : dataBase.getList()) {
            actionBar.addTab(actionBar.newTab().setText(city.getCountry())
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
	}


	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
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
	    			init();
	    		}
	    		else{
	    		dialog=ProgressDialog2.show(this,"Downloading json...");
	    		JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog,dataSource, new Runnable() {
					
					@Override
					public void run() {
						
						init();
					}

					
				});
	    		task.execute(dataBase.getURL());
	    		}}else
	    		{
	    			Toast.makeText(getApplicationContext(),getResources().getText(R.string.Internet_Is_Disabled),Toast.LENGTH_LONG).show();
	    			dataBase.setList(dataSource.getAllInfo());
	    			init();
	    		}
	}
	private void refreshData(){
		if(IsNetworkAvailable.isNetworkAvailable()){
			dialog=ProgressDialog2.show(this,"Downloading json...");
    		JSONAsyncTask task=new JSONAsyncTask(dataBase,dialog,dataSource,new Runnable() {
				
				@Override
				public void run() {
					//PO ODŒWIERZENIU
					init();
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
	        	if(IsNetworkAvailable.isNetworkAvailable())
		            refreshData();
	        	else
	        		Toast.makeText(this,getResources().getText(R.string.Internet_Is_Disabled),Toast.LENGTH_LONG).show();

	            return true;
	        case R.id.send_email:
	        	if(IsNetworkAvailable.isNetworkAvailable())
	        		EmailService.sendMail(dataBase.getInfoInString(viewPager.getCurrentItem()));
	        	else
	        		Toast.makeText(this,getResources().getText(R.string.Internet_Is_Disabled),Toast.LENGTH_LONG).show();
	        	return true;
	        case R.id.delete_city:
	        	if(mAdapter.getCount()>0){
	        		dataSource.removeCity(dataBase.getList().get(actionBar.getSelectedNavigationIndex()));
	        		dataBase.getList().remove(actionBar.getSelectedNavigationIndex());
	        		init();
	        	}
	        	return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
	
