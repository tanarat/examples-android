package com.example.dotapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class DotDetailActivity extends Activity {
	TextView txtX,txtY;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dot_detail);
		txtX = (TextView) findViewById(R.id.txtX);
		txtY = (TextView) findViewById(R.id.txtY);
		
		Intent intent = getIntent();
		if(intent != null && intent.getExtras() != null){
			int x = intent.getExtras().getInt(MainActivity.COORDINATE_X);
			int y = intent.getExtras().getInt(MainActivity.COORDINATE_Y);
			txtX.setText(String.valueOf(x));
			txtY.setText(String.valueOf(y));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dot_detail, menu);
		return true;
	}

}
