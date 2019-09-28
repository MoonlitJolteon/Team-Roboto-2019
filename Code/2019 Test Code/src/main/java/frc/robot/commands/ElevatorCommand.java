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

public class ElevatorCommand extends Command {
  public ElevatorCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevatorSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    elevateAtSpeed();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevatorSub.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.elevatorSub.stop();
  }

  private void elevateAtSpeed() {
     
    toPot();
    //fromSpeed(); // for testing

    Robot.elevatorSub.log();
  }

  // private void fromSpeed() {
  //   if(OI.operator.getY() > 0.5) {
  //     Robot.elevatorSub.elevate(false);
  //   } else if(OI.operator.getY() < -0.5) {
  //     Robot.elevatorSub.elevate(true);
  //   } else {
  //     Robot.elevatorSub.stop();
  //   }
  // }

  private void toPot() {
    switch(OI.operator.getPOV()) {
      case 180:
        Robot.elevatorSub.goToPosition(1);
        break;
      case 90:
        Robot.elevatorSub.goToPosition(2);
        break;
      case 0:
        Robot.elevatorSub.goToPosition(3);
        break;
      default:
        Robot.elevatorSub.goToPosition(-1);
        break;
    }
  }
}
