/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class BallCommand extends Command {
  public BallCommand() {
    requires(Robot.ballSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    intake();
    outtake();
    bump();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ballSub.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.ballSub.stop();
  }

  private void intake() {
    if(OI.operator.getRawButton(2)) {
      Robot.ballSub.intake(1);
    } else if(OI.operator.getRawButton(3)) {
      Robot.ballSub.intake(-1);
    } else {
      Robot.ballSub.intake(0);
    }
  }

  private void bump() {
    Robot.ballSub.bump(OI.operator.getRawButton(6));
  }

  private void outtake() {
    if(OI.operator.getRawButton(7)) {
      Robot.ballSub.rightOuttake(true);
    } else {
      Robot.ballSub.rightOuttake(false);
    }

    if(OI.operator.getRawButton(8)) {
      Robot.ballSub.leftOuttake(true);
    } else {
      Robot.ballSub.leftOuttake(false);
    }
  }
}
