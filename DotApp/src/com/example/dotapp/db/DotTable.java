package com.example.dotapp.db;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;



public final class DotTable {
	public static final String TABLE_NAME = "DOT";
	public static class Columns implements BaseColumns{
		public static final String X = "x";
		public static final String Y = "y";
	}
	public static void onCreate(SQLiteDatabase db){
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + TABLE_NAME + "(");
		sb.append(Columns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(Columns.X + " INTEGER, ");
		sb.append(Columns.Y + " INTEGER )");
		db.execSQL(sb.toString());
	}
}
	