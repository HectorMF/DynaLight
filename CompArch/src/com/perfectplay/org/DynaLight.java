package com.perfectplay.org;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.perfectplay.org.algorithms.BasicAlgorithm;
import com.perfectplay.org.lights.CeilingLight;
import com.perfectplay.org.lights.DeskLamp;

public class DynaLight implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private ArrayList<Room> rooms;
	
	private Texture texture;
	private Sprite sprite;
	TextureRegion region;
	private BitmapFont font;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		this.rooms = new ArrayList<Room>();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(w,h);
		camera.position.set(0,0,0);
		//camera.
		camera.update();
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		texture = new Texture(Gdx.files.internal("sun.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		region = new TextureRegion(texture, 0, 0, 300, 300);
		int moveX = -5;
		int moveY = -12;
		
		Room hallway = new Room(6,26).setPosition(moveX + 10, moveY + 0);
		Room bedroom1 = new Room(10,10).setPosition(moveX, moveY);
		Room bedroom2 = new Room (10,16).setPosition(moveX +0,  moveY +10);
		Room bedroom3 = new Room (16,8).setPosition(moveX +0,  -8 + moveY);
		Room bedroom4 = new Room (10,12).setPosition(moveX +16,  -8 + moveY );
		Room bedroom5 = new Room (20,8).setPosition(moveX +0,  moveY +26);
		Room bathroom1 = new Room(10,6).setPosition(moveX +16,  moveY +4);
		Door bedroomDoor = new Door();
		
		bedroom1.addDoor(bedroomDoor);
		
		bedroom1.setSensor(new Sensor(new BasicAlgorithm(.1f)).setTargetLux(200));
		
		//add the lights
		bedroom1.addLight(new DeskLamp().setPosition(9,1).setRadius(5));
		bedroom1.addLight(new DeskLamp().setPosition(9, 9).setRadius(6));
		bedroom1.addLight(new CeilingLight().setPosition(5,5));
		
		bedroom1.addWindow(new Window());
		
		hallway.addLight(new CeilingLight().setPosition(3, 13));

		rooms.add(hallway);
		rooms.add(bedroom1);
		rooms.add(bedroom2);
		rooms.add(bedroom3);
		rooms.add(bedroom4);
		rooms.add(bedroom5);
		rooms.add(bathroom1);

	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		Sun.instance.ambientLumens += 10;
		
		if(Sun.instance.ambientLumens > 8000){
			Sun.instance.ambientLumens = 0;
		}
		
		
		SensorController.instance.update();
	//	Gdx.gl.glClearColor(.52f, .807f, .95f, 1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		

		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		for(Room room : rooms)
			room.draw(shapeRenderer);
		shapeRenderer.end();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Room room : rooms)
			room.drawText(batch, font);
		batch.draw(region, -350, 330, 0, 0, 100, 100, 1, 1, 0);
		//sprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update(true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
