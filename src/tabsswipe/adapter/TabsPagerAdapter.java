package tabsswipe.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.charlieweather.MainActivity;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
	private int size=0;
	
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        
    }
 
    @Override
    public Fragment getItem(int index) {

        	return MainActivity.cities.get(index);
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return size;
    }
    public void setSize(int size){
    	this.size=size;
    }
}
