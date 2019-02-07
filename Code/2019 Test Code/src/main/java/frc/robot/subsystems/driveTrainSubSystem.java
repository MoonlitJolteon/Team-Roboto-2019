/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//import frc.robot.*;
import frc.robot.commands.TeleopDriveCommand;

/**
 * Add your docs here.
 */
public class driveTrainSubSystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Spark
    leftDrive,
    rightDrive;

    DifferentialDrive drive;

    //DoubleSolenoid transmission;

    public driveTrainSubSystem() {
      leftDrive = new Spark(0);
      rightDrive = new Spark(1);

      drive = new DifferentialDrive(leftDrive, rightDrive);
    }
    public void tankDrive(double leftSpeed, double rightSpeed){
      drive.tankDrive(leftSpeed, rightSpeed);
    }
    public void stop(){
      drive.tankDrive(0,0);
    }
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDriveCommand());
  }
}
