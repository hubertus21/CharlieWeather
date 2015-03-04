package com.example.charlieweather;


import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ListView;
import android.widget.TextView;

public class City extends Fragment {
	
	private List<Weather> nextDays = new ArrayList<Weather>();
	private String name;
	private View rootView;
	ListView nextDaysView;
	public City(){
		
	}
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
		nextDaysView = (ListView)rootView.findViewById(R.id.nextDaysListView);
		for(int i=0;i<10;i++){
			nextDays.add(new Weather("Poniedzia³ek","9 LUT","-3C"));
		}
	}
}
