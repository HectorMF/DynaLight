package com.perfectplay.org;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Door {
	//light transferred between rooms
	public static float transfer = .1f;
	
	private Room room1;
	private Room room2;
	private boolean isClosed;
	private boolean isHorizontal;
	private Vector2 position1;
	private Vector2 position2;
	
	public Door(){
		this.position1 = new Vector2();
		this.position2 = new Vector2();
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
	
	public Door setPosition1(int x, int y){
		position1.set(x,y);
		return this;
	}
	
	public Door setPosition2(int x, int y){
		position2.set(x,y);
		return this;
	}
	
	public Door setHorizontal(){
		isHorizontal = true;
		return this;
	}
	
	public void draw(Vector2 pos, ShapeRenderer renderer, Room room){
		Vector2 temp = position2;
		if(room == room1)
			temp = position1;
		if(isHorizontal){
			renderer.line(pos.cpy().add(temp).x, pos.cpy().add(temp).y, pos.cpy().add(temp).x + 40, pos.cpy().add(temp).y);
			renderer.line(pos.cpy().add(temp).x, pos.cpy().add(temp).y+1, pos.cpy().add(temp).x + 40, pos.cpy().add(temp).y+1);
		}else{
			renderer.line(pos.cpy().add(temp).x+1, pos.cpy().add(temp).y, pos.cpy().add(temp).x+1, pos.cpy().add(temp).y + 40);
			renderer.line(pos.cpy().add(temp).x, pos.cpy().add(temp).y, pos.cpy().add(temp).x, pos.cpy().add(temp).y + 40);
		}
	}
	
	void close(){
		isClosed = true;
	}
	
	void open(){
		isClosed = false;
	}
		
}
