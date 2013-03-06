package com.example.dotapp;
import android.provider.BaseColumns;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class Dot extends ModelBase {
    private Context context;
    private int id;
    private int X;
    private int Y;


    public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Dot() {
        super();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void fromCursor(Cursor cursor, Context context) {
        this.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
        this.X = cursor.getInt(cursor.getColumnIndex(DotTable.DotsColumns.X));        
        this.Y = cursor.getInt(cursor.getColumnIndex(DotTable.DotsColumns.Y));        
        this.context = context;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(DotTable.DotsColumns.X, this.X);
        values.put(DotTable.DotsColumns.Y, this.Y);
        return values;
    }

    public static Dot newInstance(Cursor cursor, Context context) {
        Dot DOTS = new Dot();
        DOTS.fromCursor(cursor, context);
        return DOTS;
    }


}