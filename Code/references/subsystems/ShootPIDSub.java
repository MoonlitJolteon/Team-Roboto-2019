package org.usfirst.frc.team447.robot.subsystems;

//import org.usfirst.frc.team447.robot.Robot;
import org.usfirst.frc.team447.robot.RobotMap;
import org.usfirst.frc.team447.robot.commands.BallCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class ShootPIDSub extends PIDSubsystem {

	 Spark 
		shooter,
		shaker;
	 
	 Encoder shootEncode;
	 
    // Initialize your subsystem here
    public ShootPIDSub() {
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	super("ShootPIDSub", 2.0, 0, 0);
    	setAbsoluteTolerance(0.05);
    	shooter = new Spark(RobotMap.shooter);
    	shaker = new Spark(RobotMap.shaker);
    	shootEncode = new Encoder(RobotMap.shootEncodeChanA, RobotMap.shootEncodeChanB);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BallCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return shootEncode.get();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	shooter.pidWrite(output);
    }

}
