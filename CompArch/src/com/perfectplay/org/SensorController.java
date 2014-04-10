package com.perfectplay.org;

import java.util.ArrayList;

public class SensorController {
	
	public static SensorController instance = new SensorController();
	
	private ArrayList<Sensor> sensors = new ArrayList<Sensor>();
	
	private SensorController(){
		
	}
	
	public void addSensor(Sensor sensor){
		sensors.add(sensor);
	}
	
	public void update(){
		 for(Sensor sensor : sensors){
			 sensor.clockTick();
		 }
	}
}
