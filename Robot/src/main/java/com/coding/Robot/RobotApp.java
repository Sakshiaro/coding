package com.coding.Robot;

import com.coding.Robot.service.RobotPrototypeService;
import com.coding.Robot.service.impl.RobotPrototypeServiceImpl;

public class RobotApp 
{
    	public static void main(String[] args)
    	{
    		RobotPrototypeService robot = new RobotPrototypeServiceImpl();
    		robot.walk(3.5);
    		robot.walkAndCarry(2,3);
    		robot.carry(12);
    		/*robot.carry(5);
    		robot.walkAndCarry(1,12);
    		robot.setCharging(80);
    		robot.walkAndCarry(3,2);
    		robot.displayPrice("90");
    		robot.walkAndCarry(1,2);*/
    	}
    
}
