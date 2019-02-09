/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.*;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.motionProfileCal.*;

/**
 * Add your docs here.
 */
public class DriveTrainSubSystem extends Subsystem {

  TalonSRX     
    leftDrive,
    leftDriveB,
    rightDrive,
    rightDriveB;  // Declare talons.

  DoubleSolenoid transmission;
        
  public DriveTrainSubSystem() {
    leftDrive = new TalonSRX(RobotMap.leftDrive);
    leftDriveB = new TalonSRX(RobotMap.leftDriveB);

    rightDrive = new TalonSRX(RobotMap.rightDrive);
    rightDriveB = new TalonSRX(RobotMap.rightDriveB);

    leftDrive.setInverted(true);
    
    leftDriveB.follow(leftDrive);
    leftDriveB.setInverted(InvertType.FollowMaster);

    rightDriveB.follow(rightDrive);
    rightDriveB.setInverted(InvertType.FollowMaster);

    transmission = new DoubleSolenoid(RobotMap.transmissionLow, RobotMap.transmissionHigh);

    configureTalons.configureTalon(leftDrive);
    configureTalons.configureTalon(rightDrive);

  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    leftDrive.set(ControlMode.PercentOutput, leftSpeed);
    rightDrive.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void transmission(boolean buttonOne, boolean buttonTwo) {
    if(buttonOne) {
      transmission.set(DoubleSolenoid.Value.kReverse);
    } else if(buttonTwo) {
      transmission.set(DoubleSolenoid.Value.kForward);
    }
  }

  public void stop(){
    leftDrive.set(ControlMode.PercentOutput, 0);
    rightDrive.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDriveCommand());
  }
}
