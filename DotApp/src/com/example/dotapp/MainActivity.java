package com.example.dotapp;

import java.util.Random;

import com.example.dotapp.Dots.DotsChangeListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity implements DotsChangeListener {
	private static final int MAX_Y = 100;
	private static final int MAX_X = 100;
	private static final int MENU_CLEAR = 1000;
	private static final int MENU_DELETE = 1001;
	private static final int MENU_EDIT = 1002;
	private Dots mDots = new Dots();
	private ListView lvDots;
	private DotListAdapter dotListAdapter;
	private Random randomObj = new Random();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mDots.setDotsChangeListener(this);
		lvDots = (ListView) findViewById(R.id.lvDots);
		dotListAdapter = new DotListAdapter();
		lvDots.setAdapter(dotListAdapter);
		registerForContextMenu(lvDots);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
		menu.add(Menu.NONE, MENU_CLEAR, Menu.NONE, R.string.menuClear);
		return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, R.string.menuDelete);
		menu.add(Menu.NONE, MENU_EDIT, Menu.NONE, R.string.menuEdit);
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		
		
		switch (item.getItemId()) {
		case MENU_DELETE:
			mDots.deleteDot(info.position);
			break;
		case MENU_EDIT:
			
			break;
		default:
			break;
		}
		return super.onContextItemSelected(item);
	}

	public void random(View view){
		int x = randomObj.nextInt(MAX_X);
		int y = randomObj.nextInt(MAX_Y);
		Dot dot = new Dot(x, y);
		mDots.addDot(dot);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_CLEAR:
			mDots.clearDot();
			break;
		}
		return true;
	}

	private class DotListAdapter extends BaseAdapter implements ListAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mDots.count();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mDots.getDot(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;
			if(convertView == null){
				// create a new row
				viewHolder = new ViewHolder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dot_row, null);
				viewHolder.txtX = (TextView) convertView.findViewById(R.id.tvX);
				viewHolder.txtY = (TextView) convertView.findViewById(R.id.tvY);
				convertView.setTag(viewHolder);
			}else{
				// reuse 
				viewHolder = (ViewHolder) convertView.getTag();
			}
			//update view
			Dot dot = (Dot) getItem(position);
			viewHolder.txtX.setText(String.valueOf(dot.getX()));
			viewHolder.txtY.setText(String.valueOf(dot.getY()));
			return convertView;
		}
		
		private final class ViewHolder{
			TextView txtX;
			TextView txtY;
		}
		
	}

	@Override
	public void onDotsChange(Dots dots) {
		// TODO Auto-generated method stub
		dotListAdapter.notifyDataSetChanged();
	}
}
