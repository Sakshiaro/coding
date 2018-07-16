package com.coding.Robot;

import org.junit.Test;

import com.coding.Robot.service.RobotPrototypeService;
import com.coding.Robot.service.impl.RobotPrototypeServiceImpl;


/**
 * Unit test for Robot App.
 */
public class AppTest 
{


	@Test
	public void roboTest() {
		RobotPrototypeService robotService = new RobotPrototypeServiceImpl();
		robotService.walk(3.5);
		robotService.carry(13);
		robotService.setCharging(40);
	
	}
}
