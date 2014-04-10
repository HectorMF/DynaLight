package com.perfectplay.org.algorithms;

import com.perfectplay.org.ISensorAlgorithm;

public class BasicAlgorithm implements ISensorAlgorithm{
	private float voltageDelta;
	
	public BasicAlgorithm(float voltageDelta){
		this.voltageDelta = voltageDelta;
	}
	
	@Override
	public float execute(int lux, int target, float voltage) {
		//int diff = target - lux;
		int diff = 0;
		if(target > lux)
			diff = 1;
		if(target < lux)
			diff = -1;
		
		
		float temp = voltage + (diff * voltageDelta);
		if(temp > 120) return 120;
		if(temp < 0) return 0;
		return temp;

	}
}
