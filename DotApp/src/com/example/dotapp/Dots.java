package com.example.dotapp;

import java.util.ArrayList;
import java.util.List;

public class Dots {
	private List<Dot> dots = new ArrayList<Dot>();
	private DotsChangeListener dotsChangeListener;
	public void addDot(Dot dot){
		dots.add(dot);
		notifyDotsChanged();
	}
	private void notifyDotsChanged() {
		if(dotsChangeListener != null){
			dotsChangeListener.onDotsChange(this);
		}
	}
	public void clearDot(){
		dots.clear();
		notifyDotsChanged();
	}
	public int count(){
		return dots.size();
	}
	public Dot getDot(int position){
		return dots.get(position);
	}
	public void removeDot(int position){
		dots.remove(position);
		notifyDotsChanged();
	}
	public void setDotsChangeListener(DotsChangeListener dotsChangeListener) {
		this.dotsChangeListener = dotsChangeListener;
	}
	public interface DotsChangeListener{
		public void onDotsChange(Dots dots);
	}
	public void deleteDot(int position) {
		dots.remove(position);
		notifyDotsChanged();
	}
	public void editDot(int position, int x, int y) {
		Dot d = dots.get(position);
		d.setX(x);
		d.setY(y);
		notifyDotsChanged();
	}
}
