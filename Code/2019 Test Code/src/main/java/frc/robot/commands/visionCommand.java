/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

/*public class VisionCommand extends Command {
  public visionCommand() {
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
    if (OI.operator.getPOV()==270){
      aimLeft();
    } else if (OI.operator.getPOV()==90){
      aimRight();
    } else if (OI.operator.getRawButton(1)){
      aimFront();
    }
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
    final double speed = 0.5;
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        Robot.driveTrainSub.tankDrive(-speed, speed);
      } else if (temp > target[1]){
        Robot.driveTrainSub.tankDrive(speed, -speed);
      } else {
        // Drop the thing on the left
      }
    }
  }
private void aimRight(){
  final int[] target = {90,110};
  final double speed = 0.5;
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        Robot.driveTrainSub.tankDrive(-speed, speed);
      } else if (temp > target[1]){
        Robot.driveTrainSub.tankDrive(speed, -speed);
      } else {
        // Drop the thing on the right
      }
    }
}
private void aimFront(){
  final int[] target = {90,110};
  final double speed = 0.5;
    if (Robot.visionSub.visionData.get("tape-x")!="none"){
      int temp = Integer.parseInt(Robot.visionSub.visionData.get("tape-x"));
      if (temp < target[0] ){
        Robot.driveTrainSub.tankDrive(-speed, speed);
      } else if (temp > target[1]){
        Robot.driveTrainSub.tankDrive(speed, -speed);
      } else {
        // Drop the thing in the front
      }
  }
}
}*/
