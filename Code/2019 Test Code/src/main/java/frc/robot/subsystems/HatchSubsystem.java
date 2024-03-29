/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class HatchSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Solenoid hatchExtend;
  Solenoid hatchEject;

  public HatchSubsystem() {
    hatchExtend = new Solenoid(RobotMap.hatchExtend);
    hatchEject = new Solenoid(RobotMap.hatchEject);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TeleopHatchCommand());
  }

  public void hatchExtension(boolean button) {
    if(button) {
      hatchExtend.set(true);
    } else {
      hatchExtend.set(false);
    }
  }

  boolean prevEject = false;
  public void hatchEjection(boolean button) {
    /*if(button && !prevEject) {
      if(hatchEject.get()) {
        hatchEject.set(false);
      } else {
        hatchEject.set(true);
      }
      prevEject = true;
    } else if(!button && prevEject) {
      prevEject = false;
    }*/
    hatchEject.set(!button);
  }
  
  public void reset() {
    hatchEject.set(false);
    hatchExtend.set(false);
  }
}
