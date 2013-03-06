package com.example.dotapp;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor>{
	private ListView mListView;
	private DotCursorAdapter mAdapter;
	private Dao<Dot> mDotDao;
	private Random mRandom = new Random();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListView = (ListView) findViewById(R.id.lvDots);
		mAdapter = new DotCursorAdapter(this);
		mListView.setAdapter(mAdapter);
		mDotDao = new Dao<Dot>(Dot.class, this, Provider.DOTS_CONTENT_URI);
		getSupportLoaderManager().initLoader(0, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		return new CursorLoader(this, Provider.DOTS_CONTENT_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		mAdapter.swapCursor(null);
	}
	public void random(View view){
		Dot dot = new Dot();
		dot.setX(mRandom.nextInt(300));
		dot.setY(mRandom.nextInt(300));
		mDotDao.insert(dot);
	}
}
