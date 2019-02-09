/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int ballIntake = 0; // Ball intake spark, PWM channel 0.

  //Drive Motors
  public static final int 
    leftDrive = 0, // Left drive motor talon, CAN channel 0.
    leftDriveB = 1, // Left drive motor talon B, CAN channel 1.
    rightDrive = 2, // Right drive motor talon, CAN channel 2.
    rightDriveB = 3; // Right drive motor talon B, CAN channel 3.

  //Servos
  public static final int pixyServo = 4; // Servo PWM channel.

  // PCM Channels (pneumatics)
  public static final int 
    transmissionLow = 0, // Drive transmission low channel, PCM channel 0.
    transmissionHigh = 1, // Drive transmission high channel, PCM channel 1.
    outtakeLeft = 2, // Ball left outtake, PCM channel 2.
    outtakeRight = 3; // Ball right outtake, PCM channel 3.
}
