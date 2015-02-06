package com.example.charlieweather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.TextView;

public class City extends Fragment {
	
	
	private String name;
	private View rootView;
	ListView nextDays;
	public City(String _name){
		name = _name;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_city, container,false);
        // Inflate the layout for this fragment
		setup();
        return inflater.inflate(R.layout.fragment_city, container, false);
    }

	private void setup() {
		nextDays = (ListView)rootView.findViewById(R.id.nextDaysListView);
	}
}
