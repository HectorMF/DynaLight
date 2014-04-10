package com.perfectplay.org;

import com.badlogic.gdx.math.Vector2;

public class Sun {
	public static Sun instance = new Sun();
	
	public Vector2 position;
	
	private int ambientLumens = 0000;
	public int directLumens;
	
	private Sun(){}
	
	public void setLumens(int lumens){
		StageComposer.sliderVal.setText(lumens + "");
		StageComposer.slider.setValue(lumens);
		ambientLumens = lumens;
	}
	
	public int getLumens(){
		return ambientLumens;
	}
	
}
