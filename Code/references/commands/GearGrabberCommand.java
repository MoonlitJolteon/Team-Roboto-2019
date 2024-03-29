package org.usfirst.frc.team447.robot.commands;

import org.usfirst.frc.team447.robot.OI;
import org.usfirst.frc.team447.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearGrabberCommand extends Command {

    public GearGrabberCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gearSubSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.gearSubSystem.clamp(OI.operator.getRawButton(1));
    	Robot.gearSubSystem.drop(OI.operator.getRawButton(2), OI.operator.getRawButton(4), OI.operator.getRawButton(3));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearSubSystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.gearSubSystem.stop();
    }
}
