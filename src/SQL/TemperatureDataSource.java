package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.charlieweather.data.CityInfo;
import com.example.charlieweather.data.Temperature;

public class TemperatureDataSource {
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID_TEMPERATURE,
			MySQLiteHelper.COLUMN_ID_OWNER_TEMP, MySQLiteHelper.COLUMN_DAY,
			MySQLiteHelper.COLUMN_MIN, MySQLiteHelper.COLUMN_MAX,
			MySQLiteHelper.COLUMN_NIGHT, MySQLiteHelper.COLUMN_EVE,
			MySQLiteHelper.COLUMN_MORNING };

	public TemperatureDataSource(MySQLiteHelper dbHelper,
			SQLiteDatabase database) {
		this.dbHelper = dbHelper;
		this.database = database;
	}

	public void close() {
		dbHelper.close();
	}

	public void open() throws SQLException {
		if (database == null)
			database = dbHelper.getWritableDatabase();
		if (!database.isOpen())
			database = dbHelper.getWritableDatabase();
	}

	public Temperature createTemp(Temperature temp, Long owner) {
		System.out.println(temp.getDay());
		open();
		Temperature temperature;
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID_OWNER_TEMP, owner);
		values.put(MySQLiteHelper.COLUMN_DAY, temp.getDay());
		values.put(MySQLiteHelper.COLUMN_EVE, temp.getEve());
		values.put(MySQLiteHelper.COLUMN_MAX, temp.getMax());
		values.put(MySQLiteHelper.COLUMN_MIN, temp.getMin());
		values.put(MySQLiteHelper.COLUMN_MORNING, temp.getMorning());
		values.put(MySQLiteHelper.COLUMN_NIGHT, temp.getNight());
		synchronized (database) {
			long insertID = database.insert(MySQLiteHelper.TABLE_TEMP, null,
					values);
			Cursor cursor = database.query(MySQLiteHelper.TABLE_TEMP,
					allColumns, MySQLiteHelper.COLUMN_ID_TEMPERATURE + " = "
							+ insertID, null, null, null, null);
			cursor.moveToFirst();
			temperature = cursorToTemperature(cursor);
			cursor.close();
		}
		return temperature;
	}

	private Temperature cursorToTemperature(Cursor cursor) {
		Temperature temp = new Temperature();
		temp.setID(cursor.getLong(0));
		temp.setIDOwner(cursor.getLong(1));
		temp.setDay(cursor.getDouble(2));
		temp.setMin(cursor.getDouble(3));
		temp.setMax(cursor.getDouble(4));
		temp.setNight(cursor.getDouble(5));
		temp.setEve(cursor.getDouble(6));
		temp.setMorning(cursor.getDouble(7));
		return temp;
	}

	public void deleteTemperature(Temperature temp) {
		long id = temp.getID();
		database.delete(MySQLiteHelper.TABLE_TEMP,
				MySQLiteHelper.COLUMN_ID_TEMPERATURE + " = " + id, null);
	}

	public Temperature getTempretaure(Long ID) {
		open();
		Temperature temp = new Temperature();
		Cursor cursor = database.query(MySQLiteHelper.TABLE_TEMP, allColumns,
				MySQLiteHelper.COLUMN_ID_OWNER_TEMP + " LIKE " + ID, null,
				null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			temp = cursorToTemperature(cursor);
			cursor.moveToNext();
		}
		cursor.close();
		return temp;
	}

}
