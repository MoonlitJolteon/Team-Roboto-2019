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
import frc.robot.utils.Utilities;

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

    configureDriveTalons.configureTalon(leftDrive);
    configureDriveTalons.configureTalon(rightDrive);

  }

  public void tankDrive(double leftSpeed, double rightSpeed){
    leftDrive.set(ControlMode.PercentOutput, leftSpeed);
    rightDrive.set(ControlMode.PercentOutput, rightSpeed);

    averagedEncoderVelocity();
  }
  public void arrDrive(double[] arr){
    leftDrive.set(ControlMode.PercentOutput, arr[0]);
    rightDrive.set(ControlMode.PercentOutput, arr[1]);    
  }
  public boolean visionDrive(double[] arr){
    if (arr != null){
      leftDrive.set(ControlMode.PercentOutput, arr[0]);
      rightDrive.set(ControlMode.PercentOutput, arr[1]); 
      return (arr[0] == 0 && arr[1] == 0);
    }else{
      return false;
    }
  }

  public void driveToDist(double inch) {
    leftDrive.set(ControlMode.MotionMagic, Utilities.inchToEncode(inch));
    rightDrive.set(ControlMode.MotionMagic, Utilities.inchToEncode(inch));
  }

  public void resetDriveEncoders() {
    leftDrive.setSelectedSensorPosition(0);
    rightDrive.setSelectedSensorPosition(0);
  }

  public void transmission(boolean buttonOne, boolean buttonTwo) {
    if(buttonOne) {
      transmission.set(DoubleSolenoid.Value.kReverse);
    } else if(buttonTwo) {
      transmission.set(DoubleSolenoid.Value.kForward);
    }
  }

  public void autoTrans() {
    if(averagedEncoderVelocity() > 1670 && transmission.get() == DoubleSolenoid.Value.kForward) {
      transmission.set(DoubleSolenoid.Value.kReverse);
    } else if(averagedEncoderVelocity() < 1470 && transmission.get() == DoubleSolenoid.Value.kReverse) {
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
  
  private double averagedEncoderVelocity() {
    double output = 0;
    output += Math.abs(leftDrive.getSelectedSensorVelocity());
    output += Math.abs(rightDrive.getSelectedSensorVelocity());
    output /= 2;
    //System.out.println(output);
    return output;
  }
}
