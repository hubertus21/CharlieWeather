package SQL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.charlieweather.data.CityInfo;
import com.example.charlieweather.data.Formats;

public class CityDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private ForecastDataSource forecastDataSource;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID_FORECAST,
			MySQLiteHelper.COLUMN_COUNTRY,MySQLiteHelper.COLUMN_LAT,MySQLiteHelper.COLUMN_LON };

	
	
	public CityDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
		forecastDataSource=new ForecastDataSource(dbHelper,database);
	}
	public void close() {
		dbHelper.close();
	}

	public void open() throws SQLException {
		if(database==null)
			database = dbHelper.getWritableDatabase();
		if(!database.isOpen())
			database = dbHelper.getWritableDatabase();
	}
	public void clearBase(){
		dbHelper.onUpgrade(database,1,1);
	}
	public boolean checkDate(){
		Date date=Calendar.getInstance().getTime();
		
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CITY, allColumns,
				null, null, null, null, null);
		if(cursor.getCount()<1)
			return false;
		cursor.moveToFirst();
		Date dateOfLastUpdate= forecastDataSource.getLastDate(cursorToCityInfo(cursor).getID());
		System.out.println(new SimpleDateFormat(Formats.DATE_FORMAT).format(date));
		System.out.println(new SimpleDateFormat(Formats.DATE_FORMAT).format(dateOfLastUpdate));
		return (new SimpleDateFormat(Formats.DATE_FORMAT).format(date)).equals(new SimpleDateFormat(Formats.DATE_FORMAT).format(dateOfLastUpdate));
	}
	public int getCount(){
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CITY, allColumns,
				null, null, null, null, null);
		return cursor.getCount();
	}
	public CityInfo createCityInfo(CityInfo city) {
		open();
		CityInfo newCityInfo=new CityInfo();
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COUNTRY,city.getName());
		values.put(MySQLiteHelper.COLUMN_LAT,String.valueOf(city.getCoords().getLat()));
		values.put(MySQLiteHelper.COLUMN_LON,String.valueOf(city.getCoords().getLon()));
		synchronized (database) {
			
		
		long insertID = database.insert(MySQLiteHelper.TABLE_CITY, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CITY,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertID, null,
				null, null, null);
		cursor.moveToFirst();
		newCityInfo = cursorToCityInfo(cursor);
		cursor.close();
		for(int i=0;i<city.getList().size();i++)
			newCityInfo.addForecastForOneDay(forecastDataSource.createForecast(city.getList().get(i),insertID));}
		return newCityInfo;
	}

	private CityInfo cursorToCityInfo(Cursor cursor) {
		CityInfo city=new CityInfo();
		city.setID(cursor.getLong(0));
		city.setCountry(cursor.getString(1));
		city.setCoords(cursor.getDouble(2),cursor.getDouble(3));
		return city;
	}
	public List<CityInfo> getAllInfo(){
		List<CityInfo> list=new ArrayList<CityInfo>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CITY, allColumns,
				null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			CityInfo city = cursorToCityInfo(cursor);
			city.setList(forecastDataSource.getListOfForecast(city.getID()));
			list.add(city);
			cursor.moveToNext();
		}
		cursor.close();	
		return list;
	}
	public void removeCity(CityInfo city){
		for(int i=0;i<city.getList().size();i++)
			forecastDataSource.deleteForecast(city.getList().get(i));
		database.delete(MySQLiteHelper.TABLE_CITY, MySQLiteHelper.COLUMN_ID
				+ " = " + city.getID(), null);
	}
}
