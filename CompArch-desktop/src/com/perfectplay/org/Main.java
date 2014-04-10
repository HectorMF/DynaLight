package com.perfectplay.org;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Dynamic Lighting System";
		cfg.useGL20 = true;
		
		new LwjglApplication(new DynaLight(), cfg);
	}
}
