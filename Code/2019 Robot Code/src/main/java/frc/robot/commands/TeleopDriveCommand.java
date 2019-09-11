/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;
import frc.robot.utils.Utilities;

public class TeleopDriveCommand extends Command {
  public TeleopDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveTrainSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    HABClimb();
    autoClimb();
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
    Robot.driveTrainSub.stop();
  }

  private void drive() {
    double leftSpeed = -OI.leftStick.getY();
    double rightSpeed = -OI.rightStick.getY();

    if(OI.operator.getRawButton(10)) {
      Robot.driveTrainSub.tankDrive(-1, -1);
    } else {
      if(!OI.rightStick.getRawButton(1)) {
        Robot.driveTrainSub.tankDrive(-leftSpeed, -rightSpeed);
      } else {
        Robot.driveTrainSub.tankDrive(rightSpeed, leftSpeed);
      }
    }
    SmartDashboard.putBoolean("Above Line", Robot.driveTrainSub.aboveLine());
    Robot.driveTrainSub.transmission(OI.leftStick.getRawButton(1), OI.leftStick.getRawButton(2));
    //Robot.driveTrainSub.autoTrans();
  }

  private void HABClimb() {
    Robot.driveTrainSub.frontLift(OI.rightStick.getRawButton(2));
    Robot.driveTrainSub.backLift(OI.rightStick.getRawButton(3));
  }

  int stepNum = 10;
  private void nextStep() {
    Robot.driveTrainSub.resetDriveEncoders();
    stepNum = stepNum + 1;
  };

  private void autoClimb() {
    if(OI.operator.getRawButton(9)) {
      switch(stepNum) {
        case 0:
          System.out.println("YELL");
          Robot.driveTrainSub.transmission(false, true);
          Robot.driveTrainSub.frontLift(true);
          Robot.driveTrainSub.tankDrive(-0.5, -0.5);
          // if(Robot.driveTrainSub.encoderPositions()[0] <= -Utilities.inchToEncode(1)) {
          //   nextStep();
          // }
          break;
        case 1:
          Robot.driveTrainSub.tankDrive(0.5, 0.5);
          // if(Robot.driveTrainSub.encoderPositions()[0] >= Utilities.footToEncode(1)) {
          //   nextStep();
          // }
          break;
        case 3:
          Robot.driveTrainSub.tankDrive(1, 1);
          Robot.driveTrainSub.backLift(true);
          Robot.driveTrainSub.frontLift(false);
          break;
      }
    } else {
      drive();
      //stepNum = 0;
    }
  }
}
