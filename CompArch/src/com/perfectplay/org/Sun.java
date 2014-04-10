package com.perfectplay.org;

import com.badlogic.gdx.math.Vector2;

public class Sun {
	public static Sun instance = new Sun();
	
	public Vector2 position;
	
	public int ambientLumens = 0000;
	public int directLumens;
	
	private Sun(){}
	
}
