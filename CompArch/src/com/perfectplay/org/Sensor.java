package com.perfectplay.org;

public class Sensor {
	private ISensorAlgorithm lightAlgorithm;
	private Room room;
	
	private float voltage = 120;	
	private int target = 00;
	
	public Sensor(ISensorAlgorithm lightAlgorithm){
		this.lightAlgorithm = lightAlgorithm;
	}
	
	void setRoom(Room room){
		this.room = room;
	}
	
	public void clockTick(){
		int lux = room.calculateLux();
		if(lux == target) return;
		
		voltage = lightAlgorithm.execute(lux, target, voltage);
		room.dim(voltage/120f);
	}
	
	public Sensor setTargetLux(int lux){
		this.target = lux;
		return this;
	}
}
