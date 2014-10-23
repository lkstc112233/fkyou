package com.galaxystudio.fkyou.database;

import java.util.ArrayDeque;
import java.util.Calendar;

import com.galaxystudio.fkyou.temperture.TempertureReceiver.TimeTemperturePair;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public final class DatabaseHelper {
	private static SQLiteDatabase database;
	private static Calendar less = Calendar.getInstance();
	private static Calendar more = Calendar.getInstance();

	public static SQLiteDatabase getOpenedDatabase() {
		return database;
	}

	public static SQLiteDatabase connectToDatabase(Context context, String databaseName) {
		database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
		if (!tabExists("temperture"))
			database.execSQL("CREATE TABLE temperture(Time LONG,Temperture DOUBLE);");
		return database;
	}

	private static boolean tabExists(String tabName) {
		if (tabName == null)
			return false;
		try {
			String sql = "SELECT count(*) FROM sqlite_master WHERE type ='table' AND name =?";
			Cursor cursor = database.rawQuery(sql, new String[] { tabName });
			if (cursor.moveToNext())
				return cursor.getInt(0) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void putTemperture(long time, double temperture) {
		database.execSQL("INSERT INTO temperture(Time,Temperture) VALUES(?, ?);", new String[] {
				Long.toString(time), Double.toString(temperture) });
	}

	public static ArrayDeque<TimeTemperturePair> getTempertureAll() {
		ArrayDeque<TimeTemperturePair> toReturn = new ArrayDeque<TimeTemperturePair>();
		String SQL = "SELECT Time,Temperture FROM temperture";
		Cursor cursor = database.rawQuery(SQL, new String[] {});
		while (cursor.moveToNext())
			toReturn.add(new TimeTemperturePair(cursor.getLong(0), cursor.getDouble(1)));
		return toReturn;
	}

	public static ArrayDeque<TimeTemperturePair> getTempertureTime(int yearless, int monthless,
			int dayless, int yearmore, int monthmore, int daymore) {
		ArrayDeque<TimeTemperturePair> toReturn = new ArrayDeque<TimeTemperturePair>();
		String SQL = "SELECT Time,Temperture FROM temperture WHERE (Time>? AND Time<?)";
		DatabaseHelper.less.set(yearless, monthless, dayless, 0, 0, 0);
		DatabaseHelper.more.set(yearmore, monthmore, daymore, 23, 59, 59);
		Cursor cursor = database.rawQuery(
				SQL,
				new String[] { Long.toString(DatabaseHelper.less.getTimeInMillis()),
						Long.toString(DatabaseHelper.more.getTimeInMillis()) });
		while (cursor.moveToNext())
			toReturn.add(new TimeTemperturePair(cursor.getLong(0), cursor.getDouble(1)));
		return toReturn;
	}

	public static ArrayDeque<TimeTemperturePair> getTempertureYear(int less, int more) {
		ArrayDeque<TimeTemperturePair> toReturn = new ArrayDeque<TimeTemperturePair>();
		String SQL = "SELECT Time,Temperture FROM temperture WHERE (Time>? AND Time<?)";
		DatabaseHelper.less.set(less, 1, 1, 0, 0, 0);
		DatabaseHelper.more.set(more, 12, 31, 23, 59, 59);
		Cursor cursor = database.rawQuery(
				SQL,
				new String[] { Long.toString(DatabaseHelper.less.getTimeInMillis()),
						Long.toString(DatabaseHelper.more.getTimeInMillis()) });
		while (cursor.moveToNext())
			toReturn.add(new TimeTemperturePair(cursor.getLong(0), cursor.getDouble(1)));
		return toReturn;
	}

	public static void eraseDatabase() {
		database.delete("temperture", null, null);
	}

	private DatabaseHelper() {
	}
}
