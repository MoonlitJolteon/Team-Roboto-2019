package org.usfirst.frc.team447.robot.subsystems;

import org.usfirst.frc.team447.robot.RobotMap;
import org.usfirst.frc.team447.robot.commands.BallCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallSubSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Spark 
		shooter,
		shaker;
	
	DoubleSolenoid ballIntake;
	
	Relay ballPickup;
	
	public BallSubSystem() {
		shooter = new Spark(RobotMap.shooter);
		shaker = new Spark(RobotMap.shaker);
		
		ballIntake = new DoubleSolenoid(RobotMap.ballIntake, RobotMap.ballIntake2);
		
		ballPickup = new Relay(RobotMap.ballPickup);
	}
	
	public void shoot(double speed) {
		shooter.set(speed);
	}
	
	public void shaker(double speed) {
		shaker.set(speed);
	}
	
	public void pickUp(boolean on, boolean forward) {
		if(on) {
			
			ballIntake.set(DoubleSolenoid.Value.kReverse);
			
			if(forward) {
				ballPickup.set(Relay.Value.kForward);
			} else {
				ballPickup.set(Relay.Value.kReverse);
			}
		} else {
			ballIntake.set(DoubleSolenoid.Value.kForward);
			ballPickup.set(Relay.Value.kOff);
		}
	}
	
	public void stop() {
		shaker.set(0);
		shooter.set(0);
		ballPickup.set(Relay.Value.kOff);
		ballIntake.set(DoubleSolenoid.Value.kForward);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BallCommand());
    }
}

