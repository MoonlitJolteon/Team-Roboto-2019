package org.usfirst.frc.team447.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team447.robot.*;

/**
 *
 */
public class TeleopDriveCommand extends Command {

    public TeleopDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	drive();
    	//mecanumDrive();
    	transmission();
    	
    }

	// Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainSub.stop();
    }
   
    //@SuppressWarnings("unused")
	private void drive() {
		if(OI.leftDrive.getRawButton(1) && OI.leftDrive.getRawButton(2)) {
    		Robot.driveTrainSub.tankDrive(-OI.rightDrive.getY(), -OI.leftDrive.getY());
    	} else {
    		Robot.driveTrainSub.tankDrive(OI.leftDrive.getY(), OI.rightDrive.getY());
    	}
	}
    
    private void transmission() {
    	
    	if(OI.rightDrive.getRawButton(RobotMap.gearHigh)) {
    		Robot.driveTrainSub.shiftGears("high");
    	} else if(OI.rightDrive.getRawButton(RobotMap.gearLow)) {
    		Robot.driveTrainSub.shiftGears("low");
    	}
    }
}
