package com.perfectplay.org;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Room {
	
	private Vector2 dimensions;
	private ArrayList<Window> windows;
	private ArrayList<LightFixture> lights;
	private ArrayList<Door> doors;
	private Sensor sensor;
	
	private int lux;
	
	public Room(Vector2 dimensions){
		this.windows = new ArrayList<Window>();
		this.lights = new ArrayList<LightFixture>();
		this.doors = new ArrayList<Door>();
		lux = 0;
	}
	
	int calculateLux(){
		lux = 0;
		for(Window window : windows){
			lux +=  (int)(window.getLumens()/(float)(dimensions.x * dimensions.y));
		}
		
		for(LightFixture light : lights){
			lux += (int)(light.getLumens()/(float)(dimensions.x * dimensions.y));
		}
		
		for(Door door : doors){
			lux += door.getLux(this);
		}
		
		return lux;
	}
	
	public int getLux(){
		return lux;
	}
	
	public void setSensor(Sensor sensor){
		this.sensor = sensor;
		this.sensor.setRoom(this);
	}
	
	public void addWindow(Window window){
		windows.add(window);
	}
	
	public void addLight(LightFixture fixture){
		lights.add(fixture);
	}
	
	public void addDoor(Door door){
		doors.add(door);
		door.addRoom(this);
	}
	
	void setVoltage(int voltage){
		for(LightFixture light : lights){
			light.setVoltage(voltage);
		}
	}
}
