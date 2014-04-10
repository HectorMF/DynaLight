package com.perfectplay.org.lights;

import com.perfectplay.org.LightFixture;

public class NonSensorCeilingLight extends LightFixture{

	@Override
	protected int calculateLumens(float percentage) {
		return 1000;
	}

}
