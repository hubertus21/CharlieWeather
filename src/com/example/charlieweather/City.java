package com.example.charlieweather;


import java.util.List;

import tabsswipe.adapter.RowAdapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.charlieweather.data.ForecastForOneDay;

@SuppressLint("ValidFragment")
public class City extends Fragment {
	private View rootView;
	private ListView list;
	private List<ForecastForOneDay> forecast;
	public City(){
	}
	
	public City(String _name, List<ForecastForOneDay> _forecast){
		forecast = _forecast;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragmentcity, container,false);
		setup();
        return rootView;
    }

	private void setup() {
		list=(ListView)rootView.findViewById(R.id.listView1);
		RowAdapter adapter=new RowAdapter(getActivity(), R.layout.fragment_city,forecast);
		list.setAdapter(adapter);
	}
}


