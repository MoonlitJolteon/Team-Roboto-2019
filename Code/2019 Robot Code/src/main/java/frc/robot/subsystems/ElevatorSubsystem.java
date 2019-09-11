/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.OI;
import frc.robot.commands.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.motionProfileCal.*;

/**
 * Add your docs here.
 */
public class ElevatorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  TalonSRX leadTalon;
  VictorSPX 
    follower1,
    follower2;

  public ElevatorSubsystem() {
    leadTalon = new TalonSRX(4);
    follower1 = new VictorSPX(5);
    follower2 = new VictorSPX(6);

    follower1.follow(leadTalon);
    follower2.follow(leadTalon);

    follower1.setInverted(InvertType.FollowMaster);
    follower2.setInverted(InvertType.FollowMaster);

    leadTalon.setNeutralMode(NeutralMode.Brake);

    leadTalon.setSelectedSensorPosition(0);

    configureElevatorTalon.configureTalon(leadTalon);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ElevatorCommand());
  }

  public void elevate(boolean up) {
    if(up) {
      leadTalon.set(ControlMode.PercentOutput, 0.2);
    } else {
      leadTalon.set(ControlMode.PercentOutput, -0.1);
    }
    
  }

  public void log() {
    //System.out.println(leadTalon.getSelectedSensorPosition());
  }

  public void goToPosition(int pos) {
    switch(pos) {
      case 1:
        leadTalon.set(ControlMode.MotionMagic, 0);
        break;
      case 2:
        leadTalon.set(ControlMode.MotionMagic, inchToElevate(26.5));
        break;
      case 3:
        leadTalon.set(ControlMode.MotionMagic, inchToElevate(40));
        break;
      default:
        //leadTalon.set(ControlMode.MotionMagic, 0);
        break;
    }
  }

  public void stop() {
    leadTalon.set(ControlMode.PercentOutput, 0);
  }

  private double inchToElevate(double inches) {
    return 1205.333d * inches;
  }
}
