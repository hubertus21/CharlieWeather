package SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	private static final String DATABASE_NAME = "comments.db";
	private static final int DATABASE_VERSION = 1;
	

	public static final String TABLE_CITY = "cities";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "_name";
	public static final String COLUMN_COUNTRY="_country";
	public static final String COLUMN_LAT="_lat";
	public static final String COLUMN_LON="_lon";
	
	
	public static final String TABLE_FORECAST="forecast";
	public static final String COLUMN_ID_FORECAST="_id";
	public static final String COLUMN_ID_OWNER="_idOwner";
	public static final String COLUMN_DATE="_date";
	public static final String COLUMN_MAIN_WEATHER="_mainWeather";
	public static final String COLUMN_DESCRIPTION="_description";
	public static final String COLUMN_PRESSURE="_pressure";
	public static final String COLUMN_SPEED="_speed";
	public static final String COLUMN_CLOUDS="_clouds";
	public static final String COLUMN_HUMIDITY="_humidity";
	public static final String COLUMN_SNOW="_snow";
	public static final String COLUMN_RAIN="_rain";
	
	public static final String TABLE_TEMP="temperature";
	public static final String COLUMN_ID_TEMPERATURE="_idTemp";
	public static final String COLUMN_ID_OWNER_TEMP="_idOwner";
	public static final String COLUMN_DAY ="_day";
	public static final String COLUMN_MIN ="_min";
	public static final String COLUMN_MAX ="_max";
	public static final String COLUMN_NIGHT ="_night";
	public static final String COLUMN_EVE ="_eve";
	public static final String COLUMN_MORNING="_morning";


	private static final String DATABASE_CREATE_CITY = "create table "
			+ TABLE_CITY + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not  null, "+COLUMN_COUNTRY+" text, "+COLUMN_LAT+" text, "+COLUMN_LON+" text" +");";
	private static final String DATABASE_CREATE_FORECAST = "create table "
			+ TABLE_CITY + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not  null"+");";
	private static final String DATABASE_CREATE_TEMP = "create table "
			+ TABLE_CITY + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not  null"+");";

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE_CITY);
		//db.execSQL(DATABASE_CREATE_FORECAST);
		//db.execSQL(DATABASE_CREATE_TEMP);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + " ,which will destroy all data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORECAST);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEMP);
		onCreate(db);
	}

}
