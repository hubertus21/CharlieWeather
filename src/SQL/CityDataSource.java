package SQL;

import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.Comment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.charlieweather.data.CityInfo;

public class CityDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_COUNTRY,MySQLiteHelper.COLUMN_LAT,MySQLiteHelper.COLUMN_LON };

	public CityDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void close() {
		dbHelper.close();
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	public boolean checkDate(){
		Date date=Calendar.getInstance().getTime();
		
		return false;
	}
	public CityInfo createComment(String country,String lat,String lon) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COUNTRY, country);
		values.put(MySQLiteHelper.COLUMN_LAT,lat);
		values.put(MySQLiteHelper.COLUMN_LON,lon);
		long insertID = database.insert(MySQLiteHelper.TABLE_CITY, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_CITY,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertID, null,
				null, null, null);
		cursor.moveToFirst();
		CityInfo newCityInfo = cursorToCityInfo(cursor);
		cursor.close();
		return newCityInfo;
	}

	private CityInfo cursorToCityInfo(Cursor cursor) {
		CityInfo city=new CityInfo();
		city.setID(cursor.getLong(0));
		city.setCountry(cursor.getString(1));
		city.setCoords(cursor.getDouble(2),cursor.getDouble(3));
		return city;
	}
}
