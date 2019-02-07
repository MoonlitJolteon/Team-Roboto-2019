package org.usfirst.frc.team447.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import org.usfirst.frc.team447.robot.*;
import org.usfirst.frc.team447.robot.commands.GearGrabberCommand;

/**
 *
 */
public class GearGrabberSubSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Solenoid
		gearDrop,
		gearClamp,
		gearRelease;
	
	DigitalInput gearPickupSwitch;
	
	
	public GearGrabberSubSystem() {
		
		gearDrop = new Solenoid(RobotMap.gearDrop);
		gearClamp = new Solenoid(RobotMap.gearClamp);
		gearRelease = new Solenoid(RobotMap.gearPassive);
		
		gearPickupSwitch = new DigitalInput(RobotMap.gearPickupSwitch);
		
	}
	
	public void clamp(boolean unclamp) {
		
		if(gearPickupSwitch.get() && !unclamp) {
			gearClamp.set(true);
		} else {
			gearClamp.set(false);
		}
	}
	
	public void drop(boolean pickup, boolean close, boolean drop) {
		
		if(pickup && !gearPickupSwitch.get()) {
			gearDrop.set(true);
			gearRelease.set(true);
		} else if(drop) {
			gearDrop.set(true);
			gearRelease.set(true);
			clamp(true);
		} else {
			gearDrop.set(false);
		}
		
		if(close) {
			gearRelease.set(false);
		}
	}
	
	public void stop() {
		gearClamp.set(false);
		gearDrop.set(false);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GearGrabberCommand());
    }
}

