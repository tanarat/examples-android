package com.example.dotapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DotsView extends View {
	private Paint paint = new Paint();
	public DotsView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DotsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DotsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Canvas canvas) {
		paint.setColor(Color.BLUE);
		if(dotsViewDataSource != null){
			for (int position = 0; position < dotsViewDataSource.getCount(); position++) {
				Dot dot = dotsViewDataSource.getItem(position);
				canvas.drawCircle(dot.getX(), dot.getY(), 15, paint);
			}
		}
	}
	
	public void setDotsViewDataSource(DotsViewDataSource dotsViewDataSource) {
		this.dotsViewDataSource = dotsViewDataSource;
	}
	private DotsViewDataSource dotsViewDataSource;
	public interface DotsViewDataSource{
		Dot getItem(int position);
		int getCount();
	}

}
