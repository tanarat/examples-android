package com.example.dotapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DotCursorAdapter extends CursorAdapter{

	public DotCursorAdapter(Context context) {
		super(context, null, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder viewHolder = (ViewHolder) view.getTag();
		Dot dot = Dot.newInstance(cursor, context);
		viewHolder.txtCoordX.setText(String.valueOf(dot.getX()));
		viewHolder.txtCoordY.setText(String.valueOf(dot.getY()));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.dot_row, parent, false);
		ViewHolder viewHolder = new ViewHolder();
		viewHolder.txtCoordX = (TextView) view.findViewById(R.id.tvX);
		viewHolder.txtCoordY = (TextView) view.findViewById(R.id.tvY);
		view.setTag(viewHolder);
		return view;
	}
	private static final class ViewHolder {
        TextView txtCoordX;
        TextView txtCoordY;
	}
}


