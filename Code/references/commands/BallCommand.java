package org.usfirst.frc.team447.robot.commands;

import org.usfirst.frc.team447.robot.OI;
import org.usfirst.frc.team447.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallCommand extends Command {

    public BallCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shootSub);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	shoot();
    	shake();
    	intake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shootSub.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shootSub.stop();
    }
    
    private void shoot() {
    	if(OI.operator.getRawButton(7)) {
    		Robot.shootSub.shoot(0.8);
    	} else if(OI.operator.getRawButton(5)) {
    		Robot.shootSub.shoot(0.5);
    	} else {
    		Robot.shootSub.shoot(0);
    	}
    }
    
    private void shake() {
    	Robot.shootSub.shaker(OI.operator.getRawAxis(3));
    }
    
    private void intake() {

    	if(OI.operator.getPOV() == 0) {
    		Robot.shootSub.pickUp(true, false);
    	} else if(OI.operator.getPOV() == 180) {
    		Robot.shootSub.pickUp(true, true);
    	} else {
    		Robot.shootSub.pickUp(false, false);
    	}
    }
}
