package tabsswipe.adapter;

import com.example.charlieweather.City;
import com.example.charlieweather.GamesFragment;
import com.example.charlieweather.MainActivity;
import com.example.charlieweather.MoviesFragment;
import com.example.charlieweather.TopRatedFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
 
	
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
        
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            // Top Rated fragment activity
        	return MainActivity.cities.get(index);
        case 1:
            // Games fragment activity
        	return MainActivity.cities.get(index);
        case 2:
            // Movies fragment activity
        	return MainActivity.cities.get(index);
        case 3:
        	return MainActivity.cities.get(index);
        case 4:
        	return MainActivity.cities.get(index);
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }
 
}
