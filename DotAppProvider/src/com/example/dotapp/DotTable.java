package com.example.dotapp;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;


public final class DotTable {
    public static final String TABLE_NAME = "DOTS_table";

    public static class DotsColumns implements BaseColumns {
        public static final String X = "X_column";
        public static final String Y = "Y_column";
    }



    public static void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + DotTable.TABLE_NAME + " (");
        sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
        sb.append(DotsColumns.X + " INTEGER, ");
        sb.append(DotsColumns.Y + " INTEGER");
        sb.append(");");
        db.execSQL(sb.toString());
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DotTable.TABLE_NAME);
        DotTable.onCreate(db);
    }


}