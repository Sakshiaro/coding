/**
 * 
 */
package com.coding.Robot.service.impl;

import com.coding.Robot.Robot;
import com.coding.Robot.service.RobotPrototypeService;

/**
 * @author sakshi
 *
 */
public class RobotPrototypeServiceImpl implements RobotPrototypeService{
	
	private Robot robot;
	
	public RobotPrototypeServiceImpl() {
		this.robot = new Robot();
	}
	
	
	public boolean walk(double distance) {
		int requiredCharge = (int)(distance*100/robot.getWalkingThreshold());
		if(robot.canWork(requiredCharge) ) {
			System.out.println("Walked distance(km) : " + distance  );
			System.out.println("Charge consumed : " +  requiredCharge + "%");
			System.out.println("Charge remaining: " +  robot.getChargeStatus()+ "%");
			System.out.println("Robot HeadLight Color : "+ robot.getHeadLightColor());
		}
		 
		return false;
	}

	public boolean carry(int weight) {
		if(robot.canCarry(weight)){
			int requiredCharge = 2*weight;
	
			if(robot.canWork(requiredCharge)){
				System.out.println("Carried weight(kg) : "+ weight );
				System.out.println("Charge consumed : " +  requiredCharge + "%");
				System.out.println("Charge remaining : " +  robot.getChargeStatus()+ "%");
				System.out.println("Robot HeadLight Color : "+ robot.getHeadLightColor());
				return true;
			}
		}
		return false;
	}

	public boolean walkAndCarry(double distance, int weight) {
		if(robot.canCarry(weight)){
			int requiredCharge = (int)(distance*100/robot.getWalkingThreshold()) + 2*weight;
			if(robot.canWork(requiredCharge)){
				System.out.println("Walked distance(km) : " + distance + " while carrying weight(kg) : "+ weight );
				System.out.println("Charge consumed : " +  requiredCharge + "%");
				System.out.println("Charge remaining : " +  robot.getChargeStatus()+ "%");
				System.out.println("Robot HeadLight Color : "+ robot.getHeadLightColor());
				return true;
			}
		}
		return false;
	}

	public void setCharging(int percentage) {
		int currentCharge = robot.getChargeStatus();
		int newCharge = currentCharge + percentage ;
		if(newCharge > 100)
			robot.setChargeStatus(100);
		else{
			robot.setChargeStatus(newCharge);
		}
		
	}

	public void displayPrice(String barcode) {
		try {
			int code = Integer.parseInt(barcode);
			System.out.println("Price is XXX");

		} catch(NumberFormatException e) {
			System.out.println("Scan Failure");
		}

	}

}
