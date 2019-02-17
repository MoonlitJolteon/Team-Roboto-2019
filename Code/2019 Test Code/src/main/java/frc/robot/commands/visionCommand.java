/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class VisionCommand extends Command {
  public VisionCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.visionSub);
    requires(Robot.driveTrainSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    boolean onTarget = false;
    

    //SmartDashboard.putNumber("POV", OI.operator.getPOV());

    switch(OI.operator.getPOV()) {
      case 270:      
        onTarget = Robot.driveTrainSub.visionDrive(Robot.visionSub.aimLeft());
        break;
      case 90:
        onTarget = Robot.driveTrainSub.visionDrive(Robot.visionSub.aimRight());
        break;
      case 0:
        onTarget = Robot.driveTrainSub.visionDrive(Robot.visionSub.aimFront());
        break;
      default:
        onTarget = false;
        Robot.driveTrainSub.stop();
    }

    SmartDashboard.putBoolean("On Target", onTarget);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrainSub.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
   Robot.driveTrainSub.stop();
  }

}
