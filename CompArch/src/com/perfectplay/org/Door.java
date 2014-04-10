package com.perfectplay.org;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Door {
	//light transferred between rooms
	public static float transfer = .2f;
	
	private Room room1;
	private Room room2;
	private boolean isClosed;
	private boolean isHorizontal;
	private Vector2 position;
	
	public Door(){
		this.position = new Vector2();
	}
		
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
	
	public Door setPosition(int x, int y){
		position.set(x*20,y*20);
		return this;
	}
	
	public Door setHorizontal(){
		isHorizontal = true;
		return this;
	}
	
	public void draw(Vector2 pos, ShapeRenderer renderer){
		if(isHorizontal){
			renderer.line(pos.cpy().add(position).x, pos.cpy().add(position).y, pos.cpy().add(position).x + 40, pos.cpy().add(position).y);
			renderer.line(pos.cpy().add(position).x, pos.cpy().add(position).y-1, pos.cpy().add(position).x + 40, pos.cpy().add(position).y-1);
		}else{
			renderer.line(pos.cpy().add(position).x+1, pos.cpy().add(position).y, pos.cpy().add(position).x+1, pos.cpy().add(position).y + 40);
			renderer.line(pos.cpy().add(position).x, pos.cpy().add(position).y, pos.cpy().add(position).x, pos.cpy().add(position).y + 40);
		}
	}
	
	void close(){
		isClosed = true;
	}
	
	void open(){
		isClosed = false;
	}
		
}
