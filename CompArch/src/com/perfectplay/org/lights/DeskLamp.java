package com.perfectplay.org.lights;

import com.perfectplay.org.LightFixture;

public class DeskLamp extends LightFixture{

	@Override
	protected int calculateLumens(float percentage) {
		return 450;
	}

}
