package com.perfectplay.org;

public class Door {
	//light transferred between rooms
	public static float transfer = .2f;
	
	private Room room1;
	private Room room2;
		
	public Door(){}
		
	void addRoom(Room room){
		if(room1 == null) 
			room1 = room;
		else
			room2 = room;
	}
		
	int getLux(Room room){
		if(room == room1){
			return (int) (room2.calculateLux() * transfer);
		}
		if(room == room2){
			return (int) (room1.calculateLux() * transfer);
		}
		return 0;
	}
		
}
