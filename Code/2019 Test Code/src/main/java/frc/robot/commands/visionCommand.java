/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class visionCommand extends Command {
  public visionCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.visionSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    aimLeft();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
  private void aimLeft(){
    final int[] target = {90,110};
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        // turn left but NATHAN
      } else if (temp > target[1]){
        // turn right but NATHAN
      } else {
        // Drop the thing on the left
      }
    }
  }

}
