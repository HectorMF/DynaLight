package com.perfectplay.org;

public abstract class LightFixture {
	private int voltageIn;
	private int lumens;
	
	public LightFixture(){}
	
	public void setVoltage(int v){
		voltageIn = v;
	}
	
	public int getLumens()
	{
		this.lumens = calculateLumens(voltageIn);
		return lumens;
	}
	
	
	protected abstract int calculateLumens(float percentage);
}
