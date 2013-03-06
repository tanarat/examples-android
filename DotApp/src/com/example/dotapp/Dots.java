package com.example.dotapp;

import java.util.ArrayList;
import java.util.List;

import com.example.dotapp.db.DBHelper;
import com.example.dotapp.db.DotTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Dots {
//	private List<Dot> dots = new ArrayList<Dot>();
	private DotsChangeListener dotsChangeListener;
	private SQLiteDatabase db;
	public Dots(Context context){
		DBHelper dbHleper = new DBHelper(context);
		db = dbHleper.getWritableDatabase();
	}
	public void addDot(Dot dot){
//		dots.add(dot);
		ContentValues values = new ContentValues();
		values.put(DotTable.Columns.X, dot.getX());
		values.put(DotTable.Columns.Y, dot.getY());
		db.insert(DotTable.TABLE_NAME, null, values);
		notifyDotsChanged();
	}
	private void notifyDotsChanged() {
		if(dotsChangeListener != null){
			dotsChangeListener.onDotsChange(this);
		}
	}
	public void clearDot(){
//		dots.clear();
		db.delete(DotTable.TABLE_NAME, null, null);
		notifyDotsChanged();
	}
	public int count(){
//		return dots.size();
		Cursor cursor = db.query(DotTable.TABLE_NAME, new String[]{DotTable.Columns._ID}, null, null, null, null, null);
		int count = cursor.getCount();
		cursor.close();
		return count;
	}
	public Dot getDot(int position){
//		return dots.get(position);
		Cursor cursor = db.query(DotTable.TABLE_NAME, new String[]{DotTable.Columns.X,DotTable.Columns.Y}, null, null, null, null, null);
		cursor.moveToPosition(position);
		Dot dot = new Dot(cursor.getInt(cursor.getColumnIndex(DotTable.Columns.X)), cursor.getInt(cursor.getColumnIndex(DotTable.Columns.Y)));
		cursor.close();
		return dot;
	}
	public void deleteDot(int position){
//		dots.remove(position);
		
		notifyDotsChanged();
	}
	public void setDotsChangeListener(DotsChangeListener dotsChangeListener) {
		this.dotsChangeListener = dotsChangeListener;
	}
	public interface DotsChangeListener{
		public void onDotsChange(Dots dots);
	}
//	public void deleteDot(int position) {
//		dots.remove(position);
//		notifyDotsChanged();
//	}
	public void editDot(int position, int x, int y) {
//		Dot d = dots.get(position);
		Dot d = getDot(position);
		d.setX(x);
		d.setY(y);
		notifyDotsChanged();
	}
}
