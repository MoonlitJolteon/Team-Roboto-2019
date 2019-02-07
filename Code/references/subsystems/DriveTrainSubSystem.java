package org.usfirst.frc.team447.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import org.usfirst.frc.team447.robot.*;
import org.usfirst.frc.team447.robot.commands.TeleopDriveCommand;

/**
 *
 */
public class DriveTrainSubSystem extends Subsystem {

	Spark
		leftDrive, 
		rightDrive;
	
	DifferentialDrive drive;
	
	DoubleSolenoid transmission;
	
	public DriveTrainSubSystem() {
		
		leftDrive = new Spark(RobotMap.leftDrive);
		rightDrive = new Spark(RobotMap.rightDrive);
		
		
		drive = new DifferentialDrive(leftDrive, rightDrive);
		
		transmission = new DoubleSolenoid(RobotMap.transLow, RobotMap.transHigh);
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		
		drive.tankDrive(leftSpeed, rightSpeed);
		
	}
	
	public void shiftGears(String gear) {
		gear = gear.toLowerCase();
		
		if(gear == "low") {
			transmission.set(DoubleSolenoid.Value.kForward);
		} else if (gear == "high") {
			transmission.set(DoubleSolenoid.Value.kReverse);
		} else {
			System.err.println("Please Select Low or High Gear");
		}
	}
	
	public void stop() {
		
		drive.tankDrive(0, 0);
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopDriveCommand());
    }
}

