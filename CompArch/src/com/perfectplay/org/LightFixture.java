package com.perfectplay.org;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class LightFixture {
	public final Vector2 position;
	private int radius;
	
	private float percentage;
	private int lumens;
	
	public LightFixture(){
		this.position = new Vector2();
		this.radius = 20;
	}
	
	public void dim(float percentage){
		this.percentage = percentage;
	}
	
	public LightFixture setPosition(int x, int y){
		this.position.set(x*20,y*20);
		return this;
	}
	
	public LightFixture setRadius(int radius){
		this.radius = radius;
		return this;
	}
	
	public int getLumens()
	{
		this.lumens = calculateLumens(percentage);
		return lumens;
	}
	
	public void draw(Vector2 pos, ShapeRenderer renderer){
		renderer.circle(pos.cpy().add(position).x, pos.cpy().add(position).y, radius);
	}
	
	public void drawText(Vector2 pos, SpriteBatch batch, BitmapFont font){
		font.draw(batch, String.format("%04d", lumens), pos.cpy().add(position).x - 15, pos.cpy().add(position).y - radius - 3 );
	}
	
	protected abstract int calculateLumens(float percentage);
}
