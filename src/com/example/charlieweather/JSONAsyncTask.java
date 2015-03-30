package com.example.charlieweather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import SQL.CityDataSource;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.charlieweather.data.CityInfo;
import com.example.charlieweather.data.DataBase;
import com.example.charlieweather.data.ParseJson;

class JSONAsyncTask extends AsyncTask<String[], Void, List<CityInfo>> {

	private DataBase db;
	private ProgressDialog2 dialog;
	private CityDataSource dataSource;
	private Runnable runnable;

	public JSONAsyncTask(DataBase dataBase,Runnable r) {
		db = dataBase;
		runnable=r;
	}

	public JSONAsyncTask(DataBase dataBase, ProgressDialog2 dialog, CityDataSource dataSource,Runnable r) {
		db = dataBase;
		this.dialog = dialog;
		this.dataSource=dataSource;
		runnable=r;
	}

	@Override
	protected void onPostExecute(List<CityInfo> result) {
		dialog.dismiss();
		db.setList(result);
		runnable.run();
		super.onPostExecute(result);
	}

	@Override
	protected List<CityInfo> doInBackground(String[]... params) {
		InputStream is = null;
		String json = "";
		String stringURL[] = params[0];
		List<CityInfo> cityInfo = new ArrayList<CityInfo>();
		dataSource.clearBase();
		for (int i = 0; i < stringURL.length; i++) {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(stringURL[i]);
			try {
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				is.close();
				json = sb.toString();
				CityInfo city=ParseJson.getListOfForecastFromString(json);
				if(city!=null)
					cityInfo.add(dataSource.createCityInfo(city));
				else
					db.removeCity(i);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return cityInfo;
	}

}
