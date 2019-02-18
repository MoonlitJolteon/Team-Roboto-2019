/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

/*
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TeleopDriveToWall extends Command {

  double distance = 0;
  double curDistance = 0;
  double distanceFromWall = 20;
  boolean runBefore = false;

  public TeleopDriveToWall() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrainSub);
    requires(Robot.driveTrainSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    driveToWall();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(curDistance <= distanceFromWall && runBefore && curDistance > 0) {
      return true;
    } else {
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    if(curDistance <= distanceFromWall && runBefore) {
      runBefore = false;
      System.out.println("Done Running");
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private void driveToWall() {
    if(runBefore == false) {
      distance = Robot.visionSub.getValue("lidar");
      Robot.driveTrainSub.resetDriveEncoders();

      if(distance > 0) {
        runBefore = true;
      } else {
        runBefore = false;
      }
    } else {
      Robot.driveTrainSub.driveToDist(-(distance - distanceFromWall - 1));
      curDistance = Robot.visionSub.getValue("lidar");
      
    }
  }
}
*/