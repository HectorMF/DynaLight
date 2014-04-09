package com.perfectplay.org.algorithms;

import com.perfectplay.org.ISensorAlgorithm;

public class BasicAlgorithm implements ISensorAlgorithm{
	private int voltageDelta;
	
	public BasicAlgorithm(int voltageDelta){
		this.voltageDelta = voltageDelta;
	}
	
	@Override
	public int execute(int lux, int target, int voltage) {
		int diff = target - lux;
		
		return voltage + (diff * voltageDelta);

	}
}
