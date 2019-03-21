/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team447.robot;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	
	public static int
	
	// PWMs
		leftDrive = 0,
		rightDrive = 1,
		shootLoad = 2,
		shooter = 3,
		shaker = 4,
		climber = 5,
		
	//Relays
		ballPickup = 0, 
		lights = 1,
		
	//DIO
		shootEncodeChanA = 4, 
		shootEncodeChanB = 5,
		
		gearPoleSwitch = 6,
		gearPickupSwitch = 7,
		
	//Pneumatics
		transLow = 0,
		transHigh = 1,
		gearPassive = 2,
		ballIntake = 3,
		ballIntake2 = 4,
		gearClamp = 5,
		gearDrop = 6,
		
	//Dummy Variables
		fRight = 6,
		fLeft = 7,
		rRight = 8,
		rLeft = 9,

	//Buttons
		gearHigh=1,
		gearLow=2,
		gearClampB=1,
		gearPickUp=2,
		//gearPickUp=4,
		gearClose=4,
		//gearClose=3,
		gearDropB=3;
		//gearDropB=5;
}
