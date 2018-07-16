package com.coding.Robot;

public class Robot {
	public enum Color{
		GREEN,RED
	}
	private int chargeStatus;
	private Color headLightColor;
	private int weighingThreshold;
	private int walkingThreshold;
	private static final int MAX_WEIGHT = 10;
	private static final int MAX_WALK = 5;
	
	public Robot(){
		this.chargeStatus = 100;
		this.walkingThreshold = MAX_WALK;
		this.weighingThreshold = MAX_WEIGHT;
		this.headLightColor = Color.GREEN;
	}

	public int getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(int chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public Color getHeadLightColor() {
		return headLightColor;
	}

	public void setHeadLightColor(Color headLightColor) {
		this.headLightColor = headLightColor;
	}

	public int getWeighingThreshold() {
		return weighingThreshold;
	}

	public void setWeighingThreshold(int weighingThreshold) {
		this.weighingThreshold = weighingThreshold;
	}

	public int getWalkingThreshold() {
		return walkingThreshold;
	}

	public void setWalkingThreshold(int walkingThreshold) {
		this.walkingThreshold = walkingThreshold;
	}
	
	public boolean canWork(int requiredCharge){
		int availableCharge = getChargeStatus();
		if(availableCharge < requiredCharge){
			System.out.println("Battery not sufficiently charged");
			return false;
		}
		else{
			
			int remaining = availableCharge - requiredCharge;
			setChargeStatus(remaining);
			if(remaining < 15){
				System.out.println("Battery low");
				setHeadLightColor(Color.RED);
			}
			return true;
		}
	}
	
	public boolean canCarry(int weight){
		if(weight > this.weighingThreshold){
			System.out.println("Overweight");
			return false;
		}
		return true;
	}



}
