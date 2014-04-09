package com.perfectplay.org;

public class Sensor {
	private ISensorAlgorithm lightAlgorithm;
	private Room room;
	
	private int voltage = 2;	
	private int target = 500;
	
	public Sensor(ISensorAlgorithm lightAlgorithm){
		this.lightAlgorithm = lightAlgorithm;
	}
	
	void setRoom(Room room){
		this.room = room;
	}
	
	public void clockTick(){
		int lux = room.calculateLux();
		voltage = lightAlgorithm.execute(lux, target, voltage);
		room.setVoltage(voltage);
	}
	
	public void setTargetLux(int lux){
		this.target = lux;
	}
}
