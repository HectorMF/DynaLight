package com.perfectplay.org;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Room {
	public final Vector2 position;
	public final Vector2 dimensions;
	
	private ArrayList<Window> windows;
	private ArrayList<LightFixture> lights;
	private ArrayList<Door> doors;
	private Sensor sensor;
	
	private int lux;
	
	private float percentage;
	
	public Room(int width, int length){
		this.position = new Vector2();
		this.dimensions = new Vector2(width,length);
		this.windows = new ArrayList<Window>();
		this.lights = new ArrayList<LightFixture>();
		this.doors = new ArrayList<Door>();
		this.lux = 0;
	}
	
	int calculateLux(){
		lux = 0;
		for(Window window : windows){
			lux +=  (int)(10.76391 * window.getLumens()/(float)(dimensions.x * dimensions.y));
		}
		
		for(LightFixture light : lights){
			lux += (int)(10.76391 * light.getLumens()/(float)(dimensions.x * dimensions.y));
		}
		
		for(Door door : doors){
			lux += door.getLux(this);
		}
		
		return lux;
	}
	
	public int getLux(){
		return lux;
	}
	
	public Room setPosition(int x, int y){
		this.position.set(x*20,y*20);
		return this;
	}
	
	public Room setSensor(Sensor sensor){
		this.sensor = sensor;
		this.sensor.setRoom(this);
		SensorController.instance.addSensor(sensor);
		return this;
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
	
	void dim(float percentage){
		this.percentage = percentage;
		for(LightFixture light : lights){
			light.dim(percentage);
		}
	}
	
	public void draw(ShapeRenderer renderer){
		renderer.rect(position.x, position.y, dimensions.x*20, dimensions.y*20, 0, 0, 0, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);
		renderer.rect(position.x+1, position.y+1, dimensions.x*20 - 2, dimensions.y*20 - 2, 0, 0, 0, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK);

		for(Window window : windows){
			
		}
		
		for(LightFixture light : lights){
			renderer.setColor(Color.ORANGE);
			light.draw(position, renderer);
		}
		
		for(Door door : doors){
			renderer.setColor(Color.WHITE);
			door.draw(position, renderer, this);
		}
		
	}
	
	public void drawText(SpriteBatch batch, BitmapFont font){
		String temp = "";
		if(sensor != null){
			temp += " T: " + sensor.target;
			temp += " P: " + String.format("%.2f", percentage);
		}
			
		font.draw(batch, "L: " + lux + "\n" + temp, position.x + 5, position.y + 20);
		for(LightFixture light : lights){
			light.drawText(position, batch, font);
		}
	}
}
