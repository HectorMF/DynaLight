package com.perfectplay.org.lights;

import com.perfectplay.org.LightFixture;

public class CeilingLight extends LightFixture{

	@Override
	protected int calculateLumens(float percentage) {
		return (int) (percentage * 2000);
	}

}
