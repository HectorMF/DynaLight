package com.perfectplay.org;

public class Window {

	public Window(){}
	
	public int getLumens(){
		return calculateLumens();
	}
	
	int calculateLumens(){
		return Sun.instance.getLumens();
	}
	
}
