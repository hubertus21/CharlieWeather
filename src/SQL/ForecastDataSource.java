package SQL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.charlieweather.data.ForecastForOneDay;

public class ForecastDataSource {

	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private TemperatureDataSource temp;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID_FORECAST,
			MySQLiteHelper.COLUMN_ID_OWNER, MySQLiteHelper.COLUMN_DATE,
			MySQLiteHelper.COLUMN_MAIN_WEATHER,
			MySQLiteHelper.COLUMN_DESCRIPTION, MySQLiteHelper.COLUMN_PRESSURE,
			MySQLiteHelper.COLUMN_SPEED, MySQLiteHelper.COLUMN_CLOUDS,
			MySQLiteHelper.COLUMN_HUMIDITY, MySQLiteHelper.COLUMN_SNOW,
			MySQLiteHelper.COLUMN_RAIN,MySQLiteHelper.COLUMN_ICON};

	public ForecastDataSource(MySQLiteHelper dbHelper, SQLiteDatabase database2) {
		this.dbHelper = dbHelper;
		database=database2;
		temp=new TemperatureDataSource(dbHelper, database2);
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

	public ForecastForOneDay createForecast(
			ForecastForOneDay forecastForOneDay, Long idOwner) {
		open();
		ForecastForOneDay forecast = new ForecastForOneDay();
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_DATE,
				forecastForOneDay.getUnixDate());
		values.put(MySQLiteHelper.COLUMN_ID_OWNER, idOwner);
		values.put(MySQLiteHelper.COLUMN_MAIN_WEATHER,
				forecastForOneDay.getMainWheater());
		values.put(MySQLiteHelper.COLUMN_DESCRIPTION,
				forecastForOneDay.getDescription());
		values.put(MySQLiteHelper.COLUMN_HUMIDITY,
				forecastForOneDay.getHumidity());
		values.put(MySQLiteHelper.COLUMN_PRESSURE,
				forecastForOneDay.getPressure());
		values.put(MySQLiteHelper.COLUMN_SPEED, forecastForOneDay.getSpeed());
		values.put(MySQLiteHelper.COLUMN_CLOUDS, forecastForOneDay.getClouds());
		values.put(MySQLiteHelper.COLUMN_SNOW, forecastForOneDay.getSnow());
		values.put(MySQLiteHelper.COLUMN_RAIN, forecastForOneDay.getRain());
		values.put(MySQLiteHelper.COLUMN_ICON, forecastForOneDay.getImageUrl());
		
		synchronized (database) {		long insertID = database
				.insert(MySQLiteHelper.TABLE_FORECAST,null, values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_FORECAST, allColumns,
				MySQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null,
				null);
		cursor.moveToFirst();
		forecast = cursorToForecast(cursor);
		forecast.setTemperature(temp.createTemp(forecastForOneDay.getTemperature(),insertID));
		cursor.close();}
		System.out.println(forecast.getImageUrl());
		return forecast;
	}

	private ForecastForOneDay cursorToForecast(Cursor cursor) {
		ForecastForOneDay forecast = new ForecastForOneDay();
		forecast.setID(cursor.getLong(0));
		forecast.setID_owner(cursor.getLong(1));
		forecast.setDateUnix(cursor.getString(2));
		forecast.setMainWheater(cursor.getString(3));
		forecast.setDescription(cursor.getString(4));
		forecast.setPressure(cursor.getDouble(5));
		forecast.setSpeed(cursor.getDouble(6));
		forecast.setClouds(cursor.getInt(7));
		forecast.setHumidity(cursor.getInt(8));
		forecast.setSnow(cursor.getDouble(9));
		forecast.setRain(cursor.getDouble(10));
		forecast.setImageUrl(cursor.getString(11));
		return forecast;
	}
	public void deleteForecast(ForecastForOneDay forecast) {
		long id = forecast.getID();
		temp.deleteTemperature(forecast.getTemperature());
		database.delete(MySQLiteHelper.TABLE_FORECAST, MySQLiteHelper.COLUMN_ID_FORECAST
				+ " = " + id, null);
	}
	public List<ForecastForOneDay> getListOfForecast(Long id){
		open();
		List<ForecastForOneDay> list=new ArrayList<ForecastForOneDay>();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_FORECAST, allColumns,
				MySQLiteHelper.COLUMN_ID_OWNER+ " LIKE "+id, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ForecastForOneDay f=cursorToForecast(cursor);
			f.setTemperature(temp.getTempretaure(f.getID()));
			list.add(f);
			cursor.moveToNext();
		}
		cursor.close();	
		
		return list;
	}
	public Date getLastDate(Long id){
		open();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_FORECAST, allColumns,
				MySQLiteHelper.COLUMN_ID_OWNER+ " LIKE "+id, null, null, null, null);
		cursor.moveToFirst();
		ForecastForOneDay f=cursorToForecast(cursor);
		return f.getDate();
		
		
	}
}
