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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.perfectplay.org.algorithms.BasicAlgorithm;
import com.perfectplay.org.lights.*;

public class DynaLight implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private ArrayList<Room> rooms;
	
	private Texture texture;
	private Sprite sprite;
	TextureRegion region;
	private BitmapFont font;
	
	private Skin skin;
	private Stage stage;
	
	private double time;
	public static boolean isRunning;
	
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
		font = new BitmapFont(Gdx.files.internal("data/arial.fnt"));
		
		texture = new Texture(Gdx.files.internal("sun.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		region = new TextureRegion(texture, 0, 0, 300, 300);
		int moveX = -2;
		int moveY = -13;
		
		time = 0;
		
		this.stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, this.batch);
		this.skin = new Skin(Gdx.files.internal("data/buttons.json"));
		Gdx.input.setInputProcessor(this.stage);
		
		Room hallway = new Room(6,26).setPosition(moveX + 10, moveY + 0);
		Room bedroom1 = new Room(10,10).setPosition(moveX, moveY);
		Room bedroom2 = new Room (10,16).setPosition(moveX +0,  moveY +10);
		Room bedroom3 = new Room (16,8).setPosition(moveX +0,  -8 + moveY);
		Room bedroom4 = new Room (10,12).setPosition(moveX +16,  -8 + moveY );
		Room bedroom5 = new Room (20,8).setPosition(moveX +0,  moveY +26);
		Room bathroom1 = new Room(10,6).setPosition(moveX +16,  moveY +4);
		Door bedroomDoor = new Door();
		Door bedroomDoor2 = new Door().setHorizontal();
		Door bedroomDoor3 = new Door();
		Door bedroomDoor4 = new Door();
		Door bedroomDoor5 = new Door().setHorizontal();
		Door bathroomDoor = new Door();
		
		bedroom1.addDoor(bedroomDoor);
		bedroomDoor.setPosition1(199,10);
		bedroomDoor.setPosition2(0,10);
		
		bedroomDoor2.setPosition1(60,0);
		bedroomDoor2.setPosition2(260,159);
		
		bedroomDoor3.setPosition1(0,170);
		bedroomDoor3.setPosition2(119,10);
		
		bedroomDoor4.setPosition1(0,260);
		bedroomDoor4.setPosition2(199,60);
		
		bedroomDoor5.setPosition1(10,519);
		bedroomDoor5.setPosition2(210,00);
		
		bathroomDoor.setPosition1(119,90);
		bathroomDoor.setPosition2(0,10);
		
		
		hallway.addDoor(bedroomDoor2);
		bedroom3.addDoor(bedroomDoor2);

		hallway.addDoor(bedroomDoor4);
		bedroom2.addDoor(bedroomDoor4);
		
		bedroom4.addDoor(bedroomDoor3);
		hallway.addDoor(bedroomDoor3);
		
		hallway.addDoor(bedroomDoor5);
		bedroom5.addDoor(bedroomDoor5);
		
		hallway.addDoor(bathroomDoor);
		bathroom1.addDoor(bathroomDoor);
		
		bedroom1.setSensor(new Sensor(new BasicAlgorithm(.1f)).setTargetLux(200));
		
		//add the lights
		bedroom1.addLight(new DeskLamp().setPosition(8.5f, 4f).setRadius(5));
		bedroom1.addLight(new DeskLamp().setPosition(8.5f, 9).setRadius(6));
		bedroom1.addLight(new CeilingLight().setPosition(5,5));
		
		bedroom1.addWindow(new Window());
		
		
		hallway.addLight(new NonSensorCeilingLight().setPosition(3, 13));
		hallway.addDoor(bedroomDoor);
		
		
		bedroom2.addLight(new StandingLamp().setPosition(1, 15).setRadius(7));
		bedroom2.addLight(new CeilingLight().setPosition(5,8));
		bedroom2.addLight(new DeskLamp().setPosition(8.5f, 6.5f).setRadius(5));
		
		bedroom2.addWindow(new Window());
		
		bedroom2.setSensor(new Sensor(new BasicAlgorithm(.1f)).setTargetLux(500));
		
		
		bedroom3.addLight(new CeilingLight().setPosition(8,4));
		bedroom3.addLight(new StandingLamp().setPosition(15,2).setRadius(7));
		
		bedroom3.addWindow(new Window());
		
		bedroom3.setSensor(new Sensor(new BasicAlgorithm(.1f)).setTargetLux(350));
		
		
		bedroom4.addLight(new CeilingLight().setPosition(5,6));
		bedroom4.addLight(new DeskLamp().setPosition(8.5f,11).setRadius(5));
		
		bedroom4.addWindow(new Window());
		
		bedroom4.setSensor(new Sensor(new BasicAlgorithm(.1f)).setTargetLux(450));
		
		
		bedroom5.addLight(new CeilingLight().setPosition(5,4));
		bedroom5.addLight(new CeilingLight().setPosition(15, 4));
		bedroom5.addLight(new StandingLamp().setPosition(1,7).setRadius(7));
		bedroom5.addLight(new DeskLamp().setPosition(18.5f, 4).setRadius(5));
		
		bedroom5.addWindow(new Window());
		bedroom5.addWindow(new Window());
		
		bedroom5.setSensor(new Sensor(new BasicAlgorithm(.1f)).setTargetLux(1000));
		
		
		bathroom1.addLight(new NonSensorCeilingLight().setPosition(5,3));
		

		rooms.add(hallway);
		rooms.add(bedroom1);
		rooms.add(bedroom2);
		rooms.add(bedroom3);
		rooms.add(bedroom4);
		rooms.add(bedroom5);
		rooms.add(bathroom1);
		StageComposer.compose(stage, skin);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
	}

	@Override
	public void render() {		
		

		if(isRunning){
			time += .0006;
			Sun.instance.setLumens((int) (Math.abs(7995 * Math.sin(time))));
			SensorController.instance.update();
		}
		
	//	Sun.instance.directLumens = (int) (5 + Math.abs(5 + 99995 * Math.sin(time))));

		
		
	//	Gdx.gl.glClearColor(.52f, .807f, .95f, 1);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		

		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		for(Room room : rooms)
		{
			room.draw(shapeRenderer);
			room.calculateLux();
		}
		stage.draw();
		shapeRenderer.end();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for(Room room : rooms)
			room.drawText(batch, font);
		///batch.draw(region, -350, 330, 0, 0, 100, 100, 1, 1, 0);
		//sprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, false);
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
