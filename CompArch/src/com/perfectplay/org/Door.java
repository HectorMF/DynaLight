package com.perfectplay.org;

public class Door {
	//light transferred between rooms
	public static float transfer = .2f;
	
	private Room room1;
	private Room room2;
	private boolean isClosed;
	
	public Door(){}
		
	void addRoom(Room room){
		if(room1 == null) 
			room1 = room;
		else
			room2 = room;
	}
		
	int getLux(Room room){
		if(isClosed) return 0;
		
		if(room == room1){
			if(room2 == null) return 0;
			return (int) (room2.getLux() * transfer);
		}
		if(room == room2){
			if(room1 == null) return 0;
			return (int) (room1.getLux() * transfer);
		}
		return 0;
	}
	
	void close(){
		isClosed = true;
	}
	
	void open(){
		isClosed = false;
	}
		
}
