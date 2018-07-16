package com.coding.Robot.service;


/*
 * This describes the basic faetures of the Robot exposed to the user
 */

public interface RobotPrototypeService {
	
	boolean walk(double distance);
	boolean carry(int weight);
	boolean walkAndCarry(double distance, int weight);
	void setCharging(int percentage);
	void displayPrice(String barcode);
}
