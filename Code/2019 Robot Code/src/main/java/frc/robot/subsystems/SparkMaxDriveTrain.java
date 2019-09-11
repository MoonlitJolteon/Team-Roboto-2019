/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalGlitchFilter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.*;
import frc.robot.commands.*;
/**
 * Add your docs here.
 */
public class SparkMaxDriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  
  DifferentialDrive drive;
  CANSparkMax 
    leftMotor,
    leftMotorB,
    rightMotor,
    rightMotorB;
  DoubleSolenoid transmission;
  Solenoid
    climbFront,
    climbBack;
  CANEncoder
    leftEncoder,
    rightEncoder;
  DigitalInput tapeSensor;

  public SparkMaxDriveTrain() {
    leftMotor = new CANSparkMax(3, MotorType.kBrushless);
    leftEncoder = leftMotor.getEncoder();
    leftMotorB = new CANSparkMax(4, MotorType.kBrushless);
    
    rightMotor = new CANSparkMax(1, MotorType.kBrushless);
    rightEncoder = rightMotor.getEncoder();
    rightMotorB = new CANSparkMax(2, MotorType.kBrushless);

    transmission = new DoubleSolenoid(RobotMap.transmissionLow, RobotMap.transmissionHigh);

    climbFront = new Solenoid(1, RobotMap.liftFront);
    climbBack = new Solenoid(1, RobotMap.liftBack);

    leftMotorB.follow(leftMotor);
    rightMotorB.follow(rightMotor);

    drive = new DifferentialDrive(leftMotor, rightMotor);

    tapeSensor = new DigitalInput(0);
  }
  public void tankDrive(double leftSpeed, double rightSpeed){
    drive.tankDrive(leftSpeed, rightSpeed);
    averagedEncoderVelocity();
  }
  public void arrDrive(double[] arr){
    drive.tankDrive(arr[0], arr[1]);
  }
  public boolean visionDrive(double[] arr){
    if (arr != null){
      drive.tankDrive(arr[0], arr[1]);
      return (arr[0] == 0 && arr[1] == 0);
    } else {
      return false;
    }
  }

  // public void driveToDist(double inch) {
  //   leftDrive.set(ControlMode.MotionMagic, Utilities.inchToEncode(inch));
  //   rightDrive.set(ControlMode.MotionMagic, Utilities.inchToEncode(inch));
  // }

  // public void driveToPos(double inch) {
  //   leftDrive.set(ControlMode.Position, Utilities.inchToEncode(inch));
  //   rightDrive.set(ControlMode.Position, Utilities.inchToEncode(inch));
  // }

  public void resetDriveEncoders() {
    //leftMotor
    //rightDrive
  }

  public void transmission(boolean buttonOne, boolean buttonTwo) {
    if(buttonOne) {
      transmission.set(DoubleSolenoid.Value.kReverse);
    } else if(buttonTwo) {
      transmission.set(DoubleSolenoid.Value.kForward);
    }
  }

  public boolean aboveLine() {
    return tapeSensor.get();
  }

  // public void autoTrans() {
  //   if(averagedEncoderVelocity() > 1670 && transmission.get() == DoubleSolenoid.Value.kForward) {
  //     transmission.set(DoubleSolenoid.Value.kReverse);
  //   } else if(averagedEncoderVelocity() < 1470 && transmission.get() == DoubleSolenoid.Value.kReverse) {
  //     transmission.set(DoubleSolenoid.Value.kForward);
  //   }
  // }

  public void stop(){
    drive.tankDrive(0, 0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDriveCommand());
  }
  
  private double averagedEncoderVelocity() {
    double output = 0;
    output += leftEncoder.getPosition();
    output += rightEncoder.getPosition();
    output /= 2;
    //System.out.println(output);
    return output;
  }

  public double[] encoderPositions() {
    double[] output = new double[]{
      leftEncoder.getPosition(),
      rightEncoder.getPosition()
    };
    return output;
  }

  boolean prevFront = false;
  public void frontLift(boolean on) {
    /*if(on && !prevFront) {
      if(climbFront.get()) {
        climbFront.set(false);
      } else {
        climbFront.set(true);
      }
      prevFront = true;
    } else if(!on && prevFront) {
      prevFront = false;
    }*/

    climbFront.set(on);
  }

  boolean prevBack = false;
  public void backLift(boolean on) {
    /*if(on && !prevBack) {
      if(climbBack.get()) {
        climbBack.set(false);
      } else {
        climbBack.set(true);
      }
      prevBack = true;
    } else if(!on && prevBack) {
      prevBack = false;
    }*/
    climbBack.set(on);
  }
}
